package cz.lttr.esch.controller;

import cz.lttr.esch.message.UserMessageUtils;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * Created by Lukas Trumm on 23.09.2016
 */
public class RootController {

    @FXML private BorderPane rootPane;
    @FXML private Label userMessageLabel;
    @FXML private Parent instructorListTab;
    @FXML private InstructorListController instructorListController;


    public void initialize() {
        UserMessageUtils.setUserMessageLabel(userMessageLabel);
    }

}
