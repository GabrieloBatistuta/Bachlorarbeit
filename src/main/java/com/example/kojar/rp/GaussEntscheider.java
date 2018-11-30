package com.example.kojar.rp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import Datenhaltung.Buffer;

public class GaussEntscheider extends AppCompatActivity {


    private Buffer buffer;
    private int dimension = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gauss_entscheider);
        buffer = buffer.getInstance();
        dimAuswahl();

    }

    public void gaussEntscheiderVerwaltung(View v) {
        if (v.getId() == R.id.GentscheiderOK) {
            if (dimension == 2) {
                Intent i = new Intent(getApplicationContext(), Gauss2.class);
                startActivity(i);
            } else if (dimension == 3) {
                Intent i = new Intent(getApplicationContext(), Gauss3.class);
                startActivity(i);
            } else if (dimension == 4) {
                Intent i = new Intent(getApplicationContext(), Gauss4.class);
                startActivity(i);
            } else if (dimension == 5) {
                Intent i = new Intent(getApplicationContext(), Gauss5.class);
                startActivity(i);
            }
        } else if (v.getId() == R.id.GentscheiderAbbrechen) {
            buffer.schrittListeLeeren();
            buffer.gListeLeeren();
            Intent i = new Intent(getApplicationContext(), Menue.class);
            startActivity(i);
        }
    }

    public void dimAuswahl() {
        Button hoch = (Button) findViewById(R.id.GentscheiderHoch);
        Button runter = (Button) findViewById(R.id.GentscheiderRunter);
        final TextView text = (TextView) findViewById(R.id.GentscheiderText);
        text.setText(dimension + " Unbekannte");
        hoch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dimension < 5) {
                    dimension++;
                    text.setText(dimension + " Unbekannte");
                    zeigeAnmerkung("Anzahl der Unbekannte wird geändert !", 1000);
                }
            }
        });
        runter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dimension > 2) {
                    dimension--;
                    text.setText(dimension + " Unbekannte");
                    zeigeAnmerkung("Anzahl der Unbekannte wird geändert !", 1000);
                }
            }
        });
    }

    public void zeigeAnmerkung(String anmerkung, int milisekunde) {
        final Toast toast = Toast.makeText(getApplicationContext(), anmerkung, Toast.LENGTH_SHORT);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, milisekunde);
    }

}
