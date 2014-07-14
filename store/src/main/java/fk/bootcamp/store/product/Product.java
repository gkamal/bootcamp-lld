package fk.bootcamp.store.product;

import java.math.BigDecimal;

public class Product {
  private Long id;
  private String title;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  private BigDecimal price;
  private int availableQuantity;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public int getAvailableQuantity() {
    return availableQuantity;
  }

  public void setAvailableQuantity(int availableQuantity) {
    this.availableQuantity = availableQuantity;
  }

  public void reserveInventory(int quantity) {
    if (availableQuantity >= quantity) {
      this.availableQuantity = availableQuantity - quantity;
    } else  {
      throw new ItemNotAvailableException("Item " + getTitle() + "not available");
    }
  }

  public BigDecimal computePriceFor(int quantity) {
    return getPrice().multiply(new BigDecimal(quantity));
  }
}
