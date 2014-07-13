package fk.bootcamp.store.metrics;

import java.math.BigDecimal;

import fk.bootcamp.store.orders.Order;

public class MetricsService {
  private BigDecimal totalSales = new BigDecimal("0");

  public void updateMetrics(Order order) {
    totalSales = totalSales.add(order.getPrice());
  }

  public BigDecimal getTotalSales() {
    return totalSales;
  }
}
