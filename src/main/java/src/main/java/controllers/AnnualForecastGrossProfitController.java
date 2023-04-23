package src.main.java.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URL;
import java.util.Objects;
import java.io.IOException;
import java.util.ResourceBundle;
import src.main.java.Main;
import src.main.java.models.OptimalSalesRevenueModel;
import src.main.java.models.AnnualForecastGrossProfitModel;

public class AnnualForecastGrossProfitController implements Initializable {
    final Logger logger = LoggerFactory.getLogger(getClass());
    private final AnnualForecastGrossProfitModel annualForecastGrossProfitModel;
    private int[] fieldID;

    @FXML
    private TextField CapGrapeValueFunA;

    @FXML
    private TextField CapLaborValueFunA;

    @FXML
    private Label DisplayOptNoirValueFunA;

    @FXML
    private Label DisplayOptRoseValueFunA;

    @FXML
    private Label DisplayProfitMarginFunA;

    @FXML
    private Label DisplayTotalGrossProfitFunA;

    @FXML
    private Label DisplayTotalValueFunA;

    @FXML
    private Label FieldValidationCheckDetailsFunA;

    @FXML
    private Label FieldValidationErrorMessageFunA;

    @FXML
    private TextField FixedCostsValueFunA;

    @FXML
    private TextField NumWeekValueFunA;

    @FXML
    private TextField PrcNoirValueFunA;

    @FXML
    private TextField PrcRoseValueFunA;

    @FXML
    private Label ProfitMarginTitleFunA;

    @FXML
    private Label ProfitMarginUnitFunA;

    @FXML
    private Label TotalGrossProfitTitleFunA;

    @FXML
    private Label TotalGrossProfitUnitFunA;

    @FXML
    private Button button_exit_FunA;

    @FXML
    private Button button_run_FunA;

