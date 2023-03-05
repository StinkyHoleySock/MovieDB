package com.example.moviedb.model.authorization

data class RequestToken(
    val expires_at: String,
    val request_token: String,
    val success: Boolean
)