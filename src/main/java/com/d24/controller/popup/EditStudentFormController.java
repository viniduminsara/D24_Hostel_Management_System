package com.d24.controller.popup;

import com.d24.bo.custom.StudentBO;
import com.d24.bo.factory.BOFactory;
import com.d24.bo.factory.BOTypes;
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
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class EditStudentFormController {
    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXDatePicker datepicker;

    @FXML
    private Label lblId;

    @FXML
    private JFXRadioButton rbMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXRadioButton rbFemale;

    private StudentFormController studentFormController;

    StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBO(BOTypes.STUDENT);

    public void setStudentFormController(StudentFormController studentFormController) {
        this.studentFormController = studentFormController;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (!(txtName.getText().isEmpty() || txtAddress.getText().isEmpty() || txtContact.getText().isEmpty())){
            if (RegExPatterns.getContactPattern().matcher(txtContact.getText()).matches()) {
                String gender = null;
                if (rbMale.isSelected()) {
                    gender = rbMale.getText();
                } else if (rbFemale.isSelected()) {
                    gender = rbFemale.getText();
                }
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setStudentId(lblId.getText());
                studentDTO.setName(txtName.getText());
                studentDTO.setAddress(txtAddress.getText());
                studentDTO.setContactNo(txtContact.getText());
                studentDTO.setDob(datepicker.getValue());
                studentDTO.setGender(gender);

                boolean isUpdated = studentBO.updateStudent(studentDTO);
                if (isUpdated) {
                    new SystemAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Student update successful", ButtonType.OK).show();
                    Stage stage = (Stage) txtName.getScene().getWindow();
                    stage.close();
                    studentFormController.populateStudentTable();
                    studentFormController.searchFilter();
                } else {
                    new SystemAlert(Alert.AlertType.WARNING, "Warning", "Failed to update the student", ButtonType.OK).show();
                }

            }else {
                new SystemAlert(Alert.AlertType.WARNING, "Warning", "Please enter correct contact number").show();
            }
        }else{
            new SystemAlert(Alert.AlertType.WARNING,"Warning","Please fill all details", ButtonType.OK).show();
        }
    }

    public void setDetails(StudentDTO student){
        lblId.setText(student.getStudentId());
        txtName.setText(student.getName());
        txtAddress.setText(student.getAddress());
        txtContact.setText(student.getContactNo());
        datepicker.setValue(student.getDob());

        if (student.getGender().equals("Male")){
            rbMale.setSelected(true);
        }else if (student.getGender().equals("Female")){
            rbFemale.setSelected(true);
        }
    }
}
