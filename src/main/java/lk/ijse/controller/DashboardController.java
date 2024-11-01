package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class DashboardController {

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private AnchorPane dashboardForm;

    @FXML
    private Label lblStudentCount;

    @FXML
    private Label lblTotalPrograms;

    @FXML
    private Label lblTotalStudent;

    @FXML
    private TableView<?> tblStudyAll;

}
