package web.commands;

import business.exceptions.UserException;
import business.services.SVG;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowSVGCommand  extends CommandUnprotectedPage {

    public ShowSVGCommand(String pageToShow)
    {
        super(pageToShow);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException
    {
        int orderID;
        int carportLength;
        int carportWidth;
        int shedLength;
        int shedWidth;

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

        int outerSVGWidth = carportLength + 100;
        int outerSVGHeight = carportWidth + 100;

        String outerSVGWidthStr = String.valueOf(outerSVGWidth);
        String outerSVGHeightStr = String.valueOf(outerSVGHeight);

        String outerViewBoxStr = "0 0 " + outerSVGWidthStr + " " + outerSVGHeightStr;
        String innerViewBoxStr = "0 0 " + carportWidth + " " + carportLength;


        SVG svg = new SVG(0, 0, outerViewBoxStr, 100, 100 );
        SVG innerSVG = new SVG(30, 0, outerViewBoxStr, 100, 100);
        svg.addDevsTemplate();
        svg.addStyleTemplate();

        String carportLengthStr = String.valueOf(carportLength);
        String carportWidthStr = String.valueOf(carportWidth);


        String yAxisMid = String.valueOf(carportWidth/2);
        String xAxisMid = String.valueOf(outerSVGWidth/2);
        String yAxisMax = String.valueOf(carportWidth+30);


        svg.addArrow(15,0,15,carportWidth);
        svg.addText( 0, 0, "small", "translate(10,"+ yAxisMid +") rotate(-90)",carportWidthStr + " cm");

        svg.addArrow(30,15+carportWidth, 30+carportLength, 15+carportWidth);
        svg.addText( 0, 0, "small", "translate("+ xAxisMid +", " + yAxisMax+")",carportLengthStr + " cm");

        double sparDistance = 55;
        double xFrontHang = sparDistance*2;
        double xBackHang = sparDistance*0.5;
        double xTotalHang = xBackHang + xFrontHang;
        double ySideHang = 35;
        double yTotalHang = ySideHang*2;
        double constructionLength = carportLength-xTotalHang;
        double constructionWidth = carportWidth-yTotalHang;


        //midlertidige
        double stolpeTykkelse = 9.7;
        double spærbredde = 5;
        double remTykkelse = 5;
        int sparAmount = (int) (carportLength/sparDistance);

        // tegner ende spær
        for (int x = 0; x < 2; x++)
        {
                innerSVG.addRect(x*(carportLength-spærbredde),0,carportWidth,spærbredde);
        }
        // tegner mellem spær
        for (int x = 0; x < sparAmount-1; x++)
        {
                innerSVG.addRect(sparDistance + x*(carportLength/sparAmount),0,carportWidth,5/*spær bredde*/);
        }

        //tegner rem
        for (int x = 0; x < 2; x++)
        {
                innerSVG.addRect(0, (stolpeTykkelse-remTykkelse)/2 + ySideHang+x*constructionWidth, remTykkelse,carportLength);
        }

        //tegner stolper
        for (int y = 0; y < 2; y++)
        {
            for (int x = 0; x < 3; x++) {
                innerSVG.addRect(xFrontHang+(x*constructionLength/2), ySideHang+(y*constructionWidth), 9.7/*stolpe dimensioner*/, 9.7/*stolpe dimensioner*/);
            }
        }

        svg.addSvg(innerSVG);
        request.setAttribute("svgdrawing", svg.toString());
        return pageToShow;

    }
}
