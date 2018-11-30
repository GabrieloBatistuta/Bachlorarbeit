package Logik;

import Datenhaltung.Buffer;

/**
 * Created by KOJAR on 04.01.2016.
 */
public class Verwaltung {

    private Buffer buffer;

    private VektorenVerwaltung vv;
    private MatrixVerwaltung mv;
    private GaussVerwaltung gv;

    public Verwaltung() {
        buffer = Buffer.getInstance();
        gv = new GaussVerwaltung();
        vv = new VektorenVerwaltung();
        mv = new MatrixVerwaltung();
    }

    // ##########################################################
    // ##########################################################
    // ###################### Vektoren ##########################
    // ##########################################################
    // ##########################################################

    // wurde gemacht
    public void vektorErzeugen(int zeile, double x, double y, double z, int speicherPlatz) {
        buffer.vListeAddABC(vv.vektorErzeugen(zeile, x, y, z), speicherPlatz);
    }

    public double linearabhaengigkeit(Vektor va, Vektor vb, Vektor vc) {
        return vv.linearabhaengigkeit(va, vb, vc);
    }

    public double vektorenMultiplikation(Vektor vA, Vektor vB) {
        return vv.vektorenMultiplikation(vA, vB);
    }

    // wurde gemacht
    public Vektor vektorenAddition(Vektor vA, Vektor vB) {
        return vv.vektorenAddition(vA, vB);
    }

    // wurde gemacht
    public Vektor vektorenSubtrationAminusB(Vektor vA, Vektor vB) {
        return vv.vektorenSubtrationAminusB(vA, vB);
    }

    public Vektor vektorenSubtrationBminusA(Vektor vA, Vektor vB) {
        return vv.vektorenSubtrationBminusB(vA, vB);
    }

    // wurde gemacht
    public Vektor lambdaMalA(Vektor vA, double d) {
        return vv.lambdaMalA(vA, d);
    }

    public Vektor lambdaMalB(Vektor vA, double d) {
        return vv.lambdaMalB(vA, d);
    }

    // wurde gemacht
    public double vektorlaengeA(Vektor v) {
        return vv.vektorlaengeA(v);
    }

    public double vektorlaengeB(Vektor v) {
        return vv.vektorlaengeB(v);
    }

    // wurde gemacht

    public void kollinear(Vektor vA, Vektor vB) {

        vv.kollinear(vA, vB);
    }

    // wurde gemacht
    public double sindDieVektorenAbhaengigg(Vektor vA, Vektor vB, Vektor vC) {
        return vv.linearabhaengigkeit(vA, vB, vC);
    }

    public void sindDieVektorenSenkrecht(Vektor vA, Vektor vB) {
        vv.sindDieVektorenSenkrecht(vA, vB);
    }

    public double vektorenWinkelBerechnen(Vektor vA, Vektor vB) {
        return vv.vektorenWinkelBerechnen(vA, vB);
    }

    public Vektor aKreuzB(Vektor vA, Vektor vB) {
        return vv.aKreuzB(vA, vB);
    }

    public Vektor bKreuzA(Vektor vA, Vektor vB) {
        return vv.bKreuzA(vA, vB);
    }


    // ##########################################################
    // ##########################################################
    // ###################### Matrizen ##########################
    // ##########################################################
    // ##########################################################

    public Matrix matrixErzeugen(double z, double s) {
        return mv.matrixErzeugen(z, s);
    }

    public Matrix AplusB(Matrix mA, Matrix mB) {
        return mv.AplusB(mA, mB);
    }

    public Matrix AmalB(Matrix mA, Matrix mB) {
        return mv.AmalB(mA, mB);
    }

    public Matrix BmalA(Matrix mA, Matrix mB) {
        return mv.BmalA(mA, mB);
    }

    public Matrix AminusB(Matrix mA, Matrix mB) {
        return mv.AminusB(mA, mB);
    }

    public Matrix BminusA(Matrix mA, Matrix mB) {
        return mv.BminusA(mA, mB);
    }

    public Matrix lambdaMalB(Matrix mA, double d) {
        return mv.lambdaMalB(mA, d);
    }

    public Matrix lambdaMalA(Matrix mA, double d) {
        return mv.lambdaMalA(mA, d);
    }

    public Matrix aTransponieren(Matrix mA) {
        return mv.aTransponieren(mA);
    }

    public Matrix bTransponieren(Matrix mA) {
        return mv.bTransponieren(mA);
    }

    public void istAsymetrisch(Matrix mA) {
        mv.istAsymetrisch(mA);
    }

    public void istBsymetrisch(Matrix mA) {
        mv.istBsymetrisch(mA);
    }


    public Matrix inverseA(Matrix m) {
        return mv.inverseA(m);
    }

    public Matrix inverseA2(Matrix m) {
        return mv.inverseA2(m);
    }

    public Matrix inverseB(Matrix m) {
        return mv.inverseB(m);
    }

    public Matrix zweiDinverseB(Matrix m) {
        return mv.zweiDinverseB(m);
    }

    public Matrix zweiDinverseA(Matrix m) {
        return mv.zweiDinverseA(m);
    }

    public double determinanteA(Matrix mA, boolean loeschen) {
        return mv.determinanteA(mA, loeschen);
    }

    public double uebungDeterminanteA(Matrix mA) {
        return mv.uebungDeterminanteA(mA);
    }

    public double determinanteB(Matrix mA) {
        return mv.determinanteB(mA);
    }

    // ##########################################################
    // ##########################################################
    // ##################### Gauß-Schema #######################
    // ##########################################################
    // ##########################################################

    public Matrix gaussBerechnen(Matrix m) {
        return gv.gaussRechnen(m);
    }


}



