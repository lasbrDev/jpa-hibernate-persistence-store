package tests;

import dao.CategoryDAO;
import dao.ClientDAO;
import dao.ProductDAO;
import dao.RequestDAO;
import model.*;
import util.JPAUtil;

import javax.persistence.EntityManager;

import java.math.BigDecimal;

    public class PerformanceQueries {

        public static void main(String[] args) {
            popularDatabase();
            EntityManager em = JPAUtil.getEntityManager();
            RequestDAO requestDAO = new RequestDAO(em);
            Request request = requestDAO.searchRequestWithClient(1l);
            em.close();
            System.out.println(request.getClient().getName());
        }
        public static void popularDatabase()  {
            Category smartphones = new Category("SMARTPHONE");
            Category videogames = new Category("VIDEOGAMES");
            Category computing = new Category("COMPUTING");

            Product smartphone = new Product("Xiomi Redmi", "Very cool",
                    new BigDecimal("800"), smartphones);
            Product videogame = new Product("PS5", "PlayStation 5",
                    new BigDecimal("5000"), videogames);
            Product macbook = new Product("Macbook", "Macbook Pro Retina",
                    new BigDecimal("14000"), computing);

            Client client = new Client("Luciano", "123.456.789-10");

            Request request = new Request(client);
            request.addItem(new RequestItem(10, request, smartphone));
            request.addItem(new RequestItem(40, request, videogame));

            Request request2 = new Request(client);
            request2.addItem(new RequestItem(2, request, macbook));

            EntityManager em = JPAUtil.getEntityManager();
            ProductDAO productDAO = new ProductDAO(em);
            CategoryDAO categoryDAO = new CategoryDAO(em);
            ClientDAO clientDAO = new ClientDAO(em);
            RequestDAO requestDAO = new RequestDAO(em);

            em.getTransaction().begin();

            categoryDAO.register(smartphones);
            categoryDAO.register(videogames);
            categoryDAO.register(computing);

            productDAO.register(smartphone);
            productDAO.register(videogame);
            productDAO.register(macbook);

            clientDAO.register(client);

            requestDAO.register(request);
            requestDAO.register(request2);

            em.getTransaction().commit();
            em.close();
        }
    }
