package dao;

import model.Order;
import model.Product;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

    public class OrderDAO {
        private EntityManager em;

        public OrderDAO(EntityManager em) {
            this.em = em;
        }

        public void register(Order order) {
            this.em.persist(order);
        }

        public BigDecimal totalValueSold() {
            String jpql = "SELECT SUM(p.amount) FROM Order p";
            return em.createQuery(jpql, BigDecimal.class).getSingleResult();
        }

        public List<Object[]> salesReport() {
            String jpql = "SELECT name.product, SUM(amount.item), MAX(order.data) FROM Order order "
            + " JOIN order.items item JOIN product.item product GROUP BY name.produtct ORDER BY amount.item DESC";
            return em.createQuery(jpql, Object[].class).getResultList();
        }
    }