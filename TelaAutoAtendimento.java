import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class TelaAutoAtendimento extends JFrame {

    private String[] colunas = {"SKU", "Peça", "Montadora", "Preço Venda", "Disponibilidade"};
    private DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JTable    tabela     = new JTable(modelo);
    private JLabel    lblBarra   = new JLabel("Pesquisa:");
    private JTextField txtPesquisa = new JTextField();
    private JButton   btnSair    = new JButton("Sair");
    private TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);

    public TelaAutoAtendimento() {
        setTitle("Autoatendimento - Auto Peças Tião");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout(20, 20));
        getContentPane().setBackground(new Color(240, 240, 240));

        // Topo azul
        JPanel pnlTopo = new JPanel(new BorderLayout());
        pnlTopo.setBackground(new Color(0, 120, 215));
        pnlTopo.setBorder(BorderFactory.createEmptyBorder(18, 30, 18, 30));

        JLabel lblTitulo = new JLabel("Auto Peças Tião  —  Consulta de Preços");
        lblTitulo.setFont(new Font("Monospaced", Font.BOLD, 22));
        lblTitulo.setForeground(Color.WHITE);

        JLabel lblSubtitulo = new JLabel("Consulte preços e disponibilidade. Para comprar, fale com um vendedor.");
        lblSubtitulo.setFont(new Font("Monospaced", Font.PLAIN, 12));
        lblSubtitulo.setForeground(new Color(210, 230, 255));

        JPanel pnlTopoTexto = new JPanel();
        pnlTopoTexto.setLayout(new BoxLayout(pnlTopoTexto, BoxLayout.Y_AXIS));
        pnlTopoTexto.setBackground(new Color(0, 120, 215));
        pnlTopoTexto.add(lblTitulo);
        pnlTopoTexto.add(Box.createVerticalStrut(4));
        pnlTopoTexto.add(lblSubtitulo);

        pnlTopo.add(pnlTopoTexto, BorderLayout.WEST);
        add(pnlTopo, BorderLayout.NORTH);

        // Painel esquerdo
        JPanel pnlCard = new JPanel();
        pnlCard.setLayout(new BoxLayout(pnlCard, BoxLayout.Y_AXIS));
        pnlCard.setBackground(Color.WHITE);
        pnlCard.setPreferredSize(new Dimension(300, 0));
        pnlCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        lblBarra.setFont(new Font("Monospaced", Font.BOLD, 13));
        lblBarra.setForeground(Color.BLACK);
        lblBarra.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtPesquisa.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtPesquisa.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtPesquisa.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtPesquisa.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 120, 215), 1),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));

        txtPesquisa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void filtrar() {
                String texto = txtPesquisa.getText().trim();
                sorter.setRowFilter(texto.isEmpty() ? null : RowFilter.regexFilter("(?i)" + texto));
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e)  { filtrar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e)  { filtrar(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
        });

        pnlCard.add(lblBarra);
        pnlCard.add(Box.createVerticalStrut(5));
        pnlCard.add(txtPesquisa);
        pnlCard.add(Box.createVerticalStrut(25));

        // Aviso desconto
        JPanel pnlAviso = new JPanel();
        pnlAviso.setLayout(new BoxLayout(pnlAviso, BoxLayout.Y_AXIS));
        pnlAviso.setBackground(new Color(240, 255, 240));
        pnlAviso.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 180, 80), 1),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));
        pnlAviso.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlAviso.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel lblAvisoTitulo = new JLabel("Desconto especial!");
        lblAvisoTitulo.setFont(new Font("Monospaced", Font.BOLD, 13));
        lblAvisoTitulo.setForeground(new Color(0, 130, 0));

        JLabel lblAvisoDesc = new JLabel("PIX ou Dinheiro: 10% OFF");
        lblAvisoDesc.setFont(new Font("Monospaced", Font.PLAIN, 12));
        lblAvisoDesc.setForeground(new Color(0, 130, 0));

        pnlAviso.add(lblAvisoTitulo);
        pnlAviso.add(Box.createVerticalStrut(4));
        pnlAviso.add(lblAvisoDesc);

        pnlCard.add(pnlAviso);
        pnlCard.add(Box.createVerticalGlue());

        // Botão sair
        btnSair.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btnSair.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnSair.setBackground(new Color(200, 50, 50));
        btnSair.setForeground(Color.WHITE);
        btnSair.setFont(new Font("Monospaced", Font.BOLD, 14));
        btnSair.setFocusPainted(false);
        btnSair.setBorderPainted(false);
        btnSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSair.addActionListener(e -> { dispose(); new TelaLogin().setVisible(true); });
        pnlCard.add(btnSair);

        add(pnlCard, BorderLayout.WEST);

        // Tabela
        tabela.setFont(new Font("Monospaced", Font.PLAIN, 13));
        tabela.setRowHeight(30);
        tabela.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 13));
        tabela.setSelectionBackground(new Color(180, 215, 255));
        tabela.setRowSorter(sorter);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        add(scroll, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            TelaAutoAtendimento tela = new TelaAutoAtendimento();
            tela.setVisible(true);
        });
    }
}