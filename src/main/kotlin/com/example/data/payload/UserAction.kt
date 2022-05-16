package com.example.data.payload

@kotlinx.serialization.Serializable
data class UserAction(
    val action_id: String,
    val block_id: String,
    val text: UserActionText,
    val type: String,
    val action_ts: String
)
