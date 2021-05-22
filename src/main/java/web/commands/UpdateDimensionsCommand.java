package web.commands;

import business.entities.Order;
import business.exceptions.DatabaseConnectionException;
import business.exceptions.IllegalDimensionsException;
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
        
        // validates dimensions of the shed against the carport and sets and error message
        try
        {
            Order.validateShed(carportWidth, carportLength, shedWidth, shedLength);
        }
        catch (IllegalDimensionsException e)
        {
            request.setAttribute("error", e.getMessage());
            refreshList(request);
            return pageToShow;
        }
        orderFacade.updateDimensions(orderID, carportLength, carportWidth, shedLength, shedWidth);
    
        refreshList(request);
        return pageToShow;
    }
}
