package kinobuchungssytem.Klassen;

public class Kinosaal{
    
    private int saal;
    private int anzahlPlätze;

    public Kinosaal(int saal) {
        this.saal = saal;
    }

    
    public Kinosaal(int saal, int anzahlPlätze) {
        this.saal = saal;
        this.anzahlPlätze = anzahlPlätze;
    } 

    public int getSaal() {
        return saal;
    }

    public int getAnzahlPlätze() {
        return anzahlPlätze;
    }
    
    
}
