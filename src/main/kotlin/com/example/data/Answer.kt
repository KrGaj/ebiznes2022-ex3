package com.example.data

data class Answer(val text: String, val type: AnswerType, val nextQuestions: List<Question> = emptyList())
