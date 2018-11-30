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
import Logik.Vektor;
import Logik.Verwaltung;

public class Vektoren extends AppCompatActivity {

    private Verwaltung vr = new Verwaltung();
    private Buffer buffer;
    private EditText lambda;

    private String AexistiertNicht = "Der Vektor a existiert noch nicht !";
    private String BexistiertNicht = "Der Vektor b existiert noch nicht !";
    private String CexistiertNicht = "Der Vektor c existiert noch nicht !";


    private String unterschiedlicheD = "Die Vektoren haben unterschiedliche Dimensionen !";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vektoren);
        buffer = buffer.getInstance();
        buttonsSymbole();
        gibtEsVektoren();
    }

    public void vektorenInfo() {
        String s = "\\mathbf{  Hilfe \\quad Vektoren } \\\\ \\quad \\\\ \\quad \\\\";
        s = s + " {\\color{Red} {Allgemeines}} \\\\ \\quad \\\\ ";
        s = s + "{\\color{Red} {1)} } \\quad Es \\quad können \\quad Operationen \\quad mit \\quad zwei- \\quad oder \\\\ dreidimensionalen \\quad Vektoren \\quad durchgeführt \\quad werden.\\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {2)} } \\quad Operationen \\quad mit \\quad mehreren \\quad Vektoren \\\\ sind \\quad nur \\quad möglich \\quad wenn \\\\ die \\quad Vektoren \\quad dieselbe \\quad Dimension\\quad  besitzen.\\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {3)} } \\quad Bevor \\quad eine \\quad Operation \\quad durchgeführt \\\\ werden \\quad kann, \\quad müssen \\quad die \\\\ dafür \\quad erforderlichen \\quad Vektoren \\quad über  \\quad die \\\\ grünen \\quad Buttons \\quad eingeben \\quad werden.\\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {4)} } \\quad Bevor \\quad ein \\quad Vektor \\quad mit \\\\ einer \\quad Zahl \\quad multipliziert \\quad werden \\quad kann, \\\\ muss \\quad dies \\quad Zahl \\quad durch \\quad den \\\\ entsprechenden \\quad grünen \\quad Button \\quad eingegeben \\quad werden.\\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {5)} } \\quad Berechnete \\quad Vektoren \\quad werden \\\\ automatisch \\quad unter \\quad \\vec{c} \\quad gespeichert.\\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {6)} } \\quad Durch \\quad die \\quad Buttons \\quad \\vec{a}=:\\vec{c}, \\quad \\vec{b}=:\\vec{c} \\quad bzw\\quad \\vec{a}=:\\vec{b} \\\\ werden \\quad die \\quad Vektoren \\\\ \\quad \\vec{a} \\quad \\vec{b} \\quad bzw. \\quad \\vec{c}  \\quad  durch \\quad ersetzt.\\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {7)} } \\quad Alle \\quad eingegebenen \\quad und \\quad gespeicherten \\\\ Vektoren \\quad sowie \\quad \\lambda \\quad können \\quad durch \\\\ die \\quad Betätigung \\quad der \\quad entsprechenden \\quad grünen \\\\ Buttons \\quad angesehen \\quad und \\quad geändert \\quad werden.\\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {8)} } \\quad Bei \\quad Betätigen \\quad des \\\\ Zurück-Buttons \\quad werden \\quad alle \\\\ Vektoren \\quad und \\quad \\lambda \\quad gelöscht.\\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {Rechenoperationen}} \\\\ \\quad \\\\ ";
        s = s + "{\\color{Red} {9)} } \\quad Vektorenaddition \\\\ \\vec{a}+\\vec{b}=\\left(\\begin{array}{c} x_{a} \\\\ y_{a} \\end{array}\\right) + \\left(\\begin{array}{c} x_{b} \\\\ y_{b} \\end{array}\\right)=\\left(\\begin{array}{c} x_{a}+x_{b} \\\\ y_{a}+y_{b} \\end{array}\\right) \\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {10)} } \\quad Vektorensubtraktion \\\\ \\vec{a}-\\vec{b}=\\left(\\begin{array}{c} x_{a} \\\\ y_{a} \\end{array}\\right) - \\left(\\begin{array}{c} x_{b} \\\\ y_{b} \\end{array}\\right)=\\left(\\begin{array}{c} x_{a}-x_{b} \\\\ y_{a}-y_{b} \\end{array}\\right) \\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {11)} } \\quad Skalarprodukt \\\\ \\vec{a}\\cdot \\vec{b}=\\left(\\begin{array}{c} x_{a} \\\\ y_{a} \\end{array}\\right) \\cdot \\left(\\begin{array}{c} x_{b} \\\\ y_{b} \\end{array}\\right)=\\left(\\begin{array}{c} x_{a} \\cdot x_{b} \\\\ y_{a} \\cdot y_{b} \\end{array}\\right) \\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {12)} } \\quad Kreuzprodukt \\\\ \\vec{a} \\chi  \\vec{b}=\\left(\\begin{array}{c} x_{a} \\\\ y_{a} \\\\z_{a} \\end{array}\\right) \\chi  \\left(\\begin{array}{c} x_{b} \\\\ y_{b} \\\\ z_{b} \\end{array}\\right)=\\left(\\begin{array}{c} y_{a} \\cdot z_{b} - z_{a} \\cdot y_{b}  \\\\ z_{a} \\cdot x_{b} - x_{a} \\cdot z_{b} \\\\ x_{a} \\cdot y_{b} - y_{a} \\cdot x_{b} \\end{array}\\right) \\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {13)} } \\quad Länge \\quad eines \\quad Vektors\\\\ \\vec{a}= \\left(\\begin{array}{c} x \\\\ y \\\\ z \\end{array}\\right) \\\\ \\quad \\\\ \\left | \\vec{a}  \\right |  = \\sqrt{x^2 +y^2+z^2} \\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {14)} }\\quad Der \\quad Winkel \\quad zwischen \\quad zwei \\quad Vektoren \\\\  \\cos \\varphi =\\frac{\\vec{a}\\cdot \\vec{b}}{\\left | \\vec{a} \\right |\\cdot \\left | \\vec{b} \\right |} \\rightarrow \\varphi = \\arccos \\left ( \\frac{\\vec{a}\\cdot \\vec{b}}{\\left | \\vec{a} \\right |\\cdot \\left | \\vec{b} \\right |} \\right ) \\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {15)} } \\quad Multiplikation \\quad eines \\quad Vektors \\quad mit \\quad einer \\quad Zahl \\\\ \\lambda \\cdot \\vec{a}= \\lambda \\cdot \\left(\\begin{array}{c} x \\\\ y\\end{array}\\right) = \\left(\\begin{array}{c} \\lambda \\cdot x \\\\ \\lambda \\cdot y \\end{array}\\right) \\\\ \\quad \\\\ \\quad \\\\";
        s = s + "{\\color{Red} {16)} } \\quad Lineare-Abhängigkeit \\quad drei \\quad Vektoren \\\\ \\vec{a} = \\left(\\begin{array}{c} x_{a} \\\\ y_{a} \\\\ z_{a}\\end{array}\\right) ,\\quad \\vec{b} = \\left(\\begin{array}{c} x_{b} \\\\ y_{b} \\\\ z_{b}\\end{array}\\right), \\quad \\vec{c} = \\left(\\begin{array}{c} x_{c} \\\\ y_{c} \\\\ z_{c}\\end{array}\\right)" +
                "\\\\ Es \\quad wird \\quad die \\quad Matrix \\quad untersucht, \\\\ deren \\quad Spalten \\quad aus \\quad den \\quad drei \\\\ Vektoren \\quad bestehen. \\\\ Ist \\quad die \\quad Determinante \\quad dieser \\\\ Matrix \\quad gleich \\quad Null, \\quad dann \\quad sind \\\\ die \\quad Vektoren \\quad linear \\quad abhängig." +
                " \\\\ |D| = \\begin{vmatrix} {\\color{Red} { \\vec{a} } }    &  {\\color{Red} { \\vec{b} } }  &  {\\color{Red} { \\vec{c} } } \\\\ x_{a} & x_{b} & x_{c} \\\\ y_{a} & y_{b} & y_{c} \\\\ z_{a} & z_{b} & z_{c} \\end{vmatrix} = 0 \\\\  \\quad \\\\ \\quad \\\\";

        buffer.infoListeLeeren();
        buffer.infoListeAdd("\\[" + s + "\\]");

        Intent i = new Intent(getApplicationContext(), Informationen.class);
        startActivity(i);
    }

    public void vektorenVerwaltung(View v) {
        if (v.getId() == R.id.VerstellenA) {
            buffer.setNavigation(1);
            Intent i = new Intent(getApplicationContext(), Vektorenerstellen.class);
            startActivity(i);
        } else if (v.getId() == R.id.VerstllenB) {
            buffer.setNavigation(2);
            Intent i = new Intent(getApplicationContext(), Vektorenerstellen.class);
            startActivity(i);
        } else if (v.getId() == R.id.VerstellenC) {
            buffer.setNavigation(3);
            Intent i = new Intent(getApplicationContext(), Vektorenerstellen.class);
            startActivity(i);
        } else if (v.getId() == R.id.VaGleichb) {
            aGleichb();
        } else if (v.getId() == R.id.VaGleichc) {
            aGleichc();
        } else if (v.getId() == R.id.VbGleichc) {
            bGleichc();
        } else if (v.getId() == R.id.VaPlusb) {
            aPlusb();
        } else if (v.getId() == R.id.VaMalb) {
            aMalb();
        } else if (v.getId() == R.id.VaminusB) {
            aMinusb();
        } else if (v.getId() == R.id.VbMinusa) {
            bMinusa();
        } else if (v.getId() == R.id.VaKreuzB) {
            aKreuzb();
        } else if (v.getId() == R.id.VbKreuzA) {
            bKreuza();
        } else if (v.getId() == R.id.VaLaenge) {
            aLaenge();
        } else if (v.getId() == R.id.VbLaenge) {
            bLaenge();
        } else if (v.getId() == R.id.VaSenkrechtb) {
            aSenkrechtb();
        } else if (v.getId() == R.id.Vwinkel) {
            winkel();
        } else if (v.getId() == R.id.VphawMala) {
            lambdaMalA();
        } else if (v.getId() == R.id.VphawMalb) {
            lambdaMalB();
        } else if (v.getId() == R.id.Vlinearabhaengig) {
            kollinearOderLinaerabhaengig();
        } else if (v.getId() == R.id.Vzurueck) {
            zurueck();
        } else if (v.getId() == R.id.Vinfo) {
            vektorenInfo();
        } else if (v.getId() == R.id.VphawEingabe) {
            skalarEingabe("");
        }
        gibtEsVektoren();
    }

    public void lambdaMalB() {
        if (gibtsVektoren(2)) {
            showInputMessage(BexistiertNicht);
        } else {
            lambdaMalB(buffer.vListeZurueckgebenABC(2), buffer.getSkalarListe());
            schritte();
        }
    }

    public void lambdaMalA() {
        if (gibtsVektoren(1)) {
            showInputMessage(AexistiertNicht);
        } else {
            lambdaMalA(buffer.vListeZurueckgebenABC(1), buffer.getSkalarListe());
            schritte();
        }
    }

    public void lambdaMalA(Vektor vA, double d) {
        buffer.vListeAddABC(vr.lambdaMalA(vA, d), 3);
    }

    public void lambdaMalB(Vektor vA, double d) {
        buffer.vListeAddABC(vr.lambdaMalB(vA, d), 3);
    }

    public void winkel() {
        if (gibtsVektoren(1)) {
            showInputMessage(AexistiertNicht);
        } else if (gibtsVektoren(2)) {
            showInputMessage(BexistiertNicht);
        } else {
            if (sindGleicheDimension(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2))) {
                if (istUngleichNull(buffer.vListeZurueckgebenABC(1))) {
                    if (istUngleichNull(buffer.vListeZurueckgebenABC(2))) {
                        vr.vektorenWinkelBerechnen(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2));
                        schritte();
                    } else
                        showInputMessage("Der Winkel mit einem Nullvektor ist nicht definiert !");
                } else
                    showInputMessage("Der Winkel mit einem Nullvektor ist nicht definiert !");
            } else {
                showInputMessage(unterschiedlicheD);
            }
        }
    }

    public void aSenkrechtb() {
        if (gibtsVektoren(1)) {
            showInputMessage(AexistiertNicht);
        } else if (gibtsVektoren(2)) {
            showInputMessage(BexistiertNicht);
        } else {
            if (sindGleicheDimension(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2))) {
                if (istUngleichNull(buffer.vListeZurueckgebenABC(1))) {
                    if (istUngleichNull(buffer.vListeZurueckgebenABC(2))) {
                        vr.sindDieVektorenSenkrecht(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2));
                        schritte();
                    } else
                        showInputMessage("Der Winkel mit einem Nullvektor ist nicht definiert !");
                } else
                    showInputMessage("Der Winkel mit einem Nullvektor ist nicht definiert !");
            } else {
                showInputMessage(unterschiedlicheD);
            }
        }
    }

    public void bLaenge() {
        if (gibtsVektoren(2)) {
            showInputMessage(BexistiertNicht);
        } else {
            vr.vektorlaengeB(buffer.vListeZurueckgebenABC(2));
            schritte();
        }
    }

    public void aLaenge() {
        if (gibtsVektoren(1)) {
            showInputMessage(AexistiertNicht);
        } else {
            vr.vektorlaengeA(buffer.vListeZurueckgebenABC(1));
            schritte();
        }
    }

    public void bKreuza() {
        if (gibtsVektoren(1)) {
            showInputMessage(AexistiertNicht);
        } else if (gibtsVektoren(2)) {
            showInputMessage(BexistiertNicht);
        } else {
            if (sindGleicheDimension(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2))) {
                if (buffer.vListeZurueckgebenABC(1).grosse == 3) {
                    buffer.vListeAddABC(vr.bKreuzA(buffer.vListeZurueckgebenABC(2), buffer.vListeZurueckgebenABC(1)), 3);
                    schritte();
                } else {
                    showInputMessage("Das Kreuzprodukt ist nur für 3-dimensionale Vektoren definiert !");
                }
            } else {
                showInputMessage(unterschiedlicheD);
            }
        }
    }

    public void aKreuzb() {
        if (gibtsVektoren(1)) {
            showInputMessage(AexistiertNicht);
        } else if (gibtsVektoren(2)) {
            showInputMessage(BexistiertNicht);
        } else {
            if (sindGleicheDimension(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2))) {
                if (buffer.vListeZurueckgebenABC(1).grosse == 3) {

                    buffer.vListeAddABC(vr.aKreuzB(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2)), 3);
                    schritte();
                } else {
                    showInputMessage("Das Kreuzprodukt ist nur für 3-dimensionale Vektoren definiert !");
                }
            } else {
                showInputMessage(unterschiedlicheD);
            }
        }
    }

    public void bMinusa() {
        if (gibtsVektoren(1)) {
            showInputMessage(AexistiertNicht);
        } else if (gibtsVektoren(2)) {
            showInputMessage(BexistiertNicht);
        } else {
            if (sindGleicheDimension(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2))) {
                buffer.vListeAddABC(vr.vektorenSubtrationBminusA(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2)), 3);
                schritte();
            } else {
                showInputMessage(unterschiedlicheD);
            }
        }
    }

    public void aMinusb() {
        if (gibtsVektoren(1)) {
            showInputMessage(AexistiertNicht);
        } else if (gibtsVektoren(2)) {
            showInputMessage(BexistiertNicht);
        } else {
            if (sindGleicheDimension(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2))) {
                buffer.vListeAddABC(vr.vektorenSubtrationAminusB(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2)), 3);
                schritte();

            } else {
                showInputMessage(unterschiedlicheD);
            }
        }
    }

    public void aMalb() {
        if (gibtsVektoren(1)) {
            showInputMessage(AexistiertNicht);
        } else if (gibtsVektoren(2)) {
            showInputMessage(BexistiertNicht);
        } else {
            if (sindGleicheDimension(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2))) {
                vr.vektorenMultiplikation(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2));
                schritte();
            } else {
                showInputMessage(unterschiedlicheD);
            }
        }
    }

    public void aPlusb() {
        if (gibtsVektoren(1)) {
            showInputMessage(AexistiertNicht);
        } else if (gibtsVektoren(2)) {
            showInputMessage(BexistiertNicht);
        } else {
            if (sindGleicheDimension(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2))) {
                buffer.vListeAddABC(vr.vektorenAddition(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2)), 3);
                schritte();
            } else {
                showInputMessage(unterschiedlicheD);
            }
        }
    }

    public void aGleichb() {
        if (gibtsVektoren(2)) {
            showInputMessage(BexistiertNicht);
        } else {
            buffer.vListeAddABC(buffer.vListeZurueckgebenABC(2), 1);
            zeigeAnmerkung(Html.fromHtml("&rarr;<br>&nbsp; a &nbsp;&nbsp; hat neue Komponenten !") + "", 1000);
        }
    }

    public void aGleichc() {
        if (gibtsVektoren(3)) {
            showInputMessage(CexistiertNicht);
        } else {
            buffer.vListeAddABC(buffer.vListeZurueckgebenABC(3), 1);
            zeigeAnmerkung(Html.fromHtml("&rarr;<br>&nbsp; a &nbsp;&nbsp; hat neue Komponenten !") + "", 1000);
        }
    }

    public void bGleichc() {
        if (gibtsVektoren(3)) {
            showInputMessage(CexistiertNicht);
        } else {
            buffer.vListeAddABC(buffer.vListeZurueckgebenABC(3), 2);
            zeigeAnmerkung(Html.fromHtml("&rarr;<br>&nbsp; b &nbsp;&nbsp; hat neue Komponenten !") + "", 1000);
        }
    }

    public boolean gibtsVektoren(int a) {
        return buffer.vListeIsEmptyABC(a);
    }

    public void showInputMessage(String message) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(Html.fromHtml(message));
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    public boolean sindGleicheDimension(Vektor v1, Vektor v2) {
        if (v1.grosse == v2.grosse) {
            return true;
        } else
            return false;
    }

    public void schritte() {
        Intent i = new Intent(getApplicationContext(), Schritte.class);
        startActivity(i);
    }

    public void skalarEingabe(String s) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(Html.fromHtml("Geben Sie Lambda ( &lambda; ) ein " + s + "!"));

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


    public void linearabhaengigkeit() {
        if (!gibtsVektoren(1)) {
            if (!gibtsVektoren(2)) {
                if (!gibtsVektoren(3)) {
                    if (sindGleicheDimension(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2)) && sindGleicheDimension(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(3))) {
                        double d = vr.sindDieVektorenAbhaengigg(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2), buffer.vListeZurueckgebenABC(3));
                        vr.linearabhaengigkeit(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2), buffer.vListeZurueckgebenABC(3));
                        schritte();
                    } else {
                        showInputMessage(unterschiedlicheD);
                    }
                } else {
                    showInputMessage(CexistiertNicht);
                }

            } else {

                showInputMessage(BexistiertNicht);
            }

        } else {
            showInputMessage(AexistiertNicht);
        }
    }

    public void kollinearOderLinaerabhaengig() {
        if (!gibtsVektoren(1)) {
            if (buffer.vListeZurueckgebenABC(1).grosse == 2) {
                kollinar();
            } else {
                linearabhaengigkeit();
            }
        } else {
            showInputMessage(AexistiertNicht);
        }
    }

    public void kollinar() {
        if (!gibtsVektoren(1)) {
            if (!gibtsVektoren(2)) {
                if (buffer.vListeZurueckgebenABC(1).grosse == 2 && buffer.vListeZurueckgebenABC(2).grosse == 2) {
                    vr.kollinear(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2));
                    schritte();
                } else {
                    showInputMessage(unterschiedlicheD);
                }

            } else {
                showInputMessage(BexistiertNicht);
            }
        } else {
            showInputMessage(CexistiertNicht);
        }
    }

    public void zurueck() {
        buffer.vListeLeeren();
        buffer.schrittListeLeeren();
        Intent i = new Intent(getApplicationContext(), Menue.class);
        startActivity(i);
    }

    public void buttonsSymbole() {
        TextView vErstellenA = (TextView) findViewById(R.id.VerstellenA);

        vErstellenA.setText(Html.fromHtml("&rarr;<br>a"));

        TextView vErstellenB = (TextView) findViewById(R.id.VerstllenB);
        vErstellenB.setText(Html.fromHtml("&rarr;<br>b"));

        TextView vErstellenC = (TextView) findViewById(R.id.VerstellenC);
        vErstellenC.setText(Html.fromHtml("&rarr;<br>c"));

        TextView lamda = (TextView) findViewById(R.id.VphawEingabe);
        lamda.setText(Html.fromHtml("&lambda;"));

        TextView AgleichC = (TextView) findViewById(R.id.VaGleichc);
        AgleichC.setText(Html.fromHtml("&rarr;\t&rarr;<br>a := c"));

        TextView AgleichB = (TextView) findViewById(R.id.VaGleichb);
        AgleichB.setText(Html.fromHtml("&rarr;\t&rarr;<br>a := b"));

        TextView BgleichC = (TextView) findViewById(R.id.VbGleichc);
        BgleichC.setText(Html.fromHtml("&rarr;\t&rarr;<br>b := c"));

        TextView AplusB = (TextView) findViewById(R.id.VaPlusb);
        AplusB.setText(Html.fromHtml("&rarr;\t\t&rarr;<br>a + b"));

        TextView AmalB = (TextView) findViewById(R.id.VaMalb);
        AmalB.setText(Html.fromHtml("&rarr;\t\t&rarr;<br>a &#183; b"));

        TextView AminusB = (TextView) findViewById(R.id.VaminusB);
        AminusB.setText(Html.fromHtml("&rarr;\t\t&rarr;<br>a - b"));

        TextView BminusA = (TextView) findViewById(R.id.VbMinusa);
        BminusA.setText(Html.fromHtml("&rarr;\t\t&rarr;<br>b - a"));

        TextView AkreuzB = (TextView) findViewById(R.id.VaKreuzB);
        AkreuzB.setText(Html.fromHtml("&rarr;\t\t&rarr;<br>a X b"));

        TextView BkreuzA = (TextView) findViewById(R.id.VbKreuzA);
        BkreuzA.setText(Html.fromHtml("&rarr;\t\t&rarr;<br>b X a"));

        TextView Alaenge = (TextView) findViewById(R.id.VaLaenge);
        Alaenge.setText(Html.fromHtml("\t&rarr;<br>| a |"));

        TextView Blaenge = (TextView) findViewById(R.id.VbLaenge);
        Blaenge.setText(Html.fromHtml("\t&rarr;<br>| b |"));

        TextView AsenkrechtB = (TextView) findViewById(R.id.VaSenkrechtb);
        AsenkrechtB.setText(Html.fromHtml("&rarr;\t\t&rarr;<br> a ⊥ b"));

        TextView AwinkelB = (TextView) findViewById(R.id.Vwinkel);
        AwinkelB.setText(Html.fromHtml("&nbsp;&nbsp; &rarr;\t&rarr;<br>&ang;(a , b)"));

        TextView LmalA = (TextView) findViewById(R.id.VphawMala);
        LmalA.setText(Html.fromHtml("a &#183; &lambda;"));
        LmalA.setText(Html.fromHtml("&rarr;<br>\t\ta &#183; &lambda;"));

        TextView LmalB = (TextView) findViewById(R.id.VphawMalb);
        LmalB.setText(Html.fromHtml("&rarr;<br>\t\tb &#183; &lambda;"));

        TextView linearabhaengig = (TextView) findViewById(R.id.Vlinearabhaengig);
        linearabhaengig.setText(Html.fromHtml("LIN. ABH."));
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

    public boolean istUngleichNull(Vektor v) {
        int vergleichZahl = 0;
        for (int i = 0; i < v.grosse; i++) {
            if (v.getPV(i + 1) == 0)
                vergleichZahl++;
        }
        if (vergleichZahl == v.grosse)
            return false;
        else
            return true;
    }

    public void gibtEsVektoren() {
        Button a = (Button) findViewById(R.id.VerstellenA);
        Button b = (Button) findViewById(R.id.VerstllenB);
        Button c = (Button) findViewById(R.id.VerstellenC);
        if (buffer.vListeIsEmptyABC(1)) {
            a.setBackgroundResource(R.drawable.eingabe_buttons);
        } else {
            a.setBackgroundResource(R.drawable.eingegeben_buttons);
        }
        if (buffer.vListeIsEmptyABC(2)) {
            b.setBackgroundResource(R.drawable.eingabe_buttons);
        } else {
            b.setBackgroundResource(R.drawable.eingegeben_buttons);
        }
        if (buffer.vListeIsEmptyABC(3)) {
            c.setBackgroundResource(R.drawable.eingabe_buttons);
        } else {
            c.setBackgroundResource(R.drawable.eingegeben_buttons);
        }
    }
}
