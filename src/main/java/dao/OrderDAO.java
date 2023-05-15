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
    }