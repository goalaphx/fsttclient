package ma.fstt.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import ma.fstt.entities.Commande;
import ma.fstt.services.CommandeRepository;

import java.util.List;

@ApplicationScoped
@Default
@Transactional
public class CommandeDAO implements CommandeRepository {

    @Inject
    private EntityManager entityManager;

    @Override
    public Commande trouverById(int id) {
        return entityManager.find(Commande.class, id);
    }

    @Override
    public void ajouterCommande(Commande commande) {
        entityManager.getTransaction().begin();
        entityManager.persist(commande);
        entityManager.getTransaction().commit();
    }

    @Override
    public void modifierCommande(Commande commande) {
        entityManager.getTransaction().begin();
        entityManager.merge(commande);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Commande> listerToutes() {
        return entityManager.createQuery("SELECT c FROM Commande c", Commande.class).getResultList();
    }

    @Override
    public void supprimerCommande(int id) {
        Commande commande = entityManager.find(Commande.class, id);
        if (commande != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(commande);
            entityManager.getTransaction().commit();
        }
    }
}
