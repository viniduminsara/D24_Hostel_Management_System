package com.d24.controller;

import com.d24.bo.custom.RoomBO;
import com.d24.bo.custom.impl.RoomBOImpl;
import com.d24.controller.popup.AddRoomFormController;
import com.d24.dto.RoomDTO;
import com.d24.tm.RoomTM;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RoomFromController {

    @FXML
    private TableView<RoomTM> tblRooms;

    @FXML
    private TableColumn<?, ?> colRoomId;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colKeymoney;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private JFXTextField txtSearch;
    
    RoomBO roomBO = new RoomBOImpl();
    
    ObservableList<RoomTM> roomTMS = FXCollections.observableArrayList();

    public void initialize(){

        //set Value factory to table
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeymoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("hBox"));

        populateRoomTable();
    }

    public void populateRoomTable() {

        try {
            List<RoomDTO> roomDTOS = roomBO.getAllRooms();
            roomTMS.clear();
            for (RoomDTO roomDTO : roomDTOS) {
                //create edit button
                JFXButton editBtn = new JFXButton("Edit",new ImageView("/img/table/update.png"));
                editBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                editBtn.getStyleClass().add("EditBtn");
                editBtn.setPrefSize(50,40);
                setEditBtnAction(editBtn,roomDTO);

                //create remove button
                JFXButton removeBtn = new JFXButton("remove",new ImageView("/img/table/remove.png"));
                removeBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                removeBtn.getStyleClass().add("RemoveBtn");
                removeBtn.setPrefSize(50,40);
                setRemoveBtnAction(removeBtn,roomDTO);

                //add buttons to hbox
                HBox hBox = new HBox(editBtn,removeBtn);
                hBox.setAlignment(Pos.CENTER);
                hBox.setSpacing(15);

                roomTMS.add(new RoomTM(roomDTO.getRoomTypeId(),
                        roomDTO.getType(),roomDTO.getKeyMoney(),
                        roomDTO.getQty(), hBox
                ));
                tblRooms.setItems(roomTMS);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private void setRemoveBtnAction(JFXButton removeBtn, RoomDTO roomDTO) {
    }

    private void setEditBtnAction(JFXButton editBtn, RoomDTO roomDTO) {
    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/popup/addRoomForm.fxml"));
        stage.setScene(new Scene(loader.load()));
        AddRoomFormController addRoomFormController = loader.getController();
        addRoomFormController.setRoomFormController(this);
        stage.setTitle("Add New Room");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.centerOnScreen();
        stage.showAndWait();
    }
}
