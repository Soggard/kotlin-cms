package net.bernetrollande.emeric.controllers

import io.ktor.freemarker.FreeMarkerContent
import net.bernetrollande.emeric.FakeModel
import net.bernetrollande.emeric.FakeSessionProvider
import net.bernetrollande.emeric.controller.ArticleListControllerImpl
import net.bernetrollande.emeric.controller.UserControllerImpl
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class UserTest {

    // Affichage de la page de connexion
    @Test
    fun testLoginPage() {
        val model = FakeModel()
        val result = UserControllerImpl(model).loginPage(FakeSessionProvider())
        assertTrue(result is FreeMarkerContent)
    }

    // Connexion avec un identifiant correct / erroné
    /*@Test
    fun testLoginActionCorrect() {
        val model = FakeModel()
        val result = UserControllerImpl(model).loginAction("test", "salomon", FakeSessionProvider())
        assertTrue(result is FreeMarkerContent)
    }

    @Test
    fun testLoginActionIncorrect() {
        val model = FakeModel()
        val result = UserControllerImpl(model).loginAction("alain", "deloin", FakeSessionProvider())
        assertTrue(result is FreeMarkerContent)
    }

    @Test
    fun testLoginActionIncorrectPwd() {
        val model = FakeModel()
        val result = UserControllerImpl(model).loginAction("test", "toto", FakeSessionProvider())
        assertTrue(result is FreeMarkerContent)
    }

    @Test
    fun testLoginActionIncorrect() {
        val model = FakeModel()
        val result = UserControllerImpl(model).disconnectAction(FakeSessionProvider())
        assertEquals("/", result)
    }
    */

}
