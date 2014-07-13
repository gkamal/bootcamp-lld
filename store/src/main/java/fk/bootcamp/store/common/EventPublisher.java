package fk.bootcamp.store.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventPublisher {
  private List<EventListener> listeners = new ArrayList<EventListener>();

  public void registerListener(EventListener listener) {
    listeners.add(listener);
  }

  public void publishEvent(Event event) {
    for(EventListener listener: listeners) {
      listener.onEvent(event);
    }
  }
}
