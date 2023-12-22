package fill;

import model.Point;
import model.Polygon;
import rasterize.Raster;
import rasterize.RasterBufferedImage;

import java.awt.*;

public class SeedFill implements Filler {
    //TODO Převezmu si barvu při kliknutí a podle ní porovnávám a obarvuji
    //Rekuzivně spouštět metodu a kontrolovat zda barva následujících 4 pixelů se rovná barvě kliku, pokud ne tak je nutné obarvit a spustit metodu znova, pokud ano nic nedělám

    Raster raster;
    Color targetColor;

    public SeedFill(Raster raster) {
        this.raster = raster;
    }

    @Override
    public void fill(Point point, Color targetColor) {
        Color rasterColor = raster.getPixelColor(point.x, point.y);
        if (!rasterColor.equals(targetColor)) {
            return;
        }

        Color fillColor = Color.GREEN;

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

    }

    public void fill(Polygon polygon) {

    }
}
