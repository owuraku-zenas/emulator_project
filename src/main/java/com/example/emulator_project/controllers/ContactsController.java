package com.example.emulator_project.controllers;

import com.example.emulator_project.utils.SceneManager;
import com.example.emulator_project.utils.Views;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ContactsController extends NavigationController {

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
        FXMLLoader fx = sm.switchSceneFromEvent(event, Views.CALL_VIEW);
        ((CallViewController) fx.getController()).setCallerID(dialInputField.getText());
        sm.load();

    }
}
