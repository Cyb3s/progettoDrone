
package com.example.progetto_drone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NuovoTrofeoController {

    @FXML
    private Button button_annulla;

    @FXML
    private Button button_conferma;

    @FXML
    private CheckBox choiceScarta;

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
        ChangeWindow.changeWindow("menu.fxml","Menu");
        Stage ss=(Stage)button_annulla.getScene().getWindow();
        ss.close();
    }

    @FXML
    void onConferma(ActionEvent event) {
        ChangeWindow.changeWindow("partecipanti.fxml","Partecipanti");
        Stage ss=(Stage)button_annulla.getScene().getWindow();
        ss.close();
    }

    @FXML
    void onScarta(ActionEvent event) {
        //da implementare
    }

}


