package com.example.book_management_system;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public abstract class SearchController {
    protected BookDAOInterface bookControl = Start.DatabaseAccess;
    public boolean VerifyQuery() {
        return false;        
    }
    public boolean isSearchEmpty(List <Book> books) {
        if (books.isEmpty()){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("No Books Found");
            alert.setHeaderText(null);
            alert.setContentText("No books were found with the specified criteria.");
            alert.showAndWait();            
            return true;
        }
        return false;
    }    
}