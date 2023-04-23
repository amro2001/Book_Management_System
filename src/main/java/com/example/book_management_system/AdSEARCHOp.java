package com.example.book_management_system;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdSEARCHOp extends SearchController {
    Map<SearchField,String> criteria;
    
    public AdSEARCHOp(Map<SearchField,String> criteria){
        this.criteria = criteria;
    }            
    public List<Book> AdvancedSearch(){
        List<Book> filteredbooks = new ArrayList<>();
        List<Book> SearchSet = new ArrayList<>();
        try {
            SearchSet = bookControl.SearchBooks(criteria.get(SearchField.ISBN), SearchField.ISBN);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Combine all search criteria into a single loop
        for (Book book : SearchSet) {
            boolean match = true;
            String title = criteria.get(SearchField.Title);
            String author = criteria.get(SearchField.Author);
            String genre = criteria.get(SearchField.Genre);
            String location = criteria.get(SearchField.Location);
            String format = criteria.get(SearchField.Format);

            if (title != null && !book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                match = false;
            }
            if (author != null && !book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                match = false;
            }
            if (genre != null && !book.getGenre().toLowerCase().contains(genre.toLowerCase())) {
                match = false;
            }
            if (location != null && !book.getLocation().toLowerCase().contains(location.toLowerCase())) {
                match = false;
            }
            if (format != null && !book.getFormat().toLowerCase().contains(format.toLowerCase())) {
                match = false;
            }


            if (match) {
                filteredbooks.add(book);
            }
            
        }
        isSearchEmpty(filteredbooks);

        return filteredbooks;
    }
}