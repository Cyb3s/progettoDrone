package com.example.progetto_drone;

import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lancio {
    private final Partecipante partecipante;
    private final List<Long> tempiLanci = new ArrayList<>();
    private final IntegerProperty penalita = new SimpleIntegerProperty(0);
    private final StringProperty punti = new SimpleStringProperty("0");
    private final StringProperty tempo = new SimpleStringProperty("");
    private final IntegerProperty numLanci = new SimpleIntegerProperty(4);

    public Lancio(Partecipante partecipante) {
        this.partecipante = partecipante;
    }

    public void aggiungiTempo(long tempoMillis) {
        if (numLanci.get() > 0) {
            tempiLanci.add(tempoMillis);
            numLanci.set(numLanci.get() - 1);
            tempo.set(formattaTempo(tempoMillis));
            aggiornaPunteggio();
        }
    }

    private void aggiornaPunteggio() {
        List<Integer> punteggi = new ArrayList<>();
        for (long tempo : tempiLanci) {
            int secondi = (int) (tempo / 1000);
            int puntiCalcolati = secondi <= 240 ? secondi : 240 - (secondi - 240) * 2;
            punteggi.add(puntiCalcolati);
        }

        if (punteggi.size() > 1) {
            punteggi.remove(Collections.min(punteggi));
        }

        int totale = punteggi.stream().mapToInt(Integer::intValue).sum();
        punti.set(String.valueOf(totale));
    }

    private String formattaTempo(long tempoMillis) {
        long secondi = tempoMillis / 1000;
        long minuti = secondi / 60;
        secondi = secondi % 60;
        long millisecondi = tempoMillis % 1000;
        return String.format("%02d:%02d.%03d", minuti, secondi, millisecondi);
    }

    public Partecipante getPartecipante() {
        return partecipante;
    }

    public StringProperty tempoProperty() {
        return tempo;
    }

    public StringProperty puntiProperty() {
        return punti;
    }

    public IntegerProperty penalitaProperty() {
        return penalita;
    }

    public IntegerProperty numLanciProperty() {
        return numLanci;
    }
}
