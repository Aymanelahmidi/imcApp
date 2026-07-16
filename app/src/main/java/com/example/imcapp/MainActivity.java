package com.example.imcapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etPoids, etTaille;
    private Button btnCalculer;
    private TextView tvResultat, tvCategorie;
    private ImageView imgCategorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPoids     = findViewById(R.id.etPoids);
        etTaille    = findViewById(R.id.etTaille);
        btnCalculer = findViewById(R.id.btnCalculer);
        tvResultat  = findViewById(R.id.tvResultat);
        tvCategorie = findViewById(R.id.tvCategorie);
        imgCategorie = findViewById(R.id.imgCategorie);

        btnCalculer.setOnClickListener(v -> calculerIMC());
    }

    private void calculerIMC() {
        String sPoids  = etPoids.getText().toString().trim();
        String sTaille = etTaille.getText().toString().trim();

        if (sPoids.isEmpty() || sTaille.isEmpty()) {
            Toast.makeText(this, getString(R.string.erreur_champs_vides), Toast.LENGTH_SHORT).show();
            return;
        }

        double poids  = Double.parseDouble(sPoids);
        double taille = Double.parseDouble(sTaille) / 100.0; // cm -> m
        double imc    = poids / (taille * taille);

        // Affichage du résultat
        tvResultat.setText(getString(R.string.label_resultat) + String.format("%.2f", imc));
        tvResultat.setVisibility(View.VISIBLE);

        // Détermination de la catégorie
        int drawableRes;
        String categorie;

        if (imc < 18.5) {
            drawableRes = R.drawable.maigre;
            categorie   = getString(R.string.categorie_maigre);
        } else if (imc < 25) {
            drawableRes = R.drawable.normal;
            categorie   = getString(R.string.categorie_normal);
        } else if (imc < 30) {
            drawableRes = R.drawable.surpoids;
            categorie   = getString(R.string.categorie_surpoids);
        } else if (imc < 35) {
            drawableRes = R.drawable.obese;
            categorie   = getString(R.string.categorie_obese);
        } else {
            drawableRes = R.drawable.t_obese;
            categorie   = getString(R.string.categorie_tobese);
        }

        imgCategorie.setImageResource(drawableRes);
        imgCategorie.setVisibility(View.VISIBLE);

        tvCategorie.setText(categorie);
        tvCategorie.setVisibility(View.VISIBLE);
    }
}
