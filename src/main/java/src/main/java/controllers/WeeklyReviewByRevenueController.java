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

public class WeeklyReviewByRevenueController implements Initializable {
    final Logger logger = LoggerFactory.getLogger(getClass());
    private final WeeklyReviewByRevenueModel weeklyReviewByRevenueModel;
    private int[] fieldID;
    @FXML
    private TextField NumYearValue;
    @FXML
    private TextField CapLaborValue;
    @FXML
    private TextField CapGrapeValue;
    @FXML
    private TextField PrcRoseValue;
    @FXML
    private TextField PrcNoirValue;
    @FXML
    private Label DisplayOptRoseValue;
    @FXML
    private Label DisplayOptNoirValue;
    @FXML
    private Label DisplayTotalValue;
    @FXML
    private Label DisplayTotalRevenueValue;
    @FXML
    private Label DisplayLabourSurplusValue;
    @FXML
    private Label DisplayGrapeSurplusValue;
    @FXML
    private Label FieldValidationCheckDetails;
    @FXML
    private Label FieldValidationErrorMessage;

    public WeeklyReviewByRevenueController() {
        weeklyReviewByRevenueModel = new WeeklyReviewByRevenueModel();
    }

    private void syncNumYearValue() {
        String input = this.NumYearValue.getText();
        if (weeklyReviewByRevenueModel.isEmptyField(input)) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            weeklyReviewByRevenueModel.setWeekOfYear(Integer.MIN_VALUE);
            logger.info("NumYear change to null");
            return;
        }
        // numeric validation amd weekOfYear range validation 2301 - 2315
        boolean validInput = weeklyReviewByRevenueModel.isValidWeekOfYear(input);
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
        weeklyReviewByRevenueModel.setWeekOfYear(Integer.parseInt(input));
        logger.info("NumYear change to {}", input);
    }

    private void syncCapLabourValue() {
        String input = this.CapLaborValue.getText();
        if (weeklyReviewByRevenueModel.isEmptyField(input)) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            weeklyReviewByRevenueModel.setCapLabour(Integer.MIN_VALUE);
            logger.info("CapLabor change to null");
            return;
        }
        // numeric validation
        boolean validInput = weeklyReviewByRevenueModel.isInteger(input);
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
        weeklyReviewByRevenueModel.setCapLabour(Integer.parseInt(input));
        logger.info("CapLabor change to {}", input);
    }

    public void syncCapGrapeValue() {
        String input = this.CapGrapeValue.getText();
        if (weeklyReviewByRevenueModel.isEmptyField(input)) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            weeklyReviewByRevenueModel.setCapGrape(Integer.MIN_VALUE);
            logger.info("CapGrape change to null");
            return;
        }
        // numeric validation
        boolean validInput = weeklyReviewByRevenueModel.isInteger(input);
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
        weeklyReviewByRevenueModel.setCapGrape(Integer.parseInt(input));
        logger.info("CapGrape change to {}", input);
    }

    private void syncPrcRoseValue() {
        String input = this.PrcRoseValue.getText();
        if (weeklyReviewByRevenueModel.isEmptyField(input)) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            weeklyReviewByRevenueModel.setPrcRose(Float.MIN_VALUE);
            logger.info("PrcRose change to null");
            return;
        }
        // numeric validation
        boolean validInput = weeklyReviewByRevenueModel.isNumeric(input);
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
        weeklyReviewByRevenueModel.setPrcRose(Float.parseFloat(input));
        logger.info("PrcRose change to {}", input);
    }

    private void syncPrcNoirValue() {
        String input = this.PrcNoirValue.getText();
        if (weeklyReviewByRevenueModel.isEmptyField(input)) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            weeklyReviewByRevenueModel.setPrcNoir(Float.MIN_VALUE);
            logger.info("PrcNoir change to null");
            return;
        }
        // numeric validation
        boolean validInput = weeklyReviewByRevenueModel.isNumeric(input);
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
        weeklyReviewByRevenueModel.setPrcNoir(Float.parseFloat(input));
        logger.info("PrcNoir change to {}", input);
    }

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
            case 1 ->
                    this.FieldValidationCheckDetails.setText(valid ? "2301 <= WeekOfYear <= 2315" : "WeekOfYear should be in range of 2301 to 2315");
            case 2 ->
                    this.FieldValidationCheckDetails.setText(valid ? "Cap_Labour is Integer" : "Cap_Labour is not Numeric or Integer");
            case 3 ->
                    this.FieldValidationCheckDetails.setText(valid ? "Cap_Grape is Integer" : "Cap_Grape is not Numeric or Integer");
            case 4 ->
                    this.FieldValidationCheckDetails.setText(valid ? "Prc_Rose is Numeric" : "Prc_Rose is not Numeric");
            case 5 ->
                    this.FieldValidationCheckDetails.setText(valid ? "Prc_Noir is Numeric" : "Prc_Noir is not Numeric");
            default -> this.FieldValidationCheckDetails.setText("");
        }
    }

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

    private void releaseAllFields() {
        this.fieldID = new int[5];
        this.NumYearValue.setEditable(true);
        this.CapLaborValue.setEditable(true);
        this.CapGrapeValue.setEditable(true);
        this.PrcRoseValue.setEditable(true);
        this.PrcNoirValue.setEditable(true);
    }

    private boolean checkAllFieldsFilled() {
        return !(this.weeklyReviewByRevenueModel.getWeekOfYear() == Integer.MIN_VALUE ||
                this.weeklyReviewByRevenueModel.getCapGrape() == Integer.MIN_VALUE ||
                this.weeklyReviewByRevenueModel.getCapLabour() == Integer.MIN_VALUE ||
                this.weeklyReviewByRevenueModel.getPrcRose() == Float.MIN_VALUE ||
                this.weeklyReviewByRevenueModel.getPrcNoir() == Float.MIN_VALUE);
    }

    public void runCalculation() {
        logger.info("Input fields NumYear={}, CapLabour={}, CapGrape={}, PrcRose={}, PrcNoir={}",
                this.weeklyReviewByRevenueModel.getWeekOfYear(),
                this.weeklyReviewByRevenueModel.getCapLabour(),
                this.weeklyReviewByRevenueModel.getCapGrape(),
                this.weeklyReviewByRevenueModel.getPrcRose(),
                this.weeklyReviewByRevenueModel.getPrcNoir()
        );

        if (!this.checkAllFieldsFilled()) {
            this.FieldValidationErrorMessage.setText("Required fields are not filled yet!");
            logger.error("Fields are not fully filled yet!");
            return;
        }
        this.FieldValidationCheckDetails.setText("");

        // Calculate optimal Sales Revenue and display them on the right of the panel
        this.calculateAndDisplayOptimalSalesRevenue();

        // Validate data and pop error message if needed
        this.abnormalSituationValidations();
    }

    public void returnToMain() throws IOException {
        Main.stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/frontend/applicationMainMenu.fxml")))));
    }

    private void calculateAndDisplayOptimalSalesRevenue() {
        OptimalSalesRevenueModel optimalSalesRevenue = this.weeklyReviewByRevenueModel.calculateOptimalProductionValue();
        this.DisplayOptRoseValue.setText(String.format(this.weeklyReviewByRevenueModel.addFormatToInteger(optimalSalesRevenue.getOptimalLitresOfRose())));
        this.DisplayOptNoirValue.setText(String.format(this.weeklyReviewByRevenueModel.addFormatToInteger(optimalSalesRevenue.getOptimalLitresOfNoir())));
        this.DisplayTotalValue.setText(this.weeklyReviewByRevenueModel.addFormatToInteger(optimalSalesRevenue.getOptimalLitresOfRose() + optimalSalesRevenue.getOptimalLitresOfNoir()));
        this.DisplayTotalRevenueValue.setText(this.weeklyReviewByRevenueModel.addFormatToInteger(optimalSalesRevenue.getOptimalSalesRevenue()));
        int[] labourAndGrapeSurplus = this.weeklyReviewByRevenueModel.calculateLabourAndGrapeSurplus(optimalSalesRevenue.getOptimalLitresOfRose(), optimalSalesRevenue.getOptimalLitresOfNoir());
        // ii.f: If Sur_Labor > 0 but not enough to produce a bottle of any type of wines, set it to zero.
        this.DisplayLabourSurplusValue.setText(labourAndGrapeSurplus[0] > 1 && labourAndGrapeSurplus[0] < 6 && labourAndGrapeSurplus[0] < 4 ? "0" : Integer.toString(labourAndGrapeSurplus[0]));
        // ii.g: If Sur_Grape > 0 but not enough to produce a bottle of any type of wines, set it to zero.
        this.DisplayGrapeSurplusValue.setText(labourAndGrapeSurplus[1] > 1 && labourAndGrapeSurplus[1] < 12 && labourAndGrapeSurplus[1] < 5 ? "0" : Integer.toString(labourAndGrapeSurplus[1]));
    }

    // Field Validations
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

    private boolean determineInsufficientProductionCapacityForOptimalMix() {
        OptimalSalesRevenueModel optimalSalesRevenue = this.weeklyReviewByRevenueModel.calculateOptimalProductionValue();
        return this.weeklyReviewByRevenueModel.wineProductionCapacityOverloadedOnGrape(optimalSalesRevenue.getOptimalLitresOfRose(), optimalSalesRevenue.getOptimalLitresOfNoir());
    }

    private boolean determineInsufficientLabourSupplyForSatisfiedGrapeResourceUtilization() {
        OptimalSalesRevenueModel optimalSalesRevenue = this.weeklyReviewByRevenueModel.calculateOptimalProductionValue();
        return this.weeklyReviewByRevenueModel.grapeResourceUtilizationIsInsufficientDueToInsufficientLabourSupplied(optimalSalesRevenue.getOptimalLitresOfRose(), optimalSalesRevenue.getOptimalLitresOfNoir(), this.weeklyReviewByRevenueModel.getCapGrape());
    }

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NumYearValue.textProperty().addListener((Observable, oldValue, newValue) -> this.syncNumYearValue());
        CapLaborValue.textProperty().addListener((Observable, oldValue, newValue) -> this.syncCapLabourValue());
        CapGrapeValue.textProperty().addListener((Observable, oldValue, newValue) -> this.syncCapGrapeValue());
        PrcRoseValue.textProperty().addListener((Observable, oldValue, newValue) -> this.syncPrcRoseValue());
        PrcNoirValue.textProperty().addListener((Observable, oldValue, newValue) -> this.syncPrcNoirValue());
    }
}
