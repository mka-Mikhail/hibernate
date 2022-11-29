package org.mka.dao;

import jakarta.persistence.EntityManager;
import org.mka.models.Product;

import java.util.List;

public class ProductDao {

    private EntityManager entityManager;

    public ProductDao() {
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Product getProductById(int id) {
        entityManager.getTransaction().begin();
        Product product = entityManager.createQuery(
                "select p from Product p where p.id = :id", Product.class)
                    .setParameter("id", id)
                    .getSingleResult();
        entityManager.getTransaction().commit();

        return product;
    }

    public List<Product> getAll() {
        entityManager.getTransaction().begin();
        List<Product> products = entityManager.createNamedQuery("Product.findAll", Product.class)
                .getResultList();
        entityManager.getTransaction().commit();

        return products;
    }

    public void deleteProductById(int id) {
        entityManager.getTransaction().begin();
        int deletedCount = entityManager.createNamedQuery("Product.deleteProductById")
                .setParameter("id", id)
                        .executeUpdate();
        entityManager.getTransaction().commit();
    }

    public void insertProduct(Product product) {
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }
}
