package net.bernetrollande.emeric

class FakeSessionProvider():
    SessionProvider {
    override fun setSession(userSession: UserSession) {}
    override fun getSession() = UserSession("salomon", 2)
    override fun clearSession() {}
}