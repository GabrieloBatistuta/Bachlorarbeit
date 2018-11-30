package Logik;

import Datenhaltung.Buffer;

/**
 * Created by KOJAR on 04.01.2016.
 */
public class MatrixOperationen {
    private Matrix matrix = null;
    private Buffer buffer;
    private double skalarZahl;
    private boolean istRichtig = true;
    private boolean keineLoesung = false;
    private double determinanteZahl;

    public MatrixOperationen() {
        buffer = buffer.getInstance();
    }

    public Matrix matrixErzeugen(double z, double s) {
        matrix = new Matrix((int) z, (int) s, istRichtig, "", keineLoesung);
        return matrix;
    }

    // gebraucht
    public Matrix bTransponieren(Matrix m, boolean loeschen) {
        if (loeschen == true) {
            buffer.schrittListeLeeren();
        }
        double ein;
        buffer.schrittListeAdd("\\[ B^T = C \\]" + "\\[" + m.druckeDaten("B") + "\\]");
        buffer.schrittListeAdd("\\[ \\mathrm{Die \\quad transponierte \\quad Matrix {\\displaystyle \\quad B^{T}}\\\\ \\quad ergibt \\quad sich \\quad also \\quad dadurch\\\\ das \\quad Vertauschen \\quad von \\\\ Zeilen \\quad und \\quad Spalten \\quad der \\quad Ausgangsmatrix \\quad {\\displaystyle B}. } \\]");

        matrix = new Matrix(m.getSpalten(), m.getZeile(), istRichtig, "", keineLoesung);
        for (int i = 0; i < m.getZeile(); i++) {
            for (int j = 0; j < m.getSpalten(); j++) {
                ein = m.getDoubleIndexPosition(i, j);
                matrix.setDoubleIndexPosition(j, i, ein);
            }
        }
        buffer.schrittListeAdd("\\[" + matrix.druckeDaten("C") + "\\]");
        return matrix;
    }

    // gebraucht
    public Matrix aTransponieren(Matrix m, boolean loeschen) {
        if (loeschen == true) {
            buffer.schrittListeLeeren();
        }
        double ein;
        buffer.schrittListeAdd("\\[ A^T = C \\]" + "\\[" + m.druckeDaten("A") + "\\]");
        buffer.schrittListeAdd("\\[ \\mathrm{Die \\quad transponierte \\quad Matrix {\\displaystyle \\quad A^{T}}\\\\ \\quad ergibt \\quad sich \\quad also \\quad dadurch\\\\ das \\quad Vertauschen \\quad von \\\\ Zeilen \\quad und \\quad Spalten \\quad der \\quad Ausgangsmatrix  \\quad {\\displaystyle A}. } \\]");
        matrix = new Matrix(m.getSpalten(), m.getZeile(), istRichtig, "", keineLoesung);
        for (int i = 0; i < m.getZeile(); i++) {
            for (int j = 0; j < m.getSpalten(); j++) {
                ein = m.getDoubleIndexPosition(i, j);
                matrix.setDoubleIndexPosition(j, i, ein);
            }
        }
        buffer.schrittListeAdd("\\[" + matrix.druckeDaten("C") + "\\]");
        return matrix;
    }

    // gebraucht
    public Matrix transponieren(Matrix m) {
        double ein;
        matrix = new Matrix(m.getSpalten(), m.getZeile(), istRichtig, "", keineLoesung);
        for (int i = 0; i < m.getZeile(); i++) {
            for (int j = 0; j < m.getSpalten(); j++) {
                ein = m.getDoubleIndexPosition(i, j);
                matrix.setDoubleIndexPosition(j, i, ein);
            }
        }
        return matrix;
    }

    // gebraucht
    public void istAsymetrisch(Matrix mm) {
        int hilfe1 = 0;
        int hilfe = 0;
        matrix = aTransponieren(mm, true);
        buffer.schrittListeLeeren();
        buffer.schrittListeAdd("\\[ \\mathrm{Wenn \\quad die \\quad Matrix \\quad A \\\\  symmetrisch \\quad ist,\\quad dann \\\\ soll \\quad A=A^T \\quad gelten } \\]");
        buffer.schrittListeAdd("\\[" + mm.druckeDaten("A") + "\\]");
        buffer.schrittListeAdd("\\[" + matrix.druckeDaten("A^T") + "\\]");
        hilfe1 = (mm.getSpalten() * mm.getSpalten());
        matrix = mm;
        for (int i = 0; i < mm.getZeile(); i++) {
            for (int j = 0; j < mm.getSpalten(); j++) {
                if (stringToDouble(mm.getStringIndexPosition(i, j)) == stringToDouble(matrix.getStringIndexPosition(j, i))) {
                    hilfe++;
                }
            }
        }
        if (hilfe1 == hilfe) {
            buffer.schrittListeAdd("\\[ \\mathrm{Die \\quad Matrix \\quad A \\\\ {\\color{Red}  {ist \\quad  symmetrisch}},\\quad da \\\\ \\quad A=A^T } \\]");
        } else {
            buffer.schrittListeAdd("\\[ \\mathrm{Die \\quad Matrix \\quad A \\\\ ist \\quad {\\color{Red}  {nicht \\quad symmetrisch}},\\\\ da \\quad A \\neq A^T \\quad } \\]");
        }
    }

    // gebraucht
    public void istBsymetrisch(Matrix mm) {
        int hilfe1 = 0;
        int hilfe = 0;
        matrix = aTransponieren(mm, true);
        buffer.schrittListeLeeren();
        buffer.schrittListeAdd("\\[ \\mathrm{Wenn \\quad die \\quad Matrix \\quad B \\\\  symmetrisch \\quad ist,\\quad dann \\\\ soll \\quad B=B^T \\quad gelten } \\]");
        buffer.schrittListeAdd("\\[" + mm.druckeDaten("B") + "\\]");
        buffer.schrittListeAdd("\\[" + matrix.druckeDaten("B^T") + "\\]");
        hilfe1 = (mm.getSpalten() * mm.getSpalten());
        matrix = mm;
        for (int i = 0; i < mm.getZeile(); i++) {
            for (int j = 0; j < mm.getSpalten(); j++) {
                if (stringToDouble(mm.getStringIndexPosition(i, j)) == stringToDouble(matrix.getStringIndexPosition(j, i))) {
                    hilfe++;
                }
            }
        }
        if (hilfe1 == hilfe) {
            buffer.schrittListeAdd("\\[ \\mathrm{Die \\quad Matrix \\quad B \\\\ {\\color{Red}  {ist \\quad  symmetrisch}},\\quad da \\\\ \\quad B=B^T } \\]");
        } else {
            buffer.schrittListeAdd("\\[ \\mathrm{Die \\quad Matrix \\quad B \\\\ ist \\quad {\\color{Red}  {nicht \\quad symmetrisch}},\\\\ da \\quad B \\neq B^T \\quad } \\]");
        }
    }

