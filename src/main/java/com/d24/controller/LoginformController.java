package com.d24.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginformController {

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    void loginBtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) txtUsername.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"))));
        stage.show();
    }
}
