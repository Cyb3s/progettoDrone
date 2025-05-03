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
            tbc_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            tbc_data.setCellValueFactory(new PropertyValueFactory<>("data"));

            // Collega la lista filtrata alla tabella
            tbw_tabella.setItems(listaFiltrata);


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
        ChangeWindow.changeWindow("vincitori.fxml","VIsualizza vincitori");
        Stage ss=(Stage) btn_visualizzaVincitori.getScene().getWindow();
        ss.close();
    }
}