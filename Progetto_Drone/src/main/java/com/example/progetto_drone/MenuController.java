package com.example.progetto_drone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MenuController {
    @FXML
    private Button btn_nuovoTrofeo;

    @FXML
    private Button btn_visualizzaVincitori;

    @FXML
    private ChoiceBox<?> chb_anno;

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
    void onNuovoTrofeo(ActionEvent event) {

    }

    @FXML
    void onVisualizzaVincitori(ActionEvent event) {

    }
}