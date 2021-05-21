package web.commands;

import business.entities.Material;
import business.entities.OrderLine;
import business.exceptions.DatabaseConnectionException;
import business.exceptions.UserException;
import business.services.MaterialsCalculator;

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
        int carportLength = Integer.parseInt(request.getParameter("carportLength"));
        int carportWidth = Integer.parseInt(request.getParameter("carportWidth"));
        int shedLength = Integer.parseInt(request.getParameter("shedLength"));
        int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        
        List<Material> materialsList = (List<Material>) request.getServletContext().getAttribute("materialsList");
        
        calculator = new MaterialsCalculator(materialsList, carportLength, carportWidth, shedLength, shedWidth);
        
        
        List<OrderLine> BOM = calculator.showBOM();
        request.setAttribute("BOM", BOM);

        try
        {
            BOM = (List<OrderLine>) request.getServletContext().getAttribute("BOM");
        }
        catch (ClassCastException ex)
        {
            request.setAttribute("Error", "Wrong Input");
            return "index";
        }

        return pageToShow;
    }

}