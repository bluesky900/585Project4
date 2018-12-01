import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class main extends Application {
    public void start (Stage stage) throws Exception {
        try {  Parent root = (Parent) FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Facebook Lite");
        stage.show();
        stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public static void main(String[] args) throws SQLException {

		DBInterface connection = new DBInterface();
		System.out.println(connection.login("\'testMail1@gmail.com\'", "password"));
		//connection.register("\'registerTest\'", "\'testword\'", "\'Peter\'", "\'Vander\'", 52);
		connection.searchData("Users", "FirstName, LastName, Password, Email", "FirstName = \'Peter\' AND LastName = \'Vander\'");
		connection.logout();
		launch(args);
	}

}
