package net.bernetrollande.emeric.controller

import io.ktor.application.ApplicationCall
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import net.bernetrollande.emeric.UserSession
import net.bernetrollande.emeric.model.Model


class ArticleListControllerImpl(private val model: Model) :
    ArticleListController {

    override fun startFM (context: ApplicationCall): Any {
        val articles = model.getArticleList()
        return FreeMarkerContent("index.ftl", mapOf( "articles" to articles, "session" to context.sessions.get<UserSession>() ), "e")
    }
}