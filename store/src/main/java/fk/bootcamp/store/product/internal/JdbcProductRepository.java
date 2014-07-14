package fk.bootcamp.store.product.internal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fk.bootcamp.store.common.ConnectionUtil;
import fk.bootcamp.store.orders.ItemNotFoundException;
import fk.bootcamp.store.product.Product;
import fk.bootcamp.store.product.ProductRepository;

public class JdbcProductRepository implements ProductRepository {

  @Override public void updateProduct(Product product) {
    Connection connection = ConnectionUtil.get();
    try {
      PreparedStatement
          ps =
          connection.prepareStatement("update products set available_quantity = ? where id = ?");
      ps.setInt(1, product.getAvailableQuantity());
      ps.setLong(2, product.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override public Product fetchProduct(Long productId) {
    Connection connection = ConnectionUtil.get();
    try {
      PreparedStatement ps = connection.prepareStatement("select * from products where id = ?");
      ps.setLong(1, productId);
      ResultSet rs = ps.executeQuery();
      Product p = new Product();
      if (rs.next()) {
        p.setId(rs.getLong("id"));
        p.setTitle(rs.getString("title"));
        p.setPrice(rs.getBigDecimal("price"));
        p.setAvailableQuantity(rs.getInt("available_quantity"));
        return p;
      } else {
        throw new ItemNotFoundException("Product with id " + productId + " doesn't exist");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
