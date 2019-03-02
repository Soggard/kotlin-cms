package net.bernetrollande.emeric.controller

import net.bernetrollande.emeric.SessionProvider
import net.bernetrollande.emeric.model.Comment
import net.bernetrollande.emeric.model.Model

class CommentControllerImpl(private val model: Model) : CommentController {

    override fun createComment (article: Int, text: String, sessionProvider: SessionProvider): String {
        model.createComment(Comment(0, article, text))
        return "/article/$article"
    }

    override fun deleteComment (id: Int, sessionProvider: SessionProvider): String {
        if (sessionProvider.getSession() != null) {
            model.deleteComment(id)
        }

        return "/"
    }

}