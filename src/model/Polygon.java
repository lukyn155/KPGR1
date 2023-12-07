package model;

import java.util.ArrayList;

public class Polygon {
    //Zde bude list se zadanými body
    private ArrayList<Point> points = new ArrayList<>();

    public Point getPoint(int index) {
        return points.get(index);
    }

    public Polygon() {

    }
    public Polygon(ArrayList<Point> points) {
        this.points = points;
    }

    public void addPoint(Point point) {
        this.points.add(point);
    }

    public void replaceLastPoint(Point point) {
        this.points.set(points.size() - 1, point);
    }

    public ArrayList<Point> getPoints() {
        return points;
    }
//TODO

}
