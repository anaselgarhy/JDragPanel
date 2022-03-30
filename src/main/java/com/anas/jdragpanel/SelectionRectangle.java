package com.anas.jdragpanel;

import java.awt.*;

public class SelectionRectangle extends Rectangle {
    private Color color;
    private Point startSelectionPoint, endSelectionPoint;

    public SelectionRectangle(Color color) {
        super();
        this.color = color;
        startSelectionPoint = new Point();
        endSelectionPoint = new Point();
    }

    public void draw(Graphics g) {
        g.setColor(color);
        int  width = Math.abs(endSelectionPoint.x - startSelectionPoint.x);
        int height = Math.abs(endSelectionPoint.y - startSelectionPoint.y);
        Point p = new Point(Math.min(startSelectionPoint.x, endSelectionPoint.x),
                Math.min(startSelectionPoint.y, endSelectionPoint.y));
        g.drawRect(p.x, p.y, width, height);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setStartPoint(Point startSelectionPoint) {
        this.startSelectionPoint = startSelectionPoint;
    }

    public void setCurrentPoint(Point currentPoint) {
        this.endSelectionPoint = currentPoint;
    }
}
