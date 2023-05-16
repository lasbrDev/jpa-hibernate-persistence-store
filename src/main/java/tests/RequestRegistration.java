package tests;

import dao.CategoryDAO;
import dao.ClientDAO;
import dao.RequestDAO;
import dao.ProductDAO;
import model.*;
import util.JPAUtil;
import vo.SalesReportVo;

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

            Request request = new Request(client);
            request.addItem(new RequestItem(10, request, product));
            request.addItem(new RequestItem(40, request, product2));

            Request request2 = new Request(client);
            request.addItem(new RequestItem(2, request, product3));

            RequestDAO requestDAO = new RequestDAO(em);
            requestDAO.register(request);
            requestDAO.register(request2);

            em.getTransaction().commit();

            BigDecimal amount = requestDAO.totalValueSold();
            System.out.println("Amount " + amount);

            List<SalesReportVo> report = requestDAO.salesReport();
                report.forEach(System.out::println);
        }

        private static void popularDatabase() {
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
