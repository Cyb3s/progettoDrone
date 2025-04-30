package com.example.progetto_drone;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
    private Button btn_homePage;

    @FXML
    private Button btn_lancio;

    @FXML
    private Label lbl_titolo;

    @FXML
    private TableView<Partecipante> tableView_Classifica;

    @FXML
    private TableColumn<Partecipante, String> tbc_nome;

    @FXML
    private TableColumn<Partecipante, Integer> tbc_numLanci;

    @FXML
    private TableColumn<Partecipante, Integer> tbc_penalita;

    @FXML
    private TableColumn<Partecipante, Integer> tbc_punti;

    @FXML
    private TableColumn<Partecipante, String> tbc_tempo;

    private Timer cronometro;
    private int secondi = 0;
    private int minuti = 0;
    private int centesimi = 0;

    public void initialize() {
        lbl_titolo.setText("Classifica Gara Drone");

        tbc_nome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        tbc_numLanci.setCellValueFactory(cellData -> cellData.getValue().numLanciProperty().asObject());
        tbc_penalita.setCellValueFactory(cellData -> cellData.getValue().penalitaProperty().asObject());
        tbc_punti.setCellValueFactory(cellData -> cellData.getValue().puntiProperty().asObject());
        tbc_tempo.setCellValueFactory(cellData -> cellData.getValue().tempoProperty());

        tbc_tempo.setOnEditCommit(event -> {
            Partecipante partecipante = event.getRowValue();
            partecipante.setTempo(event.getNewValue());
        });

        btn_fermaCronometro.setDisable(true);
        btn_prossimaGara.setDisable(true);
        btn_salvaGara.setDisable(true);

        btn_avviaCronometro.setOnAction(event -> onAvviaCrnm(event));
        btn_fermaCronometro.setOnAction(event -> onFermaCrnm(event));
        btn_prossimaGara.setOnAction(event -> onProssima(event));
        btn_salvaGara.setOnAction(event -> onSalvaGara(event));
        btn_lancio.setOnAction(event -> onLancia(event));
        btn_homePage.setOnAction(event -> onTornaHome(event));

        caricaDati();
    }

    private void caricaDati() {
        try {
            FileInputStream fileIn = new FileInputStream("classifica_gara.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            @SuppressWarnings("unchecked")
            ObservableList<Partecipante> partecipanti = (ObservableList<Partecipante>) in.readObject();
            tableView_Classifica.setItems(partecipanti);

            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nessun dato trovato o errore nel caricamento.");
        }
    }

    @FXML
    void onAvviaCrnm(ActionEvent event) {
        cronometro = new Timer();

        cronometro.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                centesimi++;
                if (centesimi == 100) {
                    centesimi = 0;
                    secondi++;
                }
                if (secondi == 60) {
                    secondi = 0;
                    minuti++;
                }
                lbl_titolo.setText(String.format("Tempo: %02d:%02d:%02d", minuti, secondi, centesimi));
            }
        }, 0, 10);

        btn_avviaCronometro.setDisable(true);
        btn_fermaCronometro.setDisable(false);
    }

    @FXML
    void onFermaCrnm(ActionEvent event) {
        if (cronometro != null) {
            cronometro.cancel();
        }
        btn_fermaCronometro.setDisable(true);
        btn_avviaCronometro.setDisable(false);
        btn_salvaGara.setDisable(false);
    }

    @FXML
    void onProssima(ActionEvent event) {
        tableView_Classifica.getItems().clear();
        secondi = 0;
        minuti = 0;
        centesimi = 0;
        lbl_titolo.setText("Tempo: 00:00:00");
        btn_avviaCronometro.setDisable(false);
        btn_fermaCronometro.setDisable(true);
        btn_prossimaGara.setDisable(true);
        btn_salvaGara.setDisable(true);
    }

    @FXML
    void onSalvaGara(ActionEvent event) {
        try {
            FileOutputStream fileOut = new FileOutputStream("classifica_gara.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(tableView_Classifica.getItems());
            out.close();
            fileOut.close();

            System.out.println("Gara salvata con successo.");
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio della gara: " + e.getMessage());
        }
    }

    @FXML
    void onLancia(ActionEvent event) {}

    @FXML
    void onTornaHome(ActionEvent event) {}
}
