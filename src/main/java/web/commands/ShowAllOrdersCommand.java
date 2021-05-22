package web.commands;

import business.exceptions.DatabaseConnectionException;
import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAllOrdersCommand extends OrderListCommand
{
    
    public ShowAllOrdersCommand(String pageToShow, String role)
    {
        super(pageToShow, role);
    }
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException, DatabaseConnectionException
    {
        refreshList(request);
        return pageToShow;
    }
}
