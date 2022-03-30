package com.anas.jdragpanel;

import com.anas.jdragpanel.listeners.DragEvent;
import com.anas.jdragpanel.listeners.DragListener;
import com.anas.jdragpanel.listeners.MouseComponentListener;
import com.anas.jdragpanel.manager.DragManager;
import com.anas.jdragpanel.manager.mangers.FreeDragManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class DragPanel extends JPanel implements DragInterface {
    private ArrayList<DragListener> dragListeners;
    private boolean dragEnabled;
    private ArrayList<JComponent> components, selectedElements;
    private DragManager dragManager;
    private Cursor dragCursor;
    private Border selectedBorder;

    public DragPanel() {
        dragListeners = new ArrayList<>();
        dragEnabled = true;
        components = new ArrayList<>();
        selectedElements = new ArrayList<>();
        dragManager = new FreeDragManager();
        dragCursor = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
        selectedBorder = BorderFactory.createLineBorder(Color.BLUE, 2);
    }

    @Override
    public void addDragListener(DragListener listener) {
        if (!dragListeners.contains(listener))
            dragListeners.add(listener);
    }

    @Override
    public void removeDragListener(DragListener listener) {
        dragListeners.remove(listener);
    }

    @Override
    public void setDragEnabled(boolean enabled) {
        dragEnabled = enabled;
    }

    @Override
    public boolean isDragEnabled() {
        return dragEnabled;
    }

    @Override
    public void setDragManager(DragManager manager) {
        this.dragManager = manager;
    }

    @Override
    public DragManager getDragManager() {
        return dragManager;
    }

    @Override
    public void moveComponent(JComponent component, Point targetPoint) {
        if (dragManager != null && dragEnabled) {
            if (components.contains(component)) {
                dragManager.moveComponent(component, targetPoint);
                super.repaint();
            } else {
                throw new IllegalArgumentException("Component not added to DragPanel");
            }
        }
    }

    @Override
    public void moveSelectedComponents(Point targetPoint) {
        if (dragEnabled && dragManager != null) {
            for (JComponent component : selectedElements) {
                dragManager.moveComponent(component, targetPoint);
                super.repaint();
            }
        }
    }

    @Override
    public void add(JComponent component) {
        this.addComponent(component);
        super.add(component);
    }

    @Override
    public void remove(JComponent component) {
        this.removeComponent(component);
        super.remove(component);
    }

    @Override
    public void setDragCursor(Cursor cursor) {
        dragCursor = cursor;
    }

    @Override
    public Cursor getDragCursor() {
        return dragCursor;
    }

    @Override
    public void setSelectedBorder(Border border) {
        selectedBorder = border;
    }

    @Override
    public Border getSelectedBorder() {
        return selectedBorder;
    }

    @Override
    public void setSelectedElements(JComponent[] elements) {
        selectedElements.clear();
        for (JComponent element : elements) {
            if (components.contains(element)) {
                this.addSelectedComponent(element);
            }
        }
    }

    @Override
    public JComponent[] getSelectedElements() {
        return selectedElements.toArray(new JComponent[selectedElements.size()]);
    }

    @Override
    public void addSelectedComponent(JComponent component) {
        if (components.contains(component)) {
            if (!selectedElements.contains(component)) {
                component.setBorder(selectedBorder);
                selectedElements.add(component);
            }
        }
    }

    @Override
    public void removeSelectedComponent(JComponent component) {
        if (selectedElements.contains(component)) {
            component.setBorder(null);
            selectedElements.remove(component);
        }
    }

    protected void addComponent(JComponent component) {
        if (!components.contains(component)) {
            MouseComponentListener listener = new MouseComponentListener(this);
            component.addMouseListener(listener);
            component.addMouseMotionListener(listener);
            components.add(component);
            super.add(component);
        }
    }

    protected void removeComponent(JComponent component) {
        if (components.contains(component)) {
            MouseComponentListener listener = new MouseComponentListener(this);
            component.addMouseListener(listener);
            component.addMouseMotionListener(listener);
            components.remove(component);
            super.remove(component);
        }
    }

    protected void fireDragStarted(DragEvent event) {
        for (DragListener listener : dragListeners)
            listener.dragStarted(event);
    }

    protected void fireDragEnded(DragEvent event) {
        for (DragListener listener : dragListeners)
            listener.dragEnded(event);
    }
}
