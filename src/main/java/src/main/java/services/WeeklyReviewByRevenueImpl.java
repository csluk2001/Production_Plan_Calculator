package src.main.java.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.main.java.models.OptimalSalesRevenueModel;

/**
 * This is a service class that implement all the calculation for PPC1 Calculator.
 * @author {CHAN, Chun Wai Tommy, LUK, Chun San Marco, YIU, Ka Ho Alex}, Github id: {iawiawiaw, csluk2001, AcupOfHappiness}
 * @version  Java15
 */


public class WeeklyReviewByRevenueImpl {

    /**
     * The logger for debugging.
     */
    final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * The consumption of labour required to produce one litre of Rosé wine from user input.
     */
    private final int labourConsumptionOfRose;

    /**
     * The consumption of labour required to produce one litre of Noir wine from user input.
     */
    private final int labourConsumptionOfNoir;

    /**
     * The consumption of grapes required to produce one litre of Rosé wine from user input.
     */
    private final int grapeConsumptionOfRose;

    /**
     * The consumption of grapes required to produce one litre of Noir wine from user input.
     */
    private final int grapeConsumptionOfNoir;

    /**
     * Constructs an instance of the {@link WeeklyReviewByRevenueImpl} class with the specified labour and grape consumption values.
     *
     * @param labourConsumptionOfRose the consumption of labour required to produce one litre of Rosé wine
     * @param labourConsumptionOfNoir the consumption of labour required to produce one litre of Noir wine
     * @param grapeConsumptionOfRose  the consumption of grapes required to produce one litre of Rosé wine
     * @param grapeConsumptionOfNoir  the consumption of grapes required to produce one litre of Noir wine
     */
    public WeeklyReviewByRevenueImpl(int labourConsumptionOfRose, int labourConsumptionOfNoir, int grapeConsumptionOfRose, int grapeConsumptionOfNoir) {
        this.labourConsumptionOfRose = labourConsumptionOfRose;
        this.labourConsumptionOfNoir = labourConsumptionOfNoir;
        this.grapeConsumptionOfRose = grapeConsumptionOfRose;
        this.grapeConsumptionOfNoir = grapeConsumptionOfNoir;
    }

    /**
     * Calculates the optimal mix of wines based on the prices of Rose and Noir wines, and labour and grape capacity by integer linear programming.
     *
     * @param PrcRosePrice the price of one litre of Rosé wine
     * @param PrcNoirPrice the price of one litre of Noir wine
     * @param capLabour    the capacity of labour
     * @param capGrape     the capacity of grape
     * @return an instance of {@link OptimalSalesRevenueModel} that represents the optimal mix of wines and the associated sales revenue
     */
    public OptimalSalesRevenueModel optimalMixOfWines(float PrcRosePrice, float PrcNoirPrice, int capLabour, int capGrape) {
        int optimalLitresOfRose = 0, optimalLitresOfNoir = 0;
        float optimalSalesRevenue = 0f, tempRevenue = 0f;
        int maxLitreOfRoseProducedBasedOnGrapeCapacity = capGrape / grapeConsumptionOfRose;
        int maxLitreOfNoirProducedBasedOnGrapeCapacity = capGrape / grapeConsumptionOfNoir;
        int maxLitreOfRoseProducedBasedOnLabourCapacity = capLabour / labourConsumptionOfRose;
        int maxLitreOfNoirProducedBasedOnLabourCapacity = capLabour / labourConsumptionOfNoir;

        int maxLitreOfRoseProduced = Math.min(maxLitreOfRoseProducedBasedOnGrapeCapacity, maxLitreOfRoseProducedBasedOnLabourCapacity);
        int maxLitreOfNoirProduced = Math.min(maxLitreOfNoirProducedBasedOnGrapeCapacity, maxLitreOfNoirProducedBasedOnLabourCapacity);

        // Log the maximum production of Rose and Noir
        logger.info("maxLitre {} {}", maxLitreOfRoseProduced, maxLitreOfNoirProduced);

        // Rose
        for (int i = 0; i <= maxLitreOfRoseProduced; i++) {
            // Noir
            for (int j = 0; j <= maxLitreOfNoirProduced; j++) {
                // If grape consumption of Rose and Noir combined exceed the grape resource planned for the production cycle
                if (grapeConsumptionOfRose * i + grapeConsumptionOfNoir * j > capGrape)
                    break;
                // If labour consumption of Rose and Noir combined exceed the labour resource planned for the production cycle
                if (labourConsumptionOfRose * i + labourConsumptionOfNoir * j > capLabour)
                    break;
                // Calculating the Sales Revenue by each combination
                tempRevenue = PrcRosePrice * i + PrcNoirPrice * j;
                // Compare and update the best Sales Revenue
                if (tempRevenue > optimalSalesRevenue) {
                    optimalSalesRevenue = tempRevenue;
                    optimalLitresOfRose = i;
                    optimalLitresOfNoir = j;
                    logger.info("New optimal mix is found: Rose: {}, Noir: {}, Revenue: {}", optimalLitresOfRose, optimalLitresOfNoir, optimalSalesRevenue);
                }
            }
        }
        return new OptimalSalesRevenueModel(optimalLitresOfRose, optimalLitresOfNoir, Math.round(optimalSalesRevenue), optimalSalesRevenue);
    }

