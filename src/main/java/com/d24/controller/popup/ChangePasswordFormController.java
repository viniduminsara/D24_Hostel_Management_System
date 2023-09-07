package com.d24.controller.popup;

import com.d24.bo.custom.AccountBO;
import com.d24.bo.factory.BOFactory;
import com.d24.bo.factory.BOTypes;
import com.d24.controller.AccountFormController;
import com.d24.dto.UserDTO;
import com.d24.util.SystemAlert;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class ChangePasswordFormController {

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtConfirmPassword;

    AccountBO accountBO = (AccountBO) BOFactory.getBOFactory().getBO(BOTypes.ACCOUNT);

    UserDTO userDTO;

    private AccountFormController accountFormController;

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (!(txtPassword.getText().isEmpty() || txtConfirmPassword.getText().isEmpty())){
            if (txtPassword.getText().equals(txtConfirmPassword.getText())){
                if (!userDTO.getPassword().equals(txtPassword.getText())){

                    userDTO.setPassword(txtPassword.getText());
                    boolean isUpdated = accountBO.updateDetails(userDTO);
                    if (isUpdated){
                        new SystemAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Password updated", ButtonType.OK).show();
                        Stage stage = (Stage) txtPassword.getScene().getWindow();
                        stage.close();
                        accountFormController.refreshUser();
                    }else{
                        new SystemAlert(Alert.AlertType.WARNING, "Warning", "Failed to update the password").show();
                    }

                }else{
                    new SystemAlert(Alert.AlertType.WARNING, "Warning", "same password").show();
                }
            }else{
                new SystemAlert(Alert.AlertType.WARNING, "Warning", "Passwords are not matching").show();
            }
        }else{
            new SystemAlert(Alert.AlertType.WARNING, "Warning", "Fill all the details").show();
        }
    }

    public void setAccountFormController(AccountFormController accountFormController){
        this.accountFormController = accountFormController;
    }

    public void setUser(UserDTO userDTO){
        this.userDTO = userDTO;
    }
}
