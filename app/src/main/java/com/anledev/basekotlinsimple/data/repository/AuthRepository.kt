package com.anledev.basekotlinsimple.data.repository

import com.anledev.basekotlinsimple.data.network.AuthApi

class AuthRepository(private val api: AuthApi) : BaseRepository() {
    suspend fun login(email: String, password: String) = safeApiCall {
        api.login(email, password)
    }
}