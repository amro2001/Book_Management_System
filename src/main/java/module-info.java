module com.example.book_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.base;


    opens com.example.book_management_system to javafx.graphics, javafx.fxml;
    exports com.example.book_management_system;
}