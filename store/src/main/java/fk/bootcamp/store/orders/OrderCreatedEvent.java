package fk.bootcamp.store.orders;

import java.util.Date;

import fk.bootcamp.store.common.Event;

public class OrderCreatedEvent extends Event {
  private final Date eventDate = new Date();
  private final Order order;

  public OrderCreatedEvent(Order order) {
    this.order = order;
  }

  public Order getOrder() {
    return order;
  }
}
