package com.example.progetto_drone.controller;
import com.example.progetto_drone.ChangeWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;


public class LogController {
    //utente: admin
    //password: admin

    @FXML
    private TextField password;

    @FXML
    private TextField utente;

    @FXML
    private Button button_Invia;

    @FXML
    void onInvia(ActionEvent event) {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setContentText("Password o nome utente errato");
        if(password.getText().equals("admin") && utente.getText().equals("admin")){
            ChangeWindow.changeWindow("menu.fxml","Menu");
            Stage ss=(Stage)button_Invia.getScene().getWindow();
            ss.close();
        }
        else{
            password.setText("");
            utente.setText("");
            alert.show();
        }
    }


}
