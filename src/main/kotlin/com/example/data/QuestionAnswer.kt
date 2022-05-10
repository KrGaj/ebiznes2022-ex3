package com.example.data

class QuestionAnswer {
    private val qustionsAndAnswers = mapOf(
        Question("Co tam?") to Pair(Answer("Dobrze, a u ciebie?", AnswerType.NOT_LAST_ANSWER),
            listOf(Question("Też dobrze"), Question("Ujdzie"))),
        Question("Też dobrze") to Answer("To super!", AnswerType.LAST_ANSWER),
        Question("Ujdzie") to Answer("No to trzymaj się", AnswerType.LAST_ANSWER),
        Question("Jak to jest być botem? Dobrze?") to Pair(Answer("To nie jest tak, że dobrze, czy nie dobrze...", AnswerType.NOT_LAST_ANSWER),
            listOf(Question("Dość!"))),
        Question("Dość!") to Answer("Bądźmy łagodni", AnswerType.LAST_ANSWER)
    )
}