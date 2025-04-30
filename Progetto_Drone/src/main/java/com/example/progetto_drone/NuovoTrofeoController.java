package com.example.progetto_drone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class NuovoTrofeoController {

    @FXML
    private Button button_annulla;

    @FXML
    private Button button_conferma;

    @FXML
    private ChoiceBox<?> choice_anno;

    @FXML
    private ChoiceBox<?> choice_tLanci;

    @FXML
    private TextField text_nGare;

    @FXML
    private TextField text_nLanci;

    @FXML
    private TextField text_nome;

    @FXML
    void initialize(){
        //da implementare inizializazione
        
    }

    @FXML
    void onAnnulla(ActionEvent event) {
        //da implementare
    }

    @FXML
    void onConferma(ActionEvent event) {
        //da impplementare
    }

}

