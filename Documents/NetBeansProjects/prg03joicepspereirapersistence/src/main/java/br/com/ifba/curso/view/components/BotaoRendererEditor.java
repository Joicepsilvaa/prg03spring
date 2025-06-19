package br.com.ifba.curso.view.components;

import br.com.ifba.curso.view.CursoListar;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.net.URL;

public class BotaoRendererEditor extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    private final JButton button;
    private final String tipo;  // para definit o tipo do botão
    private JTable tabela;
    private int linha;          // para a linha que está sendo editada

    public BotaoRendererEditor(String tipo, JTable tabela) {
        this.tipo = tipo;
        this.tabela = tabela;
        this.button = new JButton();
        
        // Centraliza o ícone no botão
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);

        // Carrega o ícone no botão
        if ("Editar".equals(tipo)) { 
            button.setIcon(loadIcon("/br/com/ifba/curso/images/editar.png"));
        } else if ("Remover".equals(tipo)) { 
            button.setIcon(loadIcon("/br/com/ifba/curso/images/remover.png"));
        }

        button.setToolTipText(tipo);  // Tooltip com o tipo de ação

        // ActionListener para tratar os cliques
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tabela.convertRowIndexToModel(linha);
                Object idObj = tabela.getModel().getValueAt(linhaSelecionada, 0);
                
                // Valida o ID
                if (idObj == null) {
                    JOptionPane.showMessageDialog(tabela, "ID do curso não encontrado!");
                    return;
                }

                // Converte o ID para long (trata tanto Integer quanto Long)
                long id = ((Number)idObj).longValue();
                
                if ("Editar".equals(tipo)) {
                    JOptionPane.showMessageDialog(tabela, 
                    "Funcionalidade de edição em construção!",
                    "Em Desenvolvimento",
                    JOptionPane.INFORMATION_MESSAGE);
                    
                } else if ("Remover".equals(tipo)) {
                    // Confirmação antes de remover
                    int confirm = JOptionPane.showConfirmDialog(
                        tabela,
                        "Deseja realmente remover o curso com ID " + id + "?",
                        "Confirmação", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                    );
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                        // Chama método de remoção na tela principal
                        CursoListar telaPrincipal = (CursoListar) SwingUtilities.getWindowAncestor(tabela);
                        if (telaPrincipal != null) {
                            telaPrincipal.removerCurso(id);
                        } else {
                            JOptionPane.showMessageDialog(tabela, 
                                "Erro: Não foi possível remover o curso.",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                fireEditingStopped();
            }
        });
    }

 
    private ImageIcon loadIcon(String path) {
        URL imageURL = getClass().getResource(path);
        if (imageURL != null) {
            return new ImageIcon(imageURL);
        } else {
            System.err.println("Ícone não encontrado: " + path);  // Log de erro
            return null;
        }
    }

    // Métodos obrigatórios das interfaces implementadas
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        return button;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        this.linha = row;  // Atualiza a linha atual
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return tipo;  // Retorna o tipo de botão
    }
}