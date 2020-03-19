package com.example.appvehicule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class CarteGrise extends AppCompatActivity {

    private TextView Immatriculation;
    private TextView Lettre;
    private TextView Cathegori;
    private TextView SousCr;
    private TextView RefPays;
    private TextView dateA;
    private TextView dateF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carte_grise);

        Immatriculation = (TextView)findViewById(R.id.imma);
        Lettre = (TextView)findViewById(R.id.lettre);
        Cathegori = (TextView)findViewById(R.id.cathegori);
        SousCr = (TextView)findViewById(R.id.souscripteur);
        RefPays = (TextView)findViewById(R.id.refPays);
        dateA = (TextView)findViewById(R.id.dateAcquisition);
        dateF = (TextView)findViewById(R.id.dateFinValide);

        get_CG();
    }

    private void get_CG(){
        try{
            JSONObject info_Carte_Grise = new JSONObject();
            info_Carte_Grise.put("id", get_ID());

            String recu = RequestHandler.sendPost("http://beda9701.odns.fr/stage_2020/uneCarteGrise.php",info_Carte_Grise);
            JSONObject array = new JSONObject(recu);

            JSONArray array_list = array.getJSONObject("results").getJSONArray("details");

            String immatriculation = array_list.getJSONObject(0).getString("immatriculation");
            String lettre = array_list.getJSONObject(0).getString("lettre");
            String cathegorie = array_list.getJSONObject(0).getString("categorie");
            String souscripteur = array_list.getJSONObject(0).getString("souscripteur");
            String refPays = array_list.getJSONObject(0).getString("refPays");
            String dateAcquisition = array_list.getJSONObject(0).getString("dateAcquisition");
            String dateFinValide = array_list.getJSONObject(0).getString("dateFinValide");

            Immatriculation.setText(immatriculation);
            Lettre.setText(lettre);
            Cathegori.setText(cathegorie);
            SousCr.setText(souscripteur);
            RefPays.setText(refPays);
            dateA.setText(dateAcquisition);
            dateF.setText(dateFinValide);

        }catch(Exception e){
            Immatriculation.setText("Exeption : " + e);
        }
    }

    private String get_ID(){
        Intent getID = getIntent();
        String ID = getID.getStringExtra("id_cart");
        return ID;
    }
}
