package net.bernetrollande.emeric.controller

import net.bernetrollande.emeric.SessionProvider

interface EditArticleController {

    // Page de création d'article
    fun editArticlePage (id: Int, sessionProvider: SessionProvider): Any

    // Action de création d'article
    fun editArticleAction (id: Int, title: String?, text: String?, sessionProvider: SessionProvider): String
}