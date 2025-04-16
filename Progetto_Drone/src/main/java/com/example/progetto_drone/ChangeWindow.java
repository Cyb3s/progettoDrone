package com.example.progetto_drone;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeWindow {
    public static void changeWindow(String fxml,String title){
        try {
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(ChangeWindow.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
