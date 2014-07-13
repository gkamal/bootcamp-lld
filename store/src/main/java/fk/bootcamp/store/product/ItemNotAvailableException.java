package fk.bootcamp.store.product;

public class ItemNotAvailableException extends RuntimeException {

  public ItemNotAvailableException(String message) {
    super(message);
  }
}
