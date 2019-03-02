package net.bernetrollande.emeric.controllers

import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import net.bernetrollande.emeric.FakeModel
import net.bernetrollande.emeric.FakeNoSessionProvider
import net.bernetrollande.emeric.FakeSessionProvider
import net.bernetrollande.emeric.controller.EditArticleControllerImpl
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EditArticleTest {

    // Affichage de la page d'étidition (Article existant/inexistant) en tant qu'admin
    @Test
    fun testEditArticlePage() {
        val model = FakeModel()
        val result = EditArticleControllerImpl(model).editArticlePage(1, FakeSessionProvider())
        assertTrue(result is FreeMarkerContent)
    }

    @Test
    fun testEditFakeArticlePage() {
        val model = FakeModel()
        val result = EditArticleControllerImpl(model).editArticlePage(3, FakeSessionProvider())
        assertEquals(HttpStatusCode.NotFound, result)
    }

    // Affichage de la page d'étidition (Article existant/inexistant) en tant qu'invité
    @Test
    fun testEditArticlePageNoAdmin() {
        val model = FakeModel()
        val result = EditArticleControllerImpl(model).editArticlePage(1, FakeNoSessionProvider())
        assertEquals(HttpStatusCode.Forbidden, result)
    }

    // Edition d'un article existant/inexistant en tant qu'invité
    @Test
    fun testEditArticleActionNoAdmin() {
        val model = FakeModel()
        val result = EditArticleControllerImpl(model).editArticleAction(1, "test", "Lorem ipsum", FakeNoSessionProvider())
        assertEquals("/", result) // Todo : susceptible d'être modifié si de meilleures redirections sont implémentées
    }

    @Test
    fun testEditFakeArticleActionNoAdmin() {
        val model = FakeModel()
        val result = EditArticleControllerImpl(model).editArticleAction(3, "test", "Lorem ipsum", FakeNoSessionProvider())
        assertEquals("/", result) // Todo : susceptible d'être modifié si de meilleures redirections sont implémentées
    }

    // Edition d'un article existant/inexistant en tant qu'admin
    @Test
    fun testEditArticleAction() {
        val model = FakeModel()
        val result = EditArticleControllerImpl(model).editArticleAction(1, "test", "Lorem ipsum", FakeSessionProvider())
        assertEquals("/article/1", result) // Todo : susceptible d'être modifié si de meilleures redirections sont implémentées
    }

    @Test
    fun testEditFakeArticleAction() {
        val model = FakeModel()
        val result = EditArticleControllerImpl(model).editArticleAction(3, "test", "Lorem ipsum", FakeSessionProvider())
        assertEquals("/article/3", result) // Todo : susceptible d'être modifié si de meilleures redirections sont implémentées
    }


}