    /**
     * Calculates the surplus of labour and grape resources after production
     *
     * @param optimalLitresOfRose The optimal litres of Rosé wine
     * @param optimalLitresOfNoir The optimal litres of noir wine
     * @param capacityOfLabour    The production capacity of labour
     * @param capacityOfGrape     The production capacity of grape
     * @return An integer array with the labour surplus at index 0 and grape surplus at index 1
     */
    public int[] labourAndGrapeSurplus(int optimalLitresOfRose, int optimalLitresOfNoir, int capacityOfLabour, int capacityOfGrape) {
        /*
            Resource Surplus of Labour: Sur_Labour = Cap_Labour - (Opt_Rose * 5 + Opt_Noir * 12)
            Resource Surplus of Grape: Sur_Grape = Cap_Grape - (Opt_Rose * 6 + Opt_Noir * 4)
         */
        return new int[]{
                capacityOfLabour - (optimalLitresOfRose * labourConsumptionOfRose + optimalLitresOfNoir * labourConsumptionOfNoir),
                capacityOfGrape - (optimalLitresOfRose * grapeConsumptionOfRose + optimalLitresOfNoir * grapeConsumptionOfNoir)
        };
    }

    /**
     * Checks if the wine production capacity is overloaded on grape
     *
     * @param optRose                   The optimal litres of Rosé wine
     * @param optNoir                   The optimal litres of noir wine
     * @param MAX_PRODUCTION_CAPACITY_PER_WEEK The maximum production capacity per week
     * @return {@code true} if the actual production capacity is less than the sum of optimal litres of rose and noir wines, {@code false} otherwise
     */
    public boolean wineProductionCapacityOverloadedOnGrape(int optRose, int optNoir, int MAX_PRODUCTION_CAPACITY_PER_WEEK) {
        // Requirement: If Actual Production Capacity (i.e. 5,000 litres per week) < optRose + optNoir
        return MAX_PRODUCTION_CAPACITY_PER_WEEK * 5000 < optRose + optNoir;
    }

    /**
     * Checks if the grape resource utilization is insufficient due to insufficient labour supplied
     *
     * @param optRose  The optimal litres of Rosé wine
     * @param optNoir  The optimal litres of noir wine
     * @param capGrape The production capacity of grape
     * @return {@code true} if the consumption of grapes is less than 90% of the given capacity due to insufficient labour supplied, {@code false} otherwise
     */
    public boolean grapeResourceUtilizationIsInsufficientDueToInsufficientLabourSupplied(int optRose, int optNoir, int capGrape) {
        // Requirement: After optimization of order mix, if the consumption of grapes is less than 90% of the given capacity due to insufficient labour supplied
        return capGrape * 0.9 > optRose * grapeConsumptionOfRose + optNoir * grapeConsumptionOfNoir;
    }
}
