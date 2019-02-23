package net.bernetrollande.emeric.controller

import io.ktor.application.ApplicationCall

interface ArticleController {
    fun startFM (id: Int, context: ApplicationCall): Any
}