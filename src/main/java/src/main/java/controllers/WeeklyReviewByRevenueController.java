package src.main.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.main.java.Main;
import src.main.java.models.OptimalSalesRevenueModel;
import src.main.java.models.WeeklyReviewByRevenueModel;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * This class is a Controller for PPC1 Calculator Function_B GUI GUI /resource/frontend/weeklyRevenue.fxml.
 * @author LUK, Chun San Marco, Github id: csluk2001
 * @version  Java15
 */

public class WeeklyReviewByRevenueController implements Initializable {

    /**
     * The logger for debugging.
     */
    final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * The "WeeklyReviewByRevenueModel" data model for this controller.
     */
    private final WeeklyReviewByRevenueModel weeklyReviewByRevenueModel;

    /**
     * The IDs of the fields that should be locked while one field is being edited.
     */
    private int[] fieldID;

    /**
     * The input field for the number of years.
     */
    @FXML
    private TextField NumYearValue;

    /**
     * The input field for the labor capacity
     */
    @FXML
    private TextField CapLaborValue;

    /**
     * The input field for the grape capacity.
     */
    @FXML
    private TextField CapGrapeValue;

    /**
     * The input field for the price of the rose wine.
     */
    @FXML
    private TextField PrcRoseValue;

    /**
     * The input field for the price of the noir wine
     */
    @FXML
    private TextField PrcNoirValue;

    /**
     * The label for displaying the optimal rose wine production value.
     */
    @FXML
    private Label DisplayOptRoseValue;

    /**
     * The label for displaying the optimal noir wine production value.
     */
    @FXML
    private Label DisplayOptNoirValue;

    /**
     * The label for displaying the total optimal wine production value.
     */
    @FXML
    private Label DisplayTotalValue;

    /**
     * The label for displaying the total optimal sales revenue value.
     */
    @FXML
    private Label DisplayTotalRevenueValue;

    /**
     * The label for displaying the labor surplus value.
     */
    @FXML
    private Label DisplayLabourSurplusValue;

    /**
     * The label for displaying the grape surplus value.
     */
    @FXML
    private Label DisplayGrapeSurplusValue;

    /**
     * The label for displaying field validation check details.
     */
    @FXML
    private Label FieldValidationCheckDetails;

    /**
     * The label for displaying field validation error messages.
     */
    @FXML
    private Label FieldValidationErrorMessage;

    /**
     * Constructor for the controller. Initializes the data model.
     */
    public WeeklyReviewByRevenueController() {
        weeklyReviewByRevenueModel = new WeeklyReviewByRevenueModel();
    }

    /**
     * Converts an integer input to a comma-separated string.
     *
     * @param input the integer to convert
     * @return the comma-separated string representation of the input
     */
    public String convertToCommaSeparatedString(int input) {
        StringBuilder sb = new StringBuilder();
        String str = Integer.toString(Math.abs(input));
        int len = str.length();
        int count = 0;
        for (int i = len - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
            count++;
            if (count % 3 == 0 && i != 0) {
                sb.append(",");
            }
        }
        if (input < 0) {
            sb.append("-");
        }
        return sb.reverse().toString();
    }

    /**
     * This method synchronizes the value of the NumYearValue text field with
     * the weeklyReviewByRevenueModel's weekOfYear property.
     */
    private void syncNumYearValue() {
        if (weeklyReviewByRevenueModel.isEmptyField(this.NumYearValue.getText())) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            weeklyReviewByRevenueModel.setWeekOfYear(Integer.MIN_VALUE);
            logger.info("NumYear change to null");
            return;
        }
        // numeric validation amd weekOfYear range validation 2301 - 2315
        boolean validInput = weeklyReviewByRevenueModel.isValidWeekOfYear(this.NumYearValue.getText());
        // set validation message
        this.syncFieldValidationCheckDetails(1, validInput);
        if (!validInput) {
            weeklyReviewByRevenueModel.setWeekOfYear(Integer.MIN_VALUE);
            this.fieldID = new int[]{2, 3, 4, 5};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        weeklyReviewByRevenueModel.setWeekOfYear(Integer.parseInt(this.NumYearValue.getText()));
        logger.info("NumYear change to {}", this.NumYearValue.getText());
    }

