import com.idescout.sqlite.console.AddRowAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;

import java.awt.List;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jdk.nashorn.internal.ir.CatchNode;
import org.apache.poi.hssf.record.EscherAggregate;

//import javax.swing.text.html.ListView;
import javax.xml.soap.Text;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class Hompage implements Initializable {

    private String user = "";
    private String sts = "";
    private String userAge = "";
    private String userID = "";
    private DBInterface db = new DBInterface();
    private ArrayList<String> friends = new ArrayList<>();
    private ArrayList<String> friendsID = new ArrayList<>();
    ArrayList<TreeMap<String, String>> a;
    private String postsOn;
    private String stsOn;
    private String ageOn;

    @FXML
    private TextArea post;
    //@FXML
    //private ComboBox<String> setting;
    @FXML
    private TextField username;
    @FXML
    private TextField age;
    @FXML
    private TextField status;
    @FXML
    private ListView friendslist;
    @FXML
    private TextField newPostField;
    @FXML
    private TextField newTitleField;

    private ObservableList<String> settingList = FXCollections.observableArrayList("Add", "Remove", "Hide");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //setting.setItems(settingList);
        username.setEditable(false);
        age.setEditable(true);
        status.setEditable(true);
        post.setText("");
        post.setEditable(false);
        postsOn = "";
        ageOn = "";
        stsOn = "";

    }

    public void setUser(String u)
    {
        user = u;
        username.setText(user);
        try{
            a = db.searchData("Users", "Status, Age, ID", "Email = \'" + username.getText() + "\'");
            userID = a.get(0).get("ID");
            ArrayList<TreeMap<String, String >> b = db.searchData("UserSettings", "DOB, Posts, Status", "IDUser = \'" + userID + "\'");
            System.out.println("Homepage loaded correctly");

            ageOn = b.get(0).get("DOB");
            stsOn = b.get(0).get("Status");
            postsOn = b.get(0).get("Posts");
            System.out.println(stsOn);

            if(ageOn.equals("true"))
            {
                userAge = a.get(0).get("Age");
                age.setText(userAge);
            }
            else
            {
                age.setText("HIDDEN");
            }

            if(stsOn.equals("true"))
            {
                sts = a.get(0).get("Status");
                status.setText(sts);
            }
            else
            {
               status.setEditable(false);
                status.setText("HIDDEN");
            }

            int temp = 0;
            if(postsOn.equals("true"))
            {
                a = db.searchData("Posts", "Title, Content", "IDOwner = \'" + userID + "\'");

                while(temp < a.size())
                {
                    post.setText(post.getText() + a.get(temp).get("Title") + "     " + a.get(temp).get("Content") + "\n\n");
                    temp++;
                }
            }
            else
            {
                post.setText("POSTS ARE HIDDEN");
            }


            a = db.searchData("Friends", "IDFriend", "IDMain = \'" + userID + "\'");
            temp = 0;
            while(temp < a.size())
            {
                friends.add(a.get(temp).get("IDFriend"));
                friendsID.add(a.get(temp).get("IDFriend"));
                temp++;
            }

            while(friends.size()> 0)
            {
                a = db.searchData("Users", "FirstName, LastName", "ID = \'" + friends.get(0) + "\'");
                friendslist.getItems().add(a.get(0).get("FirstName") + " " + a.get(0).get("LastName"));
                friends.remove(0);
            }
        }
        catch (Exception ea)
        {
            System.out.println("Error loading homepage");
            System.out.println(ea);
        }
    }

    public void GoToFriendPage()
    {
        try{
            int item = friendslist.getSelectionModel().getSelectedIndex();
            String fID = friendsID.get(item);

            FXMLLoader loader =  new FXMLLoader(getClass().getResource("friendpage.fxml"));
            Parent root = loader.load();
            //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Homepage .fxml"));
            Scene scene = new Scene(root);
            main.getStage().setScene(scene);
            main.getStage().sizeToScene();

            Friendpage controller = loader.getController();
            controller.setFriend(user, friendslist.getSelectionModel().getSelectedItem().toString(), fID, userID);

            System.out.println("Loaded Friend Page");

        }
        catch (Exception ea)
        {
            System.out.println("Misclick");
        }

    }

    public void OpenSettings()
    {
        try
        {
            FXMLLoader loader =  new FXMLLoader(getClass().getResource("settings.fxml"));
            Parent root = loader.load();
            //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Homepage .fxml"));
            Scene scene = new Scene(root);
            main.getStage().setScene(scene);
            main.getStage().sizeToScene();

            SettingsController controller = loader.getController();
            controller.UserPrefs(userID);

        }
        catch (Exception ea)
        {
            System.out.println("Error opening settings");
        }

    }

    public void Add() throws IOException, SQLException
    {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("AddRemove.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        main.getStage().setScene(scene);
        main.getStage().sizeToScene();
        AddRemoveController controller = loader.getController();
        controller.UserList(userID, "a");
    }

    public void Remove() throws IOException, SQLException
    {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("AddRemove.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        main.getStage().setScene(scene);
        main.getStage().sizeToScene();
        AddRemoveController controller = loader.getController();
        controller.UserList(userID, "r");
    }

    public void ChangeStatus() throws SQLException
    {
        if(!status.getText().equals("HIDDEN"))
        {
            System.out.println(status.getText());
        }
            db.updateStatus(Integer.parseInt(userID), "\'" + status.getText() + "\'");
    }

    public void ChangeAge()
    {
        try
        {
            Integer.parseInt(age.getText());
            if(!age.getText().equals("HIDDEN"))
            {
                System.out.println(age.getText());
            }
            db.updateDate("Users", "Age=\'" + age.getText() + "\'", "ID = \'" + userID + "\'");
        }
        catch (Exception ea)
        {
            System.out.println("\nError updating age\n");
        }

    }

    public void NewPost()
    {
        try
        {
            if(!newPostField.getText().equals("") && !newTitleField.getText().equals(""))
            {
                db.addPost(Integer.parseInt(userID), "\'" + newTitleField.getText() + "\'", "\'" + newPostField.getText()+"\'");
                RefreshPosts();
            }
        }
        catch (Exception ea)
        {
            System.out.println("\nError creating post\n");
        }
    }

    public void RefreshPosts()
    {
        try{
            post.clear();
            int temp = 0;
            if(postsOn.equals("true"))
            {
                a = db.searchData("Posts", "Title, Content", "IDOwner = \'" + userID + "\'");

                while(temp < a.size())
                {
                    post.setText(post.getText() + a.get(temp).get("Title") + "     " + a.get(temp).get("Content") + "\n\n");
                    temp++;
                }
            }
            else
            {
                post.setText("POSTS ARE HIDDEN");
            }
        }
        catch (Exception ea)
        {
            System.out.println("\nError loading posts\n");
        }

    }

    public void DeletePosts()
    {
        try
        {
            FXMLLoader loader =  new FXMLLoader(getClass().getResource("removePost.fxml"));
            Parent root = loader.load();
            //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Homepage .fxml"));
            Scene scene = new Scene(root);
            main.getStage().setScene(scene);
            main.getStage().sizeToScene();

            RemovePostController controller = loader.getController();
            controller.Populate(userID);
        }
        catch (Exception ea)
        {
            System.out.println("\nFailed to load posts scene\n");
        }

    }
}