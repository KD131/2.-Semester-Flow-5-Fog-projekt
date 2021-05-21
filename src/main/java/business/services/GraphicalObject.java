package business.services;

public abstract class GraphicalObject {
    double x1, y1, x2, y2;

    void moveTo(double newX1, double newY1){
        x1 = newX1;
        y1 = newY1;
    }

    abstract void draw();

}
