package com.example.progetto_drone;

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
    private CheckBox ckb_continua;

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
        //gestione della tabella

    }

    @FXML
    void onNuovoTrofeo(ActionEvent event) {
        ChangeWindow.changeWindow("nuovoTrofeo.fxml","NUovo Trofeo");
        Stage ss=(Stage) btn_nuovoTrofeo.getScene().getWindow();
        ss.close();
    }

    @FXML
    void onVisualizzaVincitori(ActionEvent event) {

    }
}