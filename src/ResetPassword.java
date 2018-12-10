import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class ResetPassword implements Initializable {

    private DBInterface db = new DBInterface();
    private UserData userInfo = new UserData();
    ArrayList<TreeMap<String, String>> a;
    String e;
    String p;
    DES_EncryptionDecryption des = new DES_EncryptionDecryption();
    String decryptPassword;
    String encryptPassword;

    @FXML
    private TextField email;
    @FXML
    private TextField oldPassword;
    @FXML
    private TextField newPassword;
    @FXML
    private Button submit;

    public ResetPassword() throws Exception {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void resetPassword() {
        try{
            a = db.searchData("Users", "password", "Email = \'" + email.getText() + "\'");
            p = a.get(0).get("password");
            System.out.println("old password " + oldPassword.getText());
            String pp = "\'" + oldPassword.getText() + "\'";
            decryptPassword = des.encrypt(pp);
            System.out.println(decryptPassword);
            if(p.equals(decryptPassword)) {
                System.out.println("Found oldpassword Successfully");
                //set new password
                String ll = "\'" + newPassword.getText() + "\'";
                encryptPassword = des.encrypt(ll);
                db.updateDate("Users", "Password = \'" + encryptPassword + "\'", "Email = \'" + email.getText() + "\'");
                System.out.println("updated");


                FXMLLoader loader =  new FXMLLoader(getClass().getResource("login.fxml"));
                System.out.println(loader);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                main.getStage().setScene(scene);
                main.getStage().sizeToScene();

            }
        }
        catch (Exception e) {
            System.out.println("Error password dont match");
            System.out.println(e);
        }
    }
}
