package ma.fstt.services;

import ma.fstt.entities.Client;
import java.util.List;


public interface ClientRepository {
    // Méthodes CRUD
    Client trouverById(int id); // Trouver un client par son ID
    void ajouterClient(Client client); // Ajouter un nouveau client
    void modifierClient(Client client); // Modifier un client existant
    void supprimerClient(int id); // Supprimer un client par son ID
    List<Client> listerTous(); // Lister tous les clients

    // Méthodes spécifiques (optionnel)
    Client trouverParEmail(String email); // Trouver un client par son email
}
