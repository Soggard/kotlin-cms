package net.bernetrollande.emeric

interface Model {
    fun getArticleList(): List<Article>
    fun getArticle(id: Int): Article?
}