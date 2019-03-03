package net.bernetrollande.emeric.model

import java.sql.Statement

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

    // Récupérer les commentaires d'un article
    override fun getCommentsByArticle(article: Int): List<Comment> {
        val comments = ArrayList<Comment>()

        connectionPool.use { connection ->
            connection.prepareStatement("SELECT * FROM comment WHERE comment.article = ?;").use { stmt ->
                stmt.setInt(1, article)
                val results = stmt.executeQuery()
                while (results.next()) {
                    comments += Comment(
                        results.getInt("id"),
                        results.getInt("article"),
                        results.getString("text")
                    )
                }
            }
        }
        return comments
    }


    // Connexion
    override fun getUser(login: String?): User? {

        connectionPool.use { connection ->
            connection.prepareStatement("SELECT id, login, password FROM user WHERE login = ?").use { stmt ->
                stmt.setString(1, login)
                val result = stmt.executeQuery()
                val found = result.next()

                if (found) {
                    return User(
                        result.getInt("id"),
                        result.getString("login"),
                        result.getString("password")
                    )
                }
            }
        }
        return null
    }

    // Création d'article
    override fun createArticle(article: Article) : Int {
        var returnedId = 0
        connectionPool.use { connection ->
            val result = connection.prepareStatement("INSERT INTO `article` (`id`, `title`, `text`) VALUES (NULL, ?, ?);", Statement.RETURN_GENERATED_KEYS).use { stmt ->
                stmt.setString(1, article.title)
                stmt.setString(2, article.text)
                stmt.executeUpdate()
                val generatedKeys = stmt.generatedKeys
                if (generatedKeys.next())
                    returnedId = generatedKeys.getInt(1)
            }
        }
        return returnedId
    }

    // Edition d'article
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

        // Suppression de l'article et de ses commentaires
        connectionPool.use { connection ->
            connection.prepareStatement("DELETE FROM article WHERE article.id = ?;").use { stmt ->
                stmt.setInt(1, id)
                stmt.executeUpdate()
            }
        }
    }

    // Création de commentaire
    override fun createComment(comment: Comment): Int {

        connectionPool.use { connection ->
            connection.prepareStatement("INSERT INTO `comment` (`id`, `article`, `text`) VALUES (NULL, ?, ?);").use { stmt ->
                stmt.setInt(1, comment.article)
                stmt.setString(2, comment.text)
                stmt.executeUpdate()
            }
        }
        return 0
    }

    // Suppression d'un commentaire
    override fun deleteComment(id: Int): Int {
        var returnedId = 0
        connectionPool.use { connection ->
            connection.prepareStatement("SELECT article FROM comment WHERE comment.id = ?;").use { stmt ->
                stmt.setInt(1, id)
                val result = stmt.executeQuery()
                if (result.next())
                    returnedId = result.getInt("article")
            }
            connection.prepareStatement("DELETE FROM comment WHERE comment.id = ?;").use { stmt ->
                stmt.setInt(1, id)
                stmt.executeUpdate()
            }
        }
        // Renvoie le numéro de l'article s'il existe
        return returnedId
    }
}