package net.bernetrollande.emeric

import net.bernetrollande.emeric.model.Article
import net.bernetrollande.emeric.model.Comment
import net.bernetrollande.emeric.model.Model
import net.bernetrollande.emeric.model.User

class FakeModel : Model {
    override fun getUser(login: String?): User? {
        if (login == "test")
            return User(1, "test", "\$2a\$10\$CiwiwttM7MW3TLAdX3bJEeis6Ms1S6Wf9b7y7W/UkZmWrNfTmvUSe")
        return null
    }

    override fun createArticle(article: Article): Int {
        return if (article.title == "test")
            1
        else
            0
    }

    override fun editArticle(article: Article) {}

    override fun deleteArticle(id: Int) {}

    override fun createComment(comment: Comment) {}

    override fun deleteComment(id: Int) {}

    override fun getCommentsByArticle(article: Int): List<Comment> {
        val comments = ArrayList<Comment>()
        if (article == 1) {
            comments += Comment(1, 1, "Comment 1")
            comments += Comment(2, 1,"Comment 2")
            comments += Comment(3, 1,"Comment 3")
            comments += Comment(4, 1,"Comment 4")
        }
        return comments
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