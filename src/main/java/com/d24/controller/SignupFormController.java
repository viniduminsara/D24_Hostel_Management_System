package com.d24.controller;

import animatefx.animation.FadeIn;
import com.d24.bo.custom.SignupBO;
import com.d24.bo.custom.impl.SignupBOImpl;
import com.d24.dto.UserDTO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private JFXTextField txtVisiblePassword;

    @FXML
    private JFXButton showPasswordBtn;

    @FXML
    private JFXButton closePasswordBtn;

    SignupBO signupBO = new SignupBOImpl();

    public void initialize(){
        closePasswordBtn.setVisible(false);
        txtVisiblePassword.setVisible(false);

        txtVisiblePassword.textProperty().bindBidirectional(txtPassword.textProperty());
    }

    @FXML
    void signupBtnOnAction(ActionEvent event) {
        String fullName = txtFullName.getText();
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        UserDTO userDTO = new UserDTO();
        userDTO.setFullName(fullName);
        userDTO.setUsername(username);
        userDTO.setEmail(email);
        userDTO.setPassword(password);

        boolean isSaved = signupBO.saveUser(userDTO);
        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"Sign up successful").show();
        }else{
            new Alert(Alert.AlertType.WARNING,"Sign up unsuccessful").show();
        }

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

    @FXML
    void closePasswordBtnOnAction(ActionEvent event) {
        txtVisiblePassword.setVisible(false);
        closePasswordBtn.setVisible(false);
        txtPassword.setVisible(true);
        showPasswordBtn.setVisible(true);
        txtPassword.requestFocus();
    }

    @FXML
    void showPasswordBtnOnAction(ActionEvent event) {
        txtVisiblePassword.setVisible(true);
        closePasswordBtn.setVisible(true);
        txtPassword.setVisible(false);
        showPasswordBtn.setVisible(false);
        txtVisiblePassword.requestFocus();
    }

}
