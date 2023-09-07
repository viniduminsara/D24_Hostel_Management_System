package com.d24.controller;

import com.d24.bo.custom.RoomBO;
import com.d24.bo.factory.BOFactory;
import com.d24.bo.factory.BOTypes;
import com.d24.controller.popup.AddRoomFormController;
import com.d24.controller.popup.EditRoomFormController;
import com.d24.dto.RoomDTO;
import com.d24.tm.RoomTM;
import com.d24.util.SystemAlert;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
    
    RoomBO roomBO = (RoomBO) BOFactory.getBOFactory().getBO(BOTypes.ROOM);
    
    ObservableList<RoomTM> roomTMS = FXCollections.observableArrayList();

    public void initialize(){

        //set Value factory to table
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeymoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("hBox"));

        populateRoomTable();
        searchFilter();
    }

    public void populateRoomTable() {

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
    }

    public void searchFilter(){
        FilteredList<RoomTM> filteredData = new FilteredList<>(roomTMS, b -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredData.setPredicate(RoomTM -> {
                if (newValue.isEmpty() || newValue.isBlank()){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                if (RoomTM.getRoomTypeId().toLowerCase().contains(searchKeyword)){
                    return true;
                }else if(RoomTM.getType().toLowerCase().contains(searchKeyword)) {
                    return true;
                }else {
                    return false;
                }
            });
        });

        SortedList<RoomTM> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblRooms.comparatorProperty());
        tblRooms.setItems(sortedData);
    }

    private void setRemoveBtnAction(JFXButton removeBtn, RoomDTO roomDTO) {
        removeBtn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No",ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new SystemAlert(Alert.AlertType.INFORMATION,"Information","Do you want to delete '"+roomDTO.getType()+"' student?",yes,no).showAndWait();

            if (result.orElse(no) == yes){
                //delete student
                boolean isDeleted = roomBO.deleteStudent(roomDTO.getRoomTypeId());
                if (isDeleted){
                    new SystemAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Room deleted successfully", ButtonType.OK).show();
                    populateRoomTable();
                    searchFilter();
                }else{
                    new SystemAlert(Alert.AlertType.WARNING, "Warning", "Failed to delete the room").show();
                }
            }
        });
    }

    private void setEditBtnAction(JFXButton editBtn, RoomDTO roomDTO) {
        editBtn.setOnAction((e) -> {
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/popup/editRoomForm.fxml"));
                stage.setScene(new Scene(loader.load()));
                EditRoomFormController editRoomFormController = loader.getController();
                editRoomFormController.setDetails(roomDTO);
                editRoomFormController.setRoomController(this);
                stage.setTitle("Edit Room");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.centerOnScreen();
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
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
