package fk.bootcamp.store;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
  private Long id;
  private String address;
  private Date orderDate;
  private List<OrderItem> items = new ArrayList<OrderItem>();
  private BigDecimal price = new BigDecimal("0");

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public List<OrderItem> getItems() {
    return items;
  }

  public void setItems(List<OrderItem> items) {
    this.items = items;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  void addItem(OrderItem orderItem) {
    items.add(orderItem);
  }
}
