package com.example.progetto_drone.altro;

import java.util.ArrayList;
import java.util.List;

public class Partecipante {
    private String nome;
    private String cognome;

    public Partecipante(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

}