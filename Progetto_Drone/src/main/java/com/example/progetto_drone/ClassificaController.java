package com.example.progetto_drone;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
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
    private TableColumn<Lancio, Integer> tbc_numLanci;

    @FXML
    private TableColumn<Lancio, Integer> tbc_penalita;

    @FXML
    private TableColumn<Lancio, String> tbc_punti;

    @FXML
    private TableColumn<Lancio, String> tbc_tempo;

    @FXML
    private TableView<Partecipante> tableView;

    private ObservableList<Partecipante> listaPartecipanti = FXCollections.observableArrayList();
    private Map<Partecipante, Lancio> lanciPerPartecipante = new HashMap<>();


    private long tempoIniziale;
    private AnimationTimer timer;
    private boolean cronometroAttivo = false;

    public void initialize() {
        tableView.setEditable(true);

        tbc_nome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        tbc_numLanci.setCellValueFactory(cellData -> cellData.getValue().numLanciProperty().asObject());
        tbc_penalita.setCellValueFactory(cellData -> cellData.getValue().penalitaProperty().asObject());
        tbc_punti.setCellValueFactory(cellData -> cellData.getValue().puntiProperty());
        tbc_tempo.setCellValueFactory(cellData -> cellData.getValue().tempoProperty());

        tableView.setItems(listaPartecipanti);

        tbc_tempo.setCellFactory(TextFieldTableCell.forTableColumn());
        tbc_tempo.setOnEditCommit(event -> {
            Lancio lancio = event.getRowValue();
            lancio.tempoProperty().set(event.getNewValue());
        });

        tbc_penalita.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tbc_penalita.setOnEditCommit(event -> {
            Lancio lancio = event.getRowValue();
            lancio.penalitaProperty().set(event.getNewValue());
        });

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long milliTrascorsi = System.currentTimeMillis() - tempoIniziale;
                lbl_titolo.setText(formattaTempo(milliTrascorsi));
            }
        };

        Partecipante p = new Partecipante("Mario", "Rossi");
        listaPartecipanti.add(p);
        lanciPerPartecipante.put(p, new Lancio(p));
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
    void onLancio(ActionEvent event) {
        Partecipante p = tableView.getSelectionModel().getSelectedItem();
        if (p != null) {
            Lancio lancio = lanciPerPartecipante.get(p);
            lancio.aggiungiTempo(System.currentTimeMillis() - tempoIniziale);

            tableView.refresh();
        }

    }

    @FXML
    void onHomePage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btn_homePage.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
