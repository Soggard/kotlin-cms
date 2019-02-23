package net.bernetrollande.emeric.controller

import io.ktor.freemarker.FreeMarkerContent
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.sessions.sessions
import net.bernetrollande.emeric.UserSession
import net.bernetrollande.emeric.model.Model
import java.security.AccessControlContext

class UserController(private val model: Model) {

    fun loginPage (): Any {
        return FreeMarkerContent("login.ftl", null, "e")
    }

    // Login : Renvoie le lien de la redirection
    fun loginAction (login: String, password: String, context: ApplicationCall): String {
        val user = model.getUser(login, password)
        if (user != null) {
            val userSession = UserSession(user.login, user.id)
            context.sessions.set("user", userSession)
            return "/"
        }
        // TODO : Rediriger vers la page de connexion avec un message d'erreur
        return "/login"
    }

    fun disconnectAction (context: ApplicationCall): Any {
        context.sessions.set("user", null)
        return FreeMarkerContent("index.ftl", null, "e")
    }
}