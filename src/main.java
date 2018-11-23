import java.sql.SQLException;

public class main {

	public static void main(String[] args) throws SQLException {

		DBInterface connection = new DBInterface();
		System.out.println(connection.login("\'Skyler\'", "password"));
		connection.logout();
	}

}
