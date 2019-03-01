package net.bernetrollande.emeric.controller

import net.bernetrollande.emeric.SessionProvider

interface ArticleListController {
    fun startFM (sessionProvider: SessionProvider): Any
}