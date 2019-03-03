package net.bernetrollande.emeric.model

interface Model {
    fun getArticleList(): List<Article>
    fun getArticle(id: Int): Article?
    fun getUser(login: String?): User?
    fun createArticle(article: Article): Int
    fun editArticle(article: Article)
    fun deleteArticle(id: Int)
    fun createComment(comment: Comment): Int
    fun deleteComment(id: Int): Int
    fun getCommentsByArticle(article: Int): List<Comment>
}