package Logik;

import Datenhaltung.Buffer;

/**
 * Created by KOJAR on 22.02.2016.
 */
public class GaussOperationen {

    private boolean istRichtig = true;
    private boolean keineLoesung = false;
    private Matrix matrix;
    public Buffer buffer;
    private String[][] gaussFeld;

    public GaussOperationen() {
        buffer = buffer.getInstance();
    }

    public Matrix gaussBerechnen(Matrix m) {
        double[][] feld = matrixZuArray(m);
        return arrayZuMatrix(make_Gauss(feld));
    }

    public double[][] make_Gauss(double[][] A) {
        buffer.schrittListeLeeren();
        buffer.schrittListeAdd(arrayZuMatrix(A).druckedatenGauss());
        gaussFeld = new String[A.length][A[0].length];
        int gz = 1;
        int min_mn, m = A.length, n = A[0].length;
        double lambda;
        if (m <= n) {
            min_mn = m;
        } else {
            min_mn = n;
        }
        for (int i = 0; i < min_mn; i++) {
            if (A[i][i] != 0.0) {
                for (int j = i + 1; j < m; j++) {
                    lambda = -(A[j][i] / A[i][i]);
                    buffer.schrittListeAdd("\\[ \\lambda_{" + gz++ + "}= (-1) \\cdot \\frac{" + ZA(A[j][i]) + "}{" + ZA(A[i][i]) + "}\\]");
                    buffer.schrittListeAdd(gaussZeichnen(j, "\\mathbb{I_{" + (j + 1) + "}} + \\lambda_{" + (gz - 1) + "} \\cdot \\mathbb{I_{" + (i + 1) + "}}", arrayZuMatrix(A)));
                    buffer.schrittListeAdd("\\[  {\\color{Red} \\Rightarrow } \\]");
                    arrayZuMatrix(ZS(A, i, j, lambda));
                }
            } else {

                for (int j = i + 1; j < m; j++) {
                    if (A[j][i] != 0.0) {
                        arrayZuMatrix(ZT(A, i, j));
                        i = i - 1;
                        break;
                    }
                }
            }
        }
        werteRechnen(A);
        return A;
    }

    public void werteRechnen(double[][] a) {
        String ss = "";
        int loesung = gaussLoesungen(a);
        Matrix m = arrayZuMatrix(a);
        if (!hatLoesung(m)) {
            buffer.schrittListeAdd("\\[ \\mathrm{Das \\quad Gleichungssystem \\quad hat \\\\ {\\color{Red} { keine \\quad Loesungen}}}.\\]");
        } else if (hatVieleLoesungen(m)) {
            buffer.schrittListeAdd("\\[ \\mathrm{Das \\quad Gleichungssystem \\quad hat \\\\ {\\color{Red} {unendlich \\quad viele \\quad Lösungen}}}.\\]");
            vieleLoesungenrechnen(a);
        } else {
            unbekanntenRechnen(a);
        }
    }

    public int gaussLoesungen(double[][] f) {
        int hilfe = 0;
        for (int i = 0; i < f[f.length - 1].length; i++) {
            if (f[f.length - 1][i] != 0) {
                hilfe++;
            }
        }
        return hilfe;
    }

    public double[][] ZS(double[][] A, int i, int j, double lambda) {
        int n = A[0].length;
        double zahl;
        String index = "";
        for (int k = 0; k < n; k++) {
            zahl = A[j][k];
            A[j][k] = A[j][k] + lambda * A[i][k];
        }
        buffer.schrittListeAdd("\\[" + arrayZuMatrix(A).druckeDaten("") + "\\]");
        return A;
    }

