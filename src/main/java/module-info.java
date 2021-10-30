/**
 *
 */
module com.example.emulator_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    exports com.example.emulator_project;
    opens com.example.emulator_project to javafx.fxml;
    exports com.example.emulator_project.controllers;
    opens com.example.emulator_project.controllers to javafx.fxml;
    exports com.example.emulator_project.controllers.tabs;
    opens com.example.emulator_project.controllers.tabs to javafx.fxml;
    exports com.example.emulator_project.utils;
    opens com.example.emulator_project.utils to javafx.fxml;
    exports com.example.emulator_project.controllers.templates;
    opens com.example.emulator_project.controllers.templates to javafx.fxml;
    exports com.example.emulator_project.controllers.base;
    opens com.example.emulator_project.controllers.base to javafx.fxml;
}