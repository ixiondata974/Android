package com.example.habitbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ListeDesActivites extends AppCompatActivity {

    private CheckBox marche;
    private CheckBox course;
    private CheckBox yoga;
    private CheckBox boxe;
    private CheckBox tennis;
    private CheckBox parcours;
    private CheckBox aucune;
    private Button envoi;
    private TextView Test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_des_activites);

        Intent in = getIntent();
        final String val = in.getStringExtra("id");

        marche = (CheckBox)findViewById(R.id.marche);
        course = (CheckBox)findViewById(R.id.course);
        yoga = (CheckBox)findViewById(R.id.yoga);
        boxe = (CheckBox)findViewById(R.id.boxe);
        tennis = (CheckBox)findViewById(R.id.tennis);
        parcours = (CheckBox)findViewById(R.id.parcours);
        envoi = (Button)findViewById(R.id.envoyer);

        Test = (TextView) findViewById(R.id.test);
        marche.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Test.setText(marche.getText());
                }
            }
        });
    }

}
