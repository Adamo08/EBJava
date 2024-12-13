# EBJava (Examen Blanc)

## Overview
EBJava is a Java-based application built with JavaFX and Maven, designed to manage members in a system. The application allows users to add new members to a database (SQLite) with input validations, as well as load existing members from a CSV file.

## Features
- **Add New Members**: Users can input member information (name, surname, email, and phone) and validate the data before saving to the SQLite database.
- **CSV Integration**: Existing members can be loaded from a CSV file and displayed in a table format.
- **Data Validation**: Ensures that the email format and phone number follow proper patterns before adding members.

## Technologies Used
- **Java** (JDK 17)
- **Maven** for dependency management
- **JavaFX** for the user interface
- **SQLite** for database management
- **OpenCSV** for reading CSV files

## Setup
1. Clone the repository:
    ```bash
    git clone https://github.com/Adamo08/EBJava.git
    ```

2. Navigate to the project folder:
    ```bash
    cd EBJava
    ```

3. Build the project using Maven:
    ```bash
    mvn clean install
    ```

4. Run the application:
    ```bash
    mvn javafx:run
    ```

## Usage
- **Adding Members**: Fill in the required fields and click the "Add Member" button to insert a new member into the database.
- **Viewing Members**: Click the "Show CSV Members" button to load and display members from a CSV file.

## Database Schema
- **Membre**: Stores member information such as name, surname, email, and phone number.

## Contributing
- Contributions are welcome!
