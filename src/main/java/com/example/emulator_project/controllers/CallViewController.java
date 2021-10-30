package com.example.emulator_project.controllers;

import com.example.emulator_project.controllers.base.NavigationController;
import com.example.emulator_project.utils.SceneManager;
import com.example.emulator_project.utils.TabType;
import com.example.emulator_project.utils.ViewType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

public class CallViewController extends NavigationController {
    private final SceneManager sm = SceneManager.getInstance();
    @FXML
    private Label callerID;

    @FXML
    public void setCallerID(String callerID) {
        this.callerID.setText(callerID);
    }

    @FXML
    public void endPhoneCall() {
        // End Call Thread

        FXMLLoader fx = sm.setSceneFromView(ViewType.CONTACT_VIEW);
        // Switch View to Logs
        ((ContactsController) fx.getController()).switchTab(TabType.CALL_LOG_TAB);
        // Append Caller Id (Scene) to recents UI
        sm.loadScene();

        // Add caller Id to call logs DB


    }
}
