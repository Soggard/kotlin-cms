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

class App

fun main() {
    embeddedServer(Netty, 8080) {

        install(FreeMarker) {
            templateLoader = ClassTemplateLoader(App::class.java.classLoader, "templates")
        }

        val connectionPool = ConnectionPool("jdbc:mysql://localhost/cms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "")

        routing {
            get("/") {

                connectionPool.use { connection ->
                    val stmt = connection.prepareStatement("SELECT * FROM article")
                    val results = stmt.executeQuery()

                    /*val str = buildString {
                        while (results.next()) {
                            val id = results.getInt("id")
                            val title = results.getString("title")
                            append("<p><a href=\"/article/$id\">$title</a></p>")
                        }
                    }*/

                    val articles = ArrayList<Article>()
                    while (results.next()) {

                        articles.add( Article(
                            results.getString("id"),
                            results.getString("title")
                        ))
                    }
                    // call.respondText(str, ContentType.Text.Html)
                    call.respond(FreeMarkerContent("index.ftl", mapOf("articles" to articles), "e"))
                }
            }

            get("/article/{id}") {
                val id: String = call.parameters["id"] !!

                connectionPool.use { connection ->
                    val s = connection.prepareStatement("SELECT id, title, text FROM article WHERE id = ?")
                    s.setString(1, id)

                    val result = s.executeQuery()

                    val found = result.next()
                    if (found) {
                        val article = Article(
                            result.getString("id"),
                            result.getString("title"),
                            result.getString("text")
                        )
                        //call.respondText("<h1>$title</h1><p>$text</p>", ContentType.Text.Html)
                        call.respond(FreeMarkerContent("article.ftl", mapOf("article" to article), "e"))
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                    }
                }
            }
        }
    }.start(true)
}
