package com.example.emulator_project;

import com.example.emulator_project.utils.SceneManager;
import com.example.emulator_project.utils.Views;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Android Phone");
        SceneManager sm = SceneManager.getInstance().setContext(MainApplication.class);
        sm.setStage(stage);
        sm.setSceneFromView(Views.HOME_VIEW);
        sm.load();
    }

    public static void main(String[] args) {
        launch();
    }
}