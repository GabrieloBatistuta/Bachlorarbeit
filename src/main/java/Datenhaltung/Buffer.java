package Datenhaltung;

import java.util.ArrayList;
import java.util.Iterator;
import Logik.Matrix;
import Logik.Vektor;

public class Buffer {

    private static Buffer instance = null;


    ArrayList<String> schritteListe = new ArrayList<String>();

    int[] navigation;

    ArrayList<Vektor> vListeA = new ArrayList<Vektor>();
    ArrayList<Vektor> vListeB = new ArrayList<Vektor>();
    ArrayList<Vektor> vListeC = new ArrayList<Vektor>();

    ArrayList<Matrix> mListeA = new ArrayList<Matrix>();
    ArrayList<Matrix> mListeB = new ArrayList<Matrix>();
    ArrayList<Matrix> mListeC = new ArrayList<Matrix>();

    ArrayList<Matrix> gListe2 = new ArrayList<Matrix>();
    ArrayList<Matrix> gListe3 = new ArrayList<Matrix>();
    ArrayList<Matrix> gListe4 = new ArrayList<Matrix>();
    ArrayList<Matrix> gListe5 = new ArrayList<Matrix>();

    ArrayList<String> infoListe = new ArrayList<String>();

    public String[] sRichtung = new String[1];
    public int[] gaussRichtung = new int[1];
    public double[] skalarListe = {0};

    public static Buffer getInstance() {
        if (instance == null) {
            instance = new Buffer();
        }
        return instance;
    }

    //////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    /////////////Schritliste/////////////////////////////////
    /////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////
    public void schrittListeAdd(String s) {
        schritteListe.add(s);
    }

    public String schrittListeZurueckgeben() {
        String s = "";
        Iterator<String> it = schritteListe.iterator();
        while (it.hasNext()) {
            s = s + it.next();
        }
        return s;
    }

    public void schrittListeLeeren() {
        schritteListe.clear();
    }

    public ArrayList<String> getSchritteListe() {
        return schritteListe;
    }

    //////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    /////////////InfosListe/////////////////////////////////
    /////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////
    public void infoListeAdd(String s) {
        infoListe.add(s);
    }

    public String infoListeZurueckgeben() {
        String s = "";
        Iterator<String> it = infoListe.iterator();
        while (it.hasNext()) {
            s = s + it.next();
        }
        return s;
    }

    public void infoListeLeeren() {
        infoListe.clear();
    }

    //////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    /////////////Navigationliste////////////////////////////
    /////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////
    public void setNavigation(int i) {
        navigation = new int[1];
        navigation[0] = i;
    }

    public int getNavigation() {
        return navigation[0];
    }

    //////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    /////////////Vektorenlisten////////////////////////////
    /////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////
    public void vListeAddABC(Vektor v, int a) {
        if (a == 1) {
            vListeA.clear();
            vListeA.add(v);
        } else if (a == 2) {
            vListeB.clear();
            vListeB.add(v);
        } else if (a == 3) {
            vListeC.clear();
            vListeC.add(v);
        }
    }

    public Vektor vListeZurueckgebenABC(int a) {
        if (a == 1) {
            return vListeA.get(0);
        } else if (a == 2) {
            return vListeB.get(0);
        } else if (a == 3) {
            return vListeC.get(0);
        } else
            return null;
    }

    public boolean vListeIsEmptyABC(int a) {
        if (a == 1) {
            return vListeA.isEmpty();
        } else if (a == 2) {
            return vListeB.isEmpty();
        } else if (a == 3) {
            return vListeC.isEmpty();
        } else return false;
    }

    public void vListeLeeren() {
        vListeA.clear();
        vListeB.clear();
        vListeC.clear();
        setSkalarListe(0);
    }

    //////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    /////////////Matrixenlisten//////////////////////////////
    /////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////
    public void mListeAddABC(Matrix m, int a) {
        if (a == 1) {
            mListeA.clear();
            mListeA.add(m);
        } else if (a == 2) {
            mListeB.clear();
            mListeB.add(m);
        } else if (a == 3) {
            mListeC.clear();
            mListeC.add(m);
        }
    }

    public Matrix mListeZurueckgebenABC(int a) {
        if (a == 1) {
            return mListeA.get(0);
        } else if (a == 2) {
            return mListeB.get(0);
        } else {
            return mListeC.get(0);
        }
    }


    public boolean mListeIsEmptyABC(int a) {
        if (a == 1) {
            return mListeA.isEmpty();
        } else if (a == 2) {
            return mListeB.isEmpty();
        } else {
            return mListeC.isEmpty();
        }
    }

    public void mListeLeeren() {
        mListeA.clear();
        mListeB.clear();
        mListeC.clear();
        setSkalarListe(0);
    }

    //////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    /////////////Gausslisten//////////////////////////////
    /////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////
    public boolean gListeIsEmpty(int i) {
        if (i == 2)
            return gListe2.isEmpty();
        else if (i == 3)
            return gListe3.isEmpty();
        else if (i == 4)
            return gListe4.isEmpty();
        else if (i == 5)
            return gListe5.isEmpty();
        else
            return false;
    }

    public void gListeAddABCD(Matrix m, int a) {
        if (a == 2) {
            gListe2.clear();
            gListe2.add(m);
        } else if (a == 3) {
            gListe3.clear();
            gListe3.add(m);
        } else if (a == 4) {
            gListe4.clear();
            gListe4.add(m);
        } else {
            gListe5.clear();
            gListe5.add(m);
        }
    }

    public Matrix gListeZurueckgebenABCD(int a) {
        if (a == 2) {
            return gListe2.get(0);
        } else if (a == 3) {
            return gListe3.get(0);
        } else if (a == 4) {
            return gListe4.get(0);
        } else {
            return gListe5.get(0);
        }
    }
    public void gListeLeeren() {
        gListe2.clear();
        gListe3.clear();
        gListe4.clear();
        gListe5.clear();
    }

    //////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    /////////////Richtungliste//////////////////////////////
    /////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////

    public void setsRichtung(String s) {
        sRichtung[0] = s;
    }

    //////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    /////////////Skalarliste/////////////////////////////////
    /////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////
    public void setSkalarListe(double d) {
        skalarListe[0] = d;
    }

    public double getSkalarListe() {
        return skalarListe[0];
    }

}
