package com.d24.controller;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupFormController {

    @FXML
    private JFXTextField txtFullName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    void signupBtnOnAction(ActionEvent event) {

    }

    @FXML
    void loginOnAction(MouseEvent event) throws IOException {
        Stage stage = (Stage) txtUsername.getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view/loginform.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        //add animation
        new FadeIn(scene).play();
    }
}
