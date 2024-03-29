package br.com.lasbr.tests;

import br.com.lasbr.dao.CategoryDAO;
import br.com.lasbr.dao.ClientDAO;
import br.com.lasbr.dao.RequestDAO;
import br.com.lasbr.dao.ProductDAO;
import br.com.lasbr.model.Client;
import br.com.lasbr.model.Product;
import br.com.lasbr.model.Order;
import br.com.lasbr.model.OrderItem;
import br.com.lasbr.util.JPAUtil;
import br.com.lasbr.vo.SalesReportVo;
import br.com.lasbr.model.Category;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;


    public class RequestRegistration {
        public static void main(String[] args) {
            popularDatabase();
            EntityManager em = JPAUtil.getEntityManager();
            ProductDAO productDao = new ProductDAO(em);
            ClientDAO clientDAO = new ClientDAO(em);

            Product product = productDao.searchById(1l);
            Product product2 = productDao.searchById(2l);
            Product product3 = productDao.searchById(3l);
            Client client = clientDAO.searchById(1l);

            em.getTransaction().begin();

            Order request = new Order(client);
            request.addItem(new OrderItem(10, request, product));
            request.addItem(new OrderItem(40, request, product2));

            Order request2 = new Order(client);
            request.addItem(new OrderItem(2, request, product3));

            RequestDAO requestDAO = new RequestDAO(em);
            requestDAO.register(request);
            requestDAO.register(request2);

            em.getTransaction().commit();

            BigDecimal amount = requestDAO.totalValueSold();
            System.out.println("Amount " + amount);

            List<SalesReportVo> report = requestDAO.salesReport();
                report.forEach(System.out::println);
        }

        static void popularDatabase() {
            Category smartphones = new Category("SMARTPHONES");
            Category videogames = new Category("VIDEOGAMES");
            Category computing = new Category("COMPUTING");

            Product smartphone = new Product("Xiaomy Redmi", "Very cool",
                new BigDecimal("800"), smartphones);
            Product videogame = new Product("PS5", "PlayStation 5",
                    new BigDecimal("5000"), videogames);
            Product macbook = new Product("Macbook", "Macbook Pro",
                    new BigDecimal("13000"), computing );

            Client client = new Client("Luciano", "123.456.789-10");

            EntityManager em = JPAUtil.getEntityManager();
            ProductDAO productDao = new ProductDAO(em);
            CategoryDAO categoryDao = new CategoryDAO(em);
            ClientDAO clientDAO = new ClientDAO(em);

            em.getTransaction().begin();
            categoryDao.register(smartphones);
            categoryDao.register(videogames);
            categoryDao.register(computing);

            productDao.register(smartphone);
            productDao.register(videogame);
            productDao.register(macbook);

            clientDAO.register(client);

            em.getTransaction().commit();
            em.close();
        }
    }
