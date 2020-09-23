package com.anledev.basekotlinsimple.response

data class User(
    val access_token: String,
    val create_at: String,
    val email: String,
    val email_verified_at: Any,
    val id: Int,
    val name: String,
    val update_at: String
)