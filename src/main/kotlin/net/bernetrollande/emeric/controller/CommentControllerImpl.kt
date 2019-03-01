package net.bernetrollande.emeric.controller

import io.ktor.application.ApplicationCall
import io.ktor.sessions.sessions
import net.bernetrollande.emeric.model.Comment
import net.bernetrollande.emeric.model.Model

class CommentControllerImpl(private val model: Model) : CommentController {

    override fun createComment (article: Int, text: String, context: ApplicationCall): String {
        model.createComment(Comment(0, article, text))
        return "/"
    }

    override fun deleteComment (id: Int, context: ApplicationCall): String {
        if (context.sessions.get("user") != null) {
            model.deleteComment(id)
        }

        return "/"
    }

}