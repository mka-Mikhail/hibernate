package org.mka;


import jakarta.persistence.EntityManager;
import org.mka.configs.ConfHibernate;
import org.mka.dao.BuyerDao;
import org.mka.dao.ProductDao;

public class Main {
    public static void main(String[] args) {
        ConfHibernate confHibernate = new ConfHibernate();
        EntityManager entityManager = confHibernate.getEntityManager();

        ProductDao productDao = new ProductDao();
        productDao.setEntityManager(entityManager);
        BuyerDao buyerDao = new BuyerDao();
        buyerDao.setEntityManager(entityManager);

        System.out.println("PRODUCTS:\n" + productDao.getAll());
        System.out.println("BUYERS:\n" + buyerDao.getAll());
        
        System.out.println();
        productDao.getProductById(10).getBuyers().forEach(System.out::println);
        System.out.println();
        buyerDao.getBuyerById(1).getProducts().forEach(System.out::println);
    }
}
