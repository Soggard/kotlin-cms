package net.bernetrollande.emeric.controller

import io.ktor.application.ApplicationCall
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import net.bernetrollande.emeric.UserSession
import net.bernetrollande.emeric.model.Article
import net.bernetrollande.emeric.model.Model

class EditArticleController(private val model: Model) {

    // Page de création d'article
    fun editArticlePage (id: Int, context: ApplicationCall): Any {
        if (context.sessions.get("user") != null) {
            val article = model.getArticle(id)
            if (article != null)
                return FreeMarkerContent("editArticle.ftl", mapOf("article" to article, "session" to context.sessions.get<UserSession>()), "e")
            return HttpStatusCode.NotFound
        }
        else
            return HttpStatusCode.Forbidden
    }

    // Action de création d'article
    fun editArticleAction (id: Int, title: String?, text: String?, context: ApplicationCall): String {

        if (context.sessions.get("user") == null)
            return "/"
        else if (title != null && text != null) {
            val article = Article(id, title, text)
            model.editArticle(article)
            return "/article/" + id.toString()
        } else
            return "/edit/"+ id +"?error=1"
    }
}