package com.example.emulator_project.controllers.tabs;

import com.example.emulator_project.db_api.SQLiteDBHandler;
import com.example.emulator_project.utils.SceneManager;
import com.example.emulator_project.utils.TemplateType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactsTabController {
    @FXML
    private ScrollPane contactsPane;
    @FXML
    private VBox contactsPaneVBox;
    @FXML
    private Button newContactButton;
    @FXML
    private TextField contactSearchField;

    private SceneManager sm = SceneManager.getInstance();
    private SQLiteDBHandler db = SQLiteDBHandler.getInstance();
    private ArrayList<ArrayList<String>> users = new ArrayList<ArrayList<String>>();

    public ContactsTabController() {
        fetchAndStoreContacts();
    }

    @FXML
    public void initialize() {
        fetchAndPopulateContacts();
    }

    private void fetchAndPopulateContacts() {
        users.forEach(s -> appendItemWithDetails(s.get(0), s.get(1), s.get(2)));
    }

    private void fetchAndStoreContacts() {

        db.getConnection();
        ResultSet allContacts = db.fetchAllContacts();
        try {
            if (allContacts.next() == true) {
//                show list inside console
                System.out.println("Success In Fetching Data From DATABASE:");

                do {
                    System.out.printf("%-5s%-20s%-20s%-20s%n",
                            allContacts.getInt("id"), allContacts.getString("name"),
                            allContacts.getString("phone"), allContacts.getString("image"));

                    //--show list on phone screen
                    String phone = allContacts.getString("phone");
                    String name = ((allContacts.getString("name")).contentEquals("")) ? phone : (allContacts.getString("name"));
                    String userImageUrl = allContacts.getString("image");

                    // -- show avatar icon
//                    appendItemWithDetails(name, phone, userImageUrl);
                    ArrayList<String> s = new ArrayList<>();
                    s.add(name);
                    s.add(phone);
                    s.add(userImageUrl);

                    users.add(s);

                } while (allContacts.next());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createNewContact(ActionEvent event) {
        Scanner sc = new Scanner(System.in);
        // show new Contacts Modal
        System.out.print("Enter your Name: ");
        String name = sc.nextLine();
        System.out.print("Enter you phone number: ");
        String phone = sc.nextLine();
        System.out.print("Enter the Image path: ");
        String img = sc.nextLine();

        // get Name, Phone and Image from Modal

        // Send details to database
        Connection con = db.getConnection();

        if (con != null) {
            // if successful append to contacts pane
            db.insertContact(name, phone, img);
            appendItemWithDetails(name, phone, img);
        }
    }

    private void appendItemWithDetails(String name, String phone, String img) {
        int index = contactsPaneVBox.getChildren().size();
        try {
            FXMLLoader anchorPaneLoader = sm.getTemplateFromViewType(TemplateType.CONTACT_ITEM);

            /* Method 1 - Get the Controller from FXML file, use it to set needed fields and then load the scene */
            /* FIXME */
//            anchorPaneLoader.setController(new ContactItemController());
//            ContactItemController cci = anchorPaneLoader.getController();
//            cci.setContactNumber(phone);
//            cci.setContactName(name);
//            cci.setContactImage(img);
//            contactsPaneVBox.getChildren().add(index++, anchorPaneLoader.load());

            /* Method 2 - Load the scene (Anchor Pane), find and set the fields from the scene */
            AnchorPane ap = anchorPaneLoader.load();
            Label nameLabel = (Label) ap.lookup("#contactName");
            Label numberLabel = (Label) ap.lookup("#contactNumber");
            nameLabel.setText(name);
            numberLabel.setText(phone);
            contactsPaneVBox.getChildren().add(index++, ap);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeNewSearch(KeyEvent keyEvent) {
        contactsPaneVBox.getChildren().removeIf(c -> true);

        if (contactSearchField.getText().equals("")) {
            fetchAndPopulateContacts();
        } else {
            users.forEach(user -> {
                if (user.get(1).startsWith(contactSearchField.getText()))
                    appendItemWithDetails(user.get(0), user.get(1), user.get(2));

                if (user.get(0).startsWith(contactSearchField.getText()))
                    appendItemWithDetails(user.get(0), user.get(1), user.get(2));
            });
        }
    }

    public void makeStringSearch(MouseEvent mouseEvent) {
        if (contactSearchField.getText().equals("")) return;

        users.forEach(user -> {
            if (user.get(0).equals(contactSearchField.getText()) || user.get(1).equals(contactSearchField.getText())) {
                contactsPaneVBox.getChildren().removeIf(c -> true);
                appendItemWithDetails(user.get(0), user.get(1), user.get(2));
            }
        });

    }
}
