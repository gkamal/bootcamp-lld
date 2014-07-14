package fk.bootcamp.store.orders;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import fk.bootcamp.store.metrics.MetricsOrderEventListener;
import fk.bootcamp.store.metrics.StubMetricsService;
import fk.bootcamp.store.product.ItemNotAvailableException;
import fk.bootcamp.store.product.ProductRepositoryDecorator;
import fk.bootcamp.store.product.StubProductRepository;
import fk.bootcamp.store.shipping.ShipmentOrderEventListener;
import fk.bootcamp.store.shipping.StubShipmentService;

import static org.junit.Assert.assertEquals;

public class OrderServiceTest {

  private OrderService orderService;

  @Before
  public void setup() {
    StubShipmentService shipmentService = new StubShipmentService();
    StubMetricsService metricsService = new StubMetricsService();
    orderService = new OrderService(new StubOrderRepository(),
                                    new ProductRepositoryDecorator(new StubProductRepository()),
                                    metricsService);

    orderService.registerListener(new MetricsOrderEventListener(metricsService));
    orderService.registerListener(new ShipmentOrderEventListener(shipmentService));
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
