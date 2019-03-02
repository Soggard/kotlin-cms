package net.bernetrollande.emeric.controller

import net.bernetrollande.emeric.SessionProvider

interface NewArticleController {

    // Page de création d'article
    fun newArticlePage (sessionProvider: SessionProvider): Any

    // Action de création d'article
    fun newArticleAction (title: String?, text: String?, sessionProvider: SessionProvider): String
}