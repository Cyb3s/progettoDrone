package com.example.progetto_drone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

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
    private TableColumn<?, String> tbc_nome;

    @FXML
    private TableColumn<?, String> tbc_numLanci;

    @FXML
    private TableColumn<?, Integer> tbc_penalita;

    @FXML
    private TableColumn<?, String> tbc_punti;

    @FXML
    private TableColumn<?, String> tbc_tempo;

    public void initialize() {}

    @FXML
    void onAvviaCrnm(ActionEvent event) {}

    @FXML
    void onFermaCrnm(ActionEvent event) {}

    @FXML
    void onProssima(ActionEvent event) {}

    @FXML
    void onSalvaGara(ActionEvent event) {}

    @FXML
    void onLancia(ActionEvent event) {}

    @FXML
    void onTornaHome(ActionEvent event) {}
}
