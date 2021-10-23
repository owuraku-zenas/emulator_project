package com.example.emulator_project;

import com.example.emulator_project.utils.Views;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = SceneManager.getInstance().loadScene(Views.CALL_VIEW);
        stage.setScene(scene);
        stage.setTitle("Android Phone");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}