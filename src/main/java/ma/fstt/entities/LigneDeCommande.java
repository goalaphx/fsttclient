package ma.fstt.entities;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.persistence.*;


@Entity
@Table(name = "ligne_de_commande")
public class LigneDeCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLigneDeCommande;

    private int quantite;
    private double prixTotal;

    @ManyToOne
    @JoinColumn(name = "idProduit", nullable = false)
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "idCommande", nullable = false)
    private Commande commande;

    // Constructeurs
    public LigneDeCommande() {}

    public LigneDeCommande(int idLigneDeCommande, int quantite, double prixTotal, Produit produit, Commande commande) {
        this.idLigneDeCommande = idLigneDeCommande;
        this.quantite = quantite;
        this.prixTotal = prixTotal;
        this.produit = produit;
        this.commande = commande;
    }

    // Getters et setters
    public int getIdLigneDeCommande() {
        return idLigneDeCommande;
    }

    public void setIdLigneDeCommande(int idLigneDeCommande) {
        this.idLigneDeCommande = idLigneDeCommande;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
}
