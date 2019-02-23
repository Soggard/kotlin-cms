<#-- @ftlvariable name="article" type="net.bernetrollande.emeric.model.Article" -->
<html>
    <a href="/">Retour à l'accueil</a>
    <h1>Article</h1>
    <h2>${article.title}</h2>
    <p>${article.text?replace("\n", "<br>")}</p>
    <#if session??>
        <a href="/delete/${article.id}">Supprimer l'article</a>
    </#if>
    <form action="/comment/create" method="post">
        <input type="hidden" name="article" value="${article.id}">
        <textarea name="text" cols="30" rows="10"></textarea>
        <input type="submit" value="Commenter">
    </form>
    <div>
        <hr>
        <#list comments as comment>
            <p>
                ${comment.text} <br>
                <#if session??>
                    <a href="/comment/delete/${comment.id}">Supprimer</a>
                </#if>

            </p>
            <hr>
        </#list>
    </div>
</html>
