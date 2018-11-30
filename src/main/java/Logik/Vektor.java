package Logik;


/**
 * Created by KOJAR on 04.01.2016.
 */
public class Vektor extends Objekt {

    protected double[] vektorenArray;
    public int grosse = 0;

    public Vektor(double x, double y, boolean istRichtig, String beschreibung, boolean hatErgebnisse) {
        super(x, y, istRichtig, beschreibung, hatErgebnisse);
        vektorenArray = new double[2];
        vektorenArray[0] = x;
        vektorenArray[1] = y;
        grosse = 2;
        super.istRichtig = istRichtig;
        super.beschreibung = beschreibung;
        super.hatErgebnisse = hatErgebnisse;
    }

    public Vektor(double x, double y, double z, boolean istRichtig, String beschreibung, boolean hatErgebnisse) {
        super(x, y, z, istRichtig, beschreibung, hatErgebnisse);
        vektorenArray = new double[3];
        vektorenArray[0] = x;
        vektorenArray[1] = y;
        vektorenArray[2] = z;
        grosse = 3;
        super.istRichtig = istRichtig;
        super.beschreibung = beschreibung;
        super.hatErgebnisse = hatErgebnisse;
    }

    public double getPV(int i) {
        if (i == 1)
            return vektorenArray[0];
        else if (i == 2)
            return vektorenArray[1];
        else
            return vektorenArray[2];
    }

    public String druckeDaten(String vektorName) {
        String schritte="";
        if (this.istRichtig && this.hatErgebnissee() && super.getX() == -0.123456789) {
            return getBeschreibung();
        }
        if (this.grosse == 2) {
            schritte ="\\vec{"+vektorName+"}=\\left(\\begin{array}{c} "+ZA(this.getPV(1))+" \\\\ "+ ZA(this.getPV(2))+" \\end{array}\\right) ";
      return schritte;
        } else
            schritte ="\\vec{"+vektorName+"}=\\left(\\begin{array}{c} "+ZA(this.getPV(1))+" \\\\ "+ ZA(this.getPV(2))+" \\\\ " + ZA(this.getPV(3)) + " \\end{array}\\right) ";
        return schritte;
    }

    public String toString() {
        double a, b, c = 0;
        if (this.grosse > 2) {
            a = getPV(1);
            b = getPV(2);
            c = getPV(3);
            return a + "\n" + b + "\n" + c;
        } else {
            a = getPV(1);
            b = getPV(2);
            return a + "\n" + b;
        }
    }

    public String getBeschreibung() {
        return super.getBeschreibung();
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
