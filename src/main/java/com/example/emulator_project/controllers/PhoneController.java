package com.example.emulator_project.controllers;

import com.example.emulator_project.controllers.base.NavigationController;
import com.example.emulator_project.utils.SceneManager;
import com.example.emulator_project.utils.TabType;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class PhoneController extends NavigationController {

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab dialerTab;
    @FXML
    private Tab contactsTab;
    @FXML
    private Tab callLogTab;

    private SceneManager sm = SceneManager.getInstance();


    public void switchTab(TabType tab) {
        if (tab == TabType.CALL_LOG_TAB) {
            tabPane.getSelectionModel().select(callLogTab);
        } else if (tab == TabType.CONTACT_SEARCH_TAB) {
            tabPane.getSelectionModel().select(contactsTab);
        } else if (tab == TabType.DIAL_PAD_TAB) {
            tabPane.getSelectionModel().select(dialerTab);
        }
    }

}
