
<#include "header.ftl">

<header class="header-bg text-white">
    <div class="container text-center">
        <h1>Connexion</h1>
        <p class="lead">Accédez à l'espace administrateur</p>
    </div>
</header>

<section id="about">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <form action="" method="post">
                    <label>
                        Identifiant
                        <input type="text" name="login">
                    </label>
                    <label>
                        Mot de passe
                        <input type="password" name="password">
                    </label>
                    <input type="submit" value="Valider">
                </form>
            </div>
        </div>
    </div>
</section>

<#include "footer.ftl">