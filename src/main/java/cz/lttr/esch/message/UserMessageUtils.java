package cz.lttr.esch.message;

import javafx.scene.control.Label;

/**
 * Created by Lukas Trumm on 08.10.2016
 */
public class UserMessageUtils {

    private static Label userMessageLabel;

    public static void setUserMessageLabel(Label label) {
        userMessageLabel = label;
    }

    public static void showMessageToUser(String text) {
        userMessageLabel.setText(text);
    }
}
