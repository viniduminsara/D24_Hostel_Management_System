package com.d24.controller;

import com.d24.bo.custom.HomeBO;
import com.d24.bo.custom.impl.HomeBOImpl;
import com.d24.dto.StudentDTO;
import com.d24.tm.PendingPaymentTM;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class HomeformController {

    @FXML
    private Label lblStudents;

    @FXML
    private Label lblRooms;

    @FXML
    private Label lblReservation;

    @FXML
    private Label lblGreet;

    @FXML
    private TableView<PendingPaymentTM> tblPendingPayment;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblDate;

    HomeBO homeBO = new HomeBOImpl();

    public void initialize(){
        //set Value factory to table
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        setGreeting();
        loadStudentCount();
        loadRoomsCount();
        loadReservationCount();
        loadDateAndTime();
        populatePendingPaymentTable();
    }

    private void setGreeting() {
        int currentHour = LocalTime.now().getHour();

        String timePeriod;
        if (currentHour >= 5 && currentHour < 12) {
            timePeriod = "Morning";
        } else if (currentHour >= 12 && currentHour < 17) {
            timePeriod = "Afternoon";
        } else if (currentHour >= 17 && currentHour < 21) {
            timePeriod = "Evening";
        } else {
            timePeriod = "night";
        }
        lblGreet.setText("Good "+timePeriod);

    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(format.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e ->{
            DateTimeFormatter format2 = DateTimeFormatter.ofPattern("HH:mm:ss");
            lblTime.setText(LocalTime.now().format(format2));
        }), new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void populatePendingPaymentTable() {
        List<StudentDTO> studentDTOS = homeBO.getPendingPayments();
        ObservableList<PendingPaymentTM> pendingPaymentTMS = FXCollections.observableArrayList();
        for (StudentDTO studentDTO : studentDTOS) {
            pendingPaymentTMS.add(new PendingPaymentTM(studentDTO.getStudentId(),
                    studentDTO.getName(),studentDTO.getContactNo()));
        }
        tblPendingPayment.setItems(pendingPaymentTMS);
    }

    private void loadReservationCount() {
        String reservationCount = homeBO.getReservationCount();
        lblReservation.setText(reservationCount);
    }

    private void loadRoomsCount() {
        String roomCount = homeBO.getRoomCount();
        lblRooms.setText(roomCount);
    }

    private void loadStudentCount() {
        String studentCount = homeBO.getStudentCount();
        lblStudents.setText(studentCount);
    }
}
