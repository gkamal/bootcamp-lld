package fk.bootcamp.store.orders;

public class ItemNotFoundException extends RuntimeException {

  public ItemNotFoundException(String message) {
    super(message);
  }
}
