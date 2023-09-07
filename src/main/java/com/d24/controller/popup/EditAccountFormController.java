package com.d24.controller.popup;

import com.d24.bo.custom.AccountBO;
import com.d24.bo.custom.impl.AccountBOImpl;
import com.d24.controller.AccountFormController;
import com.d24.dto.UserDTO;
import com.d24.util.RegExPatterns;
import com.d24.util.SystemAlert;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class EditAccountFormController {

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXTextField txtFullname;

    @FXML
    private JFXTextField txtEmail;

    UserDTO userDTO = new UserDTO();

    AccountBO accountBO = new AccountBOImpl();

    AccountFormController accountFormController;

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (!(txtUsername.getText().isEmpty() || txtFullname.getText().isEmpty() || txtEmail.getText().isEmpty())){
            if (RegExPatterns.getEmailPattern().matcher(txtEmail.getText()).matches()){

                userDTO.setUsername(txtUsername.getText());
                userDTO.setFullName(txtFullname.getText());
                userDTO.setEmail(txtEmail.getText());

                boolean isUpdated = accountBO.updateDetails(userDTO);
                if (isUpdated){
                    new SystemAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Account details updated", ButtonType.OK).show();
                    Stage stage = (Stage) txtUsername.getScene().getWindow();
                    stage.close();
                    accountFormController.refreshUser();
                }else{
                    new SystemAlert(Alert.AlertType.WARNING, "Warning", "Failed to update the details").show();
                }
            }else{
                new SystemAlert(Alert.AlertType.WARNING, "Warning", "Invalid email address.").show();
            }
        }else{
            new SystemAlert(Alert.AlertType.WARNING, "Warning", "Please fill all details").show();
        }
    }

    public void setUserDTO(UserDTO userDTO){
        this.userDTO = userDTO;
    }

    public void setDetails(){
        txtFullname.setText(userDTO.getFullName());
        txtUsername.setText(userDTO.getUsername());
        txtEmail.setText(userDTO.getEmail());
    }

    public void setAccountFormController(AccountFormController accountFormController){
        this.accountFormController = accountFormController;
    }
}
