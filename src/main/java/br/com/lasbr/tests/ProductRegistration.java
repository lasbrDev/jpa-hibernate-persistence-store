package br.com.lasbr.tests;

import br.com.lasbr.dao.CategoryDAO;
import br.com.lasbr.dao.ProductDAO;

import br.com.lasbr.model.Category;
import br.com.lasbr.model.Product;
import br.com.lasbr.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

    public class ProductRegistration {

        public static void main(String[] args) {
            registerProduct();
            EntityManager em = JPAUtil.getEntityManager();
            ProductDAO productDao = new ProductDAO(em);
            Product p = productDao.searchById(1L);
            System.out.println(p.getPrice());

            List<Product> all = productDao.searchByCategoryName("SMARTPHONES");
            all.forEach(p2 -> System.out.println(p.getName()));

            BigDecimal priceProdutct = productDao.searchProductPriceWithName("Xiaomi Redmi");
            System.out.println("Price of the Product: " + priceProdutct);
        }

        private static void registerProduct() {
            Category smartphones = new Category("SMARTPHONES");
            Product smartphone = new Product("Xiaomy Redmi", "Very cool",
                    new BigDecimal("800"), smartphones);

            EntityManager em = JPAUtil.getEntityManager();
            ProductDAO productDao = new ProductDAO(em);
            CategoryDAO categoryDao = new CategoryDAO(em);

            em.getTransaction().begin();
            categoryDao.register(smartphones);
            productDao.register(smartphone);

            em.getTransaction().commit();
            em.close();
        }
    }
