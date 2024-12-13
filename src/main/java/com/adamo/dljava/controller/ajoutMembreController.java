package com.adamo.dljava.controller;

import com.adamo.dljava.dao.impl.MembreDaoImpl;
import com.adamo.dljava.model.Membre;
import com.adamo.dljava.service.MembreService;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Set;
import java.util.UUID;
import java.util.List;

public class ajoutMembreController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button addButton;

    @FXML
    private Button showCsvButton;

    @FXML
    private TableView<Membre> tableView;

    @FXML
    private TableColumn<Membre, String> nomColumn;

    @FXML
    private TableColumn<Membre, String> prenomColumn;

    @FXML
    private TableColumn<Membre, String> emailColumn;

    @FXML
    private TableColumn<Membre, String> phoneColumn;

    private final MembreDaoImpl membreDao = new MembreDaoImpl();
    private final MembreService membreService = new MembreService();

    // Regular expressions for email and phone validation
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_REGEX = "^[0-9]{10}$";

    @FXML
    public void handleAddButton(ActionEvent event) {
        // Validate inputs
        if (nomField.getText().isEmpty() || prenomField.getText().isEmpty() ||
                emailField.getText().isEmpty() || phoneField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields must be filled!");
            return;
        }

        // Validate email
        if (!isValidEmail(emailField.getText())) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Invalid email format!");
            return;
        }

        // Validate phone number
        if (!isValidPhone(phoneField.getText())) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Invalid phone number format!");
            return;
        }

        // Generate random ID for the member
        String id = UUID.randomUUID().toString();

        // Create a new Membre object
        Membre membre = new Membre(
                id,
                nomField.getText(),
                prenomField.getText(),
                emailField.getText(),
                phoneField.getText()
        );

        // Add the member to the database
        membreDao.addMembre(membre);

        // Show success message
        showAlert(Alert.AlertType.INFORMATION, "Success", "Member added successfully!");

        // Clear input fields
        clearFields();
    }

    @FXML
    public void handleShowCsvMembers(ActionEvent event) {
        // Load members from the CSV file
        List<Membre> membersList = membreService.chargerListeMembre("C:\\Users\\user\\IdeaProjects\\DLJava\\src\\main\\resources\\members.csv");
        // Create an observable list to bind with the TableView
        ObservableList<Membre> membersObservableList = FXCollections.observableArrayList(membersList);

        // Set the data for the TableView
        tableView.setItems(membersObservableList);

        // Make the TableView visible
        tableView.setVisible(true);
    }


    private boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    private boolean isValidPhone(String phone) {
        return phone.matches(PHONE_REGEX);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        nomField.clear();
        prenomField.clear();
        emailField.clear();
        phoneField.clear();
    }

    @FXML
    public void initialize() {
        // Custom callback to retrieve properties for each column
        nomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        prenomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        phoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
    }

}
