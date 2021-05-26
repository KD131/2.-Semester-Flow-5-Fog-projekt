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
    
    public void insertMaterial(Material material) throws DatabaseConnectionException, UserException
    {
        try (Connection con = database.connect())
        {
            String sql = "INSERT INTO materials (name, unit, buy_price_per_unit, price_per_unit, length, width, height) VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setString(1, material.getName());
                ps.setString(2, material.getUnit());
                ps.setDouble(3, material.getBuyPricePerUnit());
                ps.setDouble(4, material.getPricePerUnit());
                ps.setInt(5, material.getLength());
                ps.setInt(6, material.getWidth());
                ps.setInt(7, material.getHeight());
                
                ps.executeUpdate();
                
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int materialId = ids.getInt(1);
                
                insertMaterialFunctionality(materialId, material.getFunctionality());
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
    
    public void insertMaterialFunctionality(int materialId, String functionality) throws DatabaseConnectionException, UserException
    {
        try (Connection con = database.connect())
        {
            String sql = "INSERT INTO material_functionalities (material_id, functionality) VALUES (?, ?)";
        
            try (PreparedStatement ps = con.prepareStatement(sql))
            {
                ps.setInt(1, materialId);
                ps.setString(2, functionality);
            
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
    
    public void updateMaterial(Material material) throws DatabaseConnectionException, UserException
    {
        try (Connection con = database.connect())
        {
            String sql = "UPDATE materials SET name = ?, unit = ?, buy_price_per_unit = ?, price_per_unit = ?, length = ?, width = ?, height = ?" +
                    " WHERE material_id = ?";
            
            try (PreparedStatement ps = con.prepareStatement(sql))
            {
                ps.setString(1, material.getName());
                ps.setString(2, material.getUnit());
                ps.setDouble(3, material.getBuyPricePerUnit());
                ps.setDouble(4, material.getPricePerUnit());
                ps.setInt(5, material.getLength());
                ps.setInt(6, material.getWidth());
                ps.setInt(7, material.getHeight());
                ps.setInt(8, material.getMaterialID());
                
                ps.executeUpdate();
                
                updateMaterialFunctionality(material.getMaterialID(), material.getFunctionality());
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
    
    // This deletes the existing functionalities and inserts the new one.
    // THIS RESTRICTS IT TO ONE FUNCTIONALITY BUT IT'S THE BEST I CAN DO RIGHT NOW.
    // In general, a Material should have a LIST of functionalities, but we don't support that right now.
    // There's only one material that I'm aware of that has two functionalities, that being the Spærtræ.
    public void updateMaterialFunctionality(int materialId, String functionality) throws DatabaseConnectionException, UserException
    {
        try (Connection con = database.connect())
        {
            String sql = "DELETE FROM material_functionalities WHERE material_id = ?";
            
            try (PreparedStatement ps = con.prepareStatement(sql))
            {
                ps.setInt(1, materialId);
                
                ps.executeUpdate();
                
                insertMaterialFunctionality(materialId, functionality);
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
    
    public int deleteMaterialById(int materialId) throws DatabaseConnectionException, UserException
    {
        try (Connection con = database.connect())
        {
            // Can also just rely on the database foreign key constraints to throw an SQLException
            String sql = "DELETE FROM materials " +
                    "WHERE material_id = ? AND material_id NOT IN(" +
                    "SELECT material_id FROM orderline)";
        
            try (PreparedStatement ps = con.prepareStatement(sql))
            {
                ps.setInt(1, materialId);
                int affectedRows = ps.executeUpdate();
                return affectedRows;
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
    
    public List<String> getFunctionalities() throws DatabaseConnectionException, UserException
    {
        try (Connection connection = database.connect())
        {
            String sql = "SELECT functionality FROM functionalities";
        
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                List<String> functionalities = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    String functionality = rs.getString("functionality");
                    functionalities.add(functionality);
                }
                return functionalities;
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
    
    public List<String> getUnits() throws DatabaseConnectionException, UserException
    {
        try (Connection connection = database.connect())
        {
            String sql = "SELECT unit FROM unit";
        
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                List<String> units = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    String unit = rs.getString("unit");
                    units.add(unit);
                }
                return units;
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
