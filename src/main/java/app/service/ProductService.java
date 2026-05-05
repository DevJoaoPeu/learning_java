package app.service;

import app.model.Product;
import org.springframework.stereotype.Service;
import app.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository repository;

    public ProductService (ProductRepository productRepository) {
        repository = productRepository;
    }

    public Product create (Product product) {
        return repository.save(product);
    }

    public List<Product> findAll () {
        return this.repository.findAll();
    }

    public Product findById (Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Produto com id: " + id + " não encontrado"));
    }

    public List<Product> findByPrice (double price) {
        return repository.findByPrice(price);
    }

    public Product update (Long id, Product product) {
        Product item = repository.findById(id).orElseThrow(() -> new RuntimeException("Produto com id: " + id + " não encontrado"));

        item.setName(product.getName());
        item.setPrice(product.getPrice());
        item.setQuantity(product.getQuantity());

        return repository.save(item);
    }

    public void deleteById (Long id) {
        Product item = repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));

        repository.deleteById(id);
    }
}
