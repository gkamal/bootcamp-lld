package fk.bootcamp.store.orders;

import java.util.HashMap;
import java.util.Map;

public class OrderRepository {
  private Map<Long, Order> orders = new HashMap<Long, Order>();

  private static long next_order_id = 1;

  public void saveOrder(Order order) {
    order.setId(next_order_id);
    next_order_id = next_order_id + 1;
    orders.put(order.getId(), order);
  }
}
