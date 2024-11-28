package ma.fstt.utils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class PersistenceProducer {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myentity");

    @Produces
    @ApplicationScoped
    public EntityManager createEntityManager() {
        System.out.println("Creating EntityManager");
        return emf.createEntityManager();
    }

    public void closeEntityManager(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
