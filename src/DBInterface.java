import java.sql.ResultSet;
import java.sql.SQLException;

public class DBInterface {

	UserData userInfo;

	public DBInterface() {
		userInfo = new UserData();
	}

	public Boolean login(String user, String password) throws SQLException {
		ResultSet results = userInfo.getData("Users", "password", "Email = " + user);

		System.out.println("results: \n" + results.getString("password") + "\n" + password);

		if(results.getString("password").equals(password)) {
			results.close();
			return true;
		} else {
			results.close();
			return false;
		}
	}

	public void logout() throws SQLException {
		userInfo.closeConnection();
	}

}
