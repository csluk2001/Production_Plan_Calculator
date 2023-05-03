package src.main.java.controllers;

import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Objects;
import java.io.IOException;
import java.util.ResourceBundle;


import src.main.java.Main;
import src.main.java.models.OptimalSalesRevenueModel;
import src.main.java.models.WeeklyReviewByRevenueAndBackordersModel;

/**
 * This class is a Controller for PPC1 Function_C GUI /resource/frontend/weeklyReviewByRevenueBackorders.fxml.
 * It handles the logic of the UI components and communicates with the model to update the data.
 * @author YIU, Ka Ho Alex GitHub id: AcupOfHappiness
 * @version Java15
 */

public class WeeklyReviewByRevenueAndBackordersController implements Initializable{

    /**
     * The logger for debugging.
     */
    final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * The "WeeklyReviewByRevenueAndBackordersModel" data model for this controller.
     */
    private final WeeklyReviewByRevenueAndBackordersModel weeklyReviewByRevenueAndBackordersModel;

    /**
     * The IDs of the fields that should be locked while one field is being edited.
     */
    private int[] fieldID;
    @FXML

    /**
     * The input field for the backorder volume of Rosé in litre.
     */
    private TextField BkoNoirValueFunC;

    /**
     * The input field for the Backorder volume of P-Noir in litre.
     */
    @FXML
    private TextField BkoRoseValueFunC;

    /**
     * The input field for the grape capacity.
     */
    @FXML
    private TextField CapGrapeValueFunC;

    /**
     * The input label for the optimal noir wine production value.
     */
    @FXML
    private TextField CapLaborValueFunC;

    /**
     * The label for displaying the result explanation of Backorder Fulfilment value.
     */
    @FXML
    private Label DisplayBackorderFulfilmentMeaningFunC;

    /**
     * The label for displaying the result of the Backorder Fulfilment value (yes/no).
     */
    @FXML
    private Label DisplayBackorderFulfilmentValueFunC;

    /**
     * The label for displaying the optimal noir wine production value.
     */
    @FXML
    private Label DisplayOptNoirValueFunC;

    /**
     * The label for displaying the optimal rose wine production value.
     */
    @FXML
    private Label DisplayOptRoseValueFunC;

    /**
     * The label for displaying the total revenue for optimal production of wine.
     */
    @FXML
    private Label DisplayTotalRevenueValueFunC;

    /**
     * The label for displaying the total mixed of both optimal production of wine.
     */
    @FXML
    private Label DisplayTotalValueFunC;

    /**
     * The label for displaying field validation check details.
     */
    @FXML
    private Label FieldValidationCheckDetailsFunC;

    /**
     * The label for displaying field validation error messages.
     */
    @FXML
    private Label FieldValidationErrorMessageFunC;

    /**
     * The input field for the WeekOfYear.
     */
    @FXML
    private TextField NumYearValueFunC;

    /**
     * The input field for the price of the noir wine.
     */
    @FXML
    private TextField PrcNoirValueFunC;

    /**
     * The input field for the price of the rose wine.
     */
    @FXML
    private TextField PrcRoseValueFunC;

