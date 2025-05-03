package com.example.progetto_drone.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class VincitoriController {
    @FXML
    private Button btn_indietro;

    @FXML
    private TableColumn<?, ?> tbc_cognome;

    @FXML
    private TableColumn<?, ?> tbc_nome;

    @FXML
    private TableColumn<?, ?> tbc_puntiTotali;

    @FXML
    private TableView<?> tbw_tabella;

    @FXML
    private TextField txf_primoClassificato;

    @FXML
    private TextField txf_secondoClassificato;

    @FXML
    private TextField txf_terzoClassificato;

    @FXML
    void onIndietro(ActionEvent event) {

    }
}
