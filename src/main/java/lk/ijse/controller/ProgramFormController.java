package lk.ijse.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ProgramBO;
import lk.ijse.db.FactoryConfiguration;
import lk.ijse.dto.CulinaryProgramDTO;
import lk.ijse.entity.CulinaryProgram;
import lk.ijse.tdm.ProgramTm;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ProgramFormController {

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colProgramName;

    @FXML
    private TableView<ProgramTm> tblProgram;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFee;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    ProgramBO programBO = (ProgramBO) BOFactory.getBO(BOFactory.BOType.PROGRAM);

    public void initialize() {
        setCellValueFactory();
        loadAllPrograms();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
    }

    private void loadAllPrograms(){
        List<CulinaryProgramDTO> programs = programBO.getAllCulinaryProgram();
        tblProgram.getItems().clear();
        ObservableList<ProgramTm> programTms = tblProgram.getItems();
        for (CulinaryProgramDTO program : programs) {
            String duration = convertDurationToString(program.getDuration());
            programTms.add(new ProgramTm(program.getProgramId(),program.getProgramName(),duration,program.getFee()));
        }
        tblProgram.setItems(programTms);
    }

    private String convertDurationToString(int duration){
        String durationString = "";

        if(duration > 11){
            int years = duration / 12;
            int months = duration % 12;

            if (months == 0){
                durationString = years + " years";
            } else {
                durationString = years + " years " + months + " months";
            }
        } else {
            durationString = duration + " months";
        }
        return durationString;
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearData();
    }

    private void clearData(){
        txtDuration.clear();
        txtFee.clear();
        txtId.clear();
        txtName.clear();
    }

    private CulinaryProgramDTO getObject(){
        return new CulinaryProgramDTO(txtId.getText(),txtName.getText(),Integer.parseInt(txtDuration.getText()),Double.parseDouble(txtFee.getText()),new ArrayList<>());
    }

    private int convertDurationToInt(String duration){
        int years = 0;
        int months = 0;

        // Split the input string by spaces to identify the number and unit
        String[] parts = duration.split(" ");

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equalsIgnoreCase("year") || parts[i].equalsIgnoreCase("years")) {
                years = Integer.parseInt(parts[i - 1]); // Get the value before "year" or "years"
            } else if (parts[i].equalsIgnoreCase("month") || parts[i].equalsIgnoreCase("months")) {
                months = Integer.parseInt(parts[i - 1]); // Get the value before "month" or "months"
            }
        }

        // Convert years to months and add to months
        return (years * 12) + months;
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        programBO.deleteCulinaryProgram(getObject());
        loadAllPrograms();
        clearData();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        programBO.saveCulinaryProgram(getObject());
        loadAllPrograms();
        clearData();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        programBO.updateCulinaryProgram(getObject());
        loadAllPrograms();
        clearData();
    }

    @FXML
    void tblProgramOnClickAction(MouseEvent event) {
        ProgramTm programTm = tblProgram.getSelectionModel().getSelectedItem();
        if (programTm != null) {
            txtId.setText(programTm.getId());
            txtName.setText(programTm.getProgramName());
            txtDuration.setText(String.valueOf(convertDurationToInt(programTm.getDuration().trim())));
            txtFee.setText(String.valueOf(programTm.getFee()));
        }
    }

    @FXML
    void txtDurationOnAction(ActionEvent event) {
        txtFee.requestFocus();
    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        txtName.requestFocus();
    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        txtDuration.requestFocus();
    }

}
