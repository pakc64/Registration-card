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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fieldLogin;

    @FXML
    private TextField fieldPassword;

    @FXML
    private Button buttonEnter;

    @FXML
    private Button buttonRegistration;

    @FXML
    void initialize() {

        buttonEnter.setOnAction(event -> {

            if (cZero()) {
                System.out.println("Вошли в авторизацию");
                Avtorization();
            }
        });

        buttonRegistration.setOnAction(event -> {
            buttonRegistration.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/registrationFirst.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();


        });
    }


    private void Avtorization() {
        DBHandler dbHandler = new DBHandler();
        User user = new User();
        user.setLogin(fieldLogin.getText());
        user.setPassword(fieldPassword.getText());
        ResultSet resultSet = dbHandler.getUser(user);

        int counter = 0;

        try {
            while (resultSet.next()) {
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (counter >= 1) {
            System.out.println("Проверка: есть совпадения при авторизации" + " " + counter);
            buttonEnter.getScene().getWindow().hide();
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
            stage.setResizable(false);
            stage.showAndWait();

        }
    }

            private boolean cZero() {
                    boolean count = true;

                    if (fieldLogin.getText().equals("")) {
                            Shake loginAnim = new Shake(fieldLogin);
                            loginAnim.playAnim();
                            count = false;

                    }
                    if (fieldPassword.getText().equals("")) {
                            Shake passwordAnimation = new Shake(fieldPassword);
                            passwordAnimation.playAnim();
                            count = false;
                    }

                    return count;
            }

}

