<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire Commande</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        h1 {
            color: #343a40;
        }
        .form-label {
            font-weight: bold;
        }
    </style>
    <script>
        $(document).ready(function () {
            $("#commandeForm").submit(function () {
                let dateCommande = $("#dateCommande").val();
                let idClient = $("#idClient").val();

                if (dateCommande.trim() === "" || idClient.trim() === "") {
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
    <h1 class="mb-4 text-center">${commande != null ? "Modifier" : "Ajouter"} une Commande</h1>
    <form id="commandeForm" method="post" action="commande" class="card p-4 shadow-sm">
        <input type="hidden" name="action" value="${commande != null ? "update" : "insert"}">
        <c:if test="${commande != null}">
            <input type="hidden" name="idCommande" value="${commande.idCommande}">
        </c:if>
        <div class="mb-3">
            <label for="dateCommande" class="form-label">Date de Commande :</label>
            <input type="date" class="form-control" id="dateCommande" name="dateCommande"
                   value="<c:if test='${commande != null}'><fmt:formatDate value='${commande.dateCommande}' pattern='yyyy-MM-dd'/></c:if>">
        </div>
        <div class="mb-3">
            <label for="idClient" class="form-label">Client :</label>
            <select class="form-select" id="idClient" name="idClient">
                <c:forEach var="client" items="${clients}">
                    <option value="${client.idClient}"
                        ${commande != null && commande.client != null && commande.client.idClient == client.idClient ? "selected" : ""}>
                            ${client.nom}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="d-flex justify-content-between">
            <button type="submit" class="btn btn-success">Enregistrer</button>
            <a href="commande?action=list" class="btn btn-secondary">Annuler</a>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
