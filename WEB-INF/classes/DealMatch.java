import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.io.IOException;
import  java.io.StringReader;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

@WebServlet("/DealMatch")

public class DealMatch extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		

		HashMap<String,Product> selectedproducts=new HashMap<String,Product>();
		
		pw.print("<div id='content'>"); 
		pw.print("<div class='post'>"); 
		pw.print("<h2 class='title'>"); 
		pw.print("<a href='#'>Welcome to Best Deal</a></h2>"); 
		pw.print("<div class='entry'>"); 
		//pw.print("<img src='images/site/sites.jpg' style='width: 600px; display: block; margin-left: auto; margin-right: auto' />")
		pw.print("<br> <br>"); 
		pw.print("<h2>The world trusts us to deliver SPEEDY service for video-gaming fans</h2>"); 
		pw.print("<br> <br>"); 
		pw.print("<h1>We beat our competitors in all aspects. Price-Match Guaranteed</h2>"); 
		

		try { 
			HashMap<String,Product> productmap=MySQLDataStoreUtilities.getData(); 
			String line=null; 

			for(Map.Entry<String, Product> entry : productmap.entrySet()) { 
				if(selectedproducts.size()<2 && !selectedproducts.containsKey(entry.getKey())) { 
					BufferedReader reader = new BufferedReader(new FileReader(new File(TOMCAT_HOME+"\\webapps\\BestDeal\\DealMatches.txt"))); 
					line=reader.readLine(); 
					if(line==null) { 
						pw.print("<h2 align='center'>No Offers Found</h2>");
						break;
					}else {                  
						do { 
							if(line.contains(entry.getKey())) { 
								pw.print("<h2>"+line+"</h2>"); 
								line=reader.readLine(); 
								pw.print("<h2>"+line+"</h2>"); 
								pw.print("<br>"); 
								selectedproducts.put(entry.getKey(),entry.getValue()); 
								break; 
							} 
						}while((line = reader.readLine()) != null); 
					} 
				} 
			} 
		}catch(Exception e){pw.print("connection error");}

		pw.print("</div></div>");
		pw.print("<div class='post'><h2 class='title meta'><a style='font-size: 24px;'>Deal Matches</a></h2>");
		pw.print("<div class='entry'><table id='dealMatch'><tr>");

		for(Map.Entry<String, Product> entry : selectedproducts.entrySet()){
			System.out.println(entry.getKey());
			pw.print("<td><div id='deal_item'>");
			pw.print("<h3>"+entry.getKey()+"</h3>");
			pw.print("<strong>$"+entry.getValue().getPrice()+"</strong>");
			pw.print("<ul><li id='item'><img src="+"images/"+entry.getValue().getProductType()+"s/"+entry.getValue().getImage()+" alt='' width='240' height='200'></li>");
			pw.print("<li><form action='Cart' method='post'>");
			pw.print("<input type='submit' class='btnbuy' value='Buy Now'>");
			pw.print("<input type='hidden' name='name' value='"+entry.getKey()+"'>");
			pw.print("<input type='hidden' name='name' value='"+entry.getKey()+"'>");
			pw.print("<input type='hidden' name='type' value='"+entry.getValue().getProductType()+">");
			pw.print("<input type='hidden' name='maker' value='"+entry.getValue().getManufacture()+"'>");
			pw.print("<input type='hidden' name='access' value=''></form></li>");
			pw.print("<li><form action='WriteReview' method='post'>");
			pw.print("<input type='submit' class='btnreview' value='WriteReview'>");
			pw.print("<input type='hidden' name='name' value='"+entry.getKey()+"'>");
			pw.print("<input type='hidden' name='name' value='"+entry.getKey()+"'>");
			pw.print("<input type='hidden' name='type' value='"+entry.getValue().getProductType()+">");
			pw.print("<input type='hidden' name='maker' value='"+entry.getValue().getManufacture()+"'>");
			pw.print("<input type='hidden' name='access' value=''></form></li>");
			pw.print("</ul></div></td>");
		}
		
		pw.print("</tr></table></div></div></div>");

		utility.printHtml("Footer.html");
				
	}

}