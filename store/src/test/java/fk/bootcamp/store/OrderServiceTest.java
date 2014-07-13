package fk.bootcamp.store;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import fk.bootcamp.store.orders.ItemNotFoundException;
import fk.bootcamp.store.orders.Order;
import fk.bootcamp.store.orders.OrderItem;
import fk.bootcamp.store.orders.OrderService;
import fk.bootcamp.store.product.ItemNotAvailableException;

import static org.junit.Assert.assertEquals;

public class OrderServiceTest {

  private OrderService orderService;

  @Before
  public void setup() {
    orderService = new OrderService();
  }

  @Test
  public void success() throws Exception {
    Order order = defaultOrder();
    order.addItem(createOrderItem(1L, 2));

    order = orderService.processOrder(order);

    assertEquals(new BigDecimal(1000), order.getPrice());
    assertEquals(new BigDecimal(1000), orderService.getTotalSales());
  }


  @Test(expected = ItemNotFoundException.class)
  public void itemNotFound() {
    Order order = defaultOrder();
    order.addItem(createOrderItem(2L, 1));

    orderService.processOrder(order);
  }

  @Test(expected = ItemNotAvailableException.class)
  public void itemNotAvailable() {
    Order order = defaultOrder();
    order.addItem(createOrderItem(1L, 3));

    orderService.processOrder(order);
  }

  private OrderItem createOrderItem(long productId, int quantity) {
    OrderItem orderItem = new OrderItem();
    orderItem.setProductId(productId);
    orderItem.setQuantity(quantity);
    return orderItem;
  }

  private Order defaultOrder() {
    Order order = new Order();
    order.setOrderDate(new Date());
    return order;
  }


}
