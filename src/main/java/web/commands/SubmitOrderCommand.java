package web.commands;

import business.entities.Material;
import business.entities.Order;
import business.entities.User;
import business.exceptions.DatabaseConnectionException;
import business.exceptions.IllegalDimensionsException;
import business.exceptions.UserException;
import business.services.MaterialsCalculator;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SubmitOrderCommand extends CommandProtectedPage
{
    private OrderFacade orderFacade;
    
    public SubmitOrderCommand(String pageToShow, String role)
    {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
    }
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException, DatabaseConnectionException
    {
        int carportLength;
        int carportWidth;
        String shed = request.getParameter("shed");
        int shedLength;
        int shedWidth;
        User user = (User) request.getSession().getAttribute("user");
        List<Material> materialsList = (List<Material>) request.getServletContext().getAttribute("materialsList");
        
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
            
            // validates dimensions of the shed against the carport and sets and error message
            try
            {
                Order.validateShed(carportWidth, carportLength, shedWidth, shedLength);
            }
            catch (IllegalDimensionsException e)
            {
                request.setAttribute("error", e.getMessage());
                return "index";
            }
    
            MaterialsCalculator calculator = new MaterialsCalculator(materialsList, carportLength, carportWidth, shedLength, shedWidth);
            
            Order order = new Order(user, carportLength, carportWidth, shedLength, shedWidth, calculator.showBOM());
            orderFacade.insertOrder(order);
        }
        else
        {
            MaterialsCalculator calculator = new MaterialsCalculator(materialsList, carportLength, carportWidth);
            
            Order order = new Order(user, carportLength, carportWidth, calculator.showBOM());
            orderFacade.insertOrder(order);
        }
        
        return "index";
    }
}
