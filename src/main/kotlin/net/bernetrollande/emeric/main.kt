package net.bernetrollande.emeric

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {

    val model = MysqlModel()

    embeddedServer(Netty, 8080) {

        install(FreeMarker) {
            templateLoader = ClassTemplateLoader(App::class.java.classLoader, "templates")
        }

        routing {
            get("/") {
                val articles = model.getArticleList()
                call.respond(FreeMarkerContent("index.ftl", mapOf("articles" to articles), "e"))
            }

            get("/article/{id}") {
                val id = call.parameters["id"]!!.toInt()
                val article = model.getArticle(id)

                if (article != null) {
                    call.respond(FreeMarkerContent("article.ftl", mapOf("article" to article), "e"))
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }.start(true)
}
