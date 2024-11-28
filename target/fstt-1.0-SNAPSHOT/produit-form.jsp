<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Formulaire Produit</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <script>
    $(document).ready(function () {
      $("#produitForm").submit(function () {
        let nom = $("#nom").val();
        let prix = $("#prix").val();

        if (nom.trim() === "" || isNaN(prix) || prix <= 0) {
          alert("Veuillez remplir correctement tous les champs !");
          return false;
        }
        return true;
      });
    });
  </script>
</head>
<body>
<div class="container mt-5">
  <h1 class="mb-4 text-center">${produit != null ? "Modifier" : "Ajouter"} un Produit</h1>
  <form id="produitForm" method="post" action="produit">
    <input type="hidden" name="action" value="${produit != null ? "update" : "insert"}">
    <c:if test="${produit != null}">
      <input type="hidden" name="id" value="${produit.idProduit}">
    </c:if>
    <div class="mb-3">
      <label for="nom" class="form-label">Nom :</label>
      <input type="text" class="form-control" id="nom" name="nom" value="${produit != null ? produit.nom : ""}" required>
    </div>
    <div class="mb-3">
      <label for="prix" class="form-label">Prix :</label>
      <input type="number" step="0.01" class="form-control" id="prix" name="prix" value="${produit != null ? produit.prix : ""}" required>
    </div>
    <button type="submit" class="btn btn-success">Enregistrer</button>
    <a href="produit?action=list" class="btn btn-secondary">Annuler</a>
  </form>
</div>
</body>
</html>
