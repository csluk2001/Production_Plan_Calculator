package src.main.java.models;

import lombok.Getter;
import lombok.Setter;
import src.main.java.services.WeeklyReviewByRevenueImpl;

/**
 * The WeeklyReviewByRevenueModel class represents a data model for function_B - the weekly review by revenue calculation
 * that is used in Function B GUI /resource/frontend/weeklyReviewByRevenue.fxml.
 * It extends the RevenueCalculationModel class.
 * @author LUK, Chun San Marco, Github id: csluk2001
 * @version Java15
 */
@Getter
@Setter
public class WeeklyReviewByRevenueModel extends RevenueCalculationModel {

    /**
     * The weeklyReviewByRevenueImpl object is used to access the methods from WeeklyReviewByRevenueImpl class.
     */
    protected WeeklyReviewByRevenueImpl weeklyReviewByRevenueImpl;

    /**
     * Constructs a WeeklyReviewByRevenueModel object with corresponding user input
     */
    public WeeklyReviewByRevenueModel() {
        super();
        weeklyReviewByRevenueImpl = new WeeklyReviewByRevenueImpl(this.getROSE_CONSUMPTION_TO_MAKE_A_LITRE_OF_WINE_LABOUR(),
                this.getNOIR_CONSUMPTION_TO_MAKE_A_LITRE_OF_WINE_LABOUR(),
                this.getROSE_CONSUMPTION_TO_MAKE_A_LITRE_OF_WINE_GRAPE(),
                this.getNOIR_CONSUMPTION_TO_MAKE_A_LITRE_OF_WINE_GRAPE()
        );
    }

    /**
     * Calculates the optimal sales revenue and returns an OptimalSalesRevenueModel object containing the
     * optimal values for Rose, Noir and Sales Revenue.
     *
     * @return An OptimalSalesRevenueModel object containing the optimal values for Rose, Noir and Sales Revenue.
     */
    public OptimalSalesRevenueModel calculateOptimalProductionValue() {
        /*
            1. float optimalRoseForProduction
            2. float optimalNoirForProduction
            3. int optimalSalesRevenue
        */

        // public Object optimalMixOfWines(float PrcRosePrice, float PrcNoirPrice, int capLabour, int capGrape, int weekOfHarvest)
        return this.weeklyReviewByRevenueImpl.optimalMixOfWines(this.getPrcRose(), this.getPrcNoir(), this.getCapLabour(), this.getCapGrape());
    }


    /**
     * Calculates the labour and grape surplus for the given optimal Rose and Noir production values.
     *
     * @param optRose Optimal litres of Rose production value.
     * @param optNoir Optimal litres of Noir production value.
     * @return An int array containing the labour and grape surplus.
     */
    public int[] calculateLabourAndGrapeSurplus(int optRose, int optNoir) {
        // public int[] labourAndGrapeSurplus(int optimalLitresOfRose, int optimalLitresOfNoir, int capacityOfLabour, int capacityOfGrape)
        return this.weeklyReviewByRevenueImpl.labourAndGrapeSurplus(optRose, optNoir, this.getCapLabour(), this.getCapGrape());
    }

    /**
     * Checks if the production capacity is overloaded on grape for the given actual optimal Rose and Noir production values.
     *
     * @param optRose Optimal litres of Rose production value.
     * @param optNoir Optimal litres of Noir production value.
     * @return True if the production capacity is overloaded on grape, false otherwise.
     */
    public boolean wineProductionCapacityOverloadedOnGrape(int optRose, int optNoir) {
        return this.weeklyReviewByRevenueImpl.wineProductionCapacityOverloadedOnGrape(optRose, optNoir, 1);
    }

    /**
     * Checks if the grape resource utilization is insufficient due to insufficient labour supply for the given optimal
     * Rose and Noir production values.
     * @param optRose Optimal litres of Rose production value.
     * @param optNoir Optimal litres of Noir production value.
     * @param capGrape Capacity of Grape.
     * @return True if the actual consumption of grape is lower than the grape provided in that week, false otherwise.
     */
    public boolean grapeResourceUtilizationIsInsufficientDueToInsufficientLabourSupplied(int optRose, int optNoir, int capGrape) {
        return this.weeklyReviewByRevenueImpl.grapeResourceUtilizationIsInsufficientDueToInsufficientLabourSupplied(optRose, optNoir, capGrape);
    }
}
