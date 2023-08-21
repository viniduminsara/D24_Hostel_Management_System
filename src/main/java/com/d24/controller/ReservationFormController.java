package com.d24.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ReservationFormController {

    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/popup/addReservationForm.fxml"))));
        stage.setTitle("Add New Reservation");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.centerOnScreen();
        stage.showAndWait();
    }
}