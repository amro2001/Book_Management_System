package com.example.book_management_system;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class BROWSEOp extends SearchController {
    
    private SearchField searchfield;
    
    public BROWSEOp(SearchField searchfield){
        this.searchfield = searchfield;
    }
    
    
    
    public Set<String> BrowseByField(){
        
        SEARCHOp search = new SEARCHOp("",searchfield);
        Set<String> browseItems = new LinkedHashSet<>();
        Set<String> validation = new LinkedHashSet<>();
        List<Book> SearchSet = search.DefaultSearch();
        for (Book book : SearchSet) {
            if (searchfield.equals(SearchField.Author)) {    
                if(!(validation.contains(book.getAuthor().toLowerCase()))) {
                    browseItems.add(book.getAuthor());
                }
                validation.add(book.getAuthor().toLowerCase());
            }
            
            if (searchfield.equals(SearchField.Location)) {
                if(!(validation.contains(book.getLocation().toLowerCase()))) {
                    browseItems.add(book.getLocation());
                }
                validation.add(book.getLocation().toLowerCase());
            }
        }        
        return browseItems;        
    }

}
