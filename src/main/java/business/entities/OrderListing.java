package business.entities;

import java.sql.Timestamp;

public class OrderListing
{
    int orderId;
    double total;
    double profitMargin;
    String status;
    String email;
    int carportLength;
    int carportWidth;
    int shedLength;
    int shedWidth;
    Timestamp date;
    
    public OrderListing(int orderId, double total, double profitMargin, String status,Timestamp date, String email, int carportLength, int carportWidth, int shedLength, int shedWidth)
    {
        this.orderId = orderId;
        this.total = total;
        this.profitMargin = profitMargin;
        this.status = status;
        this.date = date;
        this.email = email;
        this.carportLength = carportLength;
        this.carportWidth = carportWidth;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
    }
    
    public int getOrderId()
    {
        return orderId;
    }
    
    public double getTotal()
    {
        return total;
    }
    
    public double getProfitMargin()
    {
        return profitMargin;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public int getCarportLength()
    {
        return carportLength;
    }
    
    public int getCarportWidth()
    {
        return carportWidth;
    }
    
    public int getShedLength()
    {
        return shedLength;
    }
    
    public int getShedWidth()
    {
        return shedWidth;
    }
    
    public Timestamp getDate()
    {
        return date;
    }
}
