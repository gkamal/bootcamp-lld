package fk.bootcamp.store.common;

public interface EventListener<T> {

  public void onEvent(T event);
}
