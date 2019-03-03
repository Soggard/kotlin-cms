package net.bernetrollande.emeric.controllers

import io.ktor.http.HttpStatusCode
import net.bernetrollande.emeric.FakeModel
import net.bernetrollande.emeric.FakeNoSessionProvider
import net.bernetrollande.emeric.FakeSessionProvider
import net.bernetrollande.emeric.controller.CommentControllerImpl
import org.junit.Test
import kotlin.test.assertEquals

class CommentTest {

    // Publier un commentaire sur un article existant / inexistant
    @Test
    fun testCreateComment() {
        val model = FakeModel()
        val result = CommentControllerImpl(model).createComment(1, "Lorem ipsum", FakeNoSessionProvider())
        assertEquals("/article/1", result)
    }

    @Test
    fun testCreateCommentFail() {
        val model = FakeModel()
        val result = CommentControllerImpl(model).createComment(3, "Lorem ipsum", FakeNoSessionProvider())
        assertEquals("/article/3", result) // Todo : susceptible d'être modifié si de meilleures redirections sont implémentées
    }

    // Supprimer un commentaire en tant qu'admin/utilisateur
    @Test
    fun testDeleteComment() {
        val model = FakeModel()
        val result = CommentControllerImpl(model).deleteComment(1, FakeSessionProvider())
        assertEquals("/article/1", result) // Todo : susceptible d'être modifié si de meilleures redirections sont implémentées
    }
    @Test
    fun testDeleteCommentNoAdmin() {
        val model = FakeModel()
        val result = CommentControllerImpl(model).deleteComment(1, FakeNoSessionProvider())
        assertEquals(HttpStatusCode.Forbidden, result) // Todo : susceptible d'être modifié si de meilleures redirections sont implémentées
    }


}
