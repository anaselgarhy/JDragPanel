package com.anas.jdragpanel.listeners;

import com.anas.jdragpanel.DragPanel;

import java.awt.*;
import java.awt.event.*;

public class DragPanelMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {
    private final DragPanel dragPanel;
    private Point mouseDownPoint, mouseReleasedPoint;

    public DragPanelMouseListener(DragPanel dragPanel) {
        this.dragPanel = dragPanel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) { // left click
            dragPanel.removeSelectedComponents();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseDownPoint = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseReleasedPoint = e.getPoint();
        if (dragPanel.isMultiSelect()) {
            dragPanel.drawSelectionRectangle(new Point(), new Point());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragPanel.isMultiSelect()) {
            Point mousePoint = e.getPoint();
            dragPanel.drawSelectionRectangle(mouseDownPoint, mousePoint);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
}
