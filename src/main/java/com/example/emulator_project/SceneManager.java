package com.example.emulator_project;

import com.example.emulator_project.utils.Views;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneManager {

    private Scene currentScene;
    private static SceneManager sceneManager;

    private SceneManager() {

    }

    public static SceneManager getInstance() {
        if (sceneManager == null) {
            sceneManager = new SceneManager();
        }

        return sceneManager;
    }

    public Scene getCurrentScene() {
        return this.currentScene;
    }

    public Scene loadScene(Views view) {
        FXMLLoader nextFXML = null;
        Scene nextScene = null;
        try {

            if (view == Views.CALENDAR_VIEW) {
                nextFXML = new FXMLLoader(SceneManager.class.getResource("views/calendar-view.fxml"));
            } else if (view == Views.HOME_VIEW) {
                nextFXML = new FXMLLoader(SceneManager.class.getResource("views/home-view.fxml"));
            } else if (view == Views.CALL_VIEW) {
                nextFXML = new FXMLLoader(SceneManager.class.getResource("views/call-view.fxml"));
            } else if (view == Views.CONTACT_VIEW) {
                nextFXML = new FXMLLoader(SceneManager.class.getResource("views/contact-view.fxml"));
            } else {
                nextFXML = new FXMLLoader(SceneManager.class.getResource("views/home-view.fxml"));
            }

            this.setCurrentScene(new Scene(nextFXML.load(), 360, 680));

        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO check if current scene != next scene to be loaded

//        if (currentScene.equals(nextScene)) {
//            return currentScene;
//        }

        return this.getCurrentScene();
    }

    private void setCurrentScene(Scene scene) {
        this.currentScene = scene;
    }
}