    // gebraucht
    public Matrix AplusB(Matrix m1, Matrix m2) {
        String zeilen = "\\\\";
        String spalten = "&";
        String zeichnen = "";
        String zahlen = "";
        String textSize = "";
        String zahl1 = "";
        String zahl2 = "";
        matrix = new Matrix(m1.getZeile(), m1.getSpalten(), istRichtig, "", keineLoesung);

        buffer.schrittListeLeeren();
        buffer.schrittListeAdd("\\[A + B = C \\]");
        buffer.schrittListeAdd("\\[" + buffer.mListeZurueckgebenABC(1).druckeDaten("A") + "\\]");
        buffer.schrittListeAdd("\\[" + buffer.mListeZurueckgebenABC(2).druckeDaten("B") + "\\]");
        for (int i = 0; i < m1.getZeile(); i++) {
            for (int j = 0; j < m1.getSpalten(); j++) {
                matrix.setDoubleIndexPosition(i, j, (m1.getDoubleIndexPosition(i, j) * 1000 + m2.getDoubleIndexPosition(i, j) * 1000) / 1000);


                if (m1.getDoubleIndexPosition(i, j) < 0)
                    zahl1 = "\\left (" + ZA(m1.getDoubleIndexPosition(i, j)) + "  \\right )";
                else
                    zahl1 = ZA(m1.getDoubleIndexPosition(i, j));

                if (m2.getDoubleIndexPosition(i, j) < 0)
                    zahl2 = "\\left ( " + ZA(m2.getDoubleIndexPosition(i, j)) + " \\right )";
                else
                    zahl2 = ZA(m2.getDoubleIndexPosition(i, j));

                zahlen = zahl1 + "+" + zahl2;

                if (j + 1 < m1.getSpalten()) {
                    zeichnen = zeichnen + zahlen + spalten;
                } else {
                    zeichnen = zeichnen + zahlen;
                }
            }
            zeichnen = zeichnen + zeilen;
        }
        String matrize = "\\[ A+B= \\begin{bmatrix} " + zeichnen + " \\end{bmatrix} \\]";
        buffer.schrittListeAdd(matrize);
        buffer.schrittListeAdd("\\[" + matrix.druckeDaten("C") + "\\]");
        return matrix;
    }

    // gebraucht
    public Matrix AminusB(Matrix m1, Matrix m2) {
        String zeilen = "\\\\";
        String spalten = "&";
        String zeichnen = "";
        String zahlen = "";
        String zahl1 = "";
        String zahl2 = "";

        buffer.schrittListeLeeren();
        buffer.schrittListeAdd("\\[A - B = C \\]");
        buffer.schrittListeAdd("\\[" + buffer.mListeZurueckgebenABC(1).druckeDaten("A") + "\\]");
        buffer.schrittListeAdd("\\[" + buffer.mListeZurueckgebenABC(2).druckeDaten("B") + "\\]");
        matrix = new Matrix(m1.getZeile(), m1.getSpalten(), istRichtig, "", keineLoesung);
        for (int i = 0; i < m1.getZeile(); i++) {
            for (int j = 0; j < m1.getSpalten(); j++) {
                matrix.setDoubleIndexPosition(i, j, (m1.getDoubleIndexPosition(i, j) * 1000 - m2.getDoubleIndexPosition(i, j) * 1000) / 1000);

                if (m1.getDoubleIndexPosition(i, j) < 0)
                    zahl1 = "\\left (" + ZA(m1.getDoubleIndexPosition(i, j)) + "  \\right )";
                else
                    zahl1 = ZA(m1.getDoubleIndexPosition(i, j));

                if (m2.getDoubleIndexPosition(i, j) < 0)
                    zahl2 = "\\left ( " + ZA(m2.getDoubleIndexPosition(i, j)) + " \\right )";
                else
                    zahl2 = ZA(m2.getDoubleIndexPosition(i, j));

                zahlen = zahl1 + "-" + zahl2;

                if (j + 1 < m1.getSpalten()) {
                    zeichnen = zeichnen + zahlen + spalten;
                } else {
                    zeichnen = zeichnen + zahlen;
                }
            }
            zeichnen = zeichnen + zeilen;
        }
        String matrize = "\\[ A - B = \\begin{bmatrix} " + zeichnen + " \\end{bmatrix}  \\]";
        buffer.schrittListeAdd(matrize);
        buffer.schrittListeAdd("\\[" + matrix.druckeDaten("C") + "\\]");
        return matrix;
    }

    // gebraucht
    public Matrix BminusA(Matrix m1, Matrix m2) {
        String zeilen = "\\\\";
        String spalten = "&";
        String zeichnen = "";
        String zahlen = "";
        String zahl1 = "";
        String zahl2 = "";

        buffer.schrittListeLeeren();
        buffer.schrittListeAdd("\\[B - A = C \\]");
        buffer.schrittListeAdd("\\[" + buffer.mListeZurueckgebenABC(1).druckeDaten("A") + "\\]");
        buffer.schrittListeAdd("\\[" + buffer.mListeZurueckgebenABC(2).druckeDaten("B") + "\\]");
        matrix = new Matrix(m1.getZeile(), m1.getSpalten(), istRichtig, "", keineLoesung);
        for (int i = 0; i < m1.getZeile(); i++) {
            for (int j = 0; j < m1.getSpalten(); j++) {
                matrix.setDoubleIndexPosition(i, j, (m1.getDoubleIndexPosition(i, j) * 1000 - m2.getDoubleIndexPosition(i, j) * 1000) / 1000);

                if (m1.getDoubleIndexPosition(i, j) < 0)
                    zahl1 = "\\left (" + ZA(m1.getDoubleIndexPosition(i, j)) + "  \\right )";
                else
                    zahl1 = ZA(m1.getDoubleIndexPosition(i, j));

                if (m2.getDoubleIndexPosition(i, j) < 0)
                    zahl2 = "\\left ( " + ZA(m2.getDoubleIndexPosition(i, j)) + " \\right )";
                else
                    zahl2 = ZA(m2.getDoubleIndexPosition(i, j));

                zahlen = zahl1 + "-" + zahl2;

                if (j + 1 < m1.getSpalten()) {
                    zeichnen = zeichnen + zahlen + spalten;
                } else {
                    zeichnen = zeichnen + zahlen;
                }
            }
            zeichnen = zeichnen + zeilen;
        }
        String matrize = "\\[ B - A = \\begin{bmatrix} " + zeichnen + " \\end{bmatrix}  \\]";
        buffer.schrittListeAdd(matrize);
        buffer.schrittListeAdd("\\[" + matrix.druckeDaten("C") + "\\]");
        return matrix;
    }

