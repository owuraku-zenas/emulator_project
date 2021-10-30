package com.example.emulator_project.controllers;

import com.example.emulator_project.controllers.base.NavigationController;
import com.example.emulator_project.utils.TabType;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TimeCalendarController extends NavigationController {
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab calendarTab;
    @FXML
    private Tab timeTab;

    public void switchTab(TabType tab) {
        if (tab == TabType.CALENDAR_TAB) {
            tabPane.getSelectionModel().select(calendarTab);
        } else if (tab == TabType.ALARM_TIME_TAB) {
            tabPane.getSelectionModel().select(timeTab);
        }
    }
}
