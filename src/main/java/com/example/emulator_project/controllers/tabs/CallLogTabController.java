package com.example.emulator_project.controllers.tabs;

import com.example.emulator_project.db_api.SQLiteDBHandler;
import com.example.emulator_project.utils.SceneManager;
import com.example.emulator_project.utils.TemplateType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CallLogTabController {
    @FXML
    private VBox callLogVbox;

    private SceneManager sm = SceneManager.getInstance();
    private SQLiteDBHandler db = SQLiteDBHandler.getInstance();
    private ArrayList<ArrayList<String>> logs = new ArrayList<>();

    public CallLogTabController() {
        db.getConnection();
        // fetch and store logs
        fetchAndStoreAllCalls();
    }

    @FXML
    public void initialize() {
        // fetch and display logs
        fetchAllCalls(null);
    }


    public void fetchAllMissedCall(ActionEvent event) {
        db.getConnection();
        callLogVbox.getChildren().removeIf(child -> (child instanceof AnchorPane) == true);

        logs.forEach(log -> {
            if (log.get(2).equals("missed"))
                appendItemWithDetails(log.get(0), log.get(1), log.get(2), log.get(3));
        });
    }

    public void fetchAllAcceptedCalls(ActionEvent event) {
        db.getConnection();
        callLogVbox.getChildren().removeIf(child -> (child instanceof AnchorPane) == true);
//        callLogVbox.getChildren().removeAll();

        logs.forEach(log -> {
            if (log.get(2).equals("dialed"))
                appendItemWithDetails(log.get(0), log.get(1), log.get(2), log.get(3));
        });
    }

    public void fetchAllCalls(ActionEvent event) {
        db.getConnection();
        callLogVbox.getChildren().removeIf(child -> (child instanceof AnchorPane) == true);
//        callLogVbox.getChildren().removeAll();

        logs.forEach(log -> {
            appendItemWithDetails(log.get(0), log.get(1), log.get(2), log.get(3));
        });

    }

    private void appendItemWithDetails(String name, String time, String category, String img) {
        int index = callLogVbox.getChildren().size();
        try {
            FXMLLoader anchorPaneLoader = sm.getTemplateFromViewType(TemplateType.LOG_ITEM);

            /* Method 2 - Load the scene (Anchor Pane), find and set the fields from the scene */
            AnchorPane ap = anchorPaneLoader.load();
            Label nameLabel = (Label) ap.lookup("#callerName");
            Label callLogTime = (Label) ap.lookup("#callLogTime");
//            ImageView image = (ImageView) ap.lookup("#contactImage");
            ImageView callType = (ImageView) ap.lookup("#callType");

            nameLabel.setText(name);
            callLogTime.setText(time);

            String callTypeIcon = "";
            if (category.equals("missed")) {
                callTypeIcon = "missedCall.png";
            } else if (category.equals("dialed")) {
                callTypeIcon = "callout.png";
            } else {
                callTypeIcon = "incall.png";
            }

//            callType.setImage(new Image("../../images/icons/" + callTypeIcon));

            callLogVbox.getChildren().add(index++, ap);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fetchAndStoreAllCalls() {
        db.getConnection();
        ResultSet rs = db.fetchAllCallLog();

        try {

            if (rs.next() == true) {
                do {
                    String phone = rs.getString("log_phone");
                    String date = rs.getString("date");
                    String time = rs.getString("time");
                    String category = rs.getString("category");
                    String image = rs.getString("image");
                    String name = ((rs.getString("name")) == null) ? phone : (rs.getString("name"));


                    ArrayList<String> s = new ArrayList<>();
                    s.add(name);
//                    s.add(phone);
                    s.add(date + ", " + time);
//                    s.add(time);
                    s.add(category);
                    s.add(image);

                    logs.add(s);
                } while (rs.next());
            }

//            logs.forEach(s -> {
//                System.out.println(s.get(0) + " " + s.get(1) + " " + s.get(2) + " " + s.get(3));
//            });

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }


}
