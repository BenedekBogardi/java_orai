public class Versenyzo {
    private String nev;
    private String orszagKod;
    private double technikaiPont;
    private double komponensPont;
    private double hibapont;
    private double vegsoPont;

    public Versenyzo(String nev, String orszagKod, double technikaiPont, double komponensPont, double hibapont) {
        this.nev = nev;
        this.orszagKod = orszagKod;
        this.technikaiPont = technikaiPont;
        this.komponensPont = komponensPont;
        this.hibapont = hibapont;
        this.vegsoPont = 0;
    }

    public String getNev() {
        return nev;
    }

    public String getOrszagKod() {
        return orszagKod;
    }

    public double getTechnikaiPont() {
        return technikaiPont;
    }

    public double getKomponensPont() {
        return komponensPont;
    }

    public double getHibapont() {
        return hibapont;
    }

    public double getVegsoPont() {
        return vegsoPont;
    }

    public void setVegsoPont(double vegsoPont) {
        this.vegsoPont = vegsoPont;
    }

    @Override
    public String toString() {
        return nev + ";" + orszagKod + ";" + vegsoPont;
    }
}
