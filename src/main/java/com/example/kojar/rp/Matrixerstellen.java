package com.example.kojar.rp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import Datenhaltung.Buffer;
import Logik.Matrix;
import Logik.Verwaltung;

public class Matrixerstellen extends AppCompatActivity {

    private int zeile = 2;
    private int spalte = 2;
    private Buffer buffer;

    private Button sPlus;
    private Button sMinus;
    private Button zPlus;
    private Button zMinus;

    private boolean gibtEsFehler = false;
    private TextView matrixname;
    private TextView spaltenText;
    private TextView zeilenText;
    private Verwaltung vr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matrixerstellen);
        buffer = buffer.getInstance();
        vr = new Verwaltung();
        matixname();
        verwalten();
        zeileUndSpalten();
    }

    public void zeileUndSpalten() {
        sPlus = (Button) findViewById(R.id.Mplusspalten);
        sMinus = (Button) findViewById(R.id.Mminusspalten);
        zPlus = (Button) findViewById(R.id.Mpluszeilen);
        zMinus = (Button) findViewById(R.id.Mminuszeilen);
        spaltenText = (TextView) findViewById(R.id.Mtextviewspalten);
        zeilenText = (TextView) findViewById(R.id.Mtextviewzeilen);

        if (spalte > 1) {
            spaltenText.setText("Spalten " + spalte);
            zeilenText.setText("Zeilen " + zeile);
        } else {
            spaltenText.setText("Spalte " + spalte);
            zeilenText.setText("Zeile " + zeile);
        }


        zPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zeile < 6) {
                    zeile++;
                    if (zeile == 1) {
                        zeilenText.setText("Zeile " + zeile);
                    } else {
                        zeilenText.setText("Zeilen " + zeile);
                    }
                    aktivieren();

                }
            }
        });
        zMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zeile > 1) {
                    zeile--;
                    if (zeile == 1) {
                        zeilenText.setText("Zeile " + zeile);
                    } else {
                        zeilenText.setText("Zeilen " + zeile);
                    }
                    aktivieren();
                }
            }
        });
        sPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spalte < 6) {
                    spalte++;
                    if (spalte == 1) {
                        spaltenText.setText("Spalte " + spalte);
                    } else {
                        spaltenText.setText("Spalten " + spalte);
                    }

                    aktivieren();
                }
            }
        });
        sMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spalte > 1) {
                    spalte--;
                    if (spalte == 1) {
                        spaltenText.setText("Spalte " + spalte);
                    } else {
                        spaltenText.setText("Spalten " + spalte);
                    }
                    aktivieren();
                }
            }
        });
    }

    public void verwalten() {
        int gibtMatrize = buffer.getNavigation();
        if (gibtMatrize == 1) {
            if (!buffer.mListeIsEmptyABC(1))
                matrizeLesen(buffer.mListeZurueckgebenABC(1));
            else
                aktivieren();
        } else if (gibtMatrize == 2) {
            if (!buffer.mListeIsEmptyABC(2))
                matrizeLesen(buffer.mListeZurueckgebenABC(2));
            else
                aktivieren();
        } else if (gibtMatrize == 3) {
            if (!buffer.mListeIsEmptyABC(3))
                matrizeLesen(buffer.mListeZurueckgebenABC(3));
            else
                aktivieren();
        }
    }

    public void aktivieren() {

        EditText[][] edittextFeld = new EditText[6][6];
        edittextFeld[0][0] = (EditText) findViewById(R.id.editText1);
        edittextFeld[0][1] = (EditText) findViewById(R.id.editText2);
        edittextFeld[0][2] = (EditText) findViewById(R.id.editText3);
        edittextFeld[0][3] = (EditText) findViewById(R.id.editText4);
        edittextFeld[0][4] = (EditText) findViewById(R.id.editText5);
        edittextFeld[0][5] = (EditText) findViewById(R.id.editText6);

        edittextFeld[1][0] = (EditText) findViewById(R.id.editText7);
        edittextFeld[1][1] = (EditText) findViewById(R.id.editText8);
        edittextFeld[1][2] = (EditText) findViewById(R.id.editText9);
        edittextFeld[1][3] = (EditText) findViewById(R.id.editText10);
        edittextFeld[1][4] = (EditText) findViewById(R.id.editText11);
        edittextFeld[1][5] = (EditText) findViewById(R.id.editText12);

        edittextFeld[2][0] = (EditText) findViewById(R.id.editText13);
        edittextFeld[2][1] = (EditText) findViewById(R.id.editText14);
        edittextFeld[2][2] = (EditText) findViewById(R.id.editText15);
        edittextFeld[2][3] = (EditText) findViewById(R.id.editText16);
        edittextFeld[2][4] = (EditText) findViewById(R.id.editText17);
        edittextFeld[2][5] = (EditText) findViewById(R.id.editText18);

        edittextFeld[3][0] = (EditText) findViewById(R.id.editText19);
        edittextFeld[3][1] = (EditText) findViewById(R.id.editText20);
        edittextFeld[3][2] = (EditText) findViewById(R.id.editText21);
        edittextFeld[3][3] = (EditText) findViewById(R.id.editText22);
        edittextFeld[3][4] = (EditText) findViewById(R.id.editText23);
        edittextFeld[3][5] = (EditText) findViewById(R.id.editText24);

        edittextFeld[4][0] = (EditText) findViewById(R.id.editText25);
        edittextFeld[4][1] = (EditText) findViewById(R.id.editText26);
        edittextFeld[4][2] = (EditText) findViewById(R.id.editText27);
        edittextFeld[4][3] = (EditText) findViewById(R.id.editText28);
        edittextFeld[4][4] = (EditText) findViewById(R.id.editText29);
        edittextFeld[4][5] = (EditText) findViewById(R.id.editText30);

        edittextFeld[5][0] = (EditText) findViewById(R.id.editText31);
        edittextFeld[5][1] = (EditText) findViewById(R.id.editText32);
        edittextFeld[5][2] = (EditText) findViewById(R.id.editText33);
        edittextFeld[5][3] = (EditText) findViewById(R.id.editText34);
        edittextFeld[5][4] = (EditText) findViewById(R.id.editText35);
        edittextFeld[5][5] = (EditText) findViewById(R.id.editText36);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                edittextFeld[i][j].setEnabled(false);
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (i < zeile && j < spalte) {
                    edittextFeld[i][j].setEnabled(true);
                    if (edittextFeld[i][j].getText().toString().equals("")) {
                        edittextFeld[i][j].setText("0");
                    }
                } else {
                    edittextFeld[i][j].setEnabled(false);
                    clearText(edittextFeld[i][j]);
                }
            }
        }
    }

    public void matrizeLesen(Matrix m) {

        EditText[][] edittextFeld = new EditText[6][6];
        edittextFeld[0][0] = (EditText) findViewById(R.id.editText1);
        edittextFeld[0][1] = (EditText) findViewById(R.id.editText2);
        edittextFeld[0][2] = (EditText) findViewById(R.id.editText3);
        edittextFeld[0][3] = (EditText) findViewById(R.id.editText4);
        edittextFeld[0][4] = (EditText) findViewById(R.id.editText5);
        edittextFeld[0][5] = (EditText) findViewById(R.id.editText6);

        edittextFeld[1][0] = (EditText) findViewById(R.id.editText7);
        edittextFeld[1][1] = (EditText) findViewById(R.id.editText8);
        edittextFeld[1][2] = (EditText) findViewById(R.id.editText9);
        edittextFeld[1][3] = (EditText) findViewById(R.id.editText10);
        edittextFeld[1][4] = (EditText) findViewById(R.id.editText11);
        edittextFeld[1][5] = (EditText) findViewById(R.id.editText12);

        edittextFeld[2][0] = (EditText) findViewById(R.id.editText13);
        edittextFeld[2][1] = (EditText) findViewById(R.id.editText14);
        edittextFeld[2][2] = (EditText) findViewById(R.id.editText15);
        edittextFeld[2][3] = (EditText) findViewById(R.id.editText16);
        edittextFeld[2][4] = (EditText) findViewById(R.id.editText17);
        edittextFeld[2][5] = (EditText) findViewById(R.id.editText18);

        edittextFeld[3][0] = (EditText) findViewById(R.id.editText19);
        edittextFeld[3][1] = (EditText) findViewById(R.id.editText20);
        edittextFeld[3][2] = (EditText) findViewById(R.id.editText21);
        edittextFeld[3][3] = (EditText) findViewById(R.id.editText22);
        edittextFeld[3][4] = (EditText) findViewById(R.id.editText23);
        edittextFeld[3][5] = (EditText) findViewById(R.id.editText24);

        edittextFeld[4][0] = (EditText) findViewById(R.id.editText25);
        edittextFeld[4][1] = (EditText) findViewById(R.id.editText26);
        edittextFeld[4][2] = (EditText) findViewById(R.id.editText27);
        edittextFeld[4][3] = (EditText) findViewById(R.id.editText28);
        edittextFeld[4][4] = (EditText) findViewById(R.id.editText29);
        edittextFeld[4][5] = (EditText) findViewById(R.id.editText30);

        edittextFeld[5][0] = (EditText) findViewById(R.id.editText31);
        edittextFeld[5][1] = (EditText) findViewById(R.id.editText32);
        edittextFeld[5][2] = (EditText) findViewById(R.id.editText33);
        edittextFeld[5][3] = (EditText) findViewById(R.id.editText34);
        edittextFeld[5][4] = (EditText) findViewById(R.id.editText35);
        edittextFeld[5][5] = (EditText) findViewById(R.id.editText36);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                edittextFeld[i][j].setEnabled(false);
                clearText(edittextFeld[i][j]);
            }
        }

        for (int i = 0; i < m.getZeile(); i++) {
            for (int j = 0; j < m.getSpalten(); j++) {
                edittextFeld[i][j].setEnabled(true);
                edittextFeld[i][j].setText(ZA(m.getDoubleIndexPosition(i, j)));
            }
        }
        zeile = m.getZeile();
        spalte = m.getSpalten();
    }

    public void erstellen() {
        EditText[][] edittextFeld = new EditText[6][6];
        edittextFeld[0][0] = (EditText) findViewById(R.id.editText1);
        edittextFeld[0][1] = (EditText) findViewById(R.id.editText2);
        edittextFeld[0][2] = (EditText) findViewById(R.id.editText3);
        edittextFeld[0][3] = (EditText) findViewById(R.id.editText4);
        edittextFeld[0][4] = (EditText) findViewById(R.id.editText5);
        edittextFeld[0][5] = (EditText) findViewById(R.id.editText6);

        edittextFeld[1][0] = (EditText) findViewById(R.id.editText7);
        edittextFeld[1][1] = (EditText) findViewById(R.id.editText8);
        edittextFeld[1][2] = (EditText) findViewById(R.id.editText9);
        edittextFeld[1][3] = (EditText) findViewById(R.id.editText10);
        edittextFeld[1][4] = (EditText) findViewById(R.id.editText11);
        edittextFeld[1][5] = (EditText) findViewById(R.id.editText12);

        edittextFeld[2][0] = (EditText) findViewById(R.id.editText13);
        edittextFeld[2][1] = (EditText) findViewById(R.id.editText14);
        edittextFeld[2][2] = (EditText) findViewById(R.id.editText15);
        edittextFeld[2][3] = (EditText) findViewById(R.id.editText16);
        edittextFeld[2][4] = (EditText) findViewById(R.id.editText17);
        edittextFeld[2][5] = (EditText) findViewById(R.id.editText18);

        edittextFeld[3][0] = (EditText) findViewById(R.id.editText19);
        edittextFeld[3][1] = (EditText) findViewById(R.id.editText20);
        edittextFeld[3][2] = (EditText) findViewById(R.id.editText21);
        edittextFeld[3][3] = (EditText) findViewById(R.id.editText22);
        edittextFeld[3][4] = (EditText) findViewById(R.id.editText23);
        edittextFeld[3][5] = (EditText) findViewById(R.id.editText24);

        edittextFeld[4][0] = (EditText) findViewById(R.id.editText25);
        edittextFeld[4][1] = (EditText) findViewById(R.id.editText26);
        edittextFeld[4][2] = (EditText) findViewById(R.id.editText27);
        edittextFeld[4][3] = (EditText) findViewById(R.id.editText28);
        edittextFeld[4][4] = (EditText) findViewById(R.id.editText29);
        edittextFeld[4][5] = (EditText) findViewById(R.id.editText30);

        edittextFeld[5][0] = (EditText) findViewById(R.id.editText31);
        edittextFeld[5][1] = (EditText) findViewById(R.id.editText32);
        edittextFeld[5][2] = (EditText) findViewById(R.id.editText33);
        edittextFeld[5][3] = (EditText) findViewById(R.id.editText34);
        edittextFeld[5][4] = (EditText) findViewById(R.id.editText35);
        edittextFeld[5][5] = (EditText) findViewById(R.id.editText36);

        String[][] stringFeld = new String[zeile][spalte];
        for (int i = 0; i < zeile; i++) {
            for (int j = 0; j < spalte; j++) {
                stringFeld[i][j] = edittextFeld[i][j].getText().toString();
            }
        }
        try {
            double[][] doubleFeld = new double[zeile][spalte];

            for (int i = 0; i < zeile; i++) {
                for (int j = 0; j < spalte; j++) {
                    doubleFeld[i][j] = Double.parseDouble(stringFeld[i][j]);
                }
            }
            Matrix mm = new Matrix(zeile, spalte, true, "", false);
            for (int i = 0; i < zeile; i++) {
                for (int j = 0; j < spalte; j++) {
                    mm.setDoubleIndexPosition(i, j, doubleFeld[i][j]);
                }
            }
            buffer.mListeAddABC(mm, buffer.getNavigation());
            gibtEsFehler = false;
        } catch (Exception e) {
            fehlermeldungZeigen("Geben Sie Gültige Zahlen ein !!!");
            gibtEsFehler = true;
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
                Intent i = new Intent(getApplicationContext(), Matrizen.class);
                startActivity(i);
                dialog.dismiss();
            }
        });

        AlertDialog ad = dlgAlert.create();
        ad.show();
    }

    public void matrixErstellenVerwaltung(View v) {
        if (v.getId() == R.id.MatrixerstellenOK) {
            erstellen();
            if (!gibtEsFehler) {
                Intent i = new Intent(getApplicationContext(), Matrizen.class);
                startActivity(i);
            }
        } else if (v.getId() == R.id.MatrixerstellenAbbrechen) {
            Intent i = new Intent(getApplicationContext(), Matrizen.class);
            startActivity(i);
        }
    }

    public void showInputMessage(String message) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(message);
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    public void matixname() {
        matrixname = (TextView) findViewById(R.id.matrixname);
        int z = buffer.getNavigation();
        if (z == 1) {
            matrixname.setText("A =");
        } else if (z == 2) {
            matrixname.setText("B =");
        } else if (z == 3) {
            matrixname.setText("C =");
        }
    }

    public static void hideSoftKeyboard(Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    public void keybordversteken(View v) {
        hideSoftKeyboard(Matrixerstellen.this, v);
    }

    public void clearText(EditText e) {
        e.setText("");
    }

    public String ZA(double d) {
        if (hatKomma(d)) {
            return vorUndNachkommaZahlen(d);
        } else {
            return hatKeineKomma(d);
        }
    }

    public String vorUndNachkommaZahlen(double d) {
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


    public boolean hatKomma(double d) {
        if (d % 1 == 0) {
            return false;
        } else
            return true;
    }

    public String hatKeineKomma(double d) {
        return String.format("%d", (int) d);
    }
}
