package web.commands;

import business.entities.Order;
import business.entities.OrderLine;
import business.entities.User;
import business.exceptions.DatabaseConnectionException;
import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ConfirmOrderCommand extends OrderListCommand
{

    public ConfirmOrderCommand(String pageToShow, String role)
    {
        super(pageToShow, role);
    }
    
    private String setPrice(HttpServletRequest request, int orderID) throws DatabaseConnectionException, UserException
    {
        double total;
    
        try
        {
            total = Double.parseDouble(request.getParameter("total"));
        }
        catch (NumberFormatException ex)
        {
            request.setAttribute("error", "Wrong input");
            refreshList(request);
            return pageToShow;
        }
    
        int userID = ((User) request.getSession().getAttribute("user")).getId();
        List<OrderLine> BOM = orderFacade.getOrderLinesByOrderId(orderID, userID);
        Order order = new Order(BOM);
        request.setAttribute("orderID", orderID);
        request.setAttribute("currTotal", total);
        if (BOM != null)    // it shouldn't be when logged in as employee
        {
            request.setAttribute("BOMTotal", order.calcTotal());
        }
        return "setpricepage";
    }
    
    private String confirmOrder(HttpServletRequest request, int orderID) throws DatabaseConnectionException, UserException
    {
        double total;
    
        try
        {
            total = Double.parseDouble(request.getParameter("total"));
        }
        catch (NumberFormatException ex)
        {
            request.setAttribute("error", "Wrong input");
            refreshList(request);
            return pageToShow;
        }
        orderFacade.setTotal(orderID, total);
        orderFacade.confirmOrder(orderID);
        refreshList(request);
        return pageToShow;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException, DatabaseConnectionException
    {
        int orderID;
        String action = request.getParameter("action");

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
        
        if (action != null && action.equals("setprice"))
        {
            return setPrice(request, orderID);
        }
        else if (action != null && action.equals("confirm"))
        {
            return confirmOrder(request, orderID);
        }
        else
        {
            request.setAttribute("error", "Action not recognised");
            refreshList(request);
            return pageToShow;
        }
    }
}
