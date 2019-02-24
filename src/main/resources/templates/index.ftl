<#-- @ftlvariable name="session" type="net.bernetrollande.emeric.UserSession" -->
<#-- @ftlvariable name="" type="net.bernetrollande.emeric.IndexData" -->

<#include "header.ftl">

    <header class="header-bg text-white">
        <div class="container text-center">
            <h1>Bienvenue sur mon blog</h1>
            <p class="lead">Consultez ici mes différents articles</p>
        </div>
    </header>

<section id="about">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <h2>Mes articles</h2>
                <p class="lead">This is a great place to talk about your webpage. This template is purposefully unstyled so you can use it as a boilerplate or starting point for you own landing page designs! This template features:</p>

                <#if session??>
                <p><a href="/new" class="admin-link">[Publier un nouvel article]</a></p>
                </#if>

                <ul>
                    <#list articles as article>
                        <li>
                            <a href="/article/${article.id}">${article.title}</a>
                            <#if session??>
                                <a href="/delete/${article.id}" class="admin-link">[Supprimer]</span></a>
                                <a href="/edit/${article.id}" class="admin-link">[Editer]</a>
                            </#if>
                        </li>
                    </#list>
                </ul>

                <#if session??>
                    <p><a href="/new" class="admin-link">[Publier un nouvel article]</a></p>
                </#if>
            </div>
        </div>
    </div>
</section>


<#include "footer.ftl">