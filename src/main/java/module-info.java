module com.example.timeserver {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.timeserver to javafx.fxml;
    exports com.example.timeserver;
}