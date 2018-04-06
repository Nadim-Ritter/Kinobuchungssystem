package kinobuchungssytem;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kinobuchungssytem.Klassen.Person;
import kinobuchungssytem.Klassen.Platz;
import kinobuchungssytem.Klassen.Reservation;
import kinobuchungssytem.Klassen.Vorstellung;

public class GuiSitzAuswählenController implements Initializable {

    @FXML
    Label leinwandLabel;

    @FXML
    AnchorPane pane;

    @FXML
    Label meldung;
    
    @FXML
    MenuButton personDetails;

    static int saal;
    static int anzahlPlätze;
    static Person person;
    int countPlätze;

    static ArrayList<Platz> plätze;
    static Vorstellung vorstellung;
    ArrayList<Button> plätzeButton = new ArrayList();
    ArrayList<Reservation> reservationen = new ArrayList();
    ArrayList<Platz> ausgewähltePlätze = new ArrayList();

    public static void setVorstellung(Vorstellung v) {
        saal = v.getSaal();
        anzahlPlätze = v.getPlätze().size();
        plätze = v.getPlätze();
        vorstellung = v;
    }

    public static void setPerson(Person p) {
        person = p;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        personDetails.setText(person.getVorname() + " " + person.getName());
        
        double layoutX = 315;
        double layoutY = 65;
        int count = 0;
        int count2 = 1;

        for (int i = 0; i < anzahlPlätze; i++) {
            Button platzButton = new Button();
            platzButton.setPrefSize(10, 5);
            
            if (plätze.get(i).isBesetzt()) {
                platzButton.setStyle("-fx-background-color: #ff5959;\n"
                        + "-fx-border-color: black;");
            } else {
                platzButton.setStyle("-fx-background-color: #5eff76;\n"
                        + "-fx-border-color: black;");
            }

            platzButton.setLayoutX(layoutX + (20 * count));
            platzButton.setLayoutY(layoutY);
            count++;

            if (count == 10) {
                layoutX = 315;
                layoutY = 65 + (50 * count2);
                count = 0;
                count2++;
            }
            pane.getChildren().add(platzButton);
            plätzeButton.add(platzButton);
            int counterI = i;

            if (!plätze.get(i).isBesetzt()) {
                platzButton.setOnAction((event) -> {
                    if (platzButton.getStyle().equals("-fx-background-color: #5eff76;\n" + "-fx-border-color: black;")) {
                        //auswählen
                        plätze.get(counterI).setBesetzt(true);
                        platzButton.setStyle("-fx-background-color: #42adf4;\n"
                                + "-fx-border-color: black;");
                    } 
                    else {
                        //abwählen
                        plätze.get(counterI).setBesetzt(false);
                        platzButton.setStyle("-fx-background-color: #5eff76;\n"
                                + "-fx-border-color: black;");
                    }

                    countPlätze = 0;
                    for (int y = 0; y < plätzeButton.size(); y++) {
                        if (plätzeButton.get(y).getStyle().equals("-fx-background-color: #42adf4;\n" + "-fx-border-color: black;")) {
                            countPlätze++;
                        }
                    }
                    meldung.setText(countPlätze + " Plätze ausgwählt");
                });
            }
        }
    }

    @FXML
    public void reservationAbschliessen(ActionEvent event) throws IOException {
        if (countPlätze >= 1) {
            meldung.setText("");
            for (int y = 0; y < plätzeButton.size(); y++) {
                if (plätzeButton.get(y).getStyle().equals("-fx-background-color: #42adf4;\n" + "-fx-border-color: black;")) {
                    ausgewähltePlätze.add(plätze.get(y));
                }
            }

            ReservationAbschliessenGuiController.setParameter(person, vorstellung.getFilmTitel(), saal, vorstellung.getZeit(), ausgewähltePlätze);
            Parent RAbschliessenParent = FXMLLoader.load(getClass().getResource("ReservationAbschliessenGui.fxml"));
            Scene RAbschliessenScene = new Scene(RAbschliessenParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(RAbschliessenScene);
            window.show();

        }else{
            meldung.setText("Sie müssen mindesten\n einen Platz auswählen");
        }

    }
    
    @FXML
    public void showDetails(ActionEvent event) throws IOException{
        PersonDetailsController.setLastScene("GuiSitzAuswählen");
        Parent DetailsParent = FXMLLoader.load(getClass().getResource("PersonDetails.fxml"));
        Scene DetailsScene = new Scene(DetailsParent);
        Stage window = (Stage) personDetails.getScene().getWindow();
        window.setScene(DetailsScene);
        window.show();        
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        vorstellung.setPlätze(plätze);
        Parent FilmDetailsParent = FXMLLoader.load(getClass().getResource("GuiFilmDetails.fxml"));
        Scene FilmDetailsScene = new Scene(FilmDetailsParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(FilmDetailsScene);
        window.show();

    }

}
