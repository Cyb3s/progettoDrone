package com.example.progetto_drone;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.Serializable;

public class Partecipante implements Serializable {
    private StringProperty nome;
    private IntegerProperty numLanci;
    private IntegerProperty penalita;
    private IntegerProperty punti;
    private StringProperty tempo;

    public Partecipante(String nome, int numLanci, int penalita, int punti, String tempo) {
        this.nome = new SimpleStringProperty(nome);
        this.numLanci = new SimpleIntegerProperty(numLanci);
        this.penalita = new SimpleIntegerProperty(penalita);
        this.punti = new SimpleIntegerProperty(punti);
        this.tempo = new SimpleStringProperty(tempo);
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public int getNumLanci() {
        return numLanci.get();
    }

    public void setNumLanci(int numLanci) {
        this.numLanci.set(numLanci);
    }

    public IntegerProperty numLanciProperty() {
        return numLanci;
    }

    public int getPenalita() {
        return penalita.get();
    }

    public void setPenalita(int penalita) {
        this.penalita.set(penalita);
    }

    public IntegerProperty penalitaProperty() {
        return penalita;
    }

    public int getPunti() {
        return punti.get();
    }

    public void setPunti(int punti) {
        this.punti.set(punti);
    }

    public IntegerProperty puntiProperty() {
        return punti;
    }

    public String getTempo() {
        return tempo.get();
    }

    public void setTempo(String tempo) {
        this.tempo.set(tempo);
    }

    public StringProperty tempoProperty() {
        return tempo;
    }
}
