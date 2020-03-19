package com.example.appvehicule.recyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvehicule.CaracterVehic;
import com.example.appvehicule.MainActivity;
import com.example.appvehicule.R;
import com.example.appvehicule.Vehicule;

import java.util.ArrayList;
import java.util.List;

import com.example.appvehicule.MainActivity;

public class VehiculeAdapter extends RecyclerView.Adapter<VehiculeAdapter.VehiculeHolder>{

    private MainActivity act;
    private List<Vehicule> listeVehic;

    public class VehiculeHolder extends RecyclerView.ViewHolder{

        LinearLayout parent_Layout;
        TextView nom;
        TextView immat;
        TextView statut;

        public VehiculeHolder(@NonNull final View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.texteNom);
            immat = itemView.findViewById(R.id.texteimma);
            parent_Layout = itemView.findViewById(R.id.layout);
            statut = itemView.findViewById(R.id.statut);
        }
    }

    public VehiculeAdapter(List<Vehicule> listeVehic, MainActivity mainActivity) {
        this.listeVehic = listeVehic;
        this.act = mainActivity;
    }

    @Override
    public VehiculeHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layou_listitem, parent, false);
        VehiculeHolder vehicule_holder = new VehiculeHolder(view);
        return vehicule_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VehiculeHolder vehiculeHolder, int i) {
        final Vehicule vehicule = listeVehic.get(i);
        vehiculeHolder.nom.setText(vehicule.getNom());
        vehiculeHolder.immat.setText(vehicule.getImmatri());
        vehiculeHolder.statut.setText(vehicule.getStatu());

        vehiculeHolder.parent_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                affiche_vehic(v, vehicule);
            }
        });
    }

    //Mes méthodes................................................................

    public void affiche_vehic(View v, Vehicule i){

        Intent it = new Intent(v.getContext(), CaracterVehic.class);
        it.putExtra("info", i.getId());
        act.startActivity(it);
    }

    @Override
    public int getItemCount() {
        return listeVehic.size();
    }
}
