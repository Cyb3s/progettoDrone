package com.example.progetto_drone.controller;

import com.example.progetto_drone.ChangeWindow;
import com.example.progetto_drone.altro.Lancio;
import com.example.progetto_drone.altro.Partecipante;
import com.example.progetto_drone.altro.Trofeo;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleStringProperty;
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
    private TableColumn<Partecipante, String> tbc_nome;

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

        tbc_nome.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNome() + " " + cellData.getValue().getCognome()));

        tbc_tempo.setCellValueFactory(cellData -> {
            Lancio lancio = lanciPerPartecipante.get(cellData.getValue());
            return new SimpleStringProperty(lancio != null ? lancio.getTempo() : "");
        });
        tbc_tempo.setCellFactory(TextFieldTableCell.forTableColumn());
        tbc_tempo.setOnEditCommit(event -> {
            Partecipante p = event.getRowValue().getPartecipante();
            Lancio lancio = lanciPerPartecipante.get(p);
            if (lancio != null) {
                lancio.setTempo(event.getNewValue());
                tableView.refresh();
            }
        });

        tbc_penalita.setCellValueFactory(cellData -> {
            Lancio lancio = lanciPerPartecipante.get(cellData.getValue());
            return new SimpleStringProperty(String.valueOf(lancio != null ? lancio.getPenalita() : 0));
        });
        tbc_penalita.setCellFactory(TextFieldTableCell.forTableColumn());
        tbc_penalita.setOnEditCommit(event -> {
            Partecipante p = event.getRowValue().getPartecipante();
            Lancio lancio = lanciPerPartecipante.get(p);
            if (lancio != null) {
                try {
                    lancio.setPenalita(Integer.parseInt(event.getNewValue()));
                    lancio.aggiornaPunteggio();
                    tableView.refresh();
                } catch (NumberFormatException e) {
                    System.err.println("Penalità non valida: " + event.getNewValue());
                }
            }
        });

        tbc_punti.setCellValueFactory(cellData -> {
            Lancio lancio = lanciPerPartecipante.get(cellData.getValue());
            return new SimpleStringProperty(lancio != null ? lancio.getPunti() : "0");
        });

        tableView.setItems(listaPartecipanti);

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

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long milliTrascorsi = System.currentTimeMillis() - tempoIniziale;
                lbl_titolo.setText(formattaTempo(milliTrascorsi));
            }
        };
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

            Partecipante partecipante = tableView.getSelectionModel().getSelectedItem();
            if (partecipante != null) {
                Lancio lancio = lanciPerPartecipante.get(partecipante);
                if (lancio != null) {
                    long tempoTrascorso = System.currentTimeMillis() - tempoIniziale;
                    lancio.aggiungiTempo(tempoTrascorso);
                    tableView.refresh();
                }
            }
        }
    }

    @FXML
    void onLancia(ActionEvent event) {
        Partecipante p = tableView.getSelectionModel().getSelectedItem();
        if (p != null) {
            Lancio lancio = lanciPerPartecipante.get(p);
            long tempoMillis = System.currentTimeMillis() - tempoIniziale;
            lancio.aggiungiTempo(tempoMillis);
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
