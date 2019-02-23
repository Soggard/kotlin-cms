package net.bernetrollande.emeric.controller

import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import net.bernetrollande.emeric.model.Model


class ArticleControllerImpl(private val model: Model) :
    ArticleController {

    override fun startFM (id: Int): Any {
        val article = model.getArticle(id)
        if (article != null)
            return FreeMarkerContent("article.ftl", mapOf("article" to article), "e")
        return HttpStatusCode.NotFound
    }
}