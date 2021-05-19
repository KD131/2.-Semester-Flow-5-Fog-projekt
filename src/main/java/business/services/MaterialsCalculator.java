package business.services;

import business.entities.Material;
import business.entities.OrderLine;
import business.persistence.MaterialMapper;

import java.util.ArrayList;
import java.util.List;

public class MaterialsCalculator {

    private int carportLength;
    private int carportWidth;
    private int shedLength;
    private int shedWidth;
    private List<OrderLine> orderLList = new ArrayList<>();
    private List<Material> allMaterials;

    public MaterialsCalculator(List<Material> allMaterials, int carportLength, int carportWidth) {
        this.allMaterials = allMaterials;
        this.carportLength = carportLength;
        this.carportWidth = carportWidth;
    }

    public MaterialsCalculator(List<Material> allMaterials, int carportLength, int carportWidth, int shedLength, int shedWidth) {
        this.allMaterials = allMaterials;
        this.carportLength = carportLength;
        this.carportWidth = carportWidth;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
    }
//----------------------------------------Carport-----------------------------------------------------------------------
    public void calcUnderstern(List<OrderLine> materialList, int carportLength, int carportWidth) {

        materialList.add(new OrderLine(allMaterials.get(1), 0, "Understernbrædder til for & bag ende"));
        materialList.add(new OrderLine(allMaterials.get(2), 0, "Understernbrædder til for & bag ende"));
        materialList.add(new OrderLine(allMaterials.get(1), 0, "Understernbrædder til siderne"));
        materialList.add(new OrderLine(allMaterials.get(2), 0, "Understernbrædder til siderne"));
    }

    /*
    Liste af koder til funktioner (til carport):
    - understern
            kunden har valgt en længde på 4,5 m
            find en f1 der er passende m4
            m1 + m5
    - overstern
    - rem
    - spær
    - stolpe
    - vandbrædt
    - tagplade
     */

/* Databasen

   materiale table
   m1 bla bla  #f1
   m2 blo blo  f2
   m3 ble ble  f3
   m4 bla bla   #f1  4,5m
 */

    /*
    PSEUDO FOR CALC IDEA

    public ??? calc(int desired, String name)
    {
        int desiredLength;
        List<Integer> combinedLengths;

        loop
        {
            int = combines material lengths;
            if (combined > desired)
            {
                list.add(int);
            }
        }

        Math.min(combinedLengths % desiredLength)
    }
    */

//----------------------------------------Shed--------------------------------------------------------------------------

}
