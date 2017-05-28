import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SubmitReview")

public class SubmitReview extends HttpServlet {
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);

		String name = request.getParameter("productName");
		String type = request.getParameter("productType");
		String maker = request.getParameter("productMaker");
		String rating = request.getParameter("ratings");
		String date = request.getParameter("reviewDate");
		String text = request.getParameter("reviewText");
		String zip=request.getParameter("retailerZip");

		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}


		try{
			
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
	    	pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Review</a>");
			pw.print("</h2><div class='entry'>");

			utility.storeReview(name, zip, type, maker, rating, date, text);

        	pw.print("<h2>Review for ");
        	pw.print(name);  
        	pw.print(" is stored");
			
		}catch (Exception e){
        	pw.print("<h2>MongoDB is not up and running");
  
		}
		

		
		
		pw.print("</h2></div></div></div>");		
		utility.printHtml("Footer.html");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}
