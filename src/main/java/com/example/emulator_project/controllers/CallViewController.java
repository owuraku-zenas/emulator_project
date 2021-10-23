package com.example.emulator_project.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CallViewController extends NavigationController {
    @FXML
    private Label callerID;

    @FXML
    public void setCallerID(String callerID) {
        this.callerID.setText(callerID);
    }
}
