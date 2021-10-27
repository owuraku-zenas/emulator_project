package com.example.emulator_project.controllers;

import com.example.emulator_project.utils.SceneManager;
import com.example.emulator_project.utils.ViewType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class NavigationController {
    private final SceneManager sm = SceneManager.getInstance();

    @FXML
    public void openTaskView(ActionEvent event) {
        System.out.println("Opening Task View");
        sm.switchSceneFromEvent(event, ViewType.TASK_VIEW);
        sm.load();
    }

    @FXML
    public void goBack(ActionEvent event) {
        System.out.println("Opening Back View");
        sm.switchSceneFromEvent(event, ViewType.HOME_VIEW);
        sm.load();
    }

    @FXML
    public void openHome(ActionEvent event) {
        System.out.println("Opening Home View");
        sm.switchSceneFromEvent(event, ViewType.HOME_VIEW);
        sm.load();
    }
}
