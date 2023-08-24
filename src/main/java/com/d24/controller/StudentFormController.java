package com.d24.controller;

import com.d24.bo.custom.StudentBO;
import com.d24.bo.custom.impl.StudentBOImpl;
import com.d24.controller.popup.AddStudentFormController;
import com.d24.controller.popup.EditStudentFormController;
import com.d24.dto.StudentDTO;
import com.d24.tm.StudentTM;
import com.d24.util.SystemAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StudentFormController {

    @FXML
    private TableView<StudentTM> tblStudent;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private JFXTextField txtSearch;

    StudentBO studentBO = new StudentBOImpl();

    public void initialize(){

        //set value factory for table
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("actionBtn"));

        populateStudentTable();
    }

    public void populateStudentTable() {
        try {
            List<StudentDTO> studentDTOS = studentBO.getAllStudents();
            ObservableList<StudentTM> studentTMS = FXCollections.observableArrayList();
            for (StudentDTO studentDTO : studentDTOS) {
                ImageView editImageView = new ImageView("/img/table/update.png");
                editImageView.setFitWidth(22); // Set the width to 22px
                editImageView.setFitHeight(22);
                JFXButton editBtn = new JFXButton("Edit",editImageView);
                editBtn.getStyleClass().add("EditBtn");
                editBtn.setPrefSize(80,40);
                setEditBtnAction(editBtn,studentDTO);
                ImageView removeImageView = new ImageView("/img/table/remove.png");
                removeImageView.setFitWidth(18); // Set the width to 22px
                removeImageView.setFitHeight(18);
                JFXButton removeBtn = new JFXButton("remove",removeImageView);
                removeBtn.getStyleClass().add("RemoveBtn");
                removeBtn.setPrefSize(80,40);
                setRemoveBtnAction(removeBtn,studentDTO);
                HBox hBox = new HBox(editBtn,removeBtn);
                hBox.setAlignment(Pos.CENTER);
                hBox.setSpacing(15);

                studentTMS.add(new StudentTM(studentDTO.getStudentId(),
                        studentDTO.getName(),studentDTO.getAddress(),
                        studentDTO.getContactNo(), studentDTO.getGender(),
                        hBox
                ));
            }
            tblStudent.setItems(studentTMS);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private void setRemoveBtnAction(JFXButton removeBtn, StudentDTO student) {
        removeBtn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No",ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new SystemAlert(Alert.AlertType.INFORMATION,"Information","Do you want to delete '"+student.getName()+"' student?",yes,no).showAndWait();

            if (result.orElse(no) == yes){
                //delete student
                try {
                    boolean isDeleted = studentBO.deleteStudent(student.getStudentId());
                    if (isDeleted){
                        new SystemAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Student deleted successfully", ButtonType.OK).show();
                        populateStudentTable();
                    }else{
                        new SystemAlert(Alert.AlertType.WARNING, "Warning", "Student delete unsuccessful").show();
                    }
                } catch (SQLException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void setEditBtnAction(JFXButton editBtn, StudentDTO student) {
        editBtn.setOnAction((e) -> {
            Stage stage = new Stage();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/popup/editStudentForm.fxml"));
                stage.setScene(new Scene(loader.load()));
                EditStudentFormController editStudentFormController = loader.getController();
                editStudentFormController.setDetails(student);
                editStudentFormController.setStudentFormController(this);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            stage.setTitle("Edit Student");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.centerOnScreen();
            stage.show();
        });
    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/popup/addStudentForm.fxml"));
        stage.setScene(new Scene(loader.load()));
        AddStudentFormController addStudentFormController = loader.getController();
        addStudentFormController.setStudentFormController(this);
        stage.setTitle("Add New Student");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.centerOnScreen();
        stage.show();
    }
}
