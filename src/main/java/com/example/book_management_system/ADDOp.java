package com.example.book_management_system;

import java.sql.SQLException;
import javafx.scene.control.Alert;

public class ADDOp extends ADEController{
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private String location;
    private String format;
    private String additionalInfo;
    public ADDOp(String isbn, String title, String author,  String genre, String location, String format,String description) {
    	this.isbn=isbn;
    	this.title=title;
    	this.author=author;
    	this.genre=genre;
    	this.location=location;
    	this.format=format;
    	this.additionalInfo=description;
    	
    }
	public boolean addbook() throws SQLException {
    	VerifyByISBN verification=new VerifyByISBN();
    	boolean verify=verification.isValid(isbn);
    	isbn=isbn.replaceAll("-", "").replaceAll(" ", "").trim();
    	boolean bookExists=DatabaseAccess.isBookExists(isbn);
		Book newBook= new Book(isbn, title, author,  genre, location, format, additionalInfo);

		if (!this.isInputValid(isbn,title, author,genre,location,format)) {
			return false;
		}
		else if (!verify){
			  // Display an error message
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText(null);
          alert.setContentText("Invalid ISBN!");
          alert.showAndWait();
          return false;
		}
		else if (bookExists) {
			
			  // Display an error message
	       Alert alert = new Alert(Alert.AlertType.ERROR);
	       alert.setTitle("Error");
	       alert.setHeaderText(null);
	       alert.setContentText("Book Already Exists!");
	       alert.showAndWait();
	       return false;
		}
		else {
			return this.DatabaseAccess.addBook(newBook);
		}


	}
}