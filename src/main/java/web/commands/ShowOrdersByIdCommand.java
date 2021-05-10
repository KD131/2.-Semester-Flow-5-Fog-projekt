package web.commands;

import business.entities.User;
import business.exceptions.DatabaseConnectionException;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowOrdersByIdCommand extends CommandProtectedPage
{
    private OrderFacade orderFacade;

    public ShowOrdersByIdCommand(String pageToShow, String role)
    {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
    }
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException, DatabaseConnectionException
    {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        request.setAttribute("orderListings", orderFacade.getOrdersByUserId(user.getId()));

        return pageToShow;
    }
}
