package com.example.book_management_system;

import java.util.List;
import java.sql.SQLException;


public interface BookDAOInterface {
    public boolean addBook(Book book) throws SQLException;
    public boolean updateBook(Book book) throws SQLException;
    public boolean deleteBook(String isbn) throws SQLException;
    public boolean isBookExists(String isbn) throws SQLException;
    public List<Book> SearchBooks(String query,SearchField searchfield) throws SQLException;
}