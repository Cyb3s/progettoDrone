module com.example.progetto_drone {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.progetto_drone to javafx.fxml;
    exports com.example.progetto_drone;
}