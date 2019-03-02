package net.bernetrollande.emeric.controllers

import io.ktor.freemarker.FreeMarkerContent
import net.bernetrollande.emeric.FakeModel
import net.bernetrollande.emeric.FakeSessionProvider
import net.bernetrollande.emeric.controller.ArticleListControllerImpl
import org.junit.Test
import kotlin.test.assertTrue

class ArticleListTest {

    @Test
    fun testArticleList() {
        val model = FakeModel()
        val result = ArticleListControllerImpl(model).startFM(FakeSessionProvider())
        assertTrue(result is FreeMarkerContent)
    }

}
