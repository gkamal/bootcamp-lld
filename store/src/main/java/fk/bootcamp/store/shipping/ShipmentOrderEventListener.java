package fk.bootcamp.store.shipping;

import fk.bootcamp.store.common.EventListener;
import fk.bootcamp.store.orders.Order;
import fk.bootcamp.store.orders.OrderCreatedEvent;

public class ShipmentOrderEventListener implements EventListener<OrderCreatedEvent> {

  private final ShipmentService shipmentService;

  public ShipmentOrderEventListener(ShipmentService shipmentService) {
    this.shipmentService = shipmentService;
  }

  @Override public void onEvent(OrderCreatedEvent event) {
    shipmentService.scheduleShipment(event.getOrder());
  }
}
