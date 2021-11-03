package com.example.emulator_project.controllers.tabs;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AlarmTimeController {

    private DateTimeFormatter timeFormat;
    private LocalDateTime now;
    private String time;

    @FXML
    private Button currentTime;

    public AlarmTimeController() {
        // Get system time
    }

    @FXML
    public void initialize() {
        updateTimerLabel(null);
        startTime();
//        startAnotherTimer();
    }


    public void startTime() {
        // FIXME USING JAVA.UTIL TIMER AND TIMER-TASK TO CREATE THE TIMER
//        Timer timer = new Timer();
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                // Get system time
//                DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
//                LocalDateTime now = LocalDateTime.now();
//                String time = timeFormat.format(now);
//
//                currentTime.setText(time);
//                  System.out.println(time);
//            }
//        };
//
//          timer.schedule(task, 0, 1000);
//        new Thread(task).start();

        // USING ANIMATION CLASS TO CREATE THE TIMER
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), this::updateTimerLabel));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateTimerLabel(ActionEvent event) {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss a");
        LocalDateTime now = LocalDateTime.now();
        String time = timeFormat.format(now);
        currentTime.setText(time);
    }

    public void startAnotherTimer() {
        /* USING JAVA_FX SCHEDULED_SERVICE CLASS TO RUN BACKGROUND TASK AND UPDATE UI */
        ScheduledService<String> timeService = new ScheduledService<String>() {
            @Override
            protected Task<String> createTask() {

                return new Task<String>() {

                    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss a");
                    LocalDateTime now = LocalDateTime.now();
                    String time = timeFormat.format(now);

                    @Override
                    protected String call() throws Exception {
//                        currentTime.setText(time);
                        return time;
                    }
                };
            }
        };

        timeService.setPeriod(Duration.millis(1000));
        timeService.start();
        timeService.setOnSucceeded(event -> currentTime.setText(timeService.getLastValue()));
    }

}


