package com.example.emulator_project.utils;

import com.example.emulator_project.controllers.ContactItemController;
import com.example.emulator_project.controllers.ContactsController;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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

            this.setCurrentScene(new Scene(nextFXML.load(), 360, 680));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return nextFXML;
    }


    public AnchorPane getTemplateFromViewType(TemplateType template, String name, String phone, String image) {
//        FXMLLoader fx = null;
        try {
            if (template == TemplateType.ADD_NEW_CONTACT) {
                return new FXMLLoader(this.context.getResource("templates/add-new-contact.fxml")).load();
            } else if (template == TemplateType.CONTACT_ITEM) {
                FXMLLoader fx = new FXMLLoader(this.context.getResource("templates/contact-item.fxml"));
//                ContactsController cci = (ContactsController) fx.getController();
//                if (cci == null) {
//                    System.out.println("No Controller Found for Contact Item");
//                } else {
//                    System.out.println("Controller Connected to XML File");
//                    cci.addDetails(name, phone, image);
//                }
                return fx.load();

            } else if (template == TemplateType.LOG_ITEM) {
                return new FXMLLoader(this.context.getResource("templates/log-item.fxml")).load();
            } else if (template == TemplateType.SMS_ITEM) {
                return new FXMLLoader(this.context.getResource("templates/sms-item.fxml")).load();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

//        return fx;
        return null;
    }

    public void setStage(Stage stage) {
        this.mainStage = stage;
    }

    public void load() {
        this.mainStage.setScene(this.getCurrentScene());
        this.mainStage.show();
    }

    public FXMLLoader switchSceneFromEvent(Event event, ViewType view) {
        Stage stage = getStageFromEvent(event);
        this.setStage(stage);
        FXMLLoader fx = this.setSceneFromView(view);
        return fx;
    }

    public SceneManager setContext(Class<? extends Application> applicationContext) {
        this.context = (applicationContext);
        return this;
    }
}
