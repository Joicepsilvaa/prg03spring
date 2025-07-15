package br.com.ifba.curso.view;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import br.com.ifba.curso.controller.CursoIController;
import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.view.components.BotaoRendererEditor;
import jakarta.annotation.PostConstruct;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
/**
 *
 * @author Joice
 */
@Component
public final class CursoListar extends javax.swing.JFrame {
    
    @Autowired
    private CursoIController cursoController;
    
    @Autowired
    private ApplicationContext applicationContext;

    public CursoListar() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    @PostConstruct
    private void init() {
        configurarTabela();
        carregarDados();
    }

    private void configurarTabela() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "CÓDIGO", "NOME", "COORDENADOR", "EDITAR", "REMOVER"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4 || column == 5;
            }
        };

        tblCursos.setModel(model);
        tblCursos.setRowHeight(40);

        // Cria e configura o RENDERER para o botão "Editar"
        BotaoRendererEditor editarRenderer = applicationContext.getBean(BotaoRendererEditor.class);
        editarRenderer.setTipo("Editar"); // Define o tipo para "Editar"
        tblCursos.getColumnModel().getColumn(4).setCellRenderer(editarRenderer);

        // Cria e configura o EDITOR para o botão "Editar"
        BotaoRendererEditor editarEditor = applicationContext.getBean(BotaoRendererEditor.class);
        editarEditor.setTipo("Editar"); // Define o tipo para "Editar"
        editarEditor.setTabela(tblCursos);
        editarEditor.setCursoListar(this);
        tblCursos.getColumnModel().getColumn(4).setCellEditor(editarEditor);

        // Cria e configura o RENDERER para o botão "Remover"
        BotaoRendererEditor removerRenderer = applicationContext.getBean(BotaoRendererEditor.class);
        removerRenderer.setTipo("Remover"); // Define o tipo para "Remover"
        tblCursos.getColumnModel().getColumn(5).setCellRenderer(removerRenderer);

        // Cria e configura o EDITOR para o botão "Remover"
        BotaoRendererEditor removerEditor = applicationContext.getBean(BotaoRendererEditor.class);
        removerEditor.setTipo("Remover"); // Define o tipo para "Remover"
        removerEditor.setTabela(tblCursos);
        removerEditor.setCursoListar(this);
        tblCursos.getColumnModel().getColumn(5).setCellEditor(removerEditor);
    }

    public void atualizarTabela(List<Curso> cursos) {
        DefaultTableModel model = (DefaultTableModel) tblCursos.getModel();
        model.setRowCount(0);

        for (Curso curso : cursos) {
            model.addRow(new Object[]{
                curso.getId(),
                curso.getCodigoCurso(),
                curso.getNome(),
                curso.getCoordenador(),
                "Editar", 
                "Remover"
            });
        }
    }

    public void carregarDados() {
        try {
            List<Curso> cursos = cursoController.findAll();
            atualizarTabela(cursos);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar cursos: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void buscarPorNome(String nome) {
        try {
            List<Curso> cursos = cursoController.findByNome(nome);
            if (cursos.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Nenhum curso encontrado com o termo: " + nome,
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                atualizarTabela(cursos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao buscar cursos: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public void removerCurso(long id) {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Deseja realmente remover o curso com ID " + id + "?",
            "Confirmação de Remoção",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Curso cursoParaRemover = cursoController.findById(id); 

                if (cursoParaRemover != null) {
                    cursoController.delete(cursoParaRemover);
                    JOptionPane.showMessageDialog(this, "Curso removido com sucesso!");
                    carregarDados();
                } else {
                    JOptionPane.showMessageDialog(this, "Curso não encontrado para remoção.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Erro ao remover curso: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    public void editarCurso(long id) {
        try {
            Curso curso = cursoController.findById(id);
            if (curso != null) {
                CursoEditar editar = applicationContext.getBean(CursoEditar.class);
                editar.setCurso(curso); 
                editar.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        carregarDados();
                    }
                });
                editar.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Curso não encontrado para edição!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar curso para edição: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnGeral = new javax.swing.JPanel();
        scpnTabelaCurso = new javax.swing.JScrollPane();
        tblCursos = new javax.swing.JTable();
        lblDescricao = new javax.swing.JLabel();
        txtPesquisar = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnGeral.setBackground(new java.awt.Color(153, 205, 133));
        pnGeral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scpnTabelaCurso.setBackground(new java.awt.Color(127, 166, 83));
        scpnTabelaCurso.setForeground(new java.awt.Color(127, 166, 83));

        tblCursos.setBackground(new java.awt.Color(127, 166, 83));
        tblCursos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(99, 120, 61)));
        tblCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "CÓDIGO", "NOME", "COORDENADOR", "EDITAR", "REMOVER"
            }
        ));
        scpnTabelaCurso.setViewportView(tblCursos);

        pnGeral.add(scpnTabelaCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 490, 230));

        lblDescricao.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDescricao.setForeground(new java.awt.Color(0, 51, 51));
        lblDescricao.setText("GERENCIAMENTO DE CURSOS");
        pnGeral.add(lblDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));
        pnGeral.add(txtPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 160, 50));

        btnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ifba/curso/images/cadastrar.png"))); // NOI18N
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });
        pnGeral.add(btnCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ifba/curso/images/pesquisar.png"))); // NOI18N
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        pnGeral.add(btnPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnGeral, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnGeral, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        CursoCadastro cadastro = applicationContext.getBean(CursoCadastro.class);
        cadastro.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                carregarDados();
            }
        });
        cadastro.setVisible(true);
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        String termo = txtPesquisar.getText().trim();
        if (termo.isEmpty()) {
            carregarDados();
        } else {
            buscarPorNome(termo);
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JPanel pnGeral;
    private javax.swing.JScrollPane scpnTabelaCurso;
    private javax.swing.JTable tblCursos;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}
