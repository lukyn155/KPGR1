package control;

import fill.ScanLine;
import fill.SeedFill;
import fill.SeedFillBorder;
import model.Ellipse;
import rasterize.*;
import trim.TrimNew;
import view.Panel;

import javax.swing.*;

import model.Polygon;
import model.Point;
import model.Rectangle;

import java.awt.*;
import java.awt.event.*;


public class Controller2D implements Controller {

    private final Panel panel;
    private int x, y;
    private Color color;
    private SeedFill seedFill;
    private SeedFillBorder seedFillBorder;
    private LineRasterizer rasterizer;
    private FilledLineRasterizer rasterizerTrivial;
    private DottedLineRasterizer dottedLineRasterizer;
    private PolygonRasterizer polygonRasterizer;
    private Polygon polygon;
    private Polygon konvPolygon;
    private Rectangle rectangle;
    private Rectangle eliRectangle;
    private Ellipse ellipse;

    private ScanLine scanLine;

    public Controller2D(Panel panel) {
        this.panel = panel;
        initObjects(panel.getRaster());
        initListeners(panel);
    }

    public void initObjects(Raster raster) {
        //rasterizer = new LineRasterizerGraphics(raster);
        rasterizerTrivial = new FilledLineRasterizer(raster);
        dottedLineRasterizer = new DottedLineRasterizer(raster);
//        rasterizer = new FilledLineRasterizer(raster);
        polygonRasterizer = new PolygonRasterizer(rasterizerTrivial, dottedLineRasterizer);
        scanLine = new ScanLine(raster);
        polygon = new Polygon();
        konvPolygon = new Polygon();
        rectangle = new Rectangle();
        eliRectangle = new Rectangle();

        seedFill = new SeedFill(raster);
        seedFillBorder = new SeedFillBorder(raster);
    }

    @Override
    public void initListeners(Panel panel) {
        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isControlDown()) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        x = e.getX();
                        y = e.getY();
                        Point point = new Point(x, y);
                        seedFillBorder.fill(point);
                    } else if (SwingUtilities.isLeftMouseButton(e)) {
                        x = e.getX();
                        y = e.getY();
                        Point point = new Point(x, y);
                        konvPolygon.addPoint(point);
                        polygonRasterizer.rasterize(konvPolygon);
                    }
                } else {
                    if (e.isShiftDown()) {
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            x = e.getX();
                            y = e.getY();
                            Point point = new Point(x, y);
                            if (rectangle.setPoint(point)) {
                                polygonRasterizer.rasterize(rectangle);
                            }
                        } else if (SwingUtilities.isRightMouseButton(e)) {
                            x = e.getX();
                            y = e.getY();
                            Point point = new Point(x, y);
                            if (rectangle.setPoint(point)) {
                                polygonRasterizer.rasterize(rectangle);
                                int centerX = 0;
                                int centerY = 0;
                                for (Point testPoint : rectangle.getPoints()) {
                                    centerX += testPoint.x;
                                    centerY += testPoint.y;
                                }
                                centerX = centerX / rectangle.getPoints().size();
                                centerY = centerY / rectangle.getPoints().size();

                                int semiMajorAxis = Math.abs((rectangle.getPoint(2).x - rectangle.getPoint(0).x) / 2);
                                int semiMinorAxis = Math.abs((rectangle.getPoint(2).y - rectangle.getPoint(0).y) / 2);
//
                                ellipse = new Ellipse(centerX, centerY, semiMajorAxis, semiMinorAxis);
                                polygonRasterizer.rasterize(ellipse);
                            }
                        }
                    } else if (SwingUtilities.isLeftMouseButton(e)) {
                        x = e.getX();
                        y = e.getY();
                        Point point = new Point(x, y);
                        polygon.addPoint(point);
                        polygonRasterizer.rasterize(polygon);
                    } else if (SwingUtilities.isMiddleMouseButton(e)) {
                        //TODO
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        x = e.getX();
                        y = e.getY();
                        Point point = new Point(x, y);
                        color = seedFill.getColor(x, y);
                        seedFill.fill(point, color);
                    }
                }
                update();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isControlDown()) {
                    if (SwingUtilities.isLeftMouseButton(e)) {

                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        //TODO
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    panel.clear();
                    polygonRasterizer.rasterize(polygon);
                    polygonRasterizer.rasterize(rectangle);
                    polygonRasterizer.rasterize(konvPolygon);
                }
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (e.isControlDown()) return;

                if (e.isShiftDown()) {
                    panel.clear();

                    x = e.getX();
                    y = e.getY();

                    Point lastPoint = polygon.getBeforeLastPoint();
                    Point point = new Point(x, y);

                    int dx = Math.abs(point.x - lastPoint.x);
                    int dy = Math.abs(point.y - lastPoint.y);

                    if (dx > dy) {
                        if (dx / 2 < dy) {
                            point.setX(lastPoint.x + (point.x > lastPoint.x ? dy : -dy));
                            point.setY(lastPoint.y + (point.y > lastPoint.y ? dy : -dy));
                        } else {
                            point.setY(lastPoint.y);
                        }
                    } else if (dy > dx) {
                        if (dy / 2 < dx) {
                            point.setX(lastPoint.x + (point.x > lastPoint.x ? dx : -dx));
                            point.setY(lastPoint.y + (point.y > lastPoint.y ? dx : -dx));
                        } else {
                            point.setX(lastPoint.x);
                        }
                    } else {
                        point.setX(lastPoint.x + (point.x > lastPoint.x ? dx : -dx));
                        point.setY(lastPoint.y + (point.y > lastPoint.y ? dy : -dy));
                    }

                    polygon.replaceLastPoint(point);
                    polygonRasterizer.rasterize(polygon);
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    panel.clear();
                    x = e.getX();
                    y = e.getY();
                    Point point = new Point(x, y);
                    polygon.replaceLastPoint(point);
                    polygonRasterizer.rasterizeDotted(polygon);
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    //TODO
                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    //TODO
                }
                update();
            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // na klávesu C vymazat plátno
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    panel.clear();
                    polygon = new Polygon();
                    rectangle = new Rectangle();
                    konvPolygon = new Polygon();
                } else if (e.getKeyCode() == KeyEvent.VK_T) {
                    panel.clear();
                    polygon = TrimNew.clipPolygon(polygon, konvPolygon);
                    konvPolygon = new Polygon();
                    scanLine.fill(polygon);
                    polygonRasterizer.rasterize(polygon);
                } else if (e.getKeyCode() == KeyEvent.VK_F){
                    panel.clear();
                    polygonRasterizer.rasterize(polygon);
                }
            }
        });

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel.resize();
                initObjects(panel.getRaster());
            }
        });
    }

    private void update() {
//        panel.clear();
        //TODO
        panel.repaint();
    }

    private void hardClear() {
        panel.clear();
    }

}
