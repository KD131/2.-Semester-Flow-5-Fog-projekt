package web.commands;

import business.entities.User;
import business.exceptions.DatabaseConnectionException;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PayOrderCommand extends CommandProtectedPage
{
    OrderFacade orderFacade;
    
    public PayOrderCommand(String pageToShow, String role)
    {
        super(pageToShow, role);
        orderFacade = new OrderFacade(database);
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
            return pageToShow;
        }
        orderFacade.payOrder(orderID);
    
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
    
        request.setAttribute("orderListings", orderFacade.getOrdersByUserId(user.getId()));
        return pageToShow;
    }
}
