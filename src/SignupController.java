import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class SignupController implements Initializable{

        private DBInterface db = new DBInterface();
        @FXML
        private TextField firstname;
        @FXML
        private TextField lastname;
        @FXML
        private TextField email;
        @FXML
        private TextField username;
        @FXML
        private TextField newpass;
        @FXML
        private TextField age;
        @FXML
        private Button signup;
       @FXML
       private Label eLabel;

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {

        }

    public void SignupHandler(ActionEvent e)
    {
        System.out.println(e);
        try
        {
            int temp = parseInt(age.getText());
            db.register("\'" + email.getText() + "\'","\'" +  newpass.getText() + "\'", "\'" + firstname.getText() + "\'", "\'" + lastname.getText() + "\'", temp);
            System.out.println("Registration Successful");

            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            main.getStage().setScene(scene);
            main.getStage().setTitle("Facebook Lite");
            main.getStage().sizeToScene();
        }
        catch (Exception ea)
        {
            System.out.println("Error in registration");
            eLabel.setText("ERROR: Email address might be in use by another account");
        }
    }

    public void Cancel()
    {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            main.getStage().setScene(scene);
            main.getStage().setTitle("Facebook Lite");
            main.getStage().sizeToScene();
        }
        catch (Exception ea)
        {
            System.out.println("\nError returning to Login\n");
        }
        
    }
}
