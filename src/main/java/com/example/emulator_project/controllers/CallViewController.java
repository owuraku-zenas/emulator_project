package com.example.emulator_project.controllers;

import com.example.emulator_project.controllers.base.NavigationController;
import com.example.emulator_project.db_api.SQLiteDBHandler;
import com.example.emulator_project.utils.SceneManager;
import com.example.emulator_project.utils.TabType;
import com.example.emulator_project.utils.ViewType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class CallViewController extends NavigationController {
    private final SceneManager sm = SceneManager.getInstance();
    private final SQLiteDBHandler db = SQLiteDBHandler.getInstance();

    @FXML
    private Label callerID;

    public CallViewController() {
        db.getConnection();
    }

    @FXML
    public void setCallerID(String callerID) {
        this.callerID.setText(callerID);
    }

    @FXML
    public void endPhoneCall() {
        // End Call Thread
        FXMLLoader fx = sm.setSceneFromView(ViewType.CONTACT_VIEW);
        // Switch View to Logs
        ((PhoneController) fx.getController()).switchTab(TabType.CALL_LOG_TAB);
        // Append Caller Id (Scene) to recents UI
        // Get the call logs scene
//        CallLogTabController clt = sm.getCurrentScene().

        // Add caller Id to call logs DB
        sm.loadScene();
    }

    @FXML
    public void makePhoneCall(String callerId) {
        // -- get system time and date
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        String time = timeFormat.format(now);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("E, MMM d yyyy");
        String date = dateFormat.format(now);

        // -- finally add call log
        String[] calls = {"missed", "dialed"};
        Random randomNumbers = new Random();

        db.insertCallLog(callerId, date, time, calls[randomNumbers.nextInt(2)]);

        // Check if contact is saved and display name instead of number
        String name = db.findContact(callerId);
        callerId = (name == null || (name.contentEquals(""))) ? callerId : name;

        this.setCallerID(callerId);
    }
}
