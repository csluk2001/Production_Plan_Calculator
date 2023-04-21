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

public class WeeklyReviewByRevenueAndBackordersController implements Initializable{
    final Logger logger = LoggerFactory.getLogger(getClass());
    private final WeeklyReviewByRevenueAndBackordersModel weeklyReviewByRevenueAndBackordersModel;
    private int[] fieldID;
    @FXML
    private TextField BkoNoirValueFunC;
    @FXML
    private TextField BkoRoseValueFunC;
    @FXML
    private TextField CapGrapeValueFunC;
    @FXML
    private TextField CapLaborValueFunC;
    @FXML
    private Label DisplayBackorderFulfilmentMeaningFunC;
    @FXML
    private Label DisplayBackorderFulfilmentValueFunC;
    @FXML
    private Label DisplayOptNoirValueFunC;
    @FXML
    private Label DisplayOptRoseValueFunC;
    @FXML
    private Label DisplayTotalRevenueValueFunC;
    @FXML
    private Label DisplayTotalValueFunC;
    @FXML
    private Label FieldValidationCheckDetailsFunC;
    @FXML
    private Label FieldValidationErrorMessageFunC;
    @FXML
    private TextField NumYearValueFunC;
    @FXML
    private TextField PrcNoirValueFunC;
    @FXML
    private TextField PrcRoseValueFunC;

    public WeeklyReviewByRevenueAndBackordersController() {
        weeklyReviewByRevenueAndBackordersModel = new WeeklyReviewByRevenueAndBackordersModel();
    }

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

    private boolean checkAllFieldsFilledFunC() {
        return !(this.weeklyReviewByRevenueAndBackordersModel.getWeekOfYear() == Integer.MIN_VALUE ||
                this.weeklyReviewByRevenueAndBackordersModel.getCapGrape() == Integer.MIN_VALUE ||
                this.weeklyReviewByRevenueAndBackordersModel.getCapLabour() == Integer.MIN_VALUE ||
                this.weeklyReviewByRevenueAndBackordersModel.getPrcRose() == Float.MIN_VALUE ||
                this.weeklyReviewByRevenueAndBackordersModel.getPrcNoir() == Float.MIN_VALUE ||
                this.weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeRose() == Integer.MIN_VALUE ||
                this.weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeNoir() == Integer.MIN_VALUE);
    }

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
                    this.FieldValidationCheckDetailsFunC.setText(valid ? "Cap_Labour is Numeric" : "Cap_Labour is not Numeric or integer");
                    break;
            case 3 :
                    this.FieldValidationCheckDetailsFunC.setText(valid ? "Cap_Grape is Numeric" : "Cap_Grape is not Numeric or integer");
                    break;
            case 4 :
                    this.FieldValidationCheckDetailsFunC.setText(valid ? "Prc_Rose is Numeric" : "Prc_Rose is not Numeric");
                    break;
            case 5 :
                    this.FieldValidationCheckDetailsFunC.setText(valid ? "Prc_Noir is Numeric" : "Prc_Noir is not Numeric");
                    break;
            case 6 :
                    this.FieldValidationCheckDetailsFunC.setText(valid ? "Bko_Rose is Numeric" : "Bko_Rose is not Numeric or Integer");
                    break;
            case 7 :
                    this.FieldValidationCheckDetailsFunC.setText(valid ? "Bko_Noir is Numeric" : "Bko_Noir is not Numeric or Integer");
                    break;
            default : this.FieldValidationCheckDetailsFunC.setText("");
        }
    }

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
        boolean validInput = weeklyReviewByRevenueAndBackordersModel.isInteger(this.CapLaborValueFunC.getText());
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
        boolean validInput = weeklyReviewByRevenueAndBackordersModel.isInteger(this.CapGrapeValueFunC.getText());
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
        boolean validInput = weeklyReviewByRevenueAndBackordersModel.isNumeric(this.PrcRoseValueFunC.getText());
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
        boolean validInput = weeklyReviewByRevenueAndBackordersModel.isNumeric(this.PrcNoirValueFunC.getText());
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
        boolean validInput = weeklyReviewByRevenueAndBackordersModel.isInteger(this.BkoRoseValueFunC.getText());
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

    public void setValueAgain(){
        this.weeklyReviewByRevenueAndBackordersModel.setWeekOfYear(Integer.parseInt(this.NumYearValueFunC.getText()));
        this.weeklyReviewByRevenueAndBackordersModel.setCapLabour(Integer.parseInt(this.CapLaborValueFunC.getText()));
        this.weeklyReviewByRevenueAndBackordersModel.setCapGrape(Integer.parseInt(this.CapGrapeValueFunC.getText()));
        this.weeklyReviewByRevenueAndBackordersModel.setPrcRose(Float.parseFloat(this.PrcRoseValueFunC.getText()));
        this.weeklyReviewByRevenueAndBackordersModel.setPrcNoir(Float.parseFloat(this.PrcNoirValueFunC.getText()));
        this.weeklyReviewByRevenueAndBackordersModel.setBackorderVolumeRose(Integer.parseInt(this.BkoRoseValueFunC.getText()));
        this.weeklyReviewByRevenueAndBackordersModel.setBackorderVolumeNoir(Integer.parseInt(this.BkoNoirValueFunC.getText()));
    }
    @FXML
    void returnToMainFunC() throws IOException {
         Main.stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/frontend/applicationMainMenu.fxml")))));
    }

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
