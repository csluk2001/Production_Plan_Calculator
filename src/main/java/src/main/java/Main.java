package src.main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    public static Stage stage;
    public static Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/frontend/applicationMainMenu.fxml"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/static/image/app-logo.png"))));
        stage.setTitle("Production Plan Calculator");
        stage.setScene(scene);
        stage.show();
    }
}
