package business.entities;

public class Material {
    int materialID;
    String name;
    String unit;
    double buyPricePerUnit;
    double pricePerUnit;
    int length;
    int width;
    int height;
    String functionality;

    public Material(int materialID, String name, String unit, double buyPricePerUnit, double pricePerUnit, int length, int width, int height, String functionality) {
        this.materialID = materialID;
        this.name = name;
        this.unit = unit;
        this.buyPricePerUnit = buyPricePerUnit;
        this.pricePerUnit = pricePerUnit;
        this.length = length;
        this.width = width;
        this.height = height;
        this.functionality = functionality;
    }

    public int getMaterialID() {
        return materialID;
    }

    public void setMaterialID(int materialID) {
        this.materialID = materialID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getBuyPricePerUnit() {
        return buyPricePerUnit;
    }

    public void setBuyPricePerUnit(double buyPricePerUnit) {
        this.buyPricePerUnit = buyPricePerUnit;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFunctionality() {
        return functionality;
    }

    public void setFunctionality(String functionality) {
        this.functionality = functionality;
    }
}
