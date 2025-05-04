package com.example.progetto_drone.controller;

import com.example.progetto_drone.ChangeWindow;
import com.example.progetto_drone.altro.Lancio;
import com.example.progetto_drone.altro.Partecipante;
import com.example.progetto_drone.altro.Trofeo;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.util.*;

public class ClassificaController {
    @FXML
    private Button btn_avviaCronometro;

    @FXML
    private Button btn_homePage;

    @FXML
    private Button btn_lancio;

    @FXML
    private Button btn_fermaCronometro;

    @FXML
    private Button btn_prossimaGara;

    @FXML
    private Button btn_salvaGara;

    @FXML
    private Label lbl_titolo;

    @FXML
    private TableColumn<Trofeo, String> tbc_nome;

    @FXML
    private TableColumn<Trofeo, String> tbc_numLanci;

    @FXML
    private TableColumn<Lancio, String> tbc_penalita;

    @FXML
    private TableColumn<Lancio, String> tbc_punti;

    @FXML
    private TableColumn<Lancio, String> tbc_tempo;

    @FXML
    private TableView<Partecipante> tableView;

    public static ObservableList<Partecipante> listaPartecipanti = FXCollections.observableArrayList();
    public static Map<Partecipante, Lancio> lanciPerPartecipante = new HashMap<>();


    private long tempoIniziale;
    private AnimationTimer timer;
    private boolean cronometroAttivo = false;

    public void initialize() {
        tableView.setEditable(true);

        tbc_nome.setCellValueFactory(new PropertyValueFactory<Trofeo,String>("nomeTrofeo"));
        tbc_numLanci.setCellValueFactory(new PropertyValueFactory<Trofeo,String>("nLanci"));
        tbc_penalita.setCellValueFactory(new PropertyValueFactory<Lancio,String>("penalita"));
        tbc_punti.setCellValueFactory(new PropertyValueFactory<Lancio,String>("punti"));
        tbc_tempo.setCellValueFactory(new PropertyValueFactory<Lancio,String>("tempo"));

        tableView.setItems(listaPartecipanti);

        tbc_tempo.setCellFactory(TextFieldTableCell.forTableColumn());
        tbc_tempo.setOnEditCommit(event -> {
            Partecipante partecipante = event.getRowValue().getPartecipante();
            Lancio lancio = lanciPerPartecipante.get(partecipante);
            if (lancio != null) {
                lancio.setTempo(event.getNewValue());
                tableView.refresh();
            }
        });

        tbc_penalita.setCellFactory(TextFieldTableCell.forTableColumn());
        tbc_penalita.setOnEditCommit(event -> {
            Partecipante partecipante = event.getRowValue().getPartecipante();
            Lancio lancio = lanciPerPartecipante.get(partecipante);
            if (lancio != null) {
                try {
                    int penalita = Integer.parseInt(event.getNewValue());
                    lancio.setPenalita(penalita);
                    tableView.refresh();
                } catch (NumberFormatException e) {
                    System.err.println("Valore penalità non valido: " + event.getNewValue());
                }
            }
        });

        timer = new AnimationTimer() {            @Override
        public void handle(long now) {
            long milliTrascorsi = System.currentTimeMillis() - tempoIniziale;
            lbl_titolo.setText(formattaTempo(milliTrascorsi));
        }
        };

        Partecipante p1 = new Partecipante("Luca", "Bianchi");
        Partecipante p2 = new Partecipante("Giulia", "Verdi");
        Partecipante p3 = new Partecipante("Alessandro", "Russo");
        Partecipante p4 = new Partecipante("Francesca", "Ferrari");
        Partecipante p5 = new Partecipante("Matteo", "Romano");
        Partecipante p6 = new Partecipante("Chiara", "Moretti");

        listaPartecipanti.addAll(p1, p2, p3, p4, p5, p6);

        lanciPerPartecipante.put(p1, new Lancio());
        lanciPerPartecipante.put(p2, new Lancio());
        lanciPerPartecipante.put(p3, new Lancio());
        lanciPerPartecipante.put(p4, new Lancio());
        lanciPerPartecipante.put(p5, new Lancio());
        lanciPerPartecipante.put(p6, new Lancio());
    }

    @FXML
    void onAvviaCrnm(ActionEvent event) {
        if (!cronometroAttivo) {
            tempoIniziale = System.currentTimeMillis();
            timer.start();
            cronometroAttivo = true;
        }
    }

    @FXML
    void onFermaCrnm(ActionEvent event) {
        if (cronometroAttivo) {
            timer.stop();
            cronometroAttivo = false;
        }
    }

    @FXML
    void onLancia(ActionEvent event) {
        Partecipante p = tableView.getSelectionModel().getSelectedItem();
        if (p != null) {
            Lancio lancio = lanciPerPartecipante.get(p);
            lancio.aggiungiTempo(System.currentTimeMillis() - tempoIniziale);

            tableView.refresh();
        }

    }

    @FXML
    void onTornaHome(ActionEvent event) {
        ChangeWindow.changeWindow("menu.fxml","Menu");
        Stage ss=(Stage) btn_homePage.getScene().getWindow();
        ss.close();
    }

    @FXML
    void onProssima(ActionEvent event) {
        lbl_titolo.setText("Nuova Gara");
        listaPartecipanti.clear();
    }

    @FXML
    void onSalvaGara(ActionEvent event) {
    }

    private String formattaTempo(long tempo) {
        long secondi = tempo / 1000;
        long minuti = secondi / 60;
        secondi = secondi % 60;
        long millisecondi = tempo % 1000;
        return String.format("%02d:%02d.%03d", minuti, secondi, millisecondi);
    }
}
