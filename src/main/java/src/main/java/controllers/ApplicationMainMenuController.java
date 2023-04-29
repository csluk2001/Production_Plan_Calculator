package src.main.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import src.main.java.Main;

import java.io.IOException;
import java.util.Objects;

/**
 * This class is a Controller for PPC1 Calculator's main menu GUI /resource/frontend/applicationMainMenu.fxml.
 * @author LUK, Chun San Marco, Github id: csluk2001
 * @version Java15
 */

public class ApplicationMainMenuController {

    /**
     * This method is called when the user clicks on "Function A" button.
     * It loads the annualForecastByGrossProfit.fxml scene
     * @throws IOException
     */
    @FXML
    private void invokeFunctionA() throws IOException {
        Scene AnnualForecastGrossProfitScene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/frontend/annualForecastByGrossProfit.fxml"))));
        AnnualForecastGrossProfitScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/static/annualForecastByGrossProfit.css")).toExternalForm());
        Main.stage.setScene(AnnualForecastGrossProfitScene);
    }

    /**
     * This method is called when the user clicks on "Function B" button.
     * It loads the weeklyReviewByRevenue.fxml scene
     * @throws IOException
     */
    @FXML
    private void invokeFunctionB() throws IOException {
        Scene WeeklyReviewByRevenueScene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/frontend/weeklyReviewByRevenue.fxml"))));
        WeeklyReviewByRevenueScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/static/weeklyReviewByRevenue.css")).toExternalForm());
        Main.stage.setScene(WeeklyReviewByRevenueScene);
    }

    /**
     * This method is called when the user clicks on "Function C" button.
     * It loads the weeklyReviewByRevenueAndBackorders.fxml scene.
     * @throws IOException
     */
    @FXML
    private void invokeFunctionC() throws IOException {
        Scene WeeklyReviewByRevenueAndBackordersScene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/frontend/weeklyReviewByRevenueAndBackorders.fxml"))));
        WeeklyReviewByRevenueAndBackordersScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/static/WeeklyReviewByRevenueAndBackorders.css")).toExternalForm());
        Main.stage.setScene(WeeklyReviewByRevenueAndBackordersScene);
    }

    /**
     * This method is called when the user clicks on "Exit" button.
     * It terminates the application.
     */
    @FXML
    private void terminate() {
        System.exit(0);
    }


}
