package src.main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage stage;
    public static Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage=primaryStage;
        scene=new Scene(FXMLLoader.load(getClass().getResource("/ppc1.fxml")));
        stage.setScene(scene);
        stage.show();
    }
}
