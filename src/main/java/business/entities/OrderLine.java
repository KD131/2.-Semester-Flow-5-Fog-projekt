package business.entities;

public class OrderLine {
    private Material material;
    private int quantity;
    private String description;

    public OrderLine(Material material, int quantity, String description) {
        this.material = material;
        this.quantity = quantity;
        this.description = description;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getSellTotal()
    {
        return material.getPricePerUnit() * quantity;
    }
}
