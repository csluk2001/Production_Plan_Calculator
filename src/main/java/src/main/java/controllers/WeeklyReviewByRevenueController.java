package src.main.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.main.java.Main;
import src.main.java.models.OptimalSalesRevenueModel;
import src.main.java.models.WeeklyReviewByRevenueModel;

import java.io.IOException;
import java.util.Objects;

public class WeeklyReviewByRevenueController {
    final Logger logger = LoggerFactory.getLogger(getClass());
    private final WeeklyReviewByRevenueModel weeklyReviewByRevenueModel;
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
    private Label FieldValidationDetails;
    @FXML
    private Label FieldValidationErrorMessage;

    public WeeklyReviewByRevenueController() {
        // public WeeklyReviewByRevenueModel(int numWeek, int capLabour, int capGrape, int prcRose, int prcNoir)
        weeklyReviewByRevenueModel = new WeeklyReviewByRevenueModel();
    }

    public void syncNumYearValue() throws InterruptedException {
        weeklyReviewByRevenueModel.setNumWeek(Integer.parseInt(this.NumYearValue.getText()));
        this.syncOptimalProductionValue();
        logger.info("NumYear change to {}", this.NumYearValue.getText());
    }

    public void syncCapLabourValue() throws InterruptedException {
        weeklyReviewByRevenueModel.setCapLabour(Integer.parseInt(this.CapLaborValue.getText()));
        this.syncOptimalProductionValue();
        logger.info("NumYear change to {}", this.CapLaborValue.getText());
    }

    public void syncCapGrapeValue() throws InterruptedException {
        weeklyReviewByRevenueModel.setCapGrape(Integer.parseInt(this.CapGrapeValue.getText()));
        this.syncOptimalProductionValue();
        logger.info("NumYear change to {}", this.CapGrapeValue.getText());
    }

    public void syncPrcRoseValue() throws InterruptedException {
        weeklyReviewByRevenueModel.setPrcRose(Float.parseFloat(this.PrcRoseValue.getText()));
        this.syncOptimalProductionValue();
        logger.info("NumYear change to {}", this.PrcRoseValue.getText());
    }

    public void syncPrcNoirValue() {
        weeklyReviewByRevenueModel.setPrcNoir(Float.parseFloat(this.PrcNoirValue.getText()));
        this.syncOptimalProductionValue();
        logger.info("NumYear change to {}", this.PrcNoirValue.getText());
    }

    public void syncOptimalProductionValue() {
        if (!this.checkAllFieldsFilled()) {
            logger.info("Fields are not filled yet");
            return;
        }
        this.calculateAndDisplayOptimalSalesRevenue();
    }

    private boolean checkAllFieldsFilled() {
        return !(this.weeklyReviewByRevenueModel.getNumWeek() == 0 ||
                this.weeklyReviewByRevenueModel.getCapGrape() == 0 ||
                this.weeklyReviewByRevenueModel.getCapLabour() == 0 ||
                this.weeklyReviewByRevenueModel.getPrcRose() == 0.0f ||
                this.weeklyReviewByRevenueModel.getPrcNoir() == 0.0f);
    }

    public void runCalculation() {
        // Store input field to model
        this.weeklyReviewByRevenueModel.setNumWeek(Integer.parseInt(this.NumYearValue.getText()));
        this.weeklyReviewByRevenueModel.setCapLabour(Integer.parseInt(this.CapLaborValue.getText()));
        this.weeklyReviewByRevenueModel.setCapGrape(Integer.parseInt(this.CapGrapeValue.getText()));
        this.weeklyReviewByRevenueModel.setPrcRose(Float.parseFloat(this.PrcRoseValue.getText()));
        this.weeklyReviewByRevenueModel.setPrcNoir(Float.parseFloat(this.PrcNoirValue.getText()));
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
        this.DisplayLabourSurplusValue.setText(labourAndGrapeSurplus[0] > 1 && labourAndGrapeSurplus[0] < 6 && labourAndGrapeSurplus[0] < 4 ? "0" : Integer.toString(labourAndGrapeSurplus[0]));
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

    /*
        @SneakyThrows
        @Override
        public void initialize(URL location, ResourceBundle resources) {
            NumYearValue.textProperty().addListener((Observable, oldValue, newValue) -> {
                try {
                    this.syncNumYearValue();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            CapLaborValue.textProperty().addListener((Observable, oldValue, newValue) -> {
                try {
                    this.syncCapLabourValue();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            CapGrapeValue.textProperty().addListener((Observable, oldValue, newValue) -> {
                try {
                    this.syncCapGrapeValue();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            PrcRoseValue.textProperty().addListener((Observable, oldValue, newValue) -> {
                try {
                    this.syncPrcRoseValue();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            PrcNoirValue.textProperty().addListener((Observable, oldValue, newValue) -> {
                try {
                    this.syncPrcNoirValue();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
     */
}
