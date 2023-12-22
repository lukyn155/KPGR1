package fill;

import model.Point;
import model.Polygon;

import java.awt.*;

public interface Filler {
    void fill(Point point, Color color);
    void fill(Point point);
    void fill(Polygon polygon);

}
