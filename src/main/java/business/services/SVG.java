package business.services;

public class SVG {

    StringBuilder svg = new StringBuilder();

    private int x;
    private int y;
    private String viewBox;
    private int width;
    private int height;


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
                   "<path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" /> \n" +
                 "</marker> \n" +
                 "<marker \n" +
                    "id=\"endArrow\" \n" +
                    "markerWidth=\"12\" \n" +
                    "markerHeight=\"12\" \n" +
                    "refX=\"12\" \n" +
                    "refY=\"6\" \n" +
                    "orient=\"auto\"> \n" +
                   "<path d=\"M0,0 L12,6 L0,12 L0,0\" style=\"fill: #000000;\" /> \n" +
                 "</marker> \n" +
                "</defs>";


    private final String rectTemplate = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";

    private final String arrowTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\"\n" +
            "style=\"stroke: #006600; marker-start: url(#beginArrow); marker-end: url(#endArrow);\" />";

    private final String textTemplate = "<text x=\"%d\" y=\"%d\" text-anchor=\"middle\" class=\"%s\" transform=\"%s\">%s</text>";

    public SVG(int x, int y, String viewBox, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.viewBox = viewBox;
        this.width = width;
        this.height = height;
        svg.append(String.format(headerTemplate, height, width, viewBox, x, y ));

    }

    public void addText(int x, int y, String tClass, String transform, String text)
    {
        svg.append(String.format(textTemplate, x, y, tClass, transform, text));
    }

    public void addStyleTemplate(){
        svg.append(String.format(styleTemplate));
    }

    public void addDevsTemplate(){
        svg.append(String.format(devsTemplate));
    }

    public void addArrow(int x1, int y1, int x2, int y2)
    {
        svg.append(String.format(arrowTemplate,x1,y1,x2,y2));
    }

    public void addRect(double x, double y, double height, double width)
    {
        svg.append(String.format(rectTemplate, x, y, height, width));
    }

//    public void addLine(double x1, double y1, double x2, double y2 )
//    {
//        svg.append(String.format(rectTemplate, x, y, height, width));
//    }


    public void addSvg(SVG innerSVG)
    {
        svg.append(innerSVG.toString() +"/svg>");
    }

    @Override
    public String toString()
    {
        return svg.toString() + "</svg>" ;
    }


}
