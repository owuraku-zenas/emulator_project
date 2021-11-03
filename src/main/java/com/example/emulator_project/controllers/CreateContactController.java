package com.example.emulator_project.controllers;

import com.example.emulator_project.controllers.base.NavigationController;
import com.example.emulator_project.db_api.SQLiteDBHandler;
import com.example.emulator_project.utils.SceneManager;
import com.example.emulator_project.utils.TabType;
import com.example.emulator_project.utils.ViewType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class CreateContactController extends NavigationController {
    private SceneManager sm = SceneManager.getInstance();
    private SQLiteDBHandler db = SQLiteDBHandler.getInstance();

    private String imagePath;

    @FXML
    private TextField contactNameField;
    @FXML
    private TextField contactNumberField;
    @FXML
    private ImageView contactImageView;


    public void discardNewContactDetails(MouseEvent mouseEvent) {

        // Clear The Form
        contactNameField.setText("");
        contactNumberField.setText("");

        // redirect to the contacts field;
        FXMLLoader fx = sm.setSceneFromView(ViewType.CONTACT_VIEW);
        PhoneController pc = fx.getController();
        pc.switchTab(TabType.CONTACT_SEARCH_TAB);
        sm.loadScene();
    }

    public void saveNewContactDetails(MouseEvent mouseEvent) {
        String contactName = contactNameField.getText();
        String contactNumber = contactNumberField.getText();

        // add the contact to the database
        if (!contactName.equals("") && !contactNumber.equals("") && !imagePath.equals("")) {
            db.getConnection();
            boolean b = db.insertContact(contactName, contactNumber, imagePath);

            if (b) {
                System.out.println("New Contact Added");
                // redirect to the contacts page
                FXMLLoader fx = sm.setSceneFromView(ViewType.CONTACT_VIEW);
                PhoneController pc = fx.getController();
                pc.switchTab(TabType.CONTACT_SEARCH_TAB);
                sm.loadScene();
            }
        }
    }

    public void openImageDialogForContact(MouseEvent mouseEvent) {
        // create file choose
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Profile Image");
//        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter());
        File file = fileChooser.showOpenDialog(sm.getCurrentScene().getWindow());
        if (file != null) {
            // set the image view to the image selected;
            contactImageView.setImage(new Image(file.toURI().toString()));
        }
        // get the image path
        imagePath = file.getPath();
    }

}

