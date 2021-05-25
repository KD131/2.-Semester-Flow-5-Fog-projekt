package business.persistence;

import business.entities.*;
import business.exceptions.DatabaseConnectionException;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    private Database database;

    public OrderMapper(Database database) {
        this.database = database;
    }

    public void insertOrder(Order order) throws UserException, DatabaseConnectionException
    {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO orders (User_id, Status, Carport_length, Carport_width, Shed_length, Shed_width) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, order.getUser().getId());
                ps.setString(2, "Bestilt");
                ps.setInt(3, order.getCarportLength());
                ps.setInt(4, order.getCarportWidth());
                ps.setInt(5, order.getShedLength());
                ps.setInt(6, order.getShedWidth());

                ps.executeUpdate();
                
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int orderId = ids.getInt(1);
                
                for (OrderLine ol : order.getBOM())
                {
                    insertOrderLine(orderId, ol);
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex)
        {
            throw new DatabaseConnectionException("Connection to database could not be established");
        }
    }

    public void setTotal(int OrderID, double total) throws UserException, DatabaseConnectionException
    {
        try (Connection connection = database.connect()) {
            String sql = "UPDATE orders SET total = ? WHERE Order_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setDouble(1, total);
                ps.setInt(2, OrderID);

                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseConnectionException("Connection to database could not be established");
        }
    }
    
    public void confirmOrder(int OrderID) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "UPDATE orders SET status = ? WHERE Order_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, "Godkendt");
                ps.setInt(2, OrderID);

                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
    }

    public void unconfirmOrder(int OrderID) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "UPDATE orders SET status = ? WHERE Order_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, "Bestilt");
                ps.setInt(2, OrderID);

                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }
    
    public void payOrder(int OrderID) throws UserException, DatabaseConnectionException
    {
        try (Connection connection = database.connect())
        {
            String sql = "UPDATE orders SET status = ? WHERE Order_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, "Betalt");
                ps.setInt(2, OrderID);
                
                ps.executeUpdate();
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseConnectionException("Connection to database could not be established");
        }
    }

    public void updateDimensions(int OrderID, int carportLength, int carportWidth, int shedLength, int shedWidth) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "UPDATE orders SET Carport_length = ?, Carport_width = ?, Shed_length = ?, Shed_width = ? Where Order_id =?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, carportLength);
                ps.setInt(2, carportWidth);
                ps.setInt(3, shedLength);
                ps.setInt(4, shedWidth);
                ps.setInt(5, OrderID);

                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }


    public void deleteOrder(int OrderID) throws UserException {
        try (Connection connection = database.connect()) {
             String sql = "DELETE FROM orders WHERE Order_id = ?";
             try (PreparedStatement ps = connection.prepareStatement(sql)) {
                 ps.setInt(1, OrderID);

                 ps.executeUpdate();
             }
             catch (SQLException ex) {
                 throw new UserException(ex.getMessage());
             }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    
    public List<OrderListing> getAllOrders() throws DatabaseConnectionException, UserException
    {
        try (Connection connection = database.connect())
        {
            String sql = "SELECT o.*, u.email FROM orders o JOIN users u USING (user_id)";
            
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                List<OrderListing> orderListings = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int orderId = rs.getInt("order_id");
                    String email = rs.getString("email");
                    double total = rs.getDouble("total");
                    double profitMargin = rs.getDouble("profit_margin");
                    String status = rs.getString("status");
                    Timestamp date = rs.getTimestamp("date");
                    int carportLength = rs.getInt("carport_length");
                    int carportWidth = rs.getInt("carport_width");
                    int shedLength = rs.getInt("shed_length");
                    int shedWidth = rs.getInt("shed_width");
                    
                    orderListings.add(new OrderListing(orderId, total, profitMargin, status, date, email, carportLength, carportWidth, shedLength, shedWidth));
                }
                return orderListings;
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseConnectionException("Connection to database could not be established");
        }
    }

    public List<OrderListing> getOrdersByUserId(int userId) throws DatabaseConnectionException, UserException
    {
        try (Connection connection = database.connect())
        {
            String sql = "SELECT o.*, u.email FROM orders o JOIN users u USING (user_id) WHERE user_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, userId);
                List<OrderListing> orderListings = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int orderId = rs.getInt("order_id");
                    String email = rs.getString("email");
                    double total = rs.getDouble("total");
                    double profitMargin = rs.getDouble("profit_margin");
                    String status = rs.getString("status");
                    Timestamp date = rs.getTimestamp("date");
                    int carportLength = rs.getInt("carport_length");
                    int carportWidth = rs.getInt("carport_width");
                    int shedLength = rs.getInt("shed_length");
                    int shedWidth = rs.getInt("shed_width");

                    orderListings.add(new OrderListing(orderId, total, profitMargin, status, date, email, carportLength, carportWidth, shedLength, shedWidth));
                }
                return orderListings;
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseConnectionException("Connection to database could not be established");
        }
    }
    
    public void insertOrderLine(int orderID, OrderLine ol) throws DatabaseConnectionException, UserException
    {
        try (Connection connection = database.connect())
        {
            String sql = "INSERT INTO orderline (order_id, material_id, quantity, description) VALUES (?, ?, ?, ?)";
        
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, orderID);
                ps.setInt(2, ol.getMaterial().getMaterialID());
                ps.setInt(3, ol.getQuantity());
                ps.setString(4, ol.getDescription());
            
                ps.executeUpdate();
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseConnectionException("Connection to database could not be established");
        }
    }
    
    public List<OrderLine> getOrderLinesByOrderId(int orderId) throws DatabaseConnectionException, UserException
    {
        try (Connection connection = database.connect())
        {
            String sql = "SELECT * FROM orderline WHERE order_id = ?";
            
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, orderId);
                
                List<OrderLine> BOM = new ArrayList<>();
                MaterialMapper materialMapper = new MaterialMapper(database);
                
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int materialId = rs.getInt("material_id");
                    Material material = materialMapper.getMaterialById(materialId);
                    int quantity = rs.getInt("quantity");
                    String description = rs.getString("description");
                    
                    BOM.add(new OrderLine(material, quantity, description));
                }
                return BOM;
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseConnectionException("Connection to database could not be established");
        }
    }
    
    public void updateBOM(int orderId, List<OrderLine> BOM) throws DatabaseConnectionException, UserException
    {
        try (Connection con = database.connect())
        {
            String sql = "DELETE FROM orderline WHERE order_id = ?";
            
            try (PreparedStatement ps = con.prepareStatement(sql))
            {
                ps.setInt(1, orderId);
                
                ps.executeUpdate();
                
                for (OrderLine ol : BOM)
                {
                    insertOrderLine(orderId, ol);
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseConnectionException("Connection to database could not be established");
        }
    }
}

