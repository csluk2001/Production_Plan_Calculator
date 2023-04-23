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

/**
 * This class is a Controller for PPC1 Function_A GUI /resource/frontend/annualForecastByGrossProfit.fxml.
 * It handles the logic of the UI components and communicates with the model to update the data.
 * @author CHAN, Chun Wai Tommy GitHub id: iawiawiaw
 * @version Java15
 */

public class AnnualForecastGrossProfitController implements Initializable {

    /**
     * The logger for debugging.
     */
    final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * The "AnnualForecastGrossProfitModel" data model for this controller.
     */
    private final AnnualForecastGrossProfitModel annualForecastGrossProfitModel;

    /**
     * The IDs of the fields that should be locked while one field is being edited.
     */
    private int[] fieldID;

    /**
     * The input field for the grape capacity.
     */
    @FXML
    private TextField CapGrapeValueFunA;

    /**
     * The input field for the labor capacity
     */
    @FXML
    private TextField CapLaborValueFunA;

    /**
     * The label for displaying the optimal noir wine production value.
     */
    @FXML
    private Label DisplayOptNoirValueFunA;

    /**
     * The label for displaying the optimal rose wine production value.
     */
    @FXML
    private Label DisplayOptRoseValueFunA;

    /**
     * The label for displaying the marginal profit of production based on profit and sale_revenue.
     */
    @FXML
    private Label DisplayProfitMarginFunA;

    /**
     * The label for displaying the gross profit of production based on sales revenue, variable cost of labor and fixed cost.
     */
    @FXML
    private Label DisplayTotalGrossProfitFunA;

    /**
     * The label for displaying the total mixed of both optimal production of wine.
     */
    @FXML
    private Label DisplayTotalValueFunA;

    /**
     * The label for displaying field validation check details.
     */
    @FXML
    private Label FieldValidationCheckDetailsFunA;

    /**
     * The label for displaying field validation error messages.
     */
    @FXML
    private Label FieldValidationErrorMessageFunA;

    /**
     * The input field for the fixed cost.
     */
    @FXML
    private TextField FixedCostsValueFunA;

    /**
     * The input field for the number of week.
     */
    @FXML
    private TextField NumWeekValueFunA;

    /**
     * The input field for the price of the noir wine.
     */
    @FXML
    private TextField PrcNoirValueFunA;

    /**
     * The input field for the price of the rose wine.
     */
    @FXML
    private TextField PrcRoseValueFunA;

