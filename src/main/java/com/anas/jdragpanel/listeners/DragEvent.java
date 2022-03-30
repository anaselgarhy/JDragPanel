package com.anas.jdragpanel.listeners;

import javax.swing.*;

public class DragEvent {
    public static enum Type {
        START, END
    }

    private final Type type;
    private  final JComponent component;

    public DragEvent(Type type, JComponent component) {
        this.type = type;
        this.component = component;
    }

    public Type getType() {
        return type;
    }

    public JComponent getComponent() {
        return component;
    }
}
