package com.example.kojar.rp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import Datenhaltung.Buffer;
import Logik.UGauss;

public class UebungenGleichungen extends AppCompatActivity {

    private Button starten;
    private Button stoppen;
    Chronometer chrono;
    private long time = 0;

    private TextView text;
    private SeekBar seekBar;
    private int dimension = 2;
    private Buffer buffer;
    private UGauss ug;
    private WebView w;
    private boolean uhrAmLaufen = false;
    private String aufgaben = "\\[ \\mathbf{Wählen \\quad Sie \\quad die \\quad Anzahl \\\\ der \\quad Unbekannte \\quad aus} \\]";
    private Button loesung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebungen_gleichungen);
        buffer = buffer.getInstance();
        ug = new UGauss();
        dimensionAuswahl();
        buttonAktivieren();
        starten = (Button) findViewById(R.id.UGstoppuherstarten);
        stoppen = (Button) findViewById(R.id.UGstoppuherstoppen);
        chrono = (Chronometer) findViewById(R.id.stoppuhrGauss);
        webview();
    }

    public void webview() {
        w = (WebView) findViewById(R.id.webViewG);
        WebSettings websettings = w.getSettings();
        websettings.setDefaultFontSize(12);
        w.getSettings().setJavaScriptEnabled(true);
        w.getSettings().setBuiltInZoomControls(true);
        w.loadDataWithBaseURL("http://bar", "<script type='text/x-mathjax-config'>"
                + "MathJax.Hub.Config({ showMathMenu: false, "
                + "jax: ['input/TeX','output/HTML-CSS'], "
                + "extensions: ['tex2jax.js'], "
                + "TeX: { extensions: ['AMSmath.js','AMSsymbols.js',"
                + "'noErrors.js','noUndefined.js'] } "
                + "});</script>"
                + "<script type='text/javascript' "
                + "src='file:///android_asset/MathJax/MathJax.js'"
                + "></script><span id='text'>" + aufgaben + "</span> <span id='math'></span>", "text/html", "utf-8", "");
        w.loadUrl("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);");
    }

    public void stoppuhrVerwalten(View v) {
        if (v.getId() == R.id.UGstoppuherstarten) {
            if (!uhrAmLaufen) {
                chrono.setBase(SystemClock.elapsedRealtime() + time);
                chrono.start();
                uhrAmLaufen = true;
            }
        } else if (v.getId() == R.id.UGstoppuherstoppen) {
            if (uhrAmLaufen) {
                time = chrono.getBase() - SystemClock.elapsedRealtime();
                chrono.stop();
                uhrAmLaufen = false;
            }
        }
    }

    public void stoppUhrStarten() {
        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.setText("00:00");
        chrono.stop();
        chrono.start();
    }

    public void ugVerwaltung(View v) {
        if (v.getId() == R.id.UGzeigen) {
            uhrAmLaufen = false;
            zeigen();
        } else if (v.getId() == R.id.UGberechnen) {
            berechnen();
        } else if (v.getId() == R.id.UGzurueck) {
            buffer.schrittListeLeeren();
            buffer.infoListeLeeren();
            buffer.gListeLeeren();
            Intent i = new Intent(getApplicationContext(), UebungenMenue.class);
            startActivity(i);
        } else if (v.getId() == R.id.UGinfo) {
            gleichungsInfo();
            uhrAmLaufen = false;
        }
    }

    public void gleichungsInfo() {
        buffer.infoListeLeeren();
        String s = "\\mathbf{  Hilfe \\quad Gleichungssystem } \\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {Allgemeines} } \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {1)} } \\quad Bei \\quad der \\quad Eingabe \\quad des \\\\ Gleichungssystems \\quad ist \\quad zu \\quad beachten, \\\\ dass \\quad zwar \\quad einzelne \\quad Koeffizienten \\quad 0 \\\\ " +
                "sein \\quad können, \\quad aber \\quad mindestens \\quad ein \\quad Koeffizient " +
                " \\\\ in \\quad jeder \\quad Spalte \\quad und \\quad in \\quad jeder \\\\ Zeile \\quad ungleich \\quad 0 \\quad sein \\quad muss.\\\\ Anderenfalls \\quad hat \\quad das \\quad Gleichungssystem \\quad keine" +
                "\\\\ eindeutige \\quad Lösung. \\quad Lediglich \\quad wenn \\quad die \\quad in  \\quad der \\\\" +
                "letzten \\quad Spalte \\quad nur \\quad Nullen \\quad eingegeben \\quad werden, \\\\ hat \\quad das \\quad Gleichungssystem \\quad möglicherweise \\\\ eine eindeutige \\quad Lösung, \\quad nämlich \\quad die \\quad triviale" +
                "\\\\ Lösung, \\quad d. h. \\quad alle \\quad Unbekannten \\quad sind \\\\ gleich \\quad 0. \\quad Daher \\quad wird \\quad die \\quad Eingabe \\\\ von \\quad Null-Zeilen \\quad  oder \\\\ Null-Spalten \\quad nicht \\quad akzeptiert \\quad und \\\\ die \\quad Berechnung \\quad abgebrochen.\\\\ \\quad \\\\";
        s = s + "{\\color{Red} {2)} } \\quad Wenn \\quad der \\quad Zurück-Button \\\\ betätigt \\quad wird, \\quad wird \\quad das \\\\ aktuell \\quad erstellt \\quad Gleichungssystem \\quad gelöscht. \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {3)} } \\quad Die \\quad Uhr \\quad mit \\quad der \\\\ Bearbeitungszeit \\quad wird \\quad automatisch \\quad gestartet, \\quad sobald \\\\ die \\quad Aufgabe \\quad gestellt \\quad wird. \\quad Während \\quad einer \\\\ " +
                "laufenden \\quad  Bearbeitung \\quad kann \\quad die \\quad Bearbeitungszeit \\\\ mit \\quad den \\quad Buttons \\quad Stoppen \\quad und \\\\ Starten \\quad unterbrochen \\quad bzw. \\quad fortgesetzt \\quad werden. \\\\ \\quad \\\\";
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

    public void berechnen() {
        ug.gaussErstellen(dimension);
        ug.gaussRechnen(buffer.gListeZurueckgebenABCD(dimension));
        zeigeAnmerkung("wird berechnet", 1000);
        aufgaben = "\\[\\mathbf{Berechnen \\quad Sie \\quad die \\quad Unbekannte } \\]" + buffer.gListeZurueckgebenABCD(dimension).druckedatenGauss();
        uhrAmLaufen = true;
        stoppUhrStarten();
        webview();
        buttonAktivieren();
        }

    public void zeigen() {
        time = chrono.getBase() - SystemClock.elapsedRealtime();
        chrono.stop();
        Intent i = new Intent(getApplicationContext(), Schritte.class);
        startActivity(i);
    }

    public void showInputMessage(String message) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(message);
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
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

    public void dimensionAuswahl() {
        text = (TextView) findViewById(R.id.UGtext);
        seekBar = (SeekBar) findViewById(R.id.UGseekbar);
        seekBar.setProgress(0);
        seekBar.setMax(3);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int p;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        p = progress;
                        zeigeAnmerkung("Neue Anzahl der Unbekannte", 500);
                        dimension = p + 2;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        text.setText((seekBar.getProgress() + 2) + " Unbekannte");
                        dimension = p + 2;
                    }
                }
        );
    }

    public void buttonAktivieren() {
        loesung = (Button) findViewById(R.id.UGzeigen);
        if (buffer.schrittListeZurueckgeben().isEmpty()) {
            loesung.setEnabled(false);
        } else {
            loesung.setEnabled(true);
        }
    }
}
