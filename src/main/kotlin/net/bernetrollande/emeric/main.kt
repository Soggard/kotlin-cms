package net.bernetrollande.emeric

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.sql.DriverManager


fun main() {
    embeddedServer(Netty, 8080) {
        routing {
            get("/") {
                //call.respondText("<h1>It's alive ! It's alive !", ContentType.Text.Html)

                val connection = DriverManager.getConnection("jdbc:mysql://localhost/cms", "root", "")
                val stmt = connection.prepareStatement("SELECT * FROM articles")
                val results = stmt.executeQuery()

                var str = buildString {
                    while (results.next()) {
                        val id = results.getInt("id")
                        val title = results.getString("title")
                        append("<p><a href=\"/$id\">$title</a></p>")
                    }
                }

            }
        }
    }.start(true)
}
/*

                val s = connection.prepareStatement("SELECT * FROM articles WHERE id = ?")
                s.setInt(1, id)
 */