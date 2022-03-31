package com.anas.jdragpanel.manager;

import com.anas.jdragpanel.DragInterface;

public abstract class DragMangerAdaptor  implements DragManager {
    private DragInterface dragContainer;

    public DragMangerAdaptor(DragInterface dragContainer) {
        this.dragContainer = dragContainer;
    }

    public DragMangerAdaptor() {
        dragContainer = null;
    }

    public DragInterface getDragContainer() {
        return dragContainer;
    }

    @Override
    public void setDragContainer(DragInterface dragContainer) {
        this.dragContainer = dragContainer;
    }
}
