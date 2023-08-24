package com.d24.controller.popup;

import com.d24.bo.custom.StudentBO;
import com.d24.bo.custom.impl.StudentBOImpl;
import com.d24.dto.StudentDTO;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;

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

    @FXML
    private ToggleGroup gender1;

    StudentBO studentBO = new StudentBOImpl();

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (!(txtName.getText().isEmpty() || txtAddress.getText().isEmpty() || txtContact.getText().isEmpty())){
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setName(txtName.getText());
            studentDTO.setAddress(txtAddress.getText());
            studentDTO.setContactNo(txtContact.getText());
            studentDTO.setDob(datepicker.getValue());
            String gender = null;
            if (rbMale.isSelected()) {
                gender = rbMale.getText();
            } else if (rbFemale.isSelected()) {
                gender = rbFemale.getText();
            }


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
