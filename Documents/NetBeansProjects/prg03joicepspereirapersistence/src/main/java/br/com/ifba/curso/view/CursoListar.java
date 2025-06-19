package br.com.ifba.curso.view;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import br.com.ifba.curso.view.components.BotaoRendererEditor;
import br.com.ifba.curso.entity.Curso;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Joice
 */
public class CursoListar extends javax.swing.JFrame {
    // Gerenciadores de entidade JPA
    private EntityManager em;
    private EntityManagerFactory emf;
    /**
     * Creates new form teste
     */
    public CursoListar() {
        // Inicializa JPA criando o EntityManagerFactory com a unidade de persistência definida
        emf = Persistence.createEntityManagerFactory("prg03persistenciaPU");
        // Cria o EntityManager para operações no banco de dados
        em = emf.createEntityManager();
        
        initComponents();
        configurarTabela();
        carregarDados();
    }
        //Configura a tabela de cursos com colunas personalizadas e botões editar e remover
        private void configurarTabela() {
        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"CODIGO", "NOME", "COORDENADOR", "EDITAR", "REMOVER"}, 0) {
            // Sobrescreve o método para tornar apenas as colunas de ação editáveis
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3 || column == 4; // colunas "EDITAR" e "REMOVER"
            }
        };
        // Aplica o modelo à tabela
        tblCursos.setModel(model);
        // Define a altura das linhas
        tblCursos.setRowHeight(40);
        
        // Configura a coluna "EDITAR" com renderizador e editor personalizado
        tblCursos.getColumn("EDITAR").setCellRenderer(new BotaoRendererEditor("Editar", tblCursos));
        tblCursos.getColumn("EDITAR").setCellEditor(new BotaoRendererEditor("Editar", tblCursos));
        
        // Configura a coluna "REMOVER" com renderizador e editor personalizado
        tblCursos.getColumn("REMOVER").setCellRenderer(new BotaoRendererEditor("Remover", tblCursos));
        tblCursos.getColumn("REMOVER").setCellEditor(new BotaoRendererEditor("Remover", tblCursos));
    }

    //Carrega os dados dos cursos do banco de dados
    private void carregarDados() {
        // Busca todos os cursos no banco
        List<Curso> cursos = buscarTodos();
        
        // Atualiza a tabela com os cursos encontrados
        atualizarTabela(cursos);
    }
    //Atualiza a tabela com a lista de cursos 
    private void atualizarTabela(List<Curso> cursos) {
        // Obtém o modelo da tabela
        DefaultTableModel model = (DefaultTableModel) tblCursos.getModel();
        // Limpa todas as linhas existentes
        model.setRowCount(0);
        
        // Adiciona cada curso como uma nova linha na tabela
        for (Curso curso : cursos) {
            model.addRow(new Object[]{
                curso.getId(),
                curso.getNome(),
                curso.getCoordenador(),
                "Editar",
                "Remover"
            });
        }
    }
    //Busca cursos por nome no banco de dados
    private List<Curso> buscarPorNome(String nome) {
        try {
            // Cria a consulta JPQL para busca por nome
            String jpql = "SELECT c FROM Curso c WHERE c.nome LIKE :nome";
            TypedQuery<Curso> query = em.createQuery(jpql, Curso.class);
            query.setParameter("nome", "%" + nome + "%");
            return query.getResultList();
        } catch (Exception e) {
            // Mostra mensagem de erro em caso de falha
            JOptionPane.showMessageDialog(this, "Erro ao buscar cursos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    //Busca todos os cursos cadastrados no banco de dados.
    private List<Curso> buscarTodos() {
        try {
            // Cria consulta JPQL para buscar todos os cursos
            String jpql = "SELECT c FROM Curso c";
            TypedQuery<Curso> query = em.createQuery(jpql, Curso.class);
            // Executa a consulta e retorna os resultados
            return query.getResultList();
        } catch (Exception e) {
            // Mostra mensagem de erro em caso de falha
            JOptionPane.showMessageDialog(this, "Erro ao buscar cursos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

public void removerCurso(long id) {
    try {
        // Inicia transação
        em.getTransaction().begin();
        // Busca o curso pelo ID
        Curso curso = em.find(Curso.class, id);
        
        // Remove o curso se encontrar
        if (curso != null) {
            em.remove(curso);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(this, "Curso removido com sucesso!");
            carregarDados(); // Atualiza a tabela
        } else {
            // Cancela a transação se não encrontrar o curso
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(this, "Curso não encontrado!");
        }
    } catch (Exception e) {
        // Em caso de erro, cancela a transação se estiver ativa
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        JOptionPane.showMessageDialog(this, "Erro ao remover curso: " + e.getMessage());
        e.printStackTrace();
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
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CODIGO", "NOME", "DOCENTE", "EDITAR", "REMOVER"
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
        // TODO add your handling code here:
            JOptionPane.showMessageDialog(this, 
        "Funcionalidade de cadastro em construção!",
        "Em Desenvolvimento",
        JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        // TODO add your handling code here:
        String termo = txtPesquisar.getText().trim();
        
        if (termo.isEmpty()) {
            carregarDados();
        } else {
            List<Curso> cursos = buscarPorNome(termo);
            if (cursos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum curso encontrado com o termo: " + termo);
            } else {
                atualizarTabela(cursos);
            }
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed
    // Método para atualizar a lista após cadastro/edição
    public void atualizarLista() {
        carregarDados();
    }

    @Override
    public void dispose() {
        
        super.dispose();
         // Fecha o EntityManager se estiver aberto
        if (em != null) { // Libera os recursos do EntityManager
            em.close(); 
        }
        // Fecha o EntityManagerFactory se estiver aberto
        if (emf != null) { // Libera os recursos do EntityManagerFactory
            emf.close();
        }
    }
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
