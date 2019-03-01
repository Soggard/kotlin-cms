package net.bernetrollande.emeric.controller

import io.ktor.application.ApplicationCall
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.sessions.clear
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import net.bernetrollande.emeric.UserSession
import net.bernetrollande.emeric.model.Model
import org.mindrot.jbcrypt.BCrypt


interface UserController {
    fun loginPage(context: ApplicationCall): Any
    fun loginAction(login: String?, password: String?, context: ApplicationCall): String
    fun disconnectAction(context: ApplicationCall): String
}