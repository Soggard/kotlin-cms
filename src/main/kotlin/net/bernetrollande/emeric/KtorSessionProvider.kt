package net.bernetrollande.emeric

import io.ktor.application.ApplicationCall
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import net.bernetrollande.emeric.SessionProvider
import net.bernetrollande.emeric.UserSession

class KtorSessionProvider(private val call: ApplicationCall):
    SessionProvider {
    override fun getSession() = call.sessions.get<UserSession>()
}