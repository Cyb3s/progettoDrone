module com.example.progetto_drone {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.progetto_drone to javafx.fxml;
    exports com.example.progetto_drone;
    exports com.example.progetto_drone.controller;
    opens com.example.progetto_drone.controller to javafx.fxml;
    exports com.example.progetto_drone.altro;
    opens com.example.progetto_drone.altro to javafx.fxml;
}