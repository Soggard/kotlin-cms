package net.bernetrollande.emeric

import net.bernetrollande.emeric.UserSession

interface SessionProvider {
    fun getSession(): UserSession?
}