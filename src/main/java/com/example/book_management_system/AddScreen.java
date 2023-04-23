package com.example.book_management_system;
import java.sql.SQLException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class AddScreen {
    private final Stage stage;
    private final Scene AddScene;
    private final BorderPane root;
    private final SearchScreen searchScreen;

    public AddScreen(Stage stage, SearchScreen searchScreen) {
        this.searchScreen = searchScreen;
        this.stage = stage;
        root = new BorderPane();
        Image icon = new Image(getClass().getResourceAsStream("/book.png"));
        this.stage.setTitle("Add Book");
        this.stage.getIcons().add(icon);

        // Create form controls
        Label isbnLabel = new Label("ISBN:");
        Label isbnRequiredLabel = new Label("*");
        isbnRequiredLabel.setTextFill(Color.RED);
        TextField isbnTextField = new TextField();
        isbnTextField.setPrefWidth(250);
        isbnTextField.setPromptText("Valid ISBN-10/ISBN-13");
        isbnTextField.setStyle("-fx-prompt-text-fill: grey;");
        HBox isbnLabelContainer = new HBox(5, isbnRequiredLabel, isbnTextField);
        
        
        Label titleLabel = new Label("Title:");
        Label titleRequiredLabel = new Label("*");
        titleRequiredLabel.setTextFill(Color.RED);
        TextField titleTextField = new TextField();
        titleTextField.setPrefWidth(250);
        titleTextField.setPromptText("E.g: The Adventures of Tintin");
        titleTextField.setStyle("-fx-prompt-text-fill: grey;");
        HBox titleLabelContainer = new HBox(05, titleRequiredLabel, titleTextField);

        Label authorLabel = new Label("Author:");
        Label authorRequiredLabel = new Label("*");
        authorRequiredLabel.setTextFill(Color.RED);
        TextField authorTextField = new TextField();
        authorTextField.setPrefWidth(250);
        authorTextField.setPromptText("E.g: Georges Prosper Remi");
        authorTextField.setStyle("-fx-prompt-text-fill: grey;");
        HBox authorLabelContainer = new HBox(05, authorRequiredLabel, authorTextField);

        Label genreLabel = new Label("Genre:");
        Label genreRequiredLabel = new Label("*");
        genreRequiredLabel.setTextFill(Color.RED);
        ComboBox<String> genreComboBox = new ComboBox<>();
        genreComboBox.getItems().addAll(
                "Fiction",
                "Non-fiction",
                "Mystery",
                "Thriller",
                "Romance",
                "Science Fiction",
                "Biography",
                "Autobiography",
                "History",
                "Religion"
        );
        genreComboBox.setPrefWidth(250);
        HBox genreLabelContainer = new HBox(05, genreRequiredLabel, genreComboBox);

        Label formatLabel = new Label("Format:");
        Label formatRequiredLabel = new Label("*");
        formatRequiredLabel.setTextFill(Color.RED);
        ComboBox<String> formatComboBox = new ComboBox<>();
        formatComboBox.getItems().addAll(
                "Audiobook",
                "EBook",
                "Hardcover",
                "PDF",
                "Paperback"
        );
        HBox formatLabelContainer = new HBox(05, formatRequiredLabel, formatComboBox);
        formatComboBox.setPrefWidth(250);

        Label locationLabel = new Label("Location:");
        Label locationRequiredLabel = new Label("*");
        locationRequiredLabel.setTextFill(Color.RED);
        TextField locationTextField = new TextField();
        locationTextField.setPrefWidth(250);
        locationTextField.setPromptText("E.g: Living room topshelf");
        locationTextField.setStyle("-fx-prompt-text-fill: grey;");
        HBox locationLabelContainer = new HBox(05, locationRequiredLabel, locationTextField);

        Label additionalInfoLabel = new Label("Additional Information:");
        TextArea additionalInfoTextArea = new TextArea();
        additionalInfoTextArea.setPrefWidth(250);
        additionalInfoTextArea.setPrefHeight(100);
        additionalInfoTextArea.setPromptText("E.g: With my friend/ Recently Purchased/ Torn on page 54");
        additionalInfoTextArea.setStyle("-fx-prompt-text-fill: grey;");
        Label additionalInfoRequiredLabel = new Label(" ");
		HBox additionalInfoLabelContainer = new HBox(07,additionalInfoRequiredLabel ,additionalInfoTextArea);

        // Create layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.add(isbnLabel, 0, 0);
        gridPane.add(isbnLabelContainer, 1, 0);

        gridPane.add(titleLabel, 0, 1);
        gridPane.add(titleLabelContainer, 1, 1);
        
        gridPane.add(authorLabel, 0, 2);
        gridPane.add(authorLabelContainer, 1, 2);

        gridPane.add(genreLabel, 0, 3);
        gridPane.add(genreLabelContainer, 1, 3);
        
        gridPane.add(locationLabel, 0, 4);
        gridPane.add(locationLabelContainer, 1, 4);
        
        gridPane.add(formatLabel, 0, 5);
        gridPane.add(formatLabelContainer, 1, 5);

        gridPane.add(additionalInfoLabel, 0, 6);
        gridPane.add(additionalInfoLabelContainer, 1, 6);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            String title = titleTextField.getText();
            String author = authorTextField.getText();
            String isbn = isbnTextField.getText();
            String genre = genreComboBox.getValue();
            String format = formatComboBox.getValue();
            String location = locationTextField.getText();
            String additionalInfo = additionalInfoTextArea.getText();

            ADDOp addbook= new ADDOp(isbn, title, author,  genre, location, format, additionalInfo);
            boolean success = false;
            try {
				if (addbook.addbook()) {
					success=true;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            if (success) {
                titleTextField.clear();
                authorTextField.clear();
                isbnTextField.clear();
                genreComboBox.getSelectionModel().clearSelection();
                formatComboBox.getSelectionModel().clearSelection();
                locationTextField.clear();
                additionalInfoTextArea.clear();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Book added successfully");
                alert.showAndWait();
            }
         });

        Button backButton = new Button("Back");
        backButton.setOnAction(e ->{ 
        this.stage.setTitle("Book System Search");
        this.stage.setScene(this.searchScreen.getSearchScene());
        });

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(addButton, backButton);

        root.setCenter(gridPane);
        root.setBottom(buttonBox);
        root.setPadding(new Insets(20, 20, 20, 20));

        AddScene = new Scene(root, 500, 400);
    }

    public Scene getAddScene() {
    	
        return AddScene;
    }
}