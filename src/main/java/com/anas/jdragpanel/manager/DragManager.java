package com.anas.jdragpanel.manager;

import com.anas.jdragpanel.DragInterface;

import javax.swing.*;
import java.awt.*;

public interface DragManager {
    void moveComponent(JComponent component, Point targetPoint);
    void setDragContainer(DragInterface dragContainer);
}
