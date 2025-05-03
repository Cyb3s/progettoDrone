
package com.example.progetto_drone.controller;

import com.example.progetto_drone.ChangeWindow;
import com.example.progetto_drone.altro.Trofei;
import com.example.progetto_drone.altro.Trofeo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class NuovoTrofeoController {

    @FXML
    private Button button_annulla;

    @FXML
    private Button button_conferma;

    @FXML
    private CheckBox choiceScarta;

    @FXML
    private TextField text_anno;

    @FXML
    private ChoiceBox<String> choice_tLanci;

    @FXML
    private TextField text_nGare;

    @FXML
    private TextField text_nLanci;

    @FXML
    private TextField text_nome;

    @FXML
    void initialize(){
        choice_tLanci.getItems().addAll("60","45","30");
        choice_tLanci.setValue("45");
    }

    @FXML
    void onAnnulla(ActionEvent event) {
        ChangeWindow.changeWindow("menu.fxml","Menu");
        Stage ss=(Stage)button_annulla.getScene().getWindow();
        ss.close();
    }

    @FXML
    void onConferma(ActionEvent event) {
        if(text_nGare.getText().isEmpty() || text_nGare.getText().isEmpty() || text_anno.getText().isEmpty() || text_nLanci.getText().isEmpty()){
            Alert alert=new Alert( Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setContentText("Riempi tutti i campi");
            alert.show();
        }else{
            try {
                Integer nGare = Integer.parseInt(text_nGare.getText());
                Integer nLanci = Integer.parseInt(text_nLanci.getText());
                Integer tempo = Integer.parseInt(choice_tLanci.getValue());
                Integer anno=  Integer.parseInt(text_anno.getText());
                Trofei.listaTrofei.add(new Trofeo(nGare,text_nome.getText(),anno,nLanci,tempo,choiceScarta.isSelected()));
                ChangeWindow.changeWindow("partecipanti.fxml","Partecipanti");
                Stage ss=(Stage)button_conferma.getScene().getWindow();
                ss.close();
            } catch (NumberFormatException e) {
                Alert alert=new Alert( Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setContentText("Valori non corretti");
                alert.show();
            }
        }

    }


}


