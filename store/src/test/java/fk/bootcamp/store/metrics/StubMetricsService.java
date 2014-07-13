package fk.bootcamp.store.metrics;

import java.math.BigDecimal;

import fk.bootcamp.store.orders.Order;

public class StubMetricsService implements MetricsService {
  private BigDecimal totalSales = new BigDecimal("0");

  @Override public void updateMetrics(Order order) {
    totalSales = totalSales.add(order.getPrice());
  }

  @Override public BigDecimal getTotalSales() {
    return totalSales;
  }
}
