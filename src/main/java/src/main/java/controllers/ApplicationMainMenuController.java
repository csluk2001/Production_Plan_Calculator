package src.main.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import src.main.java.Main;

import java.io.IOException;
import java.util.Objects;

public class ApplicationMainMenuController {
    @FXML
    private void invokeFunctionA() throws IOException {
        Main.stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/frontend/annualForecastByGrossProfit.fxml")))));
    }

    @FXML
    private void invokeFunctionB() throws IOException {
        Scene WeeklyReviewByRevenueScene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/frontend/weeklyReviewByRevenue.fxml"))));
        WeeklyReviewByRevenueScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/static/weeklyReviewByRevenue.css")).toExternalForm());
        Main.stage.setScene(WeeklyReviewByRevenueScene);
    }

    @FXML
    private void invokeFunctionC() throws IOException {
        Scene WeeklyReviewByRevenueAndBackordersScene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/frontend/weeklyReviewByRevenueAndBackorders.fxml"))));
        WeeklyReviewByRevenueAndBackordersScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/static/weeklyReviewByRevenueAndBackorders.css")).toExternalForm());
        Main.stage.setScene(WeeklyReviewByRevenueAndBackordersScene);
    }

    @FXML
    private void terminate() {
        System.exit(0);
    }


}
