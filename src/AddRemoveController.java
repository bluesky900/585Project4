import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class AddRemoveController implements Initializable {

    private String uID;
    private String addRemove;
    private DBInterface db = new DBInterface();
    private ArrayList<TreeMap<String, String>> a;
    private ArrayList<String> friends = new ArrayList<>();
    private ArrayList<String> friendsID = new ArrayList<>();

    @FXML
    ListView friendList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void UserList(String u, String ar) throws SQLException
    {
        uID = u;
        addRemove = ar;

        if (addRemove == "a")
        {
            AddFriend();
        }

        else if(addRemove == "r")
        {
            RemoveFriend();
        }
    }

    public void AddFriend() throws SQLException
    {
        a = db.searchData("Users", "ID", "ID <> \'" + uID + "\'");
        int temp = 0;
        while(temp < a.size())
        {
            friends.add(a.get(temp).get("ID"));
            friendsID.add(a.get(temp).get("ID"));
            temp++;
        }

        a = db.searchData("Friends", "IDFriend", "IDMain = \'" + uID + "\'");
        temp = 0;
        while(temp < a.size())
        {
            if(friends.contains(a.get(temp).get("IDFriend")))
            {
                friends.remove(a.get(temp).get("IDFriend"));
                friendsID.remove(a.get(temp).get("IDFriend"));
            }
            temp++;
        }
        while(friends.size()> 0)
        {
            a = db.searchData("Users", "FirstName, LastName", "ID = \'" + friends.get(0) + "\'");
            friendList.getItems().add(a.get(0).get("FirstName") + " " + a.get(0).get("LastName"));
            friends.remove(0);
        }
    }

    public void RemoveFriend() throws SQLException
    {
        a = db.searchData("Friends", "IDFriend", "IDMain = \'" + uID + "\'");
        int temp = 0;
        while(temp < a.size())
        {
            friends.add(a.get(temp).get("IDFriend"));
            friendsID.add(a.get(temp).get("IDFriend"));
            temp++;
        }

        while(friends.size()> 0)
        {
            a = db.searchData("Users", "FirstName, LastName", "ID = \'" + friends.get(0) + "\'");
            friendList.getItems().add(a.get(0).get("FirstName") + " " + a.get(0).get("LastName"));
            friends.remove(0);
        }
    }

    public void RemoveFromList()
    {
        try{
            int item = friendList.getSelectionModel().getSelectedIndex();
            String fID = friendsID.get(item);
            db.removeFriend(Integer.parseInt(uID), Integer.parseInt(fID));

            friendList.getItems().remove(friendList.getSelectionModel().getSelectedIndex());
            friendsID.remove(item);
        }
        catch (Exception ea)
        {
            System.out.println("Misclick");
        }
    }

    public void AddToList()
    {
        try{
            int item = friendList.getSelectionModel().getSelectedIndex();
            String fID = friendsID.get(item);
            db.addFriend(Integer.parseInt(uID), Integer.parseInt(fID));

            friendList.getItems().remove(friendList.getSelectionModel().getSelectedIndex());
            friendsID.remove(item);
        }
        catch (Exception ea)
        {
            System.out.println("Misclick");
        }
    }

    public void HandleClick() throws SQLException
    {
        if(addRemove == "a")
        {
            AddToList();
        }
        else if(addRemove == "r")
        {
            RemoveFromList();
        }

        else
        {
            System.out.println("Error: addRemove = " + addRemove);
        }
    }

    public void GoHome() throws IOException, SQLException
    {
        String email = "";
        a = db.searchData("Users", "Email", "ID = \'" + uID + "\'");
        email = a.get(0).get("Email");
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("Homepage .fxml"));
        System.out.println(loader);
        Parent root = loader.load();

        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Homepage .fxml"));
        Scene scene = new Scene(root);

        main.getStage().setScene(scene);

        main.getStage().sizeToScene();

        Hompage controller = loader.getController();
        controller.setUser(email);
    }
}
