package ma.fstt.services;

import ma.fstt.entities.Produit;
import java.util.List;

public interface ProduitRepository {
    Produit trouverById(int id);
    void ajouterProduit(Produit produit);
    void modifierProduit(Produit produit);
    void supprimerProduit(int id);
    List<Produit> listerTous();
}
