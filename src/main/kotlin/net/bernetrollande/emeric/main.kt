package net.bernetrollande.emeric

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.freemarker.FreeMarker
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.http.Parameters
import io.ktor.request.receive
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import io.ktor.sessions.sessions
import net.bernetrollande.emeric.controller.*
import net.bernetrollande.emeric.model.MysqlModel

fun Application.cmsApp(
    articleListController: ArticleListController,
    articleController: ArticleController,
    userController: UserController
) {

    // Installation du moteur de template
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(App::class.java.classLoader, "templates")
    }

    install(Sessions) {
        cookie<UserSession>("user")
    }

    // Installation du système d'authentifiation
    // Documentation : https://ktor.io/servers/features/authentication.html
    /*install(Authentication) {
        basic(name = "bigboss") {
            realm = "CMS"
            validate { credentials ->
                if (credentials.name == credentials.password) {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }
    }*/

    routing {

        /*authenticate("myauth1") {
            get("/authenticated/route1") {
                    // ...
            }
            get("/other/route2") {
                    // ...
            }
        }*/

        get("/") {
            val content = articleListController.startFM(context)
            call.respond(content)
        }

        get("/article/{id}") {
            val id = call.parameters["id"]!!.toInt()
            val content = articleController.startFM(id, context)
            call.respond(content)
        }

        // Page de connexion
        get("/login") {
            val content = userController.loginPage()
            call.respond(content)
        }

        // Action de déconnexion
        post("/login") {
            val params = call.receive< Parameters >()
            val login = params["login"]!!
            val password = params["password"]!!
            val content = userController.loginAction(login, password, context)

            print(call.sessions.get("user"))
            // Todo : Rediriger vers la page "/"
            call.respondText("Hello " + login)
            //call.respond(content)
        }
    }

}


fun main() {

    val model = MysqlModel(
        "jdbc:mysql://localhost/cms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
        "root",
        ""
    )
    val articleListController = ArticleListControllerImpl(model)
    val articleController = ArticleControllerImpl(model)
    val userController = UserController(model)

    embeddedServer(Netty, 8080) {cmsApp(articleListController, articleController, userController)}.start(true)
}