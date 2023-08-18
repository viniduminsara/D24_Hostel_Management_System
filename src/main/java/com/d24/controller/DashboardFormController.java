package com.d24.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

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
}
