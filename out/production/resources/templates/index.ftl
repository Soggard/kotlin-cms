<#-- @ftlvariable name="" type="net.bernetrollande.emeric.IndexData" -->

<html>
<h1>Hello !</h1>

<#list articles as article>
    <a href="/article/${article.id}">${article.title}</a>
</#list>
</html>