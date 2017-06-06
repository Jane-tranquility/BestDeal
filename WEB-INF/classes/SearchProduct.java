import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@WebServlet("/SearchProduct")

/* 
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Game Speed Application.

*/

public class SearchProduct extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		HashMap<String,Product> products=MySQLDataStoreUtilities.getData();
		String targetId = request.getParameter("searchId");

		if ((targetId != null) && products.containsKey(targetId.trim())){
			Product product=products.get(targetId);
		
			Utilities utility = new Utilities(request,pw);
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
		
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Search</a>");
			pw.print("</h2><div class='entry'><table id='Search'>");
			pw.print("<tr>");
			pw.print("<td><div id='search_item'>");

			pw.print("<h3>"+targetId+"</h3>");
			pw.print("<strong>$"+product.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/"+product.getProductType()+"s/"+product.getImage()+"' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
				"<input type='hidden' name='name' value='"+product.getProductID()+"'>"+
				"<input type='hidden' name='type' value='"+product.getProductType()+"'>"+
				"<input type='hidden' name='maker' value='"+product.getManufacture()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+
			    "<input type='hidden' name='name' value='"+product.getProductID()+"'>"+
				"<input type='hidden' name='type' value='"+product.getProductType()+"'>"+
				"<input type='hidden' name='maker' value='"+product.getManufacture()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+
			    "<input type='hidden' name='name' value='"+product.getProductID()+"'>"+
				"<input type='hidden' name='type' value='"+product.getProductType()+"'>"+
				"<input type='hidden' name='maker' value='"+product.getManufacture()+"'>"+
				"<input type='hidden' name='access' value=''>"+
			    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			pw.print("</tr></table></div></div></div>");	

			utility.printHtml("Footer.html");
		}
		
				
	}

}