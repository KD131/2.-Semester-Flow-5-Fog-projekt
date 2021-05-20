package web.commands;

import business.entities.Material;
import business.exceptions.UserException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowMaterialsListCommand extends CommandProtectedPage {

    public ShowMaterialsListCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        List<Material> materialsList;

        try
        {
            materialsList = (List<Material>) request.getServletContext().getAttribute("materialsList");
        }
        catch (ClassCastException ex)
        {
            request.setAttribute("Error", "Wrong Input");
            return "index";
        }

        return pageToShow;
    }
}
