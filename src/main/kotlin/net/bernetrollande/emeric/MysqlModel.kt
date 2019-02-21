package net.bernetrollande.emeric

import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond

class MysqlModel {

    val connectionPool = ConnectionPool("jdbc:mysql://localhost/cms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "")

    fun getArticleList(): List<Article> {

        val articles = ArrayList<Article>()

        connectionPool.use { connection ->
            connection.prepareStatement("SELECT * FROM article").use { stmt ->
                val results = stmt.executeQuery()

                while (results.next()) {
                    articles += Article(results.getString("id").toInt(), results.getString("title"))
                }
            }
        }
        return articles
    }

    fun getArticle(id: Int): Article? {

        connectionPool.use { connection ->
            connection.prepareStatement("SELECT id, title, text FROM article WHERE id = ?").use { stmt ->
                stmt.setInt(1, id)
                val result = stmt.executeQuery()
                val found = result.next()

                if (found) {
                    return Article(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getString("text")
                    )
                }
            }
        }
        return null
    }
}