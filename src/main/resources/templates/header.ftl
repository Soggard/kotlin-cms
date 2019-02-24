<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Le meilleur blog du monde</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="/static/css/bootstrap.css">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="/static/css/styles.css">

</head>

<body id="page-top">

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand" href="/">Accueil</a>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <#if session??>
                    <li class="nav-item">
                        <span class="nav-link">Bonjour ${session.login}</span>
                    </li>
                    <li class="nav-item">
                        <a href="/disconnect" class="nav-link">Déconnexion</a>
                    </li>
                </#if>
                <#if !session??>
                    <li class="nav-item">
                        <a href="/login" class="nav-link">Connexion</a>
                    </li>
                </#if>
            </ul>
        </div>
    </div>
</nav>