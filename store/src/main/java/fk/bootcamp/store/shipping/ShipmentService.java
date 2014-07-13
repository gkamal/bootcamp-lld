package fk.bootcamp.store.shipping;

import fk.bootcamp.store.orders.Order;

/**
 * Created by kamal.govindraj on 13/07/14.
 */
public interface ShipmentService {

  void scheduleShipment(Order order);
}
