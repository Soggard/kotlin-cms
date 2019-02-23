<#-- @ftlvariable name="" type="net.bernetrollande.emeric.model.MysqlModel" -->
<html>
    <h1>Article</h1>
    <h2>${article.title}</h2>
    <p>${article.text?replace("\n", "<br>")}</p>
</html>
