package Logik;

/**
 * Created by KOJAR on 04.01.2016.
 */
public class Matrix extends Objekt {

    private double[][] matrixArray;
    private int zeile = 0;
    private int spalten = 0;

    public Matrix(int x, int y, boolean istRichtig, String beschreibung, boolean hatErgebnisse) {
        super(x, y, istRichtig, beschreibung, hatErgebnisse);
        matrixArray = new double[x][y];
        this.zeile = x;
        this.spalten = y;
        super.istRichtig = istRichtig;
        super.beschreibung = beschreibung;
        super.hatErgebnisse = hatErgebnisse;
    }

    public int getZeile() {
        return zeile;
    }

    public int getSpalten() {
        return spalten;
    }

    public String getStringIndexPosition(int i, int j) {
        String string;
        double wert;
        wert = matrixArray[i][j];
        string = ZA(wert);
        return string;
    }

    public double getDoubleIndexPosition(int i, int j) {
        return matrixArray[i][j];
    }

    public void setDoubleIndexPosition(int i, int j, double d) {
        matrixArray[i][j] = d;
    }

    public String druckeDaten(String matrixName) {
        String zeilen = "\\\\";
        String spalten = "&";
        String zeichnen = "";

        for (int i = 0; i < this.getZeile(); i++) {
            for (int j = 0; j < this.getSpalten(); j++) {
                if (j + 1 < this.getSpalten()) {
                    zeichnen = zeichnen + ZA(this.getDoubleIndexPosition(i, j)) + spalten;
                } else {
                    zeichnen = zeichnen + ZA(this.getDoubleIndexPosition(i, j));
                }
            }
            zeichnen = zeichnen + zeilen;
        }
        if (matrixName.equals("")) {
            String matrize = "\\begin{bmatrix} " + zeichnen + " \\end{bmatrix}";
            return matrize;
        } else {
            String matrize = matrixName + "=" + " \\begin{bmatrix} " + zeichnen + " \\end{bmatrix}";
            return matrize;
        }
    }
    public String druckedatenGauss(){
        String zeilen = "\\\\";
        String spalten = "&";
        String zeichnen = "";

        for (int i = 0; i < this.getZeile(); i++) {
            for (int j = 0; j < this.getSpalten(); j++) {
                if (j + 1 < this.getSpalten()) {
                    if (j==0)
                        zeichnen = zeichnen +ZA(this.getDoubleIndexPosition(i, j))+"x_{"+(j+1)+"}"+ spalten;
                    else
                        zeichnen = zeichnen + zahlenKlammer( ZA(this.getDoubleIndexPosition(i, j))) + "x_{" + (j + 1) + "}" + spalten;
                } else {
                    zeichnen = zeichnen + "="+ZA(this.getDoubleIndexPosition(i, j));
                }
            }
            zeichnen = zeichnen + zeilen;
        }
        return "\\[ \\begin{matrix}"+zeichnen+" \\end{matrix} \\]";
    }

    public String zahlenKlammer(String s) {
        char zahl = s.charAt(0);
        if (zahl == '-') {
            return   s;
        } else {
            return "+" +s;
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
}