    /**
     * Constructor for the controller. Initializes the data model.
     */
    public WeeklyReviewByRevenueAndBackordersController() {
        weeklyReviewByRevenueAndBackordersModel = new WeeklyReviewByRevenueAndBackordersModel();
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
     * Checks if all the required fields are filled.
     * @return {@code true} if all required fields are filled, {@code false} otherwise.
     */
    private boolean checkAllFieldsFilledFunC() {
        return !(this.weeklyReviewByRevenueAndBackordersModel.getWeekOfYear() == Integer.MIN_VALUE ||
                this.weeklyReviewByRevenueAndBackordersModel.getCapGrape() == Integer.MIN_VALUE ||
                this.weeklyReviewByRevenueAndBackordersModel.getCapLabour() == Integer.MIN_VALUE ||
                this.weeklyReviewByRevenueAndBackordersModel.getPrcRose() == Float.MIN_VALUE ||
                this.weeklyReviewByRevenueAndBackordersModel.getPrcNoir() == Float.MIN_VALUE ||
                this.weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeRose() == Integer.MIN_VALUE ||
                this.weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeNoir() == Integer.MIN_VALUE);
    }

    /**
     * Updates the FieldValidationCheckDetailsFunC label with the appropriate message based on the inputFieldID and valid flag.
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
                6) Bko_Rose
                7) Bko_Noir
         */
        switch (inputFieldID) {
            case 1 :
                    this.FieldValidationCheckDetailsFunC.setText(valid ? "2301 <= WeekOfYear <= 2315" : "WeekOfYear should be in range of 2301 to 2315");
                    break;
            case 2 :
                    this.FieldValidationCheckDetailsFunC.setText(valid ? "Cap_Labour is Numeric" : "Cap_Labour is not Numeric or Integer >= 1");
                    break;
            case 3 :
                    this.FieldValidationCheckDetailsFunC.setText(valid ? "Cap_Grape is Numeric" : "Cap_Grape is not Numeric or Integer >= 1");
                    break;
            case 4 :
                    this.FieldValidationCheckDetailsFunC.setText(valid ? "Prc_Rose is Numeric" : "Prc_Rose is not Numeric or Integer >= 1");
                    break;
            case 5 :
                    this.FieldValidationCheckDetailsFunC.setText(valid ? "Prc_Noir is Numeric" : "Prc_Noir is not Numeric or Integer >= 1");
                    break;
            case 6 :
                    this.FieldValidationCheckDetailsFunC.setText(valid ? "Bko_Rose is Numeric" : "Bko_Rose is not Numeric or Integer >= 0");
                    break;
            case 7 :
                    this.FieldValidationCheckDetailsFunC.setText(valid ? "Bko_Noir is Numeric" : "Bko_Noir is not Numeric or Integer >= 0");
                    break;
            default : this.FieldValidationCheckDetailsFunC.setText("");
        }
    }

    /**
     * Enables the editing capabilities of all the fields and resets the fieldID array.
     */
    private void releaseAllFields() {
        this.fieldID = new int[7];
        this.NumYearValueFunC.setEditable(true);
        this.CapLaborValueFunC.setEditable(true);
        this.CapGrapeValueFunC.setEditable(true);
        this.PrcRoseValueFunC.setEditable(true);
        this.PrcNoirValueFunC.setEditable(true);
        this.BkoRoseValueFunC.setEditable(true);
        this.BkoNoirValueFunC.setEditable(true);
    }

    /**
     * Disables the editing capabilities of all the fields except for FieldID 0.
     */
    private void lockOtherFields() {
        for (int id : this.fieldID) {
            switch (id) {
                case 1 -> this.NumYearValueFunC.setEditable(false);
                case 2 -> this.CapLaborValueFunC.setEditable(false);
                case 3 -> this.CapGrapeValueFunC.setEditable(false);
                case 4 -> this.PrcRoseValueFunC.setEditable(false);
                case 5 -> this.PrcNoirValueFunC.setEditable(false);
                case 6 -> this.BkoRoseValueFunC.setEditable(false);
                case 7 -> this.BkoNoirValueFunC.setEditable(false);
            }
        }
    }

    /**
     * This method synchronizes the value of the NumYearValueFunC text field with
     * the weeklyReviewByRevenueAndBackordersModel's weekOfYear property.
     */
    private void syncNumYearValue() {
        if (weeklyReviewByRevenueAndBackordersModel.isEmptyField(this.NumYearValueFunC.getText())) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            weeklyReviewByRevenueAndBackordersModel.setWeekOfYear(Integer.MIN_VALUE);
            logger.info("NumYear change to null");
            return;
        }
        // set validation message
        boolean validInput = weeklyReviewByRevenueAndBackordersModel.isValidWeekOfYear(this.NumYearValueFunC.getText());
        this.syncFieldValidationCheckDetails(1, validInput);
        if (!validInput) {
            weeklyReviewByRevenueAndBackordersModel.setWeekOfYear(Integer.MIN_VALUE);
            this.fieldID = new int[]{2, 3, 4, 5, 6, 7};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        weeklyReviewByRevenueAndBackordersModel.setWeekOfYear(Integer.parseInt(this.NumYearValueFunC.getText()));
        logger.info("NumYear change to {}", this.NumYearValueFunC.getText());
    }

