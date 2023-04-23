package com.example.book_management_system;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Start extends Application {
    public static BookDAOInterface DatabaseAccess;

    @Override
    public void start(Stage primaryStage) {
        // Create a new alert dialog for database selection
    	BorderPane root = new BorderPane();
        Scene alert = new Scene(root, 280, 120);

        
        // Create an image view for the icon
        Image icon = new Image(getClass().getResourceAsStream("/book.png"));
        primaryStage.getIcons().add(icon);


        // Create radio buttons for each database option
        RadioButton mysqlRadioButton = new RadioButton("MySQL");
        mysqlRadioButton.setSelected(true);
        RadioButton sqliteRadioButton = new RadioButton("SQLite");

        // Add radio buttons to a toggle group
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(mysqlRadioButton, sqliteRadioButton);

        // Create "OK" and "Cancel" buttons
        Button okButton = new Button("OK");
        okButton.setDefaultButton(true);
        Button cancelButton = new Button("Cancel");
        cancelButton.setCancelButton(true);

        // Add radio buttons to a grid pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(mysqlRadioButton, 0, 0);
        gridPane.add(sqliteRadioButton, 0, 1);
        gridPane.add(okButton, 0, 2);
        gridPane.add(cancelButton, 1, 2);

        // Add grid pane to alert Scene
        root.setCenter(gridPane);

        // Set action for "OK" button
        okButton.setOnAction(event -> {
            if (mysqlRadioButton.isSelected()) {
                DatabaseAccess = new SQLDAO();
            } else {
                DatabaseAccess = new SQLiteDAO();
            }
            // Create a new search screen with the selected database access and set it as the primary stage's scene
            SearchScreen searchScreen = new SearchScreen(primaryStage);
            Scene searchScene = searchScreen.getSearchScene();
            primaryStage.setScene(searchScene);
            primaryStage.setTitle("Book System Search");
            primaryStage.show();
        });

        // Set action for "Cancel" button
        cancelButton.setOnAction(event -> {
            System.exit(0);
        });



        // Set the alert dialog scene as the primary stage's scene
        primaryStage.setScene(alert);
        primaryStage.setTitle("Select Database");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}