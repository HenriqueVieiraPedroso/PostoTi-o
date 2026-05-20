import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {
    
    private JPasswordField txtSenha = new JPasswordField();
    private JLabel lblSenha = new JLabel("Senha:");
    private JLabel lblNome = new JLabel("Nome:");
    private JTextField txtNome = new JTextField();
    private JButton btnEntrar = new JButton("Entrar");
    private JPanel pnlCard = new JPanel();
    private JLabel lblTitulo = new JLabel("Auto Peças - Tião");
    private JPanel pnlDados = new JPanel();

    public TelaLogin() {
        setTitle("TelaLogin - Auto Peça Tião");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(Color.LIGHT_GRAY); // fundo cinza na tela

        pnlDados.setLayout(new BoxLayout(pnlDados, BoxLayout.Y_AXIS));
        pnlDados.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlDados.setBackground(Color.WHITE); 
        
        pnlCard.setLayout(new BoxLayout(pnlCard, BoxLayout.Y_AXIS));
        pnlCard.setBackground(Color.WHITE); 
        pnlCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1), 
            BorderFactory.createEmptyBorder(30, 30, 30, 30) 
        ));
        pnlCard.setPreferredSize(new Dimension(450, 420));
        
        lblTitulo.setFont(new Font("Monospaced", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(0, 120, 215)); 
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        
        lblNome.setFont(new Font("Monospaced", Font.BOLD, 14));
        lblNome.setHorizontalAlignment(SwingConstants.LEFT);
        lblNome.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        lblNome.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblSenha.setFont(new Font("Monospaced", Font.BOLD, 14));
        lblSenha.setHorizontalAlignment(SwingConstants.LEFT);
        lblSenha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        lblSenha.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtNome.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        txtNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtNome.setFont(new Font("Monospaced", Font.PLAIN, 14)); 
        txtNome.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)), 
            BorderFactory.createEmptyBorder(4, 8, 4, 8) 
        ));

        txtSenha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        txtSenha.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtSenha.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtSenha.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));

        btnEntrar.setBackground(new Color(0, 180, 80)); 
        btnEntrar.setForeground(Color.WHITE); 
        btnEntrar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btnEntrar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnEntrar.setFont(new Font("Monospaced", Font.BOLD, 14));
        btnEntrar.setFocusPainted(false);
        btnEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnEntrar.addActionListener(e -> {
            String nome = txtNome.getText();
            String senha = new String(txtSenha.getPassword());

            if (nome.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            } else {
                JOptionPane.showMessageDialog(null, "Bem vindo, " + nome + "!");
            }
        });

        pnlDados.add(lblNome);
        pnlDados.add(Box.createVerticalStrut(5));
        pnlDados.add(txtNome);
        pnlDados.add(Box.createVerticalStrut(15));
        pnlDados.add(lblSenha);
        pnlDados.add(Box.createVerticalStrut(5));
        pnlDados.add(txtSenha);
        pnlDados.add(Box.createVerticalStrut(20));
        pnlDados.add(btnEntrar);

        pnlCard.add(lblTitulo);
        pnlCard.add(Box.createVerticalStrut(20));
        pnlCard.add(pnlDados);

        add(pnlCard);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            TelaLogin tela = new TelaLogin();
            tela.setVisible(true);
        });
    }
}