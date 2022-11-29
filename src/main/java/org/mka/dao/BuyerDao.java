package org.mka.dao;

import jakarta.persistence.EntityManager;
import org.mka.models.Buyer;

import java.util.List;

public class BuyerDao {

    private EntityManager entityManager;

    public BuyerDao() {
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Buyer getBuyerById(int id) {
        entityManager.getTransaction().begin();
        Buyer buyer = entityManager.createQuery(
                        "select b from Buyer b where b.id = :id", Buyer.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.getTransaction().commit();

        return buyer;
    }

    public List<Buyer> getAll() {
        entityManager.getTransaction().begin();
        List<Buyer> buyers = entityManager.createNamedQuery("Buyer.findAll", Buyer.class)
                .getResultList();
        entityManager.getTransaction().commit();

        return buyers;
    }

    public void deleteBuyerById(int id) {
        entityManager.getTransaction().begin();
        int deletedCount = entityManager.createNamedQuery("Buyer.deleteBuyerById")
                .setParameter("id", id)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }

    public void insertBuyer(Buyer buyer) {
        entityManager.getTransaction().begin();
        entityManager.persist(buyer);
        entityManager.getTransaction().commit();
    }
}