    /**
     * Synchronizes the value of CapLabour with the data model, validates it and updates the field's validation
     * message label.
     */
    private void syncCapLabourValue() {
        if (weeklyReviewByRevenueModel.isEmptyField(this.CapLaborValue.getText())) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            weeklyReviewByRevenueModel.setCapLabour(Integer.MIN_VALUE);
            logger.info("CapLabor change to null");
            return;
        }
        // numeric validation
        boolean validInput = weeklyReviewByRevenueModel.isInteger(this.CapLaborValue.getText());
        // set validation message
        this.syncFieldValidationCheckDetails(2, validInput);
        if (!validInput) {
            weeklyReviewByRevenueModel.setCapLabour(Integer.MIN_VALUE);
            this.fieldID = new int[]{1, 3, 4, 5};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        weeklyReviewByRevenueModel.setCapLabour(Integer.parseInt(this.CapLaborValue.getText()));
        logger.info("CapLabor change to {}", this.CapLaborValue.getText());
    }

    /**
     * Synchronizes the value of CapGrape with the data model, validates it and updates the field's validation
     * message label.
     */
    public void syncCapGrapeValue() {
        if (weeklyReviewByRevenueModel.isEmptyField(this.CapGrapeValue.getText())) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            weeklyReviewByRevenueModel.setCapGrape(Integer.MIN_VALUE);
            logger.info("CapGrape change to null");
            return;
        }
        // numeric validation
        boolean validInput = weeklyReviewByRevenueModel.isInteger(this.CapGrapeValue.getText());
        // set validation message
        this.syncFieldValidationCheckDetails(3, validInput);
        if (!validInput) {
            weeklyReviewByRevenueModel.setCapGrape(Integer.MIN_VALUE);
            this.fieldID = new int[]{1, 2, 4, 5};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        weeklyReviewByRevenueModel.setCapGrape(Integer.parseInt(this.CapGrapeValue.getText()));
        logger.info("CapGrape change to {}", this.CapGrapeValue.getText());
    }

    /**
     * Synchronizes the value of ProRose with the data model, validates it and updates the field's validation
     * message label.
     */
    private void syncPrcRoseValue() {
        if (weeklyReviewByRevenueModel.isEmptyField(this.PrcRoseValue.getText())) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            weeklyReviewByRevenueModel.setPrcRose(Float.MIN_VALUE);
            logger.info("PrcRose change to null");
            return;
        }
        // numeric validation
        boolean validInput = weeklyReviewByRevenueModel.isNumeric(this.PrcRoseValue.getText());
        // set validation message
        this.syncFieldValidationCheckDetails(4, validInput);
        if (!validInput) {
            weeklyReviewByRevenueModel.setPrcRose(Float.MIN_VALUE);
            this.fieldID = new int[]{1, 2, 3, 5};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        weeklyReviewByRevenueModel.setPrcRose(Float.parseFloat(this.PrcRoseValue.getText()));
        logger.info("PrcRose change to {}", this.PrcRoseValue.getText());
    }

    /**
     * Synchronizes the value of PrcNoirValue with the data model, validates it and updates the field's validation
     * message label.
     */
    private void syncPrcNoirValue() {
        if (weeklyReviewByRevenueModel.isEmptyField(this.PrcNoirValue.getText())) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            weeklyReviewByRevenueModel.setPrcNoir(Float.MIN_VALUE);
            logger.info("PrcNoir change to null");
            return;
        }
        // numeric validation
        boolean validInput = weeklyReviewByRevenueModel.isNumeric(this.PrcNoirValue.getText());
        // validation message
        this.syncFieldValidationCheckDetails(5, validInput);
        if (!validInput) {
            weeklyReviewByRevenueModel.setPrcNoir(Float.MIN_VALUE);
            this.fieldID = new int[]{1, 2, 3, 4};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        weeklyReviewByRevenueModel.setPrcNoir(Float.parseFloat(this.PrcNoirValue.getText()));
        logger.info("PrcNoir change to {}", this.PrcNoirValue.getText());
    }

    /**
     * Updates the FieldValidationCheckDetails label with the appropriate message based on the inputFieldID and valid flag.
     *
     * @param inputFieldID an integer representing the input field being validated
     * @param valid a boolean flag indicating whether the input is correct is or not
     */
    private void syncFieldValidationCheckDetails(int inputFieldID, boolean valid) {
        /*
            inputFieldID:
                1) WeekNum
                2) Cap_Labour
                3) Cap_Noir
                4) Prc_Rose
                5) Prc_Noir
         */
        switch (inputFieldID) {
            case 1 :
                    this.FieldValidationCheckDetails.setText(valid ? "2301 <= WeekOfYear <= 2315" : "WeekOfYear should be in range of 2301 to 2315");
                    break;
            case 2 :
                    this.FieldValidationCheckDetails.setText(valid ? "Cap_Labour is Integer" : "Cap_Labour is not Numeric or Integer");
                    break;
            case 3 :
                    this.FieldValidationCheckDetails.setText(valid ? "Cap_Grape is Integer" : "Cap_Grape is not Numeric or Integer");
                    break;
            case 4 :
                    this.FieldValidationCheckDetails.setText(valid ? "Prc_Rose is Numeric" : "Prc_Rose is not Numeric");
                    break;
            case 5 :
                    this.FieldValidationCheckDetails.setText(valid ? "Prc_Noir is Numeric" : "Prc_Noir is not Numeric");
                    break;
            default : this.FieldValidationCheckDetails.setText("");
        }
    }

