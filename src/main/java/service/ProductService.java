package service;

import model.Product;
import repository.ProductRepository;

import java.util.List;

public class ProductService {
    private ProductRepository repository = new ProductRepository();

    public ProductService () {}

    public Product create (Product product) {
        return repository.save(product);
    }

    public List<Product> findAll () {
        return this.repository.findAll();
    }

    public Product findById (Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));
    }

    public Product update (Long id, Product product) {
        return repository.update(id, product).orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));
    }

    public boolean deleteById (Long id) {
        return repository.deleteById(id);
    }
}
