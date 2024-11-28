package ma.fstt.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "commande")
public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCommande;

    @Temporal(TemporalType.DATE)
    @Column(name = "dateCommande", nullable = false)
    private Date dateCommande;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idClient", nullable = false)
    private Client client;

    // Default Constructor
    public Commande() {}

    // Parameterized Constructor
    public Commande(int idCommande, Date dateCommande, Client client) {
        this.idCommande = idCommande;
        this.dateCommande = dateCommande;
        this.client = client;
    }

    // Getters and Setters
    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    // toString (Optional)
    @Override
    public String toString() {
        return "Commande{" +
                "idCommande=" + idCommande +
                ", dateCommande=" + dateCommande +
                ", client=" + client.getIdClient() +
                '}';
    }
}
