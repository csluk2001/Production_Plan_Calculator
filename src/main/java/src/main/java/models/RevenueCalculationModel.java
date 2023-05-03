package src.main.java.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

/**
 * A model class that contains various constants and methods related to revenue calculation for the winery.
 * @author {CHAN, Chun Wai Tommy, LUK, Chun San Marco, YIU, Ka Ho Alex}, Github id: {iawiawiaw, csluk2001, AcupOfHappiness}
 * @version Java15
 */

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

    /**
     * The amount of labour consumption to make a quart (Litre) of wine for Rose.
     */
    final int ROSE_CONSUMPTION_TO_MAKE_A_LITRE_OF_WINE_LABOUR = 5;

    /**
     * The amount of labour consumption to make a quart (Litre) of wine for Noir.
     */
    final int NOIR_CONSUMPTION_TO_MAKE_A_LITRE_OF_WINE_LABOUR = 12;

    /**
     * The amount of grape consumption to make a quart (Litre) of wine for rose.
     */
    final int ROSE_CONSUMPTION_TO_MAKE_A_LITRE_OF_WINE_GRAPE = 6;

    /**
     * The amount of grape consumption to make a quart (Litre) of wine for Pinot Noir.
     */
    final int NOIR_CONSUMPTION_TO_MAKE_A_LITRE_OF_WINE_GRAPE = 4;

    /**
     * The maximum production capacity of manufacturing facilities per week.
     */
    final int MAX_PRODUCTION_CAPACITY_OF_MANUFACTURING_FACILITIES = 5000;

    /**
     * The standard man power per hand in a week.
     */
    final float STANDARD_MAN_POWER_PER_HEAD = 37.5f;

    /**
     * The labour cost rate per week.
     */
    final int LABOUR_COST_RATE_PER_WEEK = 935;

    /**
     * The week of the year.
     */
    private int WeekOfYear = Integer.MIN_VALUE;

    /**
     * The user input of production capacity of labour.
     */
    private int capLabour = Integer.MIN_VALUE;

    /**
     * The user input of production capacity of grapes.
     */
    private int capGrape = Integer.MIN_VALUE;

    /**
     * The user input of price of a rose wine.
     */
    private float prcRose = Float.MIN_VALUE;

    /**
     * The user input of price of a noir wine.
     */
    private float prcNoir = Float.MIN_VALUE;

    /**
     * The user input of fixed costs.
     */
    private int FixedCosts=Integer.MIN_VALUE;

    /**
     * The user input of backorder volume for rose wine.
     */
    private int backorderVolumeRose = Integer.MIN_VALUE;

    /**
     * The user input of backorder volume for noir wine.
     */
    private int backorderVolumeNoir = Integer.MIN_VALUE;

    /**
     * The remaining production capacity of labour after producing backorder.
     */
    private int remainingCapLabourAfterBackorder = Integer.MIN_VALUE;

    /**
     * The remaining production capacity of grape after producing backorder.
     */
    private int remainingCapGrapeAfterBackorder = Integer.MIN_VALUE;


    /**
     * Returns {@code true} if the input is a numeric value.
     *
     * @param input the string input to be checked for numeric value.
     * @return {@code true} if the input is a numeric value; otherwise {@code false}.
     */
    public boolean isNumeric(String input) {
        return input.matches("-?\\d+(\\.\\d+)?") // match a number with optional '-' and decimal.
                && !input.equals(""); // Not Null string
    }

    /**
     * Checks whether the given input string is an integer or not.
     *
     * @param input The string to check.
     * @return {@code true} if the input string is an integer, {@code false} otherwise.
     */
    public boolean isInteger(String input) {
        return input.matches("-?\\d+$"); // only contains 0 - 9 digits
    }

    /**
     * Checks whether the given input string is empty or not.
     *
     * @param input The string to check.
     * @return {@code true} if the input string is empty, {@code false} otherwise.
     */
    public boolean isEmptyField(String input) {
        return input.equals("");
    }

    /**
     * Checks if the given input is greater or equal to 1.
     * @param input The input string to be validated.
     * @return A boolean value indicating if the input is greater or equal to 1.
     */
    public boolean isGreaterThanOne(String input) {
        if (this.isInteger(input)) {
            return (Integer.parseInt(input) >= 1);
        } else {
            return (Float.parseFloat(input) >= 1);
        }
    }

    /**
     * Checks if the given input is a valid week of the year.
     * @param input The input string to be validated.
     * @return A boolean value indicating if the input is a valid week of the year.
     */
    public boolean isValidWeekOfYear(String input) {
        if (this.isNumeric(input) && isInteger(input) || this.isEmptyField(input)) {
            int numOfWeek = Integer.parseInt(input);
            return (2301 <= numOfWeek && numOfWeek <= 2315); // Week of the year should be in range from 2301 to 2315.
        }
        return false;
    }

    /**
     * Checks if the given input is a valid number of weeks.
     * @param input The input string to be validated.
     * @return A boolean value indicating if the input is a valid number of weeks.
     */
    public boolean isValidNumWeek(String input) {
        if (this.isNumeric(input) && isInteger(input) || this.isEmptyField(input)) {
            int NumWeek = Integer.parseInt(input);
            return (1 <= NumWeek && NumWeek <= 15);
        }
        return false;
    }
}
