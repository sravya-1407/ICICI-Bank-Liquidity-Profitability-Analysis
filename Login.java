
package login;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    public Login() {
        setTitle("ICICI Bank Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel heading = new JLabel(
            "ICICI Bank Login",
            JLabel.CENTER
        );
        heading.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Username:"));
        JTextField username = new JTextField();
        panel.add(username);

        panel.add(new JLabel("Password:"));
        JPasswordField password = new JPasswordField();
        panel.add(password);

        JButton loginButton = new JButton("Login");
        panel.add(new JLabel());
        panel.add(loginButton);

        loginButton.addActionListener(e -> {
            String user = username.getText().trim();
            String pass = new String(password.getPassword());

            if (user.equals("admin") && pass.equals("1234")) {
                JOptionPane.showMessageDialog(
                    this, "Login Successful!"
                );

                new Main().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(
                    this, "Invalid Username or Password"
                );
            }
        });

        add(heading, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new Login().setVisible(true)
        );
    }

    private static class Main extends JFrame {
        Main() {
            setTitle("ICICI Bank Analysis System");
            setSize(500, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JButton liquidity = new JButton("Liquidity Analysis");
            JButton profitability = new JButton("Profitability Analysis");

            liquidity.addActionListener(e ->
                new liquidity.Liquidity().setVisible(true)
            );

            profitability.addActionListener(e ->
                new profitability.Profitability().setVisible(true)
            );

            setLayout(new FlowLayout());
            add(liquidity);
            add(profitability);
        }
    }
}
