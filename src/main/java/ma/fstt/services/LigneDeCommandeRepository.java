package ma.fstt.services;

import ma.fstt.entities.LigneDeCommande;
import java.util.List;

public interface LigneDeCommandeRepository {
    LigneDeCommande trouverById(int id);
    void ajouterLigneDeCommande(LigneDeCommande ligneDeCommande);
    void modifierLigneDeCommande(LigneDeCommande ligneDeCommande);
    void supprimerLigneDeCommande(int id);
    List<LigneDeCommande> listerToutes();
}
