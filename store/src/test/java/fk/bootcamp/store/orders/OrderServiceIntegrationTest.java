package fk.bootcamp.store.orders;


import org.hsqldb.jdbc.JDBCDataSource;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.sql.DataSource;

import fk.bootcamp.store.common.ConnectionUtil;
import fk.bootcamp.store.metrics.StubMetricsService;
import fk.bootcamp.store.product.ItemNotAvailableException;
import fk.bootcamp.store.product.Product;
import fk.bootcamp.store.product.ProductRepository;
import fk.bootcamp.store.product.internal.JdbcProductRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class OrderServiceIntegrationTest {

  private DataSource dataSource;
  private ProductRepository productRepository = new JdbcProductRepository();
  private OrderService orderService = new OrderService(new StubOrderRepository(),
                                                       productRepository,
                                                       new StubMetricsService());

  @Before
  public void setup() throws SQLException {
    dataSource = initializeDB();
    ConnectionUtil.open(dataSource);
  }

  public void tearDown() {
    ConnectionUtil.close();
  }

  @Test
  public void transactionRollbackTest() {

    Product p = productRepository.fetchProduct(1l);
    int initialQuantity = p.getAvailableQuantity();

    Order order = new Order();
    order.addItem( createOrderItem(1l, 5));
    order.addItem( createOrderItem(2l, 1));
    order.addItem( createOrderItem(3l, 2));

    try {
      orderService.processOrder(order);
      fail("Should have thrown an exception");
    } catch (ItemNotAvailableException e) {

    }
    p = productRepository.fetchProduct(1l);
    int finalQuantity = p.getAvailableQuantity();

    assertEquals(initialQuantity, finalQuantity);
  }


  private DataSource initializeDB() throws SQLException {
    JDBCDataSource dataSource = new JDBCDataSource();
    dataSource.setUrl("jdbc:hsqldb:mem:store");
    dataSource.setUser("SA");
    setupSchema(dataSource);
    setupData(dataSource);
    return dataSource;
  }

  private void setupData(DataSource dataSource) throws SQLException {
    Connection connection = dataSource.getConnection();

    Statement st = connection.createStatement();
    st.executeUpdate("insert into products (id,title,price,available_quantity) "
                     + "values(1,'Pragmatic programmer', 350, 10)");
    st.executeUpdate("insert into products (id,title,price,available_quantity) "
                     + "values(2,'Refactoring', 500, 2)");
    st.executeUpdate("insert into products (id,title,price,available_quantity) "
                     + "values(3,'Code complete', 500, 1)");

  }

  private void setupSchema(DataSource dataSource) throws SQLException {
    Connection connection = dataSource.getConnection();
    Statement statement = connection.createStatement();
    statement.executeUpdate("create table products (id BIGINT, title VARCHAR(255), "
                            + " price DECIMAL(9,2), available_quantity INTEGER)");
    statement.executeUpdate("create table orders (id BIGINT, address VARCHAR(255), "
                            + " price DECIMAL(9,2), order_date DATE)");
    statement.executeUpdate("create table order_items (id BIGINT, product_id BIGINT, "
                            + "quantity INTEGER)");
    connection.close();
  }

  private OrderItem createOrderItem(long productId, int quantity) {
    OrderItem orderItem = new OrderItem();
    orderItem.setProductId(productId);
    orderItem.setQuantity(quantity);
    return orderItem;
  }

  private Order defaultOrder() {
    Order order = new Order();
    order.setOrderDate(new Date());
    return order;
  }
}
