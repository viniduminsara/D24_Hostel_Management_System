package com.d24.controller;

import com.d24.dto.UserDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class AccountFormController {

    @FXML
    private ImageView profileImage;

    @FXML
    private Label lblMainName;

    @FXML
    private Label lblMainUsername;

    @FXML
    private Label lblGmail;

    @FXML
    private Label lblUsername;

    @FXML
    private Label lblName;

    @FXML
    private JFXTextField txtCurrentPassword;

    @FXML
    private AnchorPane editPane;

    UserDTO userDTO;

    @FXML
    void changePasswordOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/popup/changePasswordForm.fxml"))));
        stage.setTitle("Change Password");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.centerOnScreen();
        stage.showAndWait();
    }

    @FXML
    void editProfileOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/popup/editAccountForm.fxml"))));
        stage.setTitle("Edit Profile");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.centerOnScreen();
        stage.showAndWait();
    }

    @FXML
    void profileImageExitHover(MouseEvent event) {
        editPane.setVisible(false);
    }

    @FXML
    void profileImageOnHover(MouseEvent event) {
        editPane.setVisible(true);
    }

    public void setUser(UserDTO userDTO){
        this.userDTO = userDTO;
    }

    public void setDetails(){
        lblMainName.setText(userDTO.getFullName());
        lblMainUsername.setText(userDTO.getUsername());

        if (userDTO.getProfileImage() != null){
            Image image = new Image(new ByteArrayInputStream(userDTO.getProfileImage()));
            profileImage.setImage(image);
        }

        lblName.setText(userDTO.getUsername());
        lblGmail.setText(userDTO.getEmail());
        lblUsername.setText(userDTO.getUsername());
    }
}
