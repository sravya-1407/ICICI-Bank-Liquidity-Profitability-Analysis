
package liquidity;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.DatabaseConnection;

public class Liquidity extends JFrame {

    public Liquidity() {
        setTitle("ICICI Bank - Liquidity Analysis");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel(
            "Liquidity Analysis (2020–2024)", JLabel.CENTER
        );
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JTextArea result = new JTextArea();
        result.setEditable(false);
        result.setFont(new Font("Consolas", Font.PLAIN, 14));

        StringBuilder output = new StringBuilder();

        String sql = "SELECT * FROM bank_data ORDER BY year";

        try (Connection con = DatabaseConnection.getConnection()) {

            if (con == null) {
                output.append("Database connection failed.");
            } else {
                try (PreparedStatement ps = con.prepareStatement(sql);
                     ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        int year = rs.getInt("year");
                        double assets = rs.getDouble("current_assets");
                        double liabilities =
                            rs.getDouble("current_liabilities");

                        double inventory = rs.getDouble("inventory");
                        double cash = rs.getDouble("cash");

                        output.append("Year: ").append(year).append("\n");
                        output.append(String.format(
                            "Current Ratio: %.2f%n",
                            currentRatio(assets, liabilities)
                        ));
                        output.append(String.format(
                            "Quick Ratio: %.2f%n",
                            quickRatio(assets, inventory, liabilities)
                        ));
                        output.append(String.format(
                            "Cash Ratio: %.2f%n",
                            cashRatio(cash, liabilities)
                        ));
                        output.append("--------------------------------\n");
                    }
                }
            }

        } catch (SQLException e) {
            output.append("Error: ").append(e.getMessage());
        }

        result.setText(output.toString());

        add(title, BorderLayout.NORTH);
        add(new JScrollPane(result), BorderLayout.CENTER);
    }

    public double currentRatio(double assets, double liabilities) {
        if (liabilities == 0) return 0;
        return assets / liabilities;
    }

    public double quickRatio(double assets, double inventory,
                             double liabilities) {
        if (liabilities == 0) return 0;
        return (assets - inventory) / liabilities;
    }

    public double cashRatio(double cash, double liabilities) {
        if (liabilities == 0) return 0;
        return cash / liabilities;
    }
}
