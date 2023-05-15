package tests;

import dao.CategoryDAO;
import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.*;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


public class OrderRegistration {

        public static void main(String[] args) {
            popularDatabase();
            EntityManager em = JPAUtil.getEntityManager();
            ProductDAO productDao = new ProductDAO(em);
            ClientDAO clientDAO = new ClientDAO(em);

            Product product = productDao.searchById(1l);
           Client client = clientDAO.searchById(1l);

            em.getTransaction().begin();

            Order order = new Order(client);
            order.addItem(new OrderedItem(10, order, product));

            OrderDAO orderDAO = new OrderDAO(em);
            orderDAO.register(order);

            em.getTransaction().commit();

            BigDecimal amount = orderDAO.totalValueSold();
            System.out.println("Amount " + amount);

            List<Object[]> report = orderDAO.salesReport();
            for (Object[] objects : report) {
                System.out.println(objects[0]);
                System.out.println(objects[1]);
                System.out.println(objects[2]);
            }
        }

        private static void popularDatabase() {
            Category smartphones = new Category("SMARTPHONES");
            Product smartphone = new Product("Xiaomy Redmi", "Very cool",
                    new BigDecimal("800"), smartphones);

            Client client = new Client("Luciano", "123.456.789-10");

            EntityManager em = JPAUtil.getEntityManager();
            ProductDAO productDao = new ProductDAO(em);
            CategoryDAO categoryDao = new CategoryDAO(em);
            ClientDAO clientDAO = new ClientDAO(em);

            em.getTransaction().begin();
            categoryDao.register(smartphones);
            productDao.register(smartphone);
            clientDAO.register(client);

            em.getTransaction().commit();
            em.close();
        }
    }
