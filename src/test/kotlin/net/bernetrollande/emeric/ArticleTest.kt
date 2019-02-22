package net.bernetrollande.emeric

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.install
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import io.ktor.routing.get
import io.ktor.routing.routing
import org.junit.Test
import kotlin.test.assertEquals

fun generateArticle() {
    val article = Article(1, "Top 5 des trucs les plus oufs (Le 6ème va vous étonner", "1-Moi. 2-Moi. 3-Moi. 4-Moi. 5-Moi. 6-Moi.")

}

class ArticleTest {

    @Test
    fun testExistingArticle() {
        val model = TestModel()
        val result = ArticleControllerImpl(model).startFM(1)
        assert(result is FreeMarkerContent)
    }

    @Test
    fun testFakeArticle() {
        val model = TestModel()
        val result =ArticleControllerImpl(model).startFM(2)
        assertEquals(HttpStatusCode.NotFound, result)

    }

}

class TestModel : Model {

    override val connectionPool = ConnectionPool("jdbc:mysql://localhost/cms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "")

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