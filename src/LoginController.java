import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sun.security.ssl.Debug;
import sun.swing.SwingLazyValue;

import javax.swing.Action;
//import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class LoginController implements Initializable{

    private DBInterface db = new DBInterface();

   // public void login(ActionEvent actionEvent) {
    //}

    //public class adduser implements Initializable {

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

        public void login(ActionEvent e) {

        }

        public void LoginHandler(ActionEvent e)
        {
            //login.setOnAction(new EventHandler<ActionEvent>() {
                //@Override
                //public void handle(ActionEvent event){
                    try
                    {
                        System.out.println(e);
                        System.out.println(userID.getText());
                        System.out.println(passID.getText());
                        db.login("\'" + userID.getText() + "\'","\'" +  passID.getText() + "\'");
                        System.out.println("Login Successful");
                    }
                    catch (Exception ea)
                    {
                        System.out.println("Error in login");
                    }
                }
            //});

        //}
    //}
}

