package net.bernetrollande.emeric.controller

import io.ktor.application.ApplicationCall
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.sessions.clear
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import net.bernetrollande.emeric.UserSession
import net.bernetrollande.emeric.model.Model
import org.mindrot.jbcrypt.BCrypt


class UserController(private val model: Model) {

    fun loginPage(context: ApplicationCall): Any {
        return FreeMarkerContent("login.ftl", mapOf("session" to context.sessions.get<UserSession>()), "e")
    }

    // Connexion : Renvoie le lien de redirection
    fun loginAction(login: String?, password: String?, context: ApplicationCall): String {
        val user = model.getUser(login)
        if (user != null) {

            if (BCrypt.checkpw(password, user.password)) {
                val userSession = UserSession(user.login, user.id)
                context.sessions.set("user", userSession)
                return "/"
            }

        }
        return "/login?error=1"
    }

    // Déconnexion : Renvoie le lien de redirection
    fun disconnectAction(context: ApplicationCall): String {
        context.sessions.clear<UserSession>()
        return "/"
    }
}