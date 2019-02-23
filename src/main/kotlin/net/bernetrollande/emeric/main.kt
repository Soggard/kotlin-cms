package net.bernetrollande.emeric

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.client.features.HttpRedirect
import io.ktor.freemarker.FreeMarker
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.http.Parameters
import io.ktor.request.receive
import io.ktor.response.respondRedirect
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import io.ktor.sessions.sessions
import net.bernetrollande.emeric.controller.*
import net.bernetrollande.emeric.model.MysqlModel

fun Application.cmsApp(
    articleListController: ArticleListController,
    articleController: ArticleController,
    userController: UserController,
    newArticleController: NewArticleController,
    editArticleController: EditArticleController
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

        // Index, liste des articles
        get("/") {
            val content = articleListController.startFM(context)
            call.respond(content)
        }

        // Affichage d'un article
        get("/article/{id}") {
            val id = call.parameters["id"]!!.toInt()
            val content = articleController.startFM(id, context)
            call.respond(content)
        }

        // Connexion
        get("/login") {
            val content = userController.loginPage()
            // TODO : Gérer le cas d'un message d'erreur
            call.respond(content)
        }
        post("/login") {
            val params = call.receive< Parameters >()
            val login = params["login"]
            val password = params["password"]
            val content = userController.loginAction(login, password, context)
            call.respondRedirect(content)
        }

        // Lien de déconnexion
        get("/disconnect") {
            val content = userController.disconnectAction(context)
            call.respondRedirect(content)
        }

        // Création d'un article
        get("/new") {
            val content = newArticleController.newArticlePage(context)
            call.respond(content)
        }
        post("/new") {
            val params = call.receive< Parameters >()
            val title = params["title"]
            val text = params["text"]
            val content = newArticleController.newArticleAction(title, text, context)
            call.respondRedirect(content)
        }

        // Edition d'un article
        get("/edit/{id}") {
            val id = call.parameters["id"]!!.toInt()
            val content = editArticleController.editArticlePage(id, context)
            call.respond(content)
        }
        post("/edit") {
            val params = call.receive< Parameters >()
            val id = params["id"]!!.toInt()
            val title = params["title"]
            val text = params["text"]
            val content = editArticleController.editArticleAction(id, title, text, context)
            call.respondRedirect(content)
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
    val newArticleController = NewArticleController(model)
    val editArticleController = EditArticleController(model)

    embeddedServer(Netty, 8080) {cmsApp(articleListController, articleController, userController, newArticleController, editArticleController)}.start(true)
}