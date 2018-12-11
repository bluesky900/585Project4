import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class main extends Application {
private static Stage mainStage;
    public void start (Stage stage) throws Exception {
        try {  Parent root = (Parent) FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Facebook Lite");
            stage.show();
            stage.setResizable(false);
            mainStage = stage;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Stage getStage()
    {
        return mainStage;
    }
	public static void main(String[] args) throws SQLException {

        //DBInterface connection = new DBInterface();
        //System.out.println(connection.login("\'registerTest@gmail.com\'", "\'testword34&\'"));
        //connection.register("\'settingTest@gmail.com\'", "\'settingTest\'", "\'setting\'", "\'test\'", 54);
        //connection.updateStatus(1, "\'I am testing this status\'");
        //connection.searchData("Users", "FirstName, LastName, Password, Email, Status", "Email = \'settingTest@gmail.com\'");
        //connection.searchData("UserSettings", "DOB, Posts, Status", "IDUser = \'settingTest@gmail.com\'");
        //System.out.println(connection.addPost(1, "\'Test post\'", "\'This is a test of the posting system\'"));
        //connection.removePost(1, 4);
        //System.out.println(connection.addPost(12, 11));
        //connection.logout();
        launch(args);
	}

}
