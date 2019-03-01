package net.bernetrollande.emeric.controller

import io.ktor.application.ApplicationCall
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import net.bernetrollande.emeric.UserSession
import net.bernetrollande.emeric.model.Article
import net.bernetrollande.emeric.model.Model

interface EditArticleController {

    // Page de création d'article
    fun editArticlePage (id: Int, context: ApplicationCall): Any

    // Action de création d'article
    fun editArticleAction (id: Int, title: String?, text: String?, context: ApplicationCall): String
}