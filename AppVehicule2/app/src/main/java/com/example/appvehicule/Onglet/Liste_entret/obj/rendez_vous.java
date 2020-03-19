package com.example.appvehicule.Onglet.Liste_entret.obj;

import androidx.annotation.NonNull;

import java.time.LocalDate;

public class rendez_vous extends EssRdHerit {

    public rendez_vous(LocalDate date, String Libelle){
        super(date,Libelle);
    }

    @NonNull
    @Override
    public String toString() {
        return getDate()+ " "+ getLibelle();
    }
}
