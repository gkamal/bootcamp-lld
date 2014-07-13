package fk.bootcamp.store.product;

/**
 * Created by kamal.govindraj on 13/07/14.
 */
public interface ProductRepository {

  void updateProduct(Product product);

  Product fetchProduct(Long productId);
}
