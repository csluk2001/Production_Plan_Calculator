package src.main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 *  The Main class is the starting point of the application.
 *  It extends the Application class which is used to launch the JavaFX application.
 *  It loads the application main menu using FXMLLoader and sets up the stage and scene.
 * @author 2022-2023 Dept of CSE - COMP3111 Teaching team
 */
public class Main extends Application {

    /**
     * The main stage of the application.
     */
    public static Stage stage;

    /**
     * The main scene of the application.
     */
    public static Scene scene;

    /**
     * This method is called when the JavaFX application is launched.
     * It sets up the stage and scene, and loads the main menu FXML file.
     *
     * @param primaryStage the primary stage for this application, onto which the application scene can be set.
     * @throws IOException if an I/O error occurs while loading the FXML file.
     */
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
