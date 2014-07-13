package fk.bootcamp.store.product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class StubProductRepository implements ProductRepository {

  private Map<Long, Product> products = loadProducts();

  @Override public void updateProduct(Product product) {
    products.put(product.getId(), product);
  }

  @Override public Product fetchProduct(Long productId) {
    return products.get(productId);
  }

  public Map<Long, Product> loadProducts() {
    Map<Long, Product> products = new HashMap<Long, Product>();
    Product product = new Product();
    product.setId(1l);
    product.setAvailableQuantity(2);
    product.setPrice(new BigDecimal("500"));

    products.put(product.getId(), product);

    return products;
  }

}
