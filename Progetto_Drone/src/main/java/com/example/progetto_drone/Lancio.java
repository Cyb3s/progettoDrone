package com.example.progetto_drone;

public class Lancio {
    private String nome; //nome della persona che sta lanciando
    private String nLancio; //indica il lancio di quella persona
    private int punteggio;
    private int tempo;
    private String penalità;

    public Lancio(String nome) {
        this.nome=nome;
        this.punteggio = 0;
        this.tempo = 0;
        this.penalità = "";
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public String getPenalità() {
        return penalità;
    }

    public void setPenalità(String penalità) {
        this.penalità = penalità;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
}
