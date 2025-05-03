package com.example.progetto_drone.controller;

import com.example.progetto_drone.ChangeWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PartecipantiController {

    @FXML
    private Button buttonAvanti;

    @FXML
    private Button buttonIndietro;

    @FXML
    void onAvanti(ActionEvent event) {
        ChangeWindow.changeWindow("classifica.fxml","Classifica");
        Stage ss=(Stage) buttonAvanti.getScene().getWindow();
        ss.close();
    }

    @FXML
    void onIndietro(ActionEvent event) {
        ChangeWindow.changeWindow("nuovoTrofeo.fxml","Nuovo trofeo");
        Stage ss=(Stage) buttonIndietro.getScene().getWindow();
        ss.close();
    }

}
