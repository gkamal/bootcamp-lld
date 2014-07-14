package fk.bootcamp.store.metrics;

import fk.bootcamp.store.common.EventListener;
import fk.bootcamp.store.orders.OrderCreatedEvent;

public class MetricsOrderEventListener implements EventListener<OrderCreatedEvent> {

  private final MetricsService metricsService;

  public MetricsOrderEventListener(MetricsService metricsService) {
    this.metricsService = metricsService;
  }

  @Override public void onEvent(OrderCreatedEvent event) {
    metricsService.updateMetrics(event.getOrder());
  }
}
