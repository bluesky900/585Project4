import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class Login {

    public class adduser implements Initializable {

        @FXML
        private TextField firstname;
        @FXML
        private TextField lastname;
        @FXML
        private TextField ymail;
        @FXML
        private TextField username;
        @FXML
        private TextField newpass;
        @FXML
        private TextField age;
        @FXML
        private Button signup;


        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {

        }

    }
}
