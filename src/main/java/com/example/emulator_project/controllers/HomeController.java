package com.example.emulator_project.controllers;

import com.example.emulator_project.controllers.base.NavigationController;
import com.example.emulator_project.utils.SceneManager;
import com.example.emulator_project.utils.ViewType;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class HomeController extends NavigationController {
    private final SceneManager sm = SceneManager.getInstance();

    @FXML
    public void openTimeView(MouseEvent mouseEvent) {
        sm.switchSceneFromEvent(mouseEvent, ViewType.TIME_VIEW);
        sm.loadScene();
    }

    @FXML
    public void openCalendarView(MouseEvent mouseEvent) {
        sm.switchSceneFromEvent(mouseEvent, ViewType.CALENDAR_VIEW);
        sm.loadScene();
    }

    @FXML
    public void openCallView(MouseEvent mouseEvent) {
        sm.switchSceneFromEvent(mouseEvent, ViewType.CONTACT_VIEW);
        sm.loadScene();
    }

    @FXML
    public void openSMSView(MouseEvent mouseEvent) {
        sm.switchSceneFromEvent(mouseEvent, ViewType.SMS_VIEW);
        sm.loadScene();
    }

    @FXML
    public void openMenuView(MouseEvent mouseEvent) {
        sm.switchSceneFromEvent(mouseEvent, ViewType.MENU_VIEW);
        sm.loadScene();
    }
}
