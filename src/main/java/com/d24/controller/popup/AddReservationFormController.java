package com.d24.controller.popup;

import com.d24.bo.custom.ReservationBO;
import com.d24.bo.custom.impl.ReservationBOImpl;
import com.d24.controller.ReservationFormController;
import com.d24.dto.ReservationDTO;
import com.d24.dto.RoomDTO;
import com.d24.dto.StudentDTO;
import com.d24.util.SystemAlert;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.util.List;

public class AddReservationFormController {

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private JFXComboBox<String> cmbStudent;

    @FXML
    private JFXComboBox<String> cmbRoom;

    @FXML
    private JFXRadioButton rbPaymentDone;

    @FXML
    private ToggleGroup payment;

    @FXML
    private JFXRadioButton rbPendingPayment;

    ReservationFormController reservationFormController;

    ReservationBO reservationBO = new ReservationBOImpl();
    
    public void initialize(){
        setStudentComboBox();
        setRoomComboBox();
    }

    private void setRoomComboBox() {
        List<RoomDTO> roomDTOS = reservationBO.getAllRooms();
        ObservableList<String> roomIds = FXCollections.observableArrayList();
        for (RoomDTO roomDTO : roomDTOS) {
            roomIds.add(roomDTO.getRoomTypeId());
        }
        cmbRoom.setItems(roomIds);
    }

    private void setStudentComboBox() {
        List<StudentDTO> studentDTOS = reservationBO.getAllStudents();
        ObservableList<String> studentIds = FXCollections.observableArrayList();
        for (StudentDTO studentDTO : studentDTOS) {
            studentIds.add(studentDTO.getStudentId());
        }
        cmbStudent.setItems(studentIds);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if ((cmbStudent.getValue() != null || cmbRoom.getValue() != null || datePicker.getValue() != null) && (rbPaymentDone.isSelected() || rbPendingPayment.isSelected())){
            String status = null;
            if (rbPaymentDone.isSelected()){
                status = "payment done";
            }else if (rbPendingPayment.isSelected()){
                status = "pending payment";
            }

            ReservationDTO reservationDTO = new ReservationDTO();
            reservationDTO.setDate(datePicker.getValue());
            reservationDTO.setStatus(status);

            StudentDTO studentDTO = reservationBO.getStudent(cmbStudent.getValue());
            reservationDTO.setStudent(studentDTO);

            RoomDTO roomDTO = reservationBO.getRoom(cmbRoom.getValue());
            reservationDTO.setRoom(roomDTO);

            boolean isSaved = reservationBO.saveReservation(reservationDTO);
            if (isSaved){
                new SystemAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Reservation added successfully", ButtonType.OK).show();
                Stage stage = (Stage) cmbRoom.getScene().getWindow();
                stage.close();
                reservationFormController.populateReservationTable();
                reservationFormController.searchFilter();
            }else{
                new SystemAlert(Alert.AlertType.WARNING, "Warning", "Failed to add the reservation").show();
            }
        }else{
            new SystemAlert(Alert.AlertType.WARNING, "Warning", "Please fill all details").show();
        }
    }

    public void setReservationController(ReservationFormController reservationFormController) {
        this.reservationFormController = reservationFormController;
    }
}
