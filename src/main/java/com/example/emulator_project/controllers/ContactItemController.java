package com.example.emulator_project.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ContactItemController {
    @FXML
    private Label contactName;
    @FXML
    private Label contactNumber;
    @FXML
    private ImageView contactImage;

    @FXML
    public void setContactName(String name) {
        contactName.setText(name);
    }

    @FXML
    public void setContactNumber(String phone) {
        contactNumber.setText(phone);
    }

    @FXML
    public void setContactImage(String imageURl) {
        contactImage.setImage(new Image(imageURl));
    }
}
