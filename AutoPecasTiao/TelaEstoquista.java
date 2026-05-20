import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class TelaEstoquista extends JFrame {

    private JTextField txtSKU       = new JTextField();
    private JTextField txtPeca      = new JTextField();
    private JComboBox<String> cmbMarca = new JComboBox<>(new String[]{"VW", "Chevrolet", "Fiat", "Peugeot"});
    private JTextField txtValorCusto = new JTextField();
    private JTextField txtQuantidade = new JTextField();
    private JLabel lblPreco          = new JLabel("Preço de venda: R$ 0,00");
    private JButton btnCadastro      = new JButton("Cadastrar");
    private JButton btnEditar        = new JButton("Editar");
    private JButton btnRemover       = new JButton("Remover");

    private String[] colunas = {"SKU", "Peça", "Montadora", "Custo", "Preço Venda", "Quantidade"};
    private DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // FIX: bloqueia edição direta sem desabilitar seleção
        }
    };
    private JTable tabela = new JTable(modelo);

    private JLabel lblBarra      = new JLabel("Pesquisa: ");
    private JTextField txtPesquisa = new JTextField();

    // FIX: sorter para filtro de pesquisa
    private TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);

    public TelaEstoquista() {
        setTitle("Estoquista - Auto Peças Tião");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout(20, 20));
        getContentPane().setBackground(new Color(240, 240, 240));

        // Título
        JLabel lblTitulo = new JLabel("Entrada de Produto");
        lblTitulo.setFont(new Font("Monospaced", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(0, 120, 215));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Painel esquerdo
        JPanel pnlCard = new JPanel();
        pnlCard.setLayout(new BoxLayout(pnlCard, BoxLayout.Y_AXIS));
        pnlCard.setBackground(Color.WHITE);
        pnlCard.setPreferredSize(new Dimension(350, 0));
        pnlCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Pesquisa
        lblBarra.setFont(new Font("Monospaced", Font.BOLD, 13));
        lblBarra.setForeground(Color.BLACK);
        lblBarra.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtPesquisa.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtPesquisa.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtPesquisa.setFont(new Font("Monospaced", Font.PLAIN, 14));

        // FIX: pesquisa funcional filtrando por SKU (coluna 0)
        txtPesquisa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void filtrar() {
                String texto = txtPesquisa.getText().trim();
                sorter.setRowFilter(texto.isEmpty() ? null : RowFilter.regexFilter("(?i)" + texto, 0));
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e)  { filtrar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e)  { filtrar(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
        });

        pnlCard.add(lblBarra);
        pnlCard.add(Box.createVerticalStrut(5));
        pnlCard.add(txtPesquisa);
        pnlCard.add(Box.createVerticalStrut(20));

        // SKU
        pnlCard.add(rotulo("SKU:"));
        pnlCard.add(Box.createVerticalStrut(5));
        txtSKU.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtSKU.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtSKU.setFont(new Font("Monospaced", Font.PLAIN, 14));
        pnlCard.add(txtSKU);
        pnlCard.add(Box.createVerticalStrut(15));

        // Peça
        pnlCard.add(rotulo("Peça:"));
        pnlCard.add(Box.createVerticalStrut(5));
        txtPeca.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtPeca.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtPeca.setFont(new Font("Monospaced", Font.PLAIN, 14));
        pnlCard.add(txtPeca);
        pnlCard.add(Box.createVerticalStrut(15));

        // Valor Custo
        pnlCard.add(rotulo("Valor Custo:"));
        pnlCard.add(Box.createVerticalStrut(5));
        txtValorCusto.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtValorCusto.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtValorCusto.setFont(new Font("Monospaced", Font.PLAIN, 14));
        pnlCard.add(txtValorCusto);
        pnlCard.add(Box.createVerticalStrut(15));

        // Quantidade
        pnlCard.add(rotulo("Quantidade:"));
        pnlCard.add(Box.createVerticalStrut(5));
        txtQuantidade.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtQuantidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtQuantidade.setFont(new Font("Monospaced", Font.PLAIN, 14));
        pnlCard.add(txtQuantidade);
        pnlCard.add(Box.createVerticalStrut(15));

        // Marca
        pnlCard.add(rotulo("Marca:"));
        pnlCard.add(Box.createVerticalStrut(5));
        cmbMarca.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        cmbMarca.setAlignmentX(Component.LEFT_ALIGNMENT);
        cmbMarca.setFont(new Font("Monospaced", Font.PLAIN, 14));
        pnlCard.add(cmbMarca);
        pnlCard.add(Box.createVerticalStrut(20));

        // Preço calculado
        lblPreco.setFont(new Font("Monospaced", Font.BOLD, 13));
        lblPreco.setForeground(new Color(0, 150, 0));
        lblPreco.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlCard.add(lblPreco);
        pnlCard.add(Box.createVerticalStrut(10));

        // Botão Cadastrar
        btnCadastro.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btnCadastro.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnCadastro.setBackground(new Color(0, 180, 80));
        btnCadastro.setForeground(Color.WHITE);
        btnCadastro.setFont(new Font("Monospaced", Font.BOLD, 14));
        btnCadastro.setFocusPainted(false);
        btnCadastro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlCard.add(btnCadastro);
        pnlCard.add(Box.createVerticalStrut(10));

        // FIX: botões Editar/Remover adicionados ao painel com tamanho definido
        JPanel pnlBotoes = new JPanel(new GridLayout(1, 2, 10, 10));
        pnlBotoes.setBackground(Color.WHITE);
        pnlBotoes.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlBotoes.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        btnEditar.setBackground(Color.GRAY);
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setBorderPainted(false);
        btnEditar.setFocusPainted(false);
        btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnRemover.setBackground(Color.RED);
        btnRemover.setForeground(Color.WHITE);
        btnRemover.setBorderPainted(false);
        btnRemover.setFocusPainted(false);
        btnRemover.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pnlBotoes.add(btnEditar);
        pnlBotoes.add(btnRemover);
        pnlCard.add(pnlBotoes);

        add(pnlCard, BorderLayout.WEST);

        // --- Listeners ---

        // Cálculo automático do preço de venda
        txtValorCusto.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void calcular() {
                try {
                    double custo = Double.parseDouble(txtValorCusto.getText().trim());
                    lblPreco.setText(String.format("Preço de venda: R$ %.2f", custo * 1.4));
                } catch (NumberFormatException ex) {
                    lblPreco.setText("Preço de venda: R$ 0,00");
                }
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e)  { calcular(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e)  { calcular(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { calcular(); }
        });

        // FIX: apenas UM listener no btnCadastro, com campos corretos
        btnCadastro.addActionListener(e -> {
            if (txtSKU.getText().isEmpty() || txtPeca.getText().isEmpty() ||
                    txtValorCusto.getText().isEmpty() || txtQuantidade.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!"); // FIX: this
                return;
            }
            try {
                double custo = Double.parseDouble(txtValorCusto.getText().trim());
                double venda = custo * 1.4;
                modelo.addRow(new Object[]{
                        txtSKU.getText(),
                        txtPeca.getText(),
                        cmbMarca.getSelectedItem(),
                        String.format("R$ %.2f", custo),
                        String.format("R$ %.2f", venda),
                        txtQuantidade.getText()
                });
                txtSKU.setText("");
                txtPeca.setText("");
                txtValorCusto.setText("");
                txtQuantidade.setText("");
                lblPreco.setText("Preço de venda: R$ 0,00");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor de custo inválido!"); // FIX: this
            }
        });

        // Remover
        btnRemover.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha != -1) {
                modelo.removeRow(tabela.convertRowIndexToModel(linha));
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma linha!");
            }
        });

        // FIX: Editar com campos corretos (txtSKU e txtQuantidade)
    // Editar — atualiza TODOS os campos da linha selecionada
