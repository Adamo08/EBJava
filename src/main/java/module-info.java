module com.adamo.dljava {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.adamo.dljava to javafx.fxml;
    opens com.adamo.dljava.controller to javafx.fxml;
    requires static lombok;
    requires com.google.gson;
    requires java.sql;
    requires com.opencsv;

    exports com.adamo.dljava;
}