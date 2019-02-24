
<#include "header.ftl">

<header class="header-bg text-white">
    <div class="container text-center">
        <h1>Administration - Edition d'article</h1>
        <p class="lead">${article.title}</p>
    </div>
</header>


<section id="about">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">

                <form action="/edit" method="post">
                    <input type="hidden" name="id" value="${article.id}">

                    <label>Titre</label>
                    <input type="text" name="title" value="${article.title}" class="form-control"> <br>

                    <label>Contenu</label>
                    <textarea name="text" cols="100" rows="5">${article.text}</textarea> <be></be>

                    <input type="submit" value="Editer l'article" class="btn btn-info">
                </form>

            </div>
        </div>
    </div>
</section>


<#include "footer.ftl">