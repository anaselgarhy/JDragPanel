package com.anas.jdragpanel;

import com.anas.jdragpanel.listeners.DragListener;
import com.anas.jdragpanel.manager.DragManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public interface DragInterface {
    void addDragListener(DragListener listener);
    void removeDragListener(DragListener listener);

    void setDragEnabled(boolean enabled);
    boolean isDragEnabled();

    void setMultiSelect(boolean multiSelect);
    boolean isMultiSelect();

    void setDragCursor(Cursor cursor);
    Cursor getDragCursor();

    void setSelectionRectangle(SelectionRectangle rectangle);

    void setSelectedBorder(Border border);
    Border getSelectedBorder();

    void setSelectedElements(JComponent[] element);
    JComponent[] getSelectedElements();

    JComponent[] getElements();

    void addSelectedComponent(JComponent component);
    void removeSelectedComponent(JComponent component);
    void removeSelectedComponents();

    void setSelectedPopupMenu(JPopupMenu menu);

    void setDragManager(DragManager manager);
    DragManager getDragManager();

    void moveComponent(JComponent component, Point targetPoint);
    void moveSelectedComponents(Point targetPoint);

    void add(JComponent component);
    void remove(JComponent component);
    void removeAll();

    void drawSelectionRectangle(Point startSelectionPoint, Point currentPoint);
}
