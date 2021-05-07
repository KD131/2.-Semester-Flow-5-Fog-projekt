package business.services;

import business.entities.Order;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;

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
}
