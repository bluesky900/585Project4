import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;

public class DBInterface {

	UserData userInfo;
	DES_EncryptionDecryption des;

	public DBInterface() {
		userInfo = new UserData();
		try {
			des = new DES_EncryptionDecryption();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Boolean login(String user, String password) throws SQLException {
		ResultSet results = userInfo.getData("Users", "password", "Email = " + user);

		System.out.println("results: \n" + results.getString("password") + "\n" + password);

		String encrypt = des.encrypt(password);
		if(results.getString("password").equals(encrypt)) {
			results.close();
			return true;
		} else {
			results.close();
			return false;
		}
	}

	public void register(String email, String password, String firstName, String lastName, int age) throws SQLException {

		String encrypt = des.encrypt(password);
		String columns = "(FirstName, LastName, Age, Password, Email)";
		String data = "(" + firstName + ", " + lastName + ", " + Integer.toString(age) + ", '" + encrypt + "', " + email + ")";

		userInfo.insertData("Users", data, columns);
		int temp = userInfo.getData("Users", "ID", "Email = " + email).getInt("ID");
		System.out.print("NEW USER ID: " + temp);
		userInfo.insertData("UserSettings", "(" + Integer.toString(temp) + ", 1, 1, 1)", "(IDUser, DOB, Posts, Status)");
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

	public int removeFriend(int idMain, int idFriend) throws SQLException {
		int numberDeleted = userInfo.removeData("Friends", "IDMain = " + idMain + " AND IDFriend = " + idFriend);
		return numberDeleted;
	}

	public boolean addPost(int idOwner, String title, String content) throws SQLException {
		userInfo.insertData("Posts", "(" + idOwner + ", " + title + ", " + content + ")", "(IDOwner, Title, Content)");
		return true;
	}

	public int removePost(int idOwner, int idPost) throws SQLException {
		int numberDeleted = userInfo.removeData("Posts", "IDOwner = " + idOwner + " AND IDPost = " + idPost);
		return numberDeleted;
	}

	public void updateStatus(int idUser, String status) throws SQLException {
		userInfo.updateData("Users", "Status=" + status + " ", "ID=" + idUser);
	}

	//updates any data you request
	//updates must be the data you desire to be uploaded in the format field1=value1, field2=value2, etc...
	//conditions must be of the format field1 = value1 AND field2 = value2 AND field3 = value3 etc...
	public void updateDate(String table, String updates, String conditions) throws SQLException {
		userInfo.updateData(table, updates, conditions);
	}

	public void logout() throws SQLException {
		userInfo.closeConnection();
	}

}
