package rasterize;


import java.awt.*;
import model.Point;

public class DottedLineRasterizer extends LineRasterizer{

    public DottedLineRasterizer(Raster raster) {
        super(raster);
    }

    public void drawLine(Point point) {
        raster.setPixel(point.x, point.y, Color.yellow.getRGB());
    }
    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        float x3;
        if(x2 - x1 == 0) {
            x3 = 1;
        } else {
            x3 = x2 - x1;
        }
        float k = (y2 - y1) / (x3);
        float q = y1 - k * x1;

        if (x1 > x2) {
            int tmp = x1;
            x1 = x2;
            x2 = tmp;
        }

        if (y1 > y2) {
            int tmp = y1;
            y1 = y2;
            y2 = tmp;
        }

        int dotCount = 0;
        final int MAX_COUNT = 5;
        if (Math.abs(k) < 1.0) {
            do {
                float y = k * x1 + q;
                if(dotCount == MAX_COUNT) {
                    dotCount = 0;
                    raster.setPixel(x1, Math.round(y), Color.yellow.getRGB());
                } else {
                    dotCount++;
                }

                x1++;
            } while (x1 <= x2);
        } else {
            do {
                float x = (y1 - q) / k;
                if(dotCount == MAX_COUNT) {
                    dotCount = 0;
                    raster.setPixel(Math.round(x), y1, Color.yellow.getRGB());
                } else {
                    dotCount++;
                }

                y1++;
            } while (y1 <= y2);
        }

    }
}
