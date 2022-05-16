package com.example.plugins.routing

import com.example.data.AnswerType
import com.example.data.Question
import com.example.data.QuestionAnswer
import com.example.data.payload.Payload
import com.slack.api.Slack
import com.slack.api.methods.kotlin_extension.request.chat.blocks
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

fun Application.configureRouting() {
    val token = System.getenv("SLACK_BOT_TOKEN")

    routing {
        post("/") {
            val json = Json {
                ignoreUnknownKeys = true
            }

            val payload = call.receiveParameters()["payload"]!!

            val payloadParsed = json.decodeFromString<Payload>(payload)

            println("Parsed data:")
            println(payloadParsed)

            val slack = Slack.getInstance()

            val question = Question(payloadParsed.actions[0].text.text)
            val answer = QuestionAnswer.qustionsAndAnswers[question]

            val response = slack.methods(token).chatPostMessage {
                when (answer!!.type) {
                    AnswerType.LAST_ANSWER -> it.channel("#general").text(answer.text)
                    AnswerType.NOT_LAST_ANSWER -> {
                        it.channel("#general").blocks {
                            section {
                                text("plain_text", answer.text)
                            }

                            actions {
                                for (nextQuestion in answer.nextQuestions) {
                                    button {
                                        text(nextQuestion.text)
                                        actionId(nextQuestion.text)
                                    }
                                }
                            }
                        }
                    }
                }
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

            call.respondText("")
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
