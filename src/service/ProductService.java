package service;

import model.Product;
import repository.ProductRepository;

import java.util.List;

public class ProductService {
    private ProductRepository repository = new ProductRepository();

    public ProductService () {
        repository.save(new Product(null, "Notebook", 4500.00, 10));
        repository.save(new Product(null, "TV Smart", 100.00, 5));
        repository.save(new Product(null, "Bicicleta", 650.00, 1));
    }

    public Product create (Product product) {
        validateProduct(product);
        return repository.save(product);
    }

    public List<Product> findAll () {
        return this.repository.findAll();
    }

    public Product findById (Long id) {
        validateId(id);

        return repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));
    }

    public Product update (Long id, Product product) {
        validateId(id);
        validateProduct(product);

        return repository.update(id, product).orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));
    }

    public boolean deleteById (Long id) {
        validateId(id);
        return repository.deleteById(id);
    }

    private void validateId(Long id) {
        if(id == null || id <= 0) {
            throw new IllegalArgumentException("Id não pode ser vazio");
        }
    }

    private void validateProduct(Product product) {
        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }

        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Preço não pode ser vazio");
        }

        if (product.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser vazia");
        }
    }
}
