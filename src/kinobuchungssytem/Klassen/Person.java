package kinobuchungssytem.Klassen;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Person{

    private String name;
    private String vorname;
    private String telefonnummer;
    private String email;
    private String passwort;
    private ArrayList<Reservation> reservationen = new ArrayList();


    public Person(String name, String vorname, String telefonnummer, String email, String passwort) {
        this.name = name;
        this.vorname = vorname;
        this.telefonnummer = telefonnummer;
        this.email = email;
        this.passwort = passwort;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getName() {
        return name;
    }

    public String getVorname() {
        return vorname;
    }

    public void addReservation(Reservation r){
        reservationen.add(r);
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public ArrayList<Reservation> getReservationen() {
        return reservationen;
    }
    
    
    
    
    
    








}