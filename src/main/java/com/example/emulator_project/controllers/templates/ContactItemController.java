package com.example.emulator_project.controllers.templates;

import com.example.emulator_project.controllers.CallViewController;
import com.example.emulator_project.utils.SceneManager;
import com.example.emulator_project.utils.ViewType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ContactItemController {
    private SceneManager sm = SceneManager.getInstance();

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

    public void handleClick(MouseEvent mouseEvent) {
        String phoneNumber = contactNumber.getText();

        FXMLLoader fx = sm.setSceneFromView(ViewType.CALL_VIEW);
        CallViewController cvc = fx.getController();
        cvc.makePhoneCall(phoneNumber);
        System.out.println("Calling :" + contactName.getText());
        sm.loadScene();

    }
}
