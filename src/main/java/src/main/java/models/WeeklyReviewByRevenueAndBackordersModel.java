package src.main.java.models;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeeklyReviewByRevenueAndBackordersModel extends WeeklyReviewByRevenueModel{

    public WeeklyReviewByRevenueAndBackordersModel() {
        super();
    }

    public boolean backorderAndProductionVolumeRatioInbalance()
    {
        return true;
    }
}
