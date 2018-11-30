package Logik;

import Datenhaltung.Buffer;

/**
 * Created by KOJAR on 04.01.2016.
 */
public class MatrixVerwaltung {

    private MatrixOperationen mo;
    private Buffer buffer;


    public MatrixVerwaltung() {

        mo = new MatrixOperationen();
        buffer = Buffer.getInstance();
    }

    public Matrix matrixErzeugen(double s, double z) {
        Matrix ha = mo.matrixErzeugen(s, z);
        return ha;
    }


    public Matrix AplusB(Matrix mA, Matrix mB) {
        return mo.AplusB(mA, mB);
    }

    public Matrix AminusB(Matrix mA, Matrix mB) {
        return mo.AminusB(mA, mB);
    }

    public Matrix BminusA(Matrix mA, Matrix mB) {
        return mo.BminusA(mA, mB);
    }

    public Matrix AmalB(Matrix mA, Matrix mB) {
        return mo.AmalB(mA, mB);
    }

    public Matrix BmalA(Matrix mA, Matrix mB) {
        return mo.BmalA(mA, mB);
    }


    public Matrix lambdaMalA(Matrix mA, double d) {
        return mo.lambdaMalA(mA, d, 1, false);
    }

    public Matrix lambdaMalB(Matrix mA, double d) {
        return mo.lambdaMalB(mA, d, 1, false);
    }


    public Matrix bTransponieren(Matrix mA) {
        return mo.bTransponieren(mA, true);
    }

    public Matrix aTransponieren(Matrix mA) {
        return mo.aTransponieren(mA, true);
    }

    public void istAsymetrisch(Matrix mA) {
        mo.istAsymetrisch(mA);
    }

    public void istBsymetrisch(Matrix mA) {
        mo.istBsymetrisch(mA);
    }

    public Matrix inverseA(Matrix m) {
        return mo.inverseA3(m);
    }
    public Matrix inverseA2(Matrix m) {
        return mo.zweiDimensionInverseA(m);
    }

    public Matrix inverseB(Matrix m) {
        return mo.inverseB3(m);
    }

    public Matrix zweiDinverseB(Matrix m) {
        return mo.zweiDimensionInverseB(m);
    }

    public Matrix zweiDinverseA(Matrix m) {
        return mo.zweiDimensionInverseA(m);
    }


    public double determinanteA(Matrix mA,boolean loeschen) {
        return mo.determinanteA(mA, loeschen);
    }
    public double uebungDeterminanteA(Matrix mA) {
        return mo.uebungDeterminanteA(mA);
    }

    public double determinanteB(Matrix mA) {
        return mo.determinanteB(mA, false);
    }
}
