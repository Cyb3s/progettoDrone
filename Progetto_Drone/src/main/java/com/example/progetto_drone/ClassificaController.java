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

    public void onSalvaGara() {}

    public void onProssima() {}

    public void onAvviaCrnm() {}

    public void onFermaCrnm() {}
}
