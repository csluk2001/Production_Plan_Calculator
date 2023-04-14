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
        // public WeeklyReviewByRevenueModel(int numWeek, int capLabour, int capGrape, int prcRose, int prcNoir)
        weeklyReviewByRevenueModel = new WeeklyReviewByRevenueModel();
    }

    private void syncNumYearValue() {
        // numeric validation
        boolean validInput = weeklyReviewByRevenueModel.isNumeric(this.NumYearValue.getText());
        // store value into data model
        weeklyReviewByRevenueModel.setNumWeek(Integer.parseInt(this.NumYearValue.getText()));
        // numWeek range validation 2301 - 2312
        validInput &= weeklyReviewByRevenueModel.isValidNumWeek();
        // set validation message
        this.syncFieldValidationCheckDetails(1, validInput);
        if (!validInput) {
            this.fieldID = new int[]{2, 3, 4, 5};
            this.lockOtherFields();
            logger.error("{}, wrong!!!!", this.NumYearValue.getText());
            return;
        }
        logger.info("correct!");
        this.releaseAllFields();
        logger.info("NumYear change to {}", this.NumYearValue.getText());
    }

    private void syncCapLabourValue() {
        // numeric validation
        boolean validInput = weeklyReviewByRevenueModel.isNumeric(this.CapLaborValue.getText());
        // set validation message
        this.syncFieldValidationCheckDetails(2, validInput);
        if (!validInput) {
            this.fieldID = new int[]{1, 3, 4, 5};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        weeklyReviewByRevenueModel.setCapLabour(Integer.parseInt(this.CapLaborValue.getText()));
        logger.info("NumYear change to {}", this.CapLaborValue.getText());
    }

    public void syncCapGrapeValue() {
        // numeric validation
        boolean validInput = weeklyReviewByRevenueModel.isNumeric(this.CapGrapeValue.getText());
        // set validation message
        this.syncFieldValidationCheckDetails(3, validInput);
        if (!validInput) {
            this.fieldID = new int[]{1, 2, 4, 5};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        weeklyReviewByRevenueModel.setCapGrape(Integer.parseInt(this.CapGrapeValue.getText()));
        logger.info("NumYear change to {}", this.CapGrapeValue.getText());
    }

    private void syncPrcRoseValue() {
        // numeric validation
        boolean validInput = weeklyReviewByRevenueModel.isNumeric(this.PrcRoseValue.getText());
        // set validation message
        this.syncFieldValidationCheckDetails(4, validInput);
        if (!validInput) {
            this.fieldID = new int[]{1, 2, 3, 5};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        weeklyReviewByRevenueModel.setPrcRose(Float.parseFloat(this.PrcRoseValue.getText()));
        logger.info("NumYear change to {}", this.PrcRoseValue.getText());
    }

    private void syncPrcNoirValue() {
        // numeric validation
        boolean validInput = weeklyReviewByRevenueModel.isNumeric(this.PrcNoirValue.getText());
        // validation message
        this.syncFieldValidationCheckDetails(5, validInput);
        if (!validInput) {
            this.fieldID = new int[]{1, 2, 3, 4};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        weeklyReviewByRevenueModel.setPrcNoir(Float.parseFloat(this.PrcNoirValue.getText()));
        logger.info("NumYear change to {}", this.PrcNoirValue.getText());
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
                    this.FieldValidationCheckDetails.setText(valid ? "2301 <= WeekOfYear <= 2312" : "WeekOfYear should be in range of 2301 to 2312");
            case 2 ->
                    this.FieldValidationCheckDetails.setText(valid ? "Cap_Labour is Numeric" : "Cap_Labour is not Numeric");
            case 3 ->
                    this.FieldValidationCheckDetails.setText(valid ? "Cap_Grape is Numeric" : "Cap_Grape is not Numeric");
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
        return !(this.weeklyReviewByRevenueModel.getNumWeek() == 0 ||
                this.weeklyReviewByRevenueModel.getCapGrape() == 0 ||
                this.weeklyReviewByRevenueModel.getCapLabour() == 0 ||
                this.weeklyReviewByRevenueModel.getPrcRose() == 0.0f ||
                this.weeklyReviewByRevenueModel.getPrcNoir() == 0.0f);
    }

    public void runCalculation() {
        logger.info("Input fields NumYear={}, CapLabour={}, CapGrape={}, PrcRose={}, PrcNoir={}",
                this.weeklyReviewByRevenueModel.getNumWeek(),
                this.weeklyReviewByRevenueModel.getCapLabour(),
                this.weeklyReviewByRevenueModel.getCapGrape(),
                this.weeklyReviewByRevenueModel.getPrcRose(),
                this.weeklyReviewByRevenueModel.getPrcNoir()
        );

        if (!this.checkAllFieldsFilled()) {
            logger.error("Fields are not fully filled yet!");
            return;
        }

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
        this.DisplayOptRoseValue.setText(Integer.toString(optimalSalesRevenue.getOptimalLitresOfRose()));
        this.DisplayOptNoirValue.setText(Integer.toString(optimalSalesRevenue.getOptimalLitresOfNoir()));
        this.DisplayTotalValue.setText(Integer.toString(optimalSalesRevenue.getOptimalLitresOfRose() + optimalSalesRevenue.getOptimalLitresOfNoir()));
        this.DisplayTotalRevenueValue.setText(Float.toString(optimalSalesRevenue.getOptimalSalesRevenue()));
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
