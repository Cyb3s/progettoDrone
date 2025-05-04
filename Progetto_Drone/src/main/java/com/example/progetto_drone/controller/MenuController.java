package com.example.progetto_drone.controller;

import com.example.progetto_drone.ChangeWindow;
import com.example.progetto_drone.altro.Trofei;
import com.example.progetto_drone.altro.Trofeo;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;

public class MenuController {
    @FXML
    private Button btn_nuovoTrofeo;

    @FXML
    private Button btn_visualizzaVincitori;

    @FXML
    private ChoiceBox<String> chb_anno;

    @FXML
    private CheckBox ckb_conclusi;

    @FXML
    private CheckBox ckb_inCorso;

    @FXML
    private TableColumn<Trofeo, String> tbc_data;

    @FXML
    private TableColumn<Trofeo, String> tbc_nome;

    @FXML
    private TableView<Trofeo> tbw_tabella;

    @FXML
    private TextField txf_ricerca;

    @FXML
    public void initialize(){
            // Crea una lista filtrabile a partire dalla lista di trofei
            FilteredList<Trofeo> listaFiltrata = new FilteredList<>(FXCollections.observableArrayList(Trofei.listaTrofei));

            // Inserimento valori della choiceBox
            if(Trofei.listaTrofei.isEmpty()){
                chb_anno.setValue("Nessun trofeo");
            }else{
                chb_anno.getItems().add("Tutti");
                for(Trofeo trofeo : Trofei.listaTrofei){
                    chb_anno.getItems().add(String.valueOf(trofeo.getDataInizio()));
                }
                chb_anno.setValue("Tutti");
            }

            /*settare la tabella col binding dinamico*/

            // Configura le colonne della tabella
            tbc_nome.setCellValueFactory(new PropertyValueFactory<>("nomeTrofeo"));
            tbc_data.setCellValueFactory(new PropertyValueFactory<>("dataInizio"));

            // Collega la lista filtrata alla tabella
            tbw_tabella.setItems(listaFiltrata);

            // Aggiungi un listener a TextField per la ricerca
            txf_ricerca.textProperty().addListener((obs, oldVal, newVal) -> {
                listaFiltrata.setPredicate(t -> {
                    String testo = newVal.toLowerCase().trim();//trim -> rimuove gli spazi bianchi
                    String anno = chb_anno.getValue();
                    boolean inCorso = ckb_inCorso.isSelected();
                    boolean conclusi = ckb_conclusi.isSelected();

                    boolean matchNome = testo.isEmpty() || t.getNomeTrofeo().toLowerCase().contains(testo);//confronto casa-insensitive
                    boolean matchAnno = "Tutti".equals(anno) || String.valueOf(t.getDataInizio()).equals(anno);
                    boolean matchStato = (!inCorso && !conclusi) || (inCorso ^ conclusi ? (inCorso == t.isInCorso()) : true); //se nessuna checkBox e selezionata accetta tutto //controlla se ne è selezionato solo uno(XOR), poi controlla se è uguale a inConrso

                    return matchNome && matchAnno && matchStato;
                });
            });

            // Aggiungi listener alla ChoiceBox (anno)
            chb_anno.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                listaFiltrata.setPredicate(t -> {
                    String testo = txf_ricerca.getText().toLowerCase().trim();
                    String anno = newVal;
                    boolean inCorso = ckb_inCorso.isSelected();
                    boolean conclusi = ckb_conclusi.isSelected();

                    boolean matchNome = testo.isEmpty() || t.getNomeTrofeo().toLowerCase().contains(testo);
                    boolean matchAnno = "Tutti".equals(anno) || String.valueOf(t.getDataInizio()).equals(anno);
                    boolean matchStato = (!inCorso && !conclusi) || (inCorso ^ conclusi ? (inCorso == t.isInCorso()) : true);

                    return matchNome && matchAnno && matchStato;
                });
            });

            // Aggiungi listener alla CheckBox (in corso)
            if(ckb_inCorso != null) {
                ckb_inCorso.selectedProperty().addListener((obs, oldVal, newVal) -> {
                    listaFiltrata.setPredicate(t -> {
                        String testo = txf_ricerca.getText().toLowerCase().trim();
                        String anno = chb_anno.getValue();
                        boolean inCorso = newVal;
                        boolean conclusi = ckb_conclusi.isSelected();

                        boolean matchNome = testo.isEmpty() || t.getNomeTrofeo().toLowerCase().contains(testo);
                        boolean matchAnno = "Tutti".equals(anno) || String.valueOf(t.getDataInizio()).equals(anno);
                        boolean matchStato = (!inCorso && !conclusi) || (inCorso ^ conclusi ? (inCorso == t.isInCorso()) : true);

                        return matchNome && matchAnno && matchStato;
                    });
                });
            }

            // Aggiungi listener alla CheckBox (conclusi)
            if(ckb_conclusi != null) {
                ckb_conclusi.selectedProperty().addListener((obs, oldVal, newVal) -> {
                    listaFiltrata.setPredicate(t -> {
                        String testo = txf_ricerca.getText().toLowerCase().trim();
                        String anno = chb_anno.getValue();
                        boolean inCorso = ckb_inCorso.isSelected();
                        boolean conclusi = newVal;

                        boolean matchNome = testo.isEmpty() || t.getNomeTrofeo().toLowerCase().contains(testo);
                        boolean matchAnno = "Tutti".equals(anno) || String.valueOf(t.getDataInizio()).equals(anno);
                        boolean matchStato = (!inCorso && !conclusi) || (inCorso ^ conclusi ? (inCorso == t.isInCorso()) : true);

                        return matchNome && matchAnno && matchStato;
                    });
                });
            }
            // Se clicco un torneo non concluso mi manda alla prossima gara del trofeo
            tbw_tabella.setOnMouseClicked(event -> {
                // Verifica se una riga è selezionata
                if (tbw_tabella.getSelectionModel().getSelectedItem() != null) {
                    Trofeo selezionato = tbw_tabella.getSelectionModel().getSelectedItem();

                    // Controlla se il trofeo selezionato non è concluso
                    if (selezionato.isInCorso()) {
                        // Apre la finestra di salvataggio gara
                        ChangeWindow.changeWindow("classifica.fxml", "Nuova Gara");
                        Stage stage = (Stage) tbw_tabella.getScene().getWindow();
                        stage.close();
                    }
                }
            });
    }

    @FXML
    void onNuovoTrofeo(ActionEvent event) {
        ChangeWindow.changeWindow("nuovoTrofeo.fxml","Nuovo Trofeo");
        Stage ss=(Stage) btn_nuovoTrofeo.getScene().getWindow();
        ss.close();
    }

    @FXML
    void onInCorso(ActionEvent event) {
        if(ckb_conclusi.isSelected()) {
            ckb_conclusi.selectedProperty().setValue(false);
        }
    }

    @FXML
    void onConclusi(ActionEvent event) {
        if(ckb_inCorso.isSelected()){
            ckb_inCorso.selectedProperty().setValue(false);
        }
    }

    @FXML
    void onVisualizzaVincitori(ActionEvent event) {
        Trofeo selezionato = tbw_tabella.getSelectionModel().getSelectedItem();
        // Controlla se il trofeo selezionato è concluso
        if (!selezionato.isInCorso()){
            ChangeWindow.changeWindow("vincitori.fxml", "Visualizza vincitori");
            Stage ss = (Stage) btn_visualizzaVincitori.getScene().getWindow();
            ss.close();
        }else{
            //altrimenti allert con scritto che non si possono vedere i vincitori
            Alert alert = new Alert(Alert.AlertType.WARNING, "Non è possile visualizzare i vincitori del torneo in quanto non è ancora concluso",ButtonType.OK);
        }

    }
}