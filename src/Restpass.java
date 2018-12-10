import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class Restpass implements Initializable {

    private DBInterface db = new DBInterface();
    ArrayList<TreeMap<String, String>> a;
    String e;


    @FXML
    private TextField email;

    @FXML
    private Button submit;

    @FXML
    private Label eLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void ResetPass() {
        try{
            a = db.searchData("Users", "Email", "Email = \'" + email.getText() + "\'");
            e = a.get(0).get("Email");
            if(e.equals(email.getText())) {
                System.out.println("Found email Successfully");
                FXMLLoader loader =  new FXMLLoader(getClass().getResource("ResetPass2.fxml"));
                System.out.println(loader);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                main.getStage().setScene(scene);
                main.getStage().sizeToScene();
            }
        }
        catch (Exception e) {
            System.out.println("Error finding this email");
            System.out.println(e);
            eLabel.setText("Email not found");
        }
    }

    public void Cancel()
    {
        try{
            FXMLLoader loader =  new FXMLLoader(getClass().getResource("login.fxml"));
            System.out.println(loader);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            main.getStage().setScene(scene);
            main.getStage().sizeToScene();
        }
        catch (Exception ea)
        {
            System.out.println("\nError loading login page\n");
        }
    }
}
