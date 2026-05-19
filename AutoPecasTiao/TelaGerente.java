import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaGerente extends JFrame {

    public TelaGerente() {
        setTitle("Gerente - Auto Peças Tião");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout(20, 20));
        getContentPane().setBackground(new Color(240, 240, 240));

        // titulo
        JLabel lblTitulo = new JLabel("Painel do Gerente");
        lblTitulo.setFont(new Font("Monospaced", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(0, 120, 215));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // abas
        JTabbedPane abas = new JTabbedPane();
        abas.setFont(new Font("Monospaced", Font.BOLD, 13));
        abas.setBackground(new Color(240, 240, 240));

        String[] colEstoque = {"SKU", "Peça", "Montadora", "Custo", "Preço Venda", "Lucro", "Quantidade"};
        DefaultTableModel mdlEstoque = new DefaultTableModel(colEstoque, 0);

        JTable tblEstoque = new JTable(mdlEstoque);
        tblEstoque.setFont(new Font("Monospaced", Font.PLAIN, 13));
        tblEstoque.setRowHeight(30);
        tblEstoque.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 13));
        tblEstoque.setEnabled(false);
        abas.addTab("Estoque e Custos", new JScrollPane(tblEstoque));

        String[] colComissao = {"Vendedor", "Vendas Realizadas", "Faturamento Total", "Comissão (1%)"};
        DefaultTableModel mdlComissao = new DefaultTableModel(colComissao, 0);

        JTable tblComissao = new JTable(mdlComissao);
        tblComissao.setFont(new Font("Monospaced", Font.PLAIN, 13));
        tblComissao.setRowHeight(30);
        tblComissao.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 13));
        tblComissao.setEnabled(false);
        abas.addTab("Comissões", new JScrollPane(tblComissao));

        JPanel pnlPedido = new JPanel();
        pnlPedido.setLayout(new BorderLayout());
        pnlPedido.setBackground(new Color(240, 240, 240));

        JPanel pnlCard = new JPanel();
        pnlCard.setLayout(new BoxLayout(pnlCard, BoxLayout.Y_AXIS));
        pnlCard.setBackground(Color.WHITE);
        pnlCard.setPreferredSize(new Dimension(350, 0));
        pnlCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblPedidoTitulo = new JLabel("Novo Pedido de Compra");
        lblPedidoTitulo.setFont(new Font("Monospaced", Font.BOLD, 16));
        lblPedidoTitulo.setForeground(new Color(0, 120, 215));
        lblPedidoTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblFornecedor = new JLabel("Fornecedor:");
        lblFornecedor.setFont(new Font("Monospaced", Font.BOLD, 13));
        lblFornecedor.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField txtFornecedor = new JTextField();
        txtFornecedor.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtFornecedor.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtFornecedor.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JLabel lblPeca = new JLabel("Peça:");
        lblPeca.setFont(new Font("Monospaced", Font.BOLD, 13));
        lblPeca.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField txtPeca = new JTextField();
        txtPeca.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtPeca.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtPeca.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JLabel lblQtd = new JLabel("Quantidade:");
        lblQtd.setFont(new Font("Monospaced", Font.BOLD, 13));
        lblQtd.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField txtQtd = new JTextField();
        txtQtd.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtQtd.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtQtd.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JButton btnPedido = new JButton("Enviar Pedido");
        btnPedido.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btnPedido.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnPedido.setBackground(new Color(0, 180, 80));
        btnPedido.setForeground(Color.WHITE);
        btnPedido.setFont(new Font("Monospaced", Font.BOLD, 14));
        btnPedido.setFocusPainted(false);
        btnPedido.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPedido.addActionListener(e -> {
            if (txtFornecedor.getText().isEmpty() || txtPeca.getText().isEmpty() || txtQtd.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            } else {
                JOptionPane.showMessageDialog(null, "Pedido enviado com sucesso!");
                txtFornecedor.setText("");
                txtPeca.setText("");
                txtQtd.setText("");
            }
        });

        pnlCard.add(lblPedidoTitulo);
        pnlCard.add(Box.createVerticalStrut(20));
        pnlCard.add(lblFornecedor);
        pnlCard.add(Box.createVerticalStrut(5));
        pnlCard.add(txtFornecedor);
        pnlCard.add(Box.createVerticalStrut(15));
        pnlCard.add(lblPeca);
        pnlCard.add(Box.createVerticalStrut(5));
        pnlCard.add(txtPeca);
        pnlCard.add(Box.createVerticalStrut(15));
        pnlCard.add(lblQtd);
        pnlCard.add(Box.createVerticalStrut(5));
        pnlCard.add(txtQtd);
        pnlCard.add(Box.createVerticalStrut(20));
        pnlCard.add(btnPedido);

        pnlPedido.add(pnlCard, BorderLayout.WEST);
        abas.addTab("Pedido de Compra", pnlPedido);

    
        JPanel pnlRelatorio = new JPanel();
        pnlRelatorio.setLayout(new BorderLayout());
        pnlRelatorio.setBackground(new Color(240, 240, 240));

        JPanel pnlCardRel = new JPanel();
        pnlCardRel.setLayout(new BoxLayout(pnlCardRel, BoxLayout.Y_AXIS));
        pnlCardRel.setBackground(Color.WHITE);
        pnlCardRel.setPreferredSize(new Dimension(350, 0));
        pnlCardRel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblRelTitulo = new JLabel("Relatório Financeiro");
        lblRelTitulo.setFont(new Font("Monospaced", Font.BOLD, 16));
        lblRelTitulo.setForeground(new Color(0, 120, 215));
        lblRelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblFaturamento = new JLabel("Faturamento Total:   R$ 0,00");
        lblFaturamento.setFont(new Font("Monospaced", Font.BOLD, 14));
        lblFaturamento.setForeground(new Color(0, 150, 0));
        lblFaturamento.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblLucro = new JLabel("Lucro Total:         R$ 0,00");
        lblLucro.setFont(new Font("Monospaced", Font.BOLD, 14));
        lblLucro.setForeground(new Color(0, 150, 0));
        lblLucro.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTotalComissao = new JLabel("Total em Comissões:  R$ 0,00");
        lblTotalComissao.setFont(new Font("Monospaced", Font.BOLD, 14));
        lblTotalComissao.setForeground(new Color(200, 100, 0));
        lblTotalComissao.setAlignmentX(Component.LEFT_ALIGNMENT);

        pnlCardRel.add(lblRelTitulo);
        pnlCardRel.add(Box.createVerticalStrut(20));
        pnlCardRel.add(lblFaturamento);
        pnlCardRel.add(Box.createVerticalStrut(10));
        pnlCardRel.add(lblLucro);
        pnlCardRel.add(Box.createVerticalStrut(10));
        pnlCardRel.add(lblTotalComissao);

        pnlRelatorio.add(pnlCardRel, BorderLayout.WEST);
        abas.addTab("Relatório Financeiro", pnlRelatorio);

        add(abas, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            TelaGerente tela = new TelaGerente();
            tela.setVisible(true);
        });
    }
}