    /**
     * Constructor for the controller. Initializes the data model.
     */
    public AnnualForecastGrossProfitController() {
        annualForecastGrossProfitModel = new AnnualForecastGrossProfitModel();
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
     * Round off a floating point number to N decimal place
     * @param value that is floating point data type
     * @param precision that specify the decimal place N that wants to round off
     * @return a floating point value that is rounded off to N decimal place
     */
    private float roundToNDp (float value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (float) Math.round(value * scale) / scale;
    }

    /**
     * Checks if all the required fields are filled.
     * @return {@code true} if all required fields are filled, {@code true} otherwise.
     */
    private boolean checkAllFieldsFilledFunA() {
        return !(this.annualForecastGrossProfitModel.getWeekOfYear() == Integer.MIN_VALUE ||
                this.annualForecastGrossProfitModel.getCapGrape() == Integer.MIN_VALUE ||
                this.annualForecastGrossProfitModel.getCapLabour() == Integer.MIN_VALUE ||
                this.annualForecastGrossProfitModel.getPrcRose() == Float.MIN_VALUE ||
                this.annualForecastGrossProfitModel.getPrcNoir() == Float.MIN_VALUE ||
                this.annualForecastGrossProfitModel.getFixedCosts() == Integer.MIN_VALUE);
    }

    /**
     * Updates the FieldValidationCheckDetailsFunA label with the appropriate message based on the inputFieldID and valid flag.
     *
     * @param inputFieldID an integer representing the input field being validated
     * @param valid a boolean flag indicating whether the input is correct is or not
     */
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

    /**
     * Enables the editing capabilities of all the fields and resets the fieldID array.
     */
    private void releaseAllFields() {
        this.fieldID = new int[6];
        this.NumWeekValueFunA.setEditable(true);
        this.CapLaborValueFunA.setEditable(true);
        this.CapGrapeValueFunA.setEditable(true);
        this.PrcRoseValueFunA.setEditable(true);
        this.PrcNoirValueFunA.setEditable(true);
        this.FixedCostsValueFunA.setEditable(true);
    }

    /**
     * Disables the editing capabilities of all the fields except for FieldID 0.
     */
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

    /**
     * This method synchronizes the value of the NumYearValueFunA text field with
     * the annualForecastGrossProfitModel's weekOfYear property.
     */
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

    /**
     * Synchronizes the value of CapLabourValueFunA with the data model, validates it and updates the field's validation
     * message label.
     */
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

    /**
     * Synchronizes the value of CapGrapeValueFunA with the data model, validates it and updates the field's validation
     * message label.
     */
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

    /**
     * Synchronizes the value of PrcRoseValueFunA with the data model, validates it and updates the field's validation
     * message label.
     */
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

    /**
     * Synchronizes the value of PrcNoirValueFunA with the data model, validates it and updates the field's validation
     * message label.
     */
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

    /**
     * Synchronizes the value of FixedCostsValueFunA with the data model, validates it and updates the field's validation
     * message label.
     */
    private void syncFixedCostsValue() {
        if (annualForecastGrossProfitModel.isEmptyField(this.FixedCostsValueFunA.getText())) {
            // set validation message
            this.syncFieldValidationCheckDetails(0, true);
            this.releaseAllFields();
            // store value into data model
            annualForecastGrossProfitModel.setFixedCosts(Integer.MIN_VALUE);
            logger.info("FixedCosts change to null");
            return;
        }
        // numeric validation
        boolean validInput = annualForecastGrossProfitModel.isInteger(this.FixedCostsValueFunA.getText());
        // validation message
        this.syncFieldValidationCheckDetails(6, validInput);
        if (!validInput) {
            annualForecastGrossProfitModel.setFixedCosts(Integer.MIN_VALUE);
            this.fieldID = new int[]{1, 2, 3, 4, 5};
            this.lockOtherFields();
            return;
        }
        this.releaseAllFields();
        // store value into data model
        annualForecastGrossProfitModel.setFixedCosts(Integer.parseInt(this.FixedCostsValueFunA.getText()));
        logger.info("FixedCosts change to {}", this.FixedCostsValueFunA.getText());
    }

    /**
     * Performs the abnormal situation validations and updates the FieldValidationErrorMessageFunA label accordingly.
     * The validations are:
     * w1: Insufficient production capacity to produce the optimal mix, please reduce or adjust the capacity of labor & grape volume!
     * w2: Insufficient labor supplied to utilize the grape resource (less than 90%)!
     * If both validations fail, it displays the concatenated error messages in the FieldValidationErrorMessageFunA label.
     */
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

    /**
     * Determines if there is insufficient production capacity to produce the optimal mix of wines.
     * @return {@code true} if there is insufficient production capacity, {@code false} otherwise.
     */
    private boolean determineInsufficientLabourSupplyForSatisfiedGrapeResourceUtilization() {
        OptimalSalesRevenueModel optimalSalesRevenue = this.annualForecastGrossProfitModel.calculateOptimalProductionValue();
        return this.annualForecastGrossProfitModel.grapeResourceUtilizationIsInsufficientDueToInsufficientLabourSupplied(optimalSalesRevenue.getOptimalLitresOfRose(), optimalSalesRevenue.getOptimalLitresOfNoir(), this.annualForecastGrossProfitModel.getCapGrape());
    }

    /**
     * set the value again after clicking "run" button  for ensuring the model obtains the latest user input value.
     */
    public void setValueAgain(){
        this.annualForecastGrossProfitModel.setWeekOfYear(Integer.parseInt(this.NumWeekValueFunA.getText()));
        this.annualForecastGrossProfitModel.setCapLabour(Integer.parseInt(this.CapLaborValueFunA.getText()));
        this.annualForecastGrossProfitModel.setCapGrape(Integer.parseInt(this.CapGrapeValueFunA.getText()));
        this.annualForecastGrossProfitModel.setPrcRose(Float.parseFloat(this.PrcRoseValueFunA.getText()));
        this.annualForecastGrossProfitModel.setPrcNoir(Float.parseFloat(this.PrcNoirValueFunA.getText()));
        this.annualForecastGrossProfitModel.setFixedCosts(Integer.parseInt(this.FixedCostsValueFunA.getText()));

    }

    /**
     * Returns to the main menu by loading the main menu FXML file "/frontend/applicationMainMenu.fxml".
     * @throws IOException if there is an error loading the main menu FXML file
     */
    @FXML
    void returnToMainFunA() throws IOException {
        Main.stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/frontend/applicationMainMenu.fxml")))));
    }

    /**
     * Runs the calculation based on the input values and updates the appropriate UI elements.
     * If any required field is not filled, it displays an error message in the FieldValidationCheckDetailsFunA label
     * and returns without executing the calculation.
     */
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

    /**
     * Calculates the optimal sales revenue based on the input values and displays the results in the appropriate UI elements.
     * @return optimal litre of rose and noir
     */
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
        this.DisplayProfitMarginFunA.setText(Float.toString(this.roundToNDp(profit/sale_revenue*100, 1)));
        return new int[] {
                optimalSalesRevenue.getOptimalLitresOfRose(),
                optimalSalesRevenue.getOptimalLitresOfRose()
        };
    }

    /**
     * Initializes the controller with the appropriate listeners for each input field.
     * @param url the location of the FXML file
     * @param resourceBundle the resources for
     */
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
