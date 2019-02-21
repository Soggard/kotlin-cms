package net.bernetrollande.emeric

import io.ktor.freemarker.FreeMarkerContent


class ArticleListControllerImpl(private val model: Model) : ArticleListController {

    override fun startFM (): Any {
        val articles = model.getArticleList()
        //call.respond(FreeMarkerContent("index.ftl", mapOf("articles" to articles), "e"))
        return FreeMarkerContent("index.ftl", mapOf("articles" to articles), "e")
    }
}