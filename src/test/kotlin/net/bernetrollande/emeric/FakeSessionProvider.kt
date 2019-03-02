package net.bernetrollande.emeric

class FakeSessionProvider():
    SessionProvider {
    override fun getSession() = UserSession("salomon", 2)
}