package net.bernetrollande.emeric

class FakeNoSessionProvider():
    SessionProvider {
    override fun getSession() = null
}