<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Liste des Commandes</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
  <h1 class="mb-4 text-center">Liste des Commandes</h1>
  <a href="commande?action=new" class="btn btn-primary mb-3">Ajouter une Commande</a>
  <table class="table table-hover table-bordered">
    <thead>
    <tr class="text-center">
      <th>ID</th>
      <th>Date de Commande</th>
      <th>Client</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="commande" items="${commandes}">
      <tr>
        <td class="text-center">${commande.idCommande}</td>
        <td class="text-center">${commande.dateCommande}</td>
        <td>${commande.client.idClient}</td>
        <td class="text-center">
          <a href="commande?action=edit&id=${commande.idCommande}" class="btn btn-warning btn-sm">Modifier</a>
          <a href="commande?action=delete&id=${commande.idCommande}" class="btn btn-danger btn-sm"
             onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette commande ?');">Supprimer</a>
          <a href="lignecommande?action=list&idCommande=${commande.idCommande}" class="btn btn-info btn-sm">Voir les Lignes</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <a href="index.jsp" class="btn btn-secondary">Retourner au Acceuil</a>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
