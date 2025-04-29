package com.example.progetto_drone;

import java.time.LocalDate;
import java.util.ArrayList;

public class Trofeo {
    private int nGare;
    private String nomeTrofeo;
    private LocalDate dataInizio;
    private boolean inCorso;
    private ArrayList<Gara> gare;
    private int posGara; //indica a quale gara ci troviamo

    public Trofeo(int nGare, String nomeTrofeo, LocalDate dataInizio) {
        this.nGare = nGare;
        this.nomeTrofeo = nomeTrofeo;
        this.dataInizio = dataInizio;
        this.inCorso = true;
        gare=new ArrayList<>(nGare);
        posGara=0;
    }

    public int getnGare() {
        return nGare;
    }

    public void setnGare(int nGare) {
        this.nGare = nGare;
    }

    public String getNomeTrofeo() {
        return nomeTrofeo;
    }

    public void setNomeTrofeo(String nomeTrofeo) {
        this.nomeTrofeo = nomeTrofeo;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public boolean isInCorso() {
        return inCorso;
    }

    public void setInCorso(boolean inCorso) {
        this.inCorso = inCorso;
    }
}
