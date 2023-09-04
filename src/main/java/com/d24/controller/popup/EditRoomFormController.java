package com.d24.controller.popup;

import com.d24.bo.custom.RoomBO;
import com.d24.bo.custom.impl.RoomBOImpl;
import com.d24.controller.RoomFromController;
import com.d24.dto.RoomDTO;
import com.d24.util.RegExPatterns;
import com.d24.util.SystemAlert;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class EditRoomFormController {

    @FXML
    private JFXTextField txtType;

    @FXML
    private JFXTextField txtKeymoney;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private Label lblRoomId;

    private RoomFromController roomFromController;

    RoomBO roomBO = new RoomBOImpl();

    public void setDetails(RoomDTO roomDTO) {
        lblRoomId.setText(roomDTO.getRoomTypeId());
        txtType.setText(roomDTO.getType());
        txtKeymoney.setText(String.valueOf(roomDTO.getKeyMoney()));
        txtQty.setText(String.valueOf(roomDTO.getQty()));
    }

    public void setRoomController(RoomFromController roomFromController) {
        this.roomFromController = roomFromController;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (!(txtType.getText().isEmpty() || txtKeymoney.getText().isEmpty() || txtQty.getText().isEmpty())){
            if (RegExPatterns.getDoublePattern().matcher(txtKeymoney.getText()).matches()) {
                if (RegExPatterns.getIntPattern().matcher(txtQty.getText()).matches()) {
                    RoomDTO roomDTO = new RoomDTO();
                    roomDTO.setRoomTypeId(lblRoomId.getText());
                    roomDTO.setType(txtType.getText());
                    roomDTO.setKeyMoney(Double.valueOf(txtKeymoney.getText()));
                    roomDTO.setQty(Integer.valueOf(txtQty.getText()));

                    boolean isUpdated = false;
                    try {
                        isUpdated = roomBO.updateRoom(roomDTO);
                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    }
                    if (isUpdated){
                        new SystemAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Room updated successfully", ButtonType.OK).show();
                        Stage stage = (Stage) txtQty.getScene().getWindow();
                        stage.close();
                        roomFromController.populateRoomTable();
                    }else{
                        new SystemAlert(Alert.AlertType.WARNING, "Warning", "Failed to update the room").show();
                    }
                }else{
                    new SystemAlert(Alert.AlertType.WARNING, "Warning", txtQty.getText()+" is not an integer value").show();
                }
            }else{
                new SystemAlert(Alert.AlertType.WARNING, "Warning", txtKeymoney.getText()+" is not floating value").show();
            }
        }else{
            new SystemAlert(Alert.AlertType.WARNING,"Warning","Please fill all details", ButtonType.OK).show();
        }
    }
}
