package business.services;

import java.util.Locale;

public class SVG {

    StringBuilder svg = new StringBuilder();

    private int x;
    private int y;
    private String viewBox;
    private int width;
    private int height;
    private Locale l = Locale.ENGLISH; //necessary to avoid problems with the Locale that Kasper and Kristoffer ran into


    private final String headerTemplate = "<svg " +
            "height=\"%d%%\" " +
            "width=\"%d%%\" " +
            "viewBox=\"%s\" "+
            "x=\"%d\"   " +
            "y=\"%d\"   " +
            " preserveAspectRatio=\"xMinYMin\">";

    private final String styleTemplate = "<style> .small { font: italic 13px sans-serif; } </style>";

    private final String devsTemplate =
            "<defs> \n" +
                "<marker \n" +
                    "id=\"beginArrow\" \n" +
                    "markerWidth=\"12\" \n" +
                    "markerHeight=\"12\" \n"+
                    "refX=\"0\" \n" +
                    "refY=\"6\" \n" +
                    "orient=\"auto\"> \n" +
                   "<path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: black;\" /> \n" +
                 "</marker> \n" +
                 "<marker \n" +
                    "id=\"endArrow\" \n" +
                    "markerWidth=\"12\" \n" +
                    "markerHeight=\"12\" \n" +
                    "refX=\"12\" \n" +
                    "refY=\"6\" \n" +
                    "orient=\"auto\"> \n" +
                   "<path d=\"M0,0 L12,6 L0,12 L0,0\" style=\"fill: black;\" /> \n" +
                 "</marker> \n" +
                "</defs>";


    private final String rectTemplate = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke: black; fill: white\" />";

    private final String lineTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" stroke-width=\"2\" style=\"stroke: black\"/>";

    private final String lineStippledTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" stroke-width=\"2\" stroke-dasharray=\"6\" style=\"stroke: black\"/>";

    private final String arrowTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\"\n" +
            "style=\"stroke: black; marker-start: url(#beginArrow); marker-end: url(#endArrow);\" />";

    private final String textTemplate = "<text x=\"%d\" y=\"%d\" text-anchor=\"middle\" class=\"%s\" transform=\"%s\">%s</text>";

    public SVG(int x, int y, String viewBox, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.viewBox = viewBox;
        this.width = width;
        this.height = height;
        svg.append(String.format(l, headerTemplate, height, width, viewBox, x, y ));

    }

    public void addText(int x, int y, String tClass, String transform, String text)
    {
        svg.append(String.format(l, textTemplate, x, y, tClass, transform, text));
    }

    public void addStyleTemplate(){
        svg.append(String.format(l, styleTemplate));
    }

    public void addDevsTemplate(){
        svg.append(String.format(l, devsTemplate));
    }

    public void addArrow(int x1, int y1, int x2, int y2)
    {
        svg.append(String.format(l, arrowTemplate,x1,y1,x2,y2));
    }

    public void addRect(double x, double y, double height, double width)
    {
        svg.append(String.format(l, rectTemplate, x, y, height, width));
    }

    public void addLine(int x1, int y1, int x2, int y2 )
    {
        svg.append(String.format(l, lineTemplate, x, y, height, width));
    }

    public void addStippledLine(int x1, int y1, int x2, int y2 )
    {
        svg.append(String.format(l, lineStippledTemplate, x, y, height, width));
    }

    public void addSvg(SVG innerSVG)
    {
        svg.append(innerSVG.toString());
    }

    public void endTag(){
        svg.append("</svg>");
    }

    @Override
    public String toString()
    {
        return svg.toString() + "</svg>" ;
    }


}
