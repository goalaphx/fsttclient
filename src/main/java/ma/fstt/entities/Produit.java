package ma.fstt.entities;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "produit")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduit;

    private String nom;
    private double prix;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LigneDeCommande> lignesDeCommande;

    // Constructeurs
    public Produit() {}

    public Produit(int idProduit, String nom, double prix) {
        this.idProduit = idProduit;
        this.nom = nom;
        this.prix = prix;
    }

    // Getters et setters
    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public List<LigneDeCommande> getLignesDeCommande() {
        return lignesDeCommande;
    }

    public void setLignesDeCommande(List<LigneDeCommande> lignesDeCommande) {
        this.lignesDeCommande = lignesDeCommande;
    }
}
