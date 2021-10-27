package com.example.emulator_project.controllers;

import com.example.emulator_project.api.SQLiteDBHandler;
import com.example.emulator_project.utils.SceneManager;
import com.example.emulator_project.utils.TabType;
import com.example.emulator_project.utils.TemplateType;
import com.example.emulator_project.utils.ViewType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactsController extends NavigationController {

    @FXML
    private TextField dialInputField;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab dialerTab;
    @FXML
    private Tab contactsTab;
    @FXML
    private Tab callLogTab;
    @FXML
    private ScrollPane contactsPane;
    @FXML
    private VBox contactsPaneVBox;
    @FXML
    private Button newContactButton;
    @FXML
    private Label contactNumber;
    @FXML
    private Label contactName;

    private SceneManager sm = SceneManager.getInstance();
    private SQLiteDBHandler db = SQLiteDBHandler.getInstance();
    private ArrayList<ArrayList<String>> users = new ArrayList<ArrayList<String>>();

    public ContactsController() {
        // fetch and populate contacts view with contacts;
        fetchAndStoreContacts();

//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        t.start();

        // add click listeners to the contacts
    }

    private void fetchAndPopulateContacts() {
        for (ArrayList<String> s : users) {
            appendItemWithDetails(s.get(0), s.get(1), s.get(2));
        }
    }

    private void fetchAndStoreContacts() {

        db.getConnection();
        ResultSet allContacts = db.fetchAllContacts();
        try {
            if (allContacts.next() == true) {
//                show list inside console
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

    public void handleButtonClick(ActionEvent event) {
        Button button = (Button) event.getTarget();
        String inputString = dialInputField.getText() + button.getText();
        if (dialInputField != null) {
            dialInputField.setText(inputString);
            System.out.println(inputString);
        } else {
            System.out.println(dialInputField.toString() + " is null");
        }
    }

    public void makePhoneCall(ActionEvent event) {
        FXMLLoader fx = sm.switchSceneFromEvent(event, ViewType.CALL_VIEW);
        ((CallViewController) fx.getController()).setCallerID(dialInputField.getText());
        sm.load();
    }

    public void switchTab(TabType tab) {
        if (tab == TabType.CALL_LOG_TAB) {
            tabPane.getSelectionModel().select(callLogTab);
        } else if (tab == TabType.CONTACT_SEARCH_TAB) {
            tabPane.getSelectionModel().select(contactsTab);
        } else if (tab == TabType.DIAL_PAD_TAB) {
            tabPane.getSelectionModel().select(dialerTab);
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

    public void addDetails(String name, String phone, String img) {
        contactName.setText(name);
        contactNumber.setText(phone);
    }

    private void appendItemWithDetails(String name, String phone, String img) {

        int index = contactsPaneVBox.getChildren().size();
        System.out.println("Number of Contacts: " + index);
        AnchorPane anchorPane = sm.getTemplateFromViewType(TemplateType.CONTACT_ITEM, name, phone, img);
        Label nameLabel = (Label) anchorPane.lookup("#contactName");
        Label numberLabel = (Label) anchorPane.lookup("#contactNumber");

        nameLabel.setText(name);
        numberLabel.setText(phone);
        contactsPaneVBox.getChildren().add(index++, anchorPane);
    }

}
