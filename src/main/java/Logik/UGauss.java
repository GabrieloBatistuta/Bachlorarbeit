package Logik;

import java.util.Random;
import Datenhaltung.Buffer;

/**
 * Created by koko on 19.05.2016.
 */
public class UGauss {

    private Buffer buffer;
    private Verwaltung vr;
    private MatrixOperationen mo;
    private GaussVerwaltung gv;

    public UGauss() {
        buffer = buffer.getInstance();
        vr = new Verwaltung();
        gv = new GaussVerwaltung();
        mo = new MatrixOperationen();
    }

    public void gaussErstellen(int unbekannten) {
        Matrix gauss = null;
        if (unbekannten == 2) {
            gauss = zahlenAusfuehlen(mo.matrixErzeugen(2, 3));
        } else if (unbekannten == 3) {
            gauss =zahlenAusfuehlen(mo.matrixErzeugen(3, 4));
        } else if (unbekannten == 4) {
            gauss = zahlenAusfuehlen(mo.matrixErzeugen(4, 5));
        } else if (unbekannten == 5) {
            gauss = zahlenAusfuehlen(mo.matrixErzeugen(5, 6));
        }
        buffer.gListeAddABCD(gauss, unbekannten);
    }

    public void gaussRechnen(Matrix m) {
        gv.gaussRechnen(m);
    }

    public double randum() {
        Random rn = new Random();
        double zahl = rn.nextInt(11);
        double zeichnen = rn.nextInt(11);
        if (zahl == 0) {
            return zahl;
        } else if (zahl != 0 && zeichnen % 2 == 0) {
            return zahl;
        } else
            return ((-1) * zahl);
    }

    public Matrix zahlenAusfuehlen(Matrix m) {
        Matrix matrix = m;
        for (int i = 0; i < m.getZeile(); i++) {
            for (int j = 0; j < m.getSpalten(); j++) {
                matrix.setDoubleIndexPosition(i, j, randum());
            }
        }
        return matrix;
    }
}