    /**
     * Disables the editing capabilities of all the fields except for FieldID 0.
     */
    private void lockOtherFields() {
        for (int id : this.fieldID) {
            switch (id) {
                case 1 -> this.NumYearValue.setEditable(false);
                case 2 -> this.CapLaborValue.setEditable(false);
                case 3 -> this.CapGrapeValue.setEditable(false);
                case 4 -> this.PrcRoseValue.setEditable(false);
                case 5 -> this.PrcNoirValue.setEditable(false);
            }
        }
    }

    /**
     * Enables the editing capabilities of all the fields and resets the fieldID array.
     */
    private void releaseAllFields() {
        this.fieldID = new int[5];
        this.NumYearValue.setEditable(true);
        this.CapLaborValue.setEditable(true);
        this.CapGrapeValue.setEditable(true);
        this.PrcRoseValue.setEditable(true);
        this.PrcNoirValue.setEditable(true);
    }

    /**
     * Checks if all the required fields are filled.
     * @return {@code true} if all required fields are filled, {@code false} otherwise.
     */
    private boolean checkAllFieldsFilled() {
        return !(this.weeklyReviewByRevenueModel.getWeekOfYear() == Integer.MIN_VALUE ||
                this.weeklyReviewByRevenueModel.getCapGrape() == Integer.MIN_VALUE ||
                this.weeklyReviewByRevenueModel.getCapLabour() == Integer.MIN_VALUE ||
                this.weeklyReviewByRevenueModel.getPrcRose() == Float.MIN_VALUE ||
                this.weeklyReviewByRevenueModel.getPrcNoir() == Float.MIN_VALUE);
    }

    /**
     * Runs the calculation based on the input values and updates the appropriate UI elements.
     * If any required field is not filled, it displays an error message in the FieldValidationCheckDetails label
     * and returns without executing the calculation.
     */
    public void runCalculation() {
        logger.info("Input fields NumYear={}, CapLabour={}, CapGrape={}, PrcRose={}, PrcNoir={}",
                this.weeklyReviewByRevenueModel.getWeekOfYear(),
                this.weeklyReviewByRevenueModel.getCapLabour(),
                this.weeklyReviewByRevenueModel.getCapGrape(),
                this.weeklyReviewByRevenueModel.getPrcRose(),
                this.weeklyReviewByRevenueModel.getPrcNoir()
        );

        if (!this.checkAllFieldsFilled()) {
            logger.error("Fields are not fully filled yet!");
            return;
        }
        this.FieldValidationCheckDetails.setText("");

        // Calculate optimal Sales Revenue and display them on the right of the panel
        this.calculateAndDisplayOptimalSalesRevenue();

        // Validate data and pop error message if needed
        this.abnormalSituationValidations();
    }

    /**
     * Returns to the main menu by loading the main menu FXML file "/frontend/applicationMainMenu.fxml".
     * @throws IOException if there is an error loading the main menu FXML file
     */
    public void returnToMain() throws IOException {
        Main.stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/frontend/applicationMainMenu.fxml")))));
    }

    /**
     * Calculates the optimal sales revenue based on the input values and displays the results in the appropriate UI elements.
     */
    private void calculateAndDisplayOptimalSalesRevenue() {
        OptimalSalesRevenueModel optimalSalesRevenue = this.weeklyReviewByRevenueModel.calculateOptimalProductionValue();
        this.DisplayOptRoseValue.setText(Integer.toString(optimalSalesRevenue.getOptimalLitresOfRose()));
        this.DisplayOptNoirValue.setText(Integer.toString(optimalSalesRevenue.getOptimalLitresOfNoir()));
        this.DisplayTotalValue.setText(Integer.toString(optimalSalesRevenue.getOptimalLitresOfRose() + optimalSalesRevenue.getOptimalLitresOfNoir()));
        this.DisplayTotalRevenueValue.setText(this.convertToCommaSeparatedString(optimalSalesRevenue.getOptimalSalesRevenue()));
        int[] labourAndGrapeSurplus = this.weeklyReviewByRevenueModel.calculateLabourAndGrapeSurplus(optimalSalesRevenue.getOptimalLitresOfRose(), optimalSalesRevenue.getOptimalLitresOfNoir());
        // ii.f: If Sur_Labor > 0 but not enough to produce a bottle of any type of wines, set it to zero.
        this.DisplayLabourSurplusValue.setText(labourAndGrapeSurplus[0] > 1 && labourAndGrapeSurplus[0] < 6 && labourAndGrapeSurplus[0] < 4 ? "0" : Integer.toString(labourAndGrapeSurplus[0]));
        // ii.g: If Sur_Grape > 0 but not enough to produce a bottle of any type of wines, set it to zero.
        this.DisplayGrapeSurplusValue.setText(labourAndGrapeSurplus[1] > 1 && labourAndGrapeSurplus[1] < 12 && labourAndGrapeSurplus[1] < 5 ? "0" : Integer.toString(labourAndGrapeSurplus[1]));
    }

