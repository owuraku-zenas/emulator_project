package com.example.emulator_project.controllers.tabs;

import com.example.emulator_project.controllers.CallViewController;
import com.example.emulator_project.utils.SceneManager;
import com.example.emulator_project.utils.ViewType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DialerTabController {
    @FXML
    private TextField dialInputField;

    private SceneManager sm = SceneManager.getInstance();

    public void handleButtonClick(ActionEvent event) {
        Button button = (Button) event.getTarget();
        String inputString = dialInputField.getText() + button.getText();
        if (dialInputField != null) {
            dialInputField.setText(inputString);
            System.out.println(inputString);
        } else {
            System.out.println(dialInputField.toString() + " is null");
        }
    }

    public void makePhoneCall(ActionEvent event) {
        FXMLLoader fx = sm.switchSceneFromEvent(event, ViewType.CALL_VIEW);
        ((CallViewController) fx.getController()).setCallerID(dialInputField.getText());
        sm.loadScene();
    }

}
