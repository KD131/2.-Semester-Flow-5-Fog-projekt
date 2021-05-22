package business.services;

import business.entities.Material;
import business.entities.OrderLine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaterialsCalculator {

    private int carportLength;
    private int carportWidth;
    private int shedLength;
    private int shedWidth;
    private List<OrderLine> BOM = new ArrayList<>();
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
    
    public List<OrderLine> showBOM()
    {
        calcUnderstern(carportLength, carportWidth);
        return BOM; //bom = bill of materials
    }
//----------------------------------------Carport-----------------------------------------------------------------------
    public void calcUnderstern(int carportLength, int carportWidth) {
        List<Integer> lengthList = new ArrayList<>(); //list to contain all the various lengths from our materials list
        List<Integer> widthList = new ArrayList<>();

        for (int x = 0; x < allMaterials.size(); x++) {
            if(allMaterials.get(x).getFunctionality().contains("understern")){
                lengthList.add(allMaterials.get(x).getLength());
                widthList.add(allMaterials.get(x).getWidth());
            }
        }

        lengthList.sort(Comparator.naturalOrder()); //sorts through the list of material lengths and arranges them in the
                                                    //order of smallest -> largest value (this makes the next bit simpler)
        widthList.sort(Comparator.naturalOrder());

        boolean lengthFlag = true;
        boolean widthFlag = true;

        //---length-wise---//
        for (int x = 0; x < lengthList.size(); x++) {
            //this is what checks if the length of a material is long enough (<1 = yes / >1 = no)
            if(carportLength / lengthList.get(x) < 1 && lengthFlag){
                for (int i = 0; i < allMaterials.size(); i++) {
                    //compares the length that was long enough to the materials and adds the one that matches its length
                    if(allMaterials.get(i).getLength() == lengthList.get(x)){
                       BOM.add(new OrderLine(allMaterials.get(i), 1, "Understernbrædder til siderne"));
                    }
                }
                lengthFlag = false;
            }else{
                for (int i = 0; i < lengthList.size(); i++) {
                    //this is what checks if the length of a material is long enough (<1 = yes / >1 = no), with the addition
                    //of the longest piece from the list, which, because it is sorted, is always the last piece
                    if(carportLength / (lengthList.get(lengthList.size()-1) + lengthList.get(i)) < 1 && lengthFlag){
                        //compares the length that was long enough to the materials and adds the one that matches its length
                        //just does it twice: once for the longest piece and once for whichever piece added with that achieved
                        //the best length
                        for (int j = 0; j < allMaterials.size(); j++) {
                            //if the 2nd piece is as long as the first piece, then both are added in the same orderLine
                            if(lengthList.get(lengthList.size()-1).equals(lengthList.get(i))){
                                if(allMaterials.get(j).getLength() == lengthList.get(lengthList.size()-1)){
                                    BOM.add(new OrderLine(allMaterials.get(j), 2, "Understernbrædder til siderne"));
                                }
                            }else {
                                if (allMaterials.get(j).getLength() == lengthList.get(lengthList.size() - 1)) {
                                    BOM.add(new OrderLine(allMaterials.get(j), 1, "Understernbrædder til siderne"));
                                }
                                if (allMaterials.get(j).getLength() == lengthList.get(i)) {
                                    BOM.add(new OrderLine(allMaterials.get(j), 1, "Understernbrædder til siderne"));
                                }
                            }
                            return;
                        }
                        lengthFlag = false; //stops the loop from adding more pieces
                    }
                }
            }
        }
        //---width-wise---//
        for (int x = 0; x < widthList.size(); x++) {
            //this is what checks if the length of a material is long enough (<1 = yes / >1 = no)
            if(carportWidth / widthList.get(x) < 1){
                for (int i = 0; i < allMaterials.size(); i++) {
                    //compares the length that was long enough to the materials and adds the one that matches its length
                    if(allMaterials.get(i).getWidth() == widthList.get(x)){
                        BOM.add(new OrderLine(allMaterials.get(i), 1, "Understernbrædder til for & bag ende"));
                    }
                }
                widthFlag = false;
            }else{
                for (int i = 0; i < widthList.size(); i++) {
                    //this is what checks if the length of a material is long enough (<1 = yes / >1 = no), with the addition
                    //of the longest piece from the list, which, because it is sorted, is always the last piece
                    if(carportWidth / (widthList.get(widthList.size()-1) + widthList.get(x)) < 1 && widthFlag){
                        //compares the length that was long enough to the materials and adds the one that matches its length
                        //just does it twice: once for the longest piece and once for whichever piece added with that achieved
                        //the best length
                        for (int j = 0; j < allMaterials.size(); j++) {
                            //if the 2nd piece is as long as the first piece, then both are added in the same orderLine
                            if(widthList.get(widthList.size()-1).equals(widthList.get(i))){
                                if(allMaterials.get(j).getWidth() == widthList.get(widthList.size()-1)){
                                    BOM.add(new OrderLine(allMaterials.get(j), 2, "Understernbrædder til for & bag ende"));
                                }
                            }else {
                                if (allMaterials.get(j).getWidth() == widthList.get(widthList.size() - 1)) {
                                    BOM.add(new OrderLine(allMaterials.get(j), 1, "Understernbrædder til for & bag ende"));
                                }
                                if (allMaterials.get(j).getWidth() == widthList.get(i)) {
                                    BOM.add(new OrderLine(allMaterials.get(j), 1, "Understernbrædder til for & bag ende"));
                                }
                            }
                            return;
                        }
                        widthFlag = false; //stops the loop from adding more pieces
                    }
                }
            }
        }
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