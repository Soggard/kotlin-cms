package net.bernetrollande.emeric.controller

import io.ktor.application.ApplicationCall
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.sessions.clear
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import net.bernetrollande.emeric.SessionProvider
import net.bernetrollande.emeric.UserSession
import net.bernetrollande.emeric.model.Model
import org.mindrot.jbcrypt.BCrypt


class UserControllerImpl(private val model: Model) : UserController {

    override fun loginPage(sessionProvider: SessionProvider): Any {
        return FreeMarkerContent("login.ftl", mapOf("session" to sessionProvider.getSession()), "e")
    }

    // Connexion : Renvoie le lien de redirection
    override fun loginAction(login: String?, password: String?, sessionProvider: SessionProvider): String {
        val user = model.getUser(login)
        if (user != null) {

            if (BCrypt.checkpw(password, user.password)) {
                val userSession = UserSession(user.login, user.id)
                sessionProvider.setSession(userSession)
                return "/"
            }

        }
        return "/login?error=1"
    }

    // Déconnexion : Renvoie le lien de redirection
    override fun disconnectAction(sessionProvider: SessionProvider): String {
        sessionProvider.clearSession()
        return "/"
    }
}