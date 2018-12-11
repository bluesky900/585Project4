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
            a = db.searchData("UserSettings", "DOB, Posts, Status, Friends", "IDUser = \'" + uID + "\'");
            if(a.get(0).get("DOB").equals("1"))
            {
                hideAge.setSelected(false);

            }
            else
            {
                hideAge.setSelected(true);

            }
            if(a.get(0).get("Status").equals("1"))
            {
                hideStatus.setSelected(false);

            }
            else
            {
                hideStatus.setSelected(true);

            }
            if(a.get(0).get("Posts").equals("1"))
            {
                hidePosts.setSelected(false);

            }
            else
            {
                hidePosts.setSelected(true);

            }
            if(a.get(0).get("Friends").equals("1"))
            {
                hideFriends.setSelected(false);
            }
            else
            {
                hideFriends.setSelected(true);
            }

            System.out.println(a.get(0).get("DOB"));
            System.out.println(a.get(0).get("Status"));
            System.out.println(a.get(0).get("Posts"));
            System.out.println(a.get(0).get("Friends"));
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
                hs = "0";
            }
            else
                hs = "1";
            if(hideAge.isSelected())
                ha ="0";
            else
                ha = "1";
            if(hidePosts.isSelected())
                hp = "0";
            else
                hp = "1";
            if(hideFriends.isSelected())
                hf = "0";
            else
                hf = "1";


            System.out.println("\n" + ha);
            System.out.println(hs);
            System.out.println(hp);
            db.updateDate("UserSettings", "DOB = \'" + ha + "\'", "IDUser = \'" + uID + "\'");
            db.updateDate("UserSettings", "Posts = \'" + hp + "\'", "IDUser = \'" + uID + "\'");
            db.updateDate("UserSettings", "Status = \'" + hs + "\'", "IDUser = \'" + uID + "\'");
            db.updateDate("UserSettings", "Friends = \'" + hf + "\'", "IDUser = \'" + uID + "\'");
            //db.updateDate("UserSettings", "DOB=\'" + ha + "\' AND Posts=\'" + hp + "\' AND Status=\'" + hs + "\'", "IDUser = \'" + uID + "\'");
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
