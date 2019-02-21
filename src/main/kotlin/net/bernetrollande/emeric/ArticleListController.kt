package net.bernetrollande.emeric

import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.response.respond


class ArticleListController(private val model: MysqlModel) {

    fun startFM (): Any {
        val articles = model.getArticleList()
        //call.respond(FreeMarkerContent("index.ftl", mapOf("articles" to articles), "e"))
        return FreeMarkerContent("index.ftl", mapOf("articles" to articles), "e")
    }
}