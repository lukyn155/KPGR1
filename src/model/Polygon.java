package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Polygon {
    //Zde bude list se zadanými body
    protected ArrayList<Point> points = new ArrayList<>();

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

    public Point getBeforeLastPoint() {
        return points.get(points.size() - 2);
    }

    public void replaceLastPoint(Point point) {
        this.points.set(points.size() - 1, point);
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public ArrayList<Line> getLines() {
        ArrayList<Line> lines = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            int nextIndex = (i + 1) % points.size();
            lines.add(
                    new Line(
                            points.get(i).x,
                            points.get(nextIndex).x,
                            points.get(i).y,
                            points.get(nextIndex).y,
                            Color.yellow.getRGB()));
//            System.out.println(points.get(i).x + " " + points.get(nextIndex).x + " " + points.get(i).y + " " + points.get(nextIndex).y);
        }
        return lines;
    }
}
