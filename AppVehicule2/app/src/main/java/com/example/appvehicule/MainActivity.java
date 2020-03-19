package com.example.appvehicule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import com.example.appvehicule.Broadcast.scannerReicever;
import com.example.appvehicule.recyclerView.VehiculeAdapter;
import com.karumi.dexter.Dexter;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView listVic;
    private RecyclerView.LayoutManager layoutMana;
    private VehiculeAdapter adapter;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listVic = (RecyclerView)findViewById(R.id.listeVehic);
        text = (TextView)findViewById(R.id.test);

        affiche_List();
    }

    //Mes méthodes*****************************************************************************

    private void affiche_List(){

        String mod;
        String immat;
        String statut;
        int nbCompt;
        int id;

        List<Vehicule> listeVehicule = new ArrayList<>();
        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String ret = RequestHandler.sendGet("http://beda9701.odns.fr/stage_2020/listeVehicules.php");
            JSONObject list_vehic = new JSONObject(ret);

            JSONArray vehic_Array = list_vehic.getJSONObject("results").getJSONArray("vehicules");

            nbCompt = list_vehic.getJSONObject("results").getInt("nbVehicules");

            for (int i = 0; i < nbCompt; i++) {
                mod = vehic_Array.getJSONObject(i).getString("modele");
                immat = vehic_Array.getJSONObject(i).getString("immatriculation");
                statut = vehic_Array.getJSONObject(i).getString("statut");
                id = vehic_Array.getJSONObject(i).getInt("id");
                listeVehicule.add(new Vehicule(mod, immat, statut,id));
            }

            layoutMana = new LinearLayoutManager(this);
            listVic.setLayoutManager(layoutMana);

            adapter = new VehiculeAdapter(listeVehicule, this);
            listVic.setAdapter(adapter);

        }catch (Exception e){
            e.getMessage();
        }
    }

    /*public class RequestAsync extends AsyncTask<String,String,String> {
        private TextView text;
        @Override
        protected String doInBackground(String... strings) {
            try {

                String ret = RequestHandler.sendGet("http://beda9701.odns.fr/stage_2020/listeVehicules.php");
                JSONObject list_vehic = new JSONObject(ret);

               // JSONArray vehic_Array = new JSONArray(list_vehic.getString("modele"));
                return list_vehic.toString();

            }catch (Exception e){
                return "Exception: " + e.getMessage();
            }
        }
        String texte = doInBackground();
    }*/
}