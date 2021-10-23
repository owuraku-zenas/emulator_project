package com.example.emulator_project.utils;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    private Scene currentScene;
    private Stage mainStage;
    private static SceneManager sceneManagerInstance;
    private Class<? extends Application> context;

    private SceneManager() {
    }

    private void setCurrentScene(Scene scene) {
        this.currentScene = scene;
    }

    private Stage getStageFromEvent(Event e) {
        Stage root = (Stage) ((Node) e.getSource()).getScene().getWindow();
        return root;
    }

    public static SceneManager getInstance() {
        if (sceneManagerInstance == null) {
            sceneManagerInstance = new SceneManager();
        }

        return sceneManagerInstance;
    }

    public Scene getCurrentScene() {
        return this.currentScene;
    }

    public FXMLLoader setSceneFromView(Views view) {
        FXMLLoader nextFXML = null;

        try {

            if (view == Views.CALENDAR_VIEW) {
                nextFXML = new FXMLLoader(this.context.getResource("views/calendar-view.fxml"));
            } else if (view == Views.HOME_VIEW) {
                nextFXML = new FXMLLoader(this.context.getResource("views/home-view.fxml"));
            } else if (view == Views.CALL_VIEW) {
                nextFXML = new FXMLLoader(this.context.getResource("views/call-view.fxml"));
            } else if (view == Views.CONTACT_VIEW) {
                nextFXML = new FXMLLoader(this.context.getResource("views/contact-view.fxml"));
            } else if (view == Views.SMS_VIEW) {
                nextFXML = new FXMLLoader(this.context.getResource("views/sms-view.fxml"));
            } else if (view == Views.TIME_VIEW) {
                nextFXML = new FXMLLoader(this.context.getResource("views/time-view.fxml"));
            } else {
            }

            this.setCurrentScene(new Scene(nextFXML.load(), 360, 680));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return nextFXML;
    }


    public void setStage(Stage stage) {
        this.mainStage = stage;
    }

    public void load() {
        this.mainStage.setScene(this.getCurrentScene());
        this.mainStage.show();
    }

    public FXMLLoader switchSceneFromEvent(Event event, Views callView) {
        Stage stage = getStageFromEvent(event);
        this.setStage(stage);
        FXMLLoader fx = this.setSceneFromView(callView);
        return fx;
    }

    public SceneManager setContext(Class<? extends Application> applicationContext) {
        this.context = (applicationContext);
        return this;
    }
}
