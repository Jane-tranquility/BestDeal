public class MySQLDataStoreUtilities{
	Connection conn = null;

	public void getConnection() { 
		try { 
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/BestDealDatabase“ ,"root",“root"); 
		} catch(Exception e) {
		} 
	}


}