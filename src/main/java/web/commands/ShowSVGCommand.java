package web.commands;

import business.exceptions.UserException;
import business.services.SVG;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowSVGCommand  extends CommandProtectedPage {

    public ShowSVGCommand(String pageToShow, String role)
    {
        super(pageToShow, role);
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
        SVG innerSVG = new SVG(30, 0, innerViewBoxStr, 100, 100);
        svg.addDevsTemplate();
        svg.addStyleTemplate();


        svg.addArrow(15,0,15,carportWidth);
        svg.addArrow(30,carportWidth, 30+carportLength, carportWidth);


        double sparDistance = 55;
        int sparAmount = (int) (carportLength/sparDistance) + 1;
        double xFrontHang = sparDistance*2;
        double xBackHang = sparDistance*0.5;
        double xTotalHang = xBackHang + xFrontHang;
        double ySideHang = 35;
        double yTotalHang = ySideHang*2;
        double constructionLength = carportLength-xTotalHang;
        double constructionWidth = carportWidth-yTotalHang;

        for (int x = 0; x < sparAmount; x++)
        {
                innerSVG.addRect(x*sparDistance,0,carportWidth,5/*spær bredde*/);
        }

        for (int y = 0; y < 2; y++)
        {
            for (int x = 0; x < 3; x++) {
                innerSVG.addRect(xFrontHang+(x*constructionLength/2), ySideHang+(y*constructionWidth), 9.7, 9.7);
            }
        }

        svg.addSvg(innerSVG);
        request.setAttribute("svgdrawing", svg.toString());
        return pageToShow;

    }
}
