
package profitability;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.DatabaseConnection;

public class Profitability extends JFrame {

    public Profitability() {
        setTitle("ICICI Bank - Profitability Analysis");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel(
            "Profitability Analysis (2020–2024)", JLabel.CENTER
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
                        double profit = rs.getDouble("net_profit");
                        double assets = rs.getDouble("total_assets");
                        double revenue = rs.getDouble("revenue");
                        double equity = rs.getDouble("shareholders_equity");

                        // Equity column is not currently in bank_data.
                        output.append("Year: ").append(year).append("\n");
                        output.append(String.format(
                            "Return on Assets (ROA): %.2f%%%n",
                            returnOnAssets(profit, assets)
                        ));
                        output.append(String.format(
                            "Net Profit Margin: %.2f%%%n",
                            netProfitMargin(profit, revenue)
                        ));
                        output.append(String.format(
    "Return on Equity (ROE): %.2f%%%n",
    returnOnEquity(profit, equity)
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

    public double returnOnAssets(double netProfit, double totalAssets) {
        if (totalAssets == 0) return 0;
        return (netProfit / totalAssets) * 100;
    }

    public double returnOnEquity(double netProfit, double equity) {
        if (equity == 0) return 0;
        return (netProfit / equity) * 100;
    }

    public double netProfitMargin(double netProfit, double sales) {
        if (sales == 0) return 0;
        return (netProfit / sales) * 100;
    }
}

