package com.d24.controller;

import animatefx.animation.Bounce;
import animatefx.animation.FadeIn;
import com.d24.bo.custom.LoginBO;
import com.d24.bo.custom.impl.LoginBOImpl;
import com.d24.dto.UserDTO;
import com.d24.util.SystemAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

    LoginBO loginBO = new LoginBOImpl();

    public void initialize(){
        closepasswordbtn.setVisible(false);
        txtVisiblePassword.setVisible(false);

        txtVisiblePassword.textProperty().bindBidirectional(txtPassword.textProperty());
    }

    @FXML
    void loginBtnOnAction(ActionEvent event) throws IOException {
        if (!txtUsername.getText().isEmpty()){
            if (!txtPassword.getText().isEmpty()){

                String username = txtUsername.getText();
                String password = txtPassword.getText();

                boolean isAuthenticated = loginBO.authenticateUser(username,password);
                if (isAuthenticated){
                    UserDTO userDTO = loginBO.getUser(username,password);

                    Stage stage = (Stage) txtUsername.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboardForm.fxml"));
                    Parent scene = loader.load();
                    stage.setScene(new Scene(scene));
                    DashboardFormController dashboardFormController = loader.getController();
                    dashboardFormController.setUser(userDTO);
                    dashboardFormController.setDetails();
                    stage.show();

                    //add animation
                    new FadeIn(scene).play();
                }else{
                    new SystemAlert(Alert.AlertType.WARNING,"Warning","incorrect username or password.", ButtonType.OK).show();
                }
            }else{
                new Bounce(txtPassword).play();
                new Bounce(txtVisiblePassword).play();
                new Bounce(showpasswordbtn).play();
                new Bounce(closepasswordbtn).play();
            }
        }else{
            new Bounce(txtUsername).play();
        }
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
