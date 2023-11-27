package br.com.lasbr.tests;

import br.com.lasbr.dao.CategoryDAO;
import br.com.lasbr.dao.ProductDAO;
import br.com.lasbr.model.Category;
import br.com.lasbr.model.Product;
import br.com.lasbr.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

    public class CriteriaTest {

        public static void main(String[] args) {
            popularDatabase();
            EntityManager em = JPAUtil.getEntityManager();
            ProductDAO productDAO = new ProductDAO(em);
            List<Product> products = productDAO.searchByParametersWithCriteria(null, null,
                    LocalDate.now());
            products.forEach(p -> System.out.println(p.getName()));
        }
        private static void popularDatabase() {
            Category smartphones = new Category("SMARTPHONES");
            Category videogames = new Category("VIDEOGAMES");
            Category computing = new Category("COMPUTING");

            Product smartphone = new Product("Xiomi Redmi", "Very cool",
                    new BigDecimal("800"), smartphones);
            Product videogame = new Product("PS5", "PlayStation 5",
                    new BigDecimal("5000"), videogames);
            Product macbook = new Product("Macbook", "Macbook Pro Retina",
                    new BigDecimal("14000"), computing);

            EntityManager em = JPAUtil.getEntityManager();
            ProductDAO productDAO = new ProductDAO(em);
            CategoryDAO categoryDAO = new CategoryDAO(em);

            em.getTransaction().begin();

            categoryDAO.register(smartphones);
            categoryDAO.register(videogames);
            categoryDAO.register(computing);

            productDAO.register(smartphone);
            productDAO.register(videogame);
            productDAO.register(macbook);

            em.getTransaction().commit();
            em.close();
        }
    }
