package com.d24.controller;

import com.d24.bo.custom.AccountBO;
import com.d24.bo.custom.impl.AccountBOImpl;
import com.d24.controller.popup.EditAccountFormController;
import com.d24.dto.UserDTO;
import com.d24.util.SystemAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import java.io.*;
import java.nio.file.Files;

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

    AccountBO accountBO = new AccountBOImpl();

    UserDTO userDTO;

    DashboardFormController dashboardFormController;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/popup/editAccountForm.fxml"));
        stage.setScene(new Scene(loader.load()));
        EditAccountFormController editAccountFormController = loader.getController();
        editAccountFormController.setUserDTO(userDTO);
        editAccountFormController.setDetails();
        editAccountFormController.setAccountFormController(this);
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

    @FXML
    void btnEditprofilePictureOnAction(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select the image");
        FileChooser.ExtensionFilter imageFilter =
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png", "*.gif", "*.bmp");
        fileChooser.getExtensionFilters().add(imageFilter);
        File file = fileChooser.showOpenDialog(lblUsername.getScene().getWindow());
        if (file != null) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                profileImage.setImage(new Image(fileInputStream));

                byte[] imageBytes = Files.readAllBytes(file.toPath());

                userDTO.setProfileImage(imageBytes);
                boolean isUpdated = accountBO.updateProfileImage(userDTO);
                if (isUpdated){
                    new SystemAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Profile image updated", ButtonType.OK).show();
                    setDetails();
                    dashboardFormController.setDetails();
                }else{
                    new SystemAlert(Alert.AlertType.WARNING, "Warning", "Failed to update the image").show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void refreshUser(){
        this.userDTO = accountBO.getUser(userDTO.getUserId());
        setDetails();
        dashboardFormController.setDetails();
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

        lblName.setText(userDTO.getFullName());
        lblGmail.setText(userDTO.getEmail());
        lblUsername.setText(userDTO.getUsername());
    }

    public void setDashboardController(DashboardFormController dashboardFormController) {
        this.dashboardFormController = dashboardFormController;
    }
}
