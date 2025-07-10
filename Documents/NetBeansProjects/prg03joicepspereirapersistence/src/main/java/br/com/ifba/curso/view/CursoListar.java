package br.com.ifba.curso.view;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import br.com.ifba.curso.controller.CursoController;
import br.com.ifba.curso.view.components.BotaoRendererEditor;
import br.com.ifba.curso.entity.Curso;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Joice
 */
public final class CursoListar extends javax.swing.JFrame {
    private final CursoController cursoController = new CursoController();
    
    public CursoListar() {
        
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        configurarTabela();
        carregarDados();
    }
    
// Configura como a tabela vai mostrar os dados
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
    
    // Configura os botões de ação na tabela
    tblCursos.getColumn("EDITAR").setCellRenderer(new BotaoRendererEditor("Editar", tblCursos));
    tblCursos.getColumn("EDITAR").setCellEditor(new BotaoRendererEditor("Editar", tblCursos));
    
    tblCursos.getColumn("REMOVER").setCellRenderer(new BotaoRendererEditor("Remover", tblCursos));
    tblCursos.getColumn("REMOVER").setCellEditor(new BotaoRendererEditor("Remover", tblCursos));
}

    // Atualiza os dados na tabela
    public void atualizarTabela(List<Curso> cursos) {
        //pega o modelo da tabela
        DefaultTableModel model = (DefaultTableModel) tblCursos.getModel();
        //remove todos os registros atuais
        model.setRowCount(0);
            // Adiciona cada curso como uma nova linha
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
    // Busca todos os cursos no banco
    public void carregarDados() {
        try {
            // Cria um novo controller para garantir nova sessão
            CursoController tempController = new CursoController();
            List<Curso> cursos = tempController.findAll();
            atualizarTabela(cursos);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar: " + e.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Busca cursos pelo nome
    private void buscarPorNome(String nome) {
        try {
            //faz a busca via controller
            List<Curso> cursos = cursoController.findByNome(nome);
            if (cursos.isEmpty()) {
                //mensagem caso não encontre nenhum curso com o termo
                JOptionPane.showMessageDialog(this, "Nenhum curso encontrado com o termo: " + nome, "Aviso",  JOptionPane.INFORMATION_MESSAGE);
            } else {
                //atualiza a tabela com os dados encotrados
                atualizarTabela(cursos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao buscar cursos: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Remove um curso pelo ID
    public void removerCurso(long id) {
        try {
            // Cria um controller temporário só para encontrar o curso
            CursoController tempController = new CursoController();
            Curso curso = tempController.findById(id);

            if (curso != null) {

                    // Cria um novo controller especifico para a excluir
                    CursoController deleteController = new CursoController();
                    deleteController.delete(curso);

                    JOptionPane.showMessageDialog(this, "Curso removido com sucesso!");

                    // Atualiza a tabela com um novo controller
                    carregarDados();
            } else {
                JOptionPane.showMessageDialog(this, "Curso não encontrado!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao remover curso: " + e.getMessage(),  "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para atualizar a lista após cadastro/edição
    public void atualizarLista() {
        carregarDados();
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
        // Cria uma nova instância da tela de cadastro
        CursoCadastro cadastro = new CursoCadastro();
        // Adiciona um listener para quando a janela for fechada
        cadastro.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                // Recarrega os dados da tabela quando o cadastro é fechado
                carregarDados();
            }
        });
        // Exibe a tela de cadastro
        cadastro.setVisible(true);
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        // Pega o termo de pesquisa digitado 
        String termo = txtPesquisar.getText().trim();
        // Verifica se o campo de pesquisa ta vazio
        if (termo.isEmpty()) {
            // Mostra todos os cursos se tiver vazio
            carregarDados();
        } else {
            // Caso contrário, realiza a pesquisa por nome
            buscarPorNome(termo);
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CursoListar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CursoListar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CursoListar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CursoListar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CursoListar().setVisible(true);
            }
        });
    }

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
