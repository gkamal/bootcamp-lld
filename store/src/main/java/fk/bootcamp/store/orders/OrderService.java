package fk.bootcamp.store.orders;

import java.math.BigDecimal;

import fk.bootcamp.store.common.EventPublisher;
import fk.bootcamp.store.metrics.MetricsService;
import fk.bootcamp.store.product.Product;
import fk.bootcamp.store.product.ProductRepository;
import fk.bootcamp.store.shipping.ShipmentService;

public class OrderService extends EventPublisher {

  private ProductRepository productRepository;
  private OrderRepository orderRepository;
  private MetricsService metricsService;

  public OrderService(OrderRepository orderRepository,
                      ProductRepository productRepository,
                      MetricsService metricsService) {
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
    this.metricsService = metricsService;
  }

  public Order processOrder(Order order) {

    BigDecimal total = new BigDecimal("0");

    for (OrderItem item : order.getItems()) {
      Product product = productRepository.fetchProduct(item.getProductId());
      if (product == null) {
        throw new ItemNotFoundException("Item with id " + item.getProductId() + "not found");
      }
      product.reserveInventory(item.getQuantity());

      productRepository.updateProduct(product);
      total = total.add(product.computePriceFor(item.getQuantity()));
    }
    order.setPrice(total);

    orderRepository.saveOrder(order);

    publishEvent(new OrderCreatedEvent(order));

    return order;
  }

  public BigDecimal getTotalSales() {
    return metricsService.getTotalSales();
  }


}
