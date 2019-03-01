package net.bernetrollande.emeric.controller

import io.ktor.application.ApplicationCall

interface CommentController {
    fun createComment (article: Int, text: String, context: ApplicationCall): String
    fun deleteComment (id: Int, context: ApplicationCall): String
}