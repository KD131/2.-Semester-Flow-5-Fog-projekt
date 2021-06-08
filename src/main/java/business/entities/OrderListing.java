package business.entities;

import business.services.Formatter;

import java.sql.Timestamp;

public class OrderListing
{
    int orderId;
    String total;
    String profitMargin;
    String status;
    String email;
    int carportLength;
    int carportWidth;
    int shedLength;
    int shedWidth;
    String date;
    
    // total, profitmargin, and date are getting formatted to Strings.
    // you can keep a copy of the original if you'd like.
    // you might have to if you format numbers to comma decimal,
    // because you need to parse them back to double in Commands.
    
    public OrderListing(int orderId, double total, double profitMargin, String status,Timestamp date, String email, int carportLength, int carportWidth, int shedLength, int shedWidth)
    {
        this.orderId = orderId;
        this.total = Formatter.formatPrice(total);
        this.profitMargin = Formatter.formatPrice(profitMargin);
        this.status = status;
        this.date = Formatter.formatTimestamp(date);
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
    
    public String getTotal()
    {
        return total;
    }
    
    public String getProfitMargin()
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
    
    public String getDate()
    {
        return date;
    }
}
