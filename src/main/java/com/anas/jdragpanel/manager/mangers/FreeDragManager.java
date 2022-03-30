package com.anas.jdragpanel.manager.mangers;

import com.anas.jdragpanel.DragInterface;
import com.anas.jdragpanel.manager.DragManager;

import javax.swing.*;
import java.awt.*;

public class FreeDragManager implements DragManager {
    @Override
    public void moveComponent(JComponent component, Point targetPoint) {
        Point point = component.getLocation();
        point.translate(targetPoint.x, targetPoint.y);
        component.setLocation(point);
    }

    @Override
    public void setDragContainer(DragInterface dragContainer) {

    }
}
