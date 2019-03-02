package net.bernetrollande.emeric.controllers

import net.bernetrollande.emeric.FakeModel
import net.bernetrollande.emeric.FakeNoSessionProvider
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

    // Supprimer un commentaire
    @Test
    fun testDeleteComment() {
        val model = FakeModel()
        val result = CommentControllerImpl(model).deleteComment(1, FakeNoSessionProvider())
        assertEquals("/", result) // Todo : susceptible d'être modifié si de meilleures redirections sont implémentées
    }


}
