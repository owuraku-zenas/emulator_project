package com.example.emulator_project.controllers;

import com.example.emulator_project.controllers.base.NavigationController;
import com.example.emulator_project.utils.SceneManager;
import com.example.emulator_project.utils.TabType;
import com.example.emulator_project.utils.ViewType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;

public class HomeController extends NavigationController {
    private final SceneManager sm = SceneManager.getInstance();

    @FXML
    public void openTimeView(MouseEvent mouseEvent) {
        FXMLLoader fx = sm.switchSceneFromEvent(mouseEvent, ViewType.TIME_VIEW);
        TimeCalendarController tController = fx.getController();
        tController.switchTab(TabType.ALARM_TIME_TAB);
        sm.loadScene();
    }

    @FXML
    public void openCalendarView(MouseEvent mouseEvent) {
        FXMLLoader fx = sm.switchSceneFromEvent(mouseEvent, ViewType.TIME_VIEW);
        TimeCalendarController tController = fx.getController();
        tController.switchTab(TabType.CALENDAR_TAB);
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
