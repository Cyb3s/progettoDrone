package com.example.progetto_drone;

import javafx.beans.property.StringProperty;

public class Partecipante {
    private StringProperty nome;
    private StringProperty cognome;
    //private IntegerProperty eta;
    //private StringProperty citta;


    public Partecipante(StringProperty nome, StringProperty cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getCognome() {
        return cognome.get();
    }

    public StringProperty cognomeProperty() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome.set(cognome);
    }
}
