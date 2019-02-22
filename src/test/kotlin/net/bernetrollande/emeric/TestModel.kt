package net.bernetrollande.emeric

class TestModel : Model {

    override val connectionPool = ConnectionPool(
        "jdbc:mysql://localhost/cms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
        "root",
        ""
    )

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