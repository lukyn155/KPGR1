package model;

import java.util.ArrayList;

public class Rectangle extends Polygon {
    public ArrayList<Point> calculatePoints() {
        ArrayList<Point> firstPoints = new ArrayList<>();
        int firstX = points.get(0).x;
        int firstY = points.get(0).y;
        int secondX = points.get(1).x;
        int secondY = points.get(1).y;
        firstPoints.add(points.get(0));
        firstPoints.add(new Point(firstX, secondY));
        firstPoints.add(points.get(1));
        firstPoints.add(new Point(secondX, firstY));
        points = firstPoints;
        return points;
    }

    public boolean setPoint(Point point) {
        if (points.size() >= 2) {
            return true;
        } else if (points.size() == 1) {
            super.addPoint(point);
            calculatePoints();
            return true;
        } else {
            super.addPoint(point);
            return false;
        }
    }
}
