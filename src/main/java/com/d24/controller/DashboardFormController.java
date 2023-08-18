package com.d24.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {

    @FXML
    private AnchorPane contentPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            contentPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/homeform.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAccountOnAction(ActionEvent event) {

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        contentPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/homeform.fxml")));
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) contentPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/loginform.fxml"))));
        stage.show();
    }

    @FXML
    void btnReservationOnAction(ActionEvent event) {

    }

    @FXML
    void btnRoomsOnAction(ActionEvent event) {

    }

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {
        contentPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/studentForm.fxml")));
    }
}
