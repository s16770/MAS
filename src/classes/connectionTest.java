package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class connectionTest {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		String dbURL = "jdbc:sqlserver://\\DESKTOP-6HAG2U2:1434;databaseName=s16770;integratedSecurity=true";
		
		Connection conn = DriverManager.getConnection(dbURL);
		if (conn != null) {
		    System.out.println("Connected");
		}
		
		String sqlQuery = "SELECT * FROM test";
		
		Statement st = conn.createStatement();
		
		ResultSet res = st.executeQuery(sqlQuery);
		
		ResultSetMetaData rsmd = res.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (res.next()) {
		    for (int i = 1; i <= columnsNumber; i++) {
		        if (i > 1) System.out.print(",  ");
		        String columnValue = res.getString(i);
		        System.out.print(columnValue + " " + rsmd.getColumnName(i));
		    }
		    System.out.println("");
		}

		conn.close();
	}

}
