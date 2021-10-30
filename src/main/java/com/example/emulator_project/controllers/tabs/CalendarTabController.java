package com.example.emulator_project.controllers.tabs;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

public class CalendarTabController {
    @FXML
    private DatePicker datePicker;


    @FXML
    public void initialize() {
        datePicker.setVisible(true);
        datePicker.show();
    }

}
