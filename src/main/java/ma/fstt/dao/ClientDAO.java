package ma.fstt.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceContext;
import ma.fstt.entities.Client;
import ma.fstt.services.ClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


import java.util.List;

@ApplicationScoped
@Default
public class ClientDAO implements ClientRepository {

    @Inject
    private EntityManager entityManager;

    @Override
    public Client trouverById(int id) {
        return entityManager.find(Client.class, id);
    }

    @Transactional
    @Override
    public void ajouterClient(Client client) {
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
    }

    @Transactional
    @Override
    public void modifierClient(Client client) {
        entityManager.getTransaction().begin();
        entityManager.merge(client);
        entityManager.getTransaction().commit();
    }

    @Transactional
    @Override
    public void supprimerClient(int id) {
        Client client = entityManager.find(Client.class, id);
        if (client != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(client);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public List<Client> listerTous() {
        return entityManager.createQuery("SELECT c FROM Client c", Client.class).getResultList();
    }

    @Override
    public Client trouverParEmail(String email) {
        return entityManager.createQuery("SELECT c FROM Client c WHERE c.email = :email", Client.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
