package net.bernetrollande.emeric

interface Model {
    val connectionPool: ConnectionPool
    fun getArticleList(): List<Article>
    fun getArticle(id: Int): Article?
}