package net.bernetrollande.emeric.controller

import io.ktor.application.ApplicationCall
import net.bernetrollande.emeric.SessionProvider

interface ArticleController {
    fun startFM (id: Int, sessionProvider: SessionProvider): Any
    fun deleteArticle (id: Int, sessionProvider: SessionProvider): String
}

