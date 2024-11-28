<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Liste des Lignes de Commande</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <style>
    body {
      background-color: #f8f9fa;
    }
    h1 {
      color: #343a40;
    }
    .table th {
      background-color: #007bff;
      color: #fff;
    }
    .table td {
      vertical-align: middle;
    }
    .btn-primary, .btn-warning, .btn-danger, .btn-secondary {
      margin-right: 5px;
    }
  </style>
</head>
<body>
<div class="container mt-5">
  <h1 class="mb-4 text-center">Lignes de Commande pour Commande #${idCommande}</h1>
  <div class="d-flex justify-content-between align-items-center mb-3">
    <a href="lignecommande?action=new&idCommande=${idCommande}" class="btn btn-primary">Ajouter des Produits</a>
    <a href="commande?action=list" class="btn btn-secondary">Retour à la Liste des Commandes</a>
  </div>
  <table class="table table-hover table-bordered">
    <thead>
    <tr class="text-center">
      <th>Produit</th>
      <th>Quantité</th>
      <th>Prix Total</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="ligne" items="${lignesCommande}">
      <tr>
        <td>${ligne.produit.nom}</td>
        <td class="text-center">${ligne.quantite}</td>
        <td class="text-end">${ligne.prixTotal} €</td>
        <td class="text-center">
          <a href="lignecommande?action=edit&id=${ligne.idLigneDeCommande}&idCommande=${idCommande}" class="btn btn-warning btn-sm">Modifier</a>
          <a href="lignecommande?action=delete&id=${ligne.idLigneDeCommande}"
             class="btn btn-danger btn-sm"
             onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette ligne ?');">Supprimer</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
    <tfoot>
    <tr>
      <th colspan="2" class="text-end">Total Général:</th>
      <th class="text-end">
        <c:set var="totalPrix" value="0"/>
        <c:forEach var="ligne" items="${lignesCommande}">
          <c:set var="totalPrix" value="${totalPrix + ligne.prixTotal}"/>
        </c:forEach>
        ${totalPrix} €
      </th>
      <th></th>
    </tr>
    </tfoot>
  </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
