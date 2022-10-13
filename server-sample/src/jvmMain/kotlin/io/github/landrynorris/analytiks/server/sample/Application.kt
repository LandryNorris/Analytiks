package io.github.landrynorris.analytiks.server.sample

import io.github.landrynorris.analytiks.Analytiks
import io.github.landrynorris.analytiks.files.FileLogger
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import okio.FileSystem

@Suppress("unused")
fun Application.module() {
    val logger = FileLogger("logs.txt", FileSystem.SYSTEM)

    Analytiks.addLoggers(logger)

    routing {
        route("/logPathParam/{param}") {
            get {
                val param = call.parameters["param"]

                Analytiks.logEvent("Got path param", mapOf("param" to param))
            }

            post {
                val body = call.receive<String>()

                Analytiks.logEvent("Post body", mapOf("body" to body))
            }
        }
    }

    environment.monitor.subscribe(ApplicationStopping) {
        logger.close() //clean up our logger when done
    }
}
