package net.bernetrollande.emeric

import net.bernetrollande.emeric.model.Article
import net.bernetrollande.emeric.model.Comment
import net.bernetrollande.emeric.model.Model
import net.bernetrollande.emeric.model.User

class TestModel : Model {
    override fun getUser(login: String?): User? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createArticle(article: Article) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun editArticle(article: Article) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteArticle(id: Int) {}

    override fun createComment(comment: Comment) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteComment(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCommentsByArticle(article: Int): List<Comment> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // SIMULATION DU MODEL

    override fun getArticleList(): List<Article> {
        val articles = ArrayList<Article>()
        articles += Article(1, "Article 1")
        articles += Article(2, "Article 2")
        articles += Article(3, "Article 3")
        articles += Article(4, "Article 4")
        return articles
    }

    override fun getArticle(id: Int): Article? {
        if (id == 1)
            return Article(1, "Article 1")
        return null
    }
}