    public AnnualForecastGrossProfitController() {
        annualForecastGrossProfitModel = new AnnualForecastGrossProfitModel();
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

    private float roundToOneDp (float value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (float) Math.round(value * scale) / scale;
    }

    private boolean checkAllFieldsFilledFunA() {
        return !(this.annualForecastGrossProfitModel.getWeekOfYear() == Integer.MIN_VALUE ||
                this.annualForecastGrossProfitModel.getCapGrape() == Integer.MIN_VALUE ||
                this.annualForecastGrossProfitModel.getCapLabour() == Integer.MIN_VALUE ||
                this.annualForecastGrossProfitModel.getPrcRose() == Float.MIN_VALUE ||
                this.annualForecastGrossProfitModel.getPrcNoir() == Float.MIN_VALUE ||
                this.annualForecastGrossProfitModel.getFixedCosts() == Float.MIN_VALUE);
    }


    private void syncFieldValidationCheckDetails(int inputFieldID, boolean valid) {
        /*
            inputFieldID:
                1) Num_Week
                2) Cap_Labour
                3) Cap_Noir
                4) Prc_Rose
                5) Prc_Noir
                6) Fixed_Costs
         */
        switch (inputFieldID) {
            case 1 :
                this.FieldValidationCheckDetailsFunA.setText(valid ? "1 <= Number of week <= 15" : "Number of week should be in range of 1 to 15");
                break;
            case 2 :
                this.FieldValidationCheckDetailsFunA.setText(valid ? "Cap_Labour is Numeric" : "Cap_Labour is not Numeric or integer");
                break;
            case 3 :
                this.FieldValidationCheckDetailsFunA.setText(valid ? "Cap_Grape is Numeric" : "Cap_Grape is not Numeric or integer");
                break;
            case 4 :
                this.FieldValidationCheckDetailsFunA.setText(valid ? "Prc_Rose is Numeric" : "Prc_Rose is not Numeric");
                break;
            case 5 :
                this.FieldValidationCheckDetailsFunA.setText(valid ? "Prc_Noir is Numeric" : "Prc_Noir is not Numeric");
                break;
            case 6 :
                this.FieldValidationCheckDetailsFunA.setText(valid ? "Fixed_Costs is Numeric" : "Fixed_Costs is not Numeric or Integer");
                break;
            default : this.FieldValidationCheckDetailsFunA.setText("");
        }
    }

    private void releaseAllFields() {
        this.fieldID = new int[6];
        this.NumWeekValueFunA.setEditable(true);
        this.CapLaborValueFunA.setEditable(true);
        this.CapGrapeValueFunA.setEditable(true);
        this.PrcRoseValueFunA.setEditable(true);
        this.PrcNoirValueFunA.setEditable(true);
        this.FixedCostsValueFunA.setEditable(true);
    }

    private void lockOtherFields() {
        for (int id : this.fieldID) {
            switch (id) {
                case 1 -> this.NumWeekValueFunA.setEditable(false);
                case 2 -> this.CapLaborValueFunA.setEditable(false);
                case 3 -> this.CapGrapeValueFunA.setEditable(false);
                case 4 -> this.PrcRoseValueFunA.setEditable(false);
                case 5 -> this.PrcNoirValueFunA.setEditable(false);
                case 6 -> this.FixedCostsValueFunA.setEditable(false);
            }
        }
    }

    private void syncNumWeekValue() {
        if (annualForecastGrossProfitModel.isEmptyField(this.NumWeekValueFunA.getText())) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            annualForecastGrossProfitModel.setWeekOfYear(Integer.MIN_VALUE);
            logger.info("NumWeek change to null");
            return;
        }
        // set validation message
        boolean validInput = annualForecastGrossProfitModel.isValidNumWeek(this.NumWeekValueFunA.getText());
        this.syncFieldValidationCheckDetails(1, validInput);
        if (!validInput) {
            annualForecastGrossProfitModel.setWeekOfYear(Integer.MIN_VALUE);
            this.fieldID = new int[]{2, 3, 4, 5, 6};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        annualForecastGrossProfitModel.setWeekOfYear(Integer.parseInt(this.NumWeekValueFunA.getText()));
        logger.info("NumWeek change to {}", this.NumWeekValueFunA.getText());
    }

    private void syncCapLabourValue() {
        // numeric validation
        if (annualForecastGrossProfitModel.isEmptyField(this.CapLaborValueFunA.getText())) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            annualForecastGrossProfitModel.setCapLabour(Integer.MIN_VALUE);
            logger.info("CapLabor change to null");
            return;
        }
        // numeric validation
        boolean validInput = annualForecastGrossProfitModel.isInteger(this.CapLaborValueFunA.getText());
        // set validation message
        this.syncFieldValidationCheckDetails(2, validInput);
        if (!validInput) {
            annualForecastGrossProfitModel.setCapLabour(Integer.MIN_VALUE);
            this.fieldID = new int[]{1, 3, 4, 5, 6};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        annualForecastGrossProfitModel.setCapLabour(Integer.parseInt(this.CapLaborValueFunA.getText()));
        logger.info("CapLabor change to {}", this.CapLaborValueFunA.getText());
    }

    public void syncCapGrapeValue() {
        if (annualForecastGrossProfitModel.isEmptyField(this.CapGrapeValueFunA.getText())) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            annualForecastGrossProfitModel.setCapGrape(Integer.MIN_VALUE);
            logger.info("CapGrape change to null");
            return;
        }
        // numeric validation
        boolean validInput = annualForecastGrossProfitModel.isInteger(this.CapGrapeValueFunA.getText());
        // set validation message
        this.syncFieldValidationCheckDetails(3, validInput);
        if (!validInput) {
            annualForecastGrossProfitModel.setCapGrape(Integer.MIN_VALUE);
            this.fieldID = new int[]{1, 2, 4, 5, 6};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        annualForecastGrossProfitModel.setCapGrape(Integer.parseInt(this.CapGrapeValueFunA.getText()));
        logger.info("NumYear change to {}", this.CapGrapeValueFunA.getText());
    }

    private void syncPrcRoseValue() {
        if (annualForecastGrossProfitModel.isEmptyField(this.PrcRoseValueFunA.getText())) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            annualForecastGrossProfitModel.setPrcRose(Float.MIN_VALUE);
            logger.info("PrcRose change to null");
            return;
        }
        // numeric validation
        boolean validInput = annualForecastGrossProfitModel.isNumeric(this.PrcRoseValueFunA.getText());
        // set validation message
        this.syncFieldValidationCheckDetails(4, validInput);
        if (!validInput) {
            annualForecastGrossProfitModel.setPrcRose(Float.MIN_VALUE);
            this.fieldID = new int[]{1, 2, 3, 5, 6};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        annualForecastGrossProfitModel.setPrcRose(Float.parseFloat(this.PrcRoseValueFunA.getText()));
        logger.info("PrcRose change to {}", this.PrcRoseValueFunA.getText());
    }


    private void syncPrcNoirValue() {
        if (annualForecastGrossProfitModel.isEmptyField(this.PrcNoirValueFunA.getText())) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            annualForecastGrossProfitModel.setPrcNoir(Float.MIN_VALUE);
            logger.info("PrcNoir change to null");
            return;
        }
        // numeric validation
        boolean validInput = annualForecastGrossProfitModel.isNumeric(this.PrcNoirValueFunA.getText());
        // validation message
        this.syncFieldValidationCheckDetails(5, validInput);
        if (!validInput) {
            annualForecastGrossProfitModel.setPrcNoir(Float.MIN_VALUE);
            this.fieldID = new int[]{1, 2, 3, 4, 6};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        annualForecastGrossProfitModel.setPrcNoir(Float.parseFloat(this.PrcNoirValueFunA.getText()));
        logger.info("PrcNoir change to {}", this.PrcNoirValueFunA.getText());
    }

    private void syncFixedCostsValue() {
        if (annualForecastGrossProfitModel.isEmptyField(this.FixedCostsValueFunA.getText())) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            annualForecastGrossProfitModel.setBackorderVolumeRose(Integer.MIN_VALUE);
            logger.info("FixedCosts change to null");
            return;
        }
        // numeric validation
        boolean validInput = annualForecastGrossProfitModel.isInteger(this.FixedCostsValueFunA.getText());
        // validation message
        this.syncFieldValidationCheckDetails(6, validInput);
        if (!validInput) {
            annualForecastGrossProfitModel.setBackorderVolumeRose(Integer.MIN_VALUE);
            this.fieldID = new int[]{1, 2, 3, 4, 5};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        annualForecastGrossProfitModel.setBackorderVolumeRose(Integer.parseInt(this.FixedCostsValueFunA.getText()));
        logger.info("FixedCosts change to {}", this.FixedCostsValueFunA.getText());
    }


    private void abnormalSituationValidationsFunA(int [] opt_Litre) {
        final String errorMsg1 = "w1: Insufficient production capacity to produce the optimal mix, please reduce or adjust the capacity of\nlabor & grape volume!";
        final String errorMsg2 = "w2: Insufficient labor supplied to utilize the grape resource (less than 90%)!";
        final String errorMsg12 = errorMsg1 + "\n" + errorMsg2;
        if (this.annualForecastGrossProfitModel.getMAX_PRODUCTION_CAPACITY_OF_MANUFACTURING_FACILITIES() * this.annualForecastGrossProfitModel.getWeekOfYear() >= opt_Litre[0] + opt_Litre[1] && !this.determineInsufficientLabourSupplyForSatisfiedGrapeResourceUtilization()) {
            // Do nothing
        } else if (this.annualForecastGrossProfitModel.getMAX_PRODUCTION_CAPACITY_OF_MANUFACTURING_FACILITIES() * this.annualForecastGrossProfitModel.getWeekOfYear() < opt_Litre[0] + opt_Litre[1] && this.determineInsufficientLabourSupplyForSatisfiedGrapeResourceUtilization()) {
            this.FieldValidationErrorMessageFunA.setText(errorMsg12);
        } else if (this.annualForecastGrossProfitModel.getMAX_PRODUCTION_CAPACITY_OF_MANUFACTURING_FACILITIES() * this.annualForecastGrossProfitModel.getWeekOfYear() < opt_Litre[0] + opt_Litre[1]) {
            this.FieldValidationErrorMessageFunA.setText(errorMsg1);
        } else {
            this.FieldValidationErrorMessageFunA.setText(errorMsg2);
        }
    }

    private boolean determineInsufficientProductionCapacityForOptimalMix() {
        OptimalSalesRevenueModel optimalSalesRevenue = this.annualForecastGrossProfitModel.calculateOptimalProductionValue();
        return this.annualForecastGrossProfitModel.wineProductionCapacityOverloadedOnGrape(optimalSalesRevenue.getOptimalLitresOfRose(), optimalSalesRevenue.getOptimalLitresOfNoir());
    }

    private boolean determineInsufficientLabourSupplyForSatisfiedGrapeResourceUtilization() {
        OptimalSalesRevenueModel optimalSalesRevenue = this.annualForecastGrossProfitModel.calculateOptimalProductionValue();
        return this.annualForecastGrossProfitModel.grapeResourceUtilizationIsInsufficientDueToInsufficientLabourSupplied(optimalSalesRevenue.getOptimalLitresOfRose(), optimalSalesRevenue.getOptimalLitresOfNoir(), this.annualForecastGrossProfitModel.getCapGrape());
    }


    public void setValueAgain(){
        this.annualForecastGrossProfitModel.setWeekOfYear(Integer.parseInt(this.NumWeekValueFunA.getText()));
        this.annualForecastGrossProfitModel.setCapLabour(Integer.parseInt(this.CapLaborValueFunA.getText()));
        this.annualForecastGrossProfitModel.setCapGrape(Integer.parseInt(this.CapGrapeValueFunA.getText()));
        this.annualForecastGrossProfitModel.setPrcRose(Float.parseFloat(this.PrcRoseValueFunA.getText()));
        this.annualForecastGrossProfitModel.setPrcNoir(Float.parseFloat(this.PrcNoirValueFunA.getText()));
        this.annualForecastGrossProfitModel.setFixedCosts(Integer.parseInt(this.FixedCostsValueFunA.getText()));

    }

    @FXML
    void returnToMainFunA() throws IOException {
        Main.stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/frontend/applicationMainMenu.fxml")))));
    }

    @FXML
    void runCalculationA(ActionEvent event) {
        logger.info("Input fields NumWeek={}, CapLabour={}, CapGrape={}, PrcRose={}, PrcNoir={}, FixedCosts={}",
                this.annualForecastGrossProfitModel.getWeekOfYear(),
                this.annualForecastGrossProfitModel.getCapLabour(),
                this.annualForecastGrossProfitModel.getCapGrape(),
                this.annualForecastGrossProfitModel.getPrcRose(),
                this.annualForecastGrossProfitModel.getPrcNoir(),
                this.annualForecastGrossProfitModel.getFixedCosts()
        );

        if (!this.checkAllFieldsFilledFunA()) {
            logger.error("Fields are not fully filled yet!");
            return;
        }
        this.FieldValidationCheckDetailsFunA.setText("");
        this.setValueAgain();

        // Calculate optimal Total Gross Profit and display them on the right of the panel
        int [] opt_Litre = this.calculateAndDisplayTotalGrossProfit();

        // Validate data and pop error message if needed
        this.abnormalSituationValidationsFunA(opt_Litre);
    }

    private int [] calculateAndDisplayTotalGrossProfit() {
        OptimalSalesRevenueModel optimalSalesRevenue = this.annualForecastGrossProfitModel.calculateOptimalProductionValue();
        this.DisplayOptRoseValueFunA.setText(Integer.toString(optimalSalesRevenue.getOptimalLitresOfRose()));
        this.DisplayOptNoirValueFunA.setText(Integer.toString(optimalSalesRevenue.getOptimalLitresOfNoir()));
        this.DisplayTotalValueFunA.setText(Integer.toString(optimalSalesRevenue.getOptimalLitresOfRose() + optimalSalesRevenue.getOptimalLitresOfNoir()));
        float sale_revenue= (optimalSalesRevenue.getOptimalSalesRevenue());
        int VCL=((optimalSalesRevenue.getOptimalLitresOfRose() * this.annualForecastGrossProfitModel.getROSE_CONSUMPTION_TO_MAKE_A_LITRE_OF_WINE_LABOUR())+(optimalSalesRevenue.getOptimalLitresOfNoir() * this.annualForecastGrossProfitModel.getNOIR_CONSUMPTION_TO_MAKE_A_LITRE_OF_WINE_LABOUR()));
        float labor_rate=((this.annualForecastGrossProfitModel.getLABOUR_COST_RATE_PER_WEEK())/this.annualForecastGrossProfitModel.getSTANDARD_MAN_POWER_PER_HEAD()/60);
        float profit=(sale_revenue-(VCL*labor_rate)-this.annualForecastGrossProfitModel.getFixedCosts());
        this.DisplayTotalGrossProfitFunA.setText(this.convertToCommaSeparatedString(Math.round(profit)));
        this.DisplayProfitMarginFunA.setText(Float.toString(this.roundToOneDp(profit/sale_revenue*100, 1)));
        return new int[] {
                optimalSalesRevenue.getOptimalLitresOfRose(),
                optimalSalesRevenue.getOptimalLitresOfRose()
        };
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NumWeekValueFunA.textProperty().addListener((Observable, oldValue, newValue) -> this.syncNumWeekValue());
        CapLaborValueFunA.textProperty().addListener((Observable, oldValue, newValue) -> this.syncCapLabourValue());
        CapGrapeValueFunA.textProperty().addListener((Observable, oldValue, newValue) -> this.syncCapGrapeValue());
        PrcRoseValueFunA.textProperty().addListener((Observable, oldValue, newValue) -> this.syncPrcRoseValue());
        PrcNoirValueFunA.textProperty().addListener((Observable, oldValue, newValue) -> this.syncPrcNoirValue());
        FixedCostsValueFunA.textProperty().addListener((Observable, oldValue, newValue) -> this.syncFixedCostsValue());
    }

}
