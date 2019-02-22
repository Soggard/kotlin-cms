package net.bernetrollande.emeric

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ArticleTest {

    @Test
    fun testExistingArticle() {
        val model = mock<Model> {
            on { getArticle(1) } doReturn Article(1, "title", "text")
        }
        val result = ArticleControllerImpl(model).startFM(1)
        assertTrue(result is FreeMarkerContent)
    }

    @Test
    fun testFakeArticle() {
        val model = mock<Model> {}
        val result = ArticleControllerImpl(model).startFM(2)
        assertEquals(HttpStatusCode.NotFound, result)

    }

}

