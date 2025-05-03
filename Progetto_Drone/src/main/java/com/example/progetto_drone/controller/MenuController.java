package com.example.progetto_drone.controller;

import com.example.progetto_drone.ChangeWindow;
import com.example.progetto_drone.altro.Trofei;
import com.example.progetto_drone.altro.Trofeo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MenuController {
    @FXML
    private Button btn_nuovoTrofeo;

    @FXML
    private Button btn_visualizzaVincitori;

    @FXML
    private ChoiceBox<String> chb_anno;

    @FXML
    private CheckBox ckb_conclusi;

    @FXML
    private CheckBox ckb_inCorso;

    @FXML
    private TableColumn<?, ?> tbc_data;

    @FXML
    private TableColumn<?, ?> tbc_nome;

    @FXML
    private TableView<?> tbw_tabella;

    @FXML
    private TextField txf_ricerca;

    @FXML
    public void initialize(){

        if(Trofei.listaTrofei.isEmpty()){
            chb_anno.setValue("Nessun trofeo");
        }else{
            chb_anno.getItems().add("Tutti");
            for(Trofeo trofeo : Trofei.listaTrofei){
                chb_anno.getItems().add(String.valueOf(trofeo.getDataInizio()));
            }
            chb_anno.setValue("Tutti");
        }

    }

    @FXML
    void onNuovoTrofeo(ActionEvent event) {
        ChangeWindow.changeWindow("nuovoTrofeo.fxml","Nuovo Trofeo");
        Stage ss=(Stage) btn_nuovoTrofeo.getScene().getWindow();
        ss.close();
    }

    @FXML
    void onInCorso(ActionEvent event) {
        if(ckb_conclusi.isSelected()) {
            ckb_conclusi.selectedProperty().setValue(false);
        }
    }

    @FXML
    void onConclusi(ActionEvent event) {
        if(ckb_inCorso.isSelected()){
            ckb_inCorso.selectedProperty().setValue(false);
        }
    }

    @FXML
    void onVisualizzaVincitori(ActionEvent event) {

    }
}