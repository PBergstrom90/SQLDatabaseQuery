# Webshop Database Integration

This project is a Java-based application designed to interact with a webshop database. The application provides a command-line interface for querying and displaying information related to customers, products, and sales.

## Key Features:

* Connects to a MySQL database.
* Provides a text-based menu for user interaction.
* Allows querying the database for specific information.

## Project Structure:

* **src/SystemIntegration**: Contains the Java source code files.
    * `Main.java`: The program entry point, starts the menu.
    * `Menu.java`: Handles user interaction through the text-based menu.
    * `Repository.java`: Connects to the database, executes queries, and retrieves data.
* **settings.properties**: Stores database connection details (connection string, username, password).
* **SQL/Webshop_DDL.sql**: Database schema definition script (tables, relationships).
* **SQL/Webshop_DML.sql**: Contains pre-defined example queries used by the program.
* **resources/ (optional)**: Folder for additional resources like an ER diagram image.

## How to Run:

1. Ensure you have a MySQL database server running with the webshop schema created (refer to `SQL/Webshop_DDL.sql`).
2. Update the `settings.properties` file with your specific database connection details.
3. Compile and run the `Main.java` class.

## Resources:

### ER Diagram:
![Application Screenshot](resources/ER%20Diagram.png)

### Relational Diagram
[Relational Diagram as PDF](resources/Relational%20Diagram.pdf)

### License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
