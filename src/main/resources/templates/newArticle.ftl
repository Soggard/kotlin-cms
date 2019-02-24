
<#include "header.ftl">

<header class="header-bg text-white">
    <div class="container text-center">
        <h1>Administration - Publication d'article</h1>
        <p class="lead">Nouvel article</p>
    </div>
</header>

<section id="about">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <form action="/new" method="post">
                    <label>Titre</label>
                    <input type="text" name="title" class="form-control">

                    <label for="text">Contenu</label>
                    <textarea name="text" cols="100" rows="5" id="text"></textarea>

                    <input type="submit" value="Créer l'article">
                </form>
            </div>
        </div>
    </div>
</section>

<#include "footer.ftl">