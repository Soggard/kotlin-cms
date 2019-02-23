package net.bernetrollande.emeric.model

class MysqlModel(url: String, user: String?, password: String?) : Model {

    val connectionPool = ConnectionPool(url, user, password)

    override fun getArticleList(): List<Article> {
        val articles = ArrayList<Article>()

        connectionPool.use { connection ->
            connection.prepareStatement("SELECT * FROM article").use { stmt ->
                val results = stmt.executeQuery()
                while (results.next()) {
                    articles += Article(
                        results.getInt("id"),
                        results.getString("title")
                    )
                }
            }
        }
        return articles
    }

    override fun getArticle(id: Int): Article? {

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


    // Connexion
    override fun getUser(login: String, password: String): User? {

        connectionPool.use { connection ->
            connection.prepareStatement("SELECT id, login FROM user WHERE login = ? AND password = ?").use { stmt ->
                stmt.setString(1, login)
                stmt.setString(2, password)
                val result = stmt.executeQuery()
                val found = result.next()

                if (found) {
                    return User(
                        result.getInt("id"),
                        result.getString("login"),
                        ""
                    )
                }
            }
        }
        return null
    }
}