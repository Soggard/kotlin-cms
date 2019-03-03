package net.bernetrollande.emeric

import io.ktor.application.ApplicationCall
import io.ktor.sessions.clear
import io.ktor.sessions.get
import io.ktor.sessions.sessions

class KtorSessionProvider(private val call: ApplicationCall):
    SessionProvider {
    override fun getSession() = call.sessions.get<UserSession>()
    override fun setSession(userSession: UserSession) = call.sessions.set("user", userSession)
    override fun clearSession() = call.sessions.clear<UserSession>()
}