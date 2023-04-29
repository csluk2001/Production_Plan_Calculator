package src.main.java.models;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

/**
 * This WeeklyReviewByRevenueAndBackordersModel class is a data model for PPC1 Function_C calculator
 * that is used in Function C GUI /resource/frontend/weeklyReviewByRevenueAndBackorders.fxml.
 * It extends the WeeklyReviewByRevenueModel class/model.
 * @author YIU, Ka Ho Alex GitHub id: AcupOfHappiness
 * @version Java15
 */
public class WeeklyReviewByRevenueAndBackordersModel extends WeeklyReviewByRevenueModel{

    /**
     * Constructor that initializes the WeeklyReviewByRevenueAndBackordersModel by calling the super constructor {@link WeeklyReviewByRevenueModel}.
     */
    public WeeklyReviewByRevenueAndBackordersModel() {
        super();
    }
}
