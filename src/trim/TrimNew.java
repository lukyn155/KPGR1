package trim;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import model.Polygon;
import model.Point;
public class TrimNew {
    public static Polygon clipPolygon(Polygon subjectPolygon, Polygon clipPolygon) {
        ArrayList<Point> outputList = subjectPolygon.getPoints();

        for (int i = 0; i < clipPolygon.getPoints().size(); i++) {
            int nextIndex = (i + 1) % clipPolygon.getPoints().size();
            Point clipStart = clipPolygon.getPoints().get(i);
            Point clipEnd = clipPolygon.getPoints().get(nextIndex);

            outputList = clip(outputList, clipStart, clipEnd);
        }

        return new Polygon(outputList);
    }

    public static ArrayList<Point> clip(ArrayList<Point> subjectPolygon, Point edgeStart, Point edgeEnd){
        ArrayList<Point> outputList = new ArrayList<>();

        // (ix,iy),(kx,ky) are the co-ordinate values of
        // the points
        for (int i = 0; i < subjectPolygon.size(); i++)
        {
            // i and k form a line in polygon
            int k = (i+1) % subjectPolygon.size();
            int ix = subjectPolygon.get(i).x, iy = subjectPolygon.get(i).y;
            int kx = subjectPolygon.get(k).x, ky = subjectPolygon.get(k).y;

            // Calculating position of first point
            // w.r.t. clipper line
//            int i_pos = (x2-x1) * (iy-y1) - (y2-y1) * (ix-x1);
            int i_pos = (edgeEnd.x-edgeStart.x) * (iy-edgeStart.y) - (edgeEnd.y - edgeStart.y) * (ix-edgeStart.x);

            // Calculating position of second point
            // w.r.t. clipper line
//            int k_pos = (x2-x1) * (ky-y1) - (y2-y1) * (kx-x1);
            int k_pos = (edgeEnd.x-edgeStart.x) * (ky-edgeStart.y) - (edgeEnd.y-edgeStart.y) * (kx-edgeStart.x);

            // Case 1 : When both points are inside
            if (i_pos < 0  && k_pos < 0)
            {
                //Only second point is added
                outputList.add(new Point(kx, ky));
//                new_points[new_poly_size][0] = kx;
//                new_points[new_poly_size][1] = ky;
//                new_poly_size++;
            }

            // Case 2: When only first point is outside
            else if (i_pos >= 0  && k_pos < 0)
            {
                // Point of intersection with edge
                // and the second point is added
                outputList.add(new Point(x_intersect(edgeStart.x, edgeStart.y, edgeEnd.x, edgeEnd.y, ix, iy, kx, ky), y_intersect(edgeStart.x, edgeStart.y, edgeEnd.x, edgeEnd.y, ix, iy, kx, ky)));
//                new_points[new_poly_size][0] = x_intersect(x1,
//                        y1, x2, y2, ix, iy, kx, ky);
//                new_points[new_poly_size][1] = y_intersect(x1,
//                        y1, x2, y2, ix, iy, kx, ky);
//                new_poly_size++;
                outputList.add(new Point(kx,ky));

//                new_points[new_poly_size][0] = kx;
//                new_points[new_poly_size][1] = ky;
//                new_poly_size++;
            }

            // Case 3: When only second point is outside
            else if (i_pos < 0  && k_pos >= 0)
            {
                //Only point of intersection with edge is added
                outputList.add(new Point(x_intersect(edgeStart.x, edgeStart.y, edgeEnd.x, edgeEnd.y, ix, iy, kx, ky), y_intersect(edgeStart.x, edgeStart.y, edgeEnd.x, edgeEnd.y, ix, iy, kx, ky)));

//                new_points[new_poly_size][0] = x_intersect(x1,
//                        y1, x2, y2, ix, iy, kx, ky);
//                new_points[new_poly_size][1] = y_intersect(x1,
//                        y1, x2, y2, ix, iy, kx, ky);
//                new_poly_size++;
            }

            // Case 4: When both points are outside
            else
            {
                //No points are added
            }
        }


        // Copying new points into original array
        // and changing the no. of vertices
//        poly_size = new_poly_size;
//        for (int i = 0; i < poly_size; i++)
//        {
//            poly_points[i][0] = new_points[i][0];
//            poly_points[i][1] = new_points[i][1];
//        }
        return outputList;
    }

    // Returns x-value of point of intersection of two
// lines
    private static int x_intersect(int x1, int y1, int x2, int y2,
                    int x3, int y3, int x4, int y4)
    {
        int num = (x1*y2 - y1*x2) * (x3-x4) -
                (x1-x2) * (x3*y4 - y3*x4);
        int den = (x1-x2) * (y3-y4) - (y1-y2) * (x3-x4);
        return num/den;
    }

    // Returns y-value of point of intersection of
// two lines
    private static int y_intersect(int x1, int y1, int x2, int y2,
                    int x3, int y3, int x4, int y4)
    {
        int num = (x1*y2 - y1*x2) * (y3-y4) -
                (y1-y2) * (x3*y4 - y3*x4);
        int den = (x1-x2) * (y3-y4) - (y1-y2) * (x3-x4);
        return num/den;
    }
}