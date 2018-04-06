package kinobuchungssytem;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static kinobuchungssytem.GuiAuswahlController.person;
import kinobuchungssytem.Klassen.Person;

public class GuiFilmDetailsController implements Initializable {

    @FXML
    Label titelFilmName;

    @FXML
    AnchorPane pane;

    @FXML
    VBox trailerBox;

    @FXML
    Label vorstellungenTitel;
    
    @FXML
    MenuButton personDetails;
    
    
    static Person person;
    static String filmName = "";
    ArrayList<kinobuchungssytem.Klassen.Vorstellung> vorstellungenFilmList = new ArrayList();

    public static void setFilmName(String filmName) {
        GuiFilmDetailsController.filmName = filmName;
    }
    
    public static void setPerson(Person p){
        person = p;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        personDetails.setText(person.getVorname() + " " + person.getName());
        //set Title
        titelFilmName.setText(filmName);

        //set Image
        ImageView iv = new ImageView();
        Image image = new Image("file:BilderFilme/" + filmName + ".jpg");
        iv.setImage(image);
        iv.setFitWidth(200);
        iv.setPreserveRatio(true);
        iv.setLayoutX(trailerBox.getLayoutX());
        iv.setLayoutY(trailerBox.getLayoutY() + trailerBox.getPrefHeight() + 10);
        pane.getChildren().add(iv);

        //set Vorstellungen
        for (int i = 0; i < Kinobuchungssytem.vorstellungen.size(); i++) {
            if (Kinobuchungssytem.vorstellungen.get(i).getFilmTitel().equals(filmName)) {
                vorstellungenFilmList.add(Kinobuchungssytem.vorstellungen.get(i));
            }
        }

        for (int i = 0; i < vorstellungenFilmList.size(); i++) {
            Label saalLabel = new Label("Saal " + vorstellungenFilmList.get(i).getSaal());
            Label zeitLabel = new Label(vorstellungenFilmList.get(i).getZeit() + "");
            Button buttonBuchen = new Button("jetzt Buchen");
            
            saalLabel.setLayoutX(vorstellungenTitel.getLayoutX());
            saalLabel.setLayoutY(vorstellungenTitel.getLayoutY() + (50 * (i+1)));

            zeitLabel.setLayoutX(vorstellungenTitel.getLayoutX() + 75);
            zeitLabel.setLayoutY(vorstellungenTitel.getLayoutY() + (50 * (i+1)));

            buttonBuchen.setLayoutX(vorstellungenTitel.getLayoutX() + 150);
            buttonBuchen.setLayoutY(vorstellungenTitel.getLayoutY() + (50 * (i+1)));
            int bla = i;
            
            buttonBuchen.setOnAction((event) -> {
                GuiSitzAuswählenController.setVorstellung(vorstellungenFilmList.get(bla));
                
                try {
                    Parent SitzAuswählenParent = FXMLLoader.load(getClass().getResource("GuiSitzAuswählen.fxml"));
                    Scene SitzAuswählenScene = new Scene(SitzAuswählenParent);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(SitzAuswählenScene);
                    window.show();
                } catch (IOException ex) {
                    Logger.getLogger(GuiAuswahlController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

            pane.getChildren().addAll(saalLabel, zeitLabel, buttonBuchen);
        }

    }
    
    @FXML
    public void showDetails(ActionEvent event) throws IOException{
        PersonDetailsController.setLastScene("GuiFilmDetails");
        Parent DetailsParent = FXMLLoader.load(getClass().getResource("PersonDetails.fxml"));
        Scene DetailsScene = new Scene(DetailsParent);
        Stage window = (Stage) personDetails.getScene().getWindow();
        window.setScene(DetailsScene);
        window.show();        
    }
    

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent AuswahlParent = FXMLLoader.load(getClass().getResource("GuiAuswahl.fxml"));
        Scene AuswahlScene = new Scene(AuswahlParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(AuswahlScene);
        window.show();
    }

}