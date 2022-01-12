package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationFirst {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldSurname;

    @FXML
    private Button buttonNewRegistration;

    @FXML
    private TextField fieldCountry;

    @FXML
    private TextField fieldSex;

    @FXML
    private TextField fieldAge;

    @FXML
    private TextField fieldLogin;

    @FXML
    private TextField fieldPassword;

    @FXML
    void initialize() {

        buttonNewRegistration.setOnAction(event -> {
            if (CheckZero()) {

                DBHandler dbHadl = new DBHandler();
                dbHadl.singUpUser(fieldName.getText(), fieldSurname.getText(), fieldAge.getText(), fieldSex.getText(), fieldCountry.getText(), fieldLogin.getText(), fieldPassword.getText());

                buttonNewRegistration.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/homePage.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
        });
    }

    private boolean CheckZero() {

        Shake logAnim = new Shake(fieldLogin);
        boolean check = true;                                                      //переменная дающая разрешение на регистрацию при заполнении всех полей

        if (fieldName.getText().equals("")) {
            Shake nameAnim = new Shake(fieldName);
            nameAnim.playAnim();
            check = false;
        }
        if (fieldSurname.getText().equals("")) {
            Shake surnameAnim = new Shake(fieldSurname);
            surnameAnim.playAnim();
            check = false;
        }
        if (fieldCountry.getText().equals("")) {
            Shake countryAnim = new Shake(fieldCountry);
            countryAnim.playAnim();
            check = false;
        }
        if (fieldSex.getText().equals("муж") || fieldSex.getText().equals("жен")) {
            //-------------------------
        } else {
            Shake sexAnim = new Shake(fieldSex);
            sexAnim.playAnim();
            check = false;
        }
        if (fieldAge.getText().equals("")) {
            Shake ageAnim = new Shake(fieldAge);
            ageAnim.playAnim();
            check = false;
        }
        if (fieldLogin.getText().equals("")) {
            logAnim.playAnim();
            check = false;
        }
        if (fieldPassword.getText().equals("")) {
            Shake passAnim = new Shake(fieldPassword);
            passAnim.playAnim();
            check = false;
        }

        DBHandler dbHandler = new DBHandler();
        User user = new User();
        user.setLogin(fieldLogin.getText());
        ResultSet resultSet = dbHandler.getLogin(user);


        int counter = 0;

        try {
            while (resultSet.next()) {
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (counter >= 1) {
            System.out.println("Есть совпадения логина при регистрации");
            logAnim.playAnim();
            check = false;

            Alert alert = new Alert((Alert.AlertType.INFORMATION));
            alert.setTitle("Внимание!");
            alert.setHeaderText(null);
            alert.setContentText("Пользователь с таким логином уже зарегистрирован!");
            alert.showAndWait();
        }

        return check;
    }
}
