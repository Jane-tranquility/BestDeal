import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import java.util.HashMap;
import java.util.Iterator;

@WebServlet("/Autocomplete")
/**
 *
 * @author nbuser
 */
public class Autocomplete extends HttpServlet {

    //private ServletContext context;
    private HashMap<String,Product> data= MySQLDataStoreUtilities.getData();

   /* @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }*/

    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");
        //System.out.print(action); 
        String targetId = request.getParameter("searchId");
        StringBuffer sb = new StringBuffer();

       /* if (targetId != null) {
            targetId = targetId.trim().toLowerCase();
        } else {
            context.getRequestDispatcher("/error.jsp").forward(request, response);
        }*/

        boolean namesAdded = false;
        if (action.equals("complete")) {

            // check if user sent empty string
            if (!targetId.equals("")) {
                 sb=AjaxUtilities.readdata(targetId);
                 if (sb!=null || !sb.equals("")){
                    namesAdded=true;
                 }
                 if (namesAdded){
                    response.setContentType("text/xml"); 
                    //response.setHeader("Cache-Control", "no-cache");
                    System.out.print(sb.toString());
                    response.getWriter().write("<products>" + sb.toString() + "</products>"); 
                 }
            }
        }/*else if (action.equals("lookup")) {

            // put the target composer in the request scope to display 
            if ((targetId != null) && data.containsKey(targetId.trim())) {
                request.setAttribute("item", data.get(targetId));
                context.getRequestDispatcher("SearchProduct").forward(request, response);
            }
        }*/
    }
}