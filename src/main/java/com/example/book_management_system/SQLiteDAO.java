package com.example.book_management_system;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteDAO implements BookDAOInterface {

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite::resource:book_management_system_1.db");
    }

    public boolean addBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (isbn, title, author, genre, location, format, additional_information) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement BookStatement = conn.prepareStatement(sql)) {
            BookStatement.setString(1, book.getIsbn());
            BookStatement.setString(2, book.getTitle());
            BookStatement.setString(3, book.getAuthor());
            BookStatement.setString(4, book.getGenre());
            BookStatement.setString(5, book.getLocation());
            BookStatement.setString(6, book.getFormat());
            BookStatement.setString(7, book.getAdditionalInfo());
            BookStatement.executeUpdate();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBook(Book book) throws SQLException {
        String sql = "UPDATE books SET title=?, author=?, genre=?, location=?, format=?, additional_information=? WHERE isbn=?";
        try (Connection conn = getConnection();
             PreparedStatement bookStatement = conn.prepareStatement(sql)) {
            bookStatement.setString(1, book.getTitle());
            bookStatement.setString(2, book.getAuthor());
            bookStatement.setString(3, book.getGenre());
            bookStatement.setString(4, book.getLocation());
            bookStatement.setString(5, book.getFormat());
            bookStatement.setString(6, book.getAdditionalInfo());
            bookStatement.setString(7, book.getIsbn());
            bookStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBook(String isbn) throws SQLException {
        String sql = "DELETE FROM books WHERE isbn=?";
        try (Connection conn = getConnection();
             PreparedStatement BookStatement = conn.prepareStatement(sql)) {
            BookStatement.setString(1, isbn);
            BookStatement.executeUpdate();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isBookExists(String isbn) throws SQLException {
        String sql = "SELECT COUNT(*) FROM books WHERE isbn=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, isbn);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
                return false;
            }
        }
    }

    public List<Book> SearchBooks(String query, SearchField searchfield) throws SQLException {
        String sql = "SELECT * FROM books WHERE " + searchfield.name().toLowerCase() + " LIKE ?";
        List<Book> books = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + query + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String isbn = rs.getString("isbn");
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    String genre = rs.getString("genre");
                    String additionalInfo = rs.getString("additional_information");
                    String location = rs.getString("location");
                    String format = rs.getString("format");
                    Book book = new Book(isbn, title, author, genre, location, format, additionalInfo);
                    books.add(book);
                }
            }
        }
        return books;
    }
}