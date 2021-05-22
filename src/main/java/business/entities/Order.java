package business.entities;

import business.exceptions.IllegalDimensionsException;

public class Order
{
    int orderID;
    double total;
    double profitMargin;
    String status;
    User user;
    int carportLength;
    int carportWidth;
    int shedLength;
    int shedWidth;
    
    public Order(User user, int carportLength, int carportWidth)
    {
        this.user = user;
        this.carportLength = carportLength;
        this.carportWidth = carportWidth;
    }
    
    public Order(User user, int carportLength, int carportWidth, int shedLength, int shedWidth)
    {
        this.user = user;
        this.carportLength = carportLength;
        this.carportWidth = carportWidth;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
    }
    
    public double getTotal()
    {
        return total;
    }
    
    public void setTotal(double total)
    {
        this.total = total;
    }
    
    public double getProfitMargin()
    {
        return profitMargin;
    }
    
    public void setProfitMargin(double profitMargin)
    {
        this.profitMargin = profitMargin;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public User getUser()
    {
        return user;
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

    public int getOrderID() { return orderID;}
    
    public static void validateShed(int carportWidth, int carportLength, int shedWidth, int shedLength) throws IllegalDimensionsException
    {
        if (shedLength > carportLength)
        {
            throw new IllegalDimensionsException("Skur kan ikke være længere en carport.");
        }
        int widthHang = 60;
        if (shedWidth > carportWidth - widthHang)
        {
            throw new IllegalDimensionsException("Skuret er for bredt. Den skal være mindst " + widthHang + " cm smallere end carporten.");
        }
    }

}
