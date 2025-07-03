package br.com.ifba.curso.view.components;

import br.com.ifba.CursoList;
import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.view.CursoEditar;
import br.com.ifba.curso.view.CursoListar;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * Classe que cria botões de ação para tabelas (editar/remover)
 */
public class BotaoRendererEditor extends AbstractCellEditor 
    implements TableCellRenderer, TableCellEditor {

    private final JButton button; // O botão que será exibido
    private final String tipo;    // Tipo do botão ("Editar" ou "Remover")
    private final JTable tabela;  // Tabela onde o botão está
    private int linha;           // Linha clicada na tabela

    public BotaoRendererEditor(String tipo, JTable tabela) {
        this.tipo = tipo;
        this.tabela = tabela;
        this.button = new JButton(tipo);
        button.setText(""); // Remove o texto do botão
        
        // Coloca o ícone correto no botão
        if ("Editar".equals(tipo)) { 
            button.setIcon(loadIcon("/br/com/ifba/curso/images/editar.png"));
        } else if ("Remover".equals(tipo)) { 
            button.setIcon(loadIcon("/br/com/ifba/curso/images/remover.png"));
        }

        // Tooltip mostra o que o botão faz
        button.setToolTipText(tipo);  
        
        // Configura o que acontece quando clica no botão
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tabela.convertRowIndexToModel(linha);
                // O ID agora está na coluna 0 (primeira coluna)
                Object idObj = tabela.getModel().getValueAt(linhaSelecionada, 0);

                if (idObj == null) {
                    JOptionPane.showMessageDialog(tabela, "ID do curso não encontrado!");
                    return;
                }

                long id = ((Number)idObj).longValue();

                if ("Editar".equals(tipo)) {
                    editarCurso(id);
                } else if ("Remover".equals(tipo)) {
                    removerCurso(id);
                }
                fireEditingStopped();
            }
        });
    }

    // Abre a tela de edição do curso
    private void editarCurso(long id) {
        try {
            CursoList cursoList = new CursoList();
            // Busca o curso pelo ID
            Curso curso = cursoList.findById(id);
            
            if (curso != null) {
                // Abre a tela de edição
                CursoEditar editar = new CursoEditar(curso);
                // Quando fechar, atualiza a tabela
                editar.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        atualizarTabelaPrincipal();
                    }
                });
                editar.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(tabela, "Curso não encontrado!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(tabela, "Erro ao buscar o curso: " + ex.getMessage());
        }
    }

    // Remove um curso após confirmação
    private void removerCurso(long id) {
        // Pede confirmação antes de remover
        int confirm = JOptionPane.showConfirmDialog(
            tabela,
            "Deseja realmente remover o curso com ID " + id + "?",
            "Confirmação", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        // Se confirmou, remove
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Pega a tela principal pra atualizar depois
                CursoListar telaPrincipal = (CursoListar) SwingUtilities.getWindowAncestor(tabela);
                if (telaPrincipal != null) {
                    telaPrincipal.removerCurso(id);
                } else {
                    JOptionPane.showMessageDialog(tabela, 
                        "Erro: Não foi possível remover o curso.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(tabela, 
                    "Erro ao remover curso: " + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Atualiza a tabela principal
    private void atualizarTabelaPrincipal() {
        CursoListar telaPrincipal = (CursoListar) SwingUtilities.getWindowAncestor(tabela);
        if (telaPrincipal != null) {
            telaPrincipal.carregarDados();
        }
    }
    // Carrega um ícone do projeto
    private ImageIcon loadIcon(String path) {
        URL imageURL = getClass().getResource(path);
        if (imageURL != null) {
            return new ImageIcon(imageURL);
        } else {
            System.err.println("Ícone não encontrado: " + path);  // Log de erro
            return null;
        }
    }
    // Métodos obrigatórios para renderizar e editar células
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        return button;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        this.linha = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return tipo;
    }
}