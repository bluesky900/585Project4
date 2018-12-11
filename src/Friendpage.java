import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class Friendpage implements Initializable {

    private String user = "";
    private String uID = "";
    private String friend = "";
    private String fID = "";
    private ArrayList<TreeMap<String, String>> a = new ArrayList<>();
    private ArrayList<TreeMap<String, String>> b = new ArrayList<>();
    private ArrayList<String> fFriends = new ArrayList<>();
    private ArrayList<String> fFriendsID = new ArrayList<>();
    private DBInterface db  = new DBInterface();
    private String ageOn;
    private String stsOn;
    private String postsOn;
    private String friendOn;
    private String fAge;
    private String sts;

    @FXML
    private TextArea post;
    @FXML
    private ComboBox<String> setting;
    @FXML
    private TextField username;
    @FXML
    private TextField age;
    @FXML
    private TextField status;
    @FXML
    private ListView friendslist;
    @FXML
    private Label friendPage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    status.setText("");
    status.setEditable(false);
    post.setText("");
    post.setEditable(false);
    age.setText("");
    age.setEditable(false);
    username.setEditable(false);
    }

    public void setFriend(String u, String f, String fid, String uid)
    {
        user = u;
        friend = f;
        fID = fid;
        uID = uid;
        username.setText(user);
        friendPage.setText(friend + "'s Page");
        HideStuff();
        if(friendOn.equals("1"))
            PopFriends();
    }

    public void HideStuff()
    {
        try{
            a = db.searchData("UserSettings", "DOB, Posts, Status, Friends", "IDUser = \'" + fID + "\'");
            b = db.searchData("Users", "Age, Status", "ID = \'" + fID + "\'");
            System.out.println("Homepage loaded correctly");

            ageOn = a.get(0).get("DOB");
            stsOn = a.get(0).get("Status");
            postsOn = a.get(0).get("Posts");
            friendOn = a.get(0).get("Friends");

            if(ageOn.equals("1"))
            {
                fAge = b.get(0).get("Age");
                age.setText(fAge);
            }
            else
            {
                age.setText("HIDDEN");
                System.out.println("Items properly hidden");
            }

            if(stsOn.equals("1"))
            {
                sts = b.get(0).get("Status");
                status.setText(sts);
                System.out.println(sts);
            }
            else
            {
                status.setText("HIDDEN");
                System.out.println("Items properly hidden");
            }

            int t = 0;
            if(postsOn.equals("1"))
            {
                a = db.searchData("Posts", "Title, Content", "IDOwner = \'" + fID + "\'");

                while(t < a.size())
                {
                    post.setText(post.getText() + a.get(t).get("Title") + "     " + a.get(t).get("Content") + "\n\n");
                    t++;
                }
            }
            else
            {
                post.setText("POSTS ARE HIDDEN");
                System.out.println("Items properly hidden");
            }

        }
        catch (Exception ea)
        {
            System.out.println("Error hiding items");
            System.out.println(ea);
        }
    }

    public void PopFriends()
    {

        try{
            a = db.searchData("Friends", "IDFriend", "IDMain = \'" + fID + "\'");
            int t = 0;
            while(t < a.size())
            {
                fFriends.add(a.get(t).get("IDFriend"));
                fFriendsID.add(a.get(t).get("IDFriend"));
                t++;
            }

            while(fFriends.size()> 0)
            {
                a = db.searchData("Users", "FirstName, LastName", "ID = \'" + fFriends.get(0) + "\'");
                friendslist.getItems().add(a.get(0).get("FirstName") + " " + a.get(0).get("LastName"));
                fFriends.remove(0);
            }
        }
        catch(Exception ea)
        {

        }

    }

    public void ChangePage()
    {
        try{
            int item = friendslist.getSelectionModel().getSelectedIndex();
            String fClicked = fFriendsID.get(item);

            if(fClicked == uID)
            {
                GoHome();
            }

            else
            {
                FXMLLoader loader =  new FXMLLoader(getClass().getResource("friendpage.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                main.getStage().setScene(scene);
                main.getStage().sizeToScene();

                Friendpage controller = loader.getController();
                controller.setFriend(user, friendslist.getSelectionModel().getSelectedItem().toString(), fClicked, uID);

                System.out.println("Loaded Friend Page");
            }


        }
        catch (Exception ea)
        {
            System.out.println("Misclick");
        }
    }

    public void GoHome()
    {
        try{
            a = db.searchData("Users", "Email", "ID = \'" + uID + "\'");
            String email = a.get(0).get("Email");
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
            System.out.println("Error loading home");
        }

    }
}
