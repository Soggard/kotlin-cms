package net.bernetrollande.emeric

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.sql.Connection
import java.sql.DriverManager


fun main() {
    embeddedServer(Netty, 8080) {
        val connectionPool = ConnectionPool("jdbc:mysql://localhost/cms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "")

        routing {
            get("/") {

                connectionPool.use { connection ->
                    val stmt = connection.prepareStatement("SELECT * FROM article")
                    val results = stmt.executeQuery()

                    val str = buildString {
                        while (results.next()) {
                            val id = results.getInt("id")
                            val title = results.getString("title")
                            append("<p><a href=\"/article/$id\">$title</a></p>")
                        }
                    }
                    call.respondText(str, ContentType.Text.Html)
                }
            }

            get("/article/{id}") {
                val id: String = call.parameters["id"] !!

                connectionPool.use { connection ->
                    val s = connection.prepareStatement("SELECT title, text FROM article WHERE id = ?")
                    s.setString(1, id)

                    val results = s.executeQuery()

                    val found = results.next()
                    if (found) {
                        val title = results.getString("title")
                        val text = results.getString("text")
                        call.respondText("<h1>$title</h1><p>$text</p>", ContentType.Text.Html)
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                    }
                }
            }
        }
    }.start(true)
}
