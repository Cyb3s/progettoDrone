package com.example.progetto_drone;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Partecipante {
    private final StringProperty nome;
    private final StringProperty cognome;

    public Partecipante(String nome, String cognome) {
        this.nome = new SimpleStringProperty(nome);
        this.cognome = new SimpleStringProperty(cognome);
    }

    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public String getCognome() {
        return cognome.get();
    }

    public StringProperty cognomeProperty() {
        return cognome;
    }

    public String toString() {
        return nome.get() + " " + cognome.get();
    }
}
