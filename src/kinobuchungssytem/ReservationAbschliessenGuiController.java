package kinobuchungssytem;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;
import static kinobuchungssytem.GuiSitzAuswählenController.vorstellung;
import kinobuchungssytem.Klassen.Person;
import kinobuchungssytem.Klassen.Platz;
import kinobuchungssytem.Klassen.Reservation;

public class ReservationAbschliessenGuiController implements Initializable {

    @FXML
    Label name;

    @FXML
    Label filmLabel;

    @FXML
    Label saalLabel;

    @FXML
    Label zeitLabel;

    @FXML
    Label plätzeLabel;

    @FXML
    Label preisPTLabel;

    @FXML
    Label preisGLabel;

    @FXML
    Label meldung;
    
    @FXML
    MenuButton personDetails;

    static Person person;
    static String film;
    static int saal;
    static LocalTime zeit;
    static ArrayList<Platz> plätze;
    boolean done = false;

    public static void setParameter(Person pers, String f, int s, LocalTime z, ArrayList<Platz> p) {
        person = pers;
        film = f;
        saal = s;
        zeit = z;
        plätze = p;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        personDetails.setText(person.getVorname() + " " + person.getName());
        
        name.setText(person.getVorname() + " " + person.getName());
        filmLabel.setText(film);
        saalLabel.setText(Integer.toString(saal));
        zeitLabel.setText(zeit + "");

        String plätzeString = "";
        for (int i = 0; i < plätze.size(); i++) {
            if (i < plätze.size() - 1) {
                plätzeString += plätze.get(i).getPlatznummer() + ", ";
            } else {
                plätzeString += plätze.get(i).getPlatznummer();
            }

        }

        plätzeLabel.setText(plätzeString);
        preisPTLabel.setText("10Fr.");
        preisGLabel.setText(plätze.size() * 10 + "Fr.");

    }

    @FXML
    public void bestätigen() {
        if (done == false) {
            Reservation r1 = new Reservation(person.getVorname(), person.getName(), film, saal, zeit, plätze);

            for (int i = 0; i < Kinobuchungssytem.personen.size(); i++) {
                if (Kinobuchungssytem.personen.get(i).getVorname().equals(person.getVorname()) && Kinobuchungssytem.personen.get(i).getName().equals(person.getName())) {
                    Kinobuchungssytem.personen.get(i).addReservation(r1);
                }
            }

            meldung.setText("Vielen Dank für ihre Reservation");
            done = true;
        }else{
            meldung.setText("Sie haben diese Reservation bereits bestätigt");
        }

    }
    
    @FXML
    public void showDetails(ActionEvent event) throws IOException{
        PersonDetailsController.setLastScene("ReservationAbschliessenGui");
        Parent DetailsParent = FXMLLoader.load(getClass().getResource("PersonDetails.fxml"));
        Scene DetailsScene = new Scene(DetailsParent);
        Stage window = (Stage) personDetails.getScene().getWindow();
        window.setScene(DetailsScene);
        window.show();        
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        Parent SitzAParent = FXMLLoader.load(getClass().getResource("GuiSitzAuswählen.fxml"));
        Scene SitzAScene = new Scene(SitzAParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(SitzAScene);
        window.show();

    }

}
