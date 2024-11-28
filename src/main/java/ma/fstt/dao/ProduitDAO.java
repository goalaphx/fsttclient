package ma.fstt.dao;

import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import ma.fstt.entities.Produit;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
@Default
public class ProduitDAO {

    @Inject
    private EntityManager entityManager;

    public Produit trouverById(int id) {
        return entityManager.find(Produit.class, id);
    }

    public void ajouterProduit(Produit produit) {
        entityManager.getTransaction().begin();
        entityManager.persist(produit);
        entityManager.getTransaction().commit();
    }

    public void modifierProduit(Produit produit) {
        entityManager.getTransaction().begin();
        entityManager.merge(produit);
        entityManager.getTransaction().commit();
    }

    public void supprimerProduit(int id) {
        Produit produit = trouverById(id);
        if (produit != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(produit);
            entityManager.getTransaction().commit();
        }
    }

    public List<Produit> listerTous() {
        return entityManager.createQuery("SELECT p FROM Produit p", Produit.class).getResultList();
    }
}
