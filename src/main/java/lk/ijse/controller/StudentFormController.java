package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.db.FactoryConfiguration;
import lk.ijse.dto.CulinaryProgramDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Enrollment;
import lk.ijse.entity.Student;
import lk.ijse.tdm.ProgramTm;
import lk.ijse.tdm.StudentTm;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colProgram;

    @FXML
    private TableColumn<?, ?> colRegisterDate;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private CheckBox noCheckBox;

    @FXML
    private ChoiceBox<String> programChoiceBox;

    @FXML
    private DatePicker registerDatePicker;

    @FXML
    private AnchorPane studentForm;

    @FXML
    private TableView<StudentTm> tblStudent;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtInstallment;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    @FXML
    private Pane visiblePane;

    @FXML
    private CheckBox yesCheckBox;

    StudentBO studentBO = (StudentBO) BOFactory.getBO(BOFactory.BOType.STUDENT);

    public void initialize() {
        visiblePane.setVisible(false);
        setCellValueFactory();
        loadAllStudent();
        setChoiceBoxData();
    }

    private void loadAllStudent() {
        List<StudentDTO> allStudent = studentBO.getAllStudent();
        tblStudent.getItems().clear();
        ObservableList<StudentTm> studentTms = tblStudent.getItems();
        for (StudentDTO studentDTO : allStudent) {
            studentTms.add(new StudentTm(studentDTO.getStudentId(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getTel(),studentDTO.getRegistrationDate(),createButton()));
        }
        tblStudent.setItems(studentTms);
    }

    private Button createButton(){
        Button button = new Button("ADD");
        button.setStyle("-fx-background-color: blue;");

        button.setOnAction((e) -> {
            System.out.println("okay");
        });

        return button;
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colRegisterDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
    }

    private void setChoiceBoxData(){
        List<CulinaryProgramDTO> program = studentBO.getAllCulinaryProgram();
        ObservableList<String> programNames = FXCollections.observableArrayList();

        for (CulinaryProgramDTO programDTO : program){
            programNames.add(programDTO.getProgramName());
        }
        programChoiceBox.setItems(programNames);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearData();
    }

    private void clearData(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtTel.clear();
        registerDatePicker.setValue(LocalDate.parse(""));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        studentBO.deleteStudent(studentBO.getStudent(txtId.getText().trim()));
        loadAllStudent();
        clearData();
    }

    private StudentDTO getObject(){
        return new StudentDTO(txtId.getText(),txtName.getText(),txtAddress.getText(),Long.parseLong(txtTel.getText()), Date.valueOf(registerDatePicker.getValue()),new ArrayList<>());
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (noCheckBox.isSelected()){
            studentBO.saveStudent(getObject());
        } else {
            studentBO.saveStudentWithProgram(getObject(),programChoiceBox.getValue(),Double.parseDouble(txtInstallment.getText()));
        }
        clearData();
        loadAllStudent();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        studentBO.updateStudent(getObject());
        clearData();
        loadAllStudent();
    }

    @FXML
    void tblStudentOnClickAction(MouseEvent event) {
        StudentTm selectedItem = tblStudent.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtId.setText(selectedItem.getStudentId());
            txtName.setText(selectedItem.getName());
            txtAddress.setText(selectedItem.getAddress());
            txtTel.setText(String.valueOf(selectedItem.getTel()));
            registerDatePicker.setValue(selectedItem.getRegistrationDate().toLocalDate());
        }
    }

    @FXML
    void noCheckBoxOnAction(ActionEvent event) {
        noCheckBox.setSelected(true);
        yesCheckBox.setSelected(false);
        visiblePane.setVisible(false);
    }

    @FXML
    void yesCheckBoxOnAction(ActionEvent event) {
        yesCheckBox.setSelected(true);
        noCheckBox.setSelected(false);
        visiblePane.setVisible(true);
    }


    @FXML
    void txtAddressOnAction(ActionEvent event) {
        txtTel.requestFocus();
    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        txtName.requestFocus();
    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        txtAddress.requestFocus();
    }

}
