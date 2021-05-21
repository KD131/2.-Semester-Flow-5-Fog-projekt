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
            return "index";
        }

        double outerSVGWidth = carportLength + 100;
        double outerSVGHeight = carportWidth + 200;

        String outerSVGWidthStr = String.valueOf(outerSVGWidth);
        String outerSVGHeightStr = String.valueOf(outerSVGHeight);

        String topViewBoxStr = "0 0 " + outerSVGWidthStr + " " + outerSVGHeightStr;

        //adds svg for top view
        SVG topSVG = new SVG(0, 0, topViewBoxStr, 100, 100 );
        SVG topRafterSVG = new SVG(30, 75, topViewBoxStr, 100, 100);
        SVG topViewSVG = new SVG(30, 150, topViewBoxStr, 100, 100);

        //adds svg for side view
        SVG sideSVG = new SVG(0,0, topViewBoxStr,100,100);
        SVG sideViewSVG = new SVG(30,0, topViewBoxStr, 100, 100);

        //adds style and arrow devs
        topSVG.addDevsTemplate();
        topSVG.addStyleTemplate();

        //dimensions to string
        String carportLengthStr = String.valueOf(carportLength);
        String carportWidthStr = String.valueOf(carportWidth);

        //text placers
        String yAxisMid = String.valueOf((carportWidth/2)+150);
        String xAxisMid = String.valueOf(outerSVGWidth/2);
        String yAxisMax = String.valueOf(carportWidth+30);

        //tegner pile og tekst
        topSVG.addArrow(15,150,15,150+carportWidth);
        topSVG.addText( 0, 0, "small", "translate(10,"+ yAxisMid +") rotate(-90)",carportWidthStr + " cm");

        topSVG.addArrow(30,150+15+carportWidth, 30+carportLength, 150+15+carportWidth);
        topSVG.addText( 0, 150, "small", "translate("+ xAxisMid +", " + yAxisMax+")",carportLengthStr + " cm");

        //flyt til stkliste beregner
        double sparDistance = 55;
        double sparRounded = Math.round(carportLength/sparDistance);
        int sparAmount = (int) sparRounded;
        double exactSparDistance = carportLength/sparAmount;


        //generelle byggedimensioner
        double xFrontHang = 0;
        if(carportLength > 300){
            xFrontHang = 100;
        }
        if(carportLength <= 300){
            xFrontHang = 50;
        }
        double xBackHang = 30;
        double xTotalHang = xBackHang + xFrontHang;
        double ySideHang = 30;
        double yTotalHang = ySideHang*2;
        double constructionLength = carportLength-xTotalHang;
        double constructionWidth = carportWidth-yTotalHang;

        //materiale specifikke dimensioner
        double stolpeTykkelse = 9.7;
        double spærbredde = 4.5;
        double remTykkelse = 4.5;
        double loeshoelterTykkelse = 4.5;
        double beklaedningTykkelse = 1.9;
        double beklaedningBredde = 10.0;
        double sparReduction = 5;
        double underStern = 2.5;
        double overStern = 2.5;

        //tegner stolper
        for (int y = 0; y < 2; y++)
        {
            for (int x = 0; x < 3; x++) {
                topViewSVG.addRect(xFrontHang+(x*constructionLength/2), ySideHang+(y*constructionWidth), stolpeTykkelse/*stolpe dimensioner*/, stolpeTykkelse/*stolpe dimensioner*/);
            }
        }

        //tegner rem
        for (int x = 0; x < 2; x++)
        {
            topViewSVG.addRect(0, (stolpeTykkelse-remTykkelse)/2 + ySideHang+x*constructionWidth, remTykkelse,carportLength);
        }


        for (int x = 0; x <= sparAmount; x++) {
            String sparDistanceStr = String.valueOf(5+(x*exactSparDistance)+0.5*exactSparDistance);

            // tegner ende spær og streg
            if(x == sparAmount){

                // spær
                topViewSVG.addRect((x*exactSparDistance)-spærbredde,sparReduction*0.5,carportWidth-sparReduction,spærbredde);

                // streg
                topRafterSVG.addLine(x*(exactSparDistance)-spærbredde+(spærbredde*0.5), 10,x*(exactSparDistance)-spærbredde+(spærbredde*0.5), 30);
            }
            //tegner sidste pil og tekst.  næstsidst resten.
            else if(x == sparAmount-1){

                //pil
                topRafterSVG.addArrow(x * exactSparDistance + spærbredde * 0.5, 15, (x+1)*(exactSparDistance)-spærbredde+(spærbredde*0.5), 15);

                // spær
                topViewSVG.addRect(x * exactSparDistance, sparReduction*0.5, carportWidth-sparReduction, spærbredde);

                // afstands streger
                topRafterSVG.addLine(x * exactSparDistance + spærbredde * 0.5, 10, x * exactSparDistance + spærbredde * 0.5, 30);

                // afstands tekst
                topRafterSVG.addText(0, 0, "small", "translate(" + sparDistanceStr + ", " + 10 + ")",  df.format(exactSparDistance) + "cm");
            }
            // tegner mellem spær

            else {

                // spær
                topViewSVG.addRect(x * exactSparDistance, sparReduction*0.5, carportWidth-sparReduction, spærbredde);

                // afstands streger
                topRafterSVG.addLine(x * exactSparDistance + spærbredde * 0.5, 10, x * exactSparDistance + spærbredde * 0.5, 30);

                // afstands pile
                topRafterSVG.addArrow(x * exactSparDistance + spærbredde * 0.5, 15, (x * exactSparDistance + spærbredde * 0.5) + exactSparDistance, 15);

                // afstands tekst
                topRafterSVG.addText(0, 0, "small", "translate(" + sparDistanceStr + ", " + 10 + ")",  df.format(exactSparDistance) + "cm");
            }

        }


        //tegner skur
            //stolper
        for (int x = 0; x < 2; x++)
        {
            for(int y = 0; y <3; y++)
            {
                topViewSVG.addRect(xFrontHang+constructionLength-(x*shedLength), ySideHang+(y*((shedWidth /* -10 */)*0.5)),stolpeTykkelse,stolpeTykkelse);
            }

        }

            //tegner løsholter
        for (int x=0; x<2; x++)
        {
            topViewSVG.addRect(xFrontHang+constructionLength-(x*shedLength)+0.25*stolpeTykkelse,ySideHang, shedWidth, loeshoelterTykkelse);
            topViewSVG.addRect(xFrontHang+constructionLength-shedLength, ySideHang+(x*shedWidth)+0.25*stolpeTykkelse, loeshoelterTykkelse, shedLength);
        }


            //tegner beklædning
        int xWallAmount = (int)  (shedLength/beklaedningBredde);
        int yWallAmount = (int) (shedWidth/beklaedningBredde);

        //x wall
        for (int x=0; x<=xWallAmount;x++)
        {
            for (int y = 0; y<2;y++)
            {
                topViewSVG.addRect(xFrontHang+constructionLength-(x*beklaedningBredde),ySideHang - beklaedningTykkelse + ((y*shedWidth+beklaedningTykkelse)+(y*stolpeTykkelse)),beklaedningTykkelse,beklaedningBredde);
            }
        }

        //y wall
        for (int x=0; x<=yWallAmount;x++)
        {
            for(int y=0; y<2;y++)
            {
                topViewSVG.addRect(xFrontHang+constructionLength + stolpeTykkelse - ((y*shedLength)+y*stolpeTykkelse),ySideHang+(x*beklaedningBredde), beklaedningBredde, beklaedningTykkelse );
            }

        }

        //hulbånd

//         <line stroke-dasharray="5, 5" x1="40" y1="20" x2="365" y2="369"
//        style="stroke:#000000"/>
//
//        <line stroke-dasharray="5, 5" x1="40" y1="369" x2="365" y2="20"
//        style="stroke:#000000"/>

        //tegn stern
        for (int x=0; x<2; x++)
        {
            topViewSVG.addRect(0,(x*carportWidth)-(x*sparReduction),underStern,carportLength);
        }

        topSVG.addSvg(topViewSVG);
        topSVG.addSvg(topRafterSVG);
        topSVG.addSvg(sideViewSVG);
        request.setAttribute("svgdrawing", topSVG.toString());
        return pageToShow;

    }
}
