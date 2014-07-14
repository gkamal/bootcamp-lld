package fk.bootcamp.store.orders;

import java.util.Date;

public class OrderCreatedEvent {
  private final Date eventDate = new Date();
  private final Order order;

  public OrderCreatedEvent(Order order) {
    this.order = order;
  }

  public Order getOrder() {
    return order;
  }
}
