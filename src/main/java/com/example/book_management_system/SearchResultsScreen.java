package com.example.book_management_system;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SearchResultsScreen {
    private final Stage stage;
    private final Scene searchResultsScene;
    private final SearchScreen searchScreen;

    @SuppressWarnings("unchecked")
	public SearchResultsScreen(Stage stage, List<Book> books, SearchScreen searchScreen) {
        this.stage = stage;
        this.searchScreen = searchScreen;

        // Create a border pane as the root node
        BorderPane root = new BorderPane();

        // Create a HBox for the back button
        HBox backButtonBox = new HBox();
        backButtonBox.setAlignment(Pos.CENTER_LEFT);
        backButtonBox.setPadding(new Insets(10));

        // Create a back button and set its action
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            // Switch back to the search screen
            this.stage.setTitle("Book System Search");
            this.stage.setScene(this.searchScreen.getSearchScene());
        });
        // Add the back button to the back button box
        backButtonBox.getChildren().add(backButton);

        // Set the back button box as the top node of the border pane
        root.setTop(backButtonBox);
        // Create a table view to display the search results
        TableView<Book> tableView = new TableView<>();
        // Create columns for the table view
        TableColumn<Book, String> ISBNColumn = new TableColumn<>("ISBN");
        ISBNColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIsbn()));
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));
        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAuthor()));
        TableColumn<Book, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGenre()));
        TableColumn<Book, String> formatColumn = new TableColumn<>("Format");
        formatColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFormat()));
        TableColumn<Book, String> locationColumn = new TableColumn<>("Location");
        locationColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLocation()));
        TableColumn<Book, String> additionalInfoColumn = new TableColumn<>("Additional Information");
        additionalInfoColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAdditionalInfo()));



        // Add columns to the table view
        tableView.getColumns().addAll(ISBNColumn, titleColumn, authorColumn, genreColumn, locationColumn, formatColumn,additionalInfoColumn);
        ObservableList<Book> data = FXCollections.observableList(books);
        tableView.setItems(data);

        // Set the table view as the center node of the border pane
        root.setCenter(tableView);
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                // Get the selected book from the table view
                Book selectedBook = tableView.getSelectionModel().getSelectedItem();
                if (selectedBook != null) {
                	// Create a new window
	                Stage dialog = new Stage();
	                DEScreen EditDeleteBookScreen = new DEScreen(dialog,selectedBook,data);
	                dialog.initModality(Modality.APPLICATION_MODAL);
	                dialog.initOwner(stage);
	
	                // Set the Edit/Delete Screen as the content of the window
	                dialog.setScene(EditDeleteBookScreen.getDEScene());
	
	                // Show the window
	                dialog.showAndWait();
                }
            }
        });

        // Create the search results scene with the border pane as its root node
        searchResultsScene = new Scene(root, 915, 600);
    }

    public Scene getSearchResultsScene() {
        return searchResultsScene;
    }
}