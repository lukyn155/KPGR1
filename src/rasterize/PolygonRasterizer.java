package rasterize;

import model.Line;
import model.Polygon;

import java.awt.*;
import java.util.ArrayList;

public class PolygonRasterizer {

    LineRasterizerTrivial lineRasterizer;
    Color color;

    public PolygonRasterizer(LineRasterizerTrivial lineRasterizer){
        this.lineRasterizer = lineRasterizer;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setColor(int color) {
        this.color = new Color(color);
    }

    public void rasterize(Polygon polygon) {
        drawPolygon(polygon);
    }
    public void rasterize(int x1, int y1, int x2, int y2, Color color) {

    }

    protected void drawPolygon(Polygon polygon) {
        System.out.println(polygon.getPoints());
        for (int i = 0; i < polygon.getPoints().size(); i++) {
            if (i == 0) {
                lineRasterizer.drawLine(polygon.getPoint(i));
            } else if (i == polygon.getPoints().size() - 1) {
                lineRasterizer.drawLine(polygon.getPoint(0).x, polygon.getPoint(0).y, polygon.getPoint(i).x, polygon.getPoint(i).y);
                lineRasterizer.drawLine(polygon.getPoint(i - 1).x, polygon.getPoint(i - 1).y, polygon.getPoint(i).x, polygon.getPoint(i).y);
            } else {
                lineRasterizer.drawLine(polygon.getPoint(i - 1).x, polygon.getPoint(i - 1).y, polygon.getPoint(i).x, polygon.getPoint(i).y);
            }
        }
    }
}
