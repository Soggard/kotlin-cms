package net.bernetrollande.emeric.controller

import io.ktor.http.HttpStatusCode
import net.bernetrollande.emeric.SessionProvider
import net.bernetrollande.emeric.model.Comment
import net.bernetrollande.emeric.model.Model

class CommentControllerImpl(private val model: Model) : CommentController {

    override fun createComment (article: Int, text: String, sessionProvider: SessionProvider): String {
        model.createComment(Comment(0, article, text))
        return "/article/$article"
    }

    override fun deleteComment (id: Int, sessionProvider: SessionProvider): Any {
        if (sessionProvider.getSession() == null)
            return HttpStatusCode.Forbidden

        val result = model.deleteComment(id)
        return if (result > 0)
            "/article/$result"
        else
            "/"
    }

}