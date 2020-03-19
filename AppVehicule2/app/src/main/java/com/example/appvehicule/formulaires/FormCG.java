package com.example.appvehicule.formulaires;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.appvehicule.R;

public class FormCG extends AppCompatActivity {

    private EditText letter;
    private EditText cath;
    private EditText souscr;
    private EditText PR;
    private EditText finV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cg);

        letter = (EditText)findViewById(R.id.form_lettre);
        cath = (EditText)findViewById(R.id.form_cathegorie);
        souscr = (EditText)findViewById(R.id.form_souscripteur);
        PR = (EditText)findViewById(R.id.form_pays);
        finV = (EditText)findViewById(R.id.form_fin);
    }
}
