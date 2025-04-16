package com.example.progetto_drone;
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
    private Label lbl_titolo;

    @FXML
    private TableColumn<?, ?> tbc_nome;

    @FXML
    private TableColumn<?, ?> tbc_numLanci;

    @FXML
    private TableColumn<?, ?> tbc_penalita;

    @FXML
    private TableColumn<?, ?> tbc_punti;

    @FXML
    private TableColumn<?, ?> tbc_tempo;

    public void initialize() {

    }
}
