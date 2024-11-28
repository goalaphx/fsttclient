<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        h1 {
            color: #343a40;
        }
        .btn-lg {
            width: 100%;
            padding: 20px;
            font-size: 1.25rem;
        }
    </style>
</head>
<body>
<div class="container text-center">
    <h1 class="mb-4">Bienvenue dans l'application de gestion</h1>
    <p class="lead mb-5">Utilisez les options ci-dessous pour naviguer vers les différentes sections.</p>
    <div class="row g-4">
        <div class="col-md-4">
            <a href="client?action=list" class="btn btn-primary btn-lg">Gérer les Clients</a>
        </div>
        <div class="col-md-4">
            <a href="produit?action=list" class="btn btn-success btn-lg">Gérer les Produits</a>
        </div>
        <div class="col-md-4">
            <a href="commande?action=list" class="btn btn-warning btn-lg">Gérer les Commandes</a>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
