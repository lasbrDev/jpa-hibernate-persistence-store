package tests;

import dao.CategoryDao;
import dao.ProductDao;
import model.Category;
import model.Product;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

    public class ProductRegistration {

        public static void main(String[] args) {
            registerProduct();
            EntityManager em = JPAUtil.getEntityManager();
            ProductDao productDao = new ProductDao(em);
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
            ProductDao productDao = new ProductDao(em);
            CategoryDao categoryDao = new CategoryDao(em);

            em.getTransaction().begin();
            categoryDao.register(smartphones);
            productDao.register(smartphone);

            em.getTransaction().commit();
            em.close();
        }
    }
