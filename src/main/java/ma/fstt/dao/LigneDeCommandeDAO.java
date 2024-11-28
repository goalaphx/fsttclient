package ma.fstt.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import ma.fstt.entities.LigneDeCommande;

import java.util.List;

@ApplicationScoped
@Default
public class LigneDeCommandeDAO {

    @Inject
    private EntityManager entityManager;

    public LigneDeCommande trouverById(int idLigne) {
        return entityManager.find(LigneDeCommande.class, idLigne);
    }

    @Transactional
    public void ajouterLigneDeCommande(LigneDeCommande ligne) {
        entityManager.getTransaction().begin();
        entityManager.persist(ligne);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void modifierLigneDeCommande(LigneDeCommande ligne) {
        entityManager.getTransaction().begin();
        entityManager.merge(ligne);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void supprimerLigneDeCommande(int idLigne) {
        LigneDeCommande ligne = entityManager.find(LigneDeCommande.class, idLigne);
        if (ligne != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(ligne);
            entityManager.getTransaction().commit();
        }
    }

    public List<LigneDeCommande> listerParCommande(int idCommande) {
        return entityManager.createQuery(
                        "SELECT ldc FROM LigneDeCommande ldc WHERE ldc.commande.idCommande = :idCommande",
                        LigneDeCommande.class)
                .setParameter("idCommande", idCommande)
                .getResultList();
    }
}
