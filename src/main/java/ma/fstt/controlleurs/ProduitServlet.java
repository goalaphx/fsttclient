package ma.fstt.controlleurs;

import jakarta.enterprise.context.RequestScoped;
import ma.fstt.dao.ProduitDAO;
import ma.fstt.entities.Produit;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/produit")
@RequestScoped
public class ProduitServlet extends HttpServlet {

    @Inject
    private ProduitDAO produitDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "new":
                    showNewForm(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    deleteProduit(req, resp);
                    break;
                case "list":
                default:
                    listProduits(req, resp);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "insert":
                    insertProduit(req, resp);
                    break;
                case "update":
                    updateProduit(req, resp);
                    break;
                default:
                    resp.sendRedirect("produit?action=list");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listProduits(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Produit> produits = produitDAO.listerTous();
        req.setAttribute("produits", produits);
        req.getRequestDispatcher("/produit-list.jsp").forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/produit-form.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Produit produit = produitDAO.trouverById(id);
            if (produit == null) {
                resp.sendRedirect("produit?action=list");
                return;
            }
            req.setAttribute("produit", produit);
            req.getRequestDispatcher("/produit-form.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendRedirect("produit?action=list");
        }
    }

    private void insertProduit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String nom = req.getParameter("nom");
            double prix = Double.parseDouble(req.getParameter("prix"));

            Produit produit = new Produit();
            produit.setNom(nom);
            produit.setPrix(prix);

            produitDAO.ajouterProduit(produit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("produit?action=list");
    }

    private void updateProduit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String nom = req.getParameter("nom");
            double prix = Double.parseDouble(req.getParameter("prix"));

            Produit produit = produitDAO.trouverById(id);
            produit.setNom(nom);
            produit.setPrix(prix);

            produitDAO.modifierProduit(produit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("produit?action=list");
    }

    private void deleteProduit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            produitDAO.supprimerProduit(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("produit?action=list");
    }
}
