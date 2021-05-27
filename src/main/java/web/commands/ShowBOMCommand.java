package web.commands;

import business.entities.Material;
import business.entities.OrderLine;
import business.entities.User;
import business.exceptions.DatabaseConnectionException;
import business.exceptions.UserException;
import business.services.MaterialsCalculator;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowBOMCommand extends CommandProtectedPage {
    MaterialsCalculator calculator;

    public ShowBOMCommand(String pageToShow, String role) {
        super(pageToShow, role);

    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException, DatabaseConnectionException
    {
        int userID = ((User) request.getSession().getAttribute("user")).getId();
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        
        /* TEST FROM BEFORE BOM WAS IN DATABASE
        int carportLength = Integer.parseInt(request.getParameter("carportLength"));
        int carportWidth = Integer.parseInt(request.getParameter("carportWidth"));
        int shedLength = Integer.parseInt(request.getParameter("shedLength"));
        int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        
        List<Material> materialsList = (List<Material>) request.getServletContext().getAttribute("materialsList");
        
        calculator = new MaterialsCalculator(materialsList, carportLength, carportWidth, shedLength, shedWidth);
        
        List<OrderLine> BOM = calculator.showBOM();
         */
        
        OrderFacade orderFacade = new OrderFacade(database);
        List<OrderLine> BOM = orderFacade.getOrderLinesByOrderId(orderID, userID);
        
        request.setAttribute("BOM", BOM);
        request.setAttribute("orderID", orderID);

        return pageToShow;
    }

}
