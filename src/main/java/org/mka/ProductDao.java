package org.mka;

import jakarta.persistence.*;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDao {

    private EntityManager entityManager;

    public ProductDao() {
        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public Product getProductById(int id) {
        entityManager.getTransaction().begin();
        Product product = entityManager.createQuery(
                "select p from Product p where p.id = ?1", Product.class)
                    .setParameter(1, id)
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
