import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class LoginController {

    public class adduser implements Initializable {

        @FXML
        private TextField userID;
        @FXML
        private TextField passID;
        @FXML
        private Button login;
        @FXML
        private Button sginup;
        @FXML
        private Button repass;

        @Override
        public void initialize(URL url, ResourceBundle rb) {

        }

        @FXML
        public void login(ActionEvent event) {

        }
    }}

