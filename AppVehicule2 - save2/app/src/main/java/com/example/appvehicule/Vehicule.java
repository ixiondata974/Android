package com.example.appvehicule;

import android.content.Intent;
import com.example.appvehicule.CaracterVehic;

import com.example.appvehicule.recyclerView.VehiculeAdapter;

public class Vehicule {
    private String nom;
    private String Immatri;
    private String statu;
    private int id;

    public Vehicule(String nom, String immatri, String statut, int id) {
        this.nom = nom;
        this.Immatri = immatri;
        this.statu = statut;
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public String getImmatri() {
        return Immatri;
    }

    public String getStatu() {
        return statu;
    }

    public int getId() {
        return id;
    }
}
