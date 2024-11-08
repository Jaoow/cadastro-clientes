package com.jaoow.clientregistry.dao;

import com.jaoow.clientregistry.entity.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClientDAO {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("clientePU");

    private EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    private void executeInsideTransaction(EntityManager em, Runnable action) {
        try {
            em.getTransaction().begin();
            action.run();
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public void saveClient(Client client) {
        EntityManager em = createEntityManager();
        executeInsideTransaction(em, () -> em.persist(client));
    }

    public Client findClientByEmail(String email) {
        EntityManager em = createEntityManager();
        try {
            TypedQuery<Client> query = em.createQuery("from Client where email = :email", Client.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Client> findAllClients() {
        EntityManager em = createEntityManager();
        try {
            TypedQuery<Client> query = em.createQuery("from Client", Client.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void updateClient(Client client) {
        EntityManager em = createEntityManager();
        executeInsideTransaction(em, () -> em.merge(client));
    }

    public void deleteClientById(Long id) {
        EntityManager em = createEntityManager();
        executeInsideTransaction(em, () -> {
            Client client = em.find(Client.class, id);
            if (client != null) {
                em.remove(client);
            }
        });
    }
}
