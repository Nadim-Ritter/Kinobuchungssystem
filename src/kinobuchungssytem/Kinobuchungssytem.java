package kinobuchungssytem;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kinobuchungssytem.Klassen.Kinosaal;
import kinobuchungssytem.Klassen.Person;
import kinobuchungssytem.Klassen.Platz;
import kinobuchungssytem.Klassen.Vorstellung;

public class Kinobuchungssytem extends Application {
    
    public static ArrayList<Kinosaal> kinosäle = new ArrayList();
    public static ArrayList<Vorstellung> vorstellungen = new ArrayList();
    public static ArrayList<String> filme = new ArrayList();
    public static ArrayList<Person> personen = new ArrayList();
    
    
    public static void main(String[] args) {
        Person p1 = new Person("Müller", "Heinz", "04412345612", "müllerheinz@gmail.com", "test");
        Person p2 = new Person("Meier", "Ruedi", "04412345612", "ruedimeier@gmail.com", "test");
        Person p3 = new Person("Muster", "Max", "04412345612", "maxmuster@gmail.com", "test");
        Person p4 = new Person("Ritter", "Nadim", "04412345612", "nadimritter@gmail.com", "test");
        Person p5 = new Person("Obama", "Barack", "04412345612", "barackobama@gmail.com", "test");
        
        personen.add(p1);
        personen.add(p2);
        personen.add(p3);
        personen.add(p4);
        personen.add(p5);
        
        Kinosaal saal1 = new Kinosaal(1, 100);
        Kinosaal saal2 = new Kinosaal(2, 100);
        Kinosaal saal3 = new Kinosaal(3, 100);
        Kinosaal saal4 = new Kinosaal(4, 100);
        Kinosaal saal5 = new Kinosaal(5, 100);
        
        kinosäle.add(saal1);
        kinosäle.add(saal2);
        kinosäle.add(saal3);
        kinosäle.add(saal4);
        kinosäle.add(saal5);
        
        filme.add("Batman");
        filme.add("HarryPotter");
        filme.add("HerrDerRinge");
        filme.add("Avatar");
        filme.add("Inception");      
        
        Vorstellung vorstellung1 = new Vorstellung("Batman", saal1.getSaal(), LocalTime.of(10, 0));
        Vorstellung vorstellung12 = new Vorstellung("Batman", saal1.getSaal(), LocalTime.of(12, 0));
        Vorstellung vorstellung13 = new Vorstellung("Batman", saal1.getSaal(), LocalTime.of(14, 0));
        Vorstellung vorstellung14 = new Vorstellung("Batman", saal1.getSaal(), LocalTime.of(16, 0));
        
        Vorstellung vorstellung2 = new Vorstellung("HarryPotter", saal2.getSaal(), LocalTime.of(12, 0));
        Vorstellung vorstellung3 = new Vorstellung("HerrDerRinge", saal3.getSaal(), LocalTime.of(14, 0));
        Vorstellung vorstellung4 = new Vorstellung("Avatar", saal4.getSaal(), LocalTime.of(16, 0));
        Vorstellung vorstellung5 = new Vorstellung("Inception", saal5.getSaal(), LocalTime.of(18, 0));
        
        vorstellungen.add(vorstellung1);
        vorstellungen.add(vorstellung12);
        vorstellungen.add(vorstellung13);
        vorstellungen.add(vorstellung14);
        
        vorstellungen.add(vorstellung2);
        vorstellungen.add(vorstellung3);
        vorstellungen.add(vorstellung4);
        vorstellungen.add(vorstellung5);
        
        
        for(int i = 0; i < vorstellungen.size(); i++){
            ArrayList<Platz> platzList = new ArrayList();
            int count = 1;
            int saal = vorstellungen.get(i).getSaal();
            for(int y = 0; y < kinosäle.get(saal-1).getAnzahlPlätze(); y++){
                Platz platz = new Platz(count, y, false, saal);
                platzList.add(platz);
                if(count == 10) count++;
            }
            vorstellungen.get(i).setPlätze(platzList);
        }
        
        
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginGui.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
}