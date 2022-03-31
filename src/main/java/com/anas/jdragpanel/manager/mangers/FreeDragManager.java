package com.anas.jdragpanel.manager.mangers;

import com.anas.jdragpanel.manager.DragMangerAdaptor;

import javax.swing.*;
import java.awt.*;

public class FreeDragManager extends DragMangerAdaptor {
    @Override
    public void moveComponent(JComponent component, Point targetPoint) {
        Point point = component.getLocation();
        point.translate(targetPoint.x, targetPoint.y);
        component.setLocation(point);
    }
}
