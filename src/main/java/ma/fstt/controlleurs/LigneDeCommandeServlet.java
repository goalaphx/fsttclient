package ma.fstt.controlleurs;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.dao.LigneDeCommandeDAO;
import ma.fstt.dao.ProduitDAO;
import ma.fstt.dao.CommandeDAO;
import ma.fstt.entities.LigneDeCommande;
import ma.fstt.entities.Produit;
import ma.fstt.entities.Commande;

import java.io.IOException;
import java.util.List;

@WebServlet("/lignecommande")
@RequestScoped
public class LigneDeCommandeServlet extends HttpServlet {

    @Inject
    private LigneDeCommandeDAO ligneDeCommandeDAO;

    @Inject
    private ProduitDAO produitDAO;

    @Inject
    private CommandeDAO commandeDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "list":
                    listLignesDeCommande(req, resp);
                    break;
                case "new":
                    showForm(req, resp, false);
                    break;
                case "edit":
                    showForm(req, resp, true);
                    break;
                case "delete":
                    deleteLigneDeCommande(req, resp);
                    break;
                default:
                    resp.sendRedirect("lignecommande?action=list");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            switch (action) {
                case "insert":
                    insertLigneDeCommande(req, resp);
                    break;
                case "update":
                    updateLigneDeCommande(req, resp);
                    break;
                default:
                    resp.sendRedirect("lignecommande?action=list");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listLignesDeCommande(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idCommande = Integer.parseInt(req.getParameter("idCommande"));
        List<LigneDeCommande> lignes = ligneDeCommandeDAO.listerParCommande(idCommande);

        req.setAttribute("lignesCommande", lignes);
        req.setAttribute("idCommande", idCommande);
        req.getRequestDispatcher("/ligne-commande-list.jsp").forward(req, resp);
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp, boolean isEdit) throws ServletException, IOException {
        int idCommande = Integer.parseInt(req.getParameter("idCommande"));
        LigneDeCommande ligne = isEdit ? ligneDeCommandeDAO.trouverById(Integer.parseInt(req.getParameter("id"))) : null;
        List<Produit> produits = produitDAO.listerTous();

        req.setAttribute("ligne", ligne);
        req.setAttribute("produits", produits);
        req.setAttribute("idCommande", idCommande);
        req.getRequestDispatcher("/ligne-commande-form.jsp").forward(req, resp);
    }

    private void insertLigneDeCommande(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int idProduit = Integer.parseInt(req.getParameter("idProduit"));
        int idCommande = Integer.parseInt(req.getParameter("idCommande"));
        int quantite = Integer.parseInt(req.getParameter("quantite"));

        Produit produit = produitDAO.trouverById(idProduit);
        Commande commande = commandeDAO.trouverById(idCommande);

        LigneDeCommande ligne = new LigneDeCommande();
        ligne.setProduit(produit);
        ligne.setCommande(commande);
        ligne.setQuantite(quantite);
        ligne.setPrixTotal(quantite * produit.getPrix());

        ligneDeCommandeDAO.ajouterLigneDeCommande(ligne);
        resp.sendRedirect("lignecommande?action=list&idCommande=" + idCommande);
    }

    private void updateLigneDeCommande(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int idLigne = Integer.parseInt(req.getParameter("idLigne"));
        int idProduit = Integer.parseInt(req.getParameter("idProduit"));
        int idCommande = Integer.parseInt(req.getParameter("idCommande"));
        int quantite = Integer.parseInt(req.getParameter("quantite"));

        Produit produit = produitDAO.trouverById(idProduit);
        Commande commande = commandeDAO.trouverById(idCommande);

        LigneDeCommande ligne = ligneDeCommandeDAO.trouverById(idLigne);
        ligne.setProduit(produit);
        ligne.setCommande(commande);
        ligne.setQuantite(quantite);
        ligne.setPrixTotal(quantite * produit.getPrix());

        ligneDeCommandeDAO.modifierLigneDeCommande(ligne);
        resp.sendRedirect("lignecommande?action=list&idCommande=" + idCommande);
    }

    private void deleteLigneDeCommande(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int idLigne = Integer.parseInt(req.getParameter("id"));
        LigneDeCommande ligne = ligneDeCommandeDAO.trouverById(idLigne);

        ligneDeCommandeDAO.supprimerLigneDeCommande(idLigne);
        resp.sendRedirect("lignecommande?action=list&idCommande=" + ligne.getCommande().getIdCommande());
    }
}