    /**
     * Synchronizes the value of CapLabourValueFunC with the data model, validates it and updates the field's validation
     * message label.
     */
    private void syncCapLabourValue() {
        // numeric validation
        if (weeklyReviewByRevenueAndBackordersModel.isEmptyField(this.CapLaborValueFunC.getText())) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            weeklyReviewByRevenueAndBackordersModel.setCapLabour(Integer.MIN_VALUE);
            logger.info("CapLabor change to null");
            return;
        }
        // numeric validation
        boolean validInput = weeklyReviewByRevenueAndBackordersModel.isInteger(this.CapLaborValueFunC.getText()) && weeklyReviewByRevenueAndBackordersModel.isGreaterThanOne(this.CapLaborValueFunC.getText());
        // set validation message
        this.syncFieldValidationCheckDetails(2, validInput);
        if (!validInput) {
            weeklyReviewByRevenueAndBackordersModel.setCapLabour(Integer.MIN_VALUE);
            this.fieldID = new int[]{1, 3, 4, 5, 6, 7};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        weeklyReviewByRevenueAndBackordersModel.setCapLabour(Integer.parseInt(this.CapLaborValueFunC.getText()));
        logger.info("CapLabor change to {}", this.CapLaborValueFunC.getText());
    }

    /**
     * Synchronizes the value of CapGrapeValueFunC with the data model, validates it and updates the field's validation
     * message label.
     */
    public void syncCapGrapeValue() {
        if (weeklyReviewByRevenueAndBackordersModel.isEmptyField(this.CapGrapeValueFunC.getText())) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            weeklyReviewByRevenueAndBackordersModel.setCapGrape(Integer.MIN_VALUE);
            logger.info("CapGrape change to null");
            return;
        }
        // numeric validation
        boolean validInput = weeklyReviewByRevenueAndBackordersModel.isInteger(this.CapGrapeValueFunC.getText()) && weeklyReviewByRevenueAndBackordersModel.isGreaterThanOne(this.CapGrapeValueFunC.getText());
        // set validation message
        this.syncFieldValidationCheckDetails(3, validInput);
        if (!validInput) {
            weeklyReviewByRevenueAndBackordersModel.setCapGrape(Integer.MIN_VALUE);
            this.fieldID = new int[]{1, 2, 4, 5, 6, 7};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        weeklyReviewByRevenueAndBackordersModel.setCapGrape(Integer.parseInt(this.CapGrapeValueFunC.getText()));
        logger.info("NumYear change to {}", this.CapGrapeValueFunC.getText());
    }

    /**
     * Synchronizes the value of PrcRoseValueFunC with the data model, validates it and updates the field's validation
     * message label.
     */
    private void syncPrcRoseValue() {
        if (weeklyReviewByRevenueAndBackordersModel.isEmptyField(this.PrcRoseValueFunC.getText())) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            weeklyReviewByRevenueAndBackordersModel.setPrcRose(Float.MIN_VALUE);
            logger.info("PrcRose change to null");
            return;
        }
        // numeric validation
        boolean validInput = weeklyReviewByRevenueAndBackordersModel.isNumeric(this.PrcRoseValueFunC.getText()) && weeklyReviewByRevenueAndBackordersModel.isGreaterThanOne(this.PrcRoseValueFunC.getText());
        // set validation message
        this.syncFieldValidationCheckDetails(4, validInput);
        if (!validInput) {
            weeklyReviewByRevenueAndBackordersModel.setPrcRose(Float.MIN_VALUE);
            this.fieldID = new int[]{1, 2, 3, 5, 6, 7};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        weeklyReviewByRevenueAndBackordersModel.setPrcRose(Float.parseFloat(this.PrcRoseValueFunC.getText()));
        logger.info("PrcRose change to {}", this.PrcRoseValueFunC.getText());
    }

