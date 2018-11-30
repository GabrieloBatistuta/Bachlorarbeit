package Logik;


import java.util.Random;
import Datenhaltung.Buffer;

/**
 * Created by koko on 02.05.2016.
 */
public class UVektoren {

    private Buffer buffer;
    private Verwaltung vr;

    public UVektoren() {
        buffer = buffer.getInstance();
        vr = new Verwaltung();
    }


    public void vAddieren() {
        vr.vektorenAddition(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2));
    }

    public void vSubtrahieren() {
        vr.vektorenSubtrationAminusB(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2));
    }

    public void vKreuzprodukt() {

        vr.aKreuzB(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2));
    }

    public double vMitZahlMultiplizieren() {
        double z;
        z = randum();
        vr.lambdaMalA(buffer.vListeZurueckgebenABC(1), z);
        return z;
    }

    public void vSenkrecht() {
        vr.sindDieVektorenSenkrecht(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2));
        }

    public void vWinkel() {
        vr.vektorenWinkelBerechnen(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2));
         }

    public void vLaenge() {

        vr.vektorlaengeA(buffer.vListeZurueckgebenABC(1));
        }

    public void vLinearabhaengig() {
        vr.sindDieVektorenAbhaengigg(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2), buffer.vListeZurueckgebenABC(3));
        }

    public void kollinear() {
        vr.kollinear(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2));
        }

    public void vSkalarprodukt() {
        vr.vektorenMultiplikation(buffer.vListeZurueckgebenABC(1), buffer.vListeZurueckgebenABC(2));
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

    public void vektorenErstellen(int anzahl, int dimeinsion) {
        if (anzahl == 2) {
            if (dimeinsion == 2) {
                vr.vektorErzeugen(2, randum(), randum(), randum(), 1);
                vr.vektorErzeugen(2, randum(), randum(), randum(), 2);
            } else if (dimeinsion == 3) {
                vr.vektorErzeugen(3, randum(), randum(), randum(), 1);
                vr.vektorErzeugen(3, randum(), randum(), randum(), 2);
            }
        } else if (anzahl == 3) {
            if (dimeinsion == 2) {
                vr.vektorErzeugen(2, randum(), randum(), randum(), 1);
                vr.vektorErzeugen(2, randum(), randum(), randum(), 2);
                vr.vektorErzeugen(2, randum(), randum(), randum(), 3);
            } else if (dimeinsion == 3) {
                vr.vektorErzeugen(3, randum(), randum(), randum(), 1);
                vr.vektorErzeugen(3, randum(), randum(), randum(), 2);
                vr.vektorErzeugen(3, randum(), randum(), randum(), 3);
            }
        }
    }
}
