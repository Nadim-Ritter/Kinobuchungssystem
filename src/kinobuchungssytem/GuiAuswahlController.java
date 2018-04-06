package kinobuchungssytem;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import static kinobuchungssytem.GuiFilmDetailsController.person;
import kinobuchungssytem.Klassen.Person;

public class GuiAuswahlController implements Initializable {

    @FXML
    ScrollPane sp;

    @FXML
    AnchorPane pane;
    
    @FXML
    MenuButton personDetails;
    
        
    ArrayList<String> bilderNamen = new ArrayList();
    static Person person;
    
    public static void setPerson(Person p){
        person = p;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        personDetails.setText(person.getVorname() + " " + person.getName());

        for (int i = 0; i < Kinobuchungssytem.filme.size(); i++) {
            bilderNamen.add(Kinobuchungssytem.filme.get(i));
        }

        //create new Box for conten
        VBox box = new VBox();
        VBox.setVgrow(sp, Priority.ALWAYS);

        for (int i = 0; i < bilderNamen.size(); i++) {
            //add image
            ImageView iv = new ImageView();
            Image image = new Image("file:BilderFilme/" + bilderNamen.get(i) + ".jpg");
            iv.setImage(image);
            iv.setFitWidth(100);
            iv.setPreserveRatio(true);
            if (i > 0) {
                iv.setTranslateY(-85 * i);
            }
            //title & button
            Label filmTitel = new Label(bilderNamen.get(i));
            filmTitel.setTranslateX(150);
            if (i > 0) {
                filmTitel.setTranslateY(-85 * (i + 1));
            } else {
                filmTitel.setTranslateY(-85);
            }
            filmTitel.setStyle("-fx-font-size: 25px;\n"
                    + "-fx-font-weight: bold;");

            Button button = new Button("Details");
            button.setTranslateX(680);
            button.setTranslateY(filmTitel.getTranslateY() - 40);
            button.setId("Button" + bilderNamen.get(i));
            button.setOnAction((event) -> {
                GuiFilmDetailsController.setFilmName(button.getId().substring(6));
                try {
                    Parent FilmDetailsParent = FXMLLoader.load(getClass().getResource("GuiFilmDetails.fxml"));
                    Scene FilmDetailsScene = new Scene(FilmDetailsParent);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(FilmDetailsScene);
                    window.show();
                } catch (IOException ex) {
                    Logger.getLogger(GuiAuswahlController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
            //add content to box
            box.getChildren().addAll(iv, filmTitel, button);
        }

        sp.setVmax(440);
        //sp.setPrefSize(400, 400);
        //add box to scroll pane
        sp.setContent(box);

    }
    
    @FXML
    public void showDetails(ActionEvent event) throws IOException{
        PersonDetailsController.setLastScene("GuiAuswahl");
        Parent DetailsParent = FXMLLoader.load(getClass().getResource("PersonDetails.fxml"));
        Scene DetailsScene = new Scene(DetailsParent);
        Stage window = (Stage) personDetails.getScene().getWindow();
        window.setScene(DetailsScene);
        window.show();
        
    }
    
}