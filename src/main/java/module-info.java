module com.example.command {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.command to javafx.fxml;
    exports com.example.command;
}