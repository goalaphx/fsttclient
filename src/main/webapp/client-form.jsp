<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulaire Client</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script>
        $(document).ready(function () {
            $("#clientForm").submit(function () {
                let nom = $("#nom").val();
                let email = $("#email").val();

                if (nom.trim() === "" || email.trim() === "") {
                    alert("Tous les champs sont obligatoires !");
                    return false;
                }
                return true;
            });
        });
    </script>
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4 text-center">${client != null ? "Modifier" : "Ajouter"} un Client</h1>
    <form id="clientForm" method="post" action="client">
        <input type="hidden" name="action" value="${client != null ? "update" : "insert"}">
        <c:if test="${client != null}">
            <input type="hidden" name="id" value="${client.idClient}">
        </c:if>
        <div class="mb-3">
            <label for="nom" class="form-label">Nom :</label>
            <input type="text" class="form-control" id="nom" name="nom" value="${client != null ? client.nom : ""}" required>
        </div>
        <div class="mb-3">
            <label for="adresse" class="form-label">Adresse :</label>
            <input type="text" class="form-control" id="adresse" name="adresse" value="${client != null ? client.adresse : ""}">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email :</label>
            <input type="email" class="form-control" id="email" name="email" value="${client != null ? client.email : ""}" required>
        </div>
        <button type="submit" class="btn btn-success">Enregistrer</button>
        <a href="client?action=list" class="btn btn-secondary">Annuler</a>
    </form>
</div>
</body>
</html>
