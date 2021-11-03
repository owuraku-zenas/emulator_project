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
    public Button deleteCharButton;
    @FXML
    private TextField dialInputField;

    private final SceneManager sm = SceneManager.getInstance();

    public void handleButtonClick(ActionEvent event) {
        Button button = (Button) event.getTarget();
        String inputString = dialInputField.getText() + button.getText();

        if (dialInputField != null) {
            dialInputField.setText(inputString);
            System.out.println(inputString);
        } else {
            assert dialInputField != null;
            System.out.println(dialInputField.toString() + " is null");
        }
    }

    public void makePhoneCall(ActionEvent event) {
        if (this.dialInputField.getText().equals("")) return;

        FXMLLoader fx = sm.switchSceneFromEvent(event, ViewType.CALL_VIEW);
        ((CallViewController) fx.getController()).makePhoneCall(dialInputField.getText());
        sm.loadScene();
    }

    public void deleteOneCharacter() {
        if (!dialInputField.getText().equals("")) {
            int s = dialInputField.getText().length();
            dialInputField.setText(dialInputField.getText().substring(0, s - 1));
        }
    }
}
