package web.commands;

import business.entities.Material;
import business.exceptions.DatabaseConnectionException;
import business.exceptions.UserException;
import business.services.MaterialFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ManageMaterialCommand extends CommandProtectedPage
{
    MaterialFacade materialFacade;
    
    public ManageMaterialCommand(String pageToShow, String role)
    {
        super(pageToShow, role);
        materialFacade = new MaterialFacade(database);
    }
    
    public void refreshList(HttpServletRequest request) throws DatabaseConnectionException, UserException
    {
        List<Material> materialsList = materialFacade.getAllMaterials();
        request.getServletContext().setAttribute("materialsList", materialsList);
    }
    
    private Material getMaterialFromParameters(HttpServletRequest request)
    {
        int materialID;
        String name;
        String unit;
        double buyPricePerUnit;
        double pricePerUnit;
        int length;
        int width;
        int height;
        String functionality;
    
        try
        {
            name = request.getParameter("name");
            unit = request.getParameter("unit");
            buyPricePerUnit = Double.parseDouble(request.getParameter("buyPricePerUnit"));
            pricePerUnit = Double.parseDouble(request.getParameter("pricePerUnit"));
            length = Integer.parseInt(request.getParameter("length"));
            width = Integer.parseInt(request.getParameter("width"));
            height = Integer.parseInt(request.getParameter("height"));
            functionality = request.getParameter("functionality");
            
            String idString = request.getParameter("materialID");
            if (idString != null)
            {
                materialID = Integer.parseInt(idString);
                return new Material(materialID, name, unit, buyPricePerUnit, pricePerUnit, length, width, height, functionality);
            }
            return new Material(name, unit, buyPricePerUnit, pricePerUnit, length, width, height, functionality);
        }
        catch (NumberFormatException ex)
        {
            request.setAttribute("error", "Wrong Input");
            return null;
        }
    }
    
    public String createMaterialPage(HttpServletRequest request, String action) throws DatabaseConnectionException, UserException
    {
        List<String> functionalities = materialFacade.getFunctionalities();
        List<String> units = materialFacade.getUnits();
        request.setAttribute("functionalities", functionalities);
        request.setAttribute("units", units);
        request.setAttribute("action", action);
        return "managematerialpage";
    }
    
    public String confirmCreation(HttpServletRequest request) throws DatabaseConnectionException, UserException
    {
        Material material = getMaterialFromParameters(request);
        if (material == null)
        {
            return createMaterialPage(request, "create");
        }
        materialFacade.insertMaterial(material);
        
        refreshList(request);
        return pageToShow;
    }
    
    public String editMaterialPage(HttpServletRequest request, int materialID, String action) throws DatabaseConnectionException, UserException
    {
        Material material = materialFacade.getMaterialById(materialID);
        List<String> functionalities = materialFacade.getFunctionalities();
        List<String> units = materialFacade.getUnits();
        request.setAttribute("material", material);
        request.setAttribute("functionalities", functionalities);
        request.setAttribute("units", units);
        request.setAttribute("action", action);
        return "managematerialpage";
    }
    public String confirmEdit(HttpServletRequest request) throws DatabaseConnectionException, UserException
    {
        int materialID;
        try
        {
            materialID = Integer.parseInt(request.getParameter("materialID"));
        }
        catch (NumberFormatException ex)
        {
            request.setAttribute("error", "Wrong Input");
            return pageToShow;
        }
        
        Material material = getMaterialFromParameters(request);
        if (material == null)
        {
            return editMaterialPage(request, materialID, "edit");
        }
        materialFacade.updateMaterial(material);
    
        refreshList(request);
        return pageToShow;
    }
    
    public String deleteMaterial(HttpServletRequest request, int materialID) throws DatabaseConnectionException, UserException
    {
        int res = materialFacade.deleteMaterialById(materialID);
        if (res <= 0)
        {
            request.setAttribute("error", "Du kan ikke slette et materiale, der bliver brugt i en eksisterende stykliste.");
        }
        refreshList(request);
        return pageToShow;
    }
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException, DatabaseConnectionException
    {
        String action = request.getParameter("action");
        
        if (action != null)
        {
            // can also be declared in the specific methods that need it, instead of here.
            int materialID =-1;
            try
            {
                String idString = request.getParameter("materialID");
                if (idString != null)
                {
                    materialID = Integer.parseInt(idString);
                }
            }
            catch (NumberFormatException ex)
            {
                request.setAttribute("error", "Wrong Input");
                return pageToShow;
            }
            
            if (action.equals("create"))
            {
                return createMaterialPage(request, action);
            }
            else if (action.equals("confirmcreation"))
            {
                return confirmCreation(request);
            }
            else if (action.equals("edit"))
            {
                return editMaterialPage(request, materialID, action);
            }
            else if (action.equals("confirmedit"))
            {
                return confirmEdit(request);
            }
            else if (action.equals("delete"))
            {
                return deleteMaterial(request, materialID);
            }
            else
            {
                request.setAttribute("error", "Action not recognised");
            }
        }
        return pageToShow;
    }
}
