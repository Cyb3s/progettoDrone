package com.example.progetto_drone.altro;

import java.util.ArrayList;

public class Gara {
    private int nLanci;
    private int nPersone;
    private ArrayList<Lancio> lanci;
    private int posLancio; //indica a quale lancio siamo rimasti

    public Gara(int nLanci, int nPersone) {
        this.nLanci = nLanci;
        this.nPersone = nPersone;
        this.lanci = new ArrayList<>(this.nLanci*this.nPersone);
        posLancio=0;
    }

    public int getnLanci() {
        return nLanci;
    }

    public void setnLanci(int nLanci) {
        this.nLanci = nLanci;
    }

    public int getnPersone() {
        return nPersone;
    }

    public void setnPersone(int nPersone) {
        this.nPersone = nPersone;
    }

    public ArrayList<Lancio> getLanci() {
        return lanci;
    }

    public void setLanci(ArrayList<Lancio> lanci) {
        this.lanci = lanci;
    }
}
