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

    public static SceneManager getInstance() {
        if (sceneManagerInstance == null) {
            sceneManagerInstance = new SceneManager();
        }
        return sceneManagerInstance;
    }


    private Stage getStageFromEvent(Event e) {
        Stage root = (Stage) ((Node) e.getSource()).getScene().getWindow();
        return root;
    }

    public Scene getCurrentScene() {
        return this.currentScene;
    }

    public SceneManager setContext(Class<? extends Application> applicationContext) {
        this.context = (applicationContext);
        return this;
    }

    public FXMLLoader setSceneFromView(ViewType view) {
        FXMLLoader nextFXML = null;

        try {

            if (view == ViewType.CALENDAR_VIEW) {
                nextFXML = new FXMLLoader(this.context.getResource("views/calendar-view.fxml"));
            } else if (view == ViewType.HOME_VIEW) {
                nextFXML = new FXMLLoader(this.context.getResource("views/home-view.fxml"));
            } else if (view == ViewType.CALL_VIEW) {
                nextFXML = new FXMLLoader(this.context.getResource("views/call-view.fxml"));
            } else if (view == ViewType.CONTACT_VIEW) {
                nextFXML = new FXMLLoader(this.context.getResource("views/contact-view.fxml"));
            } else if (view == ViewType.SMS_VIEW) {
                nextFXML = new FXMLLoader(this.context.getResource("views/sms-view.fxml"));
            } else if (view == ViewType.TIME_VIEW) {
                nextFXML = new FXMLLoader(this.context.getResource("views/time-view.fxml"));
            } else {
            }

            // set the current scene to a new scene with the loaded fxml file
            this.currentScene = new Scene(nextFXML.load(), 360, 680);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // return the current FXMLLoader object in the case of need
        return nextFXML;
    }

    public FXMLLoader getTemplateFromViewType(TemplateType template) {
        try {
            if (template == TemplateType.ADD_NEW_CONTACT) {
                return new FXMLLoader(this.context.getResource("views/templates/add-new-contact.fxml"));
            } else if (template == TemplateType.CONTACT_ITEM) {
                return new FXMLLoader(this.context.getResource("views/templates/contact-item.fxml"));
            } else if (template == TemplateType.LOG_ITEM) {
                return new FXMLLoader(this.context.getResource("views/templates/log-item.fxml"));
            } else if (template == TemplateType.SMS_ITEM) {
                return new FXMLLoader(this.context.getResource("views/templates/sms-item.fxml"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setStage(Stage stage) {
        this.mainStage = stage;
    }

    public void loadScene() {
        this.mainStage.setScene(this.getCurrentScene());
        this.mainStage.show();
    }

    public FXMLLoader switchSceneFromEvent(Event event, ViewType view) {
        Stage stage = this.getStageFromEvent(event);
        this.setStage(stage);
        return this.setSceneFromView(view); // returns an FXMLLoader object
    }
}