    // gebraucht
    public Matrix AmalB(Matrix m1, Matrix m2) {
        String zeilen = "\\\\";
        String spalten = "&";
        String zeichnen = "";
        String zeichnen2 = "";
        buffer.schrittListeLeeren();
        buffer.schrittListeAdd("\\[A \\cdot B = C \\]");
        buffer.schrittListeAdd("\\[" + buffer.mListeZurueckgebenABC(1).druckeDaten("A") + "\\]");
        buffer.schrittListeAdd("\\[" + buffer.mListeZurueckgebenABC(2).druckeDaten("B") + "\\]");
        double speicher1 = 0;
        matrix = new Matrix(m1.getZeile(), m2.getSpalten(), istRichtig, "", keineLoesung);

        String zahl1 = "";
        String zahl2 = "";

        for (int i = 0; i < m1.getZeile(); i++) {
            for (int z = 0; z < m2.getSpalten(); z++) {
                for (int j = 0; j < m1.getSpalten(); j++) {
                    speicher1 = speicher1 + m1.getDoubleIndexPosition(i, j) * m2.getDoubleIndexPosition(j, z);
                    if (m1.getDoubleIndexPosition(i, j) < 0)
                        zahl1 = "\\left (" + ZA(m1.getDoubleIndexPosition(i, j)) + "  \\right )";
                    else
                        zahl1 = ZA(m1.getDoubleIndexPosition(i, j));

                    if (m2.getDoubleIndexPosition(j, z) < 0)
                        zahl2 = "\\left ( " + ZA(m2.getDoubleIndexPosition(j, z)) + " \\right )";
                    else
                        zahl2 = ZA(m2.getDoubleIndexPosition(j, z));

                    zeichnen2 = zeichnen2 + zahl1 + "\\cdot" + zahl2;

                    if (j + 1 < m1.getSpalten())
                        zeichnen2 = zeichnen2 + "+";
                }
                matrix.setDoubleIndexPosition(i, z, speicher1);
                zeichnen = zeichnen + zeichnen2;
                zeichnen2 = "";
                speicher1 = 0;
                if (z + 1 < m2.getSpalten()) {
                    zeichnen = zeichnen + spalten;
                }
            }
            zeichnen = zeichnen + zeilen;
        }
        String matrize = "\\[A \\cdot B =  \\begin{bmatrix} " + zeichnen + " \\end{bmatrix}  \\]";
        buffer.schrittListeAdd(matrize);
        buffer.schrittListeAdd("\\[" + matrix.druckeDaten("C") + "\\]");
        return matrix;
    }

    // gebraucht
    public Matrix BmalA(Matrix m2, Matrix m1) {
        String zeilen = "\\\\";
        String spalten = "&";
        String zeichnen = "";
        String zeichnen2 = "";
        buffer.schrittListeLeeren();
        buffer.schrittListeAdd("\\[B \\cdot A = C \\]");
        buffer.schrittListeAdd("\\[" + buffer.mListeZurueckgebenABC(1).druckeDaten("A") + "\\]");
        buffer.schrittListeAdd("\\[" + buffer.mListeZurueckgebenABC(2).druckeDaten("B") + "\\]");
        double speicher1 = 0;
        matrix = new Matrix(m1.getZeile(), m2.getSpalten(), istRichtig, "", keineLoesung);

        String zahl1 = "";
        String zahl2 = "";

        for (int i = 0; i < m1.getZeile(); i++) {
            for (int z = 0; z < m2.getSpalten(); z++) {
                for (int j = 0; j < m1.getSpalten(); j++) {
                    speicher1 = speicher1 + m1.getDoubleIndexPosition(i, j) * m2.getDoubleIndexPosition(j, z);
                    if (m1.getDoubleIndexPosition(i, j) < 0)
                        zahl1 = "\\left (" + ZA(m1.getDoubleIndexPosition(i, j)) + "  \\right )";
                    else
                        zahl1 = ZA(m1.getDoubleIndexPosition(i, j));

                    if (m2.getDoubleIndexPosition(j, z) < 0)
                        zahl2 = "\\left ( " + ZA(m2.getDoubleIndexPosition(j, z)) + " \\right )";
                    else
                        zahl2 = ZA(m2.getDoubleIndexPosition(j, z));

                    zeichnen2 = zeichnen2 + zahl1 + "\\cdot" + zahl2;

                    if (j + 1 < m1.getSpalten())
                        zeichnen2 = zeichnen2 + "+";
                }
                matrix.setDoubleIndexPosition(i, z, speicher1);
                zeichnen = zeichnen + zeichnen2;
                zeichnen2 = "";
                speicher1 = 0;
                if (z + 1 < m2.getSpalten()) {
                    zeichnen = zeichnen + spalten;
                }
            }
            zeichnen = zeichnen + zeilen;
        }
        String matrize = "\\[ B \\cdot A =  \\begin{bmatrix} " + zeichnen + " \\end{bmatrix}  \\]";
        buffer.schrittListeAdd(matrize);
        buffer.schrittListeAdd("\\[" + matrix.druckeDaten("C") + "\\]");
        return matrix;
    }

    // gebraucht
    public Matrix lambdaMalA(Matrix m, double z, int vorherigeZeigen, boolean richtig) {
        buffer.schrittListeLeeren();

        double speicher2;
        this.skalarZahl = z;
        buffer.schrittListeAdd("\\[ \\lambda \\cdot A = C  \\]");
        buffer.schrittListeAdd("\\[ " + m.druckeDaten("A") + ", \\lambda =" + ZA(z) + " \\]");
        buffer.schrittListeAdd("\\[ C = " + ZA(z) + "\\cdot " + m.druckeDaten("") + " \\]");
        matrix = new Matrix(m.getZeile(), m.getSpalten(), istRichtig, "", keineLoesung);
        for (int i = 0; i < m.getZeile(); i++) {
            for (int j = 0; j < m.getSpalten(); j++) {
                speicher2 = z * m.getDoubleIndexPosition(i, j);
                matrix.setDoubleIndexPosition(i, j, speicher2);
            }
        }
        buffer.schrittListeAdd("\\[" + matrix.druckeDaten("C") + "\\]");
        return matrix;
    }

