package com.d24.controller;

import com.d24.bo.custom.ReservationBO;
import com.d24.bo.custom.impl.ReservationBOImpl;
import com.d24.controller.popup.AddReservationFormController;
import com.d24.dto.ReservationDTO;
import com.d24.tm.ReservationTM;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.TableColumn;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
                    paybtn.setPrefSize(50, 40);
                    setPayBtnAction(paybtn, reservationDTO);

                    //create cancel button
                    JFXButton cancelBtn = new JFXButton("Cancel");
                    cancelBtn.getStyleClass().add("RemoveBtn");
                    cancelBtn.setPrefSize(50, 40);
                    setCancelBtnAction(cancelBtn, reservationDTO);

                    //add buttons to hbox
                    hBox = new HBox(paybtn, cancelBtn);
                    hBox.setAlignment(Pos.CENTER);
                    hBox.setSpacing(15);

                    break;
                }
                case "payment done": {
                    //create cancel button
                    JFXButton cancelBtn = new JFXButton("Cancel");
                    cancelBtn.getStyleClass().add("RemoveBtn");
                    cancelBtn.setPrefSize(50, 40);
                    setCancelBtnAction(cancelBtn, reservationDTO);

                    //add buttons to hbox
                    hBox = new HBox(cancelBtn);
                    hBox.setAlignment(Pos.CENTER);

                    break;
                }
                case "canceled":
                    Text text = new Text("Already Canceled");
                    hBox = new HBox(text);
                    hBox.setPrefSize(50, 40);
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

    private void setCancelBtnAction(JFXButton cancelBtn, ReservationDTO reservationDTO) {
    }

    private void setPayBtnAction(JFXButton paybtn, ReservationDTO reservationDTO) {
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
