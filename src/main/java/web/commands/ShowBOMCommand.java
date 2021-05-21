package web.commands;

import business.entities.Material;
import business.entities.OrderLine;
import business.exceptions.DatabaseConnectionException;
import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowBOMCommand extends CommandProtectedPage {

    public ShowBOMCommand(String pageToShow, String role) {
        super(pageToShow, role);

    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException, DatabaseConnectionException
    {
        List<OrderLine> BOM;
       // request.setAttribute("BOM", BOM);

        try
        {
            BOM = (List<OrderLine>) request.getServletContext().getAttribute("BOM");
        }
        catch (ClassCastException ex)
        {
            request.setAttribute("Error", "Wrong Input");
            return "index";
        }

        return pageToShow;
    }

}