    // gebraucht
    public Matrix lambdaMalB(Matrix m, double z, int vorherigeZeigen, boolean richtig) {
        buffer.schrittListeLeeren();
        double speicher2;
        this.skalarZahl = z;
        buffer.schrittListeAdd("\\[ \\lambda \\cdot B = C  \\]");
        buffer.schrittListeAdd("\\[ " + m.druckeDaten("B") + ", \\lambda =" + ZA(z) + " \\]");
        buffer.schrittListeAdd("\\[ C = " + ZA(z) + "\\cdot " + m.druckeDaten("") + " \\]");
        matrix = new Matrix(m.getZeile(), m.getSpalten(), istRichtig, "", keineLoesung);
        for (int i = 0; i < m.getZeile(); i++) {
            for (int j = 0; j < m.getSpalten(); j++) {
                speicher2 = z * m.getDoubleIndexPosition(i, j);
                matrix.setDoubleIndexPosition(i, j, speicher2);
            }
        }
        buffer.schrittListeAdd("\\[" + matrix.druckeDaten("C") + "\\]");
        return matrix;
    }

    public double stringToDouble(String s) {
        double speicher = 0;
        try {
            speicher = Double.parseDouble(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return speicher;
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

    // gebraucht
    public double det(double[][] matrix) {
        int matrixSize = matrix.length;
        double PoderN;
        if (matrixSize == 2) {

            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else {
            int value = 0;
            int zeichnen = matrixSize;
            for (int i = 0; i < matrixSize; i++) {
                PoderN = matrix[0][i];
                PoderN = PoderN * sgn(i);
                double[][] minor = generateMinor(matrix, 0, i);
                value += (sgn(i) * matrix[0][i]) * det(minor);
            }
            return value;
        }
    }

    private int sgn(int n) {
        if (n % 2 == 0) {
            return 1;
        } else {
            return -1;
        }
    }

    // gebraucht
    public double determinanteA(Matrix mA, boolean loeschen) {
        String lambda = "";
        if (!loeschen) {
            buffer.schrittListeLeeren();
            lambda = " = \\lambda";
        }
        determinanteZahl = det((matrixZuArray(mA)));
        if (mA.getSpalten() == 3) {
            if (!loeschen) {
                buffer.schrittListeAdd("\\[ A = \\begin{bmatrix} a_{11} & a_{12}  & a_{13} \\\\ a_{21} & a_{22}  & a_{23} \\\\ a_{31} & a_{32} & a_{33} \\end{bmatrix} \\]");
                buffer.schrittListeAdd("\\[det(A) =\\left [ a_{11} \\cdot a_{22} \\cdot a_{33} + a_{12} \\cdot a_{23} \\cdot a_{31} + a_{13} \\cdot a_{21} \\cdot a_{32}\\right ] - " +
                        "\\\\ \\left [ a_{13} \\cdot a_{22} \\cdot a_{31} + a_{11} \\cdot a_{23} \\cdot a_{32} + a_{12} \\cdot a_{21} \\cdot a_{33}\\right ]\\]");
                buffer.schrittListeAdd("\\[" + mA.druckeDaten("A") + "\\]");
            }
            buffer.schrittListeAdd("\\[ det(A) = \\left [ " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 0))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 1))) + " \\cdot" +
                    " " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(2, 2))) + " + " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 1))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 2))) + " " +
                    "\\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(2, 0))) + " + " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 2))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 0))) + " " +
                    "\\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(2, 1))) + "\\right ] - \\\\ \\left [ " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 2))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 1))) + " " +
                    "\\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(2, 0))) + " + " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 0))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 2))) + " \\cdot " +
                    "" + zahlenKlammer(ZA(mA.getDoubleIndexPosition(2, 1))) + " + " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 1))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 0))) + "" +
                    " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(2, 2))) + " \\right ] = " + ZA(determinanteZahl) + " \\]");
            buffer.schrittListeAdd("\\[ det(A)= " + ZA(determinanteZahl) + " \\]");
            return determinanteZahl;
        } else if (mA.getSpalten() == 2) {
            if (!loeschen) {
                buffer.schrittListeAdd("\\[ A = \\begin{bmatrix} a_{11} & a_{12}  \\\\ a_{21} & a_{22} \\end{bmatrix} \\]");
                buffer.schrittListeAdd("\\[det(A) =\\left [ a_{11} \\cdot a_{22}\\right ] - \\left [ a_{21} \\cdot a_{12}\\right ] \\]");
                buffer.schrittListeAdd("\\[" + mA.druckeDaten("A") + "\\]");
            }
            buffer.schrittListeAdd("\\[ det(A) = \\left [ " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 0))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 1))) + " \\right ] - \\left [ " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 0))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 1))) + " \\right ] \\]");
            buffer.schrittListeAdd("\\[ det(A)= " + ZA(determinanteZahl) + "  \\]");
            return determinanteZahl;
        } else {
            if (!loeschen) {
                buffer.schrittListeAdd("\\[ A = \\begin{bmatrix} a_{11} \\end{bmatrix} \\]");
                buffer.schrittListeAdd("\\[det(A) =\\left [ a_{11}\\right ] \\]");
                buffer.schrittListeAdd("\\[" + mA.druckeDaten("A") + "\\]");
            }
            buffer.schrittListeAdd("\\[det(A)= " + ZA(mA.getDoubleIndexPosition(0, 0)) + " \\]");
            return mA.getDoubleIndexPosition(0, 0);
        }
    }

    public double uebungDeterminanteA(Matrix mA) {
        buffer.schrittListeLeeren();
        determinanteZahl = det((matrixZuArray(mA)));
        if (mA.getSpalten() == 3) {
            buffer.schrittListeAdd("\\[" + mA.druckeDaten("A") + "\\]");
            buffer.schrittListeAdd("\\[ det(A) = \\left [ " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 0))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 1))) + " \\cdot" +
                    " " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(2, 2))) + " + " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 1))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 2))) + " " +
                    "\\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(2, 0))) + " + " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 2))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 0))) + " " +
                    "\\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(2, 1))) + "\\right ] - \\\\ \\left [ " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 2))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 1))) + " " +
                    "\\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(2, 0))) + " + " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 0))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 2))) + " \\cdot " +
                    "" + zahlenKlammer(ZA(mA.getDoubleIndexPosition(2, 1))) + " + " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 1))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 0))) + "" +
                    " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(2, 2))) + " \\right ] = " + ZA(determinanteZahl) + " \\]");
            buffer.schrittListeAdd("\\[ det(A)= " + ZA(determinanteZahl) + " \\]");
            return determinanteZahl;
        } else if (mA.getSpalten() == 2) {

            buffer.schrittListeAdd("\\[" + mA.druckeDaten("A") + "\\]");

            buffer.schrittListeAdd("\\[det(A) = \\left [ " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 0))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 1))) + " \\right ] - \\left [ " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 0))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 1))) + " \\right ] \\]");
            buffer.schrittListeAdd("\\[ det(A)= " + ZA(determinanteZahl) + "  \\]");
            return determinanteZahl;
        } else {

            buffer.schrittListeAdd("\\[" + mA.druckeDaten("A") + "\\]");

            buffer.schrittListeAdd("\\[det(A)= " + ZA(mA.getDoubleIndexPosition(0, 0)) + " \\]");
            return mA.getDoubleIndexPosition(0, 0);
        }
    }

    // gebraucht
    public double determinanteB(Matrix mA, boolean loeschen) {
        String lambda = "";
        if (!loeschen) {
            buffer.schrittListeLeeren();
            lambda = " = \\lambda";
        }
        determinanteZahl = det((matrixZuArray(mA)));
        if (mA.getSpalten() == 3) {
            if (!loeschen) {
                buffer.schrittListeAdd("\\[ B = \\begin{bmatrix} b_{11} & b_{12}  & b_{13} \\\\ b_{21} & b_{22}  & b_{23} \\\\ b_{31} & b_{32} & b_{33} \\end{bmatrix} \\]");
                buffer.schrittListeAdd("\\[det(B) =\\left [ b_{11} \\cdot b_{22} \\cdot b_{33} + b_{12} \\cdot b_{23} \\cdot b_{31} + b_{13} \\cdot b_{21} \\cdot b_{32}\\right ] - " +
                        "\\\\ \\left [ b_{13} \\cdot b_{22} \\cdot b_{31} + b_{11} \\cdot b_{23} \\cdot b_{32} + b_{12} \\cdot b_{21} \\cdot b_{33}\\right ] \\]");
                buffer.schrittListeAdd("\\[" + mA.druckeDaten("B") + "\\]");
            }
            buffer.schrittListeAdd("\\[ det(B) = \\left [ " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 0))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 1))) + " \\cdot" +
                    " " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(2, 2))) + " + " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 1))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 2))) + " " +
                    "\\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(2, 0))) + " + " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 2))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 0))) + " " +
                    "\\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(2, 1))) + "\\right ] - \\\\ \\left [ " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 2))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 1))) + " " +
                    "\\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(2, 0))) + " + " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 0))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 2))) + " \\cdot " +
                    "" + zahlenKlammer(ZA(mA.getDoubleIndexPosition(2, 1))) + " + " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 1))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 0))) + "" +
                    " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(2, 2))) + " \\right ] = " + ZA(determinanteZahl) + " \\]");
            buffer.schrittListeAdd("\\[ det(B)= " + ZA(determinanteZahl) + " \\]");
            return determinanteZahl;
        } else if (mA.getSpalten() == 2) {
            if (!loeschen) {
                buffer.schrittListeAdd("\\[ B = \\begin{bmatrix} b_{11} & b_{12}  \\\\ b_{21} & b_{22} \\end{bmatrix} \\]");
                buffer.schrittListeAdd("\\[det(B) =\\left [ b_{11} \\cdot b_{22}\\right ] - \\left [ b_{21} \\cdot b_{12}\\right ]\\]");
                buffer.schrittListeAdd("\\[" + mA.druckeDaten("B") + "\\]");
            }
            buffer.schrittListeAdd("\\[det(B) = \\left [ " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 0))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 1))) + " \\right ] - \\left [ " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(1, 0))) + " \\cdot " + zahlenKlammer(ZA(mA.getDoubleIndexPosition(0, 1))) + " \\right ] \\]");
            buffer.schrittListeAdd("\\[ det(B)= " + ZA(determinanteZahl) + "  \\]");
            return determinanteZahl;
        } else {
            if (!loeschen) {
                buffer.schrittListeAdd("\\[ A = \\begin{bmatrix} a_{11} \\end{bmatrix} \\]");
                buffer.schrittListeAdd("\\[det(A) =\\left [ a_{11}\\right ]\\]");
                buffer.schrittListeAdd("\\[" + mA.druckeDaten("A") + "\\]");
            }
            buffer.schrittListeAdd("\\[det(A)= " + ZA(mA.getDoubleIndexPosition(0, 0)) + " \\]");
            return mA.getDoubleIndexPosition(0, 0);
        }
    }

    // gebraucht
    public Matrix zweiDimensionInverseA(Matrix m) {
        Matrix matr = inverseA(m);
        buffer.schrittListeLeeren();

        buffer.schrittListeAdd("\\[ A^{-1} = C \\]");
        buffer.schrittListeAdd("\\[" + m.druckeDaten("A") + "\\]");
        double de = determinanteA(m, true);
        Matrix ma = new Matrix(2, 2, true, "", false);
        ma.setDoubleIndexPosition(0, 0, m.getDoubleIndexPosition(1, 1));
        ma.setDoubleIndexPosition(1, 1, m.getDoubleIndexPosition(0, 0));
        ma.setDoubleIndexPosition(0, 1, -1 * (m.getDoubleIndexPosition(0, 1)));
        ma.setDoubleIndexPosition(1, 0, -1 * (m.getDoubleIndexPosition(1, 0)));
        buffer.schrittListeAdd("\\[ A^{-1} = C = \\frac{1}{" + ZA(de) + "} \\cdot " + ma.druckeDaten("") + "\\]");
        if (de == 1) {
            buffer.schrittListeAdd("\\[" + ma.druckeDaten("C") + "\\]");
            return ma;
        } else {
            buffer.schrittListeAdd("\\[" + matr.druckeDaten("C") + "\\]");
            return matr;
        }
    }

    // gebraucht
    public Matrix zweiDimensionInverseB(Matrix m) {
        Matrix matr = inverseB(m);
        buffer.schrittListeLeeren();

        buffer.schrittListeAdd("\\[ B^{-1} = C \\]");
        buffer.schrittListeAdd("\\[" + m.druckeDaten("B") + "\\]");
        double de = determinanteB(m, true);
        Matrix ma = new Matrix(2, 2, true, "", false);
        ma.setDoubleIndexPosition(0, 0, m.getDoubleIndexPosition(1, 1));
        ma.setDoubleIndexPosition(1, 1, m.getDoubleIndexPosition(0, 0));
        ma.setDoubleIndexPosition(0, 1, -1 * (m.getDoubleIndexPosition(0, 1)));
        ma.setDoubleIndexPosition(1, 0, -1 * (m.getDoubleIndexPosition(1, 0)));
        buffer.schrittListeAdd("\\[ B^{-1} = C = \\frac{1}{" + ZA(de) + "} \\cdot " + ma.druckeDaten("") + "\\]");
        if (de == 1) {
            buffer.schrittListeAdd("\\[" + ma.druckeDaten("C") + "\\]");
            return ma;
        } else {
            buffer.schrittListeAdd("\\[" + matr.druckeDaten("C") + "\\]");
            return matr;
        }

    }


    public Matrix inverseA3(Matrix m) {
        buffer.schrittListeLeeren();
        if (m.getSpalten() > 2)
            inverseLoesungsweg(m, "A");

        double determinante = det(matrixZuArray(m));
        double[][] zweiDeminsionMatrix = new double[3][3];
        zweiDeminsionMatrix[0][0] = m.getDoubleIndexPosition(1, 1) * m.getDoubleIndexPosition(2, 2) - m.getDoubleIndexPosition(2, 1) * m.getDoubleIndexPosition(1, 2);
        zweiDeminsionMatrix[1][0] = (-1) * (m.getDoubleIndexPosition(1, 0) * m.getDoubleIndexPosition(2, 2) - m.getDoubleIndexPosition(2, 0) * m.getDoubleIndexPosition(1, 2));
        zweiDeminsionMatrix[2][0] = m.getDoubleIndexPosition(1, 0) * m.getDoubleIndexPosition(2, 1) - m.getDoubleIndexPosition(2, 0) * m.getDoubleIndexPosition(1, 1);
        zweiDeminsionMatrix[0][1] = (-1) * (m.getDoubleIndexPosition(0, 1) * m.getDoubleIndexPosition(2, 2) - m.getDoubleIndexPosition(2, 1) * m.getDoubleIndexPosition(0, 2));
        zweiDeminsionMatrix[1][1] = m.getDoubleIndexPosition(0, 0) * m.getDoubleIndexPosition(2, 2) - m.getDoubleIndexPosition(2, 0) * m.getDoubleIndexPosition(0, 2);
        zweiDeminsionMatrix[2][1] = (-1) * (m.getDoubleIndexPosition(0, 0) * m.getDoubleIndexPosition(2, 1) - m.getDoubleIndexPosition(2, 0) * m.getDoubleIndexPosition(0, 1));
        zweiDeminsionMatrix[0][2] = m.getDoubleIndexPosition(0, 1) * m.getDoubleIndexPosition(1, 2) - m.getDoubleIndexPosition(1, 1) * m.getDoubleIndexPosition(0, 2);
        zweiDeminsionMatrix[1][2] = (-1) * (m.getDoubleIndexPosition(0, 0) * m.getDoubleIndexPosition(1, 2) - m.getDoubleIndexPosition(1, 0) * m.getDoubleIndexPosition(0, 2));
        zweiDeminsionMatrix[2][2] = m.getDoubleIndexPosition(1, 1) * m.getDoubleIndexPosition(0, 0) - m.getDoubleIndexPosition(0, 1) * m.getDoubleIndexPosition(1, 0);
        for (int i = 0; i < m.getZeile(); i++) {
            for (int j = 0; j < m.getSpalten(); j++) {
                zweiDeminsionMatrix[i][j] = zweiDeminsionMatrix[i][j] * (1 / determinante);
            }
        }
        buffer.schrittListeAdd("\\[" + arrayZuMatrix(zweiDeminsionMatrix).druckeDaten("C") + "\\]");
        return arrayZuMatrix(zweiDeminsionMatrix);
    }

    public Matrix inverseB3(Matrix m) {
        buffer.schrittListeLeeren();
        if (m.getSpalten() > 2)
            inverseLoesungsweg(m, "B");

        double determinante = det(matrixZuArray(m));
        double[][] zweiDeminsionMatrix = new double[3][3];
        zweiDeminsionMatrix[0][0] = m.getDoubleIndexPosition(1, 1) * m.getDoubleIndexPosition(2, 2) - m.getDoubleIndexPosition(2, 1) * m.getDoubleIndexPosition(1, 2);
        zweiDeminsionMatrix[1][0] = (-1) * (m.getDoubleIndexPosition(1, 0) * m.getDoubleIndexPosition(2, 2) - m.getDoubleIndexPosition(2, 0) * m.getDoubleIndexPosition(1, 2));
        zweiDeminsionMatrix[2][0] = m.getDoubleIndexPosition(1, 0) * m.getDoubleIndexPosition(2, 1) - m.getDoubleIndexPosition(2, 0) * m.getDoubleIndexPosition(1, 1);
        zweiDeminsionMatrix[0][1] = (-1) * (m.getDoubleIndexPosition(0, 1) * m.getDoubleIndexPosition(2, 2) - m.getDoubleIndexPosition(2, 1) * m.getDoubleIndexPosition(0, 2));
        zweiDeminsionMatrix[1][1] = m.getDoubleIndexPosition(0, 0) * m.getDoubleIndexPosition(2, 2) - m.getDoubleIndexPosition(2, 0) * m.getDoubleIndexPosition(0, 2);
        zweiDeminsionMatrix[2][1] = (-1) * (m.getDoubleIndexPosition(0, 0) * m.getDoubleIndexPosition(2, 1) - m.getDoubleIndexPosition(2, 0) * m.getDoubleIndexPosition(0, 1));
        zweiDeminsionMatrix[0][2] = m.getDoubleIndexPosition(0, 1) * m.getDoubleIndexPosition(1, 2) - m.getDoubleIndexPosition(1, 1) * m.getDoubleIndexPosition(0, 2);
        zweiDeminsionMatrix[1][2] = (-1) * (m.getDoubleIndexPosition(0, 0) * m.getDoubleIndexPosition(1, 2) - m.getDoubleIndexPosition(1, 0) * m.getDoubleIndexPosition(0, 2));
        zweiDeminsionMatrix[2][2] = m.getDoubleIndexPosition(1, 1) * m.getDoubleIndexPosition(0, 0) - m.getDoubleIndexPosition(0, 1) * m.getDoubleIndexPosition(1, 0);
        for (int i = 0; i < m.getZeile(); i++) {
            for (int j = 0; j < m.getSpalten(); j++) {
                zweiDeminsionMatrix[i][j] = zweiDeminsionMatrix[i][j] * (1 / determinante);
            }
        }
        buffer.schrittListeAdd("\\[" + arrayZuMatrix(zweiDeminsionMatrix).druckeDaten("C") + "\\]");
        return arrayZuMatrix(zweiDeminsionMatrix);
    }

    // gebraucht
    public Matrix inverseA(Matrix m) {
        buffer.schrittListeLeeren();
        if (m.getSpalten() > 2)
            inverseLoesungsweg(m, "A");
        double[][] a = matrixZuArray(m);
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;

        // Transform the matrix into an upper triangle

        gaussian(a, index);

        // Update the matrix b[i][j] with the ratios stored
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    b[index[j]][k] = ((b[index[j]][k] * 1000) - (a[index[j]][i]
                            * b[index[i]][k] * 1000)) / 1000;
                }
            }
        }


        // Perform backward substitutions
        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] = ((x[j][i] * 1000) - (a[index[j]][k] * x[k][i] * 1000)) / 1000;
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        buffer.schrittListeAdd("\\[" + arrayZuMatrix(x).druckeDaten("C") + "\\]");
        return arrayZuMatrix(x);
    }

    // gebraucht
    public Matrix inverseB(Matrix m) {
        buffer.schrittListeLeeren();
        if (m.getSpalten() > 2)
            inverseLoesungsweg(m, "B");
        double[][] a = matrixZuArray(m);
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;

        // Transform the matrix into an upper triangle

        gaussian(a, index);

        // Update the matrix b[i][j] with the ratios stored
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    b[index[j]][k] = ((b[index[j]][k] * 1000) - (a[index[j]][i] * b[index[i]][k] * 1000)) / 1000;
                }
            }
        }


        // Perform backward substitutions
        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] = ((x[j][i] * 1000) - (a[index[j]][k] * x[k][i] * 1000)) / 1000;
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        buffer.schrittListeAdd("\\[" + arrayZuMatrix(x).druckeDaten("C") + "\\]");
        return arrayZuMatrix(x);
    }

    // gebraucht
    public void inverseLoesungsweg(Matrix m, String matrixName) {
        double det = 0;
        buffer.schrittListeAdd("\\[ " + matrixName + "^{-1} = C \\]");
        buffer.schrittListeAdd("\\[" + m.druckeDaten(matrixName) + "\\]");
        if (matrixName.equals("A"))
            det = determinanteA(m, true);
        else
            det = determinanteB(m, true);
        double[][] zweiDeminsionMatrix = new double[3][3];
        zweiDeminsionMatrix[0][0] = m.getDoubleIndexPosition(1, 1) * m.getDoubleIndexPosition(2, 2) - m.getDoubleIndexPosition(2, 1) * m.getDoubleIndexPosition(1, 2);
        zweiDeminsionMatrix[0][1] = (-1) * (m.getDoubleIndexPosition(1, 0) * m.getDoubleIndexPosition(2, 2) - m.getDoubleIndexPosition(2, 0) * m.getDoubleIndexPosition(1, 2));
        zweiDeminsionMatrix[0][2] = m.getDoubleIndexPosition(1, 0) * m.getDoubleIndexPosition(2, 1) - m.getDoubleIndexPosition(2, 0) * m.getDoubleIndexPosition(1, 1);
        zweiDeminsionMatrix[1][0] = (-1) * (m.getDoubleIndexPosition(0, 1) * m.getDoubleIndexPosition(2, 2) - m.getDoubleIndexPosition(2, 1) * m.getDoubleIndexPosition(0, 2));
        zweiDeminsionMatrix[1][1] = m.getDoubleIndexPosition(0, 0) * m.getDoubleIndexPosition(2, 2) - m.getDoubleIndexPosition(2, 0) * m.getDoubleIndexPosition(0, 2);
        zweiDeminsionMatrix[1][2] = (-1) * (m.getDoubleIndexPosition(0, 0) * m.getDoubleIndexPosition(2, 1) - m.getDoubleIndexPosition(2, 0) * m.getDoubleIndexPosition(0, 1));
        zweiDeminsionMatrix[2][0] = m.getDoubleIndexPosition(0, 1) * m.getDoubleIndexPosition(1, 2) - m.getDoubleIndexPosition(1, 1) * m.getDoubleIndexPosition(0, 2);
        zweiDeminsionMatrix[2][1] = (-1) * (m.getDoubleIndexPosition(0, 0) * m.getDoubleIndexPosition(1, 2) - m.getDoubleIndexPosition(1, 0) * m.getDoubleIndexPosition(0, 2));
        zweiDeminsionMatrix[2][2] = m.getDoubleIndexPosition(1, 1) * m.getDoubleIndexPosition(0, 0) - m.getDoubleIndexPosition(0, 1) * m.getDoubleIndexPosition(1, 0);

        buffer.schrittListeAdd("\\[ det(" + matrixName + "_{11}) = (-1)^{1+1} \\cdot " + "\\begin{vmatrix}" + ZA(m.getDoubleIndexPosition(1, 1)) + " & " + ZA(m.getDoubleIndexPosition(1, 2)) + " \\\\ " + ZA(m.getDoubleIndexPosition(2, 1)) + " & " + ZA(m.getDoubleIndexPosition(2, 2)) + "\\end{vmatrix}=" + ZA(zweiDeminsionMatrix[0][0]) + " \\]");
        buffer.schrittListeAdd("\\[ det(" + matrixName + "_{12}) = (-1)^{1+2} \\cdot " + "\\begin{vmatrix}" + ZA(m.getDoubleIndexPosition(1, 0)) + " & " + ZA(m.getDoubleIndexPosition(1, 2)) + " \\\\ " + ZA(m.getDoubleIndexPosition(2, 0)) + " & " + ZA(m.getDoubleIndexPosition(2, 2)) + "\\end{vmatrix}=" + ZA(zweiDeminsionMatrix[0][1]) + " \\]");
        buffer.schrittListeAdd("\\[ det(" + matrixName + "_{13}) = (-1)^{1+3} \\cdot " + "\\begin{vmatrix}" + ZA(m.getDoubleIndexPosition(1, 0)) + " & " + ZA(m.getDoubleIndexPosition(1, 1)) + " \\\\ " + ZA(m.getDoubleIndexPosition(2, 0)) + " & " + ZA(m.getDoubleIndexPosition(2, 1)) + "\\end{vmatrix}=" + ZA(zweiDeminsionMatrix[0][2]) + " \\]");
        buffer.schrittListeAdd("\\[ det(" + matrixName + "_{21}) = (-1)^{2+1} \\cdot " + "\\begin{vmatrix}" + ZA(m.getDoubleIndexPosition(0, 1)) + " & " + ZA(m.getDoubleIndexPosition(0, 2)) + " \\\\ " + ZA(m.getDoubleIndexPosition(2, 1)) + " & " + ZA(m.getDoubleIndexPosition(2, 2)) + "\\end{vmatrix}=" + ZA(zweiDeminsionMatrix[1][0]) + " \\]");
        buffer.schrittListeAdd("\\[ det(" + matrixName + "_{22}) = (-1)^{2+2} \\cdot " + "\\begin{vmatrix}" + ZA(m.getDoubleIndexPosition(0, 0)) + " & " + ZA(m.getDoubleIndexPosition(0, 2)) + " \\\\ " + ZA(m.getDoubleIndexPosition(2, 0)) + " & " + ZA(m.getDoubleIndexPosition(2, 2)) + "\\end{vmatrix}=" + ZA(zweiDeminsionMatrix[1][1]) + " \\]");
        buffer.schrittListeAdd("\\[ det(" + matrixName + "_{23}) = (-1)^{2+3} \\cdot " + "\\begin{vmatrix}" + ZA(m.getDoubleIndexPosition(0, 0)) + " & " + ZA(m.getDoubleIndexPosition(0, 1)) + " \\\\ " + ZA(m.getDoubleIndexPosition(2, 0)) + " & " + ZA(m.getDoubleIndexPosition(2, 1)) + "\\end{vmatrix}=" + ZA(zweiDeminsionMatrix[1][2]) + " \\]");
        buffer.schrittListeAdd("\\[ det(" + matrixName + "_{31}) = (-1)^{3+1} \\cdot " + "\\begin{vmatrix}" + ZA(m.getDoubleIndexPosition(0, 1)) + " & " + ZA(m.getDoubleIndexPosition(0, 2)) + " \\\\ " + ZA(m.getDoubleIndexPosition(1, 1)) + " & " + ZA(m.getDoubleIndexPosition(1, 2)) + "\\end{vmatrix}=" + ZA(zweiDeminsionMatrix[2][0]) + " \\]");
        buffer.schrittListeAdd("\\[ det(" + matrixName + "_{32}) = (-1)^{3+2} \\cdot " + "\\begin{vmatrix}" + ZA(m.getDoubleIndexPosition(0, 0)) + " & " + ZA(m.getDoubleIndexPosition(0, 2)) + " \\\\ " + ZA(m.getDoubleIndexPosition(1, 0)) + " & " + ZA(m.getDoubleIndexPosition(1, 2)) + "\\end{vmatrix}=" + ZA(zweiDeminsionMatrix[2][1]) + " \\]");
        buffer.schrittListeAdd("\\[ det(" + matrixName + "_{33}) = (-1)^{3+3} \\cdot " + "\\begin{vmatrix}" + ZA(m.getDoubleIndexPosition(0, 0)) + " & " + ZA(m.getDoubleIndexPosition(0, 1)) + " \\\\ " + ZA(m.getDoubleIndexPosition(1, 0)) + " & " + ZA(m.getDoubleIndexPosition(1, 1)) + "\\end{vmatrix}=" + ZA(zweiDeminsionMatrix[2][2]) + " \\]");
        Matrix mat = transponieren(arrayZuMatrix(zweiDeminsionMatrix));
        buffer.schrittListeAdd("\\[ " + matrixName + "^{-1}= C =" + "\\frac{1}{" + ZA(det) + "} \\cdot" + mat.druckeDaten("") + " \\]");

    }

    // Method to carry out the partial-pivoting Gaussian
    // elimination. Here index[] stores pivoting order.
