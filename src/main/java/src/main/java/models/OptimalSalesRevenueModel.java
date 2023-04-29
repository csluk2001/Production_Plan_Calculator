package src.main.java.models;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * A data model class that contains all calculation results.
 * @author {CHAN, Chun Wai Tommy, LUK, Chun San Marco, YIU, Ka Ho Alex}, Github id: {iawiawiaw, csluk2001, AcupOfHappiness}
 * @version Java15
 */
@Data
@AllArgsConstructor
public class OptimalSalesRevenueModel {

    /**
     * The optimal amount of rose wine to produce.
     */
    private int optimalLitresOfRose;

    /**
     * The optimal amount of noir wine to produce.
     */
    private int optimalLitresOfNoir;

    /**
     * The optimal sales revenue obtained by selling the optimal amount of wine.
     */
    private int optimalSalesRevenue;

    /**
     * The optimal sales revenue obtained by selling the optimal amount of wine (not rounded off).
     */
    private float optimalSalesRevenueNonRoundOff = 0.0f;
}
