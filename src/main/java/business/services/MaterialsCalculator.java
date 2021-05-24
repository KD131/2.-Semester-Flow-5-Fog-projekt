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
        //carport
        calcUnderstern(carportLength, carportWidth);
        calcOverstern(carportLength, carportWidth);
        calcRemme(carportLength);
        calcSpær(carportLength,carportWidth);
        calcStolper(shedLength,shedWidth);
        calcVandbrædt(carportLength,carportWidth);
        calcTagplader(carportLength,carportWidth);
        //shed

        //misc (like screws and stuff)
        calcSkruer(carportLength,carportWidth);
        return BOM; //bom = bill of materials
    }
//----------------------------------------Carport-----------------------------------------------------------------------

//--Understern
    public void calcUnderstern(double carportLength, double carportWidth) {

//------!!!!!!!!!.getWidth() IS LITERAL WIDTH; YOU NEED TO USE .getLength()!!!!!!!!!!---------------------------------//

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
            if (carportLength / lengthList.get(x) <= 1.0 && lengthFlag) {
                for (int i = 0; i < understernList.size(); i++) {
                    //compares the length that was long enough to the materials and adds the one that matches its length
                    if (understernList.get(i).getLength() == lengthList.get(x)) {
                        BOM.add(new OrderLine(understernList.get(i), 2, "Understernbrædder til siderne"));
                    }
                }
                lengthFlag = false;
            }
        }
        for (int x = 0; x < widthList.size(); x++) {
            if (carportLength / (lengthList.get(x) * 2) <= 1.0 && lengthFlag) {
                for (int i = 0; i < understernList.size(); i++) {
                    if (understernList.get(i).getLength() == lengthList.get(x)) {
                        BOM.add(new OrderLine(understernList.get(i), 4, "Understernbrædder til siderne"));
                    }
                }
                lengthFlag = false;
            }
            else if(lengthFlag){
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
            if (carportWidth / widthList.get(x) <= 1.0 && widthFlag) {
                for (int i = 0; i < understernList.size(); i++) {
                    //compares the length that was long enough to the materials and adds the one that matches its length
                    if (understernList.get(i).getLength() == widthList.get(x)) {
                        BOM.add(new OrderLine(understernList.get(i), 2, "Understernbrædder til for & bag ende"));
                    }
                }
                widthFlag = false;
            }
        }
        for (int x = 0; x < widthList.size(); x++) {
            if (carportWidth / (widthList.get(x) * 2) <= 1.0 && widthFlag) {
                for (int i = 0; i < understernList.size(); i++) {
                    if (understernList.get(i).getLength() == widthList.get(x)) {
                        BOM.add(new OrderLine(understernList.get(i), 4, "Understernbrædder til for & bag ende"));
                    }
                }
                widthFlag = false;
            }
            else if(widthFlag){
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

//--Overstern
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
            if (carportLength / lengthList.get(x) <= 1.0 && lengthFlag) {
                for (int i = 0; i < oversternList.size(); i++) {
                    if (oversternList.get(i).getLength() == lengthList.get(x)) {
                        BOM.add(new OrderLine(oversternList.get(i), 2, "Oversternbrædder til siderne"));
                    }
                }
                lengthFlag = false;
            }
        }
        for (int x = 0; x < widthList.size(); x++) {
            if (carportLength / (lengthList.get(x) * 2) <= 1.0 && lengthFlag) {
                for (int i = 0; i < oversternList.size(); i++) {
                    if (oversternList.get(i).getLength() == lengthList.get(x)) {
                        BOM.add(new OrderLine(oversternList.get(i), 4, "Oversternbrædder til siderne"));
                    }
                }
                lengthFlag = false;
            }
            else if(lengthFlag){
                for (int i = 0; i < lengthList.size(); i++) {
                    if(carportLength / (lengthList.get(lengthList.size()-1) + lengthList.get(i)) <= 1.0 && lengthFlag){
                        for (int j = 0; j < oversternList.size(); j++) {
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
                        lengthFlag = false;
                    }
                }
            }
        }
        //---width-wise---// (Overstern forende skal kun have én side, altså forenden, så den er ikke fordoblet)
        for (int x = 0; x < widthList.size(); x++) {
            if (carportWidth / widthList.get(x) <= 1.0 && widthFlag) {
                for (int i = 0; i < oversternList.size(); i++) {
                    if (oversternList.get(i).getLength() == widthList.get(x)) {
                        BOM.add(new OrderLine(oversternList.get(i), 1, "Oversternbrædder til forenden"));
                    }
                }
                widthFlag = false;
            }
        }
        for (int x = 0; x < widthList.size(); x++) {
            if (carportWidth / (widthList.get(x) * 2) <= 1.0 && widthFlag) {
                for (int i = 0; i < oversternList.size(); i++) {
                    if (oversternList.get(i).getLength() == widthList.get(x)) {
                        BOM.add(new OrderLine(oversternList.get(i), 2, "Oversternbrædder til forenden"));
                    }
                }
                widthFlag = false;
            }
            else if(widthFlag){
                for (int i = 0; i < widthList.size(); i++) {
                    if(carportWidth / (widthList.get(widthList.size()-1) + widthList.get(i)) <= 1.0 && widthFlag){
                        for (int j = 0; j < oversternList.size(); j++) {
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
                        widthFlag = false;
                    }
                }
            }
        }
    }

//--Remme til siderne
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
            if (carportLength / lengthList.get(x) <= 1.0 && lengthFlag) {
                for (int i = 0; i < remmeList.size(); i++) {
                    if (lengthList.get(x).equals(remmeList.get(i).getLength())) {
                        BOM.add(new OrderLine(remmeList.get(i), 2, "Remme til siderne"));
                    }
                }
                lengthFlag = false;
            }
        }
        for (int x = 0; x < lengthList.size(); x++) {
            if (carportLength / (lengthList.get(x) * 2) <= 1.0 && lengthFlag) {
                for (int i = 0; i < remmeList.size(); i++) {
                    if (remmeList.get(i).getLength() == lengthList.get(x)) {
                        BOM.add(new OrderLine(remmeList.get(i), 4, "Remme til siderne"));
                    }
                }
                lengthFlag = false;
            }
            else if(lengthFlag){
                for (int i = 0; i < lengthList.size(); i++) {
                    if (carportLength / (lengthList.get(lengthList.size() - 1) + lengthList.get(i)) <= 1.0 && lengthFlag) {
                        for (int j = 0; j < remmeList.size(); j++) {
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
                        lengthFlag = false;
                    }
                }
            }
        }
    }

    public void calcSpær(double carportLength, double carportWidth) {
        List<Integer> widthList = new ArrayList<>();
        List<Material> spærList = new ArrayList<>();

        //takes the rounded-up result of carport length divided by 55cm and returns it as an int used to determine how
        //many spær are required
        //NOTE: spær cannot be combined, so 6m (aka 6000mm) is the utmost length possible, hence why this section is shorter
        //than the similar code above
        int spærNumber = (int) Math.ceil(carportLength / 550); //all our numbers are mm (very important!)

        for (int x = 0; x < allMaterials.size(); x++) {
            if (allMaterials.get(x).getFunctionality().toLowerCase().contains("spær")) {
                widthList.add(allMaterials.get(x).getLength());
                spærList.add(allMaterials.get(x));
            }
        }

        widthList.sort(Comparator.naturalOrder());

        boolean widthFlag = true;

        //---width-wise---//
        for (int x = 0; x < widthList.size(); x++) {
            if (carportWidth / widthList.get(x) <= 1.0 && widthFlag) {
                for (int i = 0; i < spærList.size(); i++) {
                    if (spærList.get(i).getLength() == widthList.get(x)) {
                        BOM.add(new OrderLine(spærList.get(i), spærNumber, "Spær, monteres på rem"));
                    }
                }
                widthFlag = false;
            }
            else if(widthFlag){ //if the width is bigger than the possible lengths, it automatically picks the longest piece anyway
                for (int i = 0; i < spærList.size(); i++) {
                    if (spærList.get(i).getLength() == widthList.get(widthList.size()-1)) {
                        BOM.add(new OrderLine(spærList.get(i), spærNumber, "Spær, monteres på rem"));
                    }
                }
                widthFlag = false;
            }
        }
    }

    public void calcStolper(double shedLength, double shedWidth) {
        List<Material> stolpeList = new ArrayList<>();

        for (int x = 0; x < allMaterials.size(); x++) {
            if (allMaterials.get(x).getFunctionality().toLowerCase().contains("stolpe")) {
                stolpeList.add(allMaterials.get(x));
            }
        }

        if(shedLength == 0 || shedWidth == 0){
            BOM.add(new OrderLine(stolpeList.get(0), 6, "Stolper nedgraves 90 cm. i jord"));
        }
        else{
            BOM.add(new OrderLine(stolpeList.get(0), 10, "Stolper nedgraves 90 cm. i jord"));
        }
    }

//--Vandbrædt (same as Overstern)
    public void calcVandbrædt(double carportLength, double carportWidth) {
        List<Integer> lengthList = new ArrayList<>();
        List<Integer> widthList = new ArrayList<>();
        List<Material> vandbrædtList = new ArrayList<>();

        for (int x = 0; x < allMaterials.size(); x++) {
            if (allMaterials.get(x).getFunctionality().toLowerCase().contains("vandbrædt")) {
                lengthList.add(allMaterials.get(x).getLength());
                widthList.add(allMaterials.get(x).getLength());
                vandbrædtList.add(allMaterials.get(x));
            }
        }

        lengthList.sort(Comparator.naturalOrder());
        widthList.sort(Comparator.naturalOrder());

        boolean lengthFlag = true;
        boolean widthFlag = true;

        //---length-wise---//
        for (int x = 0; x < lengthList.size(); x++) {
            if (carportLength / lengthList.get(x) <= 1.0 && lengthFlag) {
                for (int i = 0; i < vandbrædtList.size(); i++) {
                    if (vandbrædtList.get(i).getLength() == lengthList.get(x)) {
                        BOM.add(new OrderLine(vandbrædtList.get(i), 2, "Vandbrædt på stern i sider"));
                    }
                }
                lengthFlag = false;
            }
        }
        for (int x = 0; x < widthList.size(); x++) {
            if (carportLength / (lengthList.get(x) * 2) <= 1.0 && lengthFlag) {
                for (int i = 0; i < vandbrædtList.size(); i++) {
                    if (vandbrædtList.get(i).getLength() == lengthList.get(x)) {
                        BOM.add(new OrderLine(vandbrædtList.get(i), 4, "Vandbrædt på stern i sider"));
                    }
                }
                lengthFlag = false;
            }
            else if(lengthFlag){
                for (int i = 0; i < lengthList.size(); i++) {
                    if(carportLength / (lengthList.get(lengthList.size()-1) + lengthList.get(i)) <= 1.0 && lengthFlag){
                        for (int j = 0; j < vandbrædtList.size(); j++) {
                            if(lengthList.get(lengthList.size()-1).equals(lengthList.get(i))){
                                if(vandbrædtList.get(j).getLength() == lengthList.get(lengthList.size()-1)){
                                    BOM.add(new OrderLine(vandbrædtList.get(j), 4, "Vandbrædt på stern i sider"));
                                }
                            }else {
                                if (vandbrædtList.get(j).getLength() == lengthList.get(lengthList.size() - 1)) {
                                    BOM.add(new OrderLine(vandbrædtList.get(j), 2, "Vandbrædt på stern i sider"));
                                }
                                if (vandbrædtList.get(j).getLength() == lengthList.get(i)) {
                                    BOM.add(new OrderLine(vandbrædtList.get(j), 2, "Vandbrædt på stern i sider"));
                                }
                            }
                        }
                        lengthFlag = false;
                    }
                }
            }
        }
        //---width-wise---//
        for (int x = 0; x < widthList.size(); x++) {
            if (carportWidth / widthList.get(x) <= 1.0 && widthFlag) {
                for (int i = 0; i < vandbrædtList.size(); i++) {
                    if (vandbrædtList.get(i).getLength() == widthList.get(x)) {
                        BOM.add(new OrderLine(vandbrædtList.get(i), 1, "Vandbrædt på stern i forende"));
                    }
                }
                widthFlag = false;
            }
        }
        for (int x = 0; x < widthList.size(); x++) {
            if (carportWidth / (widthList.get(x) * 2) <= 1.0 && widthFlag) {
                for (int i = 0; i < vandbrædtList.size(); i++) {
                    if (vandbrædtList.get(i).getLength() == widthList.get(x)) {
                        BOM.add(new OrderLine(vandbrædtList.get(i), 2, "Vandbrædt på stern i forende"));
                    }
                }
                widthFlag = false;
            }
            else if(widthFlag){
                for (int i = 0; i < widthList.size(); i++) {
                    if(carportWidth / (widthList.get(widthList.size()-1) + widthList.get(i)) <= 1.0 && widthFlag){
                        for (int j = 0; j < vandbrædtList.size(); j++) {
                            if(widthList.get(widthList.size()-1).equals(widthList.get(i))){
                                if(vandbrædtList.get(j).getLength() == widthList.get(widthList.size()-1)){
                                    BOM.add(new OrderLine(vandbrædtList.get(j), 2, "Vandbrædt på stern i forende"));
                                }
                            }else {
                                if (vandbrædtList.get(j).getLength() == widthList.get(widthList.size() - 1)) {
                                    BOM.add(new OrderLine(vandbrædtList.get(j), 1, "Vandbrædt på stern i forende"));
                                }
                                if (vandbrædtList.get(j).getLength() == widthList.get(i)) {
                                    BOM.add(new OrderLine(vandbrædtList.get(j), 1, "Vandbrædt på stern i forende"));
                                }
                            }
                        }
                        widthFlag = false;
                    }
                }
            }
        }
    }

//--Tagplader (also calculates screws)
    public void calcTagplader(double carportLength, double carportWidth) {
        List<Integer> lengthList = new ArrayList<>();
        List<Material> tagpladeList = new ArrayList<>();

        int numberOfTagplader = (int) Math.ceil(carportWidth/1000);

        for (int x = 0; x < allMaterials.size(); x++) {
            if (allMaterials.get(x).getFunctionality().toLowerCase().contains("tagplade")) {
                lengthList.add(allMaterials.get(x).getLength());
                tagpladeList.add(allMaterials.get(x));
            }
        }

        lengthList.sort(Comparator.naturalOrder());

        boolean lengthFlag = true;

        for (int x = 0; x < lengthList.size(); x++) {
            if (carportLength / lengthList.get(x) <= 1.0 && lengthFlag) {
                for (int i = 0; i < tagpladeList.size(); i++) {
                    if (lengthList.get(x).equals(tagpladeList.get(i).getLength())) {
                        BOM.add(new OrderLine(tagpladeList.get(i), numberOfTagplader, "Tagplader, monteres på spær"));
                    }
                }
                lengthFlag = false;
            }
        }
        for (int x = 0; x < lengthList.size(); x++) {
            if (carportLength / (lengthList.get(x) * 2) <= 1.0 && lengthFlag) {
                for (int i = 0; i < tagpladeList.size(); i++) {
                    if (tagpladeList.get(i).getLength() == lengthList.get(x)) {
                        BOM.add(new OrderLine(tagpladeList.get(i), numberOfTagplader*2, "Tagplader, monteres på spær"));
                    }
                }
                lengthFlag = false;
            }
            else if(lengthFlag){
                for (int i = 0; i < lengthList.size(); i++) {
                    if (carportLength / (lengthList.get(lengthList.size() - 1) + lengthList.get(i)) <= 1.0 && lengthFlag) {
                        for (int j = 0; j < tagpladeList.size(); j++) {
                            if (lengthList.get(lengthList.size() - 1).equals(lengthList.get(i))) {
                                if (tagpladeList.get(j).getLength() == lengthList.get(lengthList.size() - 1)) {
                                    BOM.add(new OrderLine(tagpladeList.get(j), numberOfTagplader*2, "Tagplader, monteres på spær"));
                                }
                            } else {
                                if (tagpladeList.get(j).getLength() == lengthList.get(lengthList.size() - 1)) {
                                    BOM.add(new OrderLine(tagpladeList.get(j), numberOfTagplader, "Tagplader, monteres på spær"));
                                }
                                if (tagpladeList.get(j).getLength() == lengthList.get(i)) {
                                    BOM.add(new OrderLine(tagpladeList.get(j), numberOfTagplader, "Tagplader, monteres på spær"));
                                }
                            }
                        }
                        lengthFlag = false;
                    }
                }
            }
        }
    }
//----------------------------------------Shed--------------------------------------------------------------------------


//----------------------------------------Misc--------------------------------------------------------------------------

//--Skruer
    public void calcSkruer(double carportLength, double carportWidth){

    //--Tagplade screws
        int tagpladeScrewsNum = (int)Math.ceil((carportWidth/1000)/2); //we need half the number of packs of screws per amount of tagplader

        for (int x = 0; x < allMaterials.size(); x++) {
            if (allMaterials.get(x).getName().toLowerCase().contains("bundskruer")) {
                BOM.add(new OrderLine(allMaterials.get(x), tagpladeScrewsNum, "Skruer til tagplader"));
            }
        }

    //--Vindkryds
        double spaceLength = carportLength - 550; //accounts for the bit where the vindkryds doesn't touch
        double spaceWidth = carportWidth - 700; //basically the same ^

        if(shedLength > 0){
            spaceLength -= shedLength; //if there is a shed, its length is subtracted from the space needed for the vindkryds to cover
        }else{
            spaceLength -= 550; //if there is no shed, the other end is also shaved off the potential length
        }

        double vindkrydsLength = Math.sqrt(Math.pow(spaceLength,2) + Math.pow(spaceWidth,2)); //vindkryds is the hypotenuse (hence the pythagoran theorem)
        int numberOfRollsNeeded = (int)Math.ceil((vindkrydsLength*2) /10000); //10,000 = 10 meters

        for (int x = 0; x < allMaterials.size(); x++) {
            if (allMaterials.get(x).getName().toLowerCase().contains("hulbånd")) {
                BOM.add(new OrderLine(allMaterials.get(x), numberOfRollsNeeded, "Til vindkryds på spær"));
            }
        }

    //--Spær screws
        int spærNumber = (int) Math.ceil(carportLength / 550);
        for (int x = 0; x < allMaterials.size(); x++) {
            if (allMaterials.get(x).getName().toLowerCase().contains("universal 190")) {
                BOM.add(new OrderLine(allMaterials.get(x), spærNumber, "Til montering af spær på rem"));
            }
        }

    //--Stern & Vandbrædt screws (not sure how to calculate this, so I'll just add 1 like in the pdf)
        for (int x = 0; x < allMaterials.size(); x++) {
            if (allMaterials.get(x).getName().toLowerCase().contains("4,5 x 60 mm.")) {
                BOM.add(new OrderLine(allMaterials.get(x), 1, "Til montering af stern & vandbrædt"));
            }
        }

    //--Universalbeslag & Hulbånd screws (don't know how to calculate this either, so same as above)
        for (int x = 0; x < allMaterials.size(); x++) {
            if (allMaterials.get(x).getName().toLowerCase().contains("beslagskruer")) {
                BOM.add(new OrderLine(allMaterials.get(x), 3, "Til montering af universalbeslag & hulbånd"));
            }
        }

    //--Rem & Stolpe stuff (don't understand how this is calculated in the pdf, but I'll just go according to where Rem and Stolpe meets)
        int stuffNeeded = 0;

        if(shedLength == 0 || shedWidth == 0) {
            stuffNeeded = 6;
        }else{
            stuffNeeded = 8;
        }

        for (int x = 0; x < allMaterials.size(); x++) {
            if (allMaterials.get(x).getName().toLowerCase().contains("bræddebolt")) {
                //doubled the number of stolper since that (sort of) seems to be the way its calculated in the pdf...
                BOM.add(new OrderLine(allMaterials.get(x), stuffNeeded*2, "Til montering af rem på stolper"));
            }
            if (allMaterials.get(x).getName().toLowerCase().contains("firkantskiver")) {
                BOM.add(new OrderLine(allMaterials.get(x), stuffNeeded, "Til montering af rem på stolper"));
            }
        }

    }
}