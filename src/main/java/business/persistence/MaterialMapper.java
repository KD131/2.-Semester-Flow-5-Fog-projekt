package business.persistence;

import business.entities.Material;
import business.entities.OrderListing;
import business.exceptions.DatabaseConnectionException;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialMapper {
    private Database database;

    public MaterialMapper(Database database) {
        this.database = database;
    }

    public List<Material> getAllMaterials() throws DatabaseConnectionException, UserException
    {
        try (Connection connection = database.connect())
        {
            String sql = "SELECT * FROM carport.materials m LEFT JOIN material_functionalities mf USING (material_id) ORDER BY material_id";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                List<Material> materialsList = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int materialID = rs.getInt("material_id");
                    String name = rs.getString("name");
                    String unit = rs.getString("unit");
                    double buyPricePerUnit = rs.getDouble("buy_price_per_unit");
                    double pricePerUnit = rs.getDouble("price_per_unit");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    String functionality = rs.getString("functionality");

                    materialsList.add(new Material(materialID,name,unit,buyPricePerUnit,pricePerUnit,length,width,height,functionality));
                }
                return materialsList;
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
    
    public Material getMaterialById(int materialId) throws DatabaseConnectionException, UserException
    {
        try (Connection con = database.connect())
        {
            String sql = "SELECT * FROM materials m LEFT JOIN material_functionalities mf USING (material_id) WHERE material_id = ?";
            
            try (PreparedStatement ps = con.prepareStatement(sql))
            {
                ps.setInt(1, materialId);
                
                ResultSet rs = ps.executeQuery();
                
                if (rs.next())
                {
                    int materialID = rs.getInt("material_id");
                    String name = rs.getString("name");
                    String unit = rs.getString("unit");
                    double buyPricePerUnit = rs.getDouble("buy_price_per_unit");
                    double pricePerUnit = rs.getDouble("price_per_unit");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    String functionality = rs.getString("functionality");
                    
                    return new Material(materialID, name, unit, buyPricePerUnit, pricePerUnit, length, width, height, functionality);
                }
                else
                {
                    throw new UserException("Material could not be found");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseConnectionException("Connection to database could not be established");
        }
    }
}
