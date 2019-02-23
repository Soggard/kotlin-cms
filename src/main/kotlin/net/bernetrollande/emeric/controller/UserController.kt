package net.bernetrollande.emeric.controller

import io.ktor.application.ApplicationCall
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.sessions.sessions
import net.bernetrollande.emeric.UserSession
import net.bernetrollande.emeric.model.Model

class UserController(private val model: Model) {

    fun loginPage (): Any {
        return FreeMarkerContent("login.ftl", null, "e")
    }

    // Connexion : Renvoie le lien de redirection
    fun loginAction (login: String?, password: String?, context: ApplicationCall): String {
        val user = model.getUser(login, password)
        if (user != null) {
            val userSession = UserSession(user.login, user.id)
            context.sessions.set("user", userSession)
            return "/"
        }
        return "/login?error=1"
    }

    // Déconnexion : Renvoie le lien de redirection
    fun disconnectAction (context: ApplicationCall): String {
        context.sessions.set("user", null)
        return "/"
    }
}