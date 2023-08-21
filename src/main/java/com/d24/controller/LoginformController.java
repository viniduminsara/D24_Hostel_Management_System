package com.d24.controller;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
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

public class LoginformController{

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXButton showpasswordbtn;

    @FXML
    private JFXButton closepasswordbtn;

    @FXML
    private JFXTextField txtVisiblePassword;


    public void initialize(){
        closepasswordbtn.setVisible(false);
        txtVisiblePassword.setVisible(false);

        txtVisiblePassword.textProperty().bindBidirectional(txtPassword.textProperty());
    }

    @FXML
    void loginBtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) txtUsername.getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        //add animation
        new FadeIn(scene).play();
    }

    @FXML
    void signupOnAction(MouseEvent event) throws IOException {
        Stage stage = (Stage) txtUsername.getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view/signupForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        //add animation
        new FadeIn(scene).play();
    }

    @FXML
    void closepasswordbtnOnAction(ActionEvent event) {
        txtVisiblePassword.setVisible(false);
        closepasswordbtn.setVisible(false);
        txtPassword.setVisible(true);
        showpasswordbtn.setVisible(true);
        txtPassword.requestFocus();
    }

    @FXML
    void showpasswordbtnOnAction(ActionEvent event) {
        txtVisiblePassword.setVisible(true);
        closepasswordbtn.setVisible(true);
        txtPassword.setVisible(false);
        showpasswordbtn.setVisible(false);
        txtVisiblePassword.requestFocus();
    }
}
