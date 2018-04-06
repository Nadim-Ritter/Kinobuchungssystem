package kinobuchungssytem.Klassen;

public class Platz extends Kinosaal{
    
    private int platzreihe;
    private int platznummer;
    private boolean besetzt;

    public Platz(int saal, int anzahlPlätze) {
        super(saal, anzahlPlätze);
    }

    public Platz(int platzreihe, int platznummer, int saal, int anzahlPlätze) {
        super(saal, anzahlPlätze);
        this.platzreihe = platzreihe;
        this.platznummer = platznummer;
    }

    public Platz(int platzreihe, int platznummer, boolean besetzt, int saal) {
        super(saal);
        this.platzreihe = platzreihe;
        this.platznummer = platznummer;
        this.besetzt = besetzt;
    }

    public boolean isBesetzt() {
        return besetzt;
    }

    public void setBesetzt(boolean besetzt) {
        this.besetzt = besetzt;
    }

    public int getPlatznummer() {
        return platznummer;
    }
    
}
