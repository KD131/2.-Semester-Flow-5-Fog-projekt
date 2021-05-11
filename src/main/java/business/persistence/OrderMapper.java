package business.persistence;

import business.entities.Order;
import business.entities.OrderListing;
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

    public void insertOrder(Order order) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO orders (User_id, Status, Carport_length, Carport_width, Shed_length, Shed_width) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, order.getUser().getId());
                ps.setString(2, "Bestilt");
                ps.setInt(3, order.getCarportLength());
                ps.setInt(4, order.getCarportWidth());
                ps.setInt(5, order.getShedLength());
                ps.setInt(6, order.getShedWidth());

                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
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
}
