
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        setTitle("ICICI Bank Analysis System");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel(
            "ICICI Bank Analysis System",
            JLabel.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 20));

        JButton liquidityButton =
            new JButton("Liquidity Analysis");

        JButton profitabilityButton =
            new JButton("Profitability Analysis");

        liquidityButton.addActionListener(e -> {
            new liquidity.Liquidity().setVisible(true);
        });

        profitabilityButton.addActionListener(e -> {
            new profitability.Profitability().setVisible(true);
        });

        JPanel panel = new JPanel();
        panel.add(liquidityButton);
        panel.add(profitabilityButton);

        add(title, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}
