package Logik;


import Datenhaltung.Buffer;

/**
 * Created by KOJAR on 04.01.2016.
 */
public class GaussVerwaltung {

    private Buffer buffer;
    private GaussOperationen go;

    public GaussVerwaltung() {

        go = new GaussOperationen();
        buffer = Buffer.getInstance();

    }

    public Matrix gaussRechnen(Matrix m) {

        return go.gaussBerechnen(m);
    }

}
