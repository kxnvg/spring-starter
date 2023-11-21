package org.kxnvg.listener.entity;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class EntityListener {

    @EventListener
    @Order(10)
    public void acceptEntityRead(EntityEvent event) {
        System.out.println("Entity: " + event);
    }
}
