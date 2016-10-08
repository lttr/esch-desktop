package cz.lttr.esch.controller;

import cz.lttr.esch.dao.InMemoryIntructorDao;
import cz.lttr.esch.message.UserMessageUtils;
import cz.lttr.esch.model.Instructor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    @FXML private Label editInstructorLabel;
    @FXML private Label errorInstructorLabel;

    private InMemoryIntructorDao dao;
    private boolean addingNewInstructor = false;

    public InstructorListController() {
    }

    @FXML
    private void initialize() {
        dao = new InMemoryIntructorDao();
        instructorTable.setItems(dao.getInstructors());

        nickNameColumn.setCellValueFactory(cellData -> cellData.getValue().nickNameProperty());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        showInstructorDetails(null);

        handleSelection();
    }

    private void handleSelection() {
        instructorTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    showInstructorDetails(newValue);
                    if (newValue != null) { // something has been selected
                        enableInputFields();
                        editInstructorLabel.setText("Editing " + newValue.getNickName());
                    }
                    clearInvalidInputMessage();
                });
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

    @FXML
    private void handleDelete() {
        int selectionIndex = instructorTable.getSelectionModel().getSelectedIndex();
        if (selectionIndex >= 0) {
            Instructor removed = instructorTable.getItems().remove(selectionIndex);
            UserMessageUtils.showMessageToUser("Instructor " + removed.getNickName() + " has been deleted.");
            clearInvalidInputMessage();
        } else {
            UserMessageUtils.showMessageToUser("An existing instructor has to be selected in other to delete one.");
        }
    }

    @FXML
    public void handleNew(ActionEvent actionEvent) {
        instructorTable.getSelectionModel().clearSelection();
        showInstructorDetails(null);
        addingNewInstructor = true;
        editInstructorLabel.setText("Adding new instructor");
        enableInputFields();
        clearInvalidInputMessage();
    }

    @FXML
    public void handleSave(ActionEvent actionEvent) {
        Instructor newInstructor;
        if (addingNewInstructor) {
            if (isInputValid()) {
                newInstructor = new Instructor(nickNameField.getText(), firstNameField.getText(), lastNameField.getText());
                instructorTable.getItems().add(newInstructor);
                instructorTable.getSelectionModel().select(newInstructor);
                addingNewInstructor = false;

                UserMessageUtils.showMessageToUser("Instructor " + newInstructor.getNickName() + " has been saved.");
                clearInvalidInputMessage();
            }
        } else {
            int selectionIndex = instructorTable.getSelectionModel().getSelectedIndex();
            if (selectionIndex >= 0) {
                Instructor selectedInstructor = instructorTable.getItems().get(selectionIndex);
                if (isInputValid()) {
                    selectedInstructor.setNickName(nickNameField.getText());
                    selectedInstructor.setFirstName(firstNameField.getText());
                    selectedInstructor.setLastName(lastNameField.getText());
                    UserMessageUtils.showMessageToUser("Instructor " + selectedInstructor.getNickName() + " has been saved.");
                    clearInvalidInputMessage();
                }
            } else {
                UserMessageUtils.showMessageToUser("An instructor has to be selected in other to be saved.");
            }
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";
        boolean nickNameIsMissing = false;
        boolean nickNameAlreadyExists = false;

        if (nickNameField.getText() == null || nickNameField.getText().length() == 0) {
            nickNameIsMissing = true;
        }

        if (!nickNameIsMissing) {
            nickNameAlreadyExists = instructorTable.getItems().stream()
                    .anyMatch(i -> nickNameField.getText().equals(i.getNickName()));
        }

        if (nickNameIsMissing) {
            invalidInputMessage("Nickname is required!");
            return false;
        } else if (nickNameAlreadyExists) {
            invalidInputMessage("Nickname already exists!");
            return false;
        } else {
            return true;
        }
    }

    private void enableInputFields() {
        nickNameField.setDisable(false);
        firstNameField.setDisable(false);
        lastNameField.setDisable(false);
    }

    private void invalidInputMessage(String errorMessage) {
        errorInstructorLabel.setText(errorMessage);
    }

    private void clearInvalidInputMessage() {
        errorInstructorLabel.setText("");
    }
}
