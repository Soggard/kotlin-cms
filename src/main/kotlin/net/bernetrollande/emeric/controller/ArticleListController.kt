package net.bernetrollande.emeric.controller

import io.ktor.application.ApplicationCall

interface ArticleListController {
    fun startFM (context: ApplicationCall): Any
}