    /**
     * Synchronizes the value of PrcNoirValueFunC with the data model, validates it and updates the field's validation
     * message label.
     */
    private void syncPrcNoirValue() {
        if (weeklyReviewByRevenueAndBackordersModel.isEmptyField(this.PrcNoirValueFunC.getText())) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            weeklyReviewByRevenueAndBackordersModel.setPrcNoir(Float.MIN_VALUE);
            logger.info("PrcNoir change to null");
            return;
        }
        // numeric validation
        boolean validInput = weeklyReviewByRevenueAndBackordersModel.isNumeric(this.PrcNoirValueFunC.getText()) && weeklyReviewByRevenueAndBackordersModel.isGreaterThanOne(this.PrcNoirValueFunC.getText());
        // validation message
        this.syncFieldValidationCheckDetails(5, validInput);
        if (!validInput) {
            weeklyReviewByRevenueAndBackordersModel.setPrcNoir(Float.MIN_VALUE);
            this.fieldID = new int[]{1, 2, 3, 4, 6, 7};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        weeklyReviewByRevenueAndBackordersModel.setPrcNoir(Float.parseFloat(this.PrcNoirValueFunC.getText()));
        logger.info("PrcNoir change to {}", this.PrcNoirValueFunC.getText());
    }

    /**
     * Synchronizes the value of BkoRoseValueFunC with the data model, validates it and updates the field's validation
     * message label.
     */
    private void syncBkoRoseValue() {
        if (weeklyReviewByRevenueAndBackordersModel.isEmptyField(this.BkoRoseValueFunC.getText())) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            weeklyReviewByRevenueAndBackordersModel.setBackorderVolumeRose(Integer.MIN_VALUE);
            logger.info("bkoRose change to null");
            return;
        }
        // numeric validation
        boolean validInput = weeklyReviewByRevenueAndBackordersModel.isInteger(this.BkoRoseValueFunC.getText()) && weeklyReviewByRevenueAndBackordersModel.isGreaterThanZero(this.BkoRoseValueFunC.getText());
        // validation message
        this.syncFieldValidationCheckDetails(6, validInput);
        if (!validInput) {
            weeklyReviewByRevenueAndBackordersModel.setBackorderVolumeRose(Integer.MIN_VALUE);
            this.fieldID = new int[]{1, 2, 3, 4, 5, 7};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        weeklyReviewByRevenueAndBackordersModel.setBackorderVolumeRose(Integer.parseInt(this.BkoRoseValueFunC.getText()));
        logger.info("bkoRose change to {}", this.BkoRoseValueFunC.getText());
    }

    /**
     * Synchronizes the value of BkoNoirValueFunC with the data model, validates it and updates the field's validation
     * message label.
     */
    private void syncBkoNoirValue() {
        if (weeklyReviewByRevenueAndBackordersModel.isEmptyField(this.BkoNoirValueFunC.getText())) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            weeklyReviewByRevenueAndBackordersModel.setBackorderVolumeNoir(Integer.MIN_VALUE);
            logger.info("bkoNoir change to null");
            return;
        }
        // numeric validation
        boolean validInput = weeklyReviewByRevenueAndBackordersModel.isInteger(this.BkoNoirValueFunC.getText());
        // validation message
        this.syncFieldValidationCheckDetails(7, validInput);
        if (!validInput) {
            weeklyReviewByRevenueAndBackordersModel.setBackorderVolumeNoir(Integer.MIN_VALUE);
            this.fieldID = new int[]{1, 2, 3, 4, 5, 6};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        weeklyReviewByRevenueAndBackordersModel.setBackorderVolumeNoir(Integer.parseInt(this.BkoNoirValueFunC.getText()));
        logger.info("bkoNoir change to {}", this.BkoNoirValueFunC.getText());
    }

