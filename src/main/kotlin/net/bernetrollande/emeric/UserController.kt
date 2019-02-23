package net.bernetrollande.emeric

import io.ktor.freemarker.FreeMarkerContent

class UserController(private val model: Model) {

    fun loginPage (): Any {
        return FreeMarkerContent("login.ftl", null, "e")
    }

    fun loginAction (login: String, password: String): Any {
        println(login+ password)
        return FreeMarkerContent("login.ftl", null, "e")
    }
}