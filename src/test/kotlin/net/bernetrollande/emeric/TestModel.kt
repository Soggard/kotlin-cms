package net.bernetrollande.emeric

import net.bernetrollande.emeric.model.Article
import net.bernetrollande.emeric.model.Model

class TestModel : Model {

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