<#-- @ftlvariable name="article" type="net.bernetrollande.emeric.model.Article" -->

<#include "header.ftl">

<header class="header-bg text-white">
    <div class="container text-center">
        <h1>Consulter un article</h1>
        <p class="lead">Cet article est sans nul doute le meilleur d'entre tous</p>
    </div>
</header>

<section id="about">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <h2>${article.title}</h2>
                <p class="lead">${article.text?replace("\n", "<br>")}</p>
                <#if session??>
                    <ul>
                        <li><a href="/edit/${article.id}">Editer l'article</a></li>
                        <li><a href="/delete/${article.id}">Supprimer l'article</a></li>
                    </ul>
                </#if>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <label for="comment">Ecrire un commentaire</label>
                <form action="/comment/create" method="post">
                    <input type="hidden" name="article" value="${article.id}">
                    <textarea id="comment" name="text" cols="100" rows="5"></textarea> <br>
                    <input type="submit" value="Commenter" class="btn btn-info">
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <hr>
                <#list comments?reverse as comment>
                    <p>
                        ${comment.text} <br>
                        <#if session??>
                            <a href="/comment/delete/${comment.id}">Supprimer</a>
                        </#if>

                    </p>
                    <hr>
                </#list>
            </div>
        </div>

    </div>
</section>

<#include "footer.ftl">