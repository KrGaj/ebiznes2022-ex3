package com.example.data.payload

@kotlinx.serialization.Serializable
data class Payload(val actions: List<UserAction>)