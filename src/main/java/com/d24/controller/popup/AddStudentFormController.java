package com.d24.controller.popup;

import com.d24.bo.custom.StudentBO;
import com.d24.bo.custom.impl.StudentBOImpl;
import com.d24.controller.StudentFormController;
import com.d24.dto.StudentDTO;
import com.d24.util.RegExPatterns;
import com.d24.util.SystemAlert;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class AddStudentFormController {

    @FXML
    private JFXTextField txtStudentId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXDatePicker datepicker;

    @FXML
    private JFXRadioButton rbMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXRadioButton rbFemale;

    private StudentFormController studentFormController;

    StudentBO studentBO = new StudentBOImpl();

    public void setStudentFormController(StudentFormController studentFormController) {
        this.studentFormController = studentFormController;
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (!(txtStudentId.getText().isEmpty() || txtName.getText().isEmpty() || txtContact.getText().isEmpty() || txtAddress.getText().isEmpty())){
            if (RegExPatterns.getContactPattern().matcher(txtContact.getText()).matches()) {
                String gender = null;
                if (rbMale.isSelected()) {
                    gender = rbMale.getText();
                } else if (rbFemale.isSelected()) {
                    gender = rbFemale.getText();
                }

                boolean isExists = studentBO.existStudent(txtStudentId.getText());
                if (!isExists) {

                    StudentDTO studentDTO = new StudentDTO();
                    studentDTO.setStudentId(txtStudentId.getText());
                    studentDTO.setName(txtName.getText());
                    studentDTO.setAddress(txtAddress.getText());
                    studentDTO.setContactNo(txtContact.getText());
                    studentDTO.setDob(datepicker.getValue());
                    studentDTO.setGender(gender);

                    boolean isSaved = studentBO.saveStudent(studentDTO);

                    if (isSaved) {
                        new SystemAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Student saved successfully", ButtonType.OK).show();
                        Stage stage = (Stage) txtStudentId.getScene().getWindow();
                        stage.close();
                        studentFormController.populateStudentTable();
                        studentFormController.searchFilter();
                    } else {
                        new SystemAlert(Alert.AlertType.WARNING, "Warning", "Failed to save the student").show();
                    }

                }else{
                    new SystemAlert(Alert.AlertType.WARNING, "Warning", txtStudentId.getText()+" already exists").show();
                }
            }else{
                new SystemAlert(Alert.AlertType.WARNING, "Warning", "Please enter correct contact number").show();
            }
        }else {
            new SystemAlert(Alert.AlertType.WARNING, "Warning", "Please fill all details").show();
        }
    }
}
