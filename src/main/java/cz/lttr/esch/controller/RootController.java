package cz.lttr.esch.controller;

import cz.lttr.esch.App;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 * Created by Lukas Trumm on 23.09.2016
 */
public class RootController {

    public BorderPane rootPane;

    @FXML
    Parent instructorList;

    @FXML
    private InstructorListController instructorListController;

    public void initialize() {

    }

    public void setApp(App app) {
        instructorListController.setApp(app);
    }
}
