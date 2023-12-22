package model;

import java.util.ArrayList;

public class Line {

    public int  x1, x2, y1, y2;
    public float k,q;
    public int color;

    public Line(int x1, int x2, int y1, int y2, int color) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;
    }

    public void changeDirection() {
        int xtmp = this.x1;
        this.x1 = this.x2;
        this.x2 = xtmp;
        int ytmp = this.y1;
        this.y1 = this.y2;
        this.y2 = ytmp;
    }

    public void calculate() {
        this.k = (float) (x2 - x1) / (y2 - y1);
        this.q = x1 - k * y1;
    }
    public int Intersect(int y) {
         return (int) (k*y + q);
    }

//    public Line(Point p1, Point p2, int color, int x1) {
//        //TODO
//        this.x1 = x1;
//    }

    //TODO

}
