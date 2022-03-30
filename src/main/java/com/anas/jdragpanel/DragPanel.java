package com.anas.jdragpanel;

import com.anas.jdragpanel.listeners.DragEvent;
import com.anas.jdragpanel.listeners.DragListener;
import com.anas.jdragpanel.listeners.DragPanelMouseListener;
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
    private JPopupMenu selectedPopupMenu;
    private SelectionRectangle selectionRectangle;
    private boolean multiSelection;
    private DragPanelMouseListener mouseListener;

    public DragPanel() {
        this(true);
    }

    public DragPanel(boolean isDoubleBuffered) {
        super(null, isDoubleBuffered);
        initialize();
        super.addMouseListener(mouseListener);
        super.addMouseMotionListener(mouseListener);
        super.addMouseWheelListener(mouseListener);
    }

    private void initialize() {
        dragListeners = new ArrayList<>();
        setDragEnabled(true);
        components = new ArrayList<>();
        selectedElements = new ArrayList<>();
        setDragManager(new FreeDragManager());
        setDragCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        setSelectedBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        setSelectedPopupMenu(new JPopupMenu());
        setSelectionRectangle(new SelectionRectangle(Color.BLUE));
        setMultiSelect(true);
        mouseListener = new DragPanelMouseListener(this);
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
    public void setMultiSelect(boolean multiSelect) {
        this.multiSelection = multiSelect;
    }

    @Override
    public boolean isMultiSelect() {
        return multiSelection;
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
                fireDragStarted(new DragEvent(DragEvent.Type.START, component));
            } else {
                throw new IllegalArgumentException("Component not added to DragPanel");
            }
        }
    }

    @Override
    public void moveSelectedComponents(Point targetPoint) {
        for (JComponent component : selectedElements) {
            this.moveComponent(component, targetPoint);
        }
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
    public void setSelectionRectangle(SelectionRectangle rectangle) {
        selectionRectangle = rectangle;
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

    @Override
    public void removeSelectedComponents() {
        for (JComponent component : selectedElements) {
            this.removeSelectedComponent(component);
        }
    }

    @Override
    public void setSelectedPopupMenu(JPopupMenu menu) {
        selectedPopupMenu = menu;
    }

    @Override
    public void add(JComponent component) {
        component.setComponentPopupMenu(selectedPopupMenu);
        this.addComponent(component);
        super.add(component);
    }

    protected void addComponent(JComponent component) {
        if (!components.contains(component)) {
            MouseComponentListener listener = new MouseComponentListener(this);
            component.addMouseListener(listener);
            component.addMouseMotionListener(listener);
            component.setCursor(getDragCursor());
            components.add(component);
        }
    }

    @Override
    public void remove(JComponent component) {
        component.setComponentPopupMenu(null);
        this.removeComponent(component);
        super.remove(component);
    }

    @Override
    public void drawSelectionRectangle(Point startSelectionPoint, Point currentPoint) {
        if (selectionRectangle != null) {
            selectionRectangle.setStartPoint(startSelectionPoint);
            selectionRectangle.setCurrentPoint(currentPoint);
            super.repaint();
        }
        checkTouchingComponents(startSelectionPoint, currentPoint);
    }

    private void checkTouchingComponents(Point startSelectionPoint, Point currentPoint) {
        int width = Math.abs(currentPoint.x - startSelectionPoint.x);
        int height = Math.abs(currentPoint.y - startSelectionPoint.y);
        Point startPoint = new Point(Math.min(startSelectionPoint.x, currentPoint.x), Math.min(startSelectionPoint.y, currentPoint.y));
        for (JComponent component : components) {
            if (component.getBounds().intersects(new Rectangle(startPoint.x, startPoint.y,
                    width, height))) {
                addSelectedComponent(component);
            } else {
                removeSelectedComponent(component);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (selectionRectangle != null && isMultiSelect()) {
            selectionRectangle.draw(g);
        }
    }

    protected void removeComponent(JComponent component) {
        if (components.contains(component)) {
            MouseComponentListener listener = new MouseComponentListener(this);
            component.addMouseListener(listener);
            component.addMouseMotionListener(listener);
            components.remove(component);
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
