package kinobuchungssytem.Klassen;

import java.time.LocalTime;
import java.util.ArrayList;

public class Vorstellung{
    
    private String filmTitel;
    private int saal;
    private LocalTime zeit;
    private ArrayList<Platz> plätze = new ArrayList();
    
    public Vorstellung(String filmTitel, int saal, LocalTime zeit) {
        this.filmTitel = filmTitel;
        this.saal = saal;
        this.zeit = zeit;
    }

    public String getFilmTitel() {
        return filmTitel;
    }

    public int getSaal() {
        return saal;
    }

    public LocalTime getZeit() {
        return zeit;
    }

    public ArrayList<Platz> getPlätze() {
        return plätze;
    }

    public void setPlätze(ArrayList<Platz> plätze) {
        this.plätze = plätze;
    }
    
}