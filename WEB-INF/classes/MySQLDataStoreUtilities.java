import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;

public class MySQLDataStoreUtilities{
	//Connection conn = null;
/*
	public void getConnection() { 
		try { 
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/BestDealDatabase","root","root"); 
		} catch(Exception e) {
		} 
	}*/

	public static HashMap<String, User> selectUser(){
		HashMap<String, User> users=new HashMap<String, User>();
		Connection connection=null;
		try { 
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/BestDealDatabase?useSSL=false","root","root"); 
		
			String select="SELECT * FROM Registration";
			PreparedStatement pst = connection.prepareStatement(select);
			ResultSet rs=pst.executeQuery();
		
			while(rs.next()){
				if (!users.containsKey(rs.getString("username"))){
					User user=new User(rs.getString("username"), rs.getString("password"), rs.getString("usertype"));
					users.put(rs.getString("username"),user);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} 
		return users;
	}

	public static void insertUser(String userName, String password, String userType){
		Connection connection=null;
		try { 
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/BestDealDatabase?useSSL=false","root","root");

			String insert="INSERT INTO Registration(username, password, usertype)"+"VALUES(?,?,?);";
			PreparedStatement pst=connection.prepareStatement(insert);
			pst.setString(1,userName);
			pst.setString(2,password);
			pst.setString(3,userType);
			pst.execute();

		} catch(Exception e) {
			e.printStackTrace();
		} 
	}

	public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder(){
		Connection connection=null;
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments=new HashMap<Integer, ArrayList<OrderPayment>>();

		try { 
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/BestDealDatabase?useSSL=false","root","root");

			String query="SELECT * FROM CustomerOrders";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			//ArrayList<OrderPayment> orderPayment=new ArrayList<OrderPayment>();

			while(rs.next()){
				if (!orderPayments.containsKey(rs.getInt("orderID"))){
					ArrayList<OrderPayment> orderPayment=new ArrayList<OrderPayment>();
					orderPayments.put(rs.getInt("orderID"), orderPayment);
				}
				ArrayList<OrderPayment> old=orderPayments.get(rs.getInt("orderID"));
				OrderPayment op=new OrderPayment(rs.getInt("orderID"),rs.getString("userName"),rs.getString("orderName"), rs.getDouble("orderPrice"),rs.getString("userAddress"),rs.getString("creditcardNO"),rs.getString("deliveryDate"));
				old.add(op);
			}

		} catch(Exception e) {
			e.printStackTrace();
		} 

		return orderPayments;
	}

	public static void insertOrder(int orderId, String userName, String orderName, Double orderPrice, String userAddress, String creditCardNo){
		Connection connection=null;
		try { 
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/BestDealDatabase?useSSL=false","root","root");

			String insert="INSERT INTO CustomerOrders(orderID, userName, orderName, orderPrice, userAddress, creditcardNO, deliveryDate)"+"VALUES(?,?,?,?,?,?,date(now() + INTERVAL 14 DAY)) ;";
			PreparedStatement pst=connection.prepareStatement(insert);

			pst.setString(1,Integer.toString(orderId));
			pst.setString(2,userName);
			pst.setString(3,orderName);
			pst.setString(4,Double.toString(orderPrice));
			pst.setString(5,userAddress);
			pst.setString(6,creditCardNo);
			pst.execute();

		} catch(Exception e) {
			e.printStackTrace();
		} 
	}

	public static void removeOrder(ArrayList<OrderPayment> lists){
		Connection connection=null;
		try { 
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/BestDealDatabase?useSSL=false","root","root");

			String delete="DELETE FROM CustomerOrders WHERE orderID=? AND orderName=? AND deliveryDate>date(now() + INTERVAL 5 DAY);";
			PreparedStatement pst=connection.prepareStatement(delete);

			for (OrderPayment item: lists){
				pst.setString(1,Integer.toString(item.getOrderId()));
				pst.setString(2,item.getOrderName());
				pst.execute();
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static HashMap<String, Product> getData(){
		HashMap<String, Product> products=new HashMap<String, Product>();
		Connection connection=null;
		try { 
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/BestDealDatabase?useSSL=false","root","root"); 
		
			String select="SELECT * FROM ProductCatalog";
			PreparedStatement pst = connection.prepareStatement(select);
			ResultSet rs=pst.executeQuery();
		
			while(rs.next()){
				if (!products.containsKey(rs.getString("productName"))){
					Product product=new Product(rs.getString("productID").toLowerCase(), rs.getString("productName").toLowerCase(), rs.getString("productType"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("manufacture"));
					products.put(rs.getString("productName").toLowerCase(),product);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} 
		return products;
	}

}