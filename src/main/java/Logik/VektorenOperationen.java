package Logik;

import Datenhaltung.Buffer;

public class VektorenOperationen {
    private Vektor vektor = null;
    private Buffer buffer;
    private boolean istRichtig = true;
    private boolean keineLoesung = false;
    private MatrixOperationen mo;

    public VektorenOperationen() {
        buffer = Buffer.getInstance();
        mo = new MatrixOperationen();
    }
    // gebraucht
    public Vektor addition(Vektor v1, Vektor v2) {
        buffer.schrittListeLeeren();
        String schritte1 = "$$\\vec{a} + \\vec{b} = \\vec{c} $$ " + "$$" + v1.druckeDaten("a") + "\\quad , " + v2.druckeDaten("b") + " $$";
        double x = 0, y = 0, z = 0;
        if (v1.grosse == 2) {
            x = (v1.getPV(1) * 1000 + v2.getPV(1) * 1000) / 1000;
            y = (v1.getPV(2) * 1000 + v2.getPV(2) * 1000) / 1000;
            schritte1 = schritte1 + "\\[ \\vec{a} + \\vec{b} = \\left(\\begin{array}{c} " + zahlenKlammer(ZA(v1.getPV(1))) + " + " +zahlenKlammer( ZA(v2.getPV(1))) + " \\\\ " + zahlenKlammer(ZA(v1.getPV(2))) + " + " +zahlenKlammer( ZA(v2.getPV(2))) + " \\end{array}\\right) \\]";
            vektor = new Vektor(x, y, istRichtig, "", keineLoesung);
            schritte1 = schritte1 + "\\[" + vektor.druckeDaten("c") + "\\]";
            buffer.schrittListeAdd(schritte1);
            return vektor;
        } else {
            x = (v1.getPV(1) * 1000 + v2.getPV(1) * 1000) / 1000;
            y = (v1.getPV(2) * 1000 + v2.getPV(2) * 1000) / 1000;
            z = (v1.getPV(3) * 1000 + v2.getPV(3) * 1000) / 1000;
            schritte1 = schritte1 + "\\[ \\vec{a} + \\vec{b} = \\left(\\begin{array}{c} " + zahlenKlammer(ZA(v1.getPV(1))) + " + " + zahlenKlammer(ZA(v2.getPV(1))) + " \\\\ " +zahlenKlammer( ZA(v1.getPV(2))) + " + " + zahlenKlammer( ZA(v2.getPV(2))) + " \\\\ " + zahlenKlammer( ZA(v1.getPV(3))) + " + " + zahlenKlammer( ZA(v2.getPV(3))) + " \\end{array}\\right) \\]";

            vektor = new Vektor(x, y, z, istRichtig, "", keineLoesung);
            schritte1 = schritte1 + "\\[" + vektor.druckeDaten("c") + "\\]";
            buffer.schrittListeAdd(schritte1);
            return vektor;
        }
    }
    // gebraucht
    public Vektor subtrationAminusB(Vektor v1, Vektor v2) {
        buffer.schrittListeLeeren();
        String schritte1 = "\\[\\vec{a} - \\vec{b} = \\vec{c} \\] " + "\\[" + v1.druckeDaten("a") + "\\quad ," + v2.druckeDaten("b") + "\\]";
        double x = 0, y = 0, z = 0;
        if (v1.grosse == 2) {
            x = (v1.getPV(1) * 1000 - v2.getPV(1) * 1000) / 1000;
            y = (v1.getPV(2) * 1000 - v2.getPV(2) * 1000) / 1000;
            schritte1 = schritte1 + "\\[ \\vec{a} - \\vec{b} = \\left(\\begin{array}{c} " + zahlenKlammer(ZA(v1.getPV(1))) + " - " + zahlenKlammer(ZA(v2.getPV(1))) + " \\\\ " +zahlenKlammer( ZA(v1.getPV(2))) + " - " + zahlenKlammer(ZA(v2.getPV(2))) + " \\end{array}\\right) \\]";
            vektor = new Vektor(x, y, istRichtig, "", keineLoesung);
            schritte1 = schritte1 + "\\[" + vektor.druckeDaten("c") + "\\]";
            buffer.schrittListeAdd(schritte1);
            return vektor;
        } else {
            x = (v1.getPV(1) * 1000 - v2.getPV(1) * 1000) / 1000;
            y = (v1.getPV(2) * 1000 - v2.getPV(2) * 1000) / 1000;
            z = (v1.getPV(3) * 1000 - v2.getPV(3) * 1000) / 1000;
            schritte1 = schritte1 + "\\[ \\vec{a} - \\vec{b} = \\left(\\begin{array}{c} " +zahlenKlammer( ZA(v1.getPV(1))) + " - " + zahlenKlammer(ZA(v2.getPV(1))) + " \\\\ " +zahlenKlammer(ZA(v1.getPV(2))) + " - " +zahlenKlammer( ZA(v2.getPV(2))) + " \\\\ " + zahlenKlammer(ZA(v1.getPV(3))) + " - " +zahlenKlammer( ZA(v2.getPV(3))) + " \\end{array}\\right) \\]";
            vektor = new Vektor(x, y, z, istRichtig, "", keineLoesung);
            schritte1 = schritte1 + "\\[" + vektor.druckeDaten("c") + "\\]";
            buffer.schrittListeAdd(schritte1);
            return vektor;
        }
    }
    // gebraucht
    public Vektor subtrationBminusA(Vektor v1, Vektor v2) {
        buffer.schrittListeLeeren();
        String schritte1 = "\\[\\vec{b} - \\vec{a} = \\vec{c} \\] " + "\\[" + v2.druckeDaten("a") + "\\quad ," + v1.druckeDaten("b") + "\\]";
        double x = 0, y = 0, z = 0;
        if (v1.grosse == 2) {
            x = (v1.getPV(1) * 1000 - v2.getPV(1) * 1000) / 1000;
            y = (v1.getPV(2) * 1000 - v2.getPV(2) * 1000) / 1000;
            schritte1 = schritte1 + "\\[ \\vec{b} - \\vec{a} = \\left(\\begin{array}{c} " + zahlenKlammer(ZA(v1.getPV(1))) + " - " +zahlenKlammer( ZA(v2.getPV(1))) + " \\\\ " + zahlenKlammer(ZA(v1.getPV(2))) + " - " +zahlenKlammer( ZA(v2.getPV(2))) + " \\end{array}\\right) \\]";
            vektor = new Vektor(x, y, istRichtig, "", keineLoesung);
            schritte1 = schritte1 + "\\[" + vektor.druckeDaten("c") + "\\]";
            buffer.schrittListeAdd(schritte1);
            return vektor;
        } else {
            x = (v1.getPV(1) * 1000 - v2.getPV(1) * 1000) / 1000;
            y = (v1.getPV(2) * 1000 - v2.getPV(2) * 1000) / 1000;
            z = (v1.getPV(3) * 1000 - v2.getPV(3) * 1000) / 1000;
            schritte1 = schritte1 + "\\[ \\vec{a} - \\vec{b} = \\left(\\begin{array}{c} " +zahlenKlammer( ZA(v1.getPV(1))) + " - " + zahlenKlammer(ZA(v2.getPV(1))) + " \\\\ " +zahlenKlammer( ZA(v1.getPV(2))) + " - " +zahlenKlammer( ZA(v2.getPV(2))) + " \\\\ " + zahlenKlammer(ZA(v1.getPV(3))) + " - " + zahlenKlammer(ZA(v2.getPV(3))) + " \\end{array}\\right) \\]";
            vektor = new Vektor(x, y, z, istRichtig, "", keineLoesung);
            schritte1 = schritte1 + "\\[" + vektor.druckeDaten("c") + "\\]";
            buffer.schrittListeAdd(schritte1);
            return vektor;
        }
    }
    // gebraucht
    public Vektor lambdaMalA(Vektor v1, double i) {
        buffer.schrittListeLeeren();
        Vektor v;
        String schritte = "\\[\\lambda \\cdot \\vec{a} = \\vec{c} \\]";
        double x = 0, y = 0, z = 0;
        if (v1.grosse == 2) {
            x = i * v1.getPV(1);
            y = i * v1.getPV(2);
            schritte = schritte + "\\[" + v1.druckeDaten("a") + ", \\quad" + " \\lambda = " + ZA(i) + "\\]";
            schritte = schritte + "\\[\\lambda \\cdot \\vec{a} =  \\left(\\begin{array}{c} " +zahlenKlammer( ZA(i)) + "\\cdot" +zahlenKlammer( ZA(v1.getPV(1)) )+ " \\\\ " +zahlenKlammer( ZA(i)) + "\\cdot" +zahlenKlammer( ZA(v1.getPV(2))) + " \\end{array}\\right) \\]";
            v = new Vektor(x, y, istRichtig, "", keineLoesung);
            schritte = schritte + "\\[" + v.druckeDaten("c") + "\\]";
            buffer.schrittListeAdd(schritte);
            return v;
        } else {
            x = i * v1.getPV(1);
            y = i * v1.getPV(2);
            z = i * v1.getPV(3);
            schritte = schritte + "\\[" + v1.druckeDaten("a") + ", \\quad" + " \\lambda = " + ZA(i) + "\\]";
            schritte = schritte + "\\[\\lambda \\cdot \\vec{a} =  \\left(\\begin{array}{c} " +zahlenKlammer( ZA(i) )+ "\\cdot" +zahlenKlammer( ZA(v1.getPV(1))) + " \\\\ " +zahlenKlammer( ZA(i)) + "\\cdot" + zahlenKlammer(ZA(v1.getPV(2))) + " \\\\ " + zahlenKlammer(ZA(i)) + "\\cdot" +zahlenKlammer( ZA(v1.getPV(3))) + " \\end{array}\\right) \\]";
            v = new Vektor(x, y, z, istRichtig, "", keineLoesung);
            schritte = schritte + "\\[" + v.druckeDaten("c") + "\\]";
            buffer.schrittListeAdd(schritte);
            return v;
        }
    }
    // gebraucht
    public Vektor lambdaMalB(Vektor v1, double i) {
        buffer.schrittListeLeeren();
        Vektor v;
        String schritte = "\\[\\lambda \\cdot \\vec{b} = \\vec{c} \\]";
        double x = 0, y = 0, z = 0;
        if (v1.grosse == 2) {
            x = i * v1.getPV(1);
            y = i * v1.getPV(2);
            schritte = schritte + "\\[" + v1.druckeDaten("b") + "\\lambda = " + ZA(i) + "\\]";
            schritte = schritte + "\\[\\lambda \\cdot \\vec{b} =  \\left(\\begin{array}{c} " +zahlenKlammer( ZA(i) )+ "\\cdot" +zahlenKlammer( ZA(v1.getPV(1))) + " \\\\ " +zahlenKlammer( ZA(i) )+ "\\cdot" +zahlenKlammer( ZA(v1.getPV(2))) + " \\end{array}\\right) \\]";
            v = new Vektor(x, y, istRichtig, "", keineLoesung);
            schritte = schritte + "\\[" + v.druckeDaten("c") + "\\]";
            buffer.schrittListeAdd(schritte);
            return v;
        } else {
            x = i * v1.getPV(1);
            y = i * v1.getPV(2);
            z = i * v1.getPV(3);
            schritte = schritte + "\\[" + v1.druckeDaten("b") + "\\]";
            schritte = schritte + "\\[ \\lambda = " + ZA(i) + "\\]";
            schritte = schritte + "\\[\\lambda \\cdot \\vec{b} =  \\left(\\begin{array}{c} " +zahlenKlammer( ZA(i)) + "\\cdot" +zahlenKlammer( ZA(v1.getPV(1))) + " \\\\ " +zahlenKlammer( ZA(i)) + "\\cdot" +zahlenKlammer( ZA(v1.getPV(2))) + " \\\\ " +zahlenKlammer( ZA(i)) + "\\cdot" +zahlenKlammer( ZA(v1.getPV(3))) + " \\end{array}\\right) \\]";
            v = new Vektor(x, y, z, istRichtig, "", keineLoesung);
            schritte = schritte + "\\[" + v.druckeDaten("c") + "\\]";
            buffer.schrittListeAdd(schritte);
            return v;
        }
    }
    // gebraucht
    public double multiplikation(Vektor v1, Vektor v2, boolean loeschen) {
        double d;
        double x = 0, y = 0, z = 0;
        buffer.schrittListeLeeren();
        String schritte = "\\[\\vec{a} \\cdot \\vec{b} = \\lambda \\]";
        schritte = schritte + "\\[" + v1.druckeDaten("a") + "\\quad ," + v2.druckeDaten("b") + "\\]";
        if (v1.grosse == 2 && v2.grosse == 2) {
            x = v1.getPV(1) * v2.getPV(1);
            y = v1.getPV(2) * v2.getPV(2);
            d = (x + y);
            schritte = schritte + "\\[\\vec{a} \\cdot \\vec{b} = \\left ( " +zahlenKlammer( ZA(v1.getPV(1))) + " \\cdot " +zahlenKlammer( ZA(v2.getPV(1))) + " \\right ) + \\left ( " +zahlenKlammer( ZA(v1.getPV(2))) + " \\cdot " + zahlenKlammer(ZA(v2.getPV(2))) + " \\right ) = " + ZA(d) + "\\]";
            buffer.schrittListeAdd(schritte);
            if (loeschen)
                buffer.schrittListeLeeren();
            return d;
        } else {
            x = v1.getPV(1) * v2.getPV(1);
            y = v1.getPV(2) * v2.getPV(2);
            z = v1.getPV(3) * v2.getPV(3);
            d = (x + y + z);
            schritte = schritte + "\\[\\vec{a} \\cdot \\vec{b} = \\left ( " + zahlenKlammer(ZA(v1.getPV(1))) + " \\cdot " + zahlenKlammer(ZA(v2.getPV(1))) + " \\right ) + \\left ( " + zahlenKlammer(ZA(v1.getPV(2))) + " \\cdot " + zahlenKlammer(ZA(v2.getPV(2))) + " \\right ) + \\left ( " +zahlenKlammer( ZA(v1.getPV(3))) + " \\cdot " +zahlenKlammer( ZA(v2.getPV(3))) + " \\right )  = " + ZA(d) + " \\]";
            buffer.schrittListeAdd(schritte);
            if (loeschen)
                buffer.schrittListeLeeren();
            return d;
        }

    }
    // gebraucht
    public double vektorLaengeA(Vektor v, boolean loschen) {
        if (!loschen)
            buffer.schrittListeLeeren();
        String schritte = "";
        double x, erg, y, z = 0;
        if (v.grosse == 2) {
            x = v.getPV(1);
            y = v.getPV(2);
            schritte = schritte + "\\[" + v.druckeDaten("a") + "\\]";
            if (loschen)
                schritte = "";
            erg = Math.sqrt((x * x) + (y * y));
            schritte = schritte + "\\[ \\left | \\vec{a} \\right | = \\sqrt{{(" + ZA(x) + ")}^2 + {(" + ZA(y) + ")}^2} = " + ZA(erg) + " \\]";
            buffer.schrittListeAdd(schritte);
            return erg;
        } else {
            x = v.getPV(1);
            y = v.getPV(2);
            z = v.getPV(3);
            schritte = schritte + "\\[" + v.druckeDaten("a") + "\\]";
            if (loschen)
                schritte = "";
            erg = Math.sqrt((x * x) + (y * y) + (z * z));
            schritte = schritte + "\\[ \\left | \\vec{a} \\right | = \\sqrt{{(" + ZA(x) + ")}^2 + {(" + ZA(y) + ")}^2 + {(" + ZA(z) + ")}^2} = " + ZA(erg) + " \\]";
            buffer.schrittListeAdd(schritte);
            return erg;
        }
    }
    // gebraucht
    public double vektorLaengeB(Vektor v, boolean loschen) {
        if (!loschen)
            buffer.schrittListeLeeren();
        String schritte = "";
        double x, erg, y, z = 0;
        if (v.grosse == 2) {
            x = v.getPV(1);
            y = v.getPV(2);
            schritte = schritte + "\\[" + v.druckeDaten("b") + "\\]";
            if (loschen)
                schritte = "";
            erg = Math.sqrt((x * x) + (y * y));
            schritte = schritte + "\\[ \\left | \\vec{b} \\right | = \\sqrt{{(" + ZA(x) + ")}^2 + {(" + ZA(y) + ")}^2} = " + ZA(erg) + " \\]";
            buffer.schrittListeAdd(schritte);
            return erg;
        } else {
            x = v.getPV(1);
            y = v.getPV(2);
            z = v.getPV(3);
            schritte = schritte + "\\[" + v.druckeDaten("b") + "\\]";
            erg = Math.sqrt((x * x) + (y * y) + (z * z));
            if (loschen)
                schritte = "";
            schritte = schritte + "\\[ \\left | \\vec{b} \\right | = \\sqrt{{(" + ZA(x) + ")}^2 + {(" + ZA(y) + ")}^2 + {(" + ZA(z) + ")}^2} = " + ZA(erg) + " \\]";
            buffer.schrittListeAdd(schritte);
            return erg;
        }
    }
    // gebraucht
    public double linearabhaengigkeit(Vektor va, Vektor vb, Vektor vc) {
        double[][] feld = new double[3][3];

        feld[0][0] = va.getPV(1);
        feld[1][0] = va.getPV(2);
        feld[2][0] = va.getPV(3);

        feld[0][1] = vb.getPV(1);
        feld[1][1] = vb.getPV(2);
        feld[2][1] = vb.getPV(3);

        feld[0][2] = vc.getPV(1);
        feld[1][2] = vc.getPV(2);
        feld[2][2] = vc.getPV(3);

        double determinante = mo.det(feld);
        buffer.schrittListeLeeren();
        buffer.schrittListeAdd("\\[ \\mathrm{Es \\quad wird \\quad die \\quad Determinante \\\\ untersucht, \\quad die \\quad sich \\quad aus \\quad den \\\\ drei \\quad Vektoren \\quad ergibt.  } \\]");
        buffer.schrittListeAdd("\\[" + va.druckeDaten("a") + "," + vb.druckeDaten("b") + "," + vc.druckeDaten("c") + "\\]");
        buffer.schrittListeAdd("\\[ det(A) = \\begin{vmatrix} {\\color{Red} { \\vec{a} } }    &  {\\color{Red} { \\vec{b} } }  &  {\\color{Red} { \\vec{c} } } \\\\" + ZA(va.getPV(1)) + " & " + ZA(vb.getPV(1)) + " & " + ZA(vc.getPV(1)) + " \\\\ " +
                " " + ZA(va.getPV(2)) + " & " + ZA(vb.getPV(2)) + " & " + ZA(vc.getPV(2)) + " \\\\ "+ ZA(va.getPV(3)) + " & " + ZA(vb.getPV(3)) + " & " + ZA(vc.getPV(3)) +"\\end{vmatrix} \\]");
        mo.determinanteA(mo.arrayZuMatrix(feld),true);
        if (determinante == 0){
            buffer.schrittListeAdd("\\[ \\mathrm{Die \\quad Vektoren \\quad sind \\quad {\\color{Red} {linear \\quad abhängig} }, \\\\ da \\quad die \\quad det(A) = 0 \\quad ist.} \\]");
        }else {
            buffer.schrittListeAdd("\\[ \\mathrm{Die \\quad Vektoren \\quad sind \\quad {\\color{Red} {linear \\quad unabhängig} }, \\\\ da \\quad die \\quad det(A) \\neq 0\\quad ist.} \\]");
        }
        return determinante;
    }
    // gebraucht
    public double winkelBerechnen(Vektor v1, Vektor v2) {
        buffer.schrittListeLeeren();
        double letzteErgebniss = 0;
        double vL1;
        double vL2;
        String schritte = "";
        double erg, zaehler, nenner, ersteWurzel, zweiteWurzel;
        Vektor op = null;
        schritte = schritte + "\\[ \\cos \\varphi =\\frac{\\vec{a}\\cdot \\vec{b}}{\\left | \\vec{a} \\right |\\cdot \\left | \\vec{b} \\right |} \\Rightarrow  \\varphi = \\arccos   \\left ( \\frac{\\vec{a}\\cdot \\vec{b}}{\\left | \\vec{a} \\right |\\cdot \\left | \\vec{b} \\right |} \\right ) \\]";
        if (v1.grosse == 2) {
            double v = multiplikation(v1, v2, true);
            schritte = schritte + "\\[" + v1.druckeDaten("a") + "\\quad ," + v2.druckeDaten("b") + "\\]";
            schritte = schritte + "\\[\\vec{a} \\cdot \\vec{b} = \\left ( " + zahlenKlammer(ZA(v1.getPV(1))) + "\\cdot " +zahlenKlammer( ZA(v2.getPV(1))) + " \\right ) + \\left ( " +zahlenKlammer( ZA(v1.getPV(2))) + " \\cdot " +zahlenKlammer( ZA(v2.getPV(2))) + " \\right ) \\ = " + ZA(v) + " \\]";
            buffer.schrittListeAdd(schritte);
            vL1 = vektorLaengeA(v1, true);
            vL2 = vektorLaengeB(v2, true);

            if (sindDieVektorenGleich(v1, v2) && (v != 0 && vL1 != 0 && vL2 != 0)) {
                erg = 1;
                letzteErgebniss = Math.toDegrees(Math.acos(erg));
                buffer.schrittListeAdd("\\[ \\cos \\varphi =\\frac{" + ZA(v) + "}{" +zahlenKlammer( ZA(vL1)) + "\\cdot " + zahlenKlammer(ZA(vL2)) + "} = " + ZA(erg) + "\\\\ \\varphi = \\arccos  \\left ( " + ZA(erg) + " \\right ) = " + ZA(letzteErgebniss) + "° \\]");
            } else {
                erg = v /(vL1 * vL2);
                letzteErgebniss = Math.toDegrees(Math.acos(erg));
                buffer.schrittListeAdd("\\[ \\cos \\varphi =\\frac{" + ZA(v) + "}{" + zahlenKlammer(ZA(vL1)) + "\\cdot " + zahlenKlammer(ZA(vL2)) + "} = " + ZA(erg) + "\\\\ \\varphi = \\arccos  \\left ( " + ZA(erg) + " \\right ) = " + ZA(letzteErgebniss) + "° \\]");
            }

            return letzteErgebniss;
        } else {
            double v = multiplikation(v1, v2, true);
            schritte = schritte + "\\[" + v1.druckeDaten("a") + "\\quad ," + v2.druckeDaten("b") + "\\]";
            schritte = schritte + "\\[\\vec{a} \\cdot \\vec{b} = \\left ( " +zahlenKlammer( ZA(v1.getPV(1))) + "\\cdot " +zahlenKlammer( ZA(v2.getPV(1))) + " \\right ) + \\left ( " +zahlenKlammer( ZA(v1.getPV(2))) + " \\cdot " +zahlenKlammer( ZA(v2.getPV(2))) + " \\right ) + \\left ( " +zahlenKlammer( ZA(v1.getPV(3))) + " \\cdot " +zahlenKlammer( ZA(v2.getPV(3))) + " \\right ) \\\\ = " + ZA(v) + " \\]";
            buffer.schrittListeAdd(schritte);
            vL1 = vektorLaengeA(v1, true);
            vL2 = vektorLaengeB(v2, true);

            if (sindDieVektorenGleich(v1, v2) && (v != 0 && vL1 != 0 && vL2 != 0)) {
                erg = 1;
                letzteErgebniss = Math.toDegrees(Math.acos(erg));
                buffer.schrittListeAdd("\\[ \\cos \\varphi =\\frac{" + ZA(v) + "}{" + zahlenKlammer(ZA(vL1)) + "\\cdot " +zahlenKlammer( ZA(vL2)) + "} = " + ZA(erg) + "\\\\ \\varphi = \\arccos  \\left ( " + ZA(erg) + " \\right ) = " + ZA(letzteErgebniss) + "° \\]");
            } else {
                erg = v / (vL1 * vL2);
                letzteErgebniss = Math.toDegrees(Math.acos(erg));
                buffer.schrittListeAdd("\\[ \\cos \\varphi =\\frac{" + ZA(v) + "}{" +zahlenKlammer( ZA(vL1)) + "\\cdot " + zahlenKlammer(ZA(vL2)) + "} = " + ZA(erg) + "\\\\ \\varphi = \\arccos   \\left ( " + ZA(erg) + " \\right ) = " + ZA(letzteErgebniss) + "° \\]");
            }
            return letzteErgebniss;
        }
    }

