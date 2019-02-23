package net.bernetrollande.emeric.controller

import io.ktor.freemarker.FreeMarkerContent
import net.bernetrollande.emeric.model.Model


class ArticleListControllerImpl(private val model: Model) :
    ArticleListController {

    override fun startFM (): Any {
        val articles = model.getArticleList()
        //call.respond(FreeMarkerContent("index.ftl", mapOf("articles" to articles), "e"))
        return FreeMarkerContent("index.ftl", mapOf("articles" to articles), "e")
    }
}