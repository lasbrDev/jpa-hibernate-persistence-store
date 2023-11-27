package br.com.lasbr.dao;

import br.com.lasbr.model.Client;

import javax.persistence.EntityManager;

    public class ClientDAO {

        private EntityManager em;

        public ClientDAO(EntityManager em) {
            this.em = em;
        }

        public void register(Client client) {
            this.em.persist(client);
        }

        public Client searchById(Long id) {
            return em.find(Client.class, id);
        }
    }
