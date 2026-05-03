package repository;

import jakarta.persistence.*;
import model.Product;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductRepository {
    Dotenv dotenv = Dotenv.load();
    Map<String, Object> map = new HashMap<>();
    private EntityManagerFactory factory;

    public ProductRepository () {
        map.put("jakarta.persistence.jdbc.url", dotenv.get("POSTGRES_URL"));
        map.put("jakarta.persistence.jdbc.user", dotenv.get("POSTGRES_USER"));
        map.put("jakarta.persistence.jdbc.password", dotenv.get("POSTGRES_PASSWORD"));

        factory = Persistence.createEntityManagerFactory("learning_java", map);
    }

    public Product save(Product product) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        manager.persist(product);
        transaction.commit();

        manager.close();

        return product;
    }

    public List<Product> findAll() {
        EntityManager manager = factory.createEntityManager();
        String query = "select p from Product p";

        TypedQuery<Product> queryBuilder = manager.createQuery(query, Product.class);
        List<Product> items = queryBuilder.getResultList();
        manager.close();
        return items;
    }

    public Optional<Product> findById(Long id) {
        EntityManager manager = factory.createEntityManager();
        Product item = manager.find(Product.class, id);

        if(item == null) {
            return Optional.empty();
        }

        manager.close();
        return Optional.of(item);
    }

    public Optional<Product> update(Long id, Product product) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        Product item = manager.find(Product.class, id);

        if (item == null) {
            return Optional.empty();
        }

        item.setName(product.getName());
        item.setPrice(product.getPrice());
        item.setQuantity(product.getQuantity());

        transaction.begin();

        manager.merge(item);

        transaction.commit();
        manager.close();

        return Optional.of(item);
    }

    public boolean deleteById(Long id) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        Product item = manager.find(Product.class, id);

        if (item == null) {
            return false;
        }

        transaction.begin();
        manager.remove(item);

        transaction.commit();
        manager.close();

        return true;
    }
}