    public String gaussZeichnen(int zeile, String eingabe, Matrix m) {
        String zeilen = "\\\\";
        String spalten = "&";
        String zeichnen = "";
        String zeichnen1;
        String zeichnen2 = "";
        String zeichnen3 = "";
        for (int i = 0; i < m.getZeile(); i++) {
            for (int j = 0; j < m.getSpalten(); j++) {
                if (j + 1 < m.getSpalten()) {
                    zeichnen = zeichnen + ZA(m.getDoubleIndexPosition(i, j)) + spalten;
                } else {
                    zeichnen = zeichnen + ZA(m.getDoubleIndexPosition(i, j));
                }
            }
            zeichnen = zeichnen + zeilen;
        }
        zeichnen1 = "\\left.\\begin{matrix} " + zeichnen + " \\end{matrix}\\right|";
        for (int i = 0; i < m.getZeile(); i++) {
            if (i == zeile) {
                zeichnen2 = zeichnen2 + eingabe + zeilen;
            } else {
                zeichnen2 = zeichnen2 + zeilen;
            }
        }
        zeichnen3 = "\\begin{matrix} " + zeichnen2 + " \\end{matrix}";
        return "\\[" + zeichnen1 + zeichnen3 + "\\]";
    }

    public double[][] ZT(double[][] A, int i, int j) {
        int n = A[0].length;
        double help;
        for (int k = 0; k < n; k++) {
            help = A[i][k];
            A[i][k] = A[j][k];
            A[j][k] = help;
        }
        buffer.schrittListeAdd("\\[ \\mathrm{Die \\quad Zeilen \\quad werden \\quad vertauscht } \\]");
        return A;
    }

