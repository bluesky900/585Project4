import java.sql.SQLException;

public class main {

	public static void main(String[] args) throws SQLException {

		DBInterface connection = new DBInterface();
		System.out.println(connection.login("\'testMail1@gmail.com\'", "password"));
		//connection.register("\'registerTest\'", "\'testword\'", "\'Peter\'", "\'Vander\'", 52);
		connection.searchData("Users", "FirstName, LastName, Password, Email", "FirstName = \'Peter\' AND LastName = \'Vander\'");
		connection.logout();
	}

}
