package net.bernetrollande.emeric.controller

import io.ktor.application.ApplicationCall
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.sessions.sessions
import net.bernetrollande.emeric.UserSession
import net.bernetrollande.emeric.model.Comment
import net.bernetrollande.emeric.model.Model

class CommentController(private val model: Model) {

    fun getCommentsByArticle(article: Int) {

    }

    fun createComment (article: Int, text: String, context: ApplicationCall): String {
        if (context.sessions.get("user") != null)
            model.createComment(Comment(0, article, text))
        return "/"
    }

    fun deleteComment (id: Int, context: ApplicationCall): String {
        if (context.sessions.get("user") != null)
            model.deleteComment(id)
        return "/"
    }

}