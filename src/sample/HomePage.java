package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class HomePage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button pakcInformation;

    @FXML
    private TextArea nameArea;

    @FXML
    private TextArea surnameArea;

    @FXML
    private TextArea ageArea;

    @FXML
    private TextArea sexArea;

    @FXML
    private TextArea countryArea;

    @FXML
    void initialize() {


        pakcInformation.setOnAction(event -> {

            String fileTxt = "developer: Serkov Vyacheslav Nikolaevich Belgorod 12.01.2022. nickname rakc";

            Alert alert = new Alert((Alert.AlertType.INFORMATION));
            alert.setTitle("Внимание!");
            alert.setHeaderText(null);
            alert.setContentText(fileTxt);
            alert.showAndWait();

        });



    }
}
