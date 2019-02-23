package net.bernetrollande.emeric.controller

import io.ktor.application.ApplicationCall
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import io.ktor.sessions.sessions
import net.bernetrollande.emeric.UserSession
import net.bernetrollande.emeric.model.Article
import net.bernetrollande.emeric.model.Model

class NewArticleController(private val model: Model) {

    // Page de création d'article
    fun newArticlePage (context: ApplicationCall): Any {
        if (context.sessions.get("user") != null)
            return FreeMarkerContent("newArticle.ftl", null, "e")
        else
            return HttpStatusCode.Forbidden
}

    // Action de création d'article
    fun newArticleAction (title: String?, text: String?, context: ApplicationCall): String {

        if (context.sessions.get("user") == null) {
            return "/"
        } else if (title != null && text != null) {
            val article = Article(0, title, text)
            model.createArticle(article)
            return "/"
        } else
            // Si toutes les informations ne sont pas renseignées
            return "/new?error=1"
    }
}