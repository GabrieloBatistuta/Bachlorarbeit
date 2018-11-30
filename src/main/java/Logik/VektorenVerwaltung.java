package Logik;

import Datenhaltung.Buffer;

/**
 * Created by KOJAR on 04.01.2016.
 */
public class VektorenVerwaltung {

    private VektorenOperationen vo;
    private Vektor v;
    private Matrix m;
    private MatrixOperationen mo;
    private Buffer buffer;
    private GaussOperationen go;

    public VektorenVerwaltung() {
        vo = new VektorenOperationen();
        mo = new MatrixOperationen();
        buffer = Buffer.getInstance();
        go = new GaussOperationen();
    }

    public Vektor vektorErzeugen(int zeile, double x, double y, double z) {
        return vo.vektorenErzeugen(zeile, x, y, z);
    }

    public Vektor vektorenAddition(Vektor vA, Vektor vB) {

        return vo.addition(vA, vB);
    }

    public Vektor vektorenSubtrationAminusB(Vektor vA, Vektor vB) {

        return vo.subtrationAminusB(vA, vB);
    }

    public Vektor vektorenSubtrationBminusB(Vektor vA, Vektor vB) {

        return vo.subtrationBminusA(vB, vA);
    }

    public double vektorenMultiplikation(Vektor vA, Vektor vB) {

        return vo.multiplikation(vA, vB, false);
    }

    public Vektor lambdaMalA(Vektor vA, double d) {

        return vo.lambdaMalA(vA, d);
    }

    public Vektor lambdaMalB(Vektor vA, double d) {

        return vo.lambdaMalB(vA, d);
    }


    public double vektorlaengeA(Vektor v) {
        return vo.vektorLaengeA(v, false);
    }

    public double vektorlaengeB(Vektor v) {
        return vo.vektorLaengeB(v, false);
    }


    public void kollinear(Vektor vA, Vektor vB) {
        vo.kollinearVonVektoren(vA, vB);
    }

    public double linearabhaengigkeit(Vektor vA, Vektor vB, Vektor vC) {
        return vo.linearabhaengigkeit(vA, vB, vC);
    }

    public void sindDieVektorenSenkrecht(Vektor vA, Vektor vB) {
        vo.istSenkrecht(vA, vB);
    }

    public double vektorenWinkelBerechnen(Vektor vA, Vektor vB) {

        return vo.winkelBerechnen(vA, vB);
    }

    public Vektor aKreuzB(Vektor vA, Vektor vB) {
        return vo.aKreuzB(vA, vB);
    }

    public Vektor bKreuzA(Vektor vA, Vektor vB) {
        return vo.bKreuzA(vA, vB);
    }

}
