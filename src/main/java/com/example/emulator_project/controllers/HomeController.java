package com.example.emulator_project.controllers;

import com.example.emulator_project.controllers.base.NavigationController;
import com.example.emulator_project.utils.SceneManager;
import com.example.emulator_project.utils.TabType;
import com.example.emulator_project.utils.ViewType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomeController extends NavigationController {
    private final SceneManager sm = SceneManager.getInstance();

    @FXML
    private Label clockLabel;

    @FXML
    public void initialize() {
        updateClockLabel(null);
        updateNotificationTimeLabel(null);
        startClock();
    }


    public void startClock() {
        // USING ANIMATION CLASS TO CREATE THE TIMER
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), this::updateClockLabel), new KeyFrame(Duration.millis(1000), this::updateNotificationTimeLabel));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateNotificationTimeLabel(ActionEvent event) {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss a");
        LocalDateTime now = LocalDateTime.now();
        String time = timeFormat.format(now);
        notificationBarTime.setText(time);
    }

    private void updateClockLabel(ActionEvent event) {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss a");
        LocalDateTime now = LocalDateTime.now();
        String time = timeFormat.format(now);
        clockLabel.setText(time);
    }


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
