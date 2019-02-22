package net.bernetrollande.emeric

import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ArticleTest {

    @Test
    fun testExistingArticle() {
        val model = TestModel()
        val result = ArticleControllerImpl(model).startFM(1)
        assertTrue(result is FreeMarkerContent)
    }

    @Test
    fun testFakeArticle() {
        val model = TestModel()
        val result =ArticleControllerImpl(model).startFM(2)
        assertEquals(HttpStatusCode.NotFound, result)

    }

}

