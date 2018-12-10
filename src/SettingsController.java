import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class SettingsController implements Initializable {
    private String uID = "";
    private ArrayList<TreeMap<String, String>> a = new ArrayList<>();
    DBInterface db = new DBInterface();
    private String hs;
    private String ha;
    private String hp;
    private String hf;

    @FXML
    private CheckBox hideStatus;
    @FXML
    private CheckBox hideAge;
    @FXML
    private CheckBox hidePosts;
    @FXML
    private CheckBox hideFriends;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void UserPrefs(String id)
    {
        uID = id;

        try
        {
            a = db.searchData("UserSettings", "DOB, Posts, Status", "IDUser = \'" + uID + "\'");
            if(a.get(0).get("DOB").equals("true"))
            {
                hideAge.setSelected(false);

            }
            else
            {
                hideAge.setSelected(true);

            }
            if(a.get(0).get("Status").equals("true"))
            {
                hideStatus.setSelected(false);

            }
            else
            {
                hideStatus.setSelected(true);

            }
            if(a.get(0).get("Posts").equals("true"))
            {
                hidePosts.setSelected(false);

            }
            else
            {
                hidePosts.setSelected(true);

            }

            //if(a.get(0).get("DOB").equals("true"))
            //{

           // }
            System.out.println(a.get(0).get("DOB"));
            System.out.println(a.get(0).get("Status"));
            System.out.println(a.get(0).get("Posts"));
        }
        catch (Exception ea)
        {
            System.out.println("Error with checkboxes");
        }

    }

    public void CloseSettings()
    {

        try
        {
            if(hideStatus.isSelected())
            {
                hs = "false";
            }
            else
                hs = "true";
            if(hideAge.isSelected())
                ha ="false";
            else
                ha = "true";
            if(hidePosts.isSelected())
                hp = "false";
            else
                hp = "true";


            System.out.println("\n" + ha);
            System.out.println(hs);
            System.out.println(hp);
            db.updateDate("UserSettings", "DOB=\'" + ha + "\' AND Posts=\'" + hp + "\' AND Status=\'" + hs + "\'", "IDUser = \'" + uID + "\'");
            a = db.searchData("Users", "Email", "ID = \'" + uID + "\'");
            FXMLLoader loader =  new FXMLLoader(getClass().getResource("Homepage .fxml"));
            Parent root = loader.load();
            //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Homepage .fxml"));
            Scene scene = new Scene(root);
            main.getStage().setScene(scene);
            main.getStage().sizeToScene();

            Hompage controller = loader.getController();
            controller.setUser(a.get(0).get("Email"));
        }
        catch (Exception ea)
        {
            System.out.println("Error updating settings");
        }

    }
}
