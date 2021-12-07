/**
 * 
 */
package electricalsinventorysystem;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author SILK
 *
 */
public class DBConnection {

	private static final String URL_STRING = "jdbc:mysql://localhost:3306/";
	private static final String USER_STRING = "root";
	private static final String PASS_STRING = "";
	private static final String DBNAME_STRING = "electricals_inventory";
	
//	 private static final String PARAMS = "?verifyServerCertificate=false&useSSL=true";
	
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL_STRING+DBNAME_STRING, USER_STRING, PASS_STRING);
			if(connection!=null) {
				System.out.println("Connection to "+ DBNAME_STRING+ " SUCCEEDED!");
			}
			else {
				System.out.println("NO CONNECTION!");
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		
		
		return connection;
	}
	
	public static void closeConnection(Connection connection) {
		try {
			if (connection!= null) {
				connection.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		if (getConnection()== null) {		
//			System.err.println("CONNECTION LIMITED!");
//		} else {
//			System.out.println("CONNECTED!");
//		}
//		
//	}
}
