package com.example.book_management_system;

import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



public class EDITOp extends ADEController{
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private String location;
    private String format;
    private String additionalInfo;
	private final Book selectedBook;
	
    public EDITOp(String isbn, String title, String author,  String genre, String location, String format,String description,Book selectedBook) {
    	this.isbn=isbn;
    	this.title=title;
    	this.author=author;
    	this.genre=genre;
    	this.location=location;
    	this.format=format;
    	this.additionalInfo=description;
		this.selectedBook = selectedBook;    	   	
    }
    public boolean isChange() {
    	//Check if changes were made by comparing the fields with the original book
        if (!this.isbn.equals(selectedBook.getIsbn())) {
            return true;
        }
        if (!this.title.equals(selectedBook.getTitle())) {
            return true;
        }
        if (!this.author.equals(selectedBook.getAuthor())) {
            return true;
        }
        if (!this.genre.equals(selectedBook.getGenre())) {
            return true;
        }
        if (!this.location.equals(selectedBook.getLocation())) {
            return true;
        }
        if (!this.format.equals(selectedBook.getFormat())) {
            return true;
        }
        if (!this.additionalInfo.equals(selectedBook.getAdditionalInfo())) {
            return true;
        }
        //Flag error to user and return false
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("No changes were made.");
        alert.showAndWait();        
        return false;    	
    }
    
    public boolean editBook() {
    	//make adjustments to sqldao
    	if (!this.isChange()) {
    		return false;
    	}
    	if (!this.isInputValid(isbn, title, author, genre,location, format)) {
    		return false;
    	}
    	Book updatedBook = new Book(isbn, title, author,  genre, location, format, additionalInfo);
		boolean success = false;
		try {
			success = this.DatabaseAccess.updateBook(updatedBook);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}
}
