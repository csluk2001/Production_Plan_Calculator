package src.main.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class comp3111pcc1Controller {

    @FXML
    private Label Heading1;

    @FXML
    private Button button_exit1;

    @FXML
    private Button function1;

    @FXML
    private Button function2;

    @FXML
    private Button function3;

    @FXML
    private AnchorPane menu1;

    @FXML
    void buttonPressed(ActionEvent event) {

    }

    @FXML
    void buttonpressed(ActionEvent event) {

    }

    @FXML
    private void Afunction() throws IOException {
        Scene scene=new Scene(FXMLLoader.load(getClass().getResource("/ppc1_function_A.fxml")));
        Main.stage.setScene(scene);
    }
    @FXML
    private void exit1() throws IOException {
        System.exit(0);
    }
}

