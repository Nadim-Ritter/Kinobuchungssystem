package kinobuchungssytem;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import kinobuchungssytem.Klassen.Person;

public class LoginGuiController implements Initializable {

    @FXML
    TextField emailField;

    @FXML
    TextField pwField;

    @FXML
    Label labelNachricht;
    
    Person currentPerson;
   

    @FXML
    public void checkLogin(ActionEvent event) throws IOException {
        labelNachricht.setText("");
        String email = emailField.getText();
        String pw = pwField.getText();

        boolean personExist = false;
        for (int i = 0; i < Kinobuchungssytem.personen.size(); i++) {
            if (Kinobuchungssytem.personen.get(i).getEmail().equals(email) && Kinobuchungssytem.personen.get(i).getPasswort().equals(pw)) {
                personExist = true;
                currentPerson = Kinobuchungssytem.personen.get(i);
                GuiSitzAuswählenController.setPerson(currentPerson);
                GuiAuswahlController.setPerson(currentPerson);
                GuiFilmDetailsController.setPerson(currentPerson);
                PersonDetailsController.setPerson(currentPerson);
                break;
            }
        }

        if (personExist) {
            Parent AuswahlParent = FXMLLoader.load(getClass().getResource("GuiAuswahl.fxml"));
            Scene AuswahlScene = new Scene(AuswahlParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(AuswahlScene);
            window.show();
        }else{
            labelNachricht.setText("Bitte Login Daten überprüfen");
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
