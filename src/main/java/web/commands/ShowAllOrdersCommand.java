package web.commands;

import business.exceptions.DatabaseConnectionException;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAllOrdersCommand extends CommandProtectedPage
{
    private OrderFacade orderFacade;
    
    public ShowAllOrdersCommand(String pageToShow, String role)
    {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
    }
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException, DatabaseConnectionException
    {
        request.setAttribute("orderListings", orderFacade.getAllOrders());
        return pageToShow;
    }
}
