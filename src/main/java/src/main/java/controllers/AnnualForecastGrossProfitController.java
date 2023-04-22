package src.main.java.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private Label AnnualForecastByGrossProfitTitle;

    @FXML
    private AnchorPane AnnualForestByGrossProfitTitle;

    @FXML
    private Label CapGrapeItemNameFunA;

    @FXML
    private Label CapGrapeUnitFunA;

    @FXML
    private TextField CapGrapeValueFunA;

    @FXML
    private TextField CapLaborValueFunA;

    @FXML
    private Label CapLabourItemNameFunA;

    @FXML
    private Label CapLabourUnitFunA;

    @FXML
    private Label DisplayOptNoirLabelFunA;

    @FXML
    private Label DisplayOptNoirValueFunA;

    @FXML
    private Label DisplayOptRoseLabelFunA;

    @FXML
    private Label DisplayOptRoseValueFunA;

    @FXML
    private AnchorPane DisplayPortalFunA;

    @FXML
    private AnchorPane DisplayProductionVolumePaneFunA;

    @FXML
    private Label DisplayProductionVolumeTitleFunA;

    @FXML
    private Label DisplayProfitMarginFunA;

    @FXML
    private Label DisplayTitleFunA;

    @FXML
    private Label DisplayTotalGrossProfitFunA;

    @FXML
    private Label DisplayTotalLabelFunA;

    @FXML
    private Label DisplayTotalValueFunA;

    @FXML
    private Label FieldValidationCheckDetailsFunA;

    @FXML
    private Label FieldValidationErrorMessageFunA;

    @FXML
    private Label FieldValidationTitleFunA;

    @FXML
    private Label FixedCostsFunA;

    @FXML
    private Label FixedCostsUnitFunA;

    @FXML
    private TextField FixedCostsValueFunA;

    @FXML
    private Label NumWeekFunA;

    @FXML
    private Label NumWeekUnitFunA;

    @FXML
    private TextField NumWeekValueFunA;

    @FXML
    private Label PrcNoirItemNameFunA;

    @FXML
    private Label PrcNoirUnitFunA;

    @FXML
    private TextField PrcNoirValueFunA;

    @FXML
    private Label PrcRoseItemNameFunA;

    @FXML
    private Label PrcRoseUnitFunA;

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
    private int FixedCosts = Integer.MIN_VALUE;
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
        return !(this.annualForecastGrossProfitModel.getWeekOfYear() == Integer.MIN_VALUE ||
                this.annualForecastGrossProfitModel.getCapGrape() == Integer.MIN_VALUE ||
                this.annualForecastGrossProfitModel.getCapLabour() == Integer.MIN_VALUE ||
                this.annualForecastGrossProfitModel.getPrcRose() == Float.MIN_VALUE ||
                this.annualForecastGrossProfitModel.getPrcNoir() == Float.MIN_VALUE ||
                this.FixedCosts==Integer.MIN_VALUE);
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
