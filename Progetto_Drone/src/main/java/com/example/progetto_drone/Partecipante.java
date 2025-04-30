package com.example.progetto_drone;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Partecipante {
    private final StringProperty nome;
    private final StringProperty cognome;
    private final StringProperty numLanci;
    private final IntegerProperty penalita;
    private final StringProperty punti;
    private final StringProperty tempo;

    public Partecipante(String nome,String cognome, String numLanci, int penalita, String punti, String tempo) {
        this.nome = new SimpleStringProperty(nome);
        this.cognome = new SimpleStringProperty(cognome);
        this.numLanci = new SimpleStringProperty(numLanci);
        this.penalita = new SimpleIntegerProperty(penalita);
        this.punti = new SimpleStringProperty(punti);
        this.tempo = new SimpleStringProperty(tempo);
    }

    public String getNome() { return nome.get(); }

    public StringProperty nomeProperty() { return nome; }

    public String getNumLanci() { return numLanci.get(); }

    public String getCognome() { return cognome.get(); }

    public StringProperty cognomeProperty() { return cognome; }

    public StringProperty numLanciProperty() { return numLanci; }

    public int getPenalita() { return penalita.get(); }

    public IntegerProperty penalitaProperty() { return penalita; }

    public String getPunti() { return punti.get(); }

    public StringProperty puntiProperty() { return punti; }

    public String getTempo() { return tempo.get(); }

    public StringProperty tempoProperty() { return tempo; }
}

