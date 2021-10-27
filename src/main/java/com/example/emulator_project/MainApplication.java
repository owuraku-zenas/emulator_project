package com.example.emulator_project;

import com.example.emulator_project.api.SQLiteDBHandler;
import com.example.emulator_project.utils.SceneManager;
import com.example.emulator_project.utils.ViewType;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Android Phone");

        SQLiteDBHandler handler = SQLiteDBHandler.getInstance();
        handler.getConnection();
        handler.initialise();

        SceneManager sm = SceneManager.getInstance().setContext(MainApplication.class);
        sm.setStage(stage);
        sm.setSceneFromView(ViewType.HOME_VIEW);
        sm.load();

    }

    public static void main(String[] args) {
        launch();
    }
}