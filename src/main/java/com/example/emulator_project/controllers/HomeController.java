package com.example.emulator_project.controllers;

import com.example.emulator_project.utils.SceneManager;
import com.example.emulator_project.utils.ViewType;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class HomeController extends NavigationController {
    private final SceneManager sm = SceneManager.getInstance();

    @FXML
    public void openTimeView(MouseEvent mouseEvent) {
        sm.switchSceneFromEvent(mouseEvent, ViewType.TIME_VIEW);
        sm.load();
    }

    @FXML
    public void openCalendarView(MouseEvent mouseEvent) {
        sm.switchSceneFromEvent(mouseEvent, ViewType.CALENDAR_VIEW);
        sm.load();
    }

    @FXML
    public void openCallView(MouseEvent mouseEvent) {
        sm.switchSceneFromEvent(mouseEvent, ViewType.CONTACT_VIEW);
        sm.load();
    }

    @FXML
    public void openSMSView(MouseEvent mouseEvent) {
        sm.switchSceneFromEvent(mouseEvent, ViewType.SMS_VIEW);
        sm.load();
    }

    @FXML
    public void openMenuView(MouseEvent mouseEvent) {
        sm.switchSceneFromEvent(mouseEvent, ViewType.MENU_VIEW);
        sm.load();
    }
}
