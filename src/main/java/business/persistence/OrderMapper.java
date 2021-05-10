package business.persistence;

import business.entities.Order;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}