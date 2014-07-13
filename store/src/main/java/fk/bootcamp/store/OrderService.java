package fk.bootcamp.store;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class OrderService {

  private Map<Long,Product> products = loadProducts();
  private Map<Long, Order> orders = new HashMap<Long, Order>();

  private static long next_order_id = 1;

  private BigDecimal totalSales = new BigDecimal("0");

  public Order processOrder(Order order) {

    BigDecimal total  = new BigDecimal("0");

    for(OrderItem item: order.getItems()) {
      Product product = fetchProduct(item.getProductId());
      if (product == null) {
        throw new ItemNotFoundException("Item with id " + item.getProductId() + "not found");
      }
      product.reserveInventory(item.getQuantity());

      updateProduct(product);
      total = total.add(product.computePriceFor(item.getQuantity()));
    }
    order.setPrice(total);

    saveOrder(order);

    scheduleShipment(order);

    return order;
  }

  private void scheduleShipment(Order order) {

  }

  public BigDecimal getTotalSales() {
    return totalSales;
  }

  private void saveOrder(Order order) {
    order.setId(next_order_id);
    next_order_id = next_order_id + 1;
    orders.put(order.getId(), order);

    totalSales = totalSales.add(order.getPrice());
  }

  private void updateProduct(Product product) {
    products.put(product.getId(), product);
  }

  private Product fetchProduct(Long productId) {
    return products.get(productId);
  }

  private Map<Long, Product> loadProducts() {
    Map<Long, Product> products = new HashMap<Long, Product>();
    Product product = new Product();
    product.setId(1l);
    product.setAvailableQuantity(2);
    product.setPrice(new BigDecimal("500"));

    products.put(product.getId(), product);

    return products;
  }

}