    public Vektor vektorenErzeugen(int zeile, double x, double y, double z) {
        if (zeile == 2) {
            return new Vektor(x, y, istRichtig, "", keineLoesung);
        } else {
            return new Vektor(x, y, z, istRichtig, "", keineLoesung);
        }
    }

    // gebraucht
    public void kollinearVonVektoren(Vektor v1, Vektor v2) {
        buffer.schrittListeLeeren();
        double x, y, z = 0;
        String schritte = "";
        x = v1.getPV(1) / v2.getPV(1);
        y = v1.getPV(2) / v2.getPV(2);
        schritte = "\\[" + v1.druckeDaten("a") + "\\quad ," + v2.druckeDaten("b") + "\\]" + "\\[ \\mathrm{Die \\quad Vektoren \\quad wären \\quad kollinear, \\\\ dann \\quad sollte \\quad folgendes \\quad gelten } \\\\ \\vec{a}=\\kappa \\cdot \\vec{b} \\\\ " + ZA(v1.getPV(1)) + "=\\kappa \\cdot (" + ZA(v2.getPV(1)) + ") \\\\ " + ZA(v1.getPV(2)) + "=\\kappa \\cdot (" + ZA(v2.getPV(2)) + ") \\]";

        if (x == y || (v1.getPV(1) == 0 && v2.getPV(1) == 0 && v1.getPV(2) == 0 && v2.getPV(2) == 0)) {
            if (Double.isNaN(x))
                x = 0;
            schritte = schritte + "\\[ \\mathrm{Die \\quad Vektoren \\quad sind \\quad {\\color{Red}  {kollinear}}, \\\\ da \\quad  {\\color{Red}  {\\kappa = " + ZA(x) + "}} \\quad beide \\\\ Gleichungen \\quad erfült. } \\]";
            buffer.schrittListeAdd(schritte);
        } else {
            schritte = schritte + "\\[ \\mathrm{Die \\quad Vektoren \\quad sind \\quad {\\color{Red}  {nicht \\quad kollinear}}, \\\\ da \\quad kein \\quad \\kappa \\quad existiert, \\\\ das  \\quad beide \\quad Gleichungen \\quad erfüllt.} \\]";
            buffer.schrittListeAdd(schritte);
        }
    }
    // gebraucht
    public boolean istSenkrecht(Vektor v1, Vektor v2) {
        double v = winkelBerechnen(v1, v2);
        if (v == 90) {
            buffer.schrittListeAdd("\\[Die \\quad Vektoren \\\\  stehen \\quad {\\color{Red}  {senkrecht}} \\quad aufeinander,\\\\ weil \\quad der \\quad Winkel \\quad zwischen \\\\  ihnen \\quad 90° \\quad ist.   \\]");
            return true;
        } else
            buffer.schrittListeAdd("\\[ Die \\quad Vektoren \\\\ stehen  \\quad {\\color{Red}  {nicht}} \\quad {\\color{Red}  {senkrecht}}\\quad aufeinnader ,\\\\ weil \\quad der \\quad Winkel \\quad zwischen \\\\ihnen \\quad nicht \\quad 90° \\quad ist. \\]");
        return false;
    }


