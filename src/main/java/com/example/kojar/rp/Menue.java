package com.example.kojar.rp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import Datenhaltung.Buffer;


public class Menue extends AppCompatActivity {

    private Buffer buffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menue);
        buffer = Buffer.getInstance();
    }

    public void menueVerwaltung(View v) {
        if (v.getId() == R.id.MEvektoren) {
            Intent i = new Intent(getApplicationContext(), Vektoren.class);
            startActivity(i);
        } else if (v.getId() == R.id.MEmatrixen) {
            Intent i = new Intent(getApplicationContext(), Matrizen.class);
            startActivity(i);
        } else if (v.getId() == R.id.MEgauss) {
            Intent i = new Intent(getApplicationContext(), GaussEntscheider.class);
            startActivity(i);
        } else if (v.getId() == R.id.beenden) {
            showInputMessage("App beenden ?");
        }else {
            Intent i = new Intent(getApplicationContext(), UebungenMenue.class);
            startActivity(i);
        }
    }


    public void showInputMessage(String message) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("JA", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        moveTaskToBack(true);
                        finish();
                    }
                });
        dlgAlert.setNegativeButton("NEIN", null);
        AlertDialog alert = dlgAlert.create();
        alert.show();
    }
}
