package com.example.progetto_drone.controller;

import com.example.progetto_drone.ChangeWindow;
import com.example.progetto_drone.altro.Lancio;
import com.example.progetto_drone.altro.Partecipante;
import com.example.progetto_drone.altro.PartecipanteClassifica;
import com.example.progetto_drone.altro.Trofeo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class VincitoriController {
    @FXML
    private Button btn_indietro;

    @FXML
    private TableColumn<PartecipanteClassifica, String> tbc_cognome;

    @FXML
    private TableColumn<PartecipanteClassifica, String> tbc_nome;

    @FXML
    private TableColumn<PartecipanteClassifica, String> tbc_puntiTotali;

    @FXML
    private TableView<PartecipanteClassifica> tbw_tabella;

    @FXML
    private TextField txf_primoClassificato;

    @FXML
    private TextField txf_secondoClassificato;

    @FXML
    private TextField txf_terzoClassificato;

    @FXML
    public void initialize(){
        //creo una nuova lista ordinata
        ArrayList<PartecipanteClassifica> ordinati = new ArrayList<>();

        //aggiungere all'Array list i partecipanti con nome, cognome e puntiTotali
        for (Partecipante p : ClassificaController.listaPartecipanti) {
            //prende il lancio associato a ogni partecipante attraverso la lista lanciPerPartecipante
            Lancio lancio = ClassificaController.lanciPerPartecipante.get(p);

            //se lancio il esiste, prende i punti convertendoli da String a int, senò imposta punti = 0
            int punti = (lancio != null) ? Integer.parseInt(lancio.getPunti()) : 0;

            //aggiungo alla lista creata il partecipante che metterò poi nella tabella
            ordinati.add(new PartecipanteClassifica(p.getNome(), p.getCognome(), punti));
        }

        //ordino la lista dal più grande al più piccolo
        ordinati.sort((p1, p2) -> Integer.compare(p2.getPuntiTotali(), p1.getPuntiTotali()));

        //creo un observableList dopo gli passo classifica
        ObservableList<PartecipanteClassifica> classifica = FXCollections.observableArrayList(ordinati);
        tbw_tabella.setItems(classifica);

        // Configurare le colonne della TableView
        tbc_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbc_cognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        tbc_puntiTotali.setCellValueFactory(new PropertyValueFactory<>("puntiTotali"));

        // visualizzare il primo, secondo e terzo classificato
        if (!ClassificaController.listaPartecipanti.isEmpty()) {
            txf_primoClassificato.setText(ClassificaController.listaPartecipanti.get(0).getNome() + " " + ClassificaController.listaPartecipanti.get(0).getCognome());
        }
        if (ClassificaController.listaPartecipanti.size() > 1) {
            txf_secondoClassificato.setText(ClassificaController.listaPartecipanti.get(1).getNome() + " " + ClassificaController.listaPartecipanti.get(1).getCognome());
        }
        if (ClassificaController.listaPartecipanti.size() > 2) {
            txf_terzoClassificato.setText(ClassificaController.listaPartecipanti.get(2).getNome() + " " + ClassificaController.listaPartecipanti.get(2).getCognome());
        }
    }

    @FXML
    void onIndietro(ActionEvent event) {
        ChangeWindow.changeWindow("menu.fxml","Menu' Trofei");
        Stage ss=(Stage) btn_indietro.getScene().getWindow();
        ss.close();
    }
}
