package fk.bootcamp.store.product;

public class ProductRepositoryDecorator implements ProductRepository {
  private final ProductRepository delegate;

  public ProductRepositoryDecorator(ProductRepository delegate) {
    this.delegate = delegate;
  }

  @Override public void updateProduct(Product product) {
    long startTime = System.currentTimeMillis();
    try {
      delegate.updateProduct(product);
    } finally {
      long timeTaken = System.currentTimeMillis() - startTime;
      System.out.println("Method updateProduct took " + timeTaken + "ms");
    }
  }

  @Override public Product fetchProduct(Long productId) {
    long startTime = System.currentTimeMillis();
    try {
      return delegate.fetchProduct(productId);
    } finally {
      long timeTaken = System.currentTimeMillis() - startTime;
      System.out.println("Method fetchProduct took " + timeTaken + "ms");
    }
  }
}
