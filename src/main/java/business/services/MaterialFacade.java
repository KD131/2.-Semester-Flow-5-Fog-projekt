package business.services;

import business.entities.Material;
import business.exceptions.DatabaseConnectionException;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.MaterialMapper;

import java.util.List;

public class MaterialFacade {
    private MaterialMapper materialMapper;

    public MaterialFacade(Database database) {
        this.materialMapper = new MaterialMapper(database);
    }

    public List<Material> getAllMaterials() throws DatabaseConnectionException, UserException {
        return materialMapper.getAllMaterials();
    }
    
    public Material getMaterialById(int materialId) throws DatabaseConnectionException, UserException
    {
        return materialMapper.getMaterialById(materialId);
    }
    
    public void insertMaterial(Material material) throws DatabaseConnectionException, UserException
    {
        materialMapper.insertMaterial(material);
    }
    
    public void updateMaterial(Material material) throws DatabaseConnectionException, UserException
    {
        materialMapper.updateMaterial(material);
    }
    
    public int deleteMaterialById(int materialId) throws DatabaseConnectionException, UserException
    {
        return materialMapper.deleteMaterialById(materialId);
    }
    
    public List<String> getFunctionalities() throws DatabaseConnectionException, UserException
    {
        return materialMapper.getFunctionalities();
    }
    
    public List<String> getUnits() throws DatabaseConnectionException, UserException
    {
        return materialMapper.getUnits();
    }
}
