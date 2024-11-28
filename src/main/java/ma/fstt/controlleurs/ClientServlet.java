package ma.fstt.controlleurs;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.entities.Client;
import ma.fstt.services.ClientRepository;

import java.io.IOException;
import java.util.List;

@WebServlet("/client")
@RequestScoped
public class ClientServlet extends HttpServlet {

    @Inject
    private ClientRepository clientRepository;

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
                    deleteClient(req, resp);
                    break;
                case "list":
                default:
                    listClients(req, resp);
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
                    insertClient(req, resp);
                    break;
                case "update":
                    updateClient(req, resp);
                    break;
                default:
                    resp.sendRedirect("client?action=list");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listClients(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Client> clients = clientRepository.listerTous();
        req.setAttribute("clients", clients);
        req.getRequestDispatcher("/client-list.jsp").forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/client-form.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Client client = clientRepository.trouverById(id);
            if (client == null) {
                resp.sendRedirect("client?action=list");
                return;
            }
            req.setAttribute("client", client);
            req.getRequestDispatcher("/client-form.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendRedirect("client?action=list");
        }
    }

    private void insertClient(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String nom = req.getParameter("nom");
            String adresse = req.getParameter("adresse");
            String email = req.getParameter("email");

            if (nom == null || nom.isEmpty() || email == null || email.isEmpty()) {
                resp.sendRedirect("client?action=new");
                return;
            }

            Client client = new Client();
            client.setNom(nom);
            client.setAdresse(adresse);
            client.setEmail(email);

            clientRepository.ajouterClient(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("client?action=list");
    }

    private void updateClient(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String nom = req.getParameter("nom");
            String adresse = req.getParameter("adresse");
            String email = req.getParameter("email");

            if (nom == null || nom.isEmpty() || email == null || email.isEmpty()) {
                resp.sendRedirect("client?action=edit&id=" + id);
                return;
            }

            Client client = new Client(id, nom, adresse, email);
            clientRepository.modifierClient(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("client?action=list");
    }

    private void deleteClient(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            clientRepository.supprimerClient(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("client?action=list");
    }
}
