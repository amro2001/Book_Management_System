# Book Management System  

## Project Description
Our book management system is a comprehensive and user-friendly solution for managing personal book collections. Whether it's a personal book collection or a large organizational library, our book management system provides a robust and user-friendly solution for efficient book management. Developed using the Java programming language and JavaFX framework, the system provides a professional and modern graphical user interface that is intuitive and easy to use. The application also offers users the ability to choose between SQLite or MySQL as the database backend in order to allow flexibility in database selection.
 
With either database, the system stores and manages comprehensive information about books, including their title, author, ISBN, location, genre, format, and additional information. Users can quickly and easily add new books to the system, or search for books using various search parameters such as ISBN, title, author etc. In addition to basic search functionality, the system also offers advanced search and browse by field capabilities allowing users to efficiently locate the desired books. Furthermore, users can conveniently modify or delete their book by double-clicking on the book from the search results, providing a seamless and efficient way to manage their book collections.

## Zip Contents
The ZIP file contains 2 items (apart from this README). 

 - **Book_Management**
 This is the project folder, it contains all source code, resources, a build.xml file and built class files. Instructions on how to use these for development use will follow in this document.
 - **Book Management System V2-0.jar**
 This executable jar file contains the project with all required dependencies. Instructions on how to run the jar file from Command Prompt with the correct VM arguments will follow in this document. The jar does **not** support SQLite usage at the moment.

## Software Prerequisites

 - **Java Development Kit (JDK)**
 Make sure to install the latest version of JDK for your operating system from the [Oracle](https://www.oracle.com/ca-en/java/technologies/downloads/#jdk20-windows) website. 
 - **JavaFX** 
 Starting from JDK 11, JavaFX is no longer included with the JDK and needs to be downloaded and installed separately. You can download the latest version of JavaFX for your operating system from the Gluonhq [website](https://gluonhq.com/products/javafx/). Here's a helpful [guide](https://openjfx.io/openjfx-docs/#introduction) on setting up JavaFX on different IDEs and operating systems.
 - **MySQL**
 You'll need to download and install the latest version of [MySQL Community Server](https://dev.mysql.com/downloads/installer/) from the official MySQL website for your operating system. In the installer make sure to select MySQL Server 8.0 (under MySQL servers), MySQL Shell 8.0 (under Applications -> MySQL Shell) and  Connector/J  8.0 (under MySQL connectors).
**IMPORTANT** 
When configuring the MySQL server set the root user password to admin and ensure the port is set to 3306. The application currently does not have support for a custom server.
 - **SQLite**
You will require the SQLite JDBC jar in order to use the SQLite Database functionality. This can be downloaded from their [Github](https://github.com/xerial/sqlite-jdbc/releases). No additional steps are required as an initialized SQLite local database file is included with the project folder.
 

**Setting Up MySQL server**
The MySQL server needs to be setup in order for the application to have the required functionalities. The steps below need to be performed once before the user can begin development or running the system.

 - Open the MySQL 8.0 Command Line Client on your computer.
 - Type your MySQL password (admin) when prompted and press Enter. 
 - Type `CREATE DATABASE book_management_system;` and press Enter to create the database.
 - Type `USE book_management_system;` and press Enter to select the database.
 - Type the following command to create the "books" table: `CREATE TABLE books( isbn VARCHAR(17) NOT NULL, title TEXT NOT NULL, author TEXT NOT NULL, genre TEXT NOT NULL, location TEXT NOT NULL, format TEXT NOT NULL, additional_information TEXT NULL, PRIMARY KEY (isbn) );` 
 - Press Enter to execute the command.

 You should see a message that says "Query OK, X rows affected" (where X is the number of rows affected). Close the MySQL command line client by typing `exit;` and pressing Enter.

For convenience you can also copy and paste the entire script into the Command Line Client. Here's the complete script: 

    CREATE DATABASE book_management_system; USE book_management_system; CREATE TABLE books( isbn VARCHAR(17) NOT NULL, title TEXT NOT NULL, author TEXT NOT NULL, genre TEXT NOT NULL, location TEXT NOT NULL, format TEXT NOT NULL, additional_information TEXT NULL, PRIMARY KEY (isbn) );


## Project Development from an IDE
In order to run the project from an IDE you will first need to complete the software prerequisites and have your preferred IDE installed. 

You will first need to import the project into your IDE (The project was made on Eclipse). Your IDE will require specific plugins for JavaFX (for example Eclipse uses e(fx)clipse), and you will need to specify to the IDE the locations of your JavaFX SDK, Connector/J jar file and SQLite JDBC jar. We recommend creating separate User Libraries that store the JavaFX jars, the Connector/J jar, and the SQLite JDBC jar. Here's a simple [guide](https://websparrow.org/misc/how-to-create-user-library-in-eclipse-ide) on how to create user libraries in Eclipse. You will need to import all three libraries into the project. Here are the steps to do this in Eclipse: 

 - Right click on your project in the Package Explorer.
 - Navigate to Build Path -> and click on configure Build Path
 - Click on "Modulepath" and click on "Add Library..."
 - Select User Library and click next. Select your JavaFX user Library and click finish.
 - Click on "classpath" and click on "Add Library..."
 - Select User Library and click next. Select your Connector/J user Library and click finish.
 - Click on "classpath" and click on "Add Library..."
 - Select User Library and click next. Select your SQLiteJDBC user Library and click finish.
 
 You should now be able to build and run the Application from your IDE. The main method is contained within the Start class, ensure this is the class selected by your IDE for the run configuration.

## Running from Executable Jar File
In order to run the project from the executable Jar file you will first need to complete the software prerequisites. (SQLite is **not** compatible with running from jar)

Navigate to the location of the extracted application files using the "cd" command in the command prompt. Enter the following command in the command prompt to run the application jar file:

    java --module-path "path of your JavaFX SDK lib file" --add-modules javafx.controls,javafx.graphics,javafx.base,javafx.fxml -jar "path to JAR"Book Management System V2-0.jar

Below is an example from Windows 11 Command Prompt.
![](https://cdn.discordapp.com/attachments/842952603720613898/1090448495074357298/image.png)

In this example "C:\Users\amroe\Desktop\FULL DESKTOP\javafx-sdk-19.0.2.1\lib" contains the required JavaFX modules, and the Jar is located in "C:\Users\amroe\Desktop\Book Management APP\". The format of the path will differ based on your operating system.

## Contact for bugs
Amro Elgammal - aelgammal@mun.ca
Umair Loya - muiloya@mun.ca


## License
MIT License
Copyright (c) 2023 Mohammad Umair Loya, Amro Elgammal

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions: The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software. 

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.





