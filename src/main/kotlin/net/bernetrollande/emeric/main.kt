package net.bernetrollande.emeric

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.sql.Connection
import java.sql.DriverManager


fun main() {
    embeddedServer(Netty, 8080) {
        routing {
            get("/") {
                //call.respondText("<h1>It's alive ! It's alive !", ContentType.Text.Html)

                val connection = databaseConnection()
                val stmt = connection!!.prepareStatement("SELECT * FROM article")
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

            get("/article/{id}") {
                //call.respondText("<h1>It's alive ! It's alive !", ContentType.Text.Html)

                val connection = databaseConnection()
                val id = call.parameters["id"]

                val s = connection!!.prepareStatement("SELECT * FROM article WHERE id = ?")
                s.setString(1, id)

                val results = s.executeQuery()

                val str = buildString {
                    while (results.next()) {
                        val id = results.getInt("id")
                        val title = results.getString("title")
                        val text = results.getString("text")
                        append("<h1>$title</h1><p>$text</p>")
                    }
                }

                call.respondText(str, ContentType.Text.Html)

            }
        }
    }.start(true)
}

fun databaseConnection(): Connection? {
    return DriverManager.getConnection("jdbc:mysql://localhost/cms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "")

}