package net.bernetrollande.emeric.controllers

import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import net.bernetrollande.emeric.FakeModel
import net.bernetrollande.emeric.FakeNoSessionProvider
import net.bernetrollande.emeric.FakeSessionProvider
import net.bernetrollande.emeric.controller.NewArticleControllerImpl
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NewArticleTest {

    // Affichage de la page de création d'article en tant qu'admin / invité
    @Test
    fun testNewArticlePage() {
        val model = FakeModel()
        val result = NewArticleControllerImpl(model).newArticlePage( FakeSessionProvider())
        assertTrue(result is FreeMarkerContent)
    }

    @Test
    fun testNewArticlePageNoAdmin() {
        val model = FakeModel()
        val result = NewArticleControllerImpl(model).newArticlePage(FakeNoSessionProvider())
        assertEquals(HttpStatusCode.Forbidden, result)
    }

    // Création d'un article  en tant qu'admin / invité
    @Test
    fun testNewArticleAction() {
        val model = FakeModel()
        val result = NewArticleControllerImpl(model).newArticleAction("test", "Lorem ipsum", FakeSessionProvider())
        assertEquals("/article/1", result) // Todo : susceptible d'être modifié si de meilleures redirections sont implémentées
    }

    @Test
    fun testNewArticleActionNoAdmin() {
        val model = FakeModel()
        val result = NewArticleControllerImpl(model).newArticleAction("test", "Lorem ipsum", FakeNoSessionProvider())
        assertEquals(HttpStatusCode.Forbidden, result)
    }

    // Création d'un article  invalide
    @Test
    fun testNewFakeArticleAction() {
        val model = FakeModel()
        val result = NewArticleControllerImpl(model).newArticleAction("test2", null, FakeSessionProvider())
        assertEquals("/new?error=1", result) // Todo : susceptible d'être modifié si de meilleures redirections sont implémentées
    }


}
