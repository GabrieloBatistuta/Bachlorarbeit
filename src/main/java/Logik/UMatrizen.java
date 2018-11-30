package Logik;

import java.util.Random;
import Datenhaltung.Buffer;

/**
 * Created by koko on 18.05.2016.
 */
public class UMatrizen {

    private Buffer buffer;
    private Verwaltung vr;
    public UMatrizen() {
        buffer = buffer.getInstance();
        vr = new Verwaltung();
    }

    public void matrizenErstellen(int z, int s, int anzahl) {
        Matrix m1 = vr.matrixErzeugen(z, s);
        if (anzahl == 2) {
            Matrix m2 = vr.matrixErzeugen(z, s);
            for (int i = 0; i < z; i++) {
                for (int j = 0; j < s; j++) {
                    m1.setDoubleIndexPosition(i, j, randum());
                    m2.setDoubleIndexPosition(i, j, randum());
                }
            }

            buffer.mListeAddABC(m1, 1);
            buffer.mListeAddABC(m2, 2);
        } else if (anzahl == 1) {
            for (int i = 0; i < z; i++) {
                for (int j = 0; j < s; j++) {
                    m1.setDoubleIndexPosition(i, j, randum());
                }
            }
            buffer.mListeAddABC(m1, 1);
        }
    }

    public void mAddieren() {
        vr.AplusB(buffer.mListeZurueckgebenABC(1), buffer.mListeZurueckgebenABC(2));
    }

    public void mSubtrahieren() {

        vr.AminusB(buffer.mListeZurueckgebenABC(1), buffer.mListeZurueckgebenABC(2));

    }

    public void mMultiplizieren() {
        vr.AmalB(buffer.mListeZurueckgebenABC(1), buffer.mListeZurueckgebenABC(2));
    }

    public void mTransponieren() {
       vr.aTransponieren(buffer.mListeZurueckgebenABC(1));
    }
    public Matrix mTransponierenUndZurueckgeben(Matrix m){
        return vr.aTransponieren(m);
    }

    public void mDeterminante() {
        vr.uebungDeterminanteA(buffer.mListeZurueckgebenABC(1));
    }

    public void mSymmetrisch() {
       vr.istAsymetrisch(buffer.mListeZurueckgebenABC(1));
    }

    public void mMitzahlmultiplizieren() {
        double z = randum();
        buffer.setSkalarListe(z);
        vr.lambdaMalA(buffer.mListeZurueckgebenABC(1), z);
    }

    public void mInverse() {
        vr.inverseA(buffer.mListeZurueckgebenABC(1));
       }
    public void mInverseA2() {
         vr.inverseA2(buffer.mListeZurueckgebenABC(1));
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

}
