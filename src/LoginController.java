import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
//import sun.security.ssl.Debug;
//import sun.swing.SwingLazyValue;

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

        public void RePass(ActionEvent e) {
            try
            {
                Parent root = FXMLLoader.load(getClass().getResource("Restpass.fxml"));
                Scene scene = new Scene(root);
                main.getStage().setScene(scene);
                main.getStage().setTitle("Facebook Lite - Confirm Email");
                main.getStage().sizeToScene();

            }
            catch(Exception ea)
            {

            }
        }

        public void SignUp(ActionEvent e) {
            try
            {
                Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
                Scene scene = new Scene(root);
                main.getStage().setScene(scene);
                main.getStage().setTitle("Facebook Lite - Create Account");
                main.getStage().sizeToScene();

            }
            catch(Exception ea)
            {

            }

        }

        public void LoginHandler(ActionEvent e)
        {
            //login.setOnAction(new EventHandler<ActionEvent>() {
                //@Override
                //public void handle(ActionEvent event){
                    try
                    {
                        db.login("\'" + userID.getText() + "\'","\'" +  passID.getText() + "\'");
                        System.out.println("Login Successful");
                        FXMLLoader loader =  new FXMLLoader(getClass().getResource("Homepage .fxml"));
                        System.out.println(loader);
                        Parent root = loader.load();

                        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Homepage .fxml"));
                        Scene scene = new Scene(root);

                        main.getStage().setScene(scene);

                        main.getStage().sizeToScene();

                        Hompage controller = loader.getController();
                        controller.setUser(userID.getText());
                    }
                    catch (Exception ea)
                    {
                        System.out.println("Error in login");
                        System.out.println(ea);
                        //System.out.println(ea);
                    }
                }
            //});

        //}
    //}
}

