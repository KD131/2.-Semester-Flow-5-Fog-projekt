package web.commands;

import business.entities.Order;
import business.exceptions.DatabaseConnectionException;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmOrderCommand extends CommandProtectedPage
{
    private OrderFacade orderFacade;

    public ConfirmOrderCommand(String pageToShow, String role)
    {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
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
            request.setAttribute("orderListings", orderFacade.getAllOrders());
            return pageToShow;
        }
        orderFacade.confirmOrder(orderID);
    
        request.setAttribute("orderListings", orderFacade.getAllOrders());
        return pageToShow;
    }
}
