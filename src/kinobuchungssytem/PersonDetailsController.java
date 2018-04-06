package kinobuchungssytem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kinobuchungssytem.Klassen.Person;

public class PersonDetailsController implements Initializable {
    
    @FXML
    Label titel;
    
    @FXML
    Label emailLabel;
    
    @FXML
    Label telLabel;    
    
    @FXML
    AnchorPane pane;
    
                  
    static String lastScene;
    static Person person;

    public static void setLastScene(String s) {
        lastScene = s;
    }
    
    public static void setPerson(Person p) {
        person = p;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Label filmLabel = new Label("Film");
        filmLabel.setLayoutX(15);
        filmLabel.setLayoutY(278);
        
        Label saalLabel = new Label("Saal");
        saalLabel.setLayoutX(155);
        saalLabel.setLayoutY(278);
        
        Label zeitLabel = new Label("Zeit");
        zeitLabel.setLayoutX(300);
        zeitLabel.setLayoutY(278);
        
        Label plätzeLabel = new Label("Plätze");
        plätzeLabel.setLayoutX(441);
        plätzeLabel.setLayoutY(278);
        
        Label buttonLoc = new Label();
        buttonLoc.setLayoutX(559);
        buttonLoc.setLayoutY(278);
        
        pane.getChildren().addAll(filmLabel, saalLabel, zeitLabel, plätzeLabel, buttonLoc);
        
        titel.setText(person.getVorname() + " " + person.getName() + "'s Profil");
        emailLabel.setText(person.getEmail());
        telLabel.setText(person.getTelefonnummer());
        
        for(int i = 0; i < person.getReservationen().size(); i++){
            Label film = new Label(person.getReservationen().get(i).getFilm());
            Label saal = new Label(Integer.toString(person.getReservationen().get(i).getSaal()));
            Label zeit = new Label(person.getReservationen().get(i).getZeit() + "");
            Label plätze = new Label();
            
            String plätzeString = "";
            for(int y = 0; y < person.getReservationen().get(i).getPlätze().size(); y++){
                if(y < person.getReservationen().get(i).getPlätze().size()-1){
                    plätzeString += person.getReservationen().get(i).getPlätze().get(y).getPlatznummer() + ", ";
                }else{
                    plätzeString += person.getReservationen().get(i).getPlätze().get(y).getPlatznummer();
                }
            }
            plätze.setText(plätzeString);
            
            Button stonieren = new Button("stonieren");
            
            int counterI = i;
            
            stonieren.setOnAction((event) -> {
                person.getReservationen().remove(counterI);
                pane.getChildren().removeAll(film, saal, zeit, plätze, stonieren);
                initialize(url, rb);
            });

            film.setLayoutX(filmLabel.getLayoutX());
            film.setLayoutY(filmLabel.getLayoutY()+((i+1)*50));
                            
            saal.setLayoutX(saalLabel.getLayoutX());
            saal.setLayoutY(saalLabel.getLayoutY()+((i+1)*50));
            
            zeit.setLayoutX(zeitLabel.getLayoutX());
            zeit.setLayoutY(zeitLabel.getLayoutY()+((i+1)*50));            
            
            plätze.setLayoutX(plätzeLabel.getLayoutX());
            plätze.setLayoutY(plätzeLabel.getLayoutY()+((i+1)*50));
            
            stonieren.setLayoutX(buttonLoc.getLayoutX());
            stonieren.setLayoutY(buttonLoc.getLayoutY()+((i+1)*50));
            
            pane.getChildren().addAll(film, saal, zeit, plätze, stonieren);

        }
        
        
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        Parent backParent = FXMLLoader.load(getClass().getResource(lastScene + ".fxml"));
        Scene backScene = new Scene(backParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(backScene);
        window.show();
        

    }

}
