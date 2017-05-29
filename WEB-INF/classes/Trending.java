import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Trending")

public class Trending extends HttpServlet {

	/* Trending Page Displays all the Laptops and their Information in Game Speed*/

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Top List</a>");
		pw.print("</h2><div class='entry'><table class='gridtable'>");
		

		pw.print("<h2>Top five most liked products(based on rating of 5)</h2>");
		pw.print("<tr><td>Product Name</td><td>Count of rating 5</td></tr>");
		MongoDBDataStoreUtilities.getFiveMostLikedProducts(pw);
		pw.print("</table>");

		pw.print("<table class='gridtable'>");
		pw.print("<h2>Top five zipcodes of products sold</h2>");
		pw.print("<tr><td>Zip Codes</td><td>Count of products sold</td></tr>");
		MongoDBDataStoreUtilities.getTopFiveZipcodes(pw);
		pw.print("</table>");

		pw.print("<table class='gridtable'>");
		pw.print("<h2>Top five most sold products(Based on number of sold, all ratings applied)</h2>");
		pw.print("<tr><td>Produce Name</td><td>Count of products sold</td></tr>");
		MongoDBDataStoreUtilities.getTopFiveProducts(pw);
			
		pw.print("</table></div></div></div>");	
		utility.printHtml("Footer.html");
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
