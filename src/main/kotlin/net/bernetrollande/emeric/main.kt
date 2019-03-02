package net.bernetrollande.emeric

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ForwardedHeaderSupport
import io.ktor.freemarker.FreeMarker
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.http.Parameters
import io.ktor.http.content.files
import io.ktor.http.content.static
import io.ktor.request.receive
import io.ktor.response.respondRedirect
import io.ktor.sessions.*
import net.bernetrollande.emeric.controller.*
import net.bernetrollande.emeric.model.MysqlModel

fun Application.cmsApp(
    articleListController: ArticleListController,
    articleController: ArticleController,
    userController: UserController,
    newArticleController: NewArticleController,
    editArticleController: EditArticleController,
    commentController: CommentController
) {

    // Installation du moteur de template
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(App::class.java.classLoader, "templates")
    }

    // Installation du système de session
    install(Sessions) {
        cookie<UserSession>("user") {
            cookie.path = "/"
        }
    }

    install(ForwardedHeaderSupport)

    routing {

        // Fichiers statiques (CSS, JS)
        static("static") {
            files("static")
        }

        // Page d'accueil, liste des articles
        get("/") {
            val content = articleListController.startFM(KtorSessionProvider(call))
            call.respond(content)
        }

        // Affichage d'un article
        get("/article/{id}") {
            println("Display article")
            println(KtorSessionProvider(call).getSession())
            val id = call.parameters["id"]!!.toInt()
            val content = articleController.startFM(id, KtorSessionProvider(call))
            call.respond(content)
        }

        // Connexion
        get("/login") {
            val content = userController.loginPage(KtorSessionProvider(call))
            // TODO : Gérer le cas d'un message d'erreur
            call.respond(content)
        }
        post("/login") {
            val params = call.receive<Parameters>()
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
            val content = newArticleController.newArticlePage(KtorSessionProvider(call))
            call.respond(content)
        }
        post("/new") {
            val params = call.receive<Parameters>()
            val title = params["title"]
            val text = params["text"]
            val content = newArticleController.newArticleAction(title, text, KtorSessionProvider(call))
            call.respondRedirect(content)
        }

        // Edition d'un article
        get("/edit/{id}") {
            val id = call.parameters["id"]!!.toInt()
            val content = editArticleController.editArticlePage(id, KtorSessionProvider(call))
            call.respond(content)
        }
        post("/edit") {
            val params = call.receive<Parameters>()
            val id = params["id"]!!.toInt()
            val title = params["title"]
            val text = params["text"]
            val content = editArticleController.editArticleAction(id, title, text, KtorSessionProvider(call))
            call.respondRedirect(content)
        }

        // Suppression d'un article
        get("/delete/{id}") {
            val id = call.parameters["id"]!!.toInt()
            val content = articleController.deleteArticle(id, KtorSessionProvider(call))
            if (content is String)
                call.respondRedirect(content)
            else
                call.respond(content)
        }

        // Création et suppression de commentaires
        post("/comment/create") {
            val params = call.receive<Parameters>()
            val article = params["article"]!!.toInt()
            val text = params["text"]!!
            println("Create comment")
            println(context.sessions.get("user"))
            val content = commentController.createComment(article, text, KtorSessionProvider(call))
            call.respondRedirect(content)
        }
        get("/comment/delete/{id}") {
            val id = call.parameters["id"]!!.toInt()
            val content = commentController.deleteComment(id, KtorSessionProvider(call))
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
    val userController = UserControllerImpl(model)
    val newArticleController = NewArticleControllerImpl(model)
    val editArticleController = EditArticleControllerImpl(model)
    val commentController = CommentControllerImpl(model)

    embeddedServer(Netty, 8080) {
        cmsApp(
            articleListController,
            articleController,
            userController,
            newArticleController,
            editArticleController,
            commentController
        )
    }.start(true)
}