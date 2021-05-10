package web.commands;

import business.entities.Order;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnconfirmOrderCommand extends CommandProtectedPage
{
    private OrderFacade orderFacade;

    public UnconfirmOrderCommand(String pageToShow, String role)
    {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException
    {
        int orderID;
        Order order = (Order) request.getSession().getAttribute("orderID");

        try
        {
            orderID = Integer.parseInt(request.getParameter("orderID"));
        }
        catch (NumberFormatException ex)
        {
            request.setAttribute("Error", "Wrong input");
            return "index";
        }
        orderFacade.unconfirmOrder(order);
        return "index";
    }
}
