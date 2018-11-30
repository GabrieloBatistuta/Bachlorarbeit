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

import com.itextpdf.text.Document;

import Datenhaltung.Buffer;
import Logik.Matrix;
import Logik.UMatrizen;

public class UebungenMatrizen extends AppCompatActivity {

    private Button starten;
    private Button stoppen;
    Chronometer chrono;
    long time = 0;

    private TextView zeilenText;
    private TextView spaltenText;
    private SeekBar zeilenSeekBar;
    private SeekBar spaltenSeekBar;
    private Buffer buffer;
    private int zeilen = 2;
    private int spalten = 2;
    private UMatrizen um;
    private Button inverseButton;
    private WebView w;
    private boolean uhrAmLaufen = false;
    private Button loesung;

    String aufgaben = "\\[ \\mathbf{Wählen \\quad Sie \\quad eine \\quad Aufgabe \\quad aus} \\]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebungen_matrizen);
        dimensionAuswahl();
        buffer = buffer.getInstance();
        um = new UMatrizen();
        buttonsSymbole();
        buttonDiaktivieren();
        starten = (Button) findViewById(R.id.UMstopuhrstarten);
        stoppen = (Button) findViewById(R.id.UMstopuhrstoppen);
        chrono = (Chronometer) findViewById(R.id.stoppuhrMatrizen);
        webview();
    }

    public void webview() {
        w = (WebView) findViewById(R.id.webViewM);
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

    public void umVerwaltung(View v) {

        if (v.getId() == R.id.UMaddieren) {
            addieren();
        } else if (v.getId() == R.id.UMinfo) {
            matrizenInfo();
        } else if (v.getId() == R.id.UMinverse) {
            inverse();
        } else if (v.getId() == R.id.UMmitzahlmultiplizieren) {
            mitZahlMultiplizieren();
        } else if (v.getId() == R.id.UMmultiplikation) {
            multiplizieren();
        } else if (v.getId() == R.id.UMdeterminante) {
            determinate();
        } else if (v.getId() == R.id.UMsubtrahieren) {
            subtrahieren();
        } else if (v.getId() == R.id.UMsymmetrisch) {
            symmetrisch();
        } else if (v.getId() == R.id.UMtransponieren) {
            transponieren();
        } else if (v.getId() == R.id.UMzeigen) {
            zeigen();
        } else if (v.getId() == R.id.UMzurueck) {
            zurueck();
        }
        buttonDiaktivieren();
    }

    public void matrizenInfo() {
        buffer.infoListeLeeren();
        uhrAmLaufen = false;
        time = chrono.getBase() - SystemClock.elapsedRealtime();
        chrono.stop();
        String s = "\\mathbf{  Hilfe \\quad Matrizen } \\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {Allgemeines} } \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {1)} } \\quad Es \\quad Können \\quad Operationen \\quad mit \\quad mit \\\\ Matrizen \\quad bis \\quad zur \\quad 6x6 \\quad Dimension \\\\ durchgeführt \\quad werden. \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {2)} } \\quad Operationen \\quad mit \\quad mehreren  \\quad Matrizen \\\\ sind \\quad nur \\quad möglich \\quad wenn \\quad die \\\\ Matrizen \\quad die \\quad richtige \\quad Dimensionen " +
                "\\quad besitzen. \\\\ Für \\quad Addition \\quad und \\quad Subtraktion \\quad müssen \\\\ die \\quad Dimensionen \\quad identisch \\quad sein." +
                "\\\\ Für \\quad die \\quad Multiplikation \\quad zweier \\\\ Matrizen \\quad müssen \\quad Spaltenzahl \\quad der \\quad ersten \\\\ Matrix \\quad und \\quad Zeilenzahl \\quad der \\quad zweiten " +
                "\\\\ Matrix \\quad gleich \\quad sein.\\\\ \\quad \\\\";
        s = s + "{\\color{Red} {3)} } \\quad Die \\quad Berechnung \\quad von \\quad Determinanten \\\\ und \\quad der \\quad Inversen \\quad Matrix, \\quad sowie \\\\ die \\quad Überprüfung \\quad auf \\quad Symmetrie \\quad ist" +
                " \\\\ nur \\quad für \\quad quadratische \\quad Matrizen \\quad zulässig. \\\\ Aus \\quad Darstellungsgründen \\quad ist \\quad die \\quad Berechnung \\\\ von \\quad Determinanten \\quad und \\quad Inverser \\quad Matrix " +
                "\\\\ auf \\quad die \\quad Dimensionen \\quad 2x2 \\quad und \\quad 3x3 \\quad beschränkt.\\\\ \\quad \\\\";
        s = s + "{\\color{Red} {4)} } \\quad Berechnete \\quad Matrizen \\quad werden \\quad automatisch \\\\ unter \\quad C \\quad gespeichert. \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {5)} } \\quad Bei \\quad Betätigen \\quad des \\quad Zurück-Buttons \\\\ werden \\quad alle \\quad Matrizen \\quad und \\quad \\lambda \\quad gelöscht. \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {6)} } \\quad Die \\quad Uhr \\quad mit \\quad der \\\\ Bearbeitungszeit \\quad wird \\quad automatisch \\quad gestartet, \\quad sobald \\\\ die \\quad Aufgabe \\quad gestellt \\quad wird. \\quad Während \\quad einer \\\\ " +
                "laufenden \\quad  Bearbeitung \\quad kann \\quad die \\quad Bearbeitungszeit \\\\ mit \\quad den \\quad Buttons \\quad Stoppen \\quad und \\\\ Starten \\quad unterbrochen \\quad bzw. \\quad fortgesetzt \\quad werden. \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {Rechenoperationen} }\\\\ \\quad \\\\";
        s = s + "{\\color{Red} {7)} } \\quad Addition \\quad von \\quad Matrizen \\\\ A =  \\begin{bmatrix} a_{11} & a_{12}  \\\\ a_{21} & a_{22} \\end{bmatrix}, \\quad B = \\begin{bmatrix} b_{11} & b_{12}  \\\\ b_{21} & b_{22} \\end{bmatrix}" +
                "\\\\ A+B =\\begin{bmatrix} a_{11} + b_{11} & a_{12}+b_{12}  \\\\ a_{21}+b_{21} & a_{22}+b_{22} \\end{bmatrix} \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {8)} } \\quad Subtraktion \\quad von \\quad Matrizen \\\\ A =  \\begin{bmatrix} a_{11} & a_{12}  \\\\ a_{21} & a_{22} \\end{bmatrix}, \\quad B = \\begin{bmatrix} b_{11} & b_{12}  \\\\ b_{21} & b_{22} \\end{bmatrix}" +
                "\\\\ A-B =\\begin{bmatrix} a_{11} - b_{11} & a_{12}-b_{12}  \\\\ a_{21}-b_{21} & a_{22}-b_{22} \\end{bmatrix} \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {9)} } \\quad Mutiplikation \\quad von \\quad Matrizen \\\\ A \\cdot B {\\color{Red} {\\neq} } B \\cdot A \\\\  \\\\ A =  \\begin{bmatrix} a_{11} & a_{12}  \\\\ a_{21} & a_{22} \\end{bmatrix} \\in \\mathbb{R}^{2x2} , \\quad B = \\begin{bmatrix} b_{11}  \\\\ b_{21} \\end{bmatrix} \\in \\mathbb{R}^{2x1}" +
                "\\\\  {\\color{Red} {\\Rightarrow} } \\\\ A \\cdot B \\in \\mathbb{R}^{2x1} \\\\ \\quad \\\\ A \\cdot B = \\begin{bmatrix} a_{11} \\cdot b_{11}+ a_{12} \\cdot b_{21} \\\\ a_{21} \\cdot b_{11}+ a_{22} \\cdot b_{21} \\end{bmatrix} \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {10)} } \\quad Inverse \\quad Matrix \\\\ A \\cdot A^{-1} = E \\\\ sehe \\quad die \\quad Lösungsweg.\\\\ \\quad \\\\";
        s = s + "{\\color{Red} {11)} } \\quad Transponierte \\quad Matrix \\\\ Jede \\quad beliebige \\quad Matrix \\quad lässt \\\\ sich \\quad durch \\quad Vertauschen \\quad der \\quad Zeilen \\\\ und \\quad Spalten \\quad der \\quad Matrix \\quad  A \\quad transponieren.\\\\ \\quad \\\\" +
                " A =  \\begin{bmatrix} a_{11} & a_{12} & a_{13} \\\\ a_{21} & a_{22} & a_{23} \\\\a_{31} & a_{32} & a_{33} \\end{bmatrix}, A^T =  \\begin{bmatrix} a_{11} & a_{21} & a_{31} \\\\ a_{12} & a_{22} & a_{32} \\\\a_{13} & a_{23} & a_{33} \\end{bmatrix} \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {12)} } \\quad Multiplikation \\quad einer \\quad Matrix \\quad mit \\quad einer \\quad Zahl \\\\ \\lambda \\cdot A = \\lambda \\cdot \\begin{bmatrix} a_{11} & a_{12}  \\\\ a_{21} & a_{22} \\end{bmatrix} = \\begin{bmatrix} \\lambda \\cdot  a_{11} & \\lambda \\cdot  a_{12}  \\\\ \\lambda \\cdot  a_{21} & \\lambda \\cdot  a_{22} \\end{bmatrix} \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {13)} } \\quad Symmetrische  \\quad Matrix \\\\Eine \\quad Matrix \\quad ist \\quad symmetrisch, \\\\ wenn \\quad sie \\quad mit \\quad ihrer \\\\ transponierten \\quad Matrix \\quad übereinstimmt \\\\ A = A^{T} = \\begin{bmatrix} a & b & c \\\\ b & d & e \\\\ c & e & f \\end{bmatrix} \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {14)} } \\quad Determinante \\quad einer \\quad Matrix \\\\ A = \\begin{bmatrix} a_{11} & a_{12}  & a_{13} \\\\ a_{21} & a_{22}  & a_{23} \\\\ a_{31} & a_{32} & a_{33} \\end{bmatrix} \\\\ " +
                "det(A) =\\left \\{ a_{11} \\cdot a_{22} \\cdot a_{33} + a_{12} \\cdot a_{23} \\cdot a_{31} + a_{13} \\cdot a_{21} \\cdot a_{32}\\right \\} -" +
                "\\\\ \\left \\{ a_{13} \\cdot a_{22} \\cdot a_{31} + a_{11} \\cdot a_{23} \\cdot a_{32} + a_{12} \\cdot a_{21} \\cdot a_{33}\\right \\}";

        buffer.infoListeAdd("\\[" + s + "\\]");
        Intent i = new Intent(getApplicationContext(), Informationen.class);
        startActivity(i);
    }

    public void stoppuhrVerwalten(View v) {

        if (v.getId() == R.id.UMstopuhrstarten) {
            if (!uhrAmLaufen) {
                chrono.setBase(SystemClock.elapsedRealtime() + time);
                chrono.start();
                uhrAmLaufen = true;
            }
        } else if (v.getId() == R.id.UMstopuhrstoppen) {
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

    public void zeigen() {
        if (buffer.getSchritteListe().isEmpty()) {
            showInputMessage("Es gibt keine Lösung !");
        } else {
            uhrAmLaufen = false;
            time = chrono.getBase() - SystemClock.elapsedRealtime();
            chrono.stop();
            Intent i = new Intent(getApplicationContext(), Schritte.class);
            startActivity(i);
        }
    }

    public void zurueck() {
        buffer.schrittListeLeeren();
        buffer.infoListeLeeren();
        buffer.mListeLeeren();
        Intent i = new Intent(getApplicationContext(), UebungenMenue.class);
        startActivity(i);
    }

    public void addieren() {
        um.matrizenErstellen(zeilen, spalten, 2);
        um.mAddieren();
        zeigeAnmerkung("Wird addiert", 1000);
        aufgaben = "\\[\\mathbf{Addieren\\quad Sie \\quad die \\quad Matrizen \\quad A \\quad und \\quad B \\quad  } \\]" + "\\[" + buffer.mListeZurueckgebenABC(1).druckeDaten("A") + "," + buffer.mListeZurueckgebenABC(2).druckeDaten("B") + "\\]";
        webview();
        uhrAmLaufen = true;
        stoppUhrStarten();
    }

    public void determinate() {
        um.matrizenErstellen(zeilen, spalten, 2);
        um.mDeterminante();
        zeigeAnmerkung("Wird berechnet", 1000);
        aufgaben = "\\[\\mathbf{Berechnen\\quad Sie \\quad die \\quad Determinante  \\\\ der \\quad Martix \\quad A } \\]" + "\\[" + buffer.mListeZurueckgebenABC(1).druckeDaten("A") + "\\]";
        webview();
        uhrAmLaufen = true;
        stoppUhrStarten();
    }

    public void subtrahieren() {
        um.matrizenErstellen(zeilen, spalten, 2);
        um.mSubtrahieren();
        zeigeAnmerkung("Wird subtrahiert", 1000);
        aufgaben = "\\[\\mathbf{Subtrahieren\\quad Sie \\quad die \\quad Matrix \\quad B \\quad von \\quad der \\quad Matrix \\quad A } \\]" + "\\[" + buffer.mListeZurueckgebenABC(1).druckeDaten("A") + "," + buffer.mListeZurueckgebenABC(2).druckeDaten("B") + "\\]";
        webview();
        uhrAmLaufen = true;
        stoppUhrStarten();
    }

    public void multiplizieren() {
        um.matrizenErstellen(zeilen, spalten, 2);
        Matrix m2 = buffer.mListeZurueckgebenABC(2);
        m2 = um.mTransponierenUndZurueckgeben(m2);
        buffer.schrittListeLeeren();
        buffer.mListeAddABC(m2, 2);
        um.mMultiplizieren();
        zeigeAnmerkung("wird multipliziert", 1000);
        aufgaben = "\\[\\mathbf{Multiplizieren\\quad Sie \\quad die \\quad Matrix \\quad A \\quad mit \\quad der \\quad Matrix \\quad B   } \\]" + "\\[" + buffer.mListeZurueckgebenABC(1).druckeDaten("A") + "," + buffer.mListeZurueckgebenABC(2).druckeDaten("B") + "\\]";
        webview();
        uhrAmLaufen = true;
        stoppUhrStarten();
    }

    public void transponieren() {
        um.matrizenErstellen(zeilen, spalten, 1);
        um.mTransponieren();
        zeigeAnmerkung("wird transponiert", 1000);
        aufgaben = "\\[\\mathbf{Transponieren\\quad Sie \\quad die \\quad Matrix \\quad A } \\]" + "\\[" + buffer.mListeZurueckgebenABC(1).druckeDaten("A") + "\\]";
        webview();
        uhrAmLaufen = true;
        stoppUhrStarten();
    }

    public void symmetrisch() {
        um.matrizenErstellen(zeilen, spalten, 1);
        um.mSymmetrisch();
        zeigeAnmerkung("wird überprüft", 1000);
        aufgaben = "\\[\\mathbf{Überprüfen\\quad Sie, \\quad ob \\quad die \\quad Matrix \\\\ A \\quad symmetrisch \\quad ist } \\]" + "\\[" + buffer.mListeZurueckgebenABC(1).druckeDaten("A") + "\\]";
        webview();
        uhrAmLaufen = true;
        stoppUhrStarten();
    }

    public void mitZahlMultiplizieren() {
        um.matrizenErstellen(zeilen, spalten, 1);
        um.mMitzahlmultiplizieren();
        zeigeAnmerkung("wird multipliziert", 1000);
        aufgaben = "\\[\\mathbf{Multiplizieren\\quad Sie \\quad die \\quad Matrix \\\\ A \\quad mit \\quad \\lambda } \\]" + "\\[" + buffer.mListeZurueckgebenABC(1).druckeDaten("A") + ",\\lambda=" + (int) buffer.getSkalarListe() + "\\]";
        webview();
        uhrAmLaufen = true;
        stoppUhrStarten();
    }

    public void inverse() {
        um.matrizenErstellen(zeilen, spalten, 1);
        if (zeilen == 3)
            um.mInverse();
        else if (zeilen == 2)
            um.mInverseA2();
        zeigeAnmerkung("wird gerechnet", 1000);
        aufgaben = "\\[\\mathbf{Berechnen\\quad Sie \\quad die \\quad Inverse \\\\ der \\quad Matrix \\quad A} \\]" + "\\[" + buffer.mListeZurueckgebenABC(1).druckeDaten("A") + "\\]";
        webview();
        uhrAmLaufen = true;
        stoppUhrStarten();
    }

    public void dimensionAuswahl() {
        zeilenText = (TextView) findViewById(R.id.UMtextZeile);
        spaltenText = (TextView) findViewById(R.id.UMspalteText);
        zeilenSeekBar = (SeekBar) findViewById(R.id.UMzeileSeekBar);
        spaltenSeekBar = (SeekBar) findViewById(R.id.UMspaltenSeekBar);
        zeilenSeekBar.setProgress(0);
        zeilenSeekBar.setMax(4);
        spaltenSeekBar.setProgress(0);
        spaltenSeekBar.setMax(4);
        zeilenText.setText((zeilenSeekBar.getProgress()) + 2 + "");
        spaltenText.setText((spaltenSeekBar.getProgress()) + 2 + "");
        zeilenSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {


                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        zeilenText.setText((seekBar.getProgress()) + 2 + "");
                        zeilen = progress + 2;
                        zeigeAnmerkung("Zeilenänderung", 500);
                        buttonDiaktivieren();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        zeilenText.setText((seekBar.getProgress()) + 2 + "");

                    }
                }
        );
        spaltenSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        spaltenText.setText((seekBar.getProgress()) + 2 + "");
                        spalten = progress + 2;
                        zeigeAnmerkung("Spaltenänderung", 500);
                        buttonDiaktivieren();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        spaltenText.setText((seekBar.getProgress()) + 2 + "");

                    }
                }
        );
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

    public void buttonDiaktivieren() {
        inverseButton = (Button) findViewById(R.id.UMinverse);
        Button det = (Button) findViewById(R.id.UMdeterminante);
        Button symmetrisch = (Button) findViewById(R.id.UMsymmetrisch);
        loesung = (Button) findViewById(R.id.UMzeigen);
        if ((zeilen == spalten && zeilen == 2) || (zeilen == spalten && zeilen == 3)) {
            inverseButton.setEnabled(true);
            det.setEnabled(true);
        } else {
            inverseButton.setEnabled(false);
            det.setEnabled(false);
        }

        if (zeilen == spalten) {
            symmetrisch.setEnabled(true);
        } else {
            symmetrisch.setEnabled(false);
        }

        if (buffer.schrittListeZurueckgeben().isEmpty()) {
            loesung.setEnabled(false);
        } else {
            loesung.setEnabled(true);
        }

    }

    public void buttonsSymbole() {

        TextView AplusB = (TextView) findViewById(R.id.UMaddieren);
        AplusB.setText(Html.fromHtml("a + b"));

        TextView AminusB = (TextView) findViewById(R.id.UMsubtrahieren);
        AminusB.setText(Html.fromHtml("a - b"));


        TextView AmalB = (TextView) findViewById(R.id.UMmultiplikation);
        AmalB.setText(Html.fromHtml("a &#183; b"));


        TextView Ainverse = (TextView) findViewById(R.id.UMinverse);
        Ainverse.setText(Html.fromHtml("<small><small>\t\t-1</small></small><br>A"));


        TextView Atransponieren = (TextView) findViewById(R.id.UMtransponieren);
        Atransponieren.setText(Html.fromHtml("<small><small><small>\tT</small></small></small><br>A"));


        TextView LmalA = (TextView) findViewById(R.id.UMmitzahlmultiplizieren);
        LmalA.setText(Html.fromHtml("A &#183; &lambda;"));


        TextView Asymmetrische = (TextView) findViewById(R.id.UMsymmetrisch);
        Asymmetrische.setText(Html.fromHtml("<small><small><small>T</small></small></small><br>\tA  = A"));

        TextView Adeterminante = (TextView) findViewById(R.id.UMdeterminante);
        Adeterminante.setText(Html.fromHtml("det(A)"));
    }
}
