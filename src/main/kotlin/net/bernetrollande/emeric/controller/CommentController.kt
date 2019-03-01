package net.bernetrollande.emeric.controller

import net.bernetrollande.emeric.SessionProvider

interface CommentController {
    fun createComment (article: Int, text: String, sessionProvider: SessionProvider): String
    fun deleteComment (id: Int, sessionProvider: SessionProvider): String
}