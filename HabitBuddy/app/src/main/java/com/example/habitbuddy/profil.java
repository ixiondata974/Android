package com.example.habitbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

public class profil extends AppCompatActivity {

    private Button calcIMC;
    private EditText poid;
    private EditText taille;
    private EditText age;
    private TextView imc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        calcIMC = (Button)findViewById(R.id.calcIMC);

        poid = (EditText) findViewById(R.id.poid);
        taille = (EditText)findViewById(R.id.taille);
        age = (EditText)findViewById(R.id.age);

        imc = (TextView)findViewById(R.id.imc);

        leJSON();

        calcIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    calculeIMC();
                }catch (Exception e){
                    imc.setText("Il n'a pas de valeur");
                }
            }
        });
    }

    private void leJSON(){
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String ret = RequestHandler.sendGet("http://192.168.1.36/APItest.php");
            imc.setText(ret);
        }catch (Exception e){
            imc.setText("Erreur : " + e);
        }
    }
    private void versFormulaire(){

    }

    private void calculeIMC(){
        String vPoid = poid.getText().toString();
        String vTaille = taille.getText().toString();
        String vAge = age.getText().toString();

        int poi = Integer.parseInt(vPoid);
        int taill = Integer.parseInt(vTaille);
        int ag = Integer.parseInt(vAge);

        try {
            float IMC = (poi * 10000)/(taill * taill);

            JSONObject parseJSon = new JSONObject();
            parseJSon.put("id", 1);
            parseJSon.put("age", ag);
            parseJSon.put("poid", poi);
            parseJSon.put("taille", taill);
            parseJSon.put("imc", IMC);

            String votreIMC;
            if (IMC<18){
                votreIMC = "Vous êtes en sous poid";
            }else if (IMC>25){
                votreIMC = "Vous êtes en sur poid";
            }else {
                votreIMC = "Votre poid est normale";
            }

            imc.setText(votreIMC);

            new SendDeviceDetails().execute("http://192.168.1.36/MVC/controller.php",parseJSon.toString(), "unProfil");

            final Intent intent = new Intent(this, ListeDesActivites.class);
            intent.putExtra("id","1");
            startActivity(intent);
        }catch (Exception e){
            imc.setText("Error : "+e);
        }
    }

}