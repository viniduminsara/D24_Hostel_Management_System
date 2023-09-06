package com.d24.controller;

import com.d24.bo.custom.ReservationBO;
import com.d24.bo.custom.impl.ReservationBOImpl;
import com.d24.controller.popup.AddReservationFormController;
import com.d24.dto.ReservationDTO;
import com.d24.tm.ReservationTM;
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReservationFormController {

    @FXML
    private TableView<ReservationTM> tblReservation;

    @FXML
    private TableColumn<?, ?> colReservationId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colRoomTypeId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private JFXTextField txtSearch;

    ReservationBO reservationBO = new ReservationBOImpl();

    ObservableList<ReservationTM> reservationTMS = FXCollections.observableArrayList();

    public void initialize(){

        //set value factory for table
        colReservationId.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colRoomTypeId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("hBox"));

        populateReservationTable();
        searchFilter();
    }

    public void populateReservationTable() {
        List<ReservationDTO> reservationDTOS = reservationBO.getAllReservations();
        reservationTMS.clear();
        HBox hBox = new HBox();

        for (ReservationDTO reservationDTO : reservationDTOS) {
            switch (reservationDTO.getStatus()) {
                case "pending payment": {
                    //create pay button
                    JFXButton paybtn = new JFXButton("Mark as Paid");
                    paybtn.getStyleClass().add("EditBtn");
                    paybtn.setPrefHeight(40);
                    setPayBtnAction(paybtn, reservationDTO);

                    //create cancel button
                    JFXButton cancelBtn = new JFXButton("Cancel Reservation");
                    cancelBtn.getStyleClass().add("RemoveBtn");
                    cancelBtn.setPrefHeight(40);
                    setCancelBtnAction(cancelBtn, reservationDTO);

                    //add buttons to hbox
                    hBox = new HBox(paybtn, cancelBtn);
                    hBox.setAlignment(Pos.CENTER);
                    hBox.setSpacing(10);

                    break;
                }
                case "payment done": {
                    //create cancel button
                    JFXButton cancelBtn = new JFXButton("Cancel Reservation");
                    cancelBtn.getStyleClass().add("RemoveBtn");
                    cancelBtn.setPrefHeight(40);
                    setCancelBtnAction(cancelBtn, reservationDTO);

                    //add buttons to hbox
                    hBox = new HBox(cancelBtn);
                    hBox.setAlignment(Pos.CENTER);

                    break;
                }
                case "canceled":
                    Text text = new Text("Already Canceled");
                    hBox = new HBox(text);
                    hBox.setPrefHeight(40);
                    hBox.setAlignment(Pos.CENTER);
                    break;
            }

            reservationTMS.add(new ReservationTM(
                    reservationDTO.getReservationId(),reservationDTO.getDate(),
                    reservationDTO.getStudent().getStudentId(),reservationDTO.getRoom().getRoomTypeId(),
                    reservationDTO.getStatus(),hBox
            ));

            tblReservation.setItems(reservationTMS);
        }
    }

    public void searchFilter(){
        FilteredList<ReservationTM> filteredData = new FilteredList<>(reservationTMS, b -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredData.setPredicate(ReservationTM -> {
                if (newValue.isEmpty() || newValue.isBlank()){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                if (ReservationTM.getReservationId().toLowerCase().contains(searchKeyword)){
                    return true;
                }else if(ReservationTM.getStudentId().toLowerCase().contains(searchKeyword)) {
                    return true;
                }else if(ReservationTM.getRoomTypeId().toLowerCase().contains(searchKeyword)) {
                    return true;
                }else if(ReservationTM.getStatus().toLowerCase().contains(searchKeyword)) {
                    return true;
                }else {
                    return false;
                }
            });
        });

        SortedList<ReservationTM> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblReservation.comparatorProperty());
        tblReservation.setItems(sortedData);
    }

    private void setCancelBtnAction(JFXButton cancelBtn, ReservationDTO reservationDTO) {
        cancelBtn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No",ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new SystemAlert(Alert.AlertType.INFORMATION,"Information","Do you want to cancel '"+reservationDTO.getReservationId()+"' reservation?",yes,no).showAndWait();

            if (result.orElse(no) == yes){
                reservationDTO.setStatus("canceled");
                boolean isCanceled = reservationBO.cancelReservation(reservationDTO);
                if (isCanceled){
                    new SystemAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Reservation canceled successfully", ButtonType.OK).show();
                    populateReservationTable();
                    searchFilter();
                }else{
                    new SystemAlert(Alert.AlertType.WARNING, "Warning", "Failed to cancel the reservation").show();
                }
            }
        });
    }

    private void setPayBtnAction(JFXButton paybtn, ReservationDTO reservationDTO) {
        paybtn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No",ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new SystemAlert(Alert.AlertType.INFORMATION,"Information","Do you want to complete payment of '"+reservationDTO.getReservationId()+"' reservation?",yes,no).showAndWait();

            if (result.orElse(no) == yes){
                reservationDTO.setStatus("payment done");
                boolean isCanceled = reservationBO.cancelReservation(reservationDTO);
                if (isCanceled){
                    new SystemAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Reservation updated successfully", ButtonType.OK).show();
                    populateReservationTable();
                    searchFilter();
                }else{
                    new SystemAlert(Alert.AlertType.WARNING, "Warning", "Failed to update the reservation").show();
                }
            }
        });
    }


    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/popup/addReservationForm.fxml"));
        stage.setScene(new Scene(loader.load()));
        AddReservationFormController addReservationFormController = loader.getController();
        addReservationFormController.setReservationController(this);
        stage.setTitle("Add New Reservation");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.centerOnScreen();
        stage.showAndWait();
    }
}
