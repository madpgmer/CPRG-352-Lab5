
package ca.sait.shoppinglist.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Madhu
 */
public class ShoppingListServlet extends HttpServlet {

    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String username = (String) session.getAttribute("username");
        String query = request.getQueryString();
        
        if(username == null){            
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);                
        }else if (query != null && query.contains("logout")){
                session.invalidate();
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }else{
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        }
                
    
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();        
        String action = request.getParameter("action");
        //String username = (String)session.getAttribute("username");
        
        if(session.getAttribute("username") == null && action != null){
            //request.setAttribute("message", "Username is missing");
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }else if(action != null && action.equals("add")){            
                String item = request.getParameter("item");
                ArrayList<String> listOfItems = (ArrayList<String>) session.getAttribute("listOfItems");
                listOfItems.add(item);
                session.setAttribute("listOfItems", listOfItems);
             
        } else if (action != null && action.equals("delete")){
                String item = request.getParameter("item");
                ArrayList<String> listOfItems = (ArrayList<String>) session.getAttribute("listOfItems");
                listOfItems.remove(item);
                session.setAttribute("listOfItems", listOfItems);
             
        } else {        
                String username = request.getParameter("username");                
                session.setAttribute ("username", username);                    
                ArrayList<String> listOfItems = new ArrayList<>();        
                session.setAttribute ("listOfItems", listOfItems);
                    
        }
       getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        
    }
}

    
    

