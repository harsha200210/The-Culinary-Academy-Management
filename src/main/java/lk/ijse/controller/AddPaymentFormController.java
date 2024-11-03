package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.AddPaymentBO;
import lk.ijse.tdm.ViewAllTm;

public class AddPaymentFormController {

    @FXML
    private AnchorPane addPaymentForm;

    @FXML
    private TextField txtPayment;

    @FXML
    private TextField txtProgram;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtStudentName;

    AddPaymentBO addPaymentBO = (AddPaymentBO) BOFactory.getBO(BOFactory.BOType.ADDPAYMENT);

    public void initialize(ViewAllTm selectedItem, String programName) {
        txtStudentId.setText(selectedItem.getStudentId());
        txtStudentName.setText(selectedItem.getStudentName());
        txtProgram.setText(programName);
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Stage stage = (Stage) addPaymentForm.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        addPaymentBO.updateEnrollment(txtStudentId.getText().trim(),txtProgram.getText().trim(),Double.parseDouble(txtPayment.getText().trim()));
        new Alert(Alert.AlertType.CONFIRMATION,"Payment successfully added!").show();
        btnCancelOnAction(event);
    }

}
