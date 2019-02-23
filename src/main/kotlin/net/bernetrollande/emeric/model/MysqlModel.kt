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
    override fun getUser(login: String?, password: String?): User? {

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

    // Création d'article
    override fun createArticle(article: Article) {

        connectionPool.use { connection ->
            connection.prepareStatement("INSERT INTO `article` (`id`, `title`, `text`) VALUES (NULL, ?, ?);").use { stmt ->
                stmt.setString(1, article.title)
                stmt.setString(2, article.text)
                stmt.executeQuery()
            }
        }
    }

    // Création d'article
    override fun editArticle(article: Article) {
        connectionPool.use { connection ->
            connection.prepareStatement("UPDATE article SET title = ?, text = ? WHERE article.id = ?;").use { stmt ->
                stmt.setString(1, article.title)
                stmt.setString(2, article.text)
                stmt.setInt(3, article.id)
                stmt.executeUpdate()
            }
        }
    }

    // Suppression d'article
    override fun deleteArticle(id: Int) {

        connectionPool.use { connection ->
            connection.prepareStatement("DELETE FROM article WHERE article.id = ?;").use { stmt ->
                stmt.setInt(1, id)
                stmt.executeUpdate()
            }
        }
    }
}