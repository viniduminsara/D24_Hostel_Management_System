package com.d24.controller;

import animatefx.animation.FadeIn;
import com.d24.bo.custom.SignupBO;
import com.d24.bo.factory.BOFactory;
import com.d24.bo.factory.BOTypes;
import com.d24.dto.UserDTO;
import com.d24.util.RegExPatterns;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

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

    @FXML
    private Label passwordcheck;

    @FXML
    private JFXButton signupBtn;

    SignupBO signupBO = (SignupBO) BOFactory.getBOFactory().getBO(BOTypes.SIGNUP);

    public void initialize(){
        closePasswordBtn.setVisible(false);
        txtVisiblePassword.setVisible(false);
        signupBtn.setDisable(true);

        txtVisiblePassword.textProperty().bindBidirectional(txtPassword.textProperty());

        txtPassword.textProperty().addListener((observable, oldValue, newValue) ->{
            // Check password length and characters
            if (newValue.length() < 8) {
                passwordcheck.setStyle("-fx-text-fill: #c60000");
                passwordcheck.setText("❌ Password should contain 8 characters");
                signupBtn.setDisable(true);
            } else if (!Pattern.compile("[A-Z]").matcher(newValue).find()) {
                passwordcheck.setStyle("-fx-text-fill: #e16507");
                passwordcheck.setText("❌ Password should contain uppercase characters");
                signupBtn.setDisable(true);
            } else if (!Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]").matcher(newValue).find()) {
                passwordcheck.setStyle("-fx-text-fill: #e16507");
                passwordcheck.setText("❌ Password should contain special character");
                signupBtn.setDisable(true);
            }else{
                passwordcheck.setStyle("-fx-text-fill: #098609");
                passwordcheck.setText("✅ Password is suitable");
                signupBtn.setDisable(false);
            }
        });
    }

    @FXML
    void signupBtnOnAction(ActionEvent event) {
        if (!(txtFullName.getText().isEmpty() || txtUsername.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty())){
            if (RegExPatterns.getNamePattern().matcher(txtFullName.getText()).matches()) {
                if (RegExPatterns.getEmailPattern().matcher(txtEmail.getText()).matches()) {
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
                    if (isSaved) {
                        new SystemAlert(Alert.AlertType.CONFIRMATION,"Confirmation","Sign up successful", ButtonType.OK).show();
                        Stage stage = (Stage) txtUsername.getScene().getWindow();
                        Parent scene = null;
                        try {
                            scene = FXMLLoader.load(getClass().getResource("/view/loginform.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        stage.setScene(new Scene(scene));
                        stage.show();

                        //add animation
                        new FadeIn(scene).play();
                    } else {
                        new SystemAlert(Alert.AlertType.WARNING,"Warning","Sign up unsuccessful", ButtonType.OK).show();
                    }

                }else{
                    new SystemAlert(Alert.AlertType.WARNING,"Warning","Invalid email", ButtonType.OK).show();
                }
            }else{
                new SystemAlert(Alert.AlertType.WARNING,"Warning","Invalid name.", ButtonType.OK).show();
            }
        }else {
            new SystemAlert(Alert.AlertType.WARNING,"Warning","Please fill all fields", ButtonType.OK).show();
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
