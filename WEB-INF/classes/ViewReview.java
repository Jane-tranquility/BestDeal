import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.ArrayList;
import com.mongodb.MongoException;

@WebServlet("/ViewReview")

public class ViewReview extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();


		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/

		Utilities utility = new Utilities(request, pw);
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String maker = request.getParameter("maker");
		String access = request.getParameter("access");

		/* StoreProduct Function stores the Purchased product in Orders HashMap.*/	
		
		//utility.storeProduct(name, type, maker, access);
		//displayCart(request, response);
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		//pw.print("<form name ='ViewReview' action='CheckOut' method='post'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Review</a>");
		pw.print("</h2><div class='entry'>");

		HashMap<String, ArrayList<Review>> reviews=new HashMap<String, ArrayList<Review>>();

		try{
			reviews=MongoDBDataStoreUtilities.selectReview();
			if((reviews!=null) && (reviews.get(name)!=null) &&(reviews.get(name).size()>0)){
				ArrayList<Review> listReview=reviews.get(name);

				for (Review oi : listReview) {
					pw.print("<table  class='gridtable'>");
					pw.print("<tr><td>Product Name:</td><td>"+oi.getProductName()+"</td></tr>"+
						"<tr><td>User Name:</td><td>"+oi.getUserName()+"</td></tr>"+
						"<tr><td>Retailer Zip:</td><td>"+oi.getRetailerZip()+"</td></tr>"+
						"<tr><td>Product Type:</td><td>"+oi.getProductType()+"</td></tr>"+
						"<tr><td>Product Maker:</td><td>"+oi.getProductMaker()+"</td></tr>"+
						"<tr><td>Review Rating:</td><td>"+oi.getReviewRating()+"</td></tr>"+
						"<tr><td>Review Date:</td><td>"+oi.getReviewDate()+"</td></tr>"+
						"<tr><td>Review Text:</td><td>"+oi.getReviewText()+"</td></tr>");
					pw.print("</table>");
				}
			
			}else{
				pw.print("<h4 style='color:red'>There's no review for this item.</h4>");
			}
		}catch(MongoException e){
			
        	pw.print("<h2>MongoDB is not up and running");
        	//pw.print("<br>Your Delivary Date is "+());
			pw.print("</h2>");		

		}
		

		
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		//Utilities utility = new Utilities(request, pw);
		
		//displayCart(request, response);
	}
}