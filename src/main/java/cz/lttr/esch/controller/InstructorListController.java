package cz.lttr.esch.controller;

import cz.lttr.esch.App;
import cz.lttr.esch.model.Instructor;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * Created by Lukas Trumm on 08.08.2016
 */
public class InstructorListController {

    public AnchorPane instructorList;

    @FXML private TableView<Instructor> instructorTable;

    @FXML private TableColumn<Instructor, String> nickNameColumn;
    @FXML private TableColumn<Instructor, String> firstNameColumn;
    @FXML private TableColumn<Instructor, String> lastNameColumn;

    @FXML private TextField nickNameField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;

    private App app;

    public InstructorListController() {
    }

    @FXML
    private void initialize() {
        nickNameColumn.setCellValueFactory(cellData -> cellData.getValue().nickNameProperty());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        showInstructorDetails(null);

        instructorTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showInstructorDetails(newValue));
    }

    public void setApp(App app) {
        this.app = app;

        instructorTable.setItems(app.getInstructors());
    }

    private void showInstructorDetails(Instructor instructor) {
        if (instructor == null) {
            nickNameField.setText("");
            firstNameField.setText("");
            lastNameField.setText("");
        } else {
            nickNameField.setText(instructor.getNickName());
            firstNameField.setText(instructor.getFirstName());
            lastNameField.setText(instructor.getLastName());
        }
    }
}
