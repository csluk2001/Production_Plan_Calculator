package src.main.java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

;
import java.util.Objects;
import java.io.IOException;


import src.main.java.Main;
import src.main.java.models.OptimalSalesRevenueModel;
import src.main.java.models.WeeklyReviewByRevenueAndBackordersModel;

public class WeeklyReviewByRevenueAndBackordersController {
    final Logger logger = LoggerFactory.getLogger(getClass());
    private final WeeklyReviewByRevenueAndBackordersModel weeklyReviewByRevenueAndBackordersModel;
    @FXML
    private Label BkoNoirItemNameFunC;
    @FXML
    private Label BkoNoirUnitFunC;
    @FXML
    private TextField BkoNoirValueFunC;
    @FXML
    private Label BkoRoseItemNameFunC;
    @FXML
    private Label BkoRoseUnitFunC;
    @FXML
    private TextField BkoRoseValueFunC;
    @FXML
    private Label CapGrapeItemNameFunC;
    @FXML
    private Label CapGrapeUnitFunC;
    @FXML
    private TextField CapGrapeValueFunC;
    @FXML
    private TextField CapLaborValueFunC;
    @FXML
    private Label CapLabourItemNameFunC;
    @FXML
    private Label CapLabourUnitFunC;
    @FXML
    private Label DisplayBackOrderFulfilmentTitleFunC;
    @FXML
    private Label DisplayBackorderFulfilmentMeaningFunC;
    @FXML
    private Label DisplayBackorderFulfilmentValueFunC;
    @FXML
    private Label DisplayOptNoirLabelFunC;
    @FXML
    private Label DisplayOptNoirValueFunC;
    @FXML
    private Label DisplayOptRoseLabelFunC;
    @FXML
    private Label DisplayOptRoseValueFunC;
    @FXML
    private AnchorPane DisplayPortalFunC;
    @FXML
    private AnchorPane DisplayProductionVolumePaneFunC;
    @FXML
    private Label DisplayProductionVolumeTitleFunC;
    @FXML
    private Label DisplayTitleFunC;
    @FXML
    private Label DisplayTotalLabelFunC;
    @FXML
    private Label DisplayTotalRevenueTitleFunC;
    @FXML
    private Label DisplayTotalRevenueValueFunC;
    @FXML
    private Label DisplayTotalValueFunC;
    @FXML
    private Label FieldValidationCheckDetailsFunC;
    @FXML
    private Label FieldValidationErrorMessageFunC;
    @FXML
    private Label FieldValidationTitleFunC;
    @FXML
    private Label NumYearItemNameFunC;
    @FXML
    private Label NumYearUnitFunC;
    @FXML
    private TextField NumYearValueFunC;
    @FXML
    private Label PrcNoirItemNameFunC;
    @FXML
    private Label PrcNoirUnitFunC;
    @FXML
    private TextField PrcNoirValueFunC;
    @FXML
    private Label PrcRoseItemNameFunC;
    @FXML
    private Label PrcRoseUnitFunC;
    @FXML
    private TextField PrcRoseValueFunC;
    @FXML
    private AnchorPane WeeklyReviewToOptimizedRevenueAndBackordersPortal;
    @FXML
    private Label WeeklyReviewToOptimizedRevenueAndBackordersTitle;
    @FXML
    private Button button_exit_FunC;
    @FXML
    private Button button_run_FunC;

    public WeeklyReviewByRevenueAndBackordersController() {
        weeklyReviewByRevenueAndBackordersModel = new WeeklyReviewByRevenueAndBackordersModel();
    }

    private boolean checkAllFieldsFilledFunC() {
        return !(this.weeklyReviewByRevenueAndBackordersModel.getNumWeek() == 0 ||
                this.weeklyReviewByRevenueAndBackordersModel.getCapGrape() == 0 ||
                this.weeklyReviewByRevenueAndBackordersModel.getCapLabour() == 0 ||
                this.weeklyReviewByRevenueAndBackordersModel.getPrcRose() == 0.0f ||
                this.weeklyReviewByRevenueAndBackordersModel.getPrcNoir() == 0.0f ||
                this.weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeRose() == 0 ||
                this.weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeNoir() == 0);
    }

    @FXML
    void returnToMainFunC() throws IOException {
         Main.stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/frontend/applicationMainMenu.fxml")))));
    }

    @FXML
    void runCalculationFunC(ActionEvent event) {
        // Store input field to model
        this.weeklyReviewByRevenueAndBackordersModel.setNumWeek(Integer.parseInt(this.NumYearValueFunC.getText()));
        this.weeklyReviewByRevenueAndBackordersModel.setCapLabour(Integer.parseInt(this.CapLaborValueFunC.getText()));
        this.weeklyReviewByRevenueAndBackordersModel.setCapGrape(Integer.parseInt(this.CapGrapeValueFunC.getText()));
        this.weeklyReviewByRevenueAndBackordersModel.setPrcRose(Float.parseFloat(this.PrcRoseValueFunC.getText()));
        this.weeklyReviewByRevenueAndBackordersModel.setPrcNoir(Float.parseFloat(this.PrcNoirValueFunC.getText()));
        this.weeklyReviewByRevenueAndBackordersModel.setBackorderVolumeRose(Integer.parseInt(this.BkoRoseValueFunC.getText()));
        this.weeklyReviewByRevenueAndBackordersModel.setBackorderVolumeNoir(Integer.parseInt(this.BkoNoirValueFunC.getText()));
        logger.info("Input fields NumYear={}, CapLabour={}, CapGrape={}, PrcRose={}, PrcNoir={}",
                this.weeklyReviewByRevenueAndBackordersModel.getNumWeek(),
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

        // Calculate optimal Sales Revenue and display them on the right of the panel
        this.calculateAndDisplayOptimalSalesRevenueAndCheckBackordersProduction();

        // Validate data and pop error message if needed
//        this.abnormalSituationValidations();
    }

    private void calculateAndDisplayOptimalSalesRevenueAndCheckBackordersProduction() {
        int[] remainingResourceAfterBackorders = this.weeklyReviewByRevenueAndBackordersModel.calculateLabourAndGrapeSurplus(this.weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeRose(),
                this.weeklyReviewByRevenueAndBackordersModel.getBackorderVolumeNoir());
        this.weeklyReviewByRevenueAndBackordersModel.setCapLabour(remainingResourceAfterBackorders[0]);
        this.weeklyReviewByRevenueAndBackordersModel.setCapGrape(remainingResourceAfterBackorders[1]);
        System.out.println(remainingResourceAfterBackorders[0]);
        System.out.println(remainingResourceAfterBackorders[1]);
        OptimalSalesRevenueModel optimalSalesRevenue = this.weeklyReviewByRevenueAndBackordersModel.calculateOptimalProductionValue();
        this.DisplayOptRoseValueFunC.setText(Integer.toString(optimalSalesRevenue.getOptimalLitresOfRose()));
        this.DisplayOptNoirValueFunC.setText(Integer.toString(optimalSalesRevenue.getOptimalLitresOfNoir()));
        this.DisplayTotalValueFunC.setText(Integer.toString(optimalSalesRevenue.getOptimalLitresOfRose() + optimalSalesRevenue.getOptimalLitresOfNoir()));
        this.DisplayTotalRevenueValueFunC.setText(Float.toString(optimalSalesRevenue.getOptimalSalesRevenue()));
        this.DisplayBackorderFulfilmentValueFunC.setText(remainingResourceAfterBackorders[0] >= 0 && remainingResourceAfterBackorders[1] >= 0 ? "Yes" : "No");

    }

}
