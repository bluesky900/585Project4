import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;

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

	public void register(String email, String password, String firstName, String lastName, int age) throws SQLException {

		String columns = "(FirstName, LastName, Age, Password, Email)";
		String data = "(" + firstName + ", " + lastName + ", " + Integer.toString(age) + ", " + password + ", " + email + ")";

		userInfo.insertData("Users", data, columns);
	}

	//Searches the database for the designated fields and conditions
	//fields must be in the format: field1, field2, field3, etc.. including the commas
	//conditions must be in the format: field1 = value1 AND field2 = value2 AND field3 = value3 etc... The AND can be replaced with OR if that is the condition you wish to use
	public ArrayList searchData(String table, String fields, String conditions) throws SQLException {

		ResultSet results = userInfo.getData(table, fields, conditions);
		ArrayList<TreeMap<String, String>> formattedResults = new ArrayList<TreeMap<String, String>>();

		String[] fieldList = fields.split(", ");

		while(results.next()) {

			TreeMap<String, String> temp = new TreeMap<String, String>();

			for(String field : fieldList) {
				temp.put(field, results.getString(field));
			}

			formattedResults.add(temp);

		}

		System.out.println(formattedResults);
		return formattedResults;
	}

	public boolean addFriend(int idMain, int idFriend) throws SQLException {

		//check if friend is already added
		if(searchData("Friends", "IDFriend", "IDMain = " + idMain + " AND IDFriend = " + idFriend).size() == 0) {
			userInfo.insertData("Friends", "(" + idMain + ", " + idFriend + ")", "(IDMain, IDFriend)");
			return true;
		}
		else {
			return false;
		}
	}

	public boolean removeFriend(int idMain, int idFriend) throws SQLException {
		userInfo.removeData("Friends", "IDMain = " + idMain + " AND IDFriend = " + idFriend);
		return true;
	}

	public void logout() throws SQLException {
		userInfo.closeConnection();
	}

}
