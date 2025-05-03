package com.example.progetto_drone.altro;

import java.util.ArrayList;

public class Trofeo {
    private int nGare;
    private int nLanci;
    private String nomeTrofeo;
    private int dataInizio;
    private boolean inCorso;
    private ArrayList<Gara> gare;
    private int posGara; //indica a quale gara ci troviamo
    private int tempoLimite;
    private boolean scartaGara;

    public Trofeo(int nGare, String nomeTrofeo, int dataInizio,int nLanci,int tempoLimite, boolean scartaGara) {
        this.nLanci=nLanci;
        this.nGare = nGare;
        this.nomeTrofeo = nomeTrofeo;
        this.dataInizio = dataInizio;
        this.inCorso = true;
        this.tempoLimite=tempoLimite;
        this.scartaGara=scartaGara;
        gare=new ArrayList<>(nGare);
        posGara=0;
    }

    public boolean isScartaGara() {
        return scartaGara;
    }

    public void setScartaGara(boolean scartaGara) {
        this.scartaGara = scartaGara;
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

    public int getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(int dataInizio) {
        this.dataInizio = dataInizio;
    }

    public boolean isInCorso() {
        return inCorso;
    }

    public void setInCorso(boolean inCorso) {
        this.inCorso = inCorso;
    }

    public void setnLanci(int nLanci) {
        this.nLanci = nLanci;
    }

    public void setPosGara(int posGara) {
        this.posGara = posGara;
    }

    public void setTempoLimite(int tempoLimite) {
        this.tempoLimite = tempoLimite;
    }

    public void setGare(ArrayList<Gara> gare) {
        this.gare = gare;
    }
}
