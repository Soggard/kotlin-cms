<#-- @ftlvariable name="" type="net.bernetrollande.emeric.IndexData" -->

<html>
<head>
    <title>Mes articles</title>
</head>
<h1>Mes articles</h1>

<#list articles as article>
    <p>
        <a href="/article/${article.id}">${article.title}</a>
    </p>
</#list>
</html>