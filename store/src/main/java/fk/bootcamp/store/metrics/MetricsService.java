package fk.bootcamp.store.metrics;

import java.math.BigDecimal;

import fk.bootcamp.store.orders.Order;

/**
 * Created by kamal.govindraj on 13/07/14.
 */
public interface MetricsService {

  void updateMetrics(Order order);

  BigDecimal getTotalSales();
}
