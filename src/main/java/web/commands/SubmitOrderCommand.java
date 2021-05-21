package web.commands;

import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitOrderCommand extends CommandProtectedPage
{
    private OrderFacade orderFacade;
    
    public SubmitOrderCommand(String pageToShow, String role)
    {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
    }
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException
    {
        int carportLength;
        int carportWidth;
        String shed = request.getParameter("shed");
        int shedLength;
        int shedWidth;
        User user = (User) request.getSession().getAttribute("user");
        
        try
        {
            carportLength = Integer.parseInt(request.getParameter("carportLength"));
            carportWidth = Integer.parseInt(request.getParameter("carportWidth"));
        }
        catch (NumberFormatException ex)
        {
            request.setAttribute("error", "Du har indtasted forkert input!");
            return "index";
        }
    
        if (shed.equals("yes"))
        {
            try
            {
                shedLength = Integer.parseInt(request.getParameter("shedLength"));
                shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
            }
            catch (NumberFormatException ex)
            {
                request.setAttribute("error", "Du har indtasted forkert input!");
                return "index";
            }
            
            if (shedLength > carportLength)
            {
                request.setAttribute("error", "Skur kan ikke være længere en carport.");
                return "index";
            }
            else if (shedWidth > carportWidth - 60)
            {
                request.setAttribute("error", "Skuret er for bredt. Den skal være mindst 60 cm smallere end carporten.");
                return "index";
            }
            
            Order order = new Order(user, carportLength, carportWidth, shedLength, shedWidth);
            orderFacade.insertOrder(order);
        }
        else
        {
            Order order = new Order(user, carportLength, carportWidth);
            orderFacade.insertOrder(order);
        }
        
        return "index";
    }
}
