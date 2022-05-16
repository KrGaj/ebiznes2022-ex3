package com.example.data.payload

@kotlinx.serialization.Serializable
data class UserActionText(
    val type: String,
    val text: String,
    val emoji: Boolean
)