    // gebraucht
    public Vektor aKreuzB(Vektor v1, Vektor v2) {
        double x, y, z = 0;
        String xx = "";
        String yy = "";
        String zz = "";
        String schritte = "";
        buffer.schrittListeLeeren();
        buffer.schrittListeAdd("\\[\\vec{a} \\quad X \\quad \\vec{b} = \\vec{c} \\]");
        buffer.schrittListeAdd("\\[" + buffer.vListeZurueckgebenABC(1).druckeDaten("a") + "\\quad ," + buffer.vListeZurueckgebenABC(2).druckeDaten("b") + "\\]");
        x = (v1.getPV(2) * v2.getPV(3)) - (v1.getPV(3) * v2.getPV(2));
        xx = zahlenKlammer( ZA(v1.getPV(2))) + " \\cdot " +zahlenKlammer( ZA(v2.getPV(3))) + "- " +zahlenKlammer( ZA(v1.getPV(3))) + " \\cdot" + zahlenKlammer(ZA(v2.getPV(2)));

        y = (v1.getPV(3) * v2.getPV(1)) - (v1.getPV(1) * v2.getPV(3));

        yy = zahlenKlammer( ZA(v1.getPV(3))) + " \\cdot " +zahlenKlammer( ZA(v2.getPV(1))) + " -  " +zahlenKlammer( ZA(v1.getPV(1))) + " \\cdot" + zahlenKlammer(ZA(v2.getPV(3)));

        z = (v1.getPV(1) * v2.getPV(2)) - (v1.getPV(2) * v2.getPV(1));
        zz = zahlenKlammer(ZA(v1.getPV(1))) + " \\cdot " +zahlenKlammer( ZA(v2.getPV(2))) + "-" + zahlenKlammer(ZA(v1.getPV(2))) + " \\cdot" + zahlenKlammer(ZA(v2.getPV(1)));

        schritte = "\\[ \\vec{a} \\quad X \\quad \\vec{b} = \\left( \\begin{array}{c} " + xx + "\\\\" + yy + "\\\\" + zz + "\\end{array}\\right) \\]";

        vektor = new Vektor(x, y, z, istRichtig, "", keineLoesung);
        buffer.schrittListeAdd(schritte);
        buffer.schrittListeAdd("\\[" + vektor.druckeDaten("c") + "\\]");
        return vektor;
    }
    // gebraucht
    public Vektor bKreuzA(Vektor v1, Vektor v2) {
        double x, y, z = 0;
        String xx = "";
        String yy = "";
        String zz = "";
        String schritte = "";
        buffer.schrittListeLeeren();
        buffer.schrittListeAdd("\\[\\vec{b} \\quad X \\quad \\vec{a} = \\vec{c} \\]");
        buffer.schrittListeAdd("\\[" + buffer.vListeZurueckgebenABC(1).druckeDaten("a") + "\\quad ," + buffer.vListeZurueckgebenABC(2).druckeDaten("b") + "\\]");
        x = (v1.getPV(2) * v2.getPV(3)) - (v1.getPV(3) * v2.getPV(2));
        xx = zahlenKlammer( ZA(v1.getPV(2))) + " \\cdot " +zahlenKlammer(ZA(v2.getPV(3))) + "- " +zahlenKlammer( ZA(v1.getPV(3))) + " \\cdot" + zahlenKlammer(ZA(v2.getPV(2)));

        y = (v1.getPV(3) * v2.getPV(1)) - (v1.getPV(1) * v2.getPV(3));
        yy = zahlenKlammer( ZA(v1.getPV(3))) + " \\cdot " +zahlenKlammer(ZA(v2.getPV(1))) + " -  " +zahlenKlammer( ZA(v1.getPV(1))) + " \\cdot" + zahlenKlammer(ZA(v2.getPV(3)));

        z = (v1.getPV(1) * v2.getPV(2)) - (v1.getPV(2) * v2.getPV(1));
        zz = zahlenKlammer(ZA(v1.getPV(1))) + " \\cdot " +zahlenKlammer( ZA(v2.getPV(2))) + "-" + zahlenKlammer(ZA(v1.getPV(2))) + " \\cdot" + zahlenKlammer(ZA(v2.getPV(1)));

        schritte = "\\[ \\vec{b} \\quad X \\quad \\vec{a} = \\left( \\begin{array}{c} " + xx + "\\\\" + yy + "\\\\" + zz + "\\end{array}\\right) \\]";

        vektor = new Vektor(x, y, z, istRichtig, "", keineLoesung);
        buffer.schrittListeAdd(schritte);
        buffer.schrittListeAdd("\\[" + vektor.druckeDaten("c") + "\\]");
        return vektor;
    }
    public boolean sindDieVektorenGleich(Vektor v1, Vektor v2) {
        if (v1.grosse == 2) {
            if (v1.getPV(1) == v2.getPV(1) && v1.getPV(2) == v2.getPV(2)) {
                return true;
            } else
                return false;
        } else {
            if (v1.getPV(1) == v2.getPV(1) && v1.getPV(2) == v2.getPV(2) && v1.getPV(3) == v2.getPV(3)) {
                return true;
            } else
                return false;
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
        return vorZahln + "," + nachZahlen;
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
    public String zahlenKlammer(String s) {
        char zahl = s.charAt(0);
        if (zahl == '-') {
            return "(" + s + ")";
        } else {
            return s;
        }
    }
}

