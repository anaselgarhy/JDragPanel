package com.anas.jdragpanel.manager.mangers;

import javax.swing.*;
import java.awt.*;

public class CollisionDragManger extends FreeDragManager {
    private JComponent collisionComponent;
    @Override
    public void moveComponent(JComponent component, Point targetPoint) {
       super.moveComponent(component, targetPoint);
       while (checkCollision(component)) {
           moveCollisionComponent(component);
       }
    }

    private void moveCollisionComponent(JComponent component) {
        Rectangle componentBounds = component.getBounds();
        Rectangle collisionComponentBounds = collisionComponent.getBounds();

        Rectangle r = componentBounds.intersection(collisionComponentBounds);

        if (r.width > r.height) {
            if (componentBounds.x <= collisionComponentBounds.x) {
                collisionComponent.setLocation(collisionComponentBounds.x, collisionComponentBounds.y + 1);
            } else {
                collisionComponent.setLocation(collisionComponentBounds.x, collisionComponentBounds.y - 1);
            }
        } else if (r.height > 1) {
            if (componentBounds.y <= collisionComponentBounds.y) {
                collisionComponent.setLocation(collisionComponentBounds.x + 1, collisionComponentBounds.y);
            } else {
                collisionComponent.setLocation(collisionComponentBounds.x - 1, collisionComponentBounds.y);
            }
        }
    }

    private boolean checkCollision(JComponent component) {
        for (JComponent c : super.getDragContainer().getElements()) {
            if (!c.equals(component)) { // check if the component is not the same
                if (c.getBounds().intersects(component.getBounds())) {
                    collisionComponent = c;
                    return true; // if the component is in the same place
                }
            }
        }
        return false; // if the component is not in the same place
    }
}
