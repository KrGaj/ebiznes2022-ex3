package com.example.plugins.routing

import com.slack.api.Slack
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*

fun Application.configureRouting() {
    val token = System.getenv("SLACK_BOT_TOKEN")

    routing {
        get("/") {
            val slack = Slack.getInstance()

            val response = slack.methods(token).chatPostMessage {
                it.channel("#general").text("Hello :wave:")
            }

            call.respondText("Response is: $response")
        }

        post("/") {
            val slack = Slack.getInstance()

            val response = slack.methods(token).chatPostMessage {
                it.channel("#general").text("Hello :wave:. Nice to see you!")
            }

            call.respondText("Response is: $response")
        }
    }
}
