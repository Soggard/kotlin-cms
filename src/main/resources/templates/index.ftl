<#-- @ftlvariable name="session" type="net.bernetrollande.emeric.UserSession" -->
<#-- @ftlvariable name="" type="net.bernetrollande.emeric.IndexData" -->

<html>
<head>
    <title>Mes articles</title>
</head>
<h1>Mes articles</h1>
<#if session??>
    <p>Connecté en tant que ${session.login} (<a href="/disconnect">Déconnexion</a>)</p>
</#if>
<#if !session??>
    <p><a href="/login">Connexion</a></p>
</#if>

<#list articles as article>
    <p>
        <a href="/article/${article.id}">${article.title}</a>
    </p>
</#list>
</html>