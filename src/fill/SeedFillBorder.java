package fill;


import model.Point;
import model.Polygon;
import rasterize.Raster;

import java.awt.*;

public class SeedFillBorder implements Filler {
    Raster raster;
    Color targetColor;

    public SeedFillBorder(Raster raster) {
        this.raster = raster;
    }

    public void fill(Point point, Color targetColor) {
        Color rasterColor = raster.getPixelColor(point.x, point.y);
        if (!rasterColor.equals(targetColor)) {
            return;
        }

        Color fillColor = Color.PINK;

        raster.setPixel(point.x, point.y, fillColor.getRGB());
        fill(new Point(point.x + 1, point.y), targetColor);
        fill(new Point(point.x - 1, point.y), targetColor);
        fill(new Point(point.x, point.y + 1), targetColor);
        fill(new Point(point.x, point.y - 1), targetColor);
    }

    public Color getColor(int x, int y) {
        return raster.getPixelColor(x, y);
    }

    public void fill(Point point){
        Color rasterColor = raster.getPixelColor(point.x, point.y);
        Color borderColor = Color.yellow;
        Color fillColor = Color.PINK;
        if (rasterColor.equals(borderColor) || rasterColor.equals(fillColor)) {
            return;
        }

        raster.setPixel(point.x, point.y, fillColor.getRGB());
        fill(new Point(point.x + 1, point.y));
        fill(new Point(point.x - 1, point.y));
        fill(new Point(point.x, point.y + 1));
        fill(new Point(point.x, point.y - 1));
    }

    public void fill(Polygon polygon) {

    }
}