btnEditar.addActionListener(e -> {
    int linha = tabela.getSelectedRow();
    if (linha == -1) {
        JOptionPane.showMessageDialog(this, "Selecione uma linha!");
        return;
    }
    if (txtSKU.getText().isEmpty() || txtPeca.getText().isEmpty() ||
            txtValorCusto.getText().isEmpty() || txtQuantidade.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Preencha os campos para editar!");
        return;
    }
    try {
        int modeloLinha = tabela.convertRowIndexToModel(linha);
        double custo = Double.parseDouble(txtValorCusto.getText().trim());
        double venda = custo * 1.4;

        modelo.setValueAt(txtSKU.getText(),                       modeloLinha, 0); // SKU
        modelo.setValueAt(txtPeca.getText(),                      modeloLinha, 1); // Peça
        modelo.setValueAt(cmbMarca.getSelectedItem(),             modeloLinha, 2); // Montadora
        modelo.setValueAt(String.format("R$ %.2f", custo),        modeloLinha, 3); // Custo
        modelo.setValueAt(String.format("R$ %.2f", venda),        modeloLinha, 4); // Preço Venda
        modelo.setValueAt(txtQuantidade.getText(),                 modeloLinha, 5); // Quantidade

        // Limpa os campos após editar
        txtSKU.setText("");
        txtPeca.setText("");
        txtValorCusto.setText("");
        txtQuantidade.setText("");
        lblPreco.setText("Preço de venda: R$ 0,00");

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Valor de custo inválido!");
    }
});

// Ao clicar na linha, preenche os campos automaticamente
tabela.getSelectionModel().addListSelectionListener(ev -> {
    if (!ev.getValueIsAdjusting()) {
        int linha = tabela.getSelectedRow();
        if (linha == -1) return;
        int ml = tabela.convertRowIndexToModel(linha);

        txtSKU.setText(modelo.getValueAt(ml, 0).toString());
        txtPeca.setText(modelo.getValueAt(ml, 1).toString());

        // Extrai o número do custo formatado "R$ 99,99" → "99.99"
        String custoStr = modelo.getValueAt(ml, 3).toString()
                .replace("R$ ", "").replace(",", ".");
        txtValorCusto.setText(custoStr);
        txtQuantidade.setText(modelo.getValueAt(ml, 5).toString());

        // Restaura a montadora no combo
        String montadora = modelo.getValueAt(ml, 2).toString();
        for (int i = 0; i < cmbMarca.getItemCount(); i++) {
            if (cmbMarca.getItemAt(i).equals(montadora)) {
                cmbMarca.setSelectedIndex(i);
                break;
            }
        }
    }
});

        // Tabela
        tabela.setFont(new Font("Monospaced", Font.PLAIN, 13));
        tabela.setRowHeight(30);
        tabela.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 13));
        tabela.setRowSorter(sorter); // FIX: vincula sorter (e não desabilita a tabela)
        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, BorderLayout.CENTER);
    }

    // Auxiliar para criar rótulos padronizados
    private JLabel rotulo(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Monospaced", Font.BOLD, 13));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            TelaEstoquista tela = new TelaEstoquista();
            tela.setVisible(true);
        });
    }
}