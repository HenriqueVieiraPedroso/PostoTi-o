import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaCaixa extends JFrame {

    private String nomeUsuario;

    // Tabela venda atual
    private String[]          colVenda  = {"SKU", "Peça", "Montadora", "Preço Unit.", "Qtd", "Subtotal"};
    private DefaultTableModel mdlVenda  = new DefaultTableModel(colVenda, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    private JTable tblVenda = new JTable(mdlVenda);

    // Histórico do dia
    private String[]          colHistorico = {"#", "Vendedor", "Subtotal", "Desconto", "Total Final", "Pagamento", "Comissão"};
    private DefaultTableModel mdlHistorico = new DefaultTableModel(colHistorico, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    private JTable tblHistorico = new JTable(mdlHistorico);

    // Campos de entrada
    private JTextField        txtSKU       = new JTextField();
    private JTextField        txtPeca      = new JTextField();
    private JTextField        txtMontadora = new JTextField();
    private JTextField        txtPrecoUni  = new JTextField();
    private JTextField        txtQtd       = new JTextField();
    private JTextField        txtVendedor  = new JTextField();

    // Pagamento
    private JComboBox<String> cmbPagamento = new JComboBox<>(new String[]{
            "Cartão de Crédito", "Cartão de Débito", "PIX", "Dinheiro"});

    // Labels de resumo
    private JLabel lblSubtotal       = new JLabel("Subtotal:            R$ 0,00");
    private JLabel lblDesconto       = new JLabel("Desconto:            R$ 0,00");
    private JLabel lblTotal          = new JLabel("TOTAL:               R$ 0,00");
    private JLabel lblComissao       = new JLabel("Comissão (1%):       R$ 0,00");
    private JLabel lblFaturamentoDia = new JLabel("Faturamento do dia:  R$ 0,00");

    // Botões
    private JButton btnAddItem   = new JButton("Adicionar Item");
    private JButton btnRemItem   = new JButton("Remover Item");
    private JButton btnFinalizar = new JButton("Finalizar Venda");
    private JButton btnCancelar  = new JButton("Cancelar Venda");
    private JButton btnSair      = new JButton("Sair");

    private int    numVenda       = 1;
    private double faturamentoDia = 0;

    public TelaCaixa(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;

        setTitle("Caixa - Auto Peças Tião");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 240));

        // Topo
        JPanel pnlTopo = new JPanel(new BorderLayout());
        pnlTopo.setBackground(new Color(240, 240, 240));
        pnlTopo.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 20));

        JLabel lblTitulo = new JLabel("Finalização de Venda");
        lblTitulo.setFont(new Font("Monospaced", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(0, 120, 215));

        JPanel pnlTopoDir = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        pnlTopoDir.setBackground(new Color(240, 240, 240));

        JLabel lblUsuario = new JLabel("Olá, " + nomeUsuario + "  |  Perfil: Caixa");
        lblUsuario.setFont(new Font("Monospaced", Font.PLAIN, 12));
        lblUsuario.setForeground(Color.GRAY);

        btnSair.setBackground(new Color(200, 50, 50));
        btnSair.setForeground(Color.WHITE);
        btnSair.setFont(new Font("Monospaced", Font.BOLD, 12));
        btnSair.setBorderPainted(false);
        btnSair.setFocusPainted(false);
        btnSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSair.addActionListener(e -> { dispose(); new TelaLogin().setVisible(true); });

        pnlTopoDir.add(lblUsuario);
        pnlTopoDir.add(btnSair);
        pnlTopo.add(lblTitulo, BorderLayout.WEST);
        pnlTopo.add(pnlTopoDir, BorderLayout.EAST);
        add(pnlTopo, BorderLayout.NORTH);

        // Painel esquerdo
        JPanel pnlCard = new JPanel();
        pnlCard.setLayout(new BoxLayout(pnlCard, BoxLayout.Y_AXIS));
        pnlCard.setBackground(Color.WHITE);
        pnlCard.setPreferredSize(new Dimension(360, 0));
        pnlCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Vendedor
        pnlCard.add(rotulo("Vendedor Responsável:"));
        pnlCard.add(Box.createVerticalStrut(5));
        txtVendedor.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtVendedor.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtVendedor.setFont(new Font("Monospaced", Font.PLAIN, 14));
        pnlCard.add(txtVendedor);
        pnlCard.add(Box.createVerticalStrut(20));

        pnlCard.add(rotuloCinza("── Adicionar Item ──"));
        pnlCard.add(Box.createVerticalStrut(10));

        // SKU
        pnlCard.add(rotulo("SKU:"));
        pnlCard.add(Box.createVerticalStrut(4));
        txtSKU.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        txtSKU.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtSKU.setFont(new Font("Monospaced", Font.PLAIN, 14));
        pnlCard.add(txtSKU);
        pnlCard.add(Box.createVerticalStrut(10));

        // Peça
        pnlCard.add(rotulo("Peça:"));
        pnlCard.add(Box.createVerticalStrut(4));
        txtPeca.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        txtPeca.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtPeca.setFont(new Font("Monospaced", Font.PLAIN, 14));
        pnlCard.add(txtPeca);
        pnlCard.add(Box.createVerticalStrut(10));

        // Montadora
        pnlCard.add(rotulo("Montadora:"));
        pnlCard.add(Box.createVerticalStrut(4));
        txtMontadora.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        txtMontadora.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtMontadora.setFont(new Font("Monospaced", Font.PLAIN, 14));
        pnlCard.add(txtMontadora);
        pnlCard.add(Box.createVerticalStrut(10));

        // Preço
        pnlCard.add(rotulo("Preço Unitário (R$):"));
        pnlCard.add(Box.createVerticalStrut(4));
        txtPrecoUni.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        txtPrecoUni.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtPrecoUni.setFont(new Font("Monospaced", Font.PLAIN, 14));
        pnlCard.add(txtPrecoUni);
        pnlCard.add(Box.createVerticalStrut(10));

        // Quantidade
        pnlCard.add(rotulo("Quantidade:"));
        pnlCard.add(Box.createVerticalStrut(4));
        txtQtd.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        txtQtd.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtQtd.setFont(new Font("Monospaced", Font.PLAIN, 14));
        pnlCard.add(txtQtd);
        pnlCard.add(Box.createVerticalStrut(12));

        // Botões add/rem
        JPanel pnlBtnItem = new JPanel(new GridLayout(1, 2, 8, 0));
        pnlBtnItem.setBackground(Color.WHITE);
        pnlBtnItem.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlBtnItem.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        btnAddItem.setBackground(new Color(0, 180, 80));
        btnAddItem.setForeground(Color.WHITE);
        btnAddItem.setBorderPainted(false);
        btnAddItem.setFocusPainted(false);
        btnAddItem.setFont(new Font("Monospaced", Font.BOLD, 12));
        btnAddItem.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnRemItem.setBackground(new Color(200, 50, 50));
        btnRemItem.setForeground(Color.WHITE);
        btnRemItem.setBorderPainted(false);
        btnRemItem.setFocusPainted(false);
        btnRemItem.setFont(new Font("Monospaced", Font.BOLD, 12));
        btnRemItem.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pnlBtnItem.add(btnAddItem);
        pnlBtnItem.add(btnRemItem);
        pnlCard.add(pnlBtnItem);
        pnlCard.add(Box.createVerticalStrut(20));

        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlCard.add(sep);
        pnlCard.add(Box.createVerticalStrut(15));

        // Pagamento
        pnlCard.add(rotulo("Forma de Pagamento:"));
        pnlCard.add(Box.createVerticalStrut(5));
        cmbPagamento.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        cmbPagamento.setAlignmentX(Component.LEFT_ALIGNMENT);
        cmbPagamento.setFont(new Font("Monospaced", Font.PLAIN, 14));
        cmbPagamento.addActionListener(e -> recalcularTotais());
        pnlCard.add(cmbPagamento);
        pnlCard.add(Box.createVerticalStrut(20));

        // Resumo
        lblSubtotal.setFont(new Font("Monospaced", Font.PLAIN, 13));
        lblSubtotal.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblDesconto.setFont(new Font("Monospaced", Font.PLAIN, 13));
        lblDesconto.setForeground(new Color(180, 80, 0));
        lblDesconto.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblTotal.setFont(new Font("Monospaced", Font.BOLD, 15));
        lblTotal.setForeground(new Color(0, 130, 0));
        lblTotal.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblComissao.setFont(new Font("Monospaced", Font.PLAIN, 12));
        lblComissao.setForeground(Color.GRAY);
        lblComissao.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblFaturamentoDia.setFont(new Font("Monospaced", Font.BOLD, 12));
        lblFaturamentoDia.setForeground(new Color(0, 100, 0));
        lblFaturamentoDia.setAlignmentX(Component.LEFT_ALIGNMENT);

        pnlCard.add(lblSubtotal);
        pnlCard.add(Box.createVerticalStrut(4));
        pnlCard.add(lblDesconto);
        pnlCard.add(Box.createVerticalStrut(4));
        pnlCard.add(lblTotal);
        pnlCard.add(Box.createVerticalStrut(4));
        pnlCard.add(lblComissao);
        pnlCard.add(Box.createVerticalStrut(15));

        // Botão finalizar
        btnFinalizar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        btnFinalizar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnFinalizar.setBackground(new Color(0, 100, 200));
        btnFinalizar.setForeground(Color.WHITE);
        btnFinalizar.setFont(new Font("Monospaced", Font.BOLD, 14));
        btnFinalizar.setFocusPainted(false);
        btnFinalizar.setBorderPainted(false);
        btnFinalizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlCard.add(btnFinalizar);
        pnlCard.add(Box.createVerticalStrut(8));

        // Botão cancelar
        btnCancelar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnCancelar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnCancelar.setBackground(Color.GRAY);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Monospaced", Font.BOLD, 13));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlCard.add(btnCancelar);
        pnlCard.add(Box.createVerticalStrut(15));
        pnlCard.add(lblFaturamentoDia);

        add(pnlCard, BorderLayout.WEST);

        // Centro
        JPanel pnlCentral = new JPanel(new GridLayout(2, 1, 0, 10));
        pnlCentral.setBackground(new Color(240, 240, 240));
        pnlCentral.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
        pnlCentral.add(painelTabela("Itens da Venda Atual", tblVenda));
        pnlCentral.add(painelTabela("Histórico de Vendas do Dia", tblHistorico));
        tblHistorico.setEnabled(false);
        add(pnlCentral, BorderLayout.CENTER);

        // Rodapé
        JPanel pnlRodape = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        pnlRodape.setBackground(new Color(220, 220, 220));
        JLabel lblRodape = new JLabel("Auto Peças do Tião  |  Módulo: Caixa");
        lblRodape.setFont(new Font("Monospaced", Font.PLAIN, 11));
        lblRodape.setForeground(Color.GRAY);
        pnlRodape.add(lblRodape);
        add(pnlRodape, BorderLayout.SOUTH);

        // Ações
        btnAddItem.addActionListener(e -> adicionarItem());
        txtQtd.addActionListener(e -> adicionarItem());

        btnRemItem.addActionListener(e -> {
            int linha = tblVenda.getSelectedRow();
            if (linha == -1) { JOptionPane.showMessageDialog(this, "Selecione um item!"); return; }
            mdlVenda.removeRow(linha);
            recalcularTotais();
        });

        btnCancelar.addActionListener(e -> {
            if (mdlVenda.getRowCount() == 0) return;
            int c = JOptionPane.showConfirmDialog(this, "Cancelar a venda atual?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (c == JOptionPane.YES_OPTION) { mdlVenda.setRowCount(0); recalcularTotais(); }
        });

        btnFinalizar.addActionListener(e -> finalizarVenda());
    }

    public TelaCaixa() { this("caixa"); }

    private void adicionarItem() {
        if (txtSKU.getText().trim().isEmpty() || txtPeca.getText().trim().isEmpty() ||
                txtPrecoUni.getText().trim().isEmpty() || txtQtd.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha SKU, Peça, Preço e Quantidade!"); return;
        }
        try {
            double preco = Double.parseDouble(txtPrecoUni.getText().trim().replace(",", "."));
            int qtd      = Integer.parseInt(txtQtd.getText().trim());
            if (qtd <= 0 || preco <= 0) throw new NumberFormatException();
            mdlVenda.addRow(new Object[]{
                txtSKU.getText().trim().toUpperCase(),
                txtPeca.getText().trim(),
                txtMontadora.getText().trim(),
                String.format("R$ %.2f", preco),
                qtd,
                String.format("R$ %.2f", preco * qtd)
            });
            txtSKU.setText(""); txtPeca.setText(""); txtMontadora.setText("");
            txtPrecoUni.setText(""); txtQtd.setText("");
            recalcularTotais();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Preço ou quantidade inválidos!");
        }
    }

    private void recalcularTotais() {
        double soma = somarVenda();
        String pgto = cmbPagamento.getSelectedItem().toString();
        boolean temDesconto = pgto.equals("PIX") || pgto.equals("Dinheiro");
        double desconto = temDesconto ? soma * 0.10 : 0;
        double total    = soma - desconto;

        lblSubtotal.setText(String.format("Subtotal:            R$ %.2f", soma));
        lblDesconto.setText(String.format("Desconto (-10%%):     R$ %.2f%s",
                desconto, temDesconto ? "" : "  (só PIX/Din.)"));
        lblTotal.setText(String.format("TOTAL:               R$ %.2f", total));
        lblComissao.setText(String.format("Comissão (1%%):       R$ %.2f", total * 0.01));
    }

    private void finalizarVenda() {
        if (mdlVenda.getRowCount() == 0) { JOptionPane.showMessageDialog(this, "Nenhum item na venda!"); return; }
        if (txtVendedor.getText().trim().isEmpty()) { JOptionPane.showMessageDialog(this, "Informe o vendedor responsável!"); return; }

        double soma = somarVenda();
        String pgto = cmbPagamento.getSelectedItem().toString();
        boolean temDesconto = pgto.equals("PIX") || pgto.equals("Dinheiro");
        double desconto = temDesconto ? soma * 0.10 : 0;
        double total    = soma - desconto;
        double comissao = total * 0.01;

        mdlHistorico.addRow(new Object[]{
            "#" + numVenda,
            txtVendedor.getText().trim(),
            String.format("R$ %.2f", soma),
            String.format("R$ %.2f", desconto),
            String.format("R$ %.2f", total),
            pgto,
            String.format("R$ %.2f", comissao)
        });
        numVenda++;
        faturamentoDia += total;
        lblFaturamentoDia.setText(String.format("Faturamento do dia:  R$ %.2f", faturamentoDia));

        JOptionPane.showMessageDialog(this,
            String.format("Venda finalizada!\n\nTotal:     R$ %.2f\nDesconto:  R$ %.2f\nPagamento: %s\nComissão (%s): R$ %.2f",
                total, desconto, pgto, txtVendedor.getText().trim(), comissao),
            "Venda Finalizada", JOptionPane.INFORMATION_MESSAGE);

        mdlVenda.setRowCount(0);
        txtVendedor.setText("");
        recalcularTotais();
    }

    private double somarVenda() {
        double soma = 0;
        for (int i = 0; i < mdlVenda.getRowCount(); i++)
            soma += Double.parseDouble(mdlVenda.getValueAt(i, 5).toString().replace("R$ ", "").replace(",", "."));
        return soma;
    }

    private JPanel painelTabela(String titulo, JTable tabela) {
        tabela.setFont(new Font("Monospaced", Font.PLAIN, 13));
        tabela.setRowHeight(28);
        tabela.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 13));
        tabela.setSelectionBackground(new Color(180, 215, 255));
        JPanel pnl = new JPanel(new BorderLayout(5, 5));
        pnl.setBackground(Color.WHITE);
        pnl.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Monospaced", Font.BOLD, 13));
        lbl.setForeground(new Color(0, 120, 215));
        pnl.add(lbl, BorderLayout.NORTH);
        pnl.add(new JScrollPane(tabela), BorderLayout.CENTER);
        return pnl;
    }

    private JLabel rotulo(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Monospaced", Font.BOLD, 13));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private JLabel rotuloCinza(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Monospaced", Font.BOLD, 12));
        lbl.setForeground(new Color(100, 100, 100));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new TelaCaixa().setVisible(true));
    }
}