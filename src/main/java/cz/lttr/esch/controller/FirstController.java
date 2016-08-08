package cz.lttr.esch.controller;

import cz.lttr.esch.App;
import cz.lttr.esch.model.Instructor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by Lukas Trumm on 08.08.2016
 */
public class FirstController {

    @FXML private TableView<Instructor> instructorTable;

    @FXML private TableColumn<Instructor, String> nickNameColumn;
    @FXML private TableColumn<Instructor, String> firstNameColumn;
    @FXML private TableColumn<Instructor, String> lastNameColumn;

    @FXML private Label nickNameLabel;
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;

    private App app;

    public FirstController() {
    }

    @FXML
    private void initialize() {
        nickNameColumn.setCellValueFactory(cellData -> cellData.getValue().nickNameProperty());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    public void setApp(App app) {
        this.app = app;

        instructorTable.setItems(app.getInstructors());
    }
}
