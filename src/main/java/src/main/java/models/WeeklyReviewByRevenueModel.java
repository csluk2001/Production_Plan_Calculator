package src.main.java.models;

import lombok.Getter;
import lombok.Setter;
import src.main.java.services.WeeklyReviewByRevenueService;

@Getter
@Setter
public class WeeklyReviewByRevenueModel extends RevenueCalculationModel {
    protected WeeklyReviewByRevenueService weeklyReviewByRevenueService;

    public WeeklyReviewByRevenueModel() {
        super();
        weeklyReviewByRevenueService = new WeeklyReviewByRevenueService(this.getROSE_CONSUMPTION_TO_MAKE_A_LITRE_OF_WINE_LABOUR(),
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
        return this.weeklyReviewByRevenueService.optimalMixOfWines(this.getPrcRose(), this.getPrcNoir(), this.getCapLabour(), this.getCapGrape());
    }

    public int[] calculateLabourAndGrapeSurplus(int optRose, int optNoir) {
        // public int[] labourAndGrapeSurplus(int optimalLitresOfRose, int optimalLitresOfNoir, int capacityOfLabour, int capacityOfGrape)
        return this.weeklyReviewByRevenueService.labourAndGrapeSurplus(optRose, optNoir, this.getCapLabour(), this.getCapGrape());
    }

    public boolean wineProductionCapacityOverloadedOnGrape(int optRose, int optNoir) {
        return this.weeklyReviewByRevenueService.wineProductionCapacityOverloadedOnGrape(optRose, optNoir, this.getMAX_PRODUCTION_CAPACITY_OF_MANUFACTURING_FACILITIES());
    }

    public boolean grapeResourceUtilizationIsInsufficientDueToInsufficientLabourSupplied(int optRose, int optNoir, int capGrape) {
        return this.weeklyReviewByRevenueService.grapeResourceUtilizationIsInsufficientDueToInsufficientLabourSupplied(optRose, optNoir, capGrape);
    }
}
