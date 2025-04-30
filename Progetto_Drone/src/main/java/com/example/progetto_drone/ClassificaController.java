package com.example.progetto_drone;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

public class ClassificaController {
    @FXML
    private Button btn_avviaCronometro;

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
    private TableColumn<Partecipante, String> tbc_numLanci;

    @FXML
    private TableColumn<Partecipante, Integer> tbc_penalita;

    @FXML
    private TableColumn<Partecipante, String> tbc_punti;

    @FXML
    private TableColumn<Partecipante, String> tbc_tempo;

    @FXML
    private TableView<Partecipante> tableView;

    private ObservableList<Partecipante> listaPartecipanti = FXCollections.observableArrayList();

    private long tempoIniziale;
    private AnimationTimer timer;
    private boolean cronometroAttivo = false;

    public void initialize() {
        tableView.setEditable(true);

        tbc_nome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        tbc_numLanci.setCellValueFactory(cellData -> cellData.getValue().numLanciProperty());
        tbc_penalita.setCellValueFactory(cellData -> cellData.getValue().penalitaProperty().asObject());
        tbc_punti.setCellValueFactory(cellData -> cellData.getValue().puntiProperty());
        tbc_tempo.setCellValueFactory(cellData -> cellData.getValue().tempoProperty());

        tableView.setItems(listaPartecipanti);

        tbc_tempo.setCellFactory(TextFieldTableCell.forTableColumn());
        tbc_tempo.setOnEditCommit(event -> {
            Partecipante partecipante = event.getRowValue();
            partecipante.tempoProperty().set(event.getNewValue());
        });

        tbc_penalita.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tbc_penalita.setOnEditCommit(event -> {
            Partecipante partecipante = event.getRowValue();
            partecipante.penalitaProperty().set(event.getNewValue());
        });

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
        }
    }

    @FXML
    void onProssima(ActionEvent event) {
        lbl_titolo.setText("Nuova Gara");
        listaPartecipanti.clear();
    }

    @FXML
    void onSalvaGara(ActionEvent event) {
        //listaPartecipanti.add(new Partecipante("nome", "cognome", "3", 5, "90", lbl_titolo.getText()));
        //Da rivedere...
    }

    private String formattaTempo(long tempo) {
        long secondi = tempo / 1000;
        long minuti = secondi / 60;
        secondi = secondi % 60;
        long millisecondi = tempo % 1000;
        return String.format("%02d:%02d.%03d", minuti, secondi, millisecondi);
    }
}
