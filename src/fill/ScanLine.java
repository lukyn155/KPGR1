package fill;

import model.Line;
import model.Point;
import model.Polygon;
import rasterize.Raster;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScanLine implements Filler {
    Raster raster;
    ArrayList<Line> lines = new ArrayList<>();
    ArrayList<Line> tmpLines = new ArrayList<>();
    List<Integer> intersection = new ArrayList<>();
    int ymin, ymax;

    //TODO Při vykreslování polygonu se hned vybarvuje a to dělá tenhle objekt
    //Třídě se předává polygon
    //Iterace od shora dolů (Ymin a Ymax)
    //Vytvořit si třídu hrana (Point1, Point2) a pak seznam hran (Je nutné zjistit horizontální hranu -> mají oba body mají stejné Y a zahodit ji) a zkracovat hrany, tam kde se protínají dvě hrany
    //Průsečík y >= By a y < By zmenšení hrany
    //Při vytvoření scanline je nutné seřadit průsečíky od nejmenšího x po největší a vykreslit linii mezi nima
    //Po projití celého scanline je pak ještě nutné vykreslit samotný polygon pomocí polygon rasterizeru
    public ScanLine(Raster raster) {
        this.raster = raster;
    }

    @Override
    public void fill(Point point, Color color) {

    }

    public void fill(Point point) {

    }

    public void fill(Polygon polygon) {
        this.lines = polygon.getLines();
        for (Line line : lines) {
            if (line.y1 != line.y2) {
                Line newLine = new Line(line.x1, line.x2, line.y1, line.y2, Color.yellow.getRGB());
                if (newLine.y2 < newLine.y1) {
                    System.out.println("Před změnou: " + newLine.x1 + " " + newLine.x2 + " " + newLine.y1 + " " + newLine.y2);
                    newLine.changeDirection();
                }
                System.out.println("Po změně: " + newLine.x1 + " " + newLine.x2 + " " + newLine.y1 + " " + newLine.y2);
                newLine.calculate();
                System.out.println("K: " + newLine.k + "Q: " + newLine.q);
                tmpLines.add(newLine);
                if (ymin > newLine.y1) ymin = newLine.y1;
                if (ymax < newLine.y2) ymax = newLine.y2;
            }
        }
        for (int y = ymin; y <= ymax; y++) {
            intersection.clear();
            for (Line line : tmpLines) {
                if ((y >= line.y1) && (y < line.y2)) {
                    intersection.add(line.Intersect(y));
                }
            }
            Collections.sort(intersection);
            int i = 0;
            while (i < intersection.size() - 1) {
                int x1 = intersection.get(i);
                int x2 = intersection.get(i + 1);
                for (int x = x1; x <= x2; x++) {
                    raster.setPixel(x, y, Color.blue.getRGB());
                }
                i += 2;
            }
        }
    }
}