    /**
     * Performs the abnormal situation validations and updates the FieldValidationErrorMessageFunA label accordingly.
     * The validations are:
     * w1: Insufficient production capacity to produce the optimal mix, please reduce or adjust the capacity of labor & grape volume!
     * w2: Insufficient labor supplied to utilize the grape resource (less than 90%)!
     * w3: According to company policy, ratio of backorder volume should not lower than 70% of the optimal production volume!
     * If both validations fail, it displays the concatenated error messages in the FieldValidationErrorMessageFunC label.
     */
    // Field Validations
    private void abnormalSituationValidationsFunC(int[] opt_litre) {
        final String errorMsg1 = "w1: Insufficient production capacity to produce the optimal mix, please reduce or adjust the capacity of\nlabor & grape volume!";
        final String errorMsg2 = "w2: Insufficient labor supplied to utilize the grape resource (less than 90%)!";
        final String errorMsg3 = "w3: According to company policy, ratio of backorder volume should not lower than 70% of the optimal\nproduction volume!";
        String errorMsgConcat = "";
        if (this.weeklyReviewByRevenueAndBackordersModel.getMAX_PRODUCTION_CAPACITY_OF_MANUFACTURING_FACILITIES() < opt_litre[0] + opt_litre[1]) {
            errorMsgConcat = errorMsg1;
        }
        if (opt_litre[2] * 0.9 > opt_litre[0] * this.weeklyReviewByRevenueAndBackordersModel.getROSE_CONSUMPTION_TO_MAKE_A_LITRE_OF_WINE_GRAPE() + opt_litre[1] * this.weeklyReviewByRevenueAndBackordersModel.getNOIR_CONSUMPTION_TO_MAKE_A_LITRE_OF_WINE_GRAPE()) {
            errorMsgConcat = errorMsgConcat == "" ? errorMsg2 : errorMsgConcat + "\n" + errorMsg2;
        }
        if (weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeRose() +
                weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeNoir()
            < 0.7*(opt_litre[0] + opt_litre[1])) {
            errorMsgConcat = errorMsgConcat == "" ? errorMsg3 : errorMsgConcat + "\n" + errorMsg3;
        }
        this.FieldValidationErrorMessageFunC.setText(errorMsgConcat);
    }

    /**
     * set the value again after clicking "run" button  for ensuring the model obtains the latest user input value.
     */
    public void setValueAgain(){
        this.weeklyReviewByRevenueAndBackordersModel.setWeekOfYear(Integer.parseInt(this.NumYearValueFunC.getText()));
        this.weeklyReviewByRevenueAndBackordersModel.setCapLabour(Integer.parseInt(this.CapLaborValueFunC.getText()));
        this.weeklyReviewByRevenueAndBackordersModel.setCapGrape(Integer.parseInt(this.CapGrapeValueFunC.getText()));
        this.weeklyReviewByRevenueAndBackordersModel.setPrcRose(Float.parseFloat(this.PrcRoseValueFunC.getText()));
        this.weeklyReviewByRevenueAndBackordersModel.setPrcNoir(Float.parseFloat(this.PrcNoirValueFunC.getText()));
        this.weeklyReviewByRevenueAndBackordersModel.setBackorderVolumeRose(Integer.parseInt(this.BkoRoseValueFunC.getText()));
        this.weeklyReviewByRevenueAndBackordersModel.setBackorderVolumeNoir(Integer.parseInt(this.BkoNoirValueFunC.getText()));
    }

    /**
     * Returns to the main menu by loading the main menu FXML file "/frontend/applicationMainMenu.fxml".
     * @throws IOException if there is an error loading the main menu FXML file
     */
    @FXML
    void returnToMainFunC() throws IOException {
         Main.stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/frontend/applicationMainMenu.fxml")))));
    }

    /**
     * Runs the calculation based on the input values and updates the appropriate UI elements.
     * If any required field is not filled, it displays an error message in the FieldValidationCheckDetailsFunC label
     * and returns without executing the calculation.
     */
    @FXML
    void runCalculationFunC(ActionEvent event) {
        logger.info("Input fields NumYear={}, CapLabour={}, CapGrape={}, PrcRose={}, PrcNoir={}, BkoRose={}, BkoNoir={}",
                this.weeklyReviewByRevenueAndBackordersModel.getWeekOfYear(),
                this.weeklyReviewByRevenueAndBackordersModel.getCapLabour(),
                this.weeklyReviewByRevenueAndBackordersModel.getCapGrape(),
                this.weeklyReviewByRevenueAndBackordersModel.getPrcRose(),
                this.weeklyReviewByRevenueAndBackordersModel.getPrcNoir(),
                this.weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeRose(),
                this.weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeNoir()
        );

        if (!this.checkAllFieldsFilledFunC()) {
            logger.error("Fields are not fully filled yet!");
            return;
        }
        this.FieldValidationCheckDetailsFunC.setText("");
        this.setValueAgain();

        // Calculate optimal Sales Revenue and display them on the right of the panel
        int [] opt_Litre = this.calculateAndDisplayOptimalSalesRevenueAndCheckBackordersProduction();

        // Validate data and pop error message if needed
        this.abnormalSituationValidationsFunC(opt_Litre);
    }

