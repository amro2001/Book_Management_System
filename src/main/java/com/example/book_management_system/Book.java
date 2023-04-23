package com.example.book_management_system;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private String location;
    private String format;
    private String additionalInfo;

    public Book(String isbn, String title, String author, String genre, String location, String format, String additionalInfo) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.location = location;
        this.format = format;
        this.additionalInfo = additionalInfo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}

