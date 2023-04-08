package src.main.java.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OptimalSalesRevenueModel {
    private int optimalLitresOfRose;
    private int optimalLitresOfNoir;
    private float optimalSalesRevenue;
}
