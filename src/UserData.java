import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserData {

	Connection conn;
	//String url = "jdbc:sqlite:/Users/Maaz/IdeaProjects/585Project4/fbLiteDB.db";
	//String url = "jdbc:sqlite:C:\\Users\\shah\\Desktop\\areeba\\fbLiteDB.db";
	String url = "jdbc:sqlite:/Users/TGentry/Documents/workspace neon/585Project4/fbLiteDB.db";
  //String url = "jdbc:sqlite:C:\\Users\\Damien\\Documents\\CSUN\\Fall 2018\\COMP 585\\Project3\\Project3\\585Project4\\fbLiteDB.db";
	//String url = "jdbc:sqlite:C:\\Users\\shah\\IdeaProjects\\585Project4-backend_pass_encryption\\fbLiteDB.db";

	public UserData() {
		System.out.println("Connecting to database...");
		try {
			conn = DriverManager.getConnection(url);
			System.out.println("Connection established!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	//gets data from the designated table and fields
	public ResultSet getData(String table, String fields) throws SQLException {
		ResultSet results;

			String query = "SELECT " + fields + " FROM " + table; //fields must be in the format field1, field2, field3, etc.. including the commas

		Statement stmt = conn.createStatement();
		results = stmt.executeQuery(query);
		stmt.close();

		return results;
	}

	//gets data from the designated table and fields overriden version that will select from a specific row instead of the whole table
	public ResultSet getData(String table, String fields, String condition) throws SQLException {
		ResultSet results;

		//fields must be in the format field1, field2, field3, etc.. including the commas
		//condition must be in the format insertField = insertValue
		//for multiple conditions the AND and OR operators must be placed inbetween each condition
		String query = "SELECT " + fields + " FROM " + table + " WHERE " + condition;

		System.out.println(query);

		Statement stmt = conn.createStatement();
		results = stmt.executeQuery(query);
		//stmt.close();

		return results;
	}

	//Inserts a new row in to the designated table using the data
	public int insertData(String table, String data, String columns) throws SQLException {
		int results;

		String query = "INSERT INTO " + table + " " + columns + " VALUES " + data; //data and columns must be in the format (data1, data2, data3, etc....) including the parenthesis
		System.out.println(query);

		PreparedStatement pstmt = conn.prepareStatement(query);
		results = pstmt.executeUpdate();
		pstmt.close();

		return results;
	}

	public int removeData(String table, String conditions) throws SQLException {
		int numberDeleted;

		String query = "DELETE FROM " + table + " WHERE " + conditions;
		PreparedStatement pstmt = conn.prepareStatement(query);
		numberDeleted = pstmt.executeUpdate();
		return numberDeleted;

	}

	public void updateData(String table, String updates, String conditions) throws SQLException {
		String query = "UPDATE " + table + " SET " + updates + " WHERE " + conditions;
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.executeUpdate();
	}

	public void closeConnection() throws SQLException {
		System.out.println("Closing connection...");
		conn.close();
		System.out.println("Connection closed!");
	}

}
