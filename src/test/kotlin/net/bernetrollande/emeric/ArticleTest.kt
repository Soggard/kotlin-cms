package net.bernetrollande.emeric

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import net.bernetrollande.emeric.controller.ArticleControllerImpl
import net.bernetrollande.emeric.model.Article
import net.bernetrollande.emeric.model.Model
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ArticleTest {

    // Affichage de la page d'accueil
    @Test
    fun testHomepage() {
        /*val model = mock<Model> {
            on { getArticleList() } doReturn Article(1, "title", "text")
        }*/
        val model = TestModel()
        val result = ArticleControllerImpl(model).startFM(1, FakeSessionProvider())
        assertTrue(result is FreeMarkerContent)
    }

    // Affichage d'un article (existant/inexistant)
    @Test
    fun testExistingArticle() {
        val model = mock<Model> {
            on { getArticle(1) } doReturn Article(1, "title", "text")
        }
        val result = ArticleControllerImpl(model).startFM(1, FakeSessionProvider())
        assertTrue(result is FreeMarkerContent)
    }

    @Test
    fun testFakeArticle() {
        val model = mock<Model> {}
        val result = ArticleControllerImpl(model).startFM(2, FakeSessionProvider())
        println(result)
        assertEquals(HttpStatusCode.NotFound, result)

    }

    // Suppression d'article (Admin/visiteur)
    @Test
    fun deleteArticleAdmin() {
        //val model = mock<Model> {}
        val model = TestModel()
        val result = ArticleControllerImpl(model).deleteArticle(1, FakeSessionProvider())
        assertEquals("/", result)
    }
    @Test

    fun deleteArticle() {
        //val model = mock<Model> {}
        val model = TestModel()
        val result = ArticleControllerImpl(model).deleteArticle(1, FakeNoSessionProvider())
        assertEquals(HttpStatusCode.Forbidden, result)
    }

}

