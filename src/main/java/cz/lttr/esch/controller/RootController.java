package cz.lttr.esch.controller;

import cz.lttr.esch.App;
import javafx.fxml.FXML;
import javafx.scene.Parent;

/**
 * Created by Lukas Trumm on 23.09.2016
 */
public class RootController {

    @FXML
    Parent instructorList;

    @FXML
    InstructorListController instructorListController;

    public void initialize() {

    }

    public void setApp(App app) {
        instructorListController.setApp(app);
    }
}
