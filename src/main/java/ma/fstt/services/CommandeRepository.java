package ma.fstt.services;

import ma.fstt.entities.Commande;
import java.util.List;

public interface CommandeRepository {
    Commande trouverById(int id);
    void ajouterCommande(Commande commande);
    void modifierCommande(Commande commande);
    void supprimerCommande(int id);
    List<Commande> listerToutes();
}
