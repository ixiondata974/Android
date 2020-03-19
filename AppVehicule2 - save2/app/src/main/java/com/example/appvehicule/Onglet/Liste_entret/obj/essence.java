package com.example.appvehicule.Onglet.Liste_entret.obj;

import androidx.annotation.NonNull;

import java.time.LocalDate;

public class essence extends EssRdHerit {
    private String quantite;
    private String prix;
    private String type;

    public essence(LocalDate date, String Station, String type, float quantite, float prix){
        super(date, Station);
        this.quantite = String.valueOf(quantite);
        this.prix = String.valueOf(prix);
        this.type = type;
    }

    @NonNull
    @Override
    public String toString() {
        return getDate()+ ", Station : " +getLibelle()+", Energie : "+getType()+", "+getQuantite()+"L , "+getPrix()+"euros" ;
    }

    public String getType() {
        return type;
    }

    public String getQuantite() {
        return quantite;
    }

    public String getPrix() {
        return prix;
    }
}


