package com.example.appvehicule.Onglet.Liste_entret.obj;

import java.time.LocalDate;

public class EssRdHerit {
    private LocalDate date;
    private String libelle;

    public EssRdHerit(LocalDate date, String libelle){
        this.date = date;
        this.libelle = libelle;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLibelle() {
        return libelle;
    }
}
