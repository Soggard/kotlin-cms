package net.bernetrollande.emeric.model

interface Model {
    fun getArticleList(): List<Article>
    fun getArticle(id: Int): Article?
    fun getUser(login: String?, password: String?): User?
    fun createArticle(article: Article)
    fun editArticle(article: Article)
    fun deleteArticle(id: Int)
    fun createComment(comment: Comment)
    fun deleteComment(id: Int)
    fun getCommentsByArticle(article: Int): List<Comment>
}