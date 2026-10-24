
package analysis;

public class Analysis {

    public static double currentRatio(double currentAssets,
                                      double currentLiabilities) {
        if (currentLiabilities == 0) {
            return 0;
        }
        return currentAssets / currentLiabilities;
    }

    public static double quickRatio(double currentAssets,
                                    double inventory,
                                    double currentLiabilities) {
        if (currentLiabilities == 0) {
            return 0;
        }
        return (currentAssets - inventory) / currentLiabilities;
    }

    public static double returnOnAssets(double netProfit,
                                        double totalAssets) {
        if (totalAssets == 0) {
            return 0;
        }
        return (netProfit / totalAssets) * 100;
    }

    public static double netProfitMargin(double netProfit,
                                         double totalIncome) {
        if (totalIncome == 0) {
            return 0;
        }
        return (netProfit / totalIncome) * 100;
    }
}