    /**
     * Calculates the optimal sales revenue based on the input values and displays the results in the appropriate UI elements.
     * @return optimal litre of rose and noir, and original CapGrape (The value of CapGrape before producing Backorder wine).
     */
    private int [] calculateAndDisplayOptimalSalesRevenueAndCheckBackordersProduction() {
        int[] remainingResourceAfterBackorders = this.weeklyReviewByRevenueAndBackordersModel.calculateLabourAndGrapeSurplus(this.weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeRose(),
                this.weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeNoir());
        int originalCapGrape = this.weeklyReviewByRevenueAndBackordersModel.getCapGrape();
        boolean enough = remainingResourceAfterBackorders[0] >= 0 && remainingResourceAfterBackorders[1] >= 0;
        if (enough) {
            this.weeklyReviewByRevenueAndBackordersModel.setCapLabour(remainingResourceAfterBackorders[0]);
            this.weeklyReviewByRevenueAndBackordersModel.setCapGrape(remainingResourceAfterBackorders[1]);
        }
        OptimalSalesRevenueModel optimalSalesRevenue = this.weeklyReviewByRevenueAndBackordersModel.calculateOptimalProductionValue();
        this.DisplayOptRoseValueFunC.setText(enough ? Integer.toString(optimalSalesRevenue.getOptimalLitresOfRose() + this.weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeRose()) : Integer.toString(optimalSalesRevenue.getOptimalLitresOfRose()));
        this.DisplayOptNoirValueFunC.setText(enough ? Integer.toString(optimalSalesRevenue.getOptimalLitresOfNoir() + this.weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeNoir()) : Integer.toString(optimalSalesRevenue.getOptimalLitresOfNoir()));
        this.DisplayTotalValueFunC.setText(enough ? Integer.toString(optimalSalesRevenue.getOptimalLitresOfRose() + optimalSalesRevenue.getOptimalLitresOfNoir() + this.weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeRose() + this.weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeNoir()) : Integer.toString(optimalSalesRevenue.getOptimalLitresOfRose() + optimalSalesRevenue.getOptimalLitresOfNoir()));
        this.DisplayTotalRevenueValueFunC.setText(enough ? this.convertToCommaSeparatedString(Math.round(optimalSalesRevenue.getOptimalSalesRevenueNonRoundOff() + this.weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeRose() * this.weeklyReviewByRevenueAndBackordersModel.getPrcRose() + this.weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeNoir() * this.weeklyReviewByRevenueAndBackordersModel.getPrcNoir())) : this.convertToCommaSeparatedString(Math.round(optimalSalesRevenue.getOptimalSalesRevenue())));
        this.DisplayBackorderFulfilmentValueFunC.setText(enough ? "Yes" : "No");
        this.DisplayBackorderFulfilmentMeaningFunC.setText(enough ? "Resourcue of labor and grape are sufficient\nto produce all backorders of Rosé + P.Noir" : "Resourcue of labor and grape are insufficient\nto produce all backorders of Rosé + P.Noir");
        return new int[]{
                enough ? optimalSalesRevenue.getOptimalLitresOfRose() + this.weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeRose() : optimalSalesRevenue.getOptimalLitresOfRose(),
                enough ? optimalSalesRevenue.getOptimalLitresOfNoir() + this.weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeNoir() : optimalSalesRevenue.getOptimalLitresOfNoir(),
                originalCapGrape
        };
    }

    /**
     * Initializes the controller with the appropriate listeners for each input field.
     * @param url the location of the FXML file
     * @param resourceBundle the resources for
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NumYearValueFunC.textProperty().addListener((Observable, oldValue, newValue) -> this.syncNumYearValue());
        CapLaborValueFunC.textProperty().addListener((Observable, oldValue, newValue) -> this.syncCapLabourValue());
        CapGrapeValueFunC.textProperty().addListener((Observable, oldValue, newValue) -> this.syncCapGrapeValue());
        PrcRoseValueFunC.textProperty().addListener((Observable, oldValue, newValue) -> this.syncPrcRoseValue());
        PrcNoirValueFunC.textProperty().addListener((Observable, oldValue, newValue) -> this.syncPrcNoirValue());
        BkoRoseValueFunC.textProperty().addListener((Observable, oldValue, newValue) -> this.syncBkoRoseValue());
        BkoNoirValueFunC.textProperty().addListener((Observable, oldValue, newValue) -> this.syncBkoNoirValue());
    }
}
