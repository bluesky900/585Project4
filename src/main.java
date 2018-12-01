import java.sql.SQLException;

public class main {

	public static void main(String[] args) throws SQLException {

        DBInterface connection = new DBInterface();
        System.out.println(connection.login("\'registerTest@gmail.com\'", "\'testword34&\'"));
        //connection.register("\'registerTest@gmail.com\'", "\'testword34&\'", "\'Peter\'", "\'Vander\'", 52);
        connection.updateStatus(1, "\'I am testing this status\'");
        connection.searchData("Users", "FirstName, LastName, Password, Email, Status", "Email = \'testMail1@gmail.com\'");
        //System.out.println(connection.addPost(1, "\'Test post\'", "\'This is a test of the posting system\'"));
        //connection.removePost(1, 4);
        //System.out.println(connection.addPost(12, 11));
        connection.logout();
	}

}