    public Matrix arrayZuMatrix(double[][] a) {
        double d = 0;
        matrix = new Matrix(a.length, a[0].length, istRichtig, "", keineLoesung);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                d = a[i][j];
                matrix.setDoubleIndexPosition(i, j, d);
            }
        }
        return matrix;
    }

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

    public String zahlenKlammer(String s) {
        char zahl = s.charAt(0);
        if (zahl == '-') {
            return "(" + s + ")";
        } else {
            return s;
        }
    }

    public boolean hatLoesung(Matrix m) {
        int zaehler = 0;
        boolean entscheiden = true;
        for (int i = 0; i < m.getZeile(); i++) {
            for (int j = 0; j < m.getSpalten(); j++) {
                if (m.getDoubleIndexPosition(i, j) == 0) {
                    zaehler++;
                }
            }
            if (zaehler == m.getZeile()) {
                if (m.getDoubleIndexPosition(i, m.getSpalten() - 1) != 0) {
                    entscheiden = false;
                }
            }
            zaehler = 0;
        }
        return entscheiden;
    }

    public boolean hatVieleLoesungen(Matrix m) {
        int zaehler = 0;
        for (int i = 0; i < m.getSpalten(); i++) {
            if (m.getDoubleIndexPosition(m.getZeile() - 1, i) == 0) {
                zaehler++;
            }
        }
        if (zaehler == m.getSpalten()) {
            return true;
        } else
            return false;
    }

    public int anzahlDieVieleLoesungen(Matrix m) {
        int anzahl = 0;
        int zaehler = 0;
        for (int i = 0; i < m.getZeile(); i++) {
            for (int j = 0; j < m.getSpalten(); j++) {
                if (m.getDoubleIndexPosition(i, j) == 0) {
                    anzahl++;
                }
            }
            if (anzahl == m.getSpalten()) {
                zaehler++;
            }
            anzahl = 0;
        }
        return zaehler;
    }

    public void unbekanntenRechnen(double[][] a) {
        int zeilen = a.length;
        double[] speicherD = new double[zeilen];
        String[] speicherS = new String[zeilen];

        if (zeilen == 2) {
            speicherD[0] = a[1][2] / a[1][1];
            speicherD[1] = a[0][2] - (a[0][1] * speicherD[0]) / a[0][0];

            speicherS[0] = "\\[ x_{2}= \\frac{" + ZA(a[1][2]) + "}{" + ZA(a[1][1]) + "} = " + ZA(speicherD[0]) + " \\]";
            speicherS[1] = "\\[ x_{1}= \\frac{" + ZA(a[0][2]) + "-(" + zahlenKlammer(ZA(a[0][1])) + "\\cdot" + zahlenKlammer(ZA(speicherD[0])) + ")}{" + ZA(a[0][0]) + "} = " + ZA(speicherD[1]) + " \\]";

        } else if (zeilen == 3) {
            speicherD[0] = a[2][3] / a[2][2];
            speicherD[1] = (a[1][3] - (a[1][2] * speicherD[0])) / a[1][1];
            speicherD[2] = (a[0][3] - (a[0][2] * speicherD[0]) - (a[0][1] * speicherD[1])) / a[0][0];

            speicherS[0] = "\\[ x_{3}= \\frac{" + ZA(a[2][3]) + "}{" + ZA(a[2][2]) + "} = " + ZA(speicherD[0]) + " \\]";
            speicherS[1] = "\\[ x_{2}= \\frac{" + ZA(a[1][3]) + "-(" + zahlenKlammer(ZA(a[1][2])) + "\\cdot" + zahlenKlammer(ZA(speicherD[0])) + ")}{" + ZA(a[1][1]) + "} = " + ZA(speicherD[1]) + " \\]";
            speicherS[2] = "\\[ x_{1}= \\frac{" + ZA(a[0][3]) + "-(" + zahlenKlammer(ZA(a[0][2])) + "\\cdot" + zahlenKlammer(ZA(speicherD[0])) + ")-(" + zahlenKlammer(ZA(a[0][1])) + "\\cdot" + zahlenKlammer(ZA(speicherD[1])) + ")}{" + ZA(a[0][0]) + "} = " + ZA(speicherD[2]) + " \\]";

        } else if (zeilen == 4) {
            speicherD[0] = a[3][4] / a[3][3];
            speicherD[1] = (a[2][4] - (a[2][3] * speicherD[0])) / a[2][2];
            speicherD[2] = (a[1][4] - (a[1][3] * speicherD[0]) - (a[1][2] * speicherD[1])) / a[1][1];
            speicherD[3] = (a[0][4] - (a[0][3] * speicherD[0]) - (a[0][2] * speicherD[1]) - (a[0][1] * speicherD[2])) / a[0][0];

            speicherS[0] = "\\[ x_{4}= \\frac{" + ZA(a[3][4]) + "}{" + ZA(a[3][3]) + "} = " + ZA(speicherD[0]) + " \\]";
            speicherS[1] = "\\[ x_{3}= \\frac{" + ZA(a[2][4]) + "-(" + zahlenKlammer(ZA(a[2][3])) + "\\cdot" + zahlenKlammer(ZA(speicherD[0])) + ")}{" + ZA(a[2][2]) + "} = " + ZA(speicherD[1]) + " \\]";
            speicherS[2] = "\\[ x_{2}= \\frac{" + ZA(a[1][4]) + "-(" + zahlenKlammer(ZA(a[1][3])) + "\\cdot" + zahlenKlammer(ZA(speicherD[0])) + ")-(" + zahlenKlammer(ZA(a[1][2])) + "\\cdot" + zahlenKlammer(ZA(speicherD[1])) + ")}{" + ZA(a[1][1]) + "} = " + ZA(speicherD[2]) + " \\]";
            speicherS[3] = "\\[ x_{1}= \\frac{" + ZA(a[0][4]) + "-(" + zahlenKlammer(ZA(a[0][3])) + "\\cdot" + zahlenKlammer(ZA(speicherD[0])) + ")-(" + zahlenKlammer(ZA(a[0][2])) + "\\cdot" + zahlenKlammer(ZA(speicherD[1])) + ")-(" + zahlenKlammer(ZA(a[0][1])) + "\\cdot" + zahlenKlammer(ZA(speicherD[2])) + ")}{" + ZA(a[0][0]) + "} = " + ZA(speicherD[3]) + " \\]";

        } else if (zeilen == 5) {
            speicherD[0] = a[4][5] / a[4][4];
            speicherD[1] = (a[3][5] - (a[3][4] * speicherD[0])) / a[3][3];
            speicherD[2] = (a[2][5] - (a[2][4] * speicherD[0]) - (a[2][3] * speicherD[1])) / a[2][2];
            speicherD[3] = (a[1][5] - (a[1][4] * speicherD[0]) - (a[1][3] * speicherD[1]) - (a[1][2] * speicherD[2])) / a[1][1];
            speicherD[4] = (a[0][5] - (a[0][4] * speicherD[0]) - (a[0][3] * speicherD[1]) - (a[0][2] * speicherD[2]) - (a[0][1] * speicherD[3])) / a[0][0];

            speicherS[0] = "\\[ x_{5}= \\frac{" + ZA(a[4][5]) + "}{" + ZA(a[4][4]) + "} = " + ZA(speicherD[0]) + " \\]";
            speicherS[1] = "\\[ x_{4}= \\frac{" + ZA(a[3][5]) + "-(" + zahlenKlammer(ZA(a[3][4])) + "\\cdot" + zahlenKlammer(ZA(speicherD[0])) + ")}{" + ZA(a[3][3]) + "} = " + ZA(speicherD[1]) + " \\]";
            speicherS[2] = "\\[ x_{3}= \\frac{" + ZA(a[2][5]) + "-(" + zahlenKlammer(ZA(a[2][4])) + "\\cdot" + zahlenKlammer(ZA(speicherD[0])) + ")-(" + zahlenKlammer(ZA(a[2][3])) + "\\cdot" + zahlenKlammer(ZA(speicherD[1])) + ")}{" + ZA(a[2][2]) + "} = " + ZA(speicherD[2]) + " \\]";
            speicherS[3] = "\\[ x_{2}= \\frac{" + ZA(a[1][5]) + "-(" + zahlenKlammer(ZA(a[1][4])) + "\\cdot" + zahlenKlammer(ZA(speicherD[0])) + ")-(" + zahlenKlammer(ZA(a[1][3])) + "\\cdot" + zahlenKlammer(ZA(speicherD[1])) + ")-(" + zahlenKlammer(ZA(a[1][2])) + "\\cdot" + zahlenKlammer(ZA(speicherD[2])) + ")}{" + ZA(a[1][1]) + "} = " + ZA(speicherD[3]) + " \\]";
            speicherS[4] = "\\[ x_{1}= \\frac{" + ZA(a[0][5]) + "-(" + zahlenKlammer(ZA(a[0][4])) + "\\cdot" + zahlenKlammer(ZA(speicherD[0])) + ")-(" + zahlenKlammer(ZA(a[0][3])) + "\\cdot" + zahlenKlammer(ZA(speicherD[1])) + ")-(" + zahlenKlammer(ZA(a[0][2])) + "\\cdot" + zahlenKlammer(ZA(speicherD[2])) + ")-(" + zahlenKlammer(ZA(a[0][1])) + "\\cdot" + zahlenKlammer(ZA(speicherD[3])) + ")}{" + ZA(a[0][0]) + "} = " + ZA(speicherD[4]) + " \\]";
        }
        for (int i = 0; i < zeilen; i++) {
            buffer.schrittListeAdd(speicherS[i]);
        }
    }

    public void vieleLoesungenrechnen(double[][] a) {
        int zeilen = a.length;
        if (zeilen == 2) {
            zweiVieleLoesungen(a);
        } else if (zeilen == 3) {
            dreiVieleLoesungen(a);
        } else if (zeilen == 4) {
            vierVieleLoesungen(a);
        } else if (zeilen == 5) {
            fuenfVieleLoesungen(a);
        }
    }

    public void zweiVieleLoesungen(double[][] a) {
        int zeilen = a.length;
        String[] speicherS = new String[zeilen];
        int anzahlDieVieleLoesungen = anzahlDieVieleLoesungen(arrayZuMatrix(a));
        buffer.schrittListeAdd("\\[ t_{i} \\in \\mathbb{R} \\]");
        if (anzahlDieVieleLoesungen == 1) {
            speicherS[0] = "\\[ x_{2} = t_{2} \\]";
            speicherS[1] = "\\[ x_{1} = \\frac{" + ZA(a[0][2]) + nulleVariablenWegnehmen(ZA(a[0][1]), "t_{2}") + "}{" + ZA(a[0][0]) + "}" + letzeErgebnisse(a[0][0]) + " \\]";


        } else {
            speicherS[0] = "\\[ x_{2} = t_{2} \\]";
            speicherS[1] = "\\[ x_{1} = t_{1} \\]";
        }


        for (int i = 0; i < zeilen; i++) {
            buffer.schrittListeAdd(speicherS[i]);
        }

    }

    public void dreiVieleLoesungen(double[][] a) {
        int zeilen = a.length;
        String[] speicherS = new String[zeilen];
        int anzahlDieVieleLoesungen = anzahlDieVieleLoesungen(arrayZuMatrix(a));
        buffer.schrittListeAdd("\\[ t_{i} \\in \\mathbb{R} \\]");
        if (anzahlDieVieleLoesungen == 1) {
            speicherS[0] = "\\[ x_{3} = t_{3} \\]";
            speicherS[1] = "\\[ x_{2} = \\frac{" + ZA(a[1][3]) + nulleVariablenWegnehmen(ZA(a[1][2]), "t_{3}") + "}{" + ZA(a[1][1]) + "}" + letzeErgebnisse(a[1][1]) + "  \\]";
            speicherS[2] = "\\[ x_{1} = \\frac{" + ZA(a[0][3]) + nulleVariablenWegnehmen(ZA(a[0][1]), "x_{2}") + nulleVariablenWegnehmen(ZA(a[0][2]), "t_{3}") + "}{" + ZA(a[0][0]) + "} " + letzeErgebnisse(a[0][0]) + "  \\]";
        } else if (anzahlDieVieleLoesungen == 2) {
            speicherS[0] = "\\[ x_{3} = t_{3} \\]";
            speicherS[1] = "\\[ x_{2} = t_{2} \\]";
            speicherS[2] = "\\[ x_{1} = \\frac{" + ZA(a[0][3]) + nulleVariablenWegnehmen(ZA(a[0][1]), "t_{2}") + nulleVariablenWegnehmen(ZA(a[0][2]), "t_{3}") + "}{" + ZA(a[0][0]) + "} " + letzeErgebnisse(a[0][0]) + "  \\]";
        } else if (anzahlDieVieleLoesungen == 3) {
            speicherS[0] = "\\[ x_{3} = t_{3} \\]";
            speicherS[1] = "\\[ x_{2} = t_{2} \\]";
            speicherS[2] = "\\[ x_{1} = t_{1} \\]";
        }


        for (int i = 0; i < zeilen; i++) {
            buffer.schrittListeAdd(speicherS[i]);
        }

    }

    public void vierVieleLoesungen(double[][] a) {
        int zeilen = a.length;
        String[] speicherS = new String[zeilen];
        int anzahlDieVieleLoesungen = anzahlDieVieleLoesungen(arrayZuMatrix(a));
        buffer.schrittListeAdd("\\[ t_{i} \\in \\mathbb{R} \\]");
        if (anzahlDieVieleLoesungen == 1) {
            speicherS[0] = "\\[ x_{4} = t_{4} \\]";
            speicherS[1] = "\\[ x_{3} = \\frac{" + ZA(a[2][4]) + nulleVariablenWegnehmen(ZA(a[2][3]), "t_{4}") + "}{" + ZA(a[2][2]) + "}" + letzeErgebnisse(a[2][2]) + " \\]";
            speicherS[2] = "\\[ x_{2} = \\frac{" + ZA(a[1][4]) + nulleVariablenWegnehmen(ZA(a[1][2]), "x_{3}") + nulleVariablenWegnehmen(ZA(a[1][3]), "t_{4}") + "}{" + ZA(a[1][1]) + "} " + letzeErgebnisse(a[1][1]) + " \\]";
            speicherS[3] = "\\[ x_{1} = \\frac{" + ZA(a[0][4]) + nulleVariablenWegnehmen(ZA(a[0][1]), "x_{2}") + nulleVariablenWegnehmen(ZA(a[0][2]), "x_{3}") + nulleVariablenWegnehmen(ZA(a[0][3]), "t_{4}") + "}{" + ZA(a[0][0]) + "} " + letzeErgebnisse(a[0][0]) + " \\]";
        } else if (anzahlDieVieleLoesungen == 2) {
            speicherS[0] = "\\[ x_{4} = t_{4} \\]";
            speicherS[1] = "\\[ x_{3} = t_{3} \\]";
            speicherS[2] = "\\[ x_{2} = \\frac{" + ZA(a[1][4]) + nulleVariablenWegnehmen(ZA(a[1][2]), "t_{3}") + nulleVariablenWegnehmen(ZA(a[1][3]), "t_{4}") + "}{" + ZA(a[1][1]) + "} " + letzeErgebnisse(a[1][1]) + " \\]";
            speicherS[3] = "\\[ x_{1} = \\frac{" + ZA(a[0][4]) + nulleVariablenWegnehmen(ZA(a[0][1]), "x_{2}") + nulleVariablenWegnehmen(ZA(a[0][2]), "t_{3}") + nulleVariablenWegnehmen(ZA(a[0][3]), "t_{4}") + "}{" + ZA(a[0][0]) + "} " + letzeErgebnisse(a[0][0]) + " \\]";
        } else if (anzahlDieVieleLoesungen == 3) {
            speicherS[0] = "\\[ x_{4} = t_{4} \\]";
            speicherS[1] = "\\[ x_{3} = t_{3} \\]";
            speicherS[2] = "\\[ x_{2} = t_{2} \\]";
            speicherS[3] = "\\[ x_{1} = \\frac{" + ZA(a[0][4]) + nulleVariablenWegnehmen(ZA(a[0][1]), "t_{2}") + nulleVariablenWegnehmen(ZA(a[0][2]), "t_{3}") + nulleVariablenWegnehmen(ZA(a[0][3]), "t_{4}") + "}{" + ZA(a[0][0]) + "} " + letzeErgebnisse(a[0][0]) + " \\]";
        } else if (anzahlDieVieleLoesungen == 4) {
            speicherS[0] = "\\[ x_{4} = t_{4} \\]";
            speicherS[1] = "\\[ x_{3} = t_{3} \\]";
            speicherS[2] = "\\[ x_{2} = t_{2} \\]";
            speicherS[3] = "\\[ x_{1} = t_{1} \\]";
        }
        for (int i = 0; i < zeilen; i++) {
            buffer.schrittListeAdd(speicherS[i]);
        }
    }

    public void fuenfVieleLoesungen(double[][] a) {
        int zeilen = a.length;
        String[] speicherS = new String[zeilen];
        int anzahlDieVieleLoesungen = anzahlDieVieleLoesungen(arrayZuMatrix(a));
        buffer.schrittListeAdd("\\[ t_{i} \\in \\mathbb{R} \\]");
        if (anzahlDieVieleLoesungen == 1) {
            speicherS[0] = "\\[ x_{5} = t_{5} \\]";
            speicherS[1] = "\\[ x_{4} = \\frac{" + ZA(a[3][5]) + nulleVariablenWegnehmen(ZA(a[3][4]), "t_{5}") + "}{" + ZA(a[3][3]) + "}" + letzeErgebnisse(a[3][3]) + "  \\]";
            speicherS[2] = "\\[ x_{3} = \\frac{" + ZA(a[2][5]) + nulleVariablenWegnehmen(ZA(a[2][3]), "x_{4}") + nulleVariablenWegnehmen(ZA(a[2][4]), "t_{5}") + "}{" + ZA(a[2][2]) + "} " + letzeErgebnisse(a[2][2]) + " \\]";
            speicherS[3] = "\\[ x_{2} = \\frac{" + ZA(a[1][5]) + nulleVariablenWegnehmen(ZA(a[1][2]), "x_{3}") + nulleVariablenWegnehmen(ZA(a[1][3]), "x_{4}") + nulleVariablenWegnehmen(ZA(a[1][4]), "t_{5}") + "}{" + ZA(a[1][1]) + "} " + letzeErgebnisse(a[1][1]) + "  \\]";
            speicherS[4] = "\\[ x_{1} = \\frac{" + ZA(a[0][5]) + nulleVariablenWegnehmen(ZA(a[0][1]), "x_{2}") + nulleVariablenWegnehmen(ZA(a[0][2]), "x_{3}") + nulleVariablenWegnehmen(ZA(a[0][3]), "x_{4}") + nulleVariablenWegnehmen(ZA(a[0][4]), "t_{5}") + "}{" + ZA(a[0][0]) + "} " + letzeErgebnisse(a[0][0]) + " \\]";
        } else if (anzahlDieVieleLoesungen == 2) {
            speicherS[0] = "\\[ x_{5} = t_{5} \\]";
            speicherS[1] = "\\[ x_{4} = t_{4} \\]";
            speicherS[2] = "\\[ x_{3} = \\frac{" + ZA(a[2][5]) + nulleVariablenWegnehmen(ZA(a[2][3]), "t_{4}") + nulleVariablenWegnehmen(ZA(a[2][4]), "t_{5}") + "}{" + ZA(a[2][2]) + "} " + letzeErgebnisse(a[2][2]) + " \\]";
            speicherS[3] = "\\[ x_{2} = \\frac{" + ZA(a[1][5]) + nulleVariablenWegnehmen(ZA(a[1][2]), "x_{3}") + nulleVariablenWegnehmen(ZA(a[1][3]), "t_{4}") + nulleVariablenWegnehmen(ZA(a[1][4]), "t_{5}") + "}{" + ZA(a[1][1]) + "} " + letzeErgebnisse(a[1][1]) + " \\]";
            speicherS[4] = "\\[ x_{1} = \\frac{" + ZA(a[0][5]) + nulleVariablenWegnehmen(ZA(a[0][1]), "x_{2}") + nulleVariablenWegnehmen(ZA(a[0][2]), "x_{3}") + nulleVariablenWegnehmen(ZA(a[0][3]), "t_{4}") + nulleVariablenWegnehmen(ZA(a[0][4]), "t_{5}") + "}{" + ZA(a[0][0]) + "} " + letzeErgebnisse(a[0][0]) + " \\]";
        } else if (anzahlDieVieleLoesungen == 3) {
            speicherS[0] = "\\[ x_{5} = t_{5} \\]";
            speicherS[1] = "\\[ x_{4} = t_{4} \\]";
            speicherS[2] = "\\[ x_{3} = t_{3} \\]";
            speicherS[3] = "\\[ x_{2} = \\frac{" + ZA(a[1][5]) + nulleVariablenWegnehmen(ZA(a[1][2]), "t_{3}") + nulleVariablenWegnehmen(ZA(a[1][3]), "t_{4}") + nulleVariablenWegnehmen(ZA(a[1][4]), "t_{5}") + "}{" + ZA(a[1][1]) + "} " + letzeErgebnisse(a[1][1]) + " \\]";
            speicherS[4] = "\\[ x_{1} = \\frac{" + ZA(a[0][5]) + nulleVariablenWegnehmen(ZA(a[0][1]), "x_{2}") + nulleVariablenWegnehmen(ZA(a[0][2]), "t_{3}") + nulleVariablenWegnehmen(ZA(a[0][3]), "t_{4}") + nulleVariablenWegnehmen(ZA(a[0][4]), "t_{5}") + "}{" + ZA(a[0][0]) + "} " + letzeErgebnisse(a[0][0]) + " \\]";
        } else if (anzahlDieVieleLoesungen == 4) {
            speicherS[0] = "\\[ x_{5} = t_{5} \\]";
            speicherS[1] = "\\[ x_{4} = t_{4} \\]";
            speicherS[2] = "\\[ x_{3} = t_{3} \\]";
            speicherS[3] = "\\[ x_{2} = t_{2} \\]";
            speicherS[4] = "\\[ x_{1} = \\frac{" + ZA(a[0][5]) + nulleVariablenWegnehmen(ZA(a[0][1]), "t_{2}") + nulleVariablenWegnehmen(ZA(a[0][2]), "t_{3}") + nulleVariablenWegnehmen(ZA(a[0][3]), "t_{4}") + nulleVariablenWegnehmen(ZA(a[0][4]), "t_{5}") + "}{" + ZA(a[0][0]) + "} " + letzeErgebnisse(a[0][0]) + " \\]";
        } else if (anzahlDieVieleLoesungen == 5) {
            speicherS[0] = "\\[ x_{5} = t_{5} \\]";
            speicherS[1] = "\\[ x_{4} = t_{4} \\]";
            speicherS[2] = "\\[ x_{3} = t_{3} \\]";
            speicherS[3] = "\\[ x_{2} = t_{2} \\]";
            speicherS[4] = "\\[ x_{1} = t_{1} \\]";
        }
        for (int i = 0; i < zeilen; i++) {
            buffer.schrittListeAdd(speicherS[i]);
        }
    }

    public String nulleVariablenWegnehmen(String s, String bezeichnung) {
        int laenge = s.length();
        if (s.charAt(0) == '0' && laenge == 1) {
            return "";
        } else return "-" + zahlenKlammer(s) + bezeichnung;
    }

    public String letzeErgebnisse(double erster) {
        String loesung = "";
        if (erster == 0) {
            loesung = "= 0";
        }
        return loesung;
    }
}
