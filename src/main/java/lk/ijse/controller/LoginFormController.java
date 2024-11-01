package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private AnchorPane fullLoginForm;

    @FXML
    private TextField inputPassword;

    @FXML
    private TextField inputUserName;

    @FXML
    private AnchorPane loginForm;

    @FXML
    void loginOnAction(ActionEvent event) {
        try {
            Scene scene = new Scene(FXMLLoader.load(this.getClass().getResource("/mainForm.fxml")));
            Stage stage = (Stage) fullLoginForm.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void goToSignUpOnAction(MouseEvent event) {
        try {
            loginForm.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/signUpForm.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
