package com.anledev.basekotlinsimple.repository

import com.anledev.basekotlinsimple.network.AuthApi

class AuthRepository(private val api: AuthApi) : BaseRepository() {
    suspend fun login(email: String, password: String) = safeApiCall {
        api.login(email, password)
    }
}