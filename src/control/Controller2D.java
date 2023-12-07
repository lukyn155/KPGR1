package control;

import rasterize.*;
import view.Panel;

import javax.swing.*;
import model.Polygon;
import model.Point;
import java.awt.*;
import java.awt.event.*;

public class Controller2D implements Controller {

    private final Panel panel;

    private int x,y;
    private LineRasterizer rasterizer;

    private LineRasterizerTrivial rasterizerTrivial;

    private PolygonRasterizer polygonRasterizer;
    private Polygon polygon;

    public Controller2D(Panel panel) {
        this.panel = panel;
        initObjects(panel.getRaster());
        initListeners(panel);
    }

    public void initObjects(Raster raster) {
        //rasterizer = new LineRasterizerGraphics(raster);
        rasterizerTrivial = new LineRasterizerTrivial(raster);
//        rasterizer = new LineRasterizerTrivial(raster);
        polygonRasterizer = new PolygonRasterizer(rasterizerTrivial);
        polygon = new Polygon();
     }

    @Override
    public void initListeners(Panel panel) {
        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isControlDown()) return;

                if (e.isShiftDown()) {
                    //TODO
                } else if (SwingUtilities.isLeftMouseButton(e)) {
//                    rasterizer.rasterize(x, y, e.getX(), e.getY(), Color.RED);
//                    x = e.getX();
//                    y = e.getY();

                    panel.clear();
                    x = e.getX();
                    y = e.getY();
                    Point point = new Point(x,y);
                    polygon.addPoint(point);
                    polygonRasterizer.rasterize(polygon);
                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    //TODO
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    //TODO
                }
                update();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isControlDown()) {
                    if (SwingUtilities.isLeftMouseButton(e)) {

                        //rasterizer.rasterize(panel.getRaster().getWidth() /2, panel.getRaster().getHeight()/2, e.getX(), e.getY(), Color.YELLOW);
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        //TODO
                    }
                }
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (e.isControlDown()) return;

                if (e.isShiftDown()) {
                    //TODO
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    panel.clear();
//                    rasterizer.rasterize(x, y, e.getX(), e.getY(), Color.YELLOW);
                    x = e.getX();
                    y = e.getY();
                    Point point = new Point(x,y);
                    polygon.replaceLastPoint(point);
                    polygonRasterizer.rasterize(polygon);
                    //TODO
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
                    //TODO
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
