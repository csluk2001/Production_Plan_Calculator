package src.main.java.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RevenueCalculationModel {
    /*
        Production Formula:
            1. Labour consumption to make a quart (Litre) of wine
                -> Rose   = 5 minutes
                -> P-Noir = 12 minutes
            2. Grape consumption to make a quart (Litre) of wine
                -> Rose   = 6 Kgs
                -> P-Noir = 4 Kgs
        Other predictions for the current year
            1. Production capacity of manufacturing facilities
                -> Less than or equal to 5,000 Litres per week
            2. Standard man power per hand
                -> 37.5 Man Hours per week
            3. Labor cost rate per week
                -> A$935 per week
    */
    final int ROSE_CONSUMPTION_TO_MAKE_A_LITRE_OF_WINE_LABOUR = 5;
    final int NOIR_CONSUMPTION_TO_MAKE_A_LITRE_OF_WINE_LABOUR = 12;
    final int ROSE_CONSUMPTION_TO_MAKE_A_LITRE_OF_WINE_GRAPE = 6;
    final int NOIR_CONSUMPTION_TO_MAKE_A_LITRE_OF_WINE_GRAPE = 4;

    final int MAX_PRODUCTION_CAPACITY_OF_MANUFACTURING_FACILITIES = 5000;
    final float STANDARD_MAN_POWER_PER_HEAD = 37.5f;
    final int LABOUR_COST_RATE_PER_WEEK = 935;

    private int numWeek = 0;
    private int capLabour = 0;
    private int capGrape = 0;
    private float prcRose = 0f;
    private float prcNoir = 0f;

    private int backorderVolumeRose = 0;
    private int backorderVolumeNoir = 0;
    private int remainingCapLabourAfterBackorder = 0;
    private int remainingCapGrapeAfterBackorder = 0;


    public boolean isNumeric(String input) {
        return input.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public boolean isValidNumWeek(String input) {
        if (this.isNumeric(input)) {
            int numWeek = Integer.parseInt(input);
            return (2301 <= numWeek && numWeek <= 2315); // Week of the year should be in range from 2301 to 2315.
        }
        return false;
    }
}
