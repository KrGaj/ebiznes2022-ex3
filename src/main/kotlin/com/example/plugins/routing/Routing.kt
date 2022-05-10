package com.example.plugins.routing

import com.slack.api.Slack
import com.slack.api.model.Message
import com.slack.api.methods.kotlin_extension.request.chat.blocks
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

fun Application.configureRouting() {
    val token = System.getenv("SLACK_BOT_TOKEN")

    routing {
        post("/") {
            val params = call.receiveParameters()
            print(params)

            val slack = Slack.getInstance()

            val response = slack.methods(token).chatPostMessage {
                it.channel("#general").text("Hello :wave:")
            }

            call.respondText("Response is: $response")
        }

        post("/hello") {
            val slack = Slack.getInstance()

            val response = slack.methods(token).chatPostMessage {
                it.channel("#general").blocks {
                    section {
                        text("plain_text","Hej :wave:. Co chcesz wiedzieć?")
                    }
                    actions {
                        button {
                            text("Co tam?")
                            actionId("Co tam?")
                        }
                        button {
                            text("Jak to jest być botem? Dobrze?")
                            actionId("Jak to jest być botem? Dobrze?")
                        }
                    }
                }
            }

            call.respondText("Response is: $response")
        }

        post("/whatsup") {
            val slack = Slack.getInstance()

            val response = slack.methods(token).chatPostMessage {
                it.channel("#general").text("Nudy trochę...")
            }

            call.respond(response)
        }
    }
}
