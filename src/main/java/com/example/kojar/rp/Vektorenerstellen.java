package com.example.kojar.rp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import Datenhaltung.Buffer;
import Logik.Vektor;

public class Vektorenerstellen extends AppCompatActivity {

    private int zeile = 2;
    private Buffer buffer;

    private Button zPlus;
    private Button zMinus;
    private TextView zeilenText;
    private TextView vektorName;
    private boolean gibtEsFehler = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vektorenerstellen);
        buffer = buffer.getInstance();
        setVektorName();
        verwalten();
        zeileUndSpalten();

    }

    public void setVektorName() {
        vektorName = (TextView) findViewById(R.id.VektorName);
        int b = buffer.getNavigation();
        if (b == 1) {
            vektorName.setText(Html.fromHtml("&rarr;<br>a =  "));
        } else if (b == 2) {
            vektorName.setText(Html.fromHtml("&rarr;<br>b =  "));
        } else if (b == 3) {
            vektorName.setText(Html.fromHtml("&rarr;<br>c =  "));
        }
    }

    public void zeileUndSpalten() {
        zPlus = (Button) findViewById(R.id.VplusZeile);
        zMinus = (Button) findViewById(R.id.VminusZeile);

        zeilenText = (TextView) findViewById(R.id.VanzahlZeilen);
        zeilenText.setText(zeile + " Dimension");

        zPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zeile == 2) {
                    zeile++;
                    zeilenText.setText(zeile + " Dimension");
                    felderAktivieren();

                }
            }
        });
        zMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zeile == 3) {
                    zeile--;
                    zeilenText.setText(zeile + " Dimension");
                    felderAktivieren();
                }
            }
        });

    }

    public void verwalten() {
        int gibtVektoren = buffer.getNavigation();
        if (gibtVektoren == 1) {
            if (!buffer.vListeIsEmptyABC(1)) {
                vektorenLesen(buffer.vListeZurueckgebenABC(1));
                zeile = buffer.vListeZurueckgebenABC(1).grosse;
            } else
                felderAktivieren();
        } else if (gibtVektoren == 2) {
            if (!buffer.vListeIsEmptyABC(2)) {
                vektorenLesen(buffer.vListeZurueckgebenABC(2));
                zeile = buffer.vListeZurueckgebenABC(2).grosse;

            } else
                felderAktivieren();
        } else if (gibtVektoren == 3) {
            if (!buffer.vListeIsEmptyABC(3)) {
                vektorenLesen(buffer.vListeZurueckgebenABC(3));
                zeile = buffer.vListeZurueckgebenABC(3).grosse;

            } else
                felderAktivieren();
        }
    }

    public void vektorenErstellenVerwaltung(View v) {
        if (v.getId() == R.id.VerstellenOK) {
            vektorenErstellen();
            if (!gibtEsFehler){
                Intent i = new Intent(getApplicationContext(), Vektoren.class);
                startActivity(i);
            }

        } else if (v.getId() == R.id.VerstellenAbbrechen) {
            Intent i = new Intent(getApplicationContext(), Vektoren.class);
            startActivity(i);
        }
    }

    public void vektorenErstellen() {
        EditText x = (EditText) findViewById(R.id.Vx);
        EditText y = (EditText) findViewById(R.id.Vy);
        EditText z = (EditText) findViewById(R.id.Vz);

        String xx = x.getText().toString();
        String yy = y.getText().toString();
        String zz = z.getText().toString();

        if (zeile == 2) {
            try {
                Vektor v = new Vektor(Double.parseDouble(xx), Double.parseDouble(yy), true, "", false);
                buffer.vListeAddABC(v, buffer.getNavigation());
                gibtEsFehler = false;
            } catch (Exception e) {
                gibtEsFehler=true;
                fehlermeldungZeigen("Geben Sie gültige Zahlen ein !");
            }

        } else {
            try {
                Vektor v = new Vektor(Double.parseDouble(xx), Double.parseDouble(yy), Double.parseDouble(zz), true, "", false);
                buffer.vListeAddABC(v, buffer.getNavigation());
                gibtEsFehler = false;
            } catch (Exception e) {
                gibtEsFehler=true;
                fehlermeldungZeigen("Geben Sie gültige Zahlen ein !");
            }

        }

    }

    public void fehlermeldungZeigen(String s) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(s);

        EditText massage = new EditText(this);
        massage.setInputType(InputType.TYPE_CLASS_TEXT);
        massage.setText(s);

        dlgAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dlgAlert.setNegativeButton("ABBRECHEN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getApplicationContext(), Vektoren.class);
                startActivity(i);
                dialog.dismiss();
            }
        });

        AlertDialog ad = dlgAlert.create();
        ad.show();
    }

    public void felderAktivieren() {
        EditText x = (EditText) findViewById(R.id.Vx);
        EditText y = (EditText) findViewById(R.id.Vy);
        EditText z = (EditText) findViewById(R.id.Vz);
        if (zeile == 2) {
            z.setEnabled(false);
        } else {
            z.setEnabled(true);
        }
        if (x.getText().toString().equals("")) {
            x.setText("0");
        }
        if (y.getText().toString().equals("")) {
            y.setText("0");
        }
        if (z.getText().toString().equals("")) {
            z.setText("0");
        }
        if (zeile == 2) {
            z.setText("");
        }
    }

    public static void hideSoftKeyboard(Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    public void keybordversteken(View v) {
        hideSoftKeyboard(Vektorenerstellen.this, v);
    }

    public void vektorenLesen(Vektor v) {
        EditText x = (EditText) findViewById(R.id.Vx);
        EditText y = (EditText) findViewById(R.id.Vy);
        EditText z = (EditText) findViewById(R.id.Vz);
        x.setText("");
        y.setText("");
        z.setText("");
        x.setText(ZA(v.getPV(1)));
        y.setText(ZA(v.getPV(2)));
        if (v.grosse == 2) {
            z.setText("");
            z.setEnabled(false);
        } else {
            z.setEnabled(true);
            z.setText(ZA(v.getPV(3)));
        }
    }
public  String ZA(double d) {
    if (hatKomma(d)) {
        return vorUndNachkommaZahlen(d);
    } else {
        return hatKeineKomma(d);
    }
}

    public  String vorUndNachkommaZahlen(double d) {
        String zahl = d + "";
        String vorZahln = "";
        String nachZahlen = "";
        String komma = ",";
        String nachZahlenn = "";
        int durch = 0;
        int nachkommazahlen = 0;
        boolean hilfe = true;

        for (int i = 0; i < zahl.length(); i++) {
            if (hilfe) {
                if (zahl.charAt(i) == '.') {
                    hilfe = false;
                    nachkommazahlen = i;
                }
                if (hilfe) {
                    vorZahln = vorZahln + zahl.charAt(i);
                }
            } else {
                nachZahlen = nachZahlen + zahl.charAt(i);
            }
        }

        return vorZahln + "." + nachZahlen;
    }

    public  boolean hatKomma(double d) {
        if (d % 1 == 0) {
            return false;
        } else
            return true;
    }

    public  String hatKeineKomma(double d) {
        return String.format("%d", (int) d);
    }
}
