package rasterize;

import model.Polygon;

import java.awt.*;

public class PolygonRasterizer {

    FilledLineRasterizer lineRasterizer;
    DottedLineRasterizer dottedLineRasterizer;
    Color color;

    public PolygonRasterizer(FilledLineRasterizer lineRasterizer, DottedLineRasterizer dottedLineRasterizer){
        this.lineRasterizer = lineRasterizer;
        this.dottedLineRasterizer = dottedLineRasterizer;
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
    
    public void rasterizeDotted(Polygon polygon) {
        drawDottedPolygon(polygon);
    }
    public void rasterize(int x1, int y1, int x2, int y2, Color color) {

    }

    protected void drawPolygon(Polygon polygon) {
//        System.out.println(polygon.getPoints());
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

    protected void drawDottedPolygon(Polygon polygon) {
//        System.out.println(polygon.getPoints());
        for (int i = 0; i < polygon.getPoints().size(); i++) {
            if (i == 0) {
                lineRasterizer.drawLine(polygon.getPoint(i));
            } else if (i == polygon.getPoints().size() - 1) {
                dottedLineRasterizer.drawLine(polygon.getPoint(0).x, polygon.getPoint(0).y, polygon.getPoint(i).x, polygon.getPoint(i).y);
                dottedLineRasterizer.drawLine(polygon.getPoint(i - 1).x, polygon.getPoint(i - 1).y, polygon.getPoint(i).x, polygon.getPoint(i).y);
            } else {
                lineRasterizer.drawLine(polygon.getPoint(i - 1).x, polygon.getPoint(i - 1).y, polygon.getPoint(i).x, polygon.getPoint(i).y);
            }
        }
    }
}
