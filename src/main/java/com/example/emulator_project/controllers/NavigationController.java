package com.example.emulator_project.controllers;

import com.example.emulator_project.utils.SceneManager;
import com.example.emulator_project.utils.Views;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class NavigationController {
    private SceneManager sm = SceneManager.getInstance();

    @FXML
    public void openTaskView(ActionEvent event) {
        System.out.println("Opening Task View");
        sm.switchSceneFromEvent(event, Views.TASK_VIEW);
    }

    @FXML
    public void goBack(ActionEvent event) {
        System.out.println("Opening Back View");
        sm.switchSceneFromEvent(event, Views.HOME_VIEW);
    }

    @FXML
    public void openHome(ActionEvent event) {
        System.out.println("Opening Home View");
        sm.switchSceneFromEvent(event, Views.HOME_VIEW);
    }
}
