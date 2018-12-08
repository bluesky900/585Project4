import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
        //private

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {

        }

    public void SignupHandler(ActionEvent e)
    {
        int temp = parseInt(age.getText());
        System.out.println(e);
        try
        {

            db.register("\'" + email.getText() + "\'","\'" +  newpass.getText() + "\'", "\'" + firstname.getText() + "\'", "\'" + lastname.getText() + "\'", temp);
            System.out.println("Registration Successful");
        }
        catch (Exception ea)
        {

            System.out.println("Error in registration");
            System.out.println(email.getText());
            System.out.println(newpass.getText());
            System.out.println(firstname.getText());;
            System.out.println(lastname.getText());
            System.out.println(temp);
        }
    }

}
