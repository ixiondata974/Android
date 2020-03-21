package com.example.habitbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        calcIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculeIMC();
            }
        });
    }

    private void calculeIMC(){
        String vPoid = poid.getText().toString();
        String vTaille = taille.getText().toString();
        //String vAge = age.getText().toString();

        int poi = Integer.parseInt(vPoid);
        int taill = Integer.parseInt(vTaille);
        //int ag = Integer.parseInt(vAge);

        try {
            float IMC = (poi * 10000)/(taill * taill);

            String votreIMC;
            if (IMC<18){
                votreIMC = "Vous êtes en sous poid";
            }else if (IMC>25){
                votreIMC = "Vous êtes en sur poid";
            }else {
                votreIMC = "Votre poid est normale";
            }

            imc.setText("Votre IMC est : "+IMC+"\n"+votreIMC);
        }catch (Exception e){
            imc.setText("Error : "+e);
        }
    }

    /*private void graphique(){


        Intent chartDroidIntent = new Intent(Intent.ACTION_VIEW, ChartDroidDataProvider.PROVIDER_URI);
        chartDroidIntent.putExtra(Intent.EXTRA_TITLE, "Chart droid");
        chartDroidIntent.addCategory("com.googlecode.chartdroid.intent.category.PIE_CHART");
        startActivity(chartDroidIntent);
        
    }*/
}