    // Field Validations
    /**
     * Performs the abnormal situation validations and updates the FieldValidationErrorMessage label accordingly.
     * The validations are:
     * w1: Insufficient production capacity to produce the optimal mix, please reduce or adjust the capacity of labor & grape volume!
     * w2: Insufficient labor supplied to utilize the grape resource (less than 90%)!
     * If both validations fail, it displays the concatenated error messages in the FieldValidationErrorMessage label.
     */
    private void abnormalSituationValidations() {
        final String errorMsg1 = "w1: Insufficient production capacity to produce the optimal mix, please reduce or adjust the capacity of\nlabor & grape volume!";
        final String errorMsg2 = "w2: Insufficient labor supplied to utilize the grape resource (less than 90%)!";
        final String errorMsg12 = errorMsg1 + "\n" + errorMsg2;
        if (!this.determineInsufficientProductionCapacityForOptimalMix() && !this.determineInsufficientLabourSupplyForSatisfiedGrapeResourceUtilization()) {
            // Do nothing
        } else if (this.determineInsufficientProductionCapacityForOptimalMix() && this.determineInsufficientLabourSupplyForSatisfiedGrapeResourceUtilization()) {
            this.FieldValidationErrorMessage.setText(errorMsg12);
        } else if (this.determineInsufficientProductionCapacityForOptimalMix()) {
            this.FieldValidationErrorMessage.setText(errorMsg1);
        } else {
            this.FieldValidationErrorMessage.setText(errorMsg2);
        }
    }

    /**
     * Determines if there is insufficient production capacity to produce the optimal mix of wines.
     * @return {@code true} if there is insufficient production capacity, {@code false} otherwise.
     */
    private boolean determineInsufficientProductionCapacityForOptimalMix() {
        OptimalSalesRevenueModel optimalSalesRevenue = this.weeklyReviewByRevenueModel.calculateOptimalProductionValue();
        return this.weeklyReviewByRevenueModel.wineProductionCapacityOverloadedOnGrape(optimalSalesRevenue.getOptimalLitresOfRose(), optimalSalesRevenue.getOptimalLitresOfNoir());
    }

    /**
     * Determines if there is insufficient labor supply to utilize the grape resource.
     * @return return {@code true} if there is insufficient labor supply, {@code false} otherwise.
     */
    private boolean determineInsufficientLabourSupplyForSatisfiedGrapeResourceUtilization() {
        OptimalSalesRevenueModel optimalSalesRevenue = this.weeklyReviewByRevenueModel.calculateOptimalProductionValue();
        return this.weeklyReviewByRevenueModel.grapeResourceUtilizationIsInsufficientDueToInsufficientLabourSupplied(optimalSalesRevenue.getOptimalLitresOfRose(), optimalSalesRevenue.getOptimalLitresOfNoir(), this.weeklyReviewByRevenueModel.getCapGrape());
    }

    @SneakyThrows
    @Override
    /**
     * Initializes the controller with the appropriate listeners for each input field.
     * @param location the location of the FXML file
     * @param resources the resources for
     */
    public void initialize(URL location, ResourceBundle resources) {
        NumYearValue.textProperty().addListener((Observable, oldValue, newValue) -> this.syncNumYearValue());
        CapLaborValue.textProperty().addListener((Observable, oldValue, newValue) -> this.syncCapLabourValue());
        CapGrapeValue.textProperty().addListener((Observable, oldValue, newValue) -> this.syncCapGrapeValue());
        PrcRoseValue.textProperty().addListener((Observable, oldValue, newValue) -> this.syncPrcRoseValue());
        PrcNoirValue.textProperty().addListener((Observable, oldValue, newValue) -> this.syncPrcNoirValue());
    }
}
