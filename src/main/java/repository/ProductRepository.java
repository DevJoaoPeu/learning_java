package repository;

import jakarta.persistence.*;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("learning_java");

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

    public Product findById(Long id) {
        EntityManager manager = factory.createEntityManager();
        String query = "select p from Product p where p.id = :id";

        TypedQuery<Product> queryBuilder = manager.createQuery(query, Product.class);
        queryBuilder.setParameter("id", id);
        Product item = queryBuilder.getSingleResult();
        manager.close();
        return item;
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
        return productsItems.removeIf(p -> p.getId().equals(id));
    }
}
