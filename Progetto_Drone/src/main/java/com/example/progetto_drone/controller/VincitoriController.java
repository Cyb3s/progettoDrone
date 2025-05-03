package com.example.progetto_drone.controller;

import com.example.progetto_drone.ChangeWindow;
import com.example.progetto_drone.altro.Trofeo;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VincitoriController {
    @FXML
    private Button btn_indietro;

    @FXML
    private TableColumn<Trofeo, String> tbc_cognome;

    @FXML
    private TableColumn<Trofeo, String> tbc_nome;

    @FXML
    private TableColumn<Trofeo, String> tbc_puntiTotali;

    @FXML
    private TableView<Trofeo> tbw_tabella;

    @FXML
    private TextField txf_primoClassificato;

    @FXML
    private TextField txf_secondoClassificato;

    @FXML
    private TextField txf_terzoClassificato;

    @FXML
    public void initialize(){
        //settare le label primo, secondo e terzo con il nome e cognome del vincitore
        //scrivere la classifica nella tabella

        // Ordinare la lista in base ai punti (dal più alto al più basso)
        /*classifica*/.sort((t1, t2) -> Integer.compare(t2.getPuntiTotali(), t1.getPuntiTotali()));

        // Popolare la TableView con la classifica completa
        tbw_tabella.setItems(FXCollections.observableArrayList(/*classifica*/));

        // Configurare le colonne della TableView
        tbc_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbc_cognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        tbc_puntiTotali.setCellValueFactory(new PropertyValueFactory<>("puntiTotali"));

        // visualizzare il primo, secondo e terzo classificato
        if (!/*classifica*/.isEmpty()) {
            txf_primoClassificato.setText(/*classifica*/.get(0).getNome() + " " + /*classifica*/.get(0).getCognome());
        }
        if (partecipanti.size() > 1) {
            txf_secondoClassificato.setText(/*classifica*/.get(1).getNome() + " " + /*classifica*/.get(1).getCognome());
        }
        if (partecipanti.size() > 2) {
            txf_terzoClassificato.setText(/*classifica*/.get(2).getNome() + " " + /*classifica*/.get(2).getCognome());
        }

    }

    @FXML
    void onIndietro(ActionEvent event) {
        ChangeWindow.changeWindow("menu.fxml","Menu' Trofei");
        Stage ss=(Stage) btn_indietro.getScene().getWindow();
        ss.close();
    }
}
