package com.example.appvehicule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appvehicule.formulaires.formulaire;

import org.json.JSONArray;
import org.json.JSONObject;

public class CaracterVehic extends AppCompatActivity {

    private Button button;
    private LinearLayout Cg;
    private LinearLayout entretien;
    private LinearLayout form_S;
    private TextView model;
    private TextView immatriculation;
    private TextView marque;
    private TextView energ;
    private TextView kmTotal;
    private TextView dateAq;

    private TextView poids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caracter_vehic);
        button = (Button)findViewById(R.id.button);

        model = (TextView)findViewById(R.id.immatriculation);
        poids = (TextView)findViewById(R.id.poids);
        marque = (TextView)findViewById(R.id.marque);
        energ = (TextView)findViewById(R.id.energie);
        kmTotal = (TextView)findViewById(R.id.KmTotal);
        dateAq = (TextView)findViewById(R.id.dateAcquisition);

        Cg = (LinearLayout)findViewById(R.id.CG);
        entretien = (LinearLayout)findViewById(R.id.entretien);
        form_S =(LinearLayout)findViewById(R.id.formS);

        form_S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aller_form();
            }
        });

        affiche_Charact();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retour();
            }
        });

        entretien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entretien_();
            }
        });
        Log.i("test","onCreate");
    }

    //Eventuallement.............................................................
    @Override
    protected void onStart(){
        super.onStart();
        Log.i("test","onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    //Mes méthodes*****************************************************************************

    private void entretien_(){
        Intent in = new Intent(this, entretien.class);
        startActivity(in);
    }

    private void retour(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void affiche_Charact(){
        try{
            //unVehicule.php..................................

            JSONObject postData2 = new JSONObject();
            postData2.put("id", recup_id());
            String unVehic = RequestHandler.sendPost("http://beda9701.odns.fr/stage_2020/unVehicule.php",postData2);
            JSONObject array2 = new JSONObject(unVehic);
            JSONArray array_list_2 = array2.getJSONObject("results").getJSONArray("details");

            String imm = array_list_2.getJSONObject(0).getString("immatriculation");
            String poid = array_list_2.getJSONObject(0).getString("poids");
            String marq = array_list_2.getJSONObject(0).getString("marque");
            String energie = array_list_2.getJSONObject(0).getString("energie");
            String KMTotal = array_list_2.getJSONObject(0).getString("kilometrageTotal");
            String dateAcqui = array_list_2.getJSONObject(0).getString("dateAcquisition");
            final String carteGrise = array_list_2.getJSONObject(0).getString("codeCarteGrise");

            poids.setText(poid);
            marque.setText(marq);
            model.setText(imm);
            energ.setText(energie);
            kmTotal.setText(KMTotal);
            dateAq.setText("Date d'aquisition : "+dateAcqui);

        // Direction carte Grise...................................................
            Cg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    aller_CGrise(carteGrise);
                }
            });

        }catch (Exception e){
            e.getMessage();
        }
    }

    private void aller_CGrise(String code_CG){
        Intent in = new Intent(this, CarteGrise.class);
        in.putExtra("id_cart", code_CG);
        startActivity(in);
    }

    private void aller_form(){
        Intent in = new Intent(this, formulaire.class);
        startActivity(in);
    }

    private int recup_id(){
        Intent intent = getIntent();
        int ID = intent.getIntExtra("info",-1);

        return ID;
    }
}