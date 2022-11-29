package org.mka;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();

        /*System.out.println(productDao.getProductById(1));
        System.out.println();*/

        List<Product> products = productDao.getAll();
        products.forEach(System.out::println);

        //productDao.deleteProductById(3);

        Product product = new Product("Arduino", 500);
        productDao.insertProduct(product);

        productDao.getAll().forEach(System.out::println);
    }
}
