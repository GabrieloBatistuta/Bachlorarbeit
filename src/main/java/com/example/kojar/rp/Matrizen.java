package com.example.kojar.rp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Datenhaltung.Buffer;
import Logik.Matrix;
import Logik.Verwaltung;

public class Matrizen extends AppCompatActivity {

    private Verwaltung vr;
    private Buffer buffer;
    private Matrix matrix;
    private EditText lambda;

    private String AexistiertNicht = "Die Matrix A existiert noch nicht !";
    private String BexistiertNicht = "Die Matrix B existiert noch nicht !";
    private String CexistiertNicht = "Die Matrix C existiert noch nicht !";

    private String AistNull = "Die Matrix A ist gleich Null !";
    private String BistNull = "Die Matrix B ist gleich Null !";
    private String CistNull = "Die Matrix C ist gleich Null !";

    private String unterschiedlicheD = "Die Matrizen haben unterschiedliche Dimensionen !";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matrix);
        vr = new Verwaltung();
        buffer = buffer.getInstance();
        buttonsSymbole();
        gibtEsMatrizen();
    }

    public void MatrizenInfo() {
        String s2 = "";
        String s = "\\mathbf{  Hilfe \\quad Matrizen } \\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {Allgemeines} } \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {1)} } \\quad Es \\quad Können \\quad Operationen \\quad mit \\\\ Matrizen \\quad bis \\quad zur \\quad 6x6 \\quad Dimension \\\\ durchgeführt \\quad werden. \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {2)} } \\quad Operationen \\quad mit \\quad mehreren  \\quad Matrizen \\\\ sind \\quad nur \\quad möglich \\quad wenn \\quad die \\\\ Matrizen \\quad die \\quad richtige \\quad Dimensionen " +
                "\\quad besitzen. \\\\ Für \\quad Addition \\quad und \\quad Subtraktion \\quad müssen \\\\ die \\quad Dimensionen \\quad identisch \\quad sein." +
                "\\\\ Für \\quad die \\quad Multiplikation \\quad zweier \\\\ Matrizen \\quad müssen \\quad Spaltenzahl \\quad der \\quad ersten \\\\ Matrix \\quad und \\quad Zeilenzahl \\quad der \\quad zweiten " +
                "\\\\ Matrix \\quad gleich \\quad sein.\\\\ \\quad \\\\";
        s = s + "{\\color{Red} {3)} } \\quad Die \\quad Berechnung \\quad von \\quad Determinanten \\\\ und \\quad der \\quad Inversen \\quad Matrix, \\quad sowie \\\\ die \\quad Überprüfung \\quad auf \\quad Symmetrie \\quad ist" +
                " \\\\ nur \\quad für \\quad quadratische \\quad Matrizen \\quad zulässig. \\\\ Aus \\quad Darstellungsgründen \\quad ist \\quad die \\quad Berechnung \\\\ von \\quad Determinanten \\quad und \\quad Inverser \\quad Matrix " +
                "\\\\ auf \\quad die \\quad Dimensionen \\quad 2x2 \\quad und \\quad 3x3 \\quad beschränkt.\\\\ \\quad \\\\";
        s = s + "{\\color{Red} {4)} } \\quad Bevor \\quad eine \\quad Operation \\\\ durchgeführt \\quad werden \\quad kann, \\quad müssen \\quad die \\\\ dafür \\quad erforderlichen \\quad Matrizen \\quad über \\quad die \\\\ grünen \\quad Buttons \\quad eingeben \\quad werden.\\\\ \\quad \\\\";
        s = s + "{\\color{Red} {5)} } \\quad Bevor \\quad ein \\quad Matrix \\quad mit \\\\ einer \\quad Zahl \\quad (\\lambda) \\quad multipliziert \\quad werden \\\\ kann, \\quad muss \\quad dies \\quad Zahl \\quad durch \\\\ den \\quad entsprechenden \\quad grünen \\quad Button \\quad eingegeben \\quad werden.\\\\ \\quad \\\\";
        s = s + "{\\color{Red} {6)} } \\quad Berechnete \\quad Matrizen \\quad werden \\quad automatisch \\\\ unter \\quad C \\quad gespeichert. \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {7)} } \\quad Durch \\quad die \\quad Buttons \\quad A=:C, \\quad A=:B \\quad bzw. \\quad B=:C \\\\ werden \\quad die \\quad Matrizen \\quad A, \\quad B \\quad bzw. \\quad C \\quad durch \\\\ ersetzt.\\\\ \\quad \\\\";
        s = s + "{\\color{Red} {8)} } \\quad Alle \\quad eingegebenen \\quad und \\\\ gespeicherten \\quad Matrizen \\quad sowie \\quad \\lambda \\quad können \\quad durch \\\\ die \\quad Betätigung \\quad der \\quad entsprechenden \\quad grünen \\\\ Buttons \\quad angesehen \\quad und \\quad geändert \\quad werden.\\\\ \\quad \\\\";
        s = s + "{\\color{Red} {9)} } \\quad Bei \\quad Betätigen \\quad des \\quad Zurück-Buttons \\\\ werden \\quad alle \\quad Matrizen \\quad und \\quad \\lambda \\quad gelöscht. \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {Rechenoperationen} }\\\\ \\quad \\\\";
        s = s + "{\\color{Red} {10)} } \\quad Addition \\quad von \\quad Matrizen \\\\ A =  \\begin{bmatrix} a_{11} & a_{12}  \\\\ a_{21} & a_{22} \\end{bmatrix}, \\quad B = \\begin{bmatrix} b_{11} & b_{12}  \\\\ b_{21} & b_{22} \\end{bmatrix}" +
                "\\\\ A+B =\\begin{bmatrix} a_{11} + b_{11} & a_{12}+b_{12}  \\\\ a_{21}+b_{21} & a_{22}+b_{22} \\end{bmatrix} \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {11)} } \\quad Subtraktion \\quad von \\quad Matrizen \\\\ A =  \\begin{bmatrix} a_{11} & a_{12}  \\\\ a_{21} & a_{22} \\end{bmatrix}, \\quad B = \\begin{bmatrix} b_{11} & b_{12}  \\\\ b_{21} & b_{22} \\end{bmatrix}" +
                "\\\\ A-B =\\begin{bmatrix} a_{11} - b_{11} & a_{12}-b_{12}  \\\\ a_{21}-b_{21} & a_{22}-b_{22} \\end{bmatrix} \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {12)} } \\quad Mutiplikation \\quad von \\quad Matrizen \\\\ A \\cdot B {\\color{Red} {\\neq} } B \\cdot A \\\\  \\\\ A =  \\begin{bmatrix} a_{11} & a_{12}  \\\\ a_{21} & a_{22} \\end{bmatrix} \\in \\mathbb{R}^{2x2} , \\quad B = \\begin{bmatrix} b_{11}  \\\\ b_{21} \\end{bmatrix} \\in \\mathbb{R}^{2x1}" +
                "\\\\  {\\color{Red} {\\Rightarrow} } \\\\ A \\cdot B \\in \\mathbb{R}^{2x1} \\\\ \\quad \\\\ A \\cdot B = \\begin{bmatrix} a_{11} \\cdot b_{11}+ a_{12} \\cdot b_{21} \\\\ a_{21} \\cdot b_{11}+ a_{22} \\cdot b_{21} \\end{bmatrix} \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {13)} } \\quad Inverse \\quad Matrix \\\\ A \\cdot A^{-1} = E \\\\ sehe \\quad die \\quad Lösungsweg.\\\\ \\quad \\\\";
        s = s + "{\\color{Red} {14)} } \\quad Transponierte \\quad Matrix \\\\ Jede \\quad beliebige \\quad Matrix \\quad lässt \\\\ sich \\quad durch \\quad Vertauschen \\quad der \\quad Zeilen \\\\ und \\quad Spalten \\quad der \\quad Matrix \\quad  A \\quad transponieren.\\\\ \\quad \\\\" +
                " A =  \\begin{bmatrix} a_{11} & a_{12} & a_{13} \\\\ a_{21} & a_{22} & a_{23} \\\\a_{31} & a_{32} & a_{33} \\end{bmatrix}, A^T =  \\begin{bmatrix} a_{11} & a_{21} & a_{31} \\\\ a_{12} & a_{22} & a_{32} \\\\a_{13} & a_{23} & a_{33} \\end{bmatrix} \\\\ \\quad \\\\";
        s2 = s2 + "{\\color{Red} {15)} } \\quad Multiplikation \\quad einer \\quad Matrix \\quad mit \\quad einer \\quad Zahl \\\\ \\lambda \\cdot A = \\lambda \\cdot \\begin{bmatrix} a_{11} & a_{12}  \\\\ a_{21} & a_{22} \\end{bmatrix} = \\begin{bmatrix} \\lambda \\cdot  a_{11} & \\lambda \\cdot  a_{12}  \\\\ \\lambda \\cdot  a_{21} & \\lambda \\cdot  a_{22} \\end{bmatrix} \\\\ \\quad \\\\";
        s2 = s2 + "{\\color{Red} {16)} } \\quad Symmetrische  \\quad Matrix \\\\Eine \\quad Matrix \\quad ist \\quad symmetrisch, \\\\ wenn \\quad sie \\quad mit \\quad ihrer \\\\ transponierten \\quad Matrix \\quad übereinstimmt \\\\ A = A^{T} = \\begin{bmatrix} a & b & c \\\\ b & d & e \\\\ c & e & f \\end{bmatrix} \\\\ \\quad \\\\";
        s2 = s2 + "{\\color{Red} {17)} } \\quad Determinante \\quad einer \\quad Matrix \\\\ \\quad \\\\  A = \\begin{bmatrix} a_{11} & a_{12}  & a_{13} \\\\ a_{21} & a_{22}  & a_{23} \\\\ a_{31} & a_{32} & a_{33} \\end{bmatrix} \\\\ " +
                "det(A) =\\left \\{ a_{11} \\cdot a_{22} \\cdot a_{33} + a_{12} \\cdot a_{23} \\cdot a_{31} + a_{13} \\cdot a_{21} \\cdot a_{32}\\right \\} -" +
                "\\\\ \\left \\{ a_{13} \\cdot a_{22} \\cdot a_{31} + a_{11} \\cdot a_{23} \\cdot a_{32} + a_{12} \\cdot a_{21} \\cdot a_{33}\\right \\}";
        buffer.infoListeLeeren();
        buffer.infoListeAdd("\\[" + s + "\\]");
        buffer.infoListeAdd("\\[" + s2 + "\\]");
        Intent i = new Intent(getApplicationContext(), Informationen.class);
        startActivity(i);
    }

    public void matrixenVerwaltung(View v) {
        if (v.getId() == R.id.Mzurueck) {
            buffer.mListeLeeren();
            buffer.schrittListeLeeren();
            Intent i = new Intent(getApplicationContext(), Menue.class);
            startActivity(i);
        } else if (v.getId() == R.id.MerstellenA) {
            buffer.setNavigation(1);
            Intent i = new Intent(getApplicationContext(), Matrixerstellen.class);
            startActivity(i);
        } else if (v.getId() == R.id.MerstellenB) {
            buffer.setNavigation(2);
            Intent i = new Intent(getApplicationContext(), Matrixerstellen.class);
            startActivity(i);
        } else if (v.getId() == R.id.MerstellenC) {
            buffer.setNavigation(3);
            Intent i = new Intent(getApplicationContext(), Matrixerstellen.class);
            startActivity(i);
        } else if (v.getId() == R.id.MAplusB) {
            aPlusb();
        } else if (v.getId() == R.id.MAminusB) {
            aMinusB();
        } else if (v.getId() == R.id.MBminusA) {
            bMinusB();
        } else if (v.getId() == R.id.MphawEingeben) {
            skalarEingabe("");
        } else if (v.getId() == R.id.MAgleichB) {
            aGleichb();
        } else if (v.getId() == R.id.MAgleichC) {
            aGleichc();
        } else if (v.getId() == R.id.MBgleichC) {
            bGleichc();
        } else if (v.getId() == R.id.MAmalB) {
            aMalb();
        } else if (v.getId() == R.id.MBmalA) {
            bMala();
        } else if (v.getId() == R.id.MAinverse) {
            inverseA();
        } else if (v.getId() == R.id.MBinverse) {
            inverseB();
        } else if (v.getId() == R.id.MAsymmtrisch) {
            aSymmetrisch();
        } else if (v.getId() == R.id.MBsymmetrisch) {
            bSymmetrisch();
        } else if (v.getId() == R.id.MAtranspnieren) {
            aTransponieren();
        } else if (v.getId() == R.id.MBtransponieren) {
            bTransponieren();
        } else if (v.getId() == R.id.MAmalPhaw) {
            lambdaMalaA();
        } else if (v.getId() == R.id.MBmalPhaw) {
            lambdaMalaB();
        } else if (v.getId() == R.id.MdeterminanteA) {
            determinanteA();
        } else if (v.getId() == R.id.MdeterminanteB) {
            determinanteB();
        } else if (v.getId() == R.id.Minfo) {
            MatrizenInfo();
        }

        gibtEsMatrizen();
    }

    public void inverseA() {
        if (buffer.mListeIsEmptyABC(1)) {
            showInputMessage(AexistiertNicht);
        } else {
            if (istDieMatrizeQuadratisch(buffer.mListeZurueckgebenABC(1))) {
                if (buffer.mListeZurueckgebenABC(1).getSpalten() == 3) {
                    if (vr.determinanteA(buffer.mListeZurueckgebenABC(1), false) == 0) {
                        showInputMessage("Die Matrix A hat keine Inverse, weil die Determinante von A gleich null ist !");
                    } else {
                        buffer.mListeAddABC(vr.inverseA(buffer.mListeZurueckgebenABC(1)), 3);
                        schrittee();
                    }

                } else if (buffer.mListeZurueckgebenABC(1).getSpalten() == 2) {
                    if (vr.determinanteA(buffer.mListeZurueckgebenABC(1), false) == 0) {
                        showInputMessage("Die Matrix A hat keine Inverse, weil die Determinante von A gleich null ist !");
                    } else {
                        buffer.mListeAddABC(vr.zweiDinverseA(buffer.mListeZurueckgebenABC(1)), 3);
                        schrittee();
                    }
                } else {
                    showInputMessage("Die maximale Große ist 3x3");
                }
            } else {
                showInputMessage("Die Matrize A ist keine quadratische Matrize !!");
            }
        }
    }

    public void inverseB() {
        if (buffer.mListeIsEmptyABC(2)) {
            showInputMessage(BexistiertNicht);
        } else {
            if (istDieMatrizeQuadratisch(buffer.mListeZurueckgebenABC(2))) {
                if (buffer.mListeZurueckgebenABC(2).getSpalten() == 3) {
                    if (vr.determinanteB(buffer.mListeZurueckgebenABC(2)) == 0) {
                        showInputMessage("Die Matrix B hat keine Inverse, weil die Determinante von B gleich null ist !");
                    } else {
                        buffer.mListeAddABC(vr.inverseB(buffer.mListeZurueckgebenABC(2)), 3);
                        schrittee();
                    }
                } else if (buffer.mListeZurueckgebenABC(2).getSpalten() == 2 || buffer.mListeZurueckgebenABC(2).getSpalten() == 1) {
                    if (vr.determinanteA(buffer.mListeZurueckgebenABC(2), false) == 0) {
                        showInputMessage("Die Matrix B hat keine Inverse, weil die Determinante von B gleich null ist !");
                    } else {
                        buffer.mListeAddABC(vr.zweiDinverseB(buffer.mListeZurueckgebenABC(2)), 3);
                        schrittee();
                    }
                } else {
                    showInputMessage("Die maximale Große ist 3x3");
                }
            } else {
                showInputMessage("Die Matrize B ist keine quadratische Matrize !");
            }
        }
    }


    public void lambdaMalAA(Matrix mA, double d) {
        buffer.mListeAddABC(vr.lambdaMalA(mA, d), 3);
    }

    public void lambdaMalBB(Matrix mA, double d) {
        buffer.mListeAddABC(vr.lambdaMalB(mA, d), 3);
    }

    public void schrittee() {
        Intent i = new Intent(getApplicationContext(), Schritte.class);
        startActivity(i);
    }

    public void bTransponieren(Matrix mA) {
        buffer.mListeAddABC(vr.bTransponieren(mA), 3);
    }

    public void aTransponieren(Matrix mA) {
        buffer.mListeAddABC(vr.aTransponieren(mA), 3);
    }


    public void bSymetrisch(Matrix mA) {
        vr.istBsymetrisch(mA);
    }

    public void aSymetrisch(Matrix mA) {
        vr.istAsymetrisch(mA);
    }

    public Matrix AminusB(Matrix mA, Matrix mB) {
        matrix = vr.AminusB(mA, mB);
        buffer.mListeAddABC(matrix, 3);
        return matrix;
    }

    public Matrix BminusA(Matrix mA, Matrix mB) {
        matrix = vr.BminusA(mA, mB);
        buffer.mListeAddABC(matrix, 3);
        return matrix;
    }


    public Matrix AplusB(Matrix mA, Matrix mB) {
        Matrix ma = vr.AplusB(mA, mB);
        buffer.mListeAddABC(ma, 3);
        return ma;
    }


    public void showInputMessage(String message) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(Html.fromHtml(message));
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    public void skalarEingabe(String s) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(Html.fromHtml("Geben Sie Lambda ( &lambda; ) ein " + s + " !"));
        lambda = new EditText(this);
        lambda.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        lambda.setText("" + ZA(buffer.getSkalarListe()));
        lambda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lambda.setText("");
            }
        });
        dlgAlert.setView(lambda);
        dlgAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                try {
                    String s = lambda.getText().toString();
                    buffer.setSkalarListe(Double.parseDouble(s));
                } catch (Exception e) {
                    buffer.setSkalarListe(0);
                }
            }
        });
        dlgAlert.setNegativeButton("ABBRECHEN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog ad = dlgAlert.create();
        ad.show();
    }

    public boolean sindDieSpaltenUndZeilenGleich(Matrix mA, Matrix mB) {
        if (mA.getZeile() == mB.getZeile() && mA.getSpalten() == mB.getSpalten()) {
            return true;
        } else
            return false;
    }

    public boolean istDieMatrizeQuadratisch(Matrix mA) {
        if (mA.getSpalten() == mA.getZeile()) {
            return true;
        } else
            return false;
    }

    public boolean gibtsMatrize(int a) {
        return buffer.mListeIsEmptyABC(a);
    }

    public void aPlusb() {

        if (gibtsMatrize(1)) {
            showInputMessage(AexistiertNicht);
        } else if (gibtsMatrize(2)) {
            showInputMessage(BexistiertNicht);
        } else if (!sindDieSpaltenUndZeilenGleich(buffer.mListeZurueckgebenABC(1), buffer.mListeZurueckgebenABC(2))) {
            showInputMessage(unterschiedlicheD);
        } else {
            if (istUngleichNull(buffer.mListeZurueckgebenABC(1))) {
                if (istUngleichNull(buffer.mListeZurueckgebenABC(2))) {
                    AplusB(buffer.mListeZurueckgebenABC(1), buffer.mListeZurueckgebenABC(2));
                    schrittee();
                } else {
                    showInputMessage(BistNull);
                }
            } else {
                showInputMessage(AistNull);
            }
        }
    }


    public void aMinusB() {
        if (gibtsMatrize(1)) {
            showInputMessage(AexistiertNicht);
        } else if (gibtsMatrize(2)) {
            showInputMessage(BexistiertNicht);
        } else if (!sindDieSpaltenUndZeilenGleich(buffer.mListeZurueckgebenABC(1), buffer.mListeZurueckgebenABC(2))) {
            showInputMessage(unterschiedlicheD);
        } else {
            if (istUngleichNull(buffer.mListeZurueckgebenABC(1))) {
                if (istUngleichNull(buffer.mListeZurueckgebenABC(2))) {
                    matrix = AminusB(buffer.mListeZurueckgebenABC(1), buffer.mListeZurueckgebenABC(2));
                    schrittee();
                } else {
                    showInputMessage(BistNull);
                }
            } else {
                showInputMessage(AistNull);
            }
        }
    }

    public void bMinusB() {
        if (gibtsMatrize(1)) {
            showInputMessage(AexistiertNicht);
        } else if (gibtsMatrize(2)) {
            showInputMessage(BexistiertNicht);
        } else if (!sindDieSpaltenUndZeilenGleich(buffer.mListeZurueckgebenABC(1), buffer.mListeZurueckgebenABC(2))) {
            showInputMessage(unterschiedlicheD);
        } else {
            if (istUngleichNull(buffer.mListeZurueckgebenABC(1))) {
                if (istUngleichNull(buffer.mListeZurueckgebenABC(2))) {

                    matrix = BminusA(buffer.mListeZurueckgebenABC(2), buffer.mListeZurueckgebenABC(1));
                    schrittee();
                } else {
                    showInputMessage(BistNull);
                }
            } else {
                showInputMessage(AistNull);
            }
        }
    }

    public void aGleichb() {
        if (gibtsMatrize(2)) {
            showInputMessage(BexistiertNicht);
        } else {
            buffer.mListeAddABC(buffer.mListeZurueckgebenABC(2), 1);
            zeigeAnmerkung("A hat neue Werte !", 1000);
        }
    }

    public void bGleichc() {
        if (gibtsMatrize(3)) {
            showInputMessage(CexistiertNicht);
        } else {
            buffer.mListeAddABC(buffer.mListeZurueckgebenABC(3), 2);
            zeigeAnmerkung("B hat neue Werte !", 1000);
        }
    }

    public void aGleichc() {
        if (gibtsMatrize(3)) {
            showInputMessage(CexistiertNicht);
        } else {
            buffer.mListeAddABC(buffer.mListeZurueckgebenABC(3), 1);
            zeigeAnmerkung("A hat neue Werte !", 1000);
        }
    }

    public void aMalb() {
        if (gibtsMatrize(1)) {
            showInputMessage(AexistiertNicht);
        } else if (gibtsMatrize(2)) {
            showInputMessage(BexistiertNicht);
        } else {
            if (buffer.mListeZurueckgebenABC(1).getSpalten() == buffer.mListeZurueckgebenABC(2).getZeile()) {
                if (istUngleichNull(buffer.mListeZurueckgebenABC(1))) {
                    if (istUngleichNull(buffer.mListeZurueckgebenABC(2))) {

                        matrix = vr.AmalB(buffer.mListeZurueckgebenABC(1), buffer.mListeZurueckgebenABC(2));
                        buffer.mListeAddABC(matrix, 3);
                        schrittee();

                    } else {
                        showInputMessage(BistNull);
                    }
                } else {
                    showInputMessage(AistNull);
                }

            } else
                showInputMessage("Die Spaltenanzahl der Matrix A muss gleich die Zeilenanzahl der Matrix B sein !");
        }
    }

    public void bMala() {
        if (gibtsMatrize(1)) {
            showInputMessage(AexistiertNicht);
        } else if (gibtsMatrize(2)) {
            showInputMessage(BexistiertNicht);
        } else {
            if (buffer.mListeZurueckgebenABC(2).getSpalten() == buffer.mListeZurueckgebenABC(1).getZeile()) {
                if (istUngleichNull(buffer.mListeZurueckgebenABC(1))) {
                    if (istUngleichNull(buffer.mListeZurueckgebenABC(2))) {

                        matrix = vr.BmalA(buffer.mListeZurueckgebenABC(1), buffer.mListeZurueckgebenABC(2));
                        buffer.mListeAddABC(matrix, 3);
                        schrittee();

                    } else {
                        showInputMessage(BistNull);
                    }
                } else {
                    showInputMessage(AistNull);
                }

            } else
                showInputMessage("Die Spaltenanzahl der Matrix B muss gleich die Zeilenanzahl der Matrix A sein !");
        }
    }

    public void aSymmetrisch() {
        if (!gibtsMatrize(1)) {
            if (istDieMatrizeQuadratisch(buffer.mListeZurueckgebenABC(1))) {
                if (istUngleichNull(buffer.mListeZurueckgebenABC(1))) {
                    aSymetrisch(buffer.mListeZurueckgebenABC(1));
                    schrittee();
                } else {
                    showInputMessage(AistNull);
                }
            } else
                showInputMessage("Die Matrize A ist keine quadratische Matrize !");
        } else
            showInputMessage(AexistiertNicht);
    }

    public void bSymmetrisch() {
        if (!gibtsMatrize(2)) {
            if (istDieMatrizeQuadratisch(buffer.mListeZurueckgebenABC(2))) {
                if (istUngleichNull(buffer.mListeZurueckgebenABC(1))) {
                    bSymetrisch(buffer.mListeZurueckgebenABC(2));
                    schrittee();
                } else {
                    showInputMessage(BistNull);
                }
            } else
                showInputMessage("Die Matrize B ist keine quadratische Matrize !");
        } else
            showInputMessage(BexistiertNicht);
    }

    public void aTransponieren() {
        if (gibtsMatrize(1)) {
            showInputMessage(AexistiertNicht);
        } else {
            if (istUngleichNull(buffer.mListeZurueckgebenABC(1))) {
                aTransponieren(buffer.mListeZurueckgebenABC(1));
                schrittee();
            } else {
                showInputMessage(AistNull);
            }

        }
    }

    public void bTransponieren() {
        if (gibtsMatrize(2)) {
            showInputMessage(BexistiertNicht);
        } else {
            if (istUngleichNull(buffer.mListeZurueckgebenABC(1))) {
                bTransponieren(buffer.mListeZurueckgebenABC(2));
                schrittee();
            } else {
                showInputMessage(BistNull);
            }

        }
    }

    public void lambdaMalaA() {
        if (gibtsMatrize(1)) {
            showInputMessage(AexistiertNicht);
        } else {
            if (buffer.getSkalarListe() == 0.0) {

                showInputMessage("Geben Sie &lambda ein, weil &lambda = 0 ist !");
            } else {
                if (istUngleichNull(buffer.mListeZurueckgebenABC(1))) {
                    lambdaMalAA(buffer.mListeZurueckgebenABC(1), buffer.getSkalarListe());
                    schrittee();
                } else {
                    showInputMessage(BistNull);
                }
            }
        }
    }

    public void lambdaMalaB() {
        if (gibtsMatrize(1)) {
            showInputMessage(BexistiertNicht);
        } else {
            if (buffer.getSkalarListe() == 0.0) {
                showInputMessage("Geben Sie &lambda ein, weil &lambda = 0 ist !");
            } else {
                if (istUngleichNull(buffer.mListeZurueckgebenABC(1))) {
                    lambdaMalBB(buffer.mListeZurueckgebenABC(2), buffer.getSkalarListe());

                    schrittee();
                } else {
                    showInputMessage(BistNull);
                }
            }
        }
    }

    public void determinanteBerechnenA(Matrix mA) {
        buffer.setSkalarListe(vr.determinanteA(mA, false));
    }

    public void determinanteBerechnenB(Matrix mA) {
        buffer.setSkalarListe(vr.determinanteB(mA));
    }

    public void determinanteA() {
        if (gibtsMatrize(1)) {
            showInputMessage(AexistiertNicht);
        } else {
            if (istDieMatrizeQuadratisch(buffer.mListeZurueckgebenABC(1))) {
                if (buffer.mListeZurueckgebenABC(1).getSpalten() <= 3) {
                    if (istUngleichNull(buffer.mListeZurueckgebenABC(1))) {
                        determinanteBerechnenA(buffer.mListeZurueckgebenABC(1));
                        schrittee();
                    } else {
                        showInputMessage(AistNull);
                    }
                } else {
                    showInputMessage("Die maximale Große ist 3X3");
                }
            } else {
                showInputMessage("Die Matrize A ist keine quadratische Matrize !");
            }

        }
    }

    public void determinanteB() {
        if (gibtsMatrize(2)) {
            showInputMessage(BexistiertNicht);
        } else {
            if (istDieMatrizeQuadratisch(buffer.mListeZurueckgebenABC(2))) {
                if (buffer.mListeZurueckgebenABC(2).getSpalten() <= 3) {
                    if (istUngleichNull(buffer.mListeZurueckgebenABC(1))) {
                        determinanteBerechnenB(buffer.mListeZurueckgebenABC(2));
                        schrittee();
                    } else {
                        showInputMessage(BistNull);
                    }
                } else {
                    showInputMessage("Die maximale Große ist 3X3");
                }
            } else {
                showInputMessage("Die Matrize B ist keine quadratische Matrize !");
            }
        }
    }

    public void buttonsSymbole() {
        TextView lamda = (TextView) findViewById(R.id.MphawEingeben);
        lamda.setText(Html.fromHtml("&lambda;"));

        TextView AgleichC = (TextView) findViewById(R.id.MAgleichC);
        AgleichC.setText(Html.fromHtml("a := c"));

        TextView AgleichB = (TextView) findViewById(R.id.MAgleichB);
        AgleichB.setText(Html.fromHtml("a := b"));

        TextView BgleichC = (TextView) findViewById(R.id.MBgleichC);
        BgleichC.setText(Html.fromHtml("b := c"));

        TextView AplusB = (TextView) findViewById(R.id.MAplusB);
        AplusB.setText(Html.fromHtml("a + b"));

        TextView AminusB = (TextView) findViewById(R.id.MAminusB);
        AminusB.setText(Html.fromHtml("a - b"));

        TextView BminusA = (TextView) findViewById(R.id.MBminusA);
        BminusA.setText(Html.fromHtml("b - a"));

        TextView AmalB = (TextView) findViewById(R.id.MAmalB);
        AmalB.setText(Html.fromHtml("a &#183; b"));

        TextView BmalA = (TextView) findViewById(R.id.MBmalA);
        BmalA.setText(Html.fromHtml("b &#183; a"));

        TextView Ainverse = (TextView) findViewById(R.id.MAinverse);
        Ainverse.setText(Html.fromHtml("<small><small>\t\t-1</small></small><br>A"));

        TextView Binverse = (TextView) findViewById(R.id.MBinverse);
        Binverse.setText(Html.fromHtml("<small><small>\t\t-1</small></small><br>B"));

        TextView Atransponieren = (TextView) findViewById(R.id.MAtranspnieren);
        Atransponieren.setText(Html.fromHtml("<small><small><small>\tT</small></small></small><br>A"));

        TextView Btransponieren = (TextView) findViewById(R.id.MBtransponieren);
        Btransponieren.setText(Html.fromHtml("<small><small><small>\tT</small></small></small><br>B"));

        TextView LmalA = (TextView) findViewById(R.id.MAmalPhaw);
        LmalA.setText(Html.fromHtml("A &#183; &lambda;"));

        TextView LmalB = (TextView) findViewById(R.id.MBmalPhaw);
        LmalB.setText(Html.fromHtml("B &#183; &lambda;"));

        TextView Asymmetrische = (TextView) findViewById(R.id.MAsymmtrisch);
        Asymmetrische.setText(Html.fromHtml("<small><small><small>T</small></small></small><br>\tA  = A"));

        TextView Bsymmetrische = (TextView) findViewById(R.id.MBsymmetrisch);
        Bsymmetrische.setText(Html.fromHtml("<small><small><small>T</small></small></small><br>\tB  = B"));

        TextView Adeterminante = (TextView) findViewById(R.id.MdeterminanteA);
        Adeterminante.setText(Html.fromHtml("det(A)"));

        TextView Bdeterminante = (TextView) findViewById(R.id.MdeterminanteB);
        Bdeterminante.setText(Html.fromHtml("det(B)"));
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

    public boolean istUngleichNull(Matrix m) {
        int zahl = 0;
        for (int i = 0; i < m.getZeile(); i++) {
            for (int j = 0; j < m.getSpalten(); j++) {
                if (m.getDoubleIndexPosition(i, j) == 0) {
                    zahl++;
                }
            }
        }
        if (zahl == m.getZeile() * m.getSpalten()) {
            return false;
        } else
            return true;
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

        return vorZahln + "," + nachZahlen;
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

    public void gibtEsMatrizen() {
        Button a = (Button) findViewById(R.id.MerstellenA);
        Button b = (Button) findViewById(R.id.MerstellenB);
        Button c = (Button) findViewById(R.id.MerstellenC);
        if (buffer.mListeIsEmptyABC(1)) {
            a.setBackgroundResource(R.drawable.eingabe_buttons);
        } else {
            a.setBackgroundResource(R.drawable.eingegeben_buttons);
        }
        if (buffer.mListeIsEmptyABC(2)) {
            b.setBackgroundResource(R.drawable.eingabe_buttons);
        } else {
            b.setBackgroundResource(R.drawable.eingegeben_buttons);
        }
        if (buffer.mListeIsEmptyABC(3)) {
            c.setBackgroundResource(R.drawable.eingabe_buttons);
        } else {
            c.setBackgroundResource(R.drawable.eingegeben_buttons);
        }
    }
}
