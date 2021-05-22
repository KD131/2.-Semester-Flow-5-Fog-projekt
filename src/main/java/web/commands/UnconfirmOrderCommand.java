package web.commands;

import business.exceptions.DatabaseConnectionException;
import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnconfirmOrderCommand extends OrderListCommand
{

    public UnconfirmOrderCommand(String pageToShow, String role)
    {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException, DatabaseConnectionException
    {
        int orderID;
        
        try
        {
            orderID = Integer.parseInt(request.getParameter("orderID"));
        }
        catch (NumberFormatException ex)
        {
            request.setAttribute("error", "Wrong input");
            refreshList(request);
            return pageToShow;
        }
        orderFacade.unconfirmOrder(orderID);
    
        refreshList(request);
        return pageToShow;
    }
}
