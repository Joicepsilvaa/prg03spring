package br.com.ifba.curso.view.components;

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
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import org.springframework.context.annotation.Scope;

/**
 * Classe que cria botões de ação para tabelas (editar/remover)
 */
@org.springframework.stereotype.Component
@Scope("prototype")
public class BotaoRendererEditor extends AbstractCellEditor
        implements TableCellRenderer, TableCellEditor {

    private final JButton button;
    private String tipo;
    private JTable tabela;
    private int linha;
    private CursoListar cursoListar; // Referência à tela principal

    // Construtor sem parâmetros para injeção do Spring
    public BotaoRendererEditor() {
        this.button = new JButton();
    }

    // Setters para injeção de dependência e configuração
    public void setTipo(String tipo) {
        this.tipo = tipo;
        // Assegura que o botão seja configurado APENAS UMA VEZ após o tipo ser definido
        configurarBotao();
    }

    public void setTabela(JTable tabela) {
        this.tabela = tabela;
    }

    // Setter para injetar a instância de CursoListar
    public void setCursoListar(CursoListar cursoListar) {
        this.cursoListar = cursoListar;
    }

    private void configurarBotao() {
        button.setText(""); // Garante que não haja texto no botão
        
        // Limpa listeners antigos para evitar múltiplas execuções de eventos
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }

        if ("Editar".equals(tipo)) {
            button.setIcon(loadIcon("/br/com/ifba/curso/images/editar.png"));
            button.setToolTipText("Editar Curso");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (tabela != null && cursoListar != null) {
                        int linhaSelecionada = tabela.convertRowIndexToModel(linha);
                        Object idObj = tabela.getModel().getValueAt(linhaSelecionada, 0);

                        if (idObj instanceof Number) {
                            long id = ((Number) idObj).longValue();
                            cursoListar.editarCurso(id); // Chama o método editarCurso da tela principal
                        } else {
                            JOptionPane.showMessageDialog(tabela, "ID do curso inválido ou não encontrado!");
                        }
                    }
                    fireEditingStopped(); // Avisa a tabela que a edição parou
                }
            });
        } else if ("Remover".equals(tipo)) {
            button.setIcon(loadIcon("/br/com/ifba/curso/images/remover.png"));
            button.setToolTipText("Remover Curso");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (tabela != null && cursoListar != null) {
                        int linhaSelecionada = tabela.convertRowIndexToModel(linha);
                        Object idObj = tabela.getModel().getValueAt(linhaSelecionada, 0);

                        if (idObj instanceof Number) {
                            long id = ((Number) idObj).longValue();
                            cursoListar.removerCurso(id); // Chama o método removerCurso da tela principal
                        } else {
                            JOptionPane.showMessageDialog(tabela, "ID do curso inválido ou não encontrado!");
                        }
                    }
                    fireEditingStopped(); // Avisa a tabela que a edição parou
                }
            });
        }
    }

    // Carrega um ícone do projeto
    private ImageIcon loadIcon(String path) {
        URL imageURL = getClass().getResource(path);
        if (imageURL != null) {
            return new ImageIcon(imageURL);
        } else {
            System.err.println("Ícone não encontrado: " + path);
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
        this.setTabela(table);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return tipo;
    }
}