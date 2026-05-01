package repository;

import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private List<Product> productsItems = new ArrayList<>();
    private Long nextId = 1L;

    public Product save(Product product) {
        product.setId(nextId);
        nextId++;

        this.productsItems.add(product);
        return product;
    }

    public List<Product> findAll() {
        return productsItems;
    }

    public Optional<Product> findById(Long id) {
        for (Product p : this.productsItems) {
            if (p.getId().equals(id)) {
                return Optional.of(p);
            }
        }

        return Optional.empty();
    }

    public Optional<Product> update(Long id, Product product) {
        for (Product p : productsItems) {
            if (p.getId().equals(id)) {
                p.setName(product.getName());
                p.setPrice(product.getPrice());
                p.setQuantity(product.getQuantity());

                return Optional.of(p);
            }
        }

        return Optional.empty();
    }

    public boolean deleteById(Long id) {
        return productsItems.removeIf(p -> p.getId().equals(id));
    }
}
