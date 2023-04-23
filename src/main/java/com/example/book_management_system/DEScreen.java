package com.example.book_management_system;

import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class DEScreen {
    private final Stage stage;
    private final Scene DEScene;
    private final BorderPane root;
    private Book book;

    
    public DEScreen(Stage stage, Book book, ObservableList<Book> data) {
            this.stage = stage;
            this.book = book;
            root = new BorderPane();
            this.stage.setTitle("Delete/Edit Book");
            Image icon = new Image(getClass().getResourceAsStream("/book.png"));
            this.stage.getIcons().add(icon);            
            


            // Create labels and text fields for each input field
            Label isbnLabel = new Label("ISBN:");
            TextField isbnTextField = new TextField(this.book.getIsbn());
            isbnTextField.setEditable(false);
            isbnTextField.setStyle("-fx-opacity: 0.5;");
            Label isbnRequiredLabel = new Label(" ");
            isbnRequiredLabel.setTextFill(Color.RED);
            isbnTextField.setPrefWidth(250);
            HBox isbnLabelContainer = new HBox(7, isbnRequiredLabel, isbnTextField);
            
            
            Label titleLabel = new Label("Title:");
            Label titleRequiredLabel = new Label("*");
            titleRequiredLabel.setTextFill(Color.RED);
            TextField titleTextField = new TextField(this.book.getTitle());
            titleTextField.setPrefWidth(250);
            titleTextField.setPromptText("E.g: The Adventures of Tintin");
            titleTextField.setStyle("-fx-prompt-text-fill: grey;");
            HBox titleLabelContainer = new HBox(05, titleRequiredLabel, titleTextField);

            Label authorLabel = new Label("Author:");
            Label authorRequiredLabel = new Label("*");
            authorRequiredLabel.setTextFill(Color.RED);
            TextField authorTextField = new TextField(this.book.getAuthor());
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
            genreComboBox.setValue(this.book.getGenre());
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
            formatComboBox.setValue(this.book.getFormat());
            formatComboBox.setPrefWidth(250);

            Label locationLabel = new Label("Location:");
            Label locationRequiredLabel = new Label("*");
            locationRequiredLabel.setTextFill(Color.RED);
            TextField locationTextField = new TextField(this.book.getLocation());
            locationTextField.setPrefWidth(250);
            locationTextField.setPromptText("E.g: Living room topshelf");
            locationTextField.setStyle("-fx-prompt-text-fill: grey;");
            HBox locationLabelContainer = new HBox(05, locationRequiredLabel, locationTextField);

            Label additionalInfoLabel = new Label("Additional Information:");
            TextArea additionalInfoTextArea = new TextArea(this.book.getAdditionalInfo());
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

            // Create an HBox for the buttons
            HBox buttonBox = new HBox(10);
            buttonBox.setAlignment(Pos.CENTER);

            // Create an update button and set its action
            Button updateButton = new Button("Update");
            updateButton.setOnAction(e -> {
                // Get the values from the input fields
            	// Show a confirmation dialog
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Edit");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to edit the book?");

                // Get the result of the dialog
                Optional<ButtonType> result = alert.showAndWait();

                // Only update the book if the user selects "Yes"
                if (result.isPresent() && result.get() == ButtonType.OK) {
	            	String isbn= isbnTextField.getText();
	                String title = titleTextField.getText();
	                String author = authorTextField.getText();
	                String genre = genreComboBox.getValue();
	                String format = formatComboBox.getValue();
	                String location = locationTextField.getText();
	                String additionalInfo = additionalInfoTextArea.getText();
	                
	                EDITOp newbook=new EDITOp(isbn,title,author,genre,location,format,additionalInfo,book);
	                boolean success=newbook.editBook();
	                if (success) {
	                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
	                    alert1.setTitle("Success");
	                    alert1.setHeaderText(null);
	                    alert1.setContentText("Book Updated Successfully!");
	                    alert1.showAndWait();
	                    data.remove(this.book);
	                    this.book.setIsbn(isbn);
	                    this.book.setTitle(title);
	                    this.book.setAuthor(author);
	                    this.book.setGenre(genre);
	                    this.book.setFormat(format);
	                    this.book.setLocation(location);
	                    this.book.setAdditionalInfo(additionalInfo);
	                    data.add(this.book);
	                }
                }
            });
            // Create a delete button and set its action
            Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(e -> {
            	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Delete");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete the book?");
             // Get the result of the dialog
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                	DELETEOp delete=new DELETEOp(this.book);
                	boolean success=delete.deletebook();
                	if (success) {
	                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
	                    alert1.setTitle("Success");
	                    alert1.setHeaderText(null);
	                    alert1.setContentText("Book Deleted Successfully!");
	                    alert1.showAndWait();
	                    data.remove(this.book);
	                    ((Stage)((Node)e.getSource()).getScene().getWindow()).close();
                	}

                }

            });

            Button backButton = new Button("Back");
            backButton.setOnAction(e -> {
                // Close the screen
                ((Stage)((Node)e.getSource()).getScene().getWindow()).close();
            });

            // Add the buttons to the button box
            buttonBox.getChildren().addAll(updateButton, deleteButton,backButton);

            // Add the button box to a VBox with a small preferred height
            VBox bottomBox = new VBox(10);
            bottomBox.setAlignment(Pos.CENTER);
            bottomBox.setPrefHeight(20);
            bottomBox.setPrefWidth(20);
            bottomBox.getChildren().add(buttonBox);

            // Add the grid pane and bottom VBox to the root node
            root.setCenter(gridPane);
            root.setBottom(bottomBox);
            root.setPadding(new Insets(20,20,50,20));

            DEScene = new Scene(root, 600, 400);

        }
        public Scene getDEScene() {
            return DEScene;
        }
}