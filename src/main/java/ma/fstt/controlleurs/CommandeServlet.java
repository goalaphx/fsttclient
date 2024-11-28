package ma.fstt.controlleurs;

import jakarta.enterprise.context.RequestScoped;
import ma.fstt.dao.ClientDAO;
import ma.fstt.dao.CommandeDAO;
import ma.fstt.entities.Client;
import ma.fstt.entities.Commande;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.services.ClientRepository;
import ma.fstt.services.CommandeRepository;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/commande")
@RequestScoped
public class CommandeServlet extends HttpServlet {

    @Inject
    private CommandeRepository commandeRepository;

    @Inject
    private ClientRepository clientRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "list":
                    listCommandes(req, resp);
                    break;
                case "new":
                    showNewForm(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    deleteCommande(req, resp);
                    break;
                default:
                    listCommandes(req, resp);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("insert".equals(action)) {
            insertCommande(req, resp);
        } else if ("update".equals(action)) {
            updateCommande(req, resp);
        }
    }

    private void listCommandes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Commande> commandes = commandeRepository.listerToutes();
        req.setAttribute("commandes", commandes);
        req.getRequestDispatcher("/commande-list.jsp").forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Client> clients = clientRepository.listerTous();
        req.setAttribute("clients", clients);
        req.getRequestDispatcher("/commande-form.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Commande existingCommande = commandeRepository.trouverById(id);
        req.setAttribute("commande", existingCommande);

        List<Client> clients = clientRepository.listerTous();
        req.setAttribute("clients", clients);

        req.getRequestDispatcher("/commande-form.jsp").forward(req, resp);
    }

    private void insertCommande(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String dateStr = req.getParameter("dateCommande");
        int idClient = Integer.parseInt(req.getParameter("idClient"));

        try {
            Client client = clientRepository.trouverById(idClient);
            if (client == null) {
                throw new IllegalArgumentException("Client invalide.");
            }

            Date dateCommande = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            Commande commande = new Commande();
            commande.setDateCommande(dateCommande);
            commande.setClient(client);

            commandeRepository.ajouterCommande(commande);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect("commande?action=list");
    }

    private void updateCommande(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("idCommande"));
        String dateStr = req.getParameter("dateCommande");
        int idClient = Integer.parseInt(req.getParameter("idClient"));

        try {
            Client client = clientRepository.trouverById(idClient);
            if (client == null) {
                throw new IllegalArgumentException("Client invalide.");
            }

            Date dateCommande = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            Commande commande = commandeRepository.trouverById(id);
            commande.setDateCommande(dateCommande);
            commande.setClient(client);

            commandeRepository.modifierCommande(commande);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect("commande?action=list");
    }

    private void deleteCommande(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        commandeRepository.supprimerCommande(id);
        resp.sendRedirect("commande?action=list");
    }
}
