package com.anas.jdragpanel.listeners;

import com.anas.jdragpanel.DragPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseComponentListener implements MouseListener, MouseMotionListener {
    private Point previousPoint, currentPoint;
    private final DragPanel dragPanel;

    public MouseComponentListener(DragPanel dragPanel) {
        previousPoint = new Point();
        currentPoint = new Point();
        this.dragPanel = dragPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            System.out.println("Right click");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        previousPoint = e.getPoint();
        dragPanel.addSelectedComponent((JComponent) e.getSource());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragPanel.removeSelectedComponent((JComponent) e.getSource());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currentPoint = e.getPoint();
        dragPanel.moveSelectedComponents(new Point(currentPoint.x - previousPoint.x, currentPoint.y - previousPoint.y));
        previousPoint = currentPoint;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
