package com.example.emulator_project.controllers;

import com.example.emulator_project.utils.SceneManager;
import com.example.emulator_project.utils.Views;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class HomeController extends NavigationController {
    private SceneManager sm = SceneManager.getInstance();


    @FXML
    public void openTimeView(MouseEvent mouseEvent) {
        sm.switchSceneFromEvent(mouseEvent, Views.TIME_VIEW);
    }

    @FXML
    public void openCalendarView(MouseEvent mouseEvent) {
        sm.switchSceneFromEvent(mouseEvent, Views.CALENDAR_VIEW);
    }

    @FXML
    public void openCallView(MouseEvent mouseEvent) {
        sm.switchSceneFromEvent(mouseEvent, Views.CONTACT_VIEW);
    }

    @FXML
    public void openSMSView(MouseEvent mouseEvent) {
        sm.switchSceneFromEvent(mouseEvent, Views.SMS_VIEW);
    }

    @FXML
    public void openMenuView(MouseEvent mouseEvent) {
        sm.switchSceneFromEvent(mouseEvent, Views.MENU_VIEW);
    }

}
