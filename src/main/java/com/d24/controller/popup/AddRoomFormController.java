package com.d24.controller.popup;

import com.d24.bo.custom.RoomBO;
import com.d24.bo.factory.BOFactory;
import com.d24.bo.factory.BOTypes;
import com.d24.controller.RoomFromController;
import com.d24.dto.RoomDTO;
import com.d24.util.RegExPatterns;
import com.d24.util.SystemAlert;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;


public class AddRoomFormController {

    @FXML
    private JFXTextField txtRoomId;

    @FXML
    private JFXTextField txtType;

    @FXML
    private JFXTextField txtKeymoney;

    @FXML
    private JFXTextField txtQty;

    private RoomFromController roomFromController;

    RoomBO roomBO = (RoomBO) BOFactory.getBOFactory().getBO(BOTypes.ROOM);

    public void setRoomFormController(RoomFromController roomFromController) {
        this.roomFromController = roomFromController;
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (!(txtRoomId.getText().isEmpty() || txtType.getText().isEmpty() || txtKeymoney.getText().isEmpty() || txtQty.getText().isEmpty())){
            if (RegExPatterns.getDoublePattern().matcher(txtKeymoney.getText()).matches()){
                if (RegExPatterns.getIntPattern().matcher(txtQty.getText()).matches()){

                    RoomDTO roomDTO = new RoomDTO();
                    roomDTO.setRoomTypeId(txtRoomId.getText());
                    roomDTO.setType(txtType.getText());
                    roomDTO.setKeyMoney(Double.valueOf(txtKeymoney.getText()));
                    roomDTO.setQty(Integer.valueOf(txtQty.getText()));

                    boolean isExists = roomBO.existRoom(txtRoomId.getText());
                    if (!isExists) {

                        boolean isSaved = roomBO.saveRoom(roomDTO);
                        if (isSaved) {
                            new SystemAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Room saved successfully", ButtonType.OK).show();
                            Stage stage = (Stage) txtRoomId.getScene().getWindow();
                            stage.close();
                            roomFromController.populateRoomTable();
                            roomFromController.searchFilter();
                        } else {
                            new SystemAlert(Alert.AlertType.WARNING, "Warning", "Failed to save the room").show();
                        }

                    }else{
                        new SystemAlert(Alert.AlertType.WARNING, "Warning", txtRoomId.getText()+" already exists").show();
                    }
                }else{
                    new SystemAlert(Alert.AlertType.WARNING, "Warning", txtQty.getText()+" is not an integer value").show();
                }
            }else{
                new SystemAlert(Alert.AlertType.WARNING, "Warning", txtKeymoney.getText()+" is not floating value").show();
            }
        }else{
            new SystemAlert(Alert.AlertType.WARNING, "Warning", "Please fill all details").show();
        }
    }
}
