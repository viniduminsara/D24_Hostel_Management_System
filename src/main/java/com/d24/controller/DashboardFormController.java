package com.d24.controller;

import animatefx.animation.*;
import com.d24.dto.UserDTO;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardFormController{

    @FXML
    private AnchorPane contentPane;

    @FXML
    private JFXButton DashboardBtn;

    @FXML
    private JFXButton StudentBtn;

    @FXML
    private JFXButton RoomBtn;

    @FXML
    private JFXButton ReservationBtn;

    @FXML
    private JFXButton AccountBtn;

    @FXML
    private ImageView profileImage;

    @FXML
    private Label lblUserName;

    private JFXButton lastClickedButton;

    private UserDTO userDTO;

    public void initialize(){
        try {
            contentPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/homeform.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setImageView();
    }

    @FXML
    void btnAccountOnAction(ActionEvent event) throws IOException {
        handleButtonClick(AccountBtn);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/accountForm.fxml"));
        contentPane.getChildren().add(loader.load());
        AccountFormController accountFormController = loader.getController();
        accountFormController.setUser(userDTO);
        accountFormController.setDashboardController(this);
        accountFormController.setDetails();
        new SlideInRight(contentPane).setSpeed(2).play();
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        handleButtonClick(DashboardBtn);
        contentPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/homeform.fxml")));
        new SlideInRight(contentPane).setSpeed(2).play();
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) contentPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/loginform.fxml"))));
        stage.show();
    }

    @FXML
    void btnReservationOnAction(ActionEvent event) throws IOException {
        handleButtonClick(ReservationBtn);
        contentPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/reservationForm.fxml")));
        new SlideInRight(contentPane).setSpeed(2).play();
    }

    @FXML
    void btnRoomsOnAction(ActionEvent event) throws IOException {
        handleButtonClick(RoomBtn);
        contentPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/roomFrom.fxml")));
        new SlideInRight(contentPane).setSpeed(2).play();
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {
        handleButtonClick(StudentBtn);
        contentPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/studentForm.fxml")));
        new SlideInRight(contentPane).setSpeed(2).play();
    }

    private void handleButtonClick(JFXButton button) {
        // Remove the styling from the previous lastClickedButton
        if (lastClickedButton != null) {
            lastClickedButton.getStyleClass().remove("focused_btn");
        }else{
            DashboardBtn.getStyleClass().remove("focused_btn");
        }

        // Add the styling to the current button
        button.getStyleClass().add("focused_btn");

        // Set the current button as the lastClickedButton
        lastClickedButton = button;
    }

    public void setUser(UserDTO userDTO){
        this.userDTO = userDTO;
    }

    public void setDetails(){
        lblUserName.setText(userDTO.getFullName());

        if (userDTO.getProfileImage() != null){
            Image image = new Image(new ByteArrayInputStream(userDTO.getProfileImage()));
            profileImage.setImage(image);
        }
    }

    private void setImageView() {
        double cornerRadius = 30.0; // Set the desired corner radius
        Rectangle clip = new Rectangle(
                profileImage.getFitWidth(), profileImage.getFitHeight());
        clip.setArcWidth(cornerRadius);
        clip.setArcHeight(cornerRadius);
        profileImage.setClip(clip);
    }
}
