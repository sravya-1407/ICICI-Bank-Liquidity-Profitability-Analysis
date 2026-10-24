
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankData {

    public boolean insertData(
            int year,
            double currentAssets,
            double currentLiabilities,
            double netProfit,
            double totalAssets,
            double totalIncome) {

        String sql = "INSERT INTO bank_data "
                + "(year, current_assets, current_liabilities, "
                + "net_profit, total_assets, revenue) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection()) {
            if (con == null) return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, year);
                ps.setDouble(2, currentAssets);
                ps.setDouble(3, currentLiabilities);
                ps.setDouble(4, netProfit);
                ps.setDouble(5, totalAssets);
                ps.setDouble(6, totalIncome);

                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error inserting bank data.");
            e.printStackTrace();
            return false;
        }
    }

    public void displayData() {
        String sql = "SELECT * FROM bank_data ORDER BY year";

        try (Connection con = DatabaseConnection.getConnection()) {
            if (con == null) return;

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    System.out.println(
                        "Year: " + rs.getInt("year")
                        + ", Current Assets: "
                        + rs.getDouble("current_assets")
                        + ", Current Liabilities: "
                        + rs.getDouble("current_liabilities")
                        + ", Net Profit: "
                        + rs.getDouble("net_profit")
                        + ", Total Assets: "
                        + rs.getDouble("total_assets")
                        + ", Revenue: " + rs.getDouble("revenue")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving bank data.");
            e.printStackTrace();
        }
    }
}
