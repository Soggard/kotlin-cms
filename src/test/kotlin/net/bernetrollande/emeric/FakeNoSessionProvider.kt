package net.bernetrollande.emeric

class FakeNoSessionProvider():
    SessionProvider {
    override fun setSession(userSession: UserSession) {}
    override fun getSession() = null
    override fun clearSession() {}
}