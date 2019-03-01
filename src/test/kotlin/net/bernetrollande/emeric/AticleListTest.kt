package net.bernetrollande.emeric

import io.ktor.freemarker.FreeMarkerContent
import net.bernetrollande.emeric.controller.ArticleListControllerImpl
import org.junit.Test
import kotlin.test.assertTrue

class AticleListTest {

    @Test
    fun testArticleList() {
        val model = TestModel()
        val result = ArticleListControllerImpl(model).startFM(FakeSessionProvider())
        assertTrue(result is FreeMarkerContent)

    }

}
