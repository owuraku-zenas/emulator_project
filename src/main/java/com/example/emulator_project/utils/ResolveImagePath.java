package com.example.emulator_project.utils;

import javafx.application.Application;
import javafx.scene.image.Image;

public class ResolveImagePath {
    private static ResolveImagePath instance;
    private static Class<? extends Application> context;

    private ResolveImagePath() {

    }

    public static ResolveImagePath getInstance() {
        if (instance == null) {
            instance = new ResolveImagePath();
        }

        return instance;
    }

    public ResolveImagePath setContext(Class<? extends Application> appContext) {
        context = appContext;
        return instance;
    }

    public Image resolve(String image) {
        if (image.equals("missed")) {
            return new Image(String.valueOf(context.getResource("images/icons/missedCall.png")));
        } else if ((image).equals("accepted")) {
            return new Image(String.valueOf(context.getResource("images/icons/incall.png")));
        }

        return null;
    }
}
