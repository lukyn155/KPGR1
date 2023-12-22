package model;

public class Ellipse extends Polygon {
    private int centerX;
    private int centerY;
    private int semiMajorAxis;
    private int semiMinorAxis;

    public Ellipse(int centerX, int centerY, int semiMajorAxis, int semiMinorAxis) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.semiMajorAxis = semiMajorAxis;
        this.semiMinorAxis = semiMinorAxis;
        calculatePoints();
    }

    private void calculatePoints() {
        points.clear();
        int numberOfPoints = 360; // You can adjust this based on your requirements
        double angleIncrement = 2 * Math.PI / numberOfPoints;

        for (int i = 0; i < numberOfPoints; i++) {
            double angle = i * angleIncrement;
            int x = (int) (centerX + semiMajorAxis * Math.cos(angle));
            int y = (int) (centerY + semiMinorAxis * Math.sin(angle));
            points.add(new Point(x, y));
        }
    }
}
