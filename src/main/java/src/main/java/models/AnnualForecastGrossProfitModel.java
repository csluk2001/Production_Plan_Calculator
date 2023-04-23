package src.main.java.models;
import lombok.Getter;
import lombok.Setter;
import src.main.java.services.WeeklyReviewByRevenueImpl;

@Getter
@Setter

/**
 * This AnnualForecastGrossProfitModel class is a data model for PPC1 Function_A calculator
 * that is used in Function A GUI /resource/frontend/annualForecastByGrossProfit.fxml.
 * It extends the WeeklyReviewByRevenueModel class/model.
 * @author CHAN, Chun Wai Tommy GitHub id: iawiawiaw
 * @version Java15
 */

public class AnnualForecastGrossProfitModel extends WeeklyReviewByRevenueModel{

    /**
     * Constructor that initializes the AnnualForecastGrossProfitModel by calling the super constructor {@link WeeklyReviewByRevenueModel}.
     */
    public AnnualForecastGrossProfitModel() {
        super();
    }
}
