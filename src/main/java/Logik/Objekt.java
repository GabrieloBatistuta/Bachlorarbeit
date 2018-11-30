package Logik;

/**
 * Created by KOJAR on 04.01.2016.
 */
public abstract class Objekt {

    protected String beschreibung;
    protected boolean istRichtig;
    protected boolean hatErgebnisse;
    protected double x;
    protected double y;
    protected double z;

    public Objekt(double x, double y, boolean istRichtig, String beschreibung, boolean hatErgebnisse) {
        this.istRichtig = istRichtig;
        this.beschreibung = beschreibung;
        this.x = x;
        this.y = y;
        this.hatErgebnisse = hatErgebnisse;
    }

    public Objekt(double x, double y, double z, boolean istRichtig, String beschreibung, boolean hatErgebnisse) {
        this.istRichtig = istRichtig;
        this.beschreibung = beschreibung;
        this.x = x;
        this.y = y;
        this.z = z;
        this.hatErgebnisse = hatErgebnisse;
    }


    public boolean hatErgebnissee() {
        return hatErgebnisse;
    }

    public abstract String druckeDaten(String name);

    public String getBeschreibung() {
        return beschreibung;
    }

    public double getX() {
        return x;
    }

}