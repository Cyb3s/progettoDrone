package com.example.progetto_drone.altro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lancio {
    private Partecipante partecipante;
    private List<Long> tempiLanci = new ArrayList<>();
    private int penalita = 0;
    private String punti ="0";
    private String tempo ="";
    private int numLanci = 4;

    public Lancio() {
        this.partecipante = partecipante;
        this.tempiLanci = tempiLanci;
        this.penalita = penalita;
        this.punti = punti;
        this.tempo = tempo;
        this.numLanci = 4;
    }

    public int getNumLanci() {
        return numLanci;
    }

    public void setNumLanci(int numLanci) {
        this.numLanci = numLanci;
    }

    public Partecipante getPartecipante() {
        return partecipante;
    }

    public void setPartecipante(Partecipante partecipante) {
        this.partecipante = partecipante;
    }

    public int getPenalita() {
        return penalita;
    }

    public void setPenalita(int penalita) {
        this.penalita = penalita;
    }

    public String getPunti() {
        return punti;
    }

    public void setPunti(String punti) {
        this.punti = punti;
    }

    public List<Long> getTempiLanci() {
        return tempiLanci;
    }

    public void setTempiLanci(List<Long> tempiLanci) {
        this.tempiLanci = tempiLanci;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
        try {
            long tempoMillis = convertiTempoInMillis(tempo);
            if (numLanci > 0) {
                tempiLanci.add(tempoMillis);
                numLanci--;
            } else if (!tempiLanci.isEmpty()) {
                tempiLanci.set(tempiLanci.size() - 1, tempoMillis);
            }
            aggiornaPunteggio();
        } catch (Exception e) {
            System.err.println("Errore nel parsing del tempo: " + tempo);
        }
    }


    private long convertiTempoInMillis(String tempo) {
        String[] parti = tempo.split("[:\\.]");
        if (parti.length != 3) throw new IllegalArgumentException("Formato tempo non valido: " + tempo);
        long minuti = Long.parseLong(parti[0]);
        long secondi = Long.parseLong(parti[1]);
        long millisecondi = Long.parseLong(parti[2]);
        return (minuti * 60 + secondi) * 1000 + millisecondi;
    }

    public void aggiungiTempo(long tempoMillis) {
        if (numLanci > 0) {
            tempiLanci.add(tempoMillis);
            numLanci-=1;
            tempo=formattaTempo(tempoMillis);
            aggiornaPunteggio();
        }
    }

    public void aggiornaPunteggio() {
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
        punti=String.valueOf(totale);
    }

    public String formattaTempo(long tempoMillis) {
        long secondi = tempoMillis / 1000;
        long minuti = secondi / 60;
        secondi = secondi % 60;
        long millisecondi = tempoMillis % 1000;
        return String.format("%02d:%02d.%03d", minuti, secondi, millisecondi);
    }
}