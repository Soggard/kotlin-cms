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

class EditArticleControllerImpl(private val model: Model) : EditArticleController {

    // Page de création d'article
    override fun editArticlePage (id: Int, sessionProvider: SessionProvider): Any {
        if (sessionProvider.getSession() != null) {
            val article = model.getArticle(id)
            if (article != null)
                return FreeMarkerContent("editArticle.ftl", mapOf("article" to article, "session" to sessionProvider.getSession()), "e")
            return HttpStatusCode.NotFound
        }
        else
            return HttpStatusCode.Forbidden
    }

    // Action de création d'article
    override fun editArticleAction (id: Int, title: String?, text: String?, sessionProvider: SessionProvider): String {

        if (sessionProvider.getSession() == null)
            return "/"
        else if (title != null && text != null) {
            val article = Article(id, title, text)
            model.editArticle(article)
            return "/article/" + id.toString()
        } else
            return "/edit/"+ id +"?error=1"
    }
}