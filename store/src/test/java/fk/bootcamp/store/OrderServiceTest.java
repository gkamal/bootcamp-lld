package fk.bootcamp.store;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderServiceTest  {

  private OrderService orderService;

  @Before
  public void setup() {
    orderService = new OrderService();
  }

  @Test
  public void success() throws Exception {
    Order order = new Order();
    order.setOrderDate(new Date());

    OrderItem orderItem = new OrderItem();
    orderItem.setProductId(1L);
    orderItem.setQuantity(2);

    List<OrderItem> orderItems = new ArrayList<OrderItem>();
    orderItems.add(orderItem);

    order.setItems(orderItems);

    order = orderService.processOrder(order);

    assertEquals(new BigDecimal(1000), order.getPrice());
    assertEquals(new BigDecimal(1000), orderService.getTotalSales());
  }

  @Test(expected = ItemNotFoundException.class)
  public void itemNotFound() {
    Order order = new Order();
    order.setOrderDate(new Date());

    OrderItem orderItem = new OrderItem();
    orderItem.setProductId(2L);
    orderItem.setQuantity(1);

    List<OrderItem> orderItems = new ArrayList<OrderItem>();
    orderItems.add(orderItem);

    order.setItems(orderItems);

    orderService.processOrder(order);
  }

  @Test(expected = ItemNotAvailableException.class)
  public void itemNotAvailable() {
    Order order = new Order();
    order.setOrderDate(new Date());

    OrderItem orderItem = new OrderItem();
    orderItem.setProductId(1L);
    orderItem.setQuantity(3);

    List<OrderItem> orderItems = new ArrayList<OrderItem>();
    orderItems.add(orderItem);

    order.setItems(orderItems);

    orderService.processOrder(order);
  }

}
