package com.example.habitbuddy;

import androidx.appcompat.app.AppCompatActivity;

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
                String vPoid = poid.getText().toString();
                String vTaille = taille.getText().toString();
                String vAge = age.getText().toString();

                int poi = Integer.parseInt(vPoid);
                int taill = Integer.parseInt(vTaille);
                int ag = Integer.parseInt(vAge);

                float IMC = poi/(taill*taill);

                imc.setText(String.valueOf(IMC));
            }
        });
    }
}
