package web.commands;

import business.exceptions.DatabaseConnectionException;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;

public abstract class OrderListCommand extends CommandProtectedPage
{
    protected OrderFacade orderFacade = new OrderFacade(database);
    
    public OrderListCommand(String pageToShow, String role)
    {
        super(pageToShow, role);
    }
    
    public void refreshList(HttpServletRequest request) throws DatabaseConnectionException, UserException
    {
        request.setAttribute("orderListings", orderFacade.getAllOrders());
    }
}
