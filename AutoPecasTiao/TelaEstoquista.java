import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaEstoquista extends JFrame {
    private JTextField txtSKU = new JTextField();
    private JTextField txtPeca = new JTextField();
    private JComboBox<String> cmbMarca = new JComboBox<>(new String[]{"VW", "Chevrolet", "Fiat", "Peugeot"});
    private JTextField txtValorCusto = new JTextField();
    private JTextField txtQuantidade = new JTextField();
    private JLabel lblPreco = new JLabel("Preço de venda: R$ 0,00");
    private JButton btnCadastro = new JButton("Cadastrar");
    private String[] colunas = {"SKU", "Peça", "Montadora", "Custo", "Preço Venda", "Quantidade"};
    private DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
    private JTable tabela = new JTable(modelo);

    public TelaEstoquista() {   
        setTitle("Estoquista - Auto Peças Tião");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout(20, 20));
        getContentPane().setBackground(new Color(240, 240, 240));

        JLabel lblTitulo = new JLabel("Entrada de Produto");
        lblTitulo.setFont(new Font("Monospaced", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(0, 120, 215));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 0));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel pnlCard = new JPanel();
        pnlCard.setLayout(new BoxLayout(pnlCard, BoxLayout.Y_AXIS));
        pnlCard.setBackground(Color.WHITE);
        pnlCard.setPreferredSize(new Dimension(350, 0));
        pnlCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // SKU
        JLabel lblSKU = new JLabel("SKU:");
        lblSKU.setFont(new Font("Monospaced", Font.BOLD, 13));
        lblSKU.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtSKU.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtSKU.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtSKU.setFont(new Font("Monospaced", Font.PLAIN, 14));

        pnlCard.add(lblSKU);
        pnlCard.add(Box.createVerticalStrut(5));
        pnlCard.add(txtSKU);
        pnlCard.add(Box.createVerticalStrut(15));

        // PECA
        JLabel lblPeca = new JLabel("Peça:");
        lblPeca.setFont(new Font("Monospaced", Font.BOLD, 13));
        lblPeca.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtPeca.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtPeca.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtPeca.setFont(new Font("Monospaced", Font.PLAIN, 14));

        pnlCard.add(lblPeca);
        pnlCard.add(Box.createVerticalStrut(5));
        pnlCard.add(txtPeca);
        pnlCard.add(Box.createVerticalStrut(15));

        // VALOR CUSTO
        JLabel lblValorCusto = new JLabel("Valor Custo:");
        lblValorCusto.setFont(new Font("Monospaced", Font.BOLD, 13));
        lblValorCusto.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtValorCusto.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtValorCusto.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtValorCusto.setFont(new Font("Monospaced", Font.PLAIN, 14));

        pnlCard.add(lblValorCusto);
        pnlCard.add(Box.createVerticalStrut(5));
        pnlCard.add(txtValorCusto);
        pnlCard.add(Box.createVerticalStrut(15));

        // QUANTIDADE
        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setFont(new Font("Monospaced", Font.BOLD, 13));
        lblQuantidade.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtQuantidade.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtQuantidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtQuantidade.setFont(new Font("Monospaced", Font.PLAIN, 14));

        pnlCard.add(lblQuantidade);
        pnlCard.add(Box.createVerticalStrut(5));
        pnlCard.add(txtQuantidade);
        pnlCard.add(Box.createVerticalStrut(15));

        // MARCA
        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setFont(new Font("Monospaced", Font.BOLD, 13));
        lblMarca.setAlignmentX(Component.LEFT_ALIGNMENT);

        cmbMarca.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        cmbMarca.setAlignmentX(Component.LEFT_ALIGNMENT);
        cmbMarca.setFont(new Font("Monospaced", Font.PLAIN, 14));

        pnlCard.add(lblMarca);
        pnlCard.add(Box.createVerticalStrut(5));
        pnlCard.add(cmbMarca);
        pnlCard.add(Box.createVerticalStrut(20));

        // PRECO E BOTAO
        lblPreco.setFont(new Font("Monospaced", Font.BOLD, 13));
        lblPreco.setForeground(new Color(0, 150, 0));
        lblPreco.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnCadastro.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btnCadastro.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnCadastro.setBackground(new Color(0, 180, 80));
        btnCadastro.setForeground(Color.WHITE);
        btnCadastro.setFont(new Font("Monospaced", Font.BOLD, 14));
        btnCadastro.setFocusPainted(false);
        btnCadastro.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pnlCard.add(lblPreco);
        pnlCard.add(Box.createVerticalStrut(10));
        pnlCard.add(btnCadastro);

        add(pnlCard, BorderLayout.WEST);

        // calcula preco automaticamente
        txtValorCusto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                try {
                    double custo = Double.parseDouble(txtValorCusto.getText());
                    double venda = custo * 1.4;
                    lblPreco.setText(String.format("Preço de venda: R$ %.2f", venda));
                } catch (NumberFormatException ex) {
                    lblPreco.setText("Preço de venda: R$ 0,00");
                }
            }
        });

        // cadastra na tabela
        btnCadastro.addActionListener(e -> {
            if (txtSKU.getText().isEmpty() || txtPeca.getText().isEmpty() ||
                txtValorCusto.getText().isEmpty() || txtQuantidade.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                return;
            }
            try {
                double custo = Double.parseDouble(txtValorCusto.getText());
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
                JOptionPane.showMessageDialog(null, "Valor de custo inválido!");
            }
        });

        // tabela
        tabela.setFont(new Font("Monospaced", Font.PLAIN, 13));
        tabela.setRowHeight(30);
        tabela.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 13));
        tabela.setEnabled(false);
        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            TelaEstoquista tela = new TelaEstoquista();
            tela.setVisible(true);
        });
    }   
}