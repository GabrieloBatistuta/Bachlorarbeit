package com.example.kojar.rp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import Datenhaltung.Buffer;
import Logik.UVektoren;
import Logik.Verwaltung;


public class UebungenVektoren extends AppCompatActivity {

    private Button starten;
    private Button stoppen;
    private Chronometer chrono;
    private long time = 0;

    private Verwaltung vw = new Verwaltung();
    private UVektoren uv = new UVektoren();
    private Buffer buffer;
    private SeekBar seekBar = null;
    private TextView text;
    private int dimeinsion = 2;
    private Button kreuzprodukt;
    private String aufgaben = "\\[ \\mathbf{Wählen \\quad Sie \\quad eine \\quad Aufgabe \\quad aus} \\]";
    private WebView w;
    private boolean uhrAmLaufen = false;
    private Button loesung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebungen_vektoren);
        buffer = buffer.getInstance();
        buttonsDiaktivieren();
        vw = new Verwaltung();
        dimensionAuswahl();
        buttonsSymbole();
        starten = (Button) findViewById(R.id.UVstopuhrstarten);
        stoppen = (Button) findViewById(R.id.UVstopuhrstoppen);
        chrono = (Chronometer) findViewById(R.id.stoppuhrVektoren);
        webview();
    }

    public void webview() {
        w = (WebView) findViewById(R.id.webViewV);
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

        if (v.getId() == R.id.UVstopuhrstarten) {
            if (!uhrAmLaufen) {
                chrono.setBase(SystemClock.elapsedRealtime() + time);
                chrono.start();
                uhrAmLaufen = true;
            }

        } else if (v.getId() == R.id.UVstopuhrstoppen) {
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

    public void uvVerwaltung(View v) {
        int anmerkungZeit = 800;
        if (v.getId() == R.id.UVzurueck) {
            zurueck();
        } else if (v.getId() == R.id.UVaddieren) {
            zeigeAnmerkung("Es wurde addieret !", anmerkungZeit);
            addieren();
            aufgaben = "\\[ \\mathbf{Addieren\\quad Sie \\quad die \\quad Vektoren \\quad \\vec{a} \\quad und \\quad \\vec{b} } \\]" + "\\[" + buffer.vListeZurueckgebenABC(1).druckeDaten("a") + ",\\quad" + buffer.vListeZurueckgebenABC(2).druckeDaten("b") + "\\]";
            webview();
            uhrAmLaufen = true;
            stoppUhrStarten();
        } else if (v.getId() == R.id.UVsubtrahrieren) {
            zeigeAnmerkung("Es wurde  subtrahiriert!", anmerkungZeit);
            subtrahieren();
            aufgaben = "\\[ \\mathbf{Subtrahrieren\\quad Sie \\quad den \\quad Vektoren \\\\ \\vec{b} \\quad vom \\quad Vektor \\quad \\vec{a} } \\]" + "\\[" + buffer.vListeZurueckgebenABC(1).druckeDaten("a") + ",\\quad" + buffer.vListeZurueckgebenABC(2).druckeDaten("b") + "\\]";
            webview();
            uhrAmLaufen = true;
            stoppUhrStarten();
        } else if (v.getId() == R.id.UVinfo) {
            uhrAmLaufen = false;
            vektorenInfo();
        } else if (v.getId() == R.id.UVkreuzprodukt) {
            zeigeAnmerkung("Es wurde gerechnet !", anmerkungZeit);
            kreuzprodukt();
            aufgaben = "\\[ \\mathbf{Berechnen\\quad Sie \\quad das \\quad Kreuzprodukt \\quad der \\\\ Vektoren \\quad \\vec{a} \\quad und \\quad \\vec{b} } \\]" + "\\[" + buffer.vListeZurueckgebenABC(1).druckeDaten("a") + ",\\quad" + buffer.vListeZurueckgebenABC(2).druckeDaten("b") + "\\]";
            webview();
            uhrAmLaufen = true;
            stoppUhrStarten();
        } else if (v.getId() == R.id.UVmitZahlmulti) {
            double z;
            zeigeAnmerkung("Es wurde multipliziert !", anmerkungZeit);
            z = mitZahlmultiplizieren();
            aufgaben = "\\[ \\mathbf{ Multiplizieren \\quad Sie \\quad den \\quad Vektor \\quad \\vec{a} \\\\ mit \\quad \\lambda } \\]" + "\\[" + buffer.vListeZurueckgebenABC(1).druckeDaten("a") + ", \\quad \\lambda = " + (int) z + "\\]";
            webview();
            uhrAmLaufen = true;
            stoppUhrStarten();
        } else if (v.getId() == R.id.UVsenkrecht) {
            zeigeAnmerkung("Es wurde überbrüft !", anmerkungZeit);
            senkrecht();
            aufgaben = "\\[ \\mathbf{ Überprüfen \\quad Sie,\\quad ob \\quad die \\quad Vektoren \\\\ \\vec{a} \\quad und \\quad \\vec{b} \\quad senkrecht \\quad zueinandern \\quad sind  } \\]" + "\\[" + buffer.vListeZurueckgebenABC(1).druckeDaten("a") + ", \\quad" + buffer.vListeZurueckgebenABC(2).druckeDaten("b") + "\\]";
            webview();
            uhrAmLaufen = true;
            stoppUhrStarten();
        } else if (v.getId() == R.id.UVwinkel) {
            zeigeAnmerkung("Es wurde gerechnet !", anmerkungZeit);
            winkel();
            aufgaben = "\\[ \\mathbf{ Berechnen \\quad Sie \\quad den \\quad Winkel \\quad zwischen \\\\ den \\quad Vektoren \\quad \\vec{a} \\quad und \\quad \\vec{b} } \\]" + "\\[" + buffer.vListeZurueckgebenABC(1).druckeDaten("a") + ", \\quad " + buffer.vListeZurueckgebenABC(2).druckeDaten("b") + "\\]";
            webview();
            uhrAmLaufen = true;
            stoppUhrStarten();
        } else if (v.getId() == R.id.UVlaenge) {
            zeigeAnmerkung("Es wurde gerechnet !", anmerkungZeit);
            laenge();
            aufgaben = "\\[ \\mathbf{Rechnen \\quad Sie \\quad die \\quad Länge \\\\ des \\quad Vektors \\quad \\vec{a}} \\]" + "\\[" + buffer.vListeZurueckgebenABC(1).druckeDaten("a") + "\\]";
            webview();
            uhrAmLaufen = true;
            stoppUhrStarten();
        } else if (v.getId() == R.id.UVlinearabhaengig) {
            zeigeAnmerkung("Es wurde überprüft !", anmerkungZeit);
            if (dimeinsion == 3) {
                linearabhaengig();
                aufgaben = "\\[ \\mathbf{Überprüfen \\quad Sie, \\quad ob \\quad die \\quad Vektoren \\\\ \\vec{a}, \\quad \\vec{b} \\quad und \\quad \\vec{c} \\quad linear abhängig \\quad sind} \\]" + "\\[" + buffer.vListeZurueckgebenABC(1).druckeDaten("a") + ", \\quad" + buffer.vListeZurueckgebenABC(2).druckeDaten("b") + "\\quad und \\quad" + buffer.vListeZurueckgebenABC(3).druckeDaten("c") + "\\]";

            } else {
                kollinear();
                aufgaben = "\\[ \\mathbf{Überprüfen \\quad Sie, \\quad ob \\quad die \\quad Vektoren \\\\ \\vec{a} \\quad und \\quad \\vec{b} \\quad kollinear \\quad sind} \\]" + "\\[" + buffer.vListeZurueckgebenABC(1).druckeDaten("a") + ", \\quad" + buffer.vListeZurueckgebenABC(2).druckeDaten("b") + "\\]";
            }
            webview();
            uhrAmLaufen = true;
            stoppUhrStarten();
        } else if (v.getId() == R.id.UVskalarprodukt) {
            zeigeAnmerkung("Es wurde gerechnet !", anmerkungZeit);
            skalarprodukt();
            aufgaben = "\\[ \\mathbf{ Berechnen \\quad Sie \\quad das \\quad Skalarprodukt \\quad der \\\\ Vektoren \\quad \\vec{a} \\quad und \\quad \\vec{b} }\\]" + "\\[" + buffer.vListeZurueckgebenABC(1).druckeDaten("a") + ", \\quad" + buffer.vListeZurueckgebenABC(2).druckeDaten("b") + "\\]";
            webview();
            uhrAmLaufen = true;
            stoppUhrStarten();
        } else if (v.getId() == R.id.UVzeigen) {
            uhrAmLaufen = false;
            zeigen();
        }
        buttonsDiaktivieren();
    }

    public void vektorenInfo() {
        buffer.infoListeLeeren();
        String s = "\\mathbf{  Hilfe \\quad Vektoren } \\\\ \\quad \\\\ \\quad \\\\";
        s = s + " {\\color{Red} {Allgemeines}} \\\\ \\quad \\\\ ";
        s = s + "{\\color{Red} {1)} } \\quad Es \\quad können \\quad Operationen \\quad mit \\quad zwei- \\quad oder \\\\ dreidimensionalen \\quad Vektoren \\quad durchgeführt \\quad werden.\\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {2)} } \\quad Operationen \\quad mit \\quad mehreren \\quad Vektoren \\\\ sind \\quad nur \\quad möglich \\quad wenn \\\\ die \\quad Vektoren \\quad dieselbe \\quad Dimension\\quad  besitzen.\\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {3)} } \\quad Berechnete \\quad Vektoren \\quad werden \\\\ automatisch \\quad unter \\quad \\vec{c} \\quad gespeichert.\\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {4)} } \\quad Bei \\quad Betätigen \\quad des \\\\ Zurück-Buttons \\quad werden \\quad alle \\\\ Vektoren \\quad und \\quad \\lambda \\quad gelöscht.\\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {5)} } \\quad Die \\quad Uhr \\quad mit \\quad der \\\\ Bearbeitungszeit \\quad wird \\quad automatisch \\quad gestartet, \\quad sobald \\\\ die \\quad Aufgabe \\quad gestellt \\quad wird. \\quad Während \\quad einer \\\\ " +
                "laufenden \\quad  Bearbeitung \\quad kann \\quad die \\quad Bearbeitungszeit \\\\ mit \\quad den \\quad Buttons \\quad Stoppen \\quad und \\\\ Starten \\quad unterbrochen \\quad bzw. \\quad fortgesetzt \\quad werden.\\\\ \\quad \\\\";
        s = s + "{\\color{Red} {Rechenoperationen}} \\\\ \\quad \\\\ ";
        s = s + "{\\color{Red} {6)} } \\quad Vektorenaddition \\\\ \\vec{a}+\\vec{b}=\\left(\\begin{array}{c} x_{a} \\\\ y_{a} \\end{array}\\right) + \\left(\\begin{array}{c} x_{b} \\\\ y_{b} \\end{array}\\right)=\\left(\\begin{array}{c} x_{a}+x_{b} \\\\ y_{a}+y_{b} \\end{array}\\right) \\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {7)} } \\quad Vektorensubtraktion \\\\ \\vec{a}-\\vec{b}=\\left(\\begin{array}{c} x_{a} \\\\ y_{a} \\end{array}\\right) - \\left(\\begin{array}{c} x_{b} \\\\ y_{b} \\end{array}\\right)=\\left(\\begin{array}{c} x_{a}-x_{b} \\\\ y_{a}-y_{b} \\end{array}\\right) \\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {8)} } \\quad Skalarprodukt \\\\ \\vec{a}\\cdot \\vec{b}=\\left(\\begin{array}{c} x_{a} \\\\ y_{a} \\end{array}\\right) \\cdot \\left(\\begin{array}{c} x_{b} \\\\ y_{b} \\end{array}\\right)=\\left(\\begin{array}{c} x_{a} \\cdot x_{b} \\\\ y_{a} \\cdot y_{b} \\end{array}\\right) \\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {9)} } \\quad Kreuzprodukt \\\\ \\vec{a} \\chi  \\vec{b}=\\left(\\begin{array}{c} x_{a} \\\\ y_{a} \\\\z_{a} \\end{array}\\right) \\chi  \\left(\\begin{array}{c} x_{b} \\\\ y_{b} \\\\ z_{b} \\end{array}\\right)=\\left(\\begin{array}{c} y_{a} \\cdot z_{b} - z_{a} \\cdot y_{b}  \\\\ z_{a} \\cdot x_{b} - x_{a} \\cdot z_{b} \\\\ x_{a} \\cdot y_{b} - y_{a} \\cdot x_{b} \\end{array}\\right) \\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {10)} } \\quad Länge \\quad eines \\quad Vektors\\\\ \\vec{a}= \\left(\\begin{array}{c} x \\\\ y \\\\ z \\end{array}\\right) \\\\ \\quad \\\\ \\left | \\vec{a}  \\right |  = \\sqrt{x^2 +y^2+z^2} \\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {11)} }\\quad Der \\quad Winkel \\quad zwischen \\quad zwei \\quad Vektoren \\\\  \\cos \\varphi =\\frac{\\vec{a}\\cdot \\vec{b}}{\\left | \\vec{a} \\right |\\cdot \\left | \\vec{b} \\right |} \\rightarrow \\varphi = \\arccos \\left ( \\frac{\\vec{a}\\cdot \\vec{b}}{\\left | \\vec{a} \\right |\\cdot \\left | \\vec{b} \\right |} \\right ) \\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {12)} } \\quad Multiplikation \\quad eines \\quad Vektors \\quad mit \\quad einer \\quad Zahl \\\\ \\lambda \\cdot \\vec{a}= \\lambda \\cdot \\left(\\begin{array}{c} x \\\\ y\\end{array}\\right) = \\left(\\begin{array}{c} \\lambda \\cdot x \\\\ \\lambda \\cdot y \\end{array}\\right) \\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {13)} } \\quad Lineare-Abhängigkeit \\quad drei \\quad Vektoren \\\\ \\vec{a} = \\left(\\begin{array}{c} x_{a} \\\\ y_{a} \\\\ z_{a}\\end{array}\\right) ,\\quad \\vec{b} = \\left(\\begin{array}{c} x_{b} \\\\ y_{b} \\\\ z_{b}\\end{array}\\right), \\quad \\vec{c} = \\left(\\begin{array}{c} x_{c} \\\\ y_{c} \\\\ z_{c}\\end{array}\\right)" +
                "\\\\ Es \\quad wird \\quad die \\quad Matrix \\quad untersucht, \\\\ deren \\quad Spalten \\quad aus \\quad den \\quad drei \\\\ Vektoren \\quad bestehen. \\\\ Ist \\quad die \\quad Determinante \\quad dieser \\\\ Matrix \\quad gleich \\quad Null, \\quad dann \\quad sind \\\\ die \\quad Vektoren \\quad linear \\quad abhängig." +
                " \\\\ |D| = \\begin{vmatrix} {\\color{Red} { \\vec{a} } }    &  {\\color{Red} { \\vec{b} } }  &  {\\color{Red} { \\vec{c} } } \\\\ x_{a} & x_{b} & x_{c} \\\\ y_{a} & y_{b} & y_{c} \\\\ z_{a} & z_{b} & z_{c} \\end{vmatrix} = 0 \\\\  \\quad \\\\ \\quad \\\\";
        buffer.infoListeAdd("\\[" + s + "\\]");
        time = chrono.getBase() - SystemClock.elapsedRealtime();
        chrono.stop();
        Intent i = new Intent(getApplicationContext(), Informationen.class);
        startActivity(i);
    }

    public void zurueck() {
        buffer.schrittListeLeeren();
        buffer.infoListeLeeren();
        buffer.vListeLeeren();
        Intent i = new Intent(getApplicationContext(), UebungenMenue.class);
        startActivity(i);
    }


    public void skalarprodukt() {
        uv.vektorenErstellen(2, dimeinsion);
        uv.vSkalarprodukt();
    }

    public void linearabhaengig() {
        uv.vektorenErstellen(3, dimeinsion);
        uv.vLinearabhaengig();
    }

    public void kollinear() {
        uv.vektorenErstellen(2, dimeinsion);
        uv.kollinear();
    }

    public void laenge() {
        uv.vektorenErstellen(2, dimeinsion);
        uv.vLaenge();
    }

    public void winkel() {
        uv.vektorenErstellen(2, dimeinsion);
        uv.vWinkel();
    }

    public void senkrecht() {
        uv.vektorenErstellen(2, dimeinsion);
        uv.vSenkrecht();
    }

    public double mitZahlmultiplizieren() {
        double z;
        uv.vektorenErstellen(2, dimeinsion);
        z = uv.vMitZahlMultiplizieren();
        return z;
    }

    public void kreuzprodukt() {
        uv.vektorenErstellen(2, 3);
        uv.vKreuzprodukt();
    }

    public void subtrahieren() {
        uv.vektorenErstellen(2, dimeinsion);
        uv.vSubtrahieren();
    }

    public void addieren() {
        uv.vektorenErstellen(2, dimeinsion);
        uv.vAddieren();
    }

    public void zeigen() {
        if (buffer.getSchritteListe().isEmpty()) {
            showInputMessage("Es gibt keine Lösung !");
        } else {
            time = chrono.getBase() - SystemClock.elapsedRealtime();
            chrono.stop();
            Intent i = new Intent(getApplicationContext(), Schritte.class);
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


    public void dimensionAuswahl() {
        text = (TextView) findViewById(R.id.UVtext);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setProgress(0);
        seekBar.setMax(1);

        text.setText((seekBar.getProgress() + 2) + " dimensionale Vektoren");


        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int p;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        p = progress;
                        text.setText((seekBar.getProgress() + 2) + " dimensionale Vektoren");
                        zeigeAnmerkung("Neue Dimensional", 500);
                        dimeinsion = p + 2;
                        buttonsDiaktivieren();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        text.setText((seekBar.getProgress() + 2) + " dimensionale Vektoren");
                        dimeinsion = p + 2;
                    }
                }
        );

    }

    public void buttonsDiaktivieren() {
        kreuzprodukt = (Button) findViewById(R.id.UVkreuzprodukt);
        loesung = (Button) findViewById(R.id.UVzeigen);
        if (dimeinsion != 2) {
            kreuzprodukt.setEnabled(true);
        } else {
            kreuzprodukt.setEnabled(false);
        }
        if (buffer.schrittListeZurueckgeben().isEmpty()) {
            loesung.setEnabled(false);
        } else {
            loesung.setEnabled(true);
        }
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

    public void buttonsSymbole() {

        TextView AplusB = (TextView) findViewById(R.id.UVaddieren);
        AplusB.setText(Html.fromHtml("&rarr;\t\t&rarr;<br>a + b"));

        TextView AminusB = (TextView) findViewById(R.id.UVsubtrahrieren);
        AminusB.setText(Html.fromHtml("&rarr;\t\t&rarr;<br>a - b"));

        TextView AkreuzB = (TextView) findViewById(R.id.UVkreuzprodukt);
        AkreuzB.setText(Html.fromHtml("&rarr;\t\t&rarr;<br>a X b"));

        TextView Alaenge = (TextView) findViewById(R.id.UVlaenge);
        Alaenge.setText(Html.fromHtml("\t&rarr;<br>| a |"));

        TextView AsenkrechtB = (TextView) findViewById(R.id.UVsenkrecht);
        AsenkrechtB.setText(Html.fromHtml("&rarr;\t\t&rarr;<br> a ⊥ b"));

        TextView AwinkelB = (TextView) findViewById(R.id.UVwinkel);
        AwinkelB.setText(Html.fromHtml("&nbsp;&nbsp; &rarr;\t&rarr;<br>&ang;(a , b)"));

        TextView LmalA = (TextView) findViewById(R.id.UVmitZahlmulti);
        LmalA.setText(Html.fromHtml("a &#183; &lambda;"));
        LmalA.setText(Html.fromHtml("&rarr;<br>\t\ta &#183; &lambda;"));

        TextView AmalB = (TextView) findViewById(R.id.UVskalarprodukt);
        AmalB.setText(Html.fromHtml("&rarr;\t\t&rarr;<br>a &#183; b"));

        TextView linearabhaengig = (TextView) findViewById(R.id.UVlinearabhaengig);
        linearabhaengig.setText(Html.fromHtml("LIN. ABH."));
    }
}
