package com.example.book_management_system;

import javafx.scene.control.Alert;

abstract class ADEController {

    protected BookDAOInterface DatabaseAccess= Start.DatabaseAccess;
    
    public boolean isInputValid(String isbn, String title, String author,String genre, String Location, String format){
        if (isbn.trim().isEmpty() || title.trim().isEmpty()||author.trim().isEmpty()|| Location.trim().isEmpty()|| genre == (null) || format == (null)) {
            // Display an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("One of the required fields is empty: ISBN, Title, Author, Genre, Location, Format");
            alert.showAndWait();
            return false;
        }
        else {
            return true;
        }
    }
}