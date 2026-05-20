import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class TelaVendedor extends JFrame {

    private String[] colunas = {"Código", "Nome", "Montadora", "Custo", "Preço/Venda", "Quantidade", "Estoque"};
    private DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // FIX: impede edição direta na tabela
        }
    };

    private JLabel lblCodigo   = new JLabel("Código: ");
    private JTextField txtCodigo  = new JTextField();

    private JLabel lblQtnd  = new JLabel("Quantidade: ");
    private JTextField txtQntd = new JTextField();

    private JButton btnCadastro = new JButton("Adicionar ao orçamento");

    private JTable tabela = new JTable(modelo);

    private JLabel lblBarra    = new JLabel("Pesquisa: ");
    private JTextField txtPesquisa = new JTextField();

    private JButton btnEditar  = new JButton("Editar");
    private JButton btnRemover = new JButton("Remover");

    // FIX: sorter para o filtro de pesquisa funcionar
    private TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);

    public TelaVendedor() {

        setTitle("TelaVendedor - AutoPeças Tião");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Título
        JLabel lblTitulo = new JLabel("Tela Vendedor");
        lblTitulo.setFont(new Font("Monospaced", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(0, 120, 215));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 0));
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

        txtPesquisa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void filtrar() {
                String texto = txtPesquisa.getText().trim();
                if (texto.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, 0));
                }
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e)  { filtrar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e)  { filtrar(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
        });

        pnlCard.add(lblBarra);
        pnlCard.add(Box.createVerticalStrut(5));
        pnlCard.add(txtPesquisa);
        pnlCard.add(Box.createVerticalStrut(20));

        // Código
        lblCodigo.setFont(new Font("Monospaced", Font.BOLD, 13));
        lblCodigo.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblCodigo.setForeground(Color.BLACK);

        txtCodigo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtCodigo.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtCodigo.setFont(new Font("Monospaced", Font.BOLD, 13));

        pnlCard.add(lblCodigo);
        pnlCard.add(Box.createVerticalStrut(5));
        pnlCard.add(txtCodigo);
        pnlCard.add(Box.createVerticalStrut(20));

        // Quantidade
        lblQtnd.setFont(new Font("Monospaced", Font.BOLD, 13));
        lblQtnd.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblQtnd.setForeground(Color.BLACK);

        txtQntd.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtQntd.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtQntd.setFont(new Font("Monospaced", Font.BOLD, 13));

        pnlCard.add(lblQtnd);
        pnlCard.add(Box.createVerticalStrut(5));
        pnlCard.add(txtQntd);
        pnlCard.add(Box.createVerticalStrut(20));

        // Botão cadastrar
        btnCadastro.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btnCadastro.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnCadastro.setBackground(new Color(0, 180, 80));
        btnCadastro.setForeground(Color.WHITE);
        btnCadastro.setFont(new Font("Monospaced", Font.BOLD, 14));
        btnCadastro.setFocusPainted(false);
        btnCadastro.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pnlCard.add(btnCadastro);
        pnlCard.add(Box.createVerticalStrut(10));

        // Painel botões Editar/Remover
        JPanel pnlBotoes = new JPanel(new GridLayout(1, 2, 10, 10));
        pnlBotoes.setBackground(Color.WHITE);
        pnlBotoes.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlBotoes.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); 

        btnRemover.setBackground(Color.RED);
        btnRemover.setForeground(Color.WHITE);
        btnRemover.setBorderPainted(false);
        btnRemover.setFocusPainted(false);
        btnRemover.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnEditar.setBackground(Color.GRAY);
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setBorderPainted(false);
        btnEditar.setFocusPainted(false);
        btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pnlBotoes.add(btnEditar);
        pnlBotoes.add(btnRemover);
        pnlCard.add(pnlBotoes);

        add(pnlCard, BorderLayout.WEST);

        // Tabela
        tabela.setFont(new Font("Monospaced", Font.PLAIN, 13));
        tabela.setRowHeight(30);
        tabela.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 13));
        tabela.setRowSorter(sorter); 

        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, BorderLayout.CENTER);


        // Adicionar
        btnCadastro.addActionListener(e -> {
            if (txtCodigo.getText().isEmpty() || txtQntd.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
                return;
            }
            try {
                int quantidade = Integer.parseInt(txtQntd.getText());
                modelo.addRow(new Object[]{
                        txtCodigo.getText(),
                        "", "", "", "",
                        quantidade,
                        ""
                });
                txtCodigo.setText("");
                txtQntd.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantidade inválida!");
            }
        });

        // Remover
        btnRemover.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha != -1) {
                int modeloLinha = tabela.convertRowIndexToModel(linha);
                modelo.removeRow(modeloLinha);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma linha!");
            }
        });

        // Editar
        btnEditar.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha == -1) {
                JOptionPane.showMessageDialog(this, "Selecione uma linha!");
                return;
            }
            // FIX: valida campos antes de editar
            if (txtCodigo.getText().isEmpty() || txtQntd.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha os campos para editar!");
                return;
            }
            try {
                int modeloLinha = tabela.convertRowIndexToModel(linha); // FIX: considera filtro ativo
                int quantidade = Integer.parseInt(txtQntd.getText());
                modelo.setValueAt(txtCodigo.getText(), modeloLinha, 0);
                modelo.setValueAt(quantidade, modeloLinha, 5);
                txtCodigo.setText("");
                txtQntd.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantidade inválida!");
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            TelaVendedor tela = new TelaVendedor();
            tela.setVisible(true);
        });
    }
}