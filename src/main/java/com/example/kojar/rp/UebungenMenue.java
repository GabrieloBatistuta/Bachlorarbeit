package com.example.kojar.rp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UebungenMenue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uebungenmenue);

    }

    public void uebuengenVerwaltung(View v) {
        if (v.getId() == R.id.Uvektoren) {
            Intent i = new Intent(getApplicationContext(), UebungenVektoren.class);
            startActivity(i);
        } else if (v.getId() == R.id.Umatrizen) {
            Intent i = new Intent(getApplicationContext(), UebungenMatrizen.class);
            startActivity(i);
        } else if (v.getId() == R.id.Ugauss) {
            Intent i = new Intent(getApplicationContext(), UebungenGleichungen.class);
            startActivity(i);
        } else if (v.getId() == R.id.Uzurueck) {
            Intent i = new Intent(this, Menue.class);
            startActivity(i);
        }
    }
}
