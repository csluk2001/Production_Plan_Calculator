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
        Main.stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/frontend/weeklyReviewByRevenue.fxml")))));
    }

    @FXML
    private void invokeFunctionC() throws IOException {
        Main.stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/frontend/WeeklyReviewByRevenueAndBackorders.fxml")))));
    }

    @FXML
    private void terminate() {
        System.exit(0);
    }


}
