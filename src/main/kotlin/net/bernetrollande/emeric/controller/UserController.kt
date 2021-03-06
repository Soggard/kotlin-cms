package net.bernetrollande.emeric.controller

import io.ktor.application.ApplicationCall
import net.bernetrollande.emeric.SessionProvider


interface UserController {
    fun loginPage(sessionProvider: SessionProvider): Any
    fun loginAction(login: String?, password: String?, sessionProvider: SessionProvider): String
    fun disconnectAction(sessionProvider: SessionProvider): String
}