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
import android.widget.EditText;

import Datenhaltung.Buffer;
import Logik.Matrix;
import Logik.Objekt;
import Logik.Verwaltung;

public class Gauss4 extends AppCompatActivity {


    private Verwaltung vr;
    private Buffer buffer;
    private boolean falsch = false;
    private boolean gibtEsFehler = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gauss4);
        vr = new Verwaltung();
        buffer = buffer.getInstance();
        aktivieren();
    }


    public void gaussVerwaltung(View v) {
        if (v.getId() == R.id.Gabbrechen4) {
            buffer.schrittListeLeeren();
            buffer.gListeLeeren();
            Intent i = new Intent(getApplicationContext(), Menue.class);
            startActivity(i);
        } else if (v.getId() == R.id.GerstellenOK4) {
            gaussRechnen();
        } else if (v.getId() == R.id.Gzurueck4) {
            buffer.schrittListeLeeren();
            buffer.gListeLeeren();
            Intent i = new Intent(getApplicationContext(), GaussEntscheider.class);
            startActivity(i);
        } else if (v.getId() == R.id.Ginfo4) {
            gleichungenInfo();
        }
    }

    public void gleichungenInfo() {
        String s = "\\mathbf{  Hilfe \\quad Gleichungssystem } \\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {Allgemeines} } \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {1)} } \\quad Um \\quad ein \\quad Gleichungssystem \\quad lösen \\quad zu \\\\ können, \\quad müssen \\quad " +
                "zunächst \\quad die \\quad erforderlichen \\\\ Koeffizienten \\quad in \\quad die \\quad Eingabefelder \\quad eingetragen \\quad werden.\\\\ \\quad \\\\";
        s = s + "{\\color{Red} {2)} } \\quad Bei \\quad der \\quad Eingabe \\quad des \\\\ Gleichungssystems \\quad ist \\quad zu \\quad beachten, \\\\ dass \\quad zwar \\quad einzelne \\quad Koeffizienten \\quad 0 \\\\ " +
                "sein \\quad können, \\quad aber \\quad mindestens \\quad ein \\quad Koeffizient " +
                " \\\\ in \\quad jeder \\quad Spalte \\quad und \\quad in \\quad jeder \\\\ Zeile \\quad ungleich \\quad 0 \\quad sein \\quad muss.\\\\ Anderenfalls \\quad hat \\quad das \\quad Gleichungssystem \\quad keine" +
                "\\\\ eindeutige \\quad Lösung. \\quad Lediglich \\quad wenn \\quad die \\quad in  \\quad der \\\\" +
                "letzten \\quad Spalte \\quad nur \\quad Nullen \\quad eingegeben \\quad werden, \\\\ hat \\quad das \\quad Gleichungssystem \\quad möglicherweise \\\\ eine eindeutige \\quad Lösung, \\quad nämlich \\quad die \\quad triviale" +
                "\\\\ Lösung, \\quad d. h. \\quad alle \\quad Unbekannten \\quad sind \\\\ gleich \\quad 0. \\quad Daher \\quad wird \\quad die \\quad Eingabe \\\\ von \\quad Null-Zeilen \\quad  oder \\\\ Null-Spalten \\quad nicht \\quad akzeptiert \\quad und \\\\ die \\quad Berechnung \\quad abgebrochen.\\\\ \\quad \\\\";
        s = s + "{\\color{Red} {3)} } \\quad Wenn \\quad der \\quad Zurück-Button \\\\ betätigt \\quad wird, \\quad wird \\quad das \\\\ aktuell \\quad erstellt \\quad Gleichungssystem \\quad gelöscht. \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {Berechnung} } \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {4)} } \\quad Allgemein \\quad kann \\quad ein \\quad lineares \\\\ Gleichungssystem \\quad mit \\quad n \\quad Gleichungen \\quad für \\quad n \\\\ Unbekannte  \\quad in \\quad der \\quad Form \\quad in \\quad eine \\\\" +
                " Dreiecksform \\quad gebracht \\quad werden. \\quad Bei \\quad dieser \\\\ Form \\quad hat \\quad jede \\quad Gleichung \\quad genau \\\\ eine \\quad Unbekannte \\quad weniger \\quad als \\quad die \\quad vorherige:\\\\ \\quad \\\\" +
                "{\\begin{matrix}a_{{11}}x_{1} & + & a_{{12}}x_{2}& + & \\cdots & + & a_{{1n}}x_{n} & = & b_{1} \\\\0 & + & a_{{22}}x_{2} & + & \\cdots & + & a_{{2n}}x_{n} & = & b_{2}\\\\ \\vdots & & \\vdots & & \\vdots & & \\vdots & & \\vdots \\\\ 0 & + & 0 & + &  \\cdots & + & a_{{nn}}x_{n}&=&b_{n}\\end{matrix}} \\\\ \\quad \\\\" +
                "Durch \\quad die \\quad App \\quad wird \\quad diese \\\\ Dreiecksform \\quad durch \\quad die \\quad Anwendung \\quad des \\\\ Gaußschen \\quad Eliminationsverfahrens \\quad erzeugt. \\\\" +
                "Es \\quad möglich \\quad das \\quad im \\quad Zuge \\quad der \\\\ Durchführung \\quad des \\quad Gauß-Algorithmus \\quad die \\\\ gewünschte \\quad Dreiecksform \\quad nicht \\quad erreicht \\quad werden \\\\ kann, \\quad weil \\quad eine \\quad Zeile \\quad des \\\\ modifizierten \\quad Systems \\quad nur \\quad Nullen \\quad enthält." +
                "\\\\ In \\quad diesem  \\quad Fall \\quad wird \\quad die \\quad Berechnung \\\\ abgebrochen, \\quad weil \\quad das \\quad Gleichungssystem \\quad unendlich \\\\ viele \\quad Lösungen \\quad hat.\\\\ \\quad \\\\ ";
        s = s + "{\\color{Red} {5)} } \\quad Die \\quad Lösung \\quad des \\quad Gleichungssystems \\\\ erfolgt \\quad anhand \\quad der \\quad Dreiecksform. \\quad Zunächst \\\\ wird \\quad die \\quad letzte \\quad Gleichung \\quad die \\\\ " +
                "für \\quad n-te \\quad Unbekannte \\quad gelöst. \\quad Anschließend \\quad wird \\\\ unter \\quad Verwendung \\quad dieses \\quad Ergebnisses \\quad die \\quad vorletzte \\\\ Gleichung \\quad für \\quad die \\quad (n-1)-te \\quad Unbekannte \\\\" +
                " gelöst. \\quad Diese \\quad Prozedur \\quad wird \\quad mit \\quad allen \\\\ darüber \\quad liegenden \\quad Gleichungen \\quad wiederholt \\quad bis \\quad alle \\\\ Unbekannten \\quad berechnet \\quad sind. \\quad Allgemein \\quad gilt \\quad für \\\\ " +
                "die \\quad k-te \\quad Unbekannte.";
        buffer.infoListeAdd("\\[" + s + "\\]");
        Intent i = new Intent(getApplicationContext(), Informationen.class);
        startActivity(i);
    }

    public void schritte() {
        if (buffer.getSchritteListe().isEmpty()) {
            showInputMessage("Es gibt keine gespeicherte Schritte !");
        } else {
            Intent i = new Intent(getApplicationContext(), Schritte.class);
            startActivity(i);
        }


    }

    public void aktivieren() {
        if (buffer.gListeIsEmpty(4)) {
            gaussAktivieren();
        } else
            gaussLesen();
    }

    public void gaussAktivieren() {

        EditText[][] edittextFeld = new EditText[4][5];
        edittextFeld[0][0] = (EditText) findViewById(R.id.editText1);
        edittextFeld[0][1] = (EditText) findViewById(R.id.editText2);
        edittextFeld[0][2] = (EditText) findViewById(R.id.editText3);
        edittextFeld[0][3] = (EditText) findViewById(R.id.editText4);
        edittextFeld[0][4] = (EditText) findViewById(R.id.editText5);


        edittextFeld[1][0] = (EditText) findViewById(R.id.editText7);
        edittextFeld[1][1] = (EditText) findViewById(R.id.editText8);
        edittextFeld[1][2] = (EditText) findViewById(R.id.editText9);
        edittextFeld[1][3] = (EditText) findViewById(R.id.editText10);
        edittextFeld[1][4] = (EditText) findViewById(R.id.editText11);

        edittextFeld[2][0] = (EditText) findViewById(R.id.editText13);
        edittextFeld[2][1] = (EditText) findViewById(R.id.editText14);
        edittextFeld[2][2] = (EditText) findViewById(R.id.editText15);
        edittextFeld[2][3] = (EditText) findViewById(R.id.editText16);
        edittextFeld[2][4] = (EditText) findViewById(R.id.editText17);

        edittextFeld[3][0] = (EditText) findViewById(R.id.editText19);
        edittextFeld[3][1] = (EditText) findViewById(R.id.editText20);
        edittextFeld[3][2] = (EditText) findViewById(R.id.editText21);
        edittextFeld[3][3] = (EditText) findViewById(R.id.editText22);
        edittextFeld[3][4] = (EditText) findViewById(R.id.editText23);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (edittextFeld[i][j].getText().toString().equals("")) {
                    edittextFeld[i][j].setText("0");
                }
            }
        }
    }

    public void gaussLesen() {
        EditText[][] edittextFeld = new EditText[4][5];
        Matrix m = buffer.gListeZurueckgebenABCD(4);
        edittextFeld[0][0] = (EditText) findViewById(R.id.editText1);
        edittextFeld[0][1] = (EditText) findViewById(R.id.editText2);
        edittextFeld[0][2] = (EditText) findViewById(R.id.editText3);
        edittextFeld[0][3] = (EditText) findViewById(R.id.editText4);
        edittextFeld[0][4] = (EditText) findViewById(R.id.editText5);


        edittextFeld[1][0] = (EditText) findViewById(R.id.editText7);
        edittextFeld[1][1] = (EditText) findViewById(R.id.editText8);
        edittextFeld[1][2] = (EditText) findViewById(R.id.editText9);
        edittextFeld[1][3] = (EditText) findViewById(R.id.editText10);
        edittextFeld[1][4] = (EditText) findViewById(R.id.editText11);

        edittextFeld[2][0] = (EditText) findViewById(R.id.editText13);
        edittextFeld[2][1] = (EditText) findViewById(R.id.editText14);
        edittextFeld[2][2] = (EditText) findViewById(R.id.editText15);
        edittextFeld[2][3] = (EditText) findViewById(R.id.editText16);
        edittextFeld[2][4] = (EditText) findViewById(R.id.editText17);

        edittextFeld[3][0] = (EditText) findViewById(R.id.editText19);
        edittextFeld[3][1] = (EditText) findViewById(R.id.editText20);
        edittextFeld[3][2] = (EditText) findViewById(R.id.editText21);
        edittextFeld[3][3] = (EditText) findViewById(R.id.editText22);
        edittextFeld[3][4] = (EditText) findViewById(R.id.editText23);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {

                edittextFeld[i][j].setText(m.getStringIndexPosition(i, j));

            }
        }
    }

    public static void hideSoftKeyboard(Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    public void keybordversteken(View v) {
        hideSoftKeyboard(Gauss4.this, v);
    }


    public void ErgebnisseSpeichern(Objekt o) {
        Intent i = new Intent(getApplicationContext(), Schritte.class);
        startActivity(i);
    }

    public void gaussRechnen() {
        gaussErstellen();
        if (gibtEsFehler) {
            fehlermeldungZeigen("Geben Sie gültige Zahlen ein !");
        } else {
            ErgebnisseSpeichern(vr.gaussBerechnen(buffer.gListeZurueckgebenABCD(4)));
        }
    }

    public void gaussErstellen() {
        gibtEsFehler = falsch;
        EditText[][] edittextFeld = new EditText[6][6];

        edittextFeld[0][0] = (EditText) findViewById(R.id.editText1);
        edittextFeld[0][1] = (EditText) findViewById(R.id.editText2);
        edittextFeld[0][2] = (EditText) findViewById(R.id.editText3);
        edittextFeld[0][3] = (EditText) findViewById(R.id.editText4);
        edittextFeld[0][4] = (EditText) findViewById(R.id.editText5);

        edittextFeld[1][0] = (EditText) findViewById(R.id.editText7);
        edittextFeld[1][1] = (EditText) findViewById(R.id.editText8);
        edittextFeld[1][2] = (EditText) findViewById(R.id.editText9);
        edittextFeld[1][3] = (EditText) findViewById(R.id.editText10);
        edittextFeld[1][4] = (EditText) findViewById(R.id.editText11);

        edittextFeld[2][0] = (EditText) findViewById(R.id.editText13);
        edittextFeld[2][1] = (EditText) findViewById(R.id.editText14);
        edittextFeld[2][2] = (EditText) findViewById(R.id.editText15);
        edittextFeld[2][3] = (EditText) findViewById(R.id.editText16);
        edittextFeld[2][4] = (EditText) findViewById(R.id.editText17);

        edittextFeld[3][0] = (EditText) findViewById(R.id.editText19);
        edittextFeld[3][1] = (EditText) findViewById(R.id.editText20);
        edittextFeld[3][2] = (EditText) findViewById(R.id.editText21);
        edittextFeld[3][3] = (EditText) findViewById(R.id.editText22);
        edittextFeld[3][4] = (EditText) findViewById(R.id.editText23);

        edittextFeld[4][0] = (EditText) findViewById(R.id.editText25);
        edittextFeld[4][1] = (EditText) findViewById(R.id.editText26);
        edittextFeld[4][2] = (EditText) findViewById(R.id.editText27);
        edittextFeld[4][3] = (EditText) findViewById(R.id.editText28);
        edittextFeld[4][4] = (EditText) findViewById(R.id.editText29);


        String[][] stringFeld = new String[6][6];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                stringFeld[i][j] = edittextFeld[i][j].getText().toString();
            }
        }
        double zahl = 0;
        Matrix m = vr.matrixErzeugen(4, 5);
        for (int i = 0; i < m.getZeile(); i++) {
            for (int j = 0; j < m.getSpalten(); j++) {
                try {
                    zahl = Double.parseDouble(stringFeld[i][j]);
                    m.setDoubleIndexPosition(i, j, zahl);
                    gibtEsFehler = false;
                } catch (Exception e) {
                    gibtEsFehler = true;
                    return;
                }
            }
        }
        if (!gibtEsFehler)
            buffer.gListeAddABCD(m, 4);
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
                Intent i = new Intent(getApplicationContext(), GaussEntscheider.class);
                startActivity(i);
                dialog.dismiss();
            }
        });

        AlertDialog ad = dlgAlert.create();
        ad.show();
    }

    public void showInputMessage(String message) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(message);
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
}
