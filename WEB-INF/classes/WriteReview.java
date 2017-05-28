import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/WriteReview")

public class WriteReview extends HttpServlet {
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
		//displayReview(request, response);
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='Review' action='SubmitReview' method='post'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Review</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table  class='gridtable'>");

		pw.print("<tr><td>Product Name:</td><td>");
		pw.print(name);
		pw.print("</td></tr>");

		pw.print("<tr><td>Product Type:</td><td>");
		pw.print(type);
		pw.print("</td></tr>");

		pw.print("<tr><td>Product Maker:</td><td>");
		pw.print(maker);
		pw.print("</td></tr></table><table><tr></tr>");

		pw.print("<tr>"+
				"<input type='hidden' name='productName' value='"+name+"'>"+
				"<input type='hidden' name='productType' value='"+type+"'>"+
				"<input type='hidden' name='productMaker' value='"+maker+"'>"+
			    "</tr>");

		pw.print("<tr><td>");
		pw.print("Review Ratings</td>");
		pw.print("<td><select name='ratings'><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select>");
		pw.print("</td></tr>");

		pw.print("<tr><td>");
     	pw.print("Retailer Zip</td>");
		pw.print("<td><input type='text' name='retailerZip'>");
		pw.print("</td></tr>");

		pw.print("<tr><td>");
     	pw.print("Review Date</td>");
		pw.print("<td><input type='text' name='reviewDate'>");
		pw.print("</td></tr>");

		pw.print("<tr><td>");
     	pw.print("Review Text</td>");
		pw.print("<td><input type='text' name='reviewText'>");
		pw.print("</td></tr>");

		pw.print("<tr><td colspan='2'>");
		pw.print("<input type='submit' name='submit' class='btnbuy'>");
        pw.print("</td></tr></table></form>");
		pw.print("</div></div></div>");	
		
		utility.printHtml("Footer.html");
	}
	

/* displayCart Function shows the products that users has bought, these products will be displayed with Total Amount.*/

	/*protected void displayReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
		
	}*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		//displayCart(request, response);
	}
}
