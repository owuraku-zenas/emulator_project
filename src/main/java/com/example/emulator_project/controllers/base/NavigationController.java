package com.example.emulator_project.controllers.base;

import com.example.emulator_project.utils.SceneManager;
import com.example.emulator_project.utils.ViewType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NavigationController {
    private final SceneManager sm = SceneManager.getInstance();

    @FXML
    protected Label notificationBarTime;

    @FXML
    public void initialize() {
        updateNotificationTimerLabel(null);
        startTime();
    }

    public void startTime() {
        System.out.println("Timer Started On for Notification");
        // USING ANIMATION CLASS TO CREATE THE TIMER
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), this::updateNotificationTimerLabel));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateNotificationTimerLabel(ActionEvent event) {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss a");
        LocalDateTime now = LocalDateTime.now();
        String time = timeFormat.format(now);
        notificationBarTime.setText(time);
    }


    @FXML
    public void openTaskView(ActionEvent event) {
        System.out.println("Opening Task View");
//        sm.switchSceneFromEvent(event, ViewType.TASK_VIEW);
//        sm.loadScene();
    }

    @FXML
    public void goBack(ActionEvent event) {
        System.out.println("Opening Back View");
        sm.switchSceneFromEvent(event, ViewType.HOME_VIEW);
        sm.loadScene();
    }

    @FXML
    public void openHome(ActionEvent event) {
        System.out.println("Opening Home View");
        sm.switchSceneFromEvent(event, ViewType.HOME_VIEW);
        sm.loadScene();
    }
}
