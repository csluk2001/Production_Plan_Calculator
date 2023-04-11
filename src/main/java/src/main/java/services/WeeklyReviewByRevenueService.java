package src.main.java.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.main.java.models.OptimalSalesRevenueModel;

import static java.lang.Math.pow;

public class WeeklyReviewByRevenueService {
    final Logger logger = LoggerFactory.getLogger(getClass());
    private final int labourConsumptionOfRose;
    private final int labourConsumptionOfNoir;
    private final int grapeConsumptionOfRose;
    private final int grapeConsumptionOfNoir;

    public WeeklyReviewByRevenueService(int labourConsumptionOfRose, int labourConsumptionOfNoir, int grapeConsumptionOfRose, int grapeConsumptionOfNoir) {
        this.labourConsumptionOfRose = labourConsumptionOfRose;
        this.labourConsumptionOfNoir = labourConsumptionOfNoir;
        this.grapeConsumptionOfRose = grapeConsumptionOfRose;
        this.grapeConsumptionOfNoir = grapeConsumptionOfNoir;
    }

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
        for (int i = 0; i < maxLitreOfRoseProduced; i++) {
            // Noir
            for (int j = 0; j < maxLitreOfNoirProduced; j++) {
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
        return new OptimalSalesRevenueModel(optimalLitresOfRose, optimalLitresOfNoir, optimalSalesRevenue);
    }

    public int[] labourAndGrapeSurplus(int optimalLitresOfRose, int optimalLitresOfNoir, int capacityOfLabour, int capacityOfGrape) {
        /*
            Resource Surplus of Labour: Sur_Labour = Cap_Labour - (Opt_Rose * 5 + Opt_Noir * 12)
            Resource Surplus of Grape: Sur_Grape = Cap_Grape - (Opt_Rose * 6 + Opt_Noir * 4)
         */
        return new int[]{
                capacityOfLabour - (optimalLitresOfRose * 5 + optimalLitresOfNoir * 12),
                capacityOfGrape - (optimalLitresOfRose * 6 + optimalLitresOfNoir * 4)
        };
    }

    public boolean wineProductionCapacityOverloadedOnGrape(int optRose, int optNoir, int numWeek) {
        // Requirement: If Actual Production Capacity (i.e. 5,000 litres per week) < optRose + optNoir
        int numOfWeek = 0;
        for (int i = 0; i < 2; i++) {
            numOfWeek += numWeek % 10 * pow(10, i);
            numWeek /= 10;
        }
        logger.info("Number of weeks: {}", numOfWeek);
        return 5000 * numOfWeek < optRose + optNoir;
    }

    public boolean grapeResourceUtilizationIsInsufficientDueToInsufficientLabourSupplied(int optRose, int optNoir, int capGrape, int capLabour) {
        // Requirement: After optimization of order mix, if the consumption of grapes is less than 90% of the given capacity due to insufficient labour supplied
        if (capGrape * 0.9 > optRose + optNoir) {
            // capLabour is all used up, so there is insufficient labour supplied
            return capLabour < 5;
        }
        return false;
    }
}
