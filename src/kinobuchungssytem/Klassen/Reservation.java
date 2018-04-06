package kinobuchungssytem.Klassen;

import java.time.LocalTime;
import java.util.ArrayList;

public class Reservation{
    
    private String personVorname;
    private String personNachname;
    private String film;
    private int saal;
    private LocalTime zeit;
    private ArrayList<Platz> plätze;

    public Reservation(String personVorname, String personNachname, String film, int saal, LocalTime zeit, ArrayList<Platz> plätze) {
        this.personVorname = personVorname;
        this.personNachname = personNachname;
        this.film = film;
        this.saal = saal;
        this.zeit = zeit;
        this.plätze = plätze;
    }

    public String getPersonVorname() {
        return personVorname;
    }

    public String getPersonNachname() {
        return personNachname;
    }

    public String getFilm() {
        return film;
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
    
}