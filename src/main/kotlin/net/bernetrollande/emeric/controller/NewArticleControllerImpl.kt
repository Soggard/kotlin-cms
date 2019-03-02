package net.bernetrollande.emeric.controller

import io.ktor.application.ApplicationCall
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import net.bernetrollande.emeric.SessionProvider
import net.bernetrollande.emeric.UserSession
import net.bernetrollande.emeric.model.Article
import net.bernetrollande.emeric.model.Model

class NewArticleControllerImpl(private val model: Model) : NewArticleController {

    // Page de création d'article
    override fun newArticlePage (sessionProvider: SessionProvider): Any {
        if (sessionProvider.getSession() != null)
            return FreeMarkerContent("newArticle.ftl", mapOf("session" to sessionProvider.getSession()), "e")
        else
            return HttpStatusCode.Forbidden
    }

    // Action de création d'article
    override fun newArticleAction (title: String?, text: String?, sessionProvider: SessionProvider): String {

        if (sessionProvider.getSession() == null) {
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