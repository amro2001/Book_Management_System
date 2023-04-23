package com.example.book_management_system;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SEARCHOp extends SearchController {
    private String query;
    private SearchField searchfield;
    public SEARCHOp(String query,SearchField searchfield) {
        this.query=query;
        this.searchfield = searchfield;                
    }
    public List<Book> DefaultSearch() {
        List<Book> Searchedbooks= new ArrayList<>();
        try {
            Searchedbooks = bookControl.SearchBooks(query, searchfield);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        isSearchEmpty(Searchedbooks);
        return Searchedbooks;
    }
}