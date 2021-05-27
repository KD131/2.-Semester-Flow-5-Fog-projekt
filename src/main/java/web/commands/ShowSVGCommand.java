package web.commands;

import business.exceptions.DatabaseConnectionException;
import business.exceptions.UserException;
import business.services.MaterialFacade;
import business.services.MaterialsCalculator;
import business.services.SVG;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;

public class ShowSVGCommand  extends CommandUnprotectedPage {


    public ShowSVGCommand(String pageToShow)
    {
        super(pageToShow);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException
    {
        int orderID;
        double carportLength;
        double carportWidth;
        double shedLength;
        double shedWidth;
        double carportHeight = 235;
        double carportInnerHeight = carportHeight - 25;

        DecimalFormat df = new DecimalFormat("####0.0");


        try
        {
            orderID = Integer.parseInt(request.getParameter("orderID"));
            carportLength = Integer.parseInt(request.getParameter("carportLength"));
            carportWidth = Integer.parseInt(request.getParameter("carportWidth"));
            shedLength = Integer.parseInt(request.getParameter("shedLength"));
            shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        }

        catch (NumberFormatException ex)
        {
            request.setAttribute("Error", "Wrong Input");
            return "index"; // fix
        }

        //-------------- SVG DIMENSIONS --------------//
        double SVGWidth = carportLength + 100; // on all drawings the x axis or width of drawings depicts the length of carports and sheds
        double sideSVGHeight = carportHeight + 100; // on side view drawing the y axis or height of drawings depicts the height of carports and sheds
        double topSVGHeight = carportWidth + 200; // on top view drawing the y axis or height of drawings depicts the width of carports and sheds


        String SVGWidthStr = String.valueOf(SVGWidth);
        String sideSVGHeightStr = String.valueOf(sideSVGHeight);
        String topSVGHeightStr = String.valueOf(topSVGHeight);

        String sideViewBoxStr = "0 0 " + SVGWidthStr + " " + sideSVGHeightStr;
        String topViewBoxStr = "0 0 " + SVGWidthStr + " " + topSVGHeightStr;


        //----------ADDS SVGs FOR SIDE VIEW--------------//
        SVG sideSVG = new SVG(0,0, sideViewBoxStr,100,100); //contains sideViewSVG and dimension arrows
        SVG sideViewSVG = new SVG(45,0, sideViewBoxStr, 100, 100); //contains drawing of carport from side

        //----------ADDS SVGs FOR TOP VIEW--------------//
        SVG topSVG = new SVG(0, 0, topViewBoxStr, 100, 100); //contains topViewSVG and topRafterSVG and dimension arrows.
        SVG topRafterSVG = new SVG(30, 75, topViewBoxStr, 100, 100); //contains dimensions between rafters
        SVG topViewSVG = new SVG(30, 130, topViewBoxStr, 100, 100); //contains drawing of carport from above

        //adds style and arrow devs
        topSVG.addDevsTemplate();
        topSVG.addStyleTemplate();
        sideSVG.addDevsTemplate();
        sideSVG.addStyleTemplate();


        //------GENERAL CONSTRUCTION DIMENSIONS---------//

        //Determines front hang in accordance to carport length.
        // Front hang is the distance of hanging roof overhead in the entrance of the carport
        double xFrontHang = 0;
        if(carportLength > 300){
            xFrontHang = 100;
        }
        if(carportLength <= 300){
            xFrontHang = 50;
        }
        // back hang is the distance of hanging roof overhead in the back of the carport
        double xBackHang = 30;
        // total hang accumulates total hang in x axis.
        double xTotalHang = xBackHang + xFrontHang;

        // side hang is the distance of hanging roof in each side of the carport
        double ySideHang = 30;
        double yTotalHang = ySideHang*2;

        // construction length is the distance between the first pole and the last pole in the carport length (x axis)
        double constructionLength = carportLength-xTotalHang;

        // construction width is the distance between the poles in the carport width (y axis, in top view)
        double constructionWidth = carportWidth-yTotalHang;

        // middle distance determines the distance between the middle pole and the shed pole.
        double middleDistance = (constructionLength/2) - shedLength;

        //Calculates exact distance between rafters
        double rafterDistance = 55;
        double rafterDistanceRounded = Math.round(carportLength/rafterDistance);
        int rafterAmount = (int) rafterDistanceRounded;
        double exactRafterDistance = carportLength/rafterAmount;

        //calculates slope degree on roof
        double roofSlopePercent = ((carportHeight-(carportHeight-10))/(carportLength));
        double roofSlopeDegrees = Math.toDegrees(roofSlopePercent);

        //calculates height difference per rafter because of slope. rise = pitch/run
        //rise = height change in roof over a distance (run)
        //run = the covered distance of roof
        //pitch = roof slope in percent
        double rise = roofSlopePercent*exactRafterDistance;

        //Material Specific dimensions
        //DA: stolpe
        double poleWidth = 9.7;

        //DA: spær
        double rafterWidth = 4.5;
        double rafterHeight = 19.5;
        double rafterReduction = 5;

        //DA: rem
        double plateWidth = 4.5;
        double plateHeight = 19.5;

        //DA: løsholter
        double noggingWidth = 4.5;

        //DA: Beklædning
        double claddingDepth = 1.9;
        double claddingWidth = 10.0;
        double claddingHeight = 190;

        //DA: Stern
        double sternHeight = 20;
        double underStern = 2.5;
        double overStern = 2.5;

        //Calculates amount of cladding
        int xWallAmount = (int)  (shedLength/claddingWidth);
        int yWallAmount = (int) (shedWidth/claddingWidth);

        //dimensions to string
        String carportLengthStr = String.valueOf(carportLength);
        String carportWidthStr = String.valueOf(carportWidth);
        String carportFrontHeightStr = String.valueOf(carportHeight);
        String carportBackHeightStr = String.valueOf(carportHeight-10);
        String carportInnerHeightStr = String.valueOf(carportInnerHeight);
        String xFrontHangStr = String.valueOf(xFrontHang);
        String xBackHangStr = String.valueOf(xBackHang);
        String constructionLengthStr = String.valueOf(constructionLength);
        String halfConstructionLengthStr = String.valueOf(constructionLength/2);
        String middleDistanceStr = String.valueOf(middleDistance);
        String longShedMiddleDistanceStr = String.valueOf(-middleDistance);
        String shedLengthStr = String.valueOf(shedLength);

        //dimension text placers
        String yTopAxisMid = String.valueOf((carportWidth/2)+130);
        String xAxisMid = String.valueOf(SVGWidth/2);
        String yAxisMax = String.valueOf(carportWidth+30);
        String ySideAxisMid = String.valueOf((carportHeight/2));
        String xFrontHangMid = String.valueOf(xFrontHang/2);

        //------------DRAWING FROM SIDE: Arrows and Dimensions------------------------//

            //Vertical (y axis) arrows and dimensions//
            //carport height (y axis) arrow and dimension
            sideSVG.addArrow(25, 0, 25, carportHeight);
            sideSVG.addText(0, 0, "small", "translate(23, " + ySideAxisMid + ") rotate(-90)", carportFrontHeightStr + " cm");

            //carport inner height (y axis) arrow and dimension
            sideSVG.addArrow(40, carportHeight-carportInnerHeight, 40, carportHeight);
            sideSVG.addText(0, 0, "small", "translate(38, " + ySideAxisMid + ") rotate(-90)", carportInnerHeightStr + " cm");

            //carport back height (y axis) arrow and dimension
            sideSVG.addArrow(carportLength+65, 10, carportLength+65, carportHeight);
            sideSVG.addText(0, 0, "small", "translate("+ (carportLength+80) + ", " + ySideAxisMid + ") rotate(-90)", carportBackHeightStr + " cm");

            //Horizontal (x axis) arrows and dimensions//
            //draws following if there is no shed.
            if(shedLength == 0)
            {
                    //start -> 1st pole (xFrontHang) dimension and arrow.
                    sideSVG.addArrow(55, 50 + carportHeight, 55 + xFrontHang, 50 + carportHeight);
                    sideSVG.addText(0, 65, "small", "translate(" + (55 + xFrontHang/2) + "," + carportFrontHeightStr + ")", xFrontHangStr + " cm");

                    //1st pole -> 2nd (Half construction length) pole length dimension and arrow
                    sideSVG.addArrow(55 + xFrontHang + poleWidth, 50 + carportHeight, 55 + xFrontHang + (constructionLength / 2), 50 + carportHeight);
                    sideSVG.addText(0, 65, "small", "translate(" + (10+45 + xFrontHang + constructionLength/4) + "," + carportFrontHeightStr + ")", halfConstructionLengthStr + " cm");

                    //2nd pole -> 3rd pole (Half construction length) length dimension and arrow
                    sideSVG.addArrow(55 + xFrontHang + (constructionLength / 2) + poleWidth, 50 + carportHeight,55 + xFrontHang + constructionLength, 50 + carportHeight);
                    sideSVG.addText(0, 65, "small", "translate(" + (10+45 + poleWidth + xFrontHang + (constructionLength / 2) + constructionLength/4 + "," + carportFrontHeightStr + ")"),  halfConstructionLengthStr + " cm");

                    //3rd pole -> end (xBackHang) length dimension and arrow
                    sideSVG.addArrow(55 + xFrontHang + poleWidth + constructionLength, 50 + carportHeight, 55 + carportLength, 50 + carportHeight);
                    sideSVG.addText(0, 65, "small", "translate(" + (10+35 + carportLength + xBackHang) + "," + carportFrontHeightStr + ")", xBackHangStr + " cm");
                }

            //draws following if shed is NOT longer than half the construction length.
            if(constructionLength/2 > shedLength && shedLength != 0) {
                    //start -> 1st pole (front hang) dimension and arrow.
                    sideSVG.addArrow(55, 50 + carportHeight, 55 + xFrontHang, 50 + carportHeight);
                    sideSVG.addText(0, 65, "small", "translate(" + (55 + xFrontHang/2) + "," + carportFrontHeightStr + ")", xFrontHangStr + " cm");

                    //1st pole -> 2nd pole (Half construction length) length dimension and arrow
                    sideSVG.addArrow(55 + xFrontHang + poleWidth, 50 + carportHeight, 55 + xFrontHang + (constructionLength / 2), 50 + carportHeight);
                    sideSVG.addText(0, 65, "small", "translate(" + (10+45 + xFrontHang + (constructionLength / 4 )) + "," + carportFrontHeightStr + ")", halfConstructionLengthStr + " cm");

                    //2nd pole -> 1st Shed pole (middle distance) length dimension and arrow
                    sideSVG.addArrow(55 + xFrontHang + poleWidth + (constructionLength / 2), 50 + carportHeight, 55 + xFrontHang + constructionLength - shedLength, 50 + carportHeight);
                    sideSVG.addText(0, 65, "small", "translate(" + (10+45 + xFrontHang + (constructionLength / 2) + middleDistance / 2) + "," + carportFrontHeightStr + ")", middleDistanceStr + " cm");

                    //shed length dimension and arrow
                    sideSVG.addArrow(55 + xFrontHang + poleWidth + constructionLength - shedLength, 50 + carportHeight, 55 + xFrontHang + constructionLength, 50 + carportHeight);
                    sideSVG.addText(0, 65, "small", "translate(" + (10+45 + xFrontHang + (constructionLength - 0.5 * shedLength)) + "," + carportFrontHeightStr + ")", shedLengthStr + " cm");

                    //3rd pole -> end (xBackHang) length dimension and arrow
                    sideSVG.addArrow(55 + xFrontHang + poleWidth + constructionLength, 50 + carportHeight, 55 + carportLength, 50 + carportHeight);
                    sideSVG.addText(0, 65, "small", "translate(" + (10+35 + carportLength + xBackHang) + "," + carportFrontHeightStr + ")", xBackHangStr + " cm");
                }

            //draws following if shed IS longer than half the construction length.
            if(constructionLength/2 < shedLength && shedLength !=0) {
                    //start -> 1st pole (front hang) dimension and arrow.
                    sideSVG.addArrow(55, 50 + carportHeight, 55 + xFrontHang, 50 + carportHeight);
                    sideSVG.addText(0, 65, "small", "translate(" + (55 + xFrontHang/2) + "," + carportFrontHeightStr + ")", xFrontHangStr + " cm");

                    //1st pole -> shed pole length dimension and arrow
                    sideSVG.addArrow(55 + xFrontHang + poleWidth, 50 + carportHeight, 55 + xFrontHang + (constructionLength -shedLength), 50 + carportHeight);
                    sideSVG.addText(0, 65, "small", "translate(" + (10+45 + xFrontHang + (constructionLength - shedLength)/2) + "," + carportFrontHeightStr + ")", halfConstructionLengthStr + " cm");

                    //shed pole -> mid pole length dimension and arrow
                    sideSVG.addArrow(55 + xFrontHang + (constructionLength -shedLength)+poleWidth, 25 + carportHeight, 55 + xFrontHang + (constructionLength/2), 25 + carportHeight);
                    sideSVG.addText(0, 40, "small", "translate(" + (10+45 + xFrontHang + (constructionLength - shedLength)-middleDistance/2) + "," + carportFrontHeightStr + ")", longShedMiddleDistanceStr + " cm");

                    //shed width length dimension and arrow
                    sideSVG.addArrow(55 + xFrontHang + poleWidth + constructionLength - shedLength, 50 + carportHeight, 55 + xFrontHang + constructionLength, 50 + carportHeight);
                    sideSVG.addText(0, 65, "small", "translate(" + (10+45 + xFrontHang + (constructionLength - 0.5 * shedLength)) + "," + carportFrontHeightStr + ")", shedLengthStr + " cm");

                    //3rd pole -> end (xBackHang) length dimension and arrow
                    sideSVG.addArrow(55 + xFrontHang + poleWidth + constructionLength, 50 + carportHeight, 55 + carportLength, 50 + carportHeight);
                    sideSVG.addText(0, 65 , "small", "translate(" + (10+35 + carportLength + xBackHang) + "," + carportFrontHeightStr + ")", xBackHangStr + " cm");
                }

        //------------DRAWING FROM SIDE: Carport and Shed------------------------//

        //draws carport poles
        sideViewSVG.addRect(10+xFrontHang+constructionLength-shedLength, 20, carportInnerHeight+5, poleWidth);
        for (int x = 0; x< 3; x++)
        {
            sideViewSVG.addRect(10+xFrontHang+(x*(constructionLength/2)), 20, carportInnerHeight+5, poleWidth);
        }

        //draws rafters. tilted.
        for (int x = 0; x<rafterAmount; x++)
        {
            sideViewSVG.addRotoRect(10+x*exactRafterDistance, x*rise + carportHeight-carportInnerHeight-rafterHeight, rafterHeight, rafterWidth, roofSlopeDegrees);

        }

        //draws cladding. 1st layer.
        for (int x = 0; x<xWallAmount; x++)
        {
            sideViewSVG.addRect(10+0.5*poleWidth + xFrontHang+constructionLength-shedLength+(x*claddingWidth),carportHeight-195,claddingHeight-3,claddingWidth);
        }

        //drwas plates. tilted.
        sideViewSVG.addRotoRect(10,carportHeight-carportInnerHeight, plateHeight,carportLength,roofSlopeDegrees);

        //draws side stern. tilted
        sideViewSVG.addRotoRect(10,0, sternHeight,carportLength,roofSlopeDegrees);

        //draws front stern. tilted
        sideViewSVG.addRotoRect(10-underStern,roofSlopePercent*underStern, sternHeight,underStern,roofSlopeDegrees);

        //draws end stern. tilted
        sideViewSVG.addRotoRect(10+carportLength,roofSlopePercent*carportLength-sternHeight*0.5, sternHeight,underStern,roofSlopeDegrees);

        //------------DRAWING FROM TOP: Arrows and Dimensions------------------------//
        //carport width (y axis) arrow and dimension.
        topSVG.addArrow(15,130,15,130+carportWidth);
        topSVG.addText( 0, 0, "small", "translate(10,"+ yTopAxisMid +") rotate(-90)",carportWidthStr + " cm");

        //carport length (x axis) arrow and dimension.
        topSVG.addArrow(30,145+carportWidth, 30+carportLength, 145+carportWidth);
        topSVG.addText( 0, 130, "small", "translate("+ xAxisMid +", " + yAxisMax+")",carportLengthStr + " cm");

        //shed width (y axis) arrow and dimension.
        if(shedWidth != 0) {
            topSVG.addArrow(carportLength + 55, 130+ySideHang, carportLength + 55, 130+ySideHang+shedWidth);
            topSVG.addText(0, 0, "small", "translate(" + (carportLength + 70) + ", " + (130+ySideHang+shedWidth/2) + ") rotate(-90)", shedWidth + " cm");
        }

        //------------DRAWING FROM TOP: Carport and Shed------------------------//
        //draws noggings
        for (int x=0; x<2; x++)
        {
            topViewSVG.addRect(xFrontHang+constructionLength-(x*shedLength)+0.25*poleWidth,ySideHang, shedWidth, noggingWidth);
            topViewSVG.addRect(xFrontHang+constructionLength-shedLength, ySideHang+(x*shedWidth)+0.25*poleWidth, noggingWidth, shedLength);
        }


        //shed length cladding (x axis)
        for (int x=0; x<=xWallAmount;x++)
        {
            for (int y = 0; y<2;y++)
            {
                topViewSVG.addRect(xFrontHang+constructionLength-(x*claddingWidth),ySideHang - claddingDepth + ((y*shedWidth+claddingDepth)+(y*poleWidth)),claddingDepth,claddingWidth);
            }
        }

        //shed width cladding (y axis)
        for (int x=0; x<=yWallAmount;x++)
        {
            for(int y=0; y<2;y++)
            {
                topViewSVG.addRect(xFrontHang+constructionLength + poleWidth - ((y*shedLength)+y*poleWidth),ySideHang+(x*claddingWidth), claddingWidth, claddingDepth );
            }

        }

        //Draws plate
        for (int x = 0; x < 2; x++)
        {
            topViewSVG.addRect(0, (poleWidth-plateWidth)/2 + ySideHang+x*constructionWidth, plateWidth,carportLength);
        }

        //Draws rafters
        for (int x = 0; x <= rafterAmount; x++) {
            String exactRafterDistanceStr = String.valueOf(5+(x*exactRafterDistance)+0.5*exactRafterDistance);

            //Draws end rafter and help line
            if(x == rafterAmount){

                // rafter
                topViewSVG.addRect((x*exactRafterDistance)-rafterWidth,rafterReduction*0.5,carportWidth-rafterReduction,rafterWidth);

                // help line
                topRafterSVG.addLine(x*(exactRafterDistance)-rafterWidth+(rafterWidth*0.5), 10,x*(exactRafterDistance)-rafterWidth+(rafterWidth*0.5), 30);
            }

            //draws last distance arrow, help line and dimension text. draws 2nd to last rafter.
            else if(x == rafterAmount-1){

                //rafter
                topViewSVG.addRect(x * exactRafterDistance + rafterWidth * 0.5, rafterReduction*0.5, carportWidth-rafterReduction, rafterWidth);

                //help line
                topRafterSVG.addLine(x * exactRafterDistance + rafterWidth, 10, x * exactRafterDistance + rafterWidth, 30);

                //arrow
                topRafterSVG.addArrow(x * exactRafterDistance + rafterWidth, 15, (x+1)*(exactRafterDistance)-rafterWidth+rafterWidth*0.5, 15);

                //distance text
                topRafterSVG.addText(0, 0, "small", "translate(" + exactRafterDistanceStr + ", " + 10 + ")",  df.format(exactRafterDistance) + "cm");
            }

            //draws rest of the rafters, lines, arrows and texts.
            else {

                //rafter
                topViewSVG.addRect(x * exactRafterDistance + rafterWidth * 0.5 , rafterReduction*0.5, carportWidth-rafterReduction, rafterWidth);

                //help lines
                topRafterSVG.addLine(x * exactRafterDistance + rafterWidth, 10, x * exactRafterDistance + rafterWidth, 30);

                //arrows
                topRafterSVG.addArrow(x * exactRafterDistance + rafterWidth, 15, (x * exactRafterDistance + rafterWidth) + exactRafterDistance, 15);

                //distance text
                topRafterSVG.addText(0, 0, "small", "translate(" + exactRafterDistanceStr + ", " + 10 + ")",  df.format(exactRafterDistance) + "cm");
            }

        }


        //draws shed poles
        for (int x = 0; x < 2; x++)
        {
            for(int y = 0; y <3; y++)
            {
                topViewSVG.addRect(xFrontHang+constructionLength-(x*shedLength), ySideHang+(y*((shedWidth /* -10 */)*0.5)),poleWidth,poleWidth);
            }

        }
        //draws carport poles
        for (int y = 0; y < 2; y++)
        {
            for (int x = 0; x < 3; x++) {
                topViewSVG.addRect(xFrontHang+(x*constructionLength/2), ySideHang+(y*constructionWidth), poleWidth/*stolpe dimensioner*/, poleWidth/*stolpe dimensioner*/);
            }
        }

        //Draws side under stern
        for (int x=0; x<2; x++)
        {
            topViewSVG.addRect(0,(x*carportWidth)-(x*0.5*rafterReduction),underStern,carportLength);
        }

        //Draws perforated tape
        topViewSVG.addStippledLine(xFrontHang, ySideHang, carportLength*0.75,ySideHang+constructionWidth+poleWidth);
        topViewSVG.addStippledLine(xFrontHang+3, ySideHang, (carportLength*0.75)+3,ySideHang+constructionWidth+poleWidth);

        topViewSVG.addStippledLine(xFrontHang, ySideHang+constructionWidth+poleWidth, carportLength*0.75,ySideHang);
        topViewSVG.addStippledLine(xFrontHang+3, ySideHang+constructionWidth+poleWidth, (carportLength*0.75)+3,ySideHang);

        //Draws end stern
        for (int x=0; x<2; x++)
        {
            topViewSVG.addRect(x*carportLength,0,carportWidth,underStern);
        }


        topSVG.addSvg(topViewSVG);
        topSVG.addSvg(topRafterSVG);
        sideSVG.addSvg(sideViewSVG);
        request.setAttribute("sidesvgdrawing", sideSVG.toString());
        request.setAttribute("topsvgdrawing", topSVG.toString());
        return pageToShow;

    }
}
