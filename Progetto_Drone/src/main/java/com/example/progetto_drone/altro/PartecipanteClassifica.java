    package com.example.progetto_drone.altro;

    public class PartecipanteClassifica {
        private final String nome;
        private final String cognome;
        private final int puntiTotali;

        public PartecipanteClassifica(String nome, String cognome, int puntiTotali) {
            this.nome = nome;
            this.cognome = cognome;
            this.puntiTotali = puntiTotali;
        }

        public String getNome() {
            return nome;
        }

        public String getCognome() {
            return cognome;
        }

        public int getPuntiTotali() {
            return puntiTotali;
        }
    }