package com.example.book_management_system;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SearchScreen {
    private final Stage stage;
    private final Scene searchScene;
    private final BorderPane root;
    private String SearchMode;
    private GridPane defaultSearchPane;
    private GridPane browseByFieldPane;
    private GridPane advancedSearchPane;
    

    public SearchScreen(Stage stage) {
        //initialization
    	this.stage = stage;
        root = new BorderPane();
        SearchMode = "Default Search";
        Image icon = new Image(getClass().getResourceAsStream("/book.png"));
        this.stage.setTitle("Book System Search");
        this.stage.getIcons().add(icon);
        
        //creating the defaultsearchpane
        defaultSearchPane = new GridPane();
        defaultSearchPane.setPadding(new Insets(10));
        defaultSearchPane.setHgap(10);
        defaultSearchPane.setVgap(10);
        TextField searchField = new TextField();
        ComboBox<SearchField> searchFieldsComboBox = new ComboBox<>();
        searchFieldsComboBox.getItems().addAll(SearchField.values());
        searchFieldsComboBox.setValue(SearchField.Title);
        defaultSearchPane.add(new Label("Search Field:"), 1, 0);
        defaultSearchPane.add(searchFieldsComboBox, 2, 0);
        defaultSearchPane.add(searchField, 3, 0);
        
        //creating the browsebypane
        browseByFieldPane = new GridPane();
        browseByFieldPane.setPadding(new Insets(10));
        browseByFieldPane.setHgap(10);
        browseByFieldPane.setVgap(10);
        ComboBox<SearchField> browseFieldsComboBox = new ComboBox<>();
        browseFieldsComboBox.getItems().add(SearchField.Author);
        browseFieldsComboBox.getItems().add(SearchField.Location);
        browseFieldsComboBox.setValue(null);
        browseFieldsComboBox.setPromptText("Choose Field");
        ComboBox<String> browseItemsComboBox = new ComboBox<>();
        browseItemsComboBox.setPrefWidth(200);
        browseItemsComboBox.setPromptText("Choose an item");
        browseItemsComboBox.setValue(null);        
        browseFieldsComboBox.setOnAction(event -> {
            SearchField selectedField = browseFieldsComboBox.getValue();
            	BROWSEOp bSearch = new BROWSEOp(selectedField);
            	Set<String>browseItems = bSearch.BrowseByField();
            	browseItemsComboBox.getItems().clear();
                browseItemsComboBox.getItems().addAll(browseItems);               	            	
        });
        browseByFieldPane.add(new Label("Browse Field:"), 0, 0);
        browseByFieldPane.add(browseFieldsComboBox, 1, 0);
        browseByFieldPane.add(browseItemsComboBox,2,0);
        
      //creating advancedsearchpane
        advancedSearchPane = new GridPane();
        advancedSearchPane.setPadding(new Insets(10));
        advancedSearchPane.setHgap(10);
        advancedSearchPane.setVgap(10);

        // Add ISBN field
        Label isbnLabel = new Label("ISBN:");
        TextField isbnField = new TextField();
        advancedSearchPane.add(isbnLabel, 1, 0);
        advancedSearchPane.add(isbnField, 2, 0);

        // Add Title field
        Label titleLabel = new Label("Title:");
        TextField titleField = new TextField();
        advancedSearchPane.add(titleLabel, 1, 1);
        advancedSearchPane.add(titleField, 2, 1);

        // Add Author field
        Label authorLabel = new Label("Author:");
        TextField authorField = new TextField();
        advancedSearchPane.add(authorLabel, 1, 2);
        advancedSearchPane.add(authorField, 2, 2);

        // Add Genre field
        Label genreLabel = new Label("Genre:");
        TextField genreField = new TextField();
        advancedSearchPane.add(genreLabel, 1, 3);
        advancedSearchPane.add(genreField, 2, 3);

        // Add Location field
        Label locationLabel = new Label("Location:");
        TextField locationField = new TextField();
        advancedSearchPane.add(locationLabel, 1, 4);
        advancedSearchPane.add(locationField, 2, 4);

        // Add Format field
        Label formatLabel = new Label("Format:");
        ComboBox<String> formatField = new ComboBox<>();
        formatField.getItems().addAll(
                 null,
                "Audiobook",
                "EBook",
                "Hardcover",
                "PDF",
                "Paperback"

        );
        formatField.setPrefWidth(250);
        advancedSearchPane.add(formatLabel, 1, 5);
        advancedSearchPane.add(formatField, 2, 5);
        //buttons
        Button addButton = new Button("Add Book");
        addButton.setOnAction(event -> {
            AddScreen addScreen = new AddScreen(stage,this);
            stage.setScene(addScreen.getAddScene());
        });        
        
        Button SearchButton = new Button("Search Book");
        SearchButton.setOnAction(event -> {
            String selectedMode = SearchMode;
            switch (selectedMode) {
                case "Default Search":
                    // Get the search query from the text field
                    String query = searchField.getText();
                    SearchField selectedField = searchFieldsComboBox.getValue();
                    SEARCHOp search = new SEARCHOp(query,selectedField);
                    List<Book> books = search.DefaultSearch();
                    if (!books.isEmpty()) {
                    	SearchResultsScreen resultsScreen = new SearchResultsScreen(stage, books, this);
                    	stage.setScene(resultsScreen.getSearchResultsScene());
                    	searchField.clear();
                    }
                    root.setCenter(defaultSearchPane);
                    break;
                case "Browse by Field":
                	String browseString = browseItemsComboBox.getValue();
                	SearchField browseField = browseFieldsComboBox.getValue();
                	if (browseString != null || browseField != null) {
	                    SEARCHOp browsesearch = new SEARCHOp(browseString,browseField);
	                    List<Book> browsebooks = browsesearch.DefaultSearch();
	                    if (!browsebooks.isEmpty()) {
	                    	SearchResultsScreen resultsScreen = new SearchResultsScreen(stage,browsebooks,this);
	                    	stage.setScene(resultsScreen.getSearchResultsScene());
	                    }
                	}    
	                    root.setCenter(browseByFieldPane);
                    break;
                case "Advanced Search":
                	Map<SearchField, String> criteria = new HashMap<>();
                	criteria.put(SearchField.ISBN, isbnField.getText());
                	criteria.put(SearchField.Title, titleField.getText());
                	criteria.put(SearchField.Author, authorField.getText());
                	criteria.put(SearchField.Genre, genreField.getText());
                	criteria.put(SearchField.Location, locationField.getText());
                	criteria.put(SearchField.Format, formatField.getValue());
                	AdSEARCHOp adsearch = new AdSEARCHOp(criteria);
                	List<Book> adbooks = adsearch.AdvancedSearch();
                    if (!adbooks.isEmpty()) {
                    	SearchResultsScreen resultsScreen = new SearchResultsScreen(stage, adbooks, this);
                    	stage.setScene(resultsScreen.getSearchResultsScene());
                    	isbnField.clear();
                    	titleField.clear();
                    	authorField.clear();
                    	genreField.clear();
                    	locationField.clear();
                    	formatField.getSelectionModel().clearSelection();
                    }
                    root.setCenter(advancedSearchPane);
                    break;
            }
        });
        
        
        //search mode combo box
        ComboBox<String> searchModeComboBox = new ComboBox<>();
        searchModeComboBox.getItems().addAll("Default Search", "Browse by Field", "Advanced Search");
        searchModeComboBox.setValue("Default Search");
        searchModeComboBox.setOnAction(event -> {
            String selectedMode = searchModeComboBox.getValue();
            switch (selectedMode) {
                case "Default Search":
                    root.setCenter(defaultSearchPane);
                    defaultSearchPane.add(addButton, 0, 0);
                    defaultSearchPane.add(SearchButton, 4, 0);
                    defaultSearchPane.add(searchModeComboBox, 5, 0);
                    SearchMode = selectedMode;
                    break;
                case "Browse by Field":
                    root.setCenter(browseByFieldPane);
                    browseByFieldPane.add(addButton, 0, 0);
                    browseByFieldPane.add(SearchButton, 4, 0);
                    browseByFieldPane.add(searchModeComboBox, 5, 0);
                    SearchMode = selectedMode;                  
                    break;
                case "Advanced Search":
                    root.setCenter(advancedSearchPane);
                    advancedSearchPane.add(addButton, 0, 0);
                    advancedSearchPane.add(SearchButton, 4, 0);                   
                    advancedSearchPane.add(searchModeComboBox, 5, 0);
                    SearchMode = selectedMode;
                    break;
            }
        });
        
        
        //first case
        defaultSearchPane.add(addButton, 0, 0);
        defaultSearchPane.add(SearchButton, 4, 0);
        defaultSearchPane.add(searchModeComboBox, 5, 0);
        //anything on the rootpane
        root.setCenter(defaultSearchPane); // default case
        //SCENE
        searchScene = new Scene(root, 680, 300);
    }
    public Scene getSearchScene() {
        return searchScene;
    }
}
