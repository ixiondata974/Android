package com.example.appvehicule.formulaires;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appvehicule.R;

import java.util.regex.Pattern;

public class formulaire extends AppCompatActivity {

    private final static Pattern paterImmat = Pattern.compile("#^[A-Z]{1,2}[0-9]{1,3}[A-Z]{1,2}$#");

    private EditText modele;
    private EditText marque;
    private EditText immatriculation;
    private EditText poid;
    private EditText energie;
    private Button Cg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);

        Cg = (Button) findViewById(R.id.cg);

        modele = (EditText)findViewById(R.id.form_modele);
        marque = (EditText)findViewById(R.id.form_marque);
        immatriculation = (EditText)findViewById(R.id.form_imma);
        poid = (EditText)findViewById(R.id.form_poid);
        energie = (EditText)findViewById(R.id.form_energi);

        modele.addTextChangedListener(mText);
        marque.addTextChangedListener(mText);
        immatriculation.addTextChangedListener(mText);
        poid.addTextChangedListener(mText);
        energie.addTextChangedListener(mText);

        Cg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direct_CG();
            }
        });
    }

    private TextWatcher mText = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String model = modele.getText().toString().trim();
            String marq = marque.getText().toString().trim();
            String immat = immatriculation.getText().toString().trim();
            String poids = poid.getText().toString().trim();
            String energ = energie.getText().toString().trim();

            if (!paterImmat.matcher(immat).matches()){
                Cg.setEnabled(!model.isEmpty() && !marq.isEmpty() && !immat.isEmpty() && !poids.isEmpty() && !energ.isEmpty());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void direct_CG(){
        Intent in = new Intent(this,FormCG.class);
        startActivity(in);
    }
}