// gebraucht
    public void gaussian(double a[][], int index[]) {
        int n = index.length;
        double c[] = new double[n];

        // Initialize the index
        for (int i = 0; i < n; ++i)
            index[i] = i;

        // Find the rescaling factors, one from each row
        // große Zahl in jeder Zeile
        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1)
                    c1 = c0;
            }
            c[i] = c1;
        }

        // Search the pivoting element from each column
        int k = 0;
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];

                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;

                // Modify other elements accordingly
                for (int l = j + 1; l < n; ++l)
                    a[index[i]][l] = ((a[index[i]][l] * 1000) - (1000 * pj * a[index[j]][l])) / 1000;
            }
        }
    }

    // gebraucht
    private double[][] generateMinor(double[][] matrix, int row, int column) { // row
        // zeile
        int matrixSize = matrix.length;
        int minorSize = matrixSize - 1;
        int counterOne = 0;
        int counterTwo = 0;

        double[][] minor = new double[minorSize][minorSize];

        for (int i = 0; i < matrixSize; i++) {
            if (i == row) {
                continue;
            }
            for (int j = 0; j < matrixSize; j++) {
                if (j == column) {
                    continue;
                }
                minor[counterOne][counterTwo] = matrix[i][j];
                ++counterTwo;

            }
            ++counterOne;
            counterTwo = 0;
        }
        return minor;
    }

    // gebraucht
    public double[][] matrixZuArray(Matrix m) {
        double d;
        double[][] feld = new double[m.getZeile()][m.getSpalten()];
        for (int i = 0; i < feld.length; i++) {
            for (int j = 0; j < feld[i].length; j++) {
                d = m.getDoubleIndexPosition(i, j);
                feld[i][j] = d;
            }
        }
        return feld;
    }

    // gebraucht
    public Matrix arrayZuMatrix(double[][] array) {
        Matrix matrix = new Matrix(array.length, array[0].length, istRichtig, "", false);
        double d;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                d = array[i][j];
                matrix.setDoubleIndexPosition(i, j, d);
            }
        }
        return matrix;
    }

    // gebraucht
    public String zahlenKlammer(String s) {
        char zahl = s.charAt(0);
        if (zahl == '-') {
            return "(" + s + ")";
        } else {
            return s;
        }
    }
}