package web;

import business.exceptions.DatabaseConnectionException;
import business.exceptions.UserException;
import business.persistence.Database;
import business.services.MaterialFacade;
import web.commands.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FrontController", urlPatterns = {"/fc/*"})
public class FrontController extends HttpServlet
{
    /*
    LOCAL TEST ENV:
        c5cp
        c5cp
        jdbc:mysql://localhost:3306/carport?serverTimezone=CET
    
    private final static String USER = "c5cp";
    private final static String PASSWORD = "c5cp";
    private final static String URL = "jdbc:mysql://localhost:3306/carport?serverTimezone=CET";
     */
    
    /*
    PROD ENV:
        JDBC_USER
        JDBC_PASSWORD
        JDBC_CONNECTION_CONNECTION_STRING
    
    private final static String USER = System.getenv("JDBC_USER");
    private final static String PASSWORD = System.getenv("JDBC_PASSWORD");
    private final static String URL = System.getenv("JDBC_CONNECTION_STRING");
     */
    
    private final static String USER = "c5cp";
    private final static String PASSWORD = "c5cp";
    private final static String URL = "jdbc:mysql://localhost:3306/carport?serverTimezone=CET";

    public static Database database;

    public void init() throws ServletException
    {
        // Initialize database connection
        if (database == null)
        {
            try
            {
                database = new Database(USER, PASSWORD, URL);
            }
            catch (ClassNotFoundException ex)
            {
                Logger.getLogger("web").log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        
        MaterialFacade materialFacade = new MaterialFacade(database);
        try
        {
            getServletContext().setAttribute("materialsList", materialFacade.getAllMaterials());
        }
        catch (UserException | DatabaseConnectionException e)
        {
            Logger.getLogger("web").log(Level.SEVERE, e.getMessage(), e);
        }

        // Initialize whatever global datastructures needed here:

    }

    protected void processRequest(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            Command action = Command.fromPath(request, database);

            if (action instanceof CommandUnknown) {
                response.sendError(404);
                return;
            }

            String view = action.execute(request, response);

            if (view.startsWith(Command.REDIRECT_INDICATOR)) {
                String page = view.substring(Command.REDIRECT_INDICATOR.length());
                response.sendRedirect(page);
                return;
            }

            request.getRequestDispatcher("/WEB-INF/" + view + ".jsp").forward(request, response);
        }
        catch (UnsupportedEncodingException | UserException | DatabaseConnectionException ex)
        {
            request.setAttribute("problem", ex.getMessage());
            Logger.getLogger("web").log(Level.SEVERE, ex.getMessage(), ex);
            request.getRequestDispatcher("/errorpage.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo()
    {
        return "FrontController for application";
    }

}
