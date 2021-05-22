package business.services;

import business.entities.Material;
import business.entities.OrderLine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class MaterialsCalculator {

    private double carportLength;
    private double carportWidth;
    private double shedLength;
    private double shedWidth;
    private List<OrderLine> BOM = new ArrayList<>();
    private List<Material> allMaterials;

    public MaterialsCalculator(List<Material> allMaterials, int carportLength, int carportWidth) {
        this.allMaterials = allMaterials;
        this.carportLength = carportLength*10;
        this.carportWidth = carportWidth*10;
    }

    public MaterialsCalculator(List<Material> allMaterials, int carportLength, int carportWidth, int shedLength, int shedWidth) {
        this.allMaterials = allMaterials;
        this.carportLength = carportLength*10;
        this.carportWidth = carportWidth*10;
        this.shedLength = shedLength*10;
        this.shedWidth = shedWidth*10;
    }
    
    public List<OrderLine> showBOM()
    {
        calcRemme(carportLength);
        calcUnderstern(carportLength, carportWidth);
        calcOverstern(carportLength, carportWidth);
        return BOM; //bom = bill of materials
    }
//----------------------------------------Carport-----------------------------------------------------------------------

    //Understern
    public void calcUnderstern(double carportLength, double carportWidth) {

//------!!!!!!!!!.getWidth() IS LITERAL WIDTH; YOU NEED TO USE .getLength() PLEASE FFS!!!!!!!!!!----------------------//

        List<Integer> lengthList = new ArrayList<>(); //list to contain all the various lengths from our materials list
        List<Integer> widthList = new ArrayList<>();
        List<Material> understernList = new ArrayList<>();

        for (int x = 0; x < allMaterials.size(); x++) {
            if (allMaterials.get(x).getFunctionality().toLowerCase().contains("understern")) {
                lengthList.add(allMaterials.get(x).getLength());
                widthList.add(allMaterials.get(x).getLength());
                understernList.add(allMaterials.get(x));
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
            if(carportLength / lengthList.get(x) <= 1.0 && lengthFlag){
                for (int i = 0; i < understernList.size(); i++) {
                    //compares the length that was long enough to the materials and adds the one that matches its length
                    if(understernList.get(i).getLength() == lengthList.get(x)){
                       BOM.add(new OrderLine(understernList.get(i), 2, "Understernbrædder til siderne"));
                    }
                }
                lengthFlag = false;
            }else{
                for (int i = 0; i < lengthList.size(); i++) {
                    //this is what checks if the length of a material is long enough (<1 = yes / >1 = no), with the addition
                    //of the longest piece from the list, which, because it is sorted, is always the last piece
                    if(carportLength / (lengthList.get(lengthList.size()-1) + lengthList.get(i)) <= 1.0 && lengthFlag){
                        //compares the length that was long enough to the materials and adds the one that matches its length
                        //just does it twice: once for the longest piece and once for whichever piece added with that achieved
                        //the best length
                        for (int j = 0; j < understernList.size(); j++) {
                            //if the 2nd piece is as long as the first piece, then both are added in the same orderLine
                            if(lengthList.get(lengthList.size()-1).equals(lengthList.get(i))){
                                if(understernList.get(j).getLength() == lengthList.get(lengthList.size()-1)){
                                    BOM.add(new OrderLine(understernList.get(j), 4, "Understernbrædder til siderne"));
                                }
                            }else {
                                if (understernList.get(j).getLength() == lengthList.get(lengthList.size() - 1)) {
                                    BOM.add(new OrderLine(understernList.get(j), 2, "Understernbrædder til siderne"));
                                }
                                if (understernList.get(j).getLength() == lengthList.get(i)) {
                                    BOM.add(new OrderLine(understernList.get(j), 2, "Understernbrædder til siderne"));
                                }
                            }
                        }
                        lengthFlag = false; //stops the loop from adding more pieces
                    }
                }
            }
        }
        //---width-wise---//
        for (int x = 0; x < widthList.size(); x++) {
            //this is what checks if the length of a material is long enough (<1 = yes / >1 = no)
            if(carportWidth / widthList.get(x) <= 1.0 && widthFlag){
                for (int i = 0; i < understernList.size(); i++) {
                    //compares the length that was long enough to the materials and adds the one that matches its length
                    if(understernList.get(i).getLength() == widthList.get(x)){
                        BOM.add(new OrderLine(understernList.get(i), 2, "Understernbrædder til for & bag ende"));
                    }
                }
                widthFlag = false;
            }else{
                for (int i = 0; i < widthList.size(); i++) {
                    //this is what checks if the length of a material is long enough (<1 = yes / >1 = no), with the addition
                    //of the longest piece from the list, which, because it is sorted, is always the last piece
                    if(carportWidth / (widthList.get(widthList.size()-1) + widthList.get(i)) <= 1.0 && widthFlag){
                        //compares the length that was long enough to the materials and adds the one that matches its length
                        //just does it twice: once for the longest piece and once for whichever piece added with that achieved
                        //the best length
                        for (int j = 0; j < understernList.size(); j++) {
                            //if the 2nd piece is as long as the first piece, then both are added in the same orderLine
                            if(widthList.get(widthList.size()-1).equals(widthList.get(i))){
                                if(understernList.get(j).getLength() == widthList.get(widthList.size()-1)){
                                    BOM.add(new OrderLine(understernList.get(j), 4, "Understernbrædder til for & bag ende"));
                                }
                            }else {
                                if (understernList.get(j).getLength() == widthList.get(widthList.size() - 1)) {
                                    BOM.add(new OrderLine(understernList.get(j), 2, "Understernbrædder til for & bag ende"));
                                }
                                if (understernList.get(j).getLength() == widthList.get(i)) {
                                    BOM.add(new OrderLine(understernList.get(j), 2, "Understernbrædder til for & bag ende"));
                                }
                            }
                        }
                        widthFlag = false; //stops the loop from adding more pieces
                    }
                }
            }
        }
    }

    //Overstern
    public void calcOverstern(double carportLength, double carportWidth) {
        List<Integer> lengthList = new ArrayList<>();
        List<Integer> widthList = new ArrayList<>();
        List<Material> oversternList = new ArrayList<>();

        for (int x = 0; x < allMaterials.size(); x++) {
            if (allMaterials.get(x).getFunctionality().toLowerCase().contains("overstern")) {
                lengthList.add(allMaterials.get(x).getLength());
                widthList.add(allMaterials.get(x).getLength());
                oversternList.add(allMaterials.get(x));
            }
        }

        lengthList.sort(Comparator.naturalOrder());
        widthList.sort(Comparator.naturalOrder());

        boolean lengthFlag = true;
        boolean widthFlag = true;

        //---length-wise---//
        for (int x = 0; x < lengthList.size(); x++) {
            //this is what checks if the length of a material is long enough (<1 = yes / >1 = no)
            if(carportLength / lengthList.get(x) <= 1.0 && lengthFlag){
                for (int i = 0; i < oversternList.size(); i++) {
                    //compares the length that was long enough to the materials and adds the one that matches its length
                    if(oversternList.get(i).getLength() == lengthList.get(x)){
                        BOM.add(new OrderLine(oversternList.get(i), 2, "Oversternbrædder til siderne"));
                    }
                }
                lengthFlag = false;
            }else{
                for (int i = 0; i < lengthList.size(); i++) {
                    //this is what checks if the length of a material is long enough (<1 = yes / >1 = no), with the addition
                    //of the longest piece from the list, which, because it is sorted, is always the last piece
                    if(carportLength / (lengthList.get(lengthList.size()-1) + lengthList.get(i)) <= 1.0 && lengthFlag){
                        //compares the length that was long enough to the materials and adds the one that matches its length
                        //just does it twice: once for the longest piece and once for whichever piece added with that achieved
                        //the best length
                        for (int j = 0; j < oversternList.size(); j++) {
                            //if the 2nd piece is as long as the first piece, then both are added in the same orderLine
                            if(lengthList.get(lengthList.size()-1).equals(lengthList.get(i))){
                                if(oversternList.get(j).getLength() == lengthList.get(lengthList.size()-1)){
                                    BOM.add(new OrderLine(oversternList.get(j), 4, "Oversternbrædder til siderne"));
                                }
                            }else {
                                if (oversternList.get(j).getLength() == lengthList.get(lengthList.size() - 1)) {
                                    BOM.add(new OrderLine(oversternList.get(j), 2, "Oversternbrædder til siderne"));
                                }
                                if (oversternList.get(j).getLength() == lengthList.get(i)) {
                                    BOM.add(new OrderLine(oversternList.get(j), 2, "Oversternbrædder til siderne"));
                                }
                            }
                        }
                        lengthFlag = false; //stops the loop from adding more pieces
                    }
                }
            }
        }
        //---width-wise---// (Overstern forende skal kun have én side, altså forenden, så den er ikke fordoblet)
        for (int x = 0; x < widthList.size(); x++) {
            //this is what checks if the length of a material is long enough (<1 = yes / >1 = no)
            if(carportWidth / widthList.get(x) <= 1.0 && widthFlag){
                for (int i = 0; i < oversternList.size(); i++) {
                    //compares the length that was long enough to the materials and adds the one that matches its length
                    if(oversternList.get(i).getLength() == widthList.get(x)){
                        BOM.add(new OrderLine(oversternList.get(i), 1, "Oversternbrædder til forenden"));
                    }
                }
                widthFlag = false;
            }else{
                for (int i = 0; i < widthList.size(); i++) {
                    //this is what checks if the length of a material is long enough (<1 = yes / >1 = no), with the addition
                    //of the longest piece from the list, which, because it is sorted, is always the last piece
                    if(carportWidth / (widthList.get(widthList.size()-1) + widthList.get(i)) <= 1.0 && widthFlag){
                        //compares the length that was long enough to the materials and adds the one that matches its length
                        //just does it twice: once for the longest piece and once for whichever piece added with that achieved
                        //the best length
                        for (int j = 0; j < oversternList.size(); j++) {
                            //if the 2nd piece is as long as the first piece, then both are added in the same orderLine
                            if(widthList.get(widthList.size()-1).equals(widthList.get(i))){
                                if(oversternList.get(j).getLength() == widthList.get(widthList.size()-1)){
                                    BOM.add(new OrderLine(oversternList.get(j), 2, "Oversternbrædder til forenden"));
                                }
                            }else {
                                if (oversternList.get(j).getLength() == widthList.get(widthList.size() - 1)) {
                                    BOM.add(new OrderLine(oversternList.get(j), 1, "Oversternbrædder til forenden"));
                                }
                                if (oversternList.get(j).getLength() == widthList.get(i)) {
                                    BOM.add(new OrderLine(oversternList.get(j), 1, "Oversternbrædder til forenden"));
                                }
                            }
                        }
                        widthFlag = false; //stops the loop from adding more pieces
                    }
                }
            }
        }
    }

    //Remme til siderne
    public void calcRemme(double carportLength) {
        List<Integer> lengthList = new ArrayList<>();
        List<Material> remmeList = new ArrayList<>();

        for (int x = 0; x < allMaterials.size(); x++) {
            if (allMaterials.get(x).getFunctionality().toLowerCase().contains("rem")) {
                lengthList.add(allMaterials.get(x).getLength());
                remmeList.add(allMaterials.get(x));
            }
        }

        lengthList.sort(Comparator.naturalOrder());

        boolean lengthFlag = true;

        for (int x = 0; x < lengthList.size(); x++) {
            //this is what checks if the length of a material is long enough (<1 = yes / >1 = no)
            if (carportLength / lengthList.get(x) <= 1.0 && lengthFlag) {
                for (int i = 0; i < remmeList.size(); i++) {
                    //compares the length that was long enough to the materials and adds the one that matches its length
                    if (remmeList.get(i).getLength() == lengthList.get(x)) {
                        BOM.add(new OrderLine(remmeList.get(i), 2, "Remme til siderne"));
                    }
                }
                lengthFlag = false;
            } else {
                for (int i = 0; i < lengthList.size(); i++) {
                    //this is what checks if the length of a material is long enough (<1 = yes / >1 = no), with the addition
                    //of the longest piece from the list, which, because it is sorted, is always the last piece
                    if (carportLength / (lengthList.get(lengthList.size() - 1) + lengthList.get(i)) <= 1.0 && lengthFlag) {
                        //compares the length that was long enough to the materials and adds the one that matches its length
                        //just does it twice: once for the longest piece and once for whichever piece added with that achieved
                        //the best length
                        for (int j = 0; j < remmeList.size(); j++) {
                            //if the 2nd piece is as long as the first piece, then both are added in the same orderLine
                            if (lengthList.get(lengthList.size() - 1).equals(lengthList.get(i))) {
                                if (remmeList.get(j).getLength() == lengthList.get(lengthList.size() - 1)) {
                                    BOM.add(new OrderLine(remmeList.get(j), 4, "Remme til siderne"));
                                }
                            } else {
                                if (remmeList.get(j).getLength() == lengthList.get(lengthList.size() - 1)) {
                                    BOM.add(new OrderLine(remmeList.get(j), 2, "Remme til siderne"));
                                }
                                if (remmeList.get(j).getLength() == lengthList.get(i)) {
                                    BOM.add(new OrderLine(remmeList.get(j), 2, "Remme til siderne"));
                                }
                            }
                        }
                        lengthFlag = false; //stops the loop from adding more pieces
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