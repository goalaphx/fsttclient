<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Liste des Produits</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4 text-center">Liste des Produits</h1>
    <a href="produit?action=new" class="btn btn-primary mb-3">Ajouter un Produit</a>
    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prix</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="produit" items="${produits}">
            <tr>
                <td>${produit.idProduit}</td>
                <td>${produit.nom}</td>
                <td>${produit.prix}</td>
                <td>
                    <a href="produit?action=edit&id=${produit.idProduit}" class="btn btn-warning btn-sm">Modifier</a>
                    <a href="produit?action=delete&id=${produit.idProduit}" class="btn btn-danger btn-sm"
                       onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce produit ?');">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="index.jsp" class="btn btn-secondary">Retourner au Acceuil</a>
</div>
</body>
</html>
