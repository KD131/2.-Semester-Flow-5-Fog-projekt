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


    public void confirmOrder(int OrderID) throws UserException
    {
        orderMapper.confirmOrder(OrderID);
    }

    public void unconfirmOrder(int OrderID) throws UserException
    {
        orderMapper.unconfirmOrder(OrderID);
    }


    public void deleteOrder(int OrderID) throws UserException
    {
        orderMapper.deleteOrder(OrderID);
    }


    
    public List<OrderListing> getAllOrders() throws DatabaseConnectionException, UserException
    {
        return orderMapper.getAllOrders();
    }

}