package net.bernetrollande.emeric.controller

import io.ktor.application.ApplicationCall
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import net.bernetrollande.emeric.UserSession
import net.bernetrollande.emeric.model.Model


class ArticleControllerImpl(private val model: Model) :
    ArticleController {

    override fun startFM (id: Int, context: ApplicationCall): Any {
        val article = model.getArticle(id)
        val comments =  model.getCommentsByArticle(id)
        if (article != null)
            return FreeMarkerContent("article.ftl", mapOf("article" to article, "comments" to comments, "session" to context.sessions.get<UserSession>()), "e")
        return HttpStatusCode.NotFound
    }

    override fun deleteArticle(id: Int, context: ApplicationCall): String {
        if (context.sessions.get("user") != null)
            model.deleteArticle(id)
        return "/"
    }
}