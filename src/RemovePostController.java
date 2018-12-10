import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class RemovePostController implements Initializable {

    private ArrayList<TreeMap<String, String>> a;
    private ArrayList<String> friends = new ArrayList<>();
    private ArrayList<String> friendsID = new ArrayList<>();
    private DBInterface db = new DBInterface();
    private String uID;

    @FXML
    private ListView listview;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void Populate(String u)
    {
        uID = u;
        try{
            a = db.searchData("Posts", "Title, Content, IDPost", "IDOwner = \'" + uID + "\'");
            int temp = 0;
            while(temp < a.size())
            {
                friends.add(a.get(temp).get("IDPost"));
                //friendsID.add(a.get(temp).get("IDpost"));
                listview.getItems().add(a.get(temp).get("Title") + "        " + a.get(temp).get("Content"));
                temp++;
            }

        }
        catch (Exception ea)
        {
            System.out.println("\nError loading posts\n");
        }

    }

    public void RemovePost()
    {
        try{
            int item = listview.getSelectionModel().getSelectedIndex();
            String fID = friends.get(item);
           db.removePost(Integer.parseInt(uID), Integer.parseInt(fID));

            listview.getItems().remove(listview.getSelectionModel().getSelectedIndex());
            friends.remove(item);
        }
        catch (Exception ea)
        {
            System.out.println("Misclick");
        }
    }

    public void GoHome()
    {
        try{
            String email = "";
            a = db.searchData("Users", "Email", "ID = \'" + uID + "\'");
            email = a.get(0).get("Email");
            FXMLLoader loader =  new FXMLLoader(getClass().getResource("Homepage .fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            main.getStage().setScene(scene);
            main.getStage().sizeToScene();

            Hompage controller = loader.getController();
            controller.setUser(email);
        }
        catch (Exception ea)
        {

        }
    }
}
