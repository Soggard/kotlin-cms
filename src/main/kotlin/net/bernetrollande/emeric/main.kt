package net.bernetrollande.emeric

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.UserIdPrincipal
import io.ktor.auth.authenticate
import io.ktor.auth.basic
import io.ktor.freemarker.FreeMarker
import io.ktor.request.receiveMultipart
import io.ktor.request.receiveParameters
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty


fun Application.cmsApp(
    articleListController: ArticleListController,
    articleController: ArticleController,
    userController: UserController) {

    // Installation du moteur de template
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(App::class.java.classLoader, "templates")
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
            val content = articleListController.startFM()
            call.respond(content)
        }

        get("/article/{id}") {
            val id = call.parameters["id"]!!.toInt()
            val content = articleController.startFM(id)
            call.respond(content)
        }

        get("/login") {
            val content = userController.loginPage()
            call.respond(content)
        }

        post("/login") {
            val login = call.receiveParameters()["login"]
            val password = call.receiveParameters()["password"]
            println(call.receiveParameters())
            //val content = userController.loginAction(login, password)
            // Todo : Rediriger vers la page "/"
        }
    }

}


fun main() {

    val model = MysqlModel("jdbc:mysql://localhost/cms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "")
    val articleListController = ArticleListControllerImpl(model)
    val articleController = ArticleControllerImpl(model)
    val userController = UserController(model)

    embeddedServer(Netty, 8080) {cmsApp(articleListController, articleController, userController)}.start(true)
}