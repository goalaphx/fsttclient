<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire Ligne de Commande</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        h1 {
            color: #343a40;
        }
        .form-label {
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4 text-center">${ligne != null ? "Modifier" : "Ajouter"} une Ligne de Commande</h1>
    <div class="card shadow-sm p-4">
        <form method="post" action="lignecommande">
            <input type="hidden" name="action" value="${ligne != null ? "update" : "insert"}">
            <input type="hidden" name="idCommande" value="${idCommande}">
            <c:if test="${ligne != null}">
                <input type="hidden" name="idLigne" value="${ligne.idLigneDeCommande}">
            </c:if>
            <div class="mb-3">
                <label for="idProduit" class="form-label">Produit</label>
                <select class="form-select" id="idProduit" name="idProduit" required>
                    <c:forEach var="produit" items="${produits}">
                        <option value="${produit.idProduit}" ${ligne != null && produit.idProduit == ligne.produit.idProduit ? "selected" : ""}>
                                ${produit.nom}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="quantite" class="form-label">Quantité</label>
                <input type="number" class="form-control" id="quantite" name="quantite"
                       value="${ligne != null ? ligne.quantite : ""}" min="1" required>
            </div>
            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-primary">${ligne != null ? "Modifier" : "Ajouter"}</button>
                <a href="lignecommande?action=list&idCommande=${idCommande}" class="btn btn-secondary">Annuler</a>
            </div>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
