package business.services;

import business.entities.Order;
import business.entities.OrderListing;
import business.exceptions.DatabaseConnectionException;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;

import java.util.List;

public class OrderFacade
{
    private OrderMapper orderMapper;
    
    public OrderFacade(Database database)
    {
        this.orderMapper = new OrderMapper(database);
    }
    
    public void insertOrder(Order order) throws UserException
    {
        orderMapper.insertOrder(order);
    }
    
    public List<OrderListing> getAllOrders() throws DatabaseConnectionException, UserException
    {
        return orderMapper.getAllOrders();
    }
}
