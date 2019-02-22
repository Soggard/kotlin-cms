package net.bernetrollande.emeric

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ModelTest {

    // TODO : Supprimer si inutile
/*
    val model = MysqlModel("jdbc:h2:mem:cms;MODE=MYSQL", null, null)

    @Before
    fun initDB() {
        model.connectionPool.use {connection ->
            connection.prepareStatement(
                """DROP TABLE IF EXISTS article ;
                CREATE TABLE article (id int(11) NOT NULL, title varchar(60) COLLATE utf8_bin NOT NULL, text text COLLATE utf8_bin NOT NULL);"""
            ).use { stmt ->
                stmt.execute()
            }
        }
    }

    @Test
    fun testArticleInDB() {
        val article = model.getArticle(1)
        assertNotNull(article)
        assertEquals(1, article.id)
        assertEquals("Article 1", article.title)
    }

    @Test
    fun testFakeArticleInDB() {
        val article = model.getArticle(3)
        assertEquals(null, article)
    }
*/
}