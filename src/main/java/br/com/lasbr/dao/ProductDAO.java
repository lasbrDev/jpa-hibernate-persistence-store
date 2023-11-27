package br.com.lasbr.dao;

import br.com.lasbr.model.Product;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

    public class ProductDAO {

        private EntityManager em;

        public ProductDAO(EntityManager em) {
            this.em = em;
        }

        public void register(Product product) {
            this.em.persist(product);
        }

        public void update(Product product) {
            this.em.merge(product);
        }

        public void remove(Product product) {
            product = em.merge(product);
            this.em.remove(product);
        }

        public Product searchById(Long id) {
            return em.find(Product.class, id);
        }

        public List<Product> searchAll() {
            String jpql = "SELECT p FROM Product p";
            return em.createQuery(jpql, Product.class).getResultList();
        }

        public List<Product> searchByName(String name) {
            String jpql = "SELECT p FROM Product p WHERE p.name = :name";
            return em.createQuery(jpql, Product.class).setParameter("name", name).getResultList();
        }

        public List<Product> searchByCategoryName(String name) {
            return em.createNamedQuery("Product.productsByCategory", Product.class)
                    .setParameter("name", name).getResultList();
        }

        public BigDecimal searchProductPriceWithName(String name) {
            String jpql = "SELECT p.price FROM Product p WHERE p.name = :name";
            return em.createQuery(jpql, BigDecimal.class).setParameter("name", name).getSingleResult();
        }
    }
