package net.bernetrollande.emeric

import org.junit.Test
import kotlin.test.assertEquals

fun generateArticle()

class ArticleTest {

    @Test
    fun testExistingArticle() {
        val model = MysqlModel()
        assertEquals("Mr Hello", mrize("Hello"))
        assertEquals("Mr Renard", "Mr Renard")
    }

}