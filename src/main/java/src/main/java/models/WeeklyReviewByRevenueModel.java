package src.main.java.models;

import lombok.Getter;
import lombok.Setter;
import src.main.java.services.WeeklyReviewByRevenueImpl;

@Getter
@Setter
public class WeeklyReviewByRevenueModel extends RevenueCalculationModel {
    protected WeeklyReviewByRevenueImpl weeklyReviewByRevenueImpl;

    public WeeklyReviewByRevenueModel() {
        super();
        weeklyReviewByRevenueImpl = new WeeklyReviewByRevenueImpl(this.getROSE_CONSUMPTION_TO_MAKE_A_LITRE_OF_WINE_LABOUR(),
                this.getNOIR_CONSUMPTION_TO_MAKE_A_LITRE_OF_WINE_LABOUR(),
                this.getROSE_CONSUMPTION_TO_MAKE_A_LITRE_OF_WINE_GRAPE(),
                this.getNOIR_CONSUMPTION_TO_MAKE_A_LITRE_OF_WINE_GRAPE()
        );
    }

    public OptimalSalesRevenueModel calculateOptimalProductionValue() {
        /*
            1. float optimalRoseForProduction
            2. float optimalNoirForProduction
            3. int optimalSalesRevenue
        */

        // public Object optimalMixOfWines(float PrcRosePrice, float PrcNoirPrice, int capLabour, int capGrape, int weekOfHarvest)
        return this.weeklyReviewByRevenueImpl.optimalMixOfWines(this.getPrcRose(), this.getPrcNoir(), this.getCapLabour(), this.getCapGrape());
    }

    public int[] calculateLabourAndGrapeSurplus(int optRose, int optNoir) {
        // public int[] labourAndGrapeSurplus(int optimalLitresOfRose, int optimalLitresOfNoir, int capacityOfLabour, int capacityOfGrape)
        return this.weeklyReviewByRevenueImpl.labourAndGrapeSurplus(optRose, optNoir, this.getCapLabour(), this.getCapGrape());
    }

    public boolean wineProductionCapacityOverloadedOnGrape(int optRose, int optNoir) {
        return this.weeklyReviewByRevenueImpl.wineProductionCapacityOverloadedOnGrape(optRose, optNoir, 1);
    }

    public boolean grapeResourceUtilizationIsInsufficientDueToInsufficientLabourSupplied(int optRose, int optNoir, int capGrape) {
        return this.weeklyReviewByRevenueImpl.grapeResourceUtilizationIsInsufficientDueToInsufficientLabourSupplied(optRose, optNoir, capGrape);
    }
}
