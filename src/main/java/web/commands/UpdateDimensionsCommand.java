package web.commands;

import business.exceptions.DatabaseConnectionException;
import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateDimensionsCommand extends OrderListCommand {

    public UpdateDimensionsCommand(String pageToShow, String role)
    {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException, DatabaseConnectionException
    {
        int orderID;
        int carportLength;
        int carportWidth;
        int shedLength;
        int shedWidth;

        try
        {
            orderID = Integer.parseInt(request.getParameter("orderID"));
            carportLength = Integer.parseInt(request.getParameter("carportLength"));
            carportWidth = Integer.parseInt(request.getParameter("carportWidth"));
            shedLength = Integer.parseInt(request.getParameter("shedLength"));
            shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        }
        catch (NumberFormatException ex)
        {
            request.setAttribute("error", "Wrong Input");
            refreshList(request);
            return pageToShow;
        }
    
        if (shedLength > carportLength)
        {
            request.setAttribute("error", "Skur kan ikke være længere en carport.");
            refreshList(request);
            return pageToShow;
        }
        else if (shedWidth > carportWidth - 60)
        {
            request.setAttribute("error", "Skuret er for bredt. Den skal være mindst 60 cm smallere end carporten.");
            refreshList(request);
            return pageToShow;
        }
        orderFacade.updateDimensions(orderID, carportLength, carportWidth, shedLength, shedWidth);
    
        //TODO
        // not very elegant with this line everywhere, including all other OrderList Commands, and all error handling that returns to that page.
        // in general, a lot of duplicate error handling.
        refreshList(request);
        return pageToShow;
    }
}
