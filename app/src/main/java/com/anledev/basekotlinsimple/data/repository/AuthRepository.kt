package com.anledev.basekotlinsimple.data.repository

import com.anledev.basekotlinsimple.data.UserPreferences
import com.anledev.basekotlinsimple.data.network.AuthApi

class AuthRepository(private val api: AuthApi, private val preferences: UserPreferences) :
    BaseRepository() {
    suspend fun login(email: String, password: String) = safeApiCall {
        api.login(email, password)
    }

    suspend fun saveAuthToken(token: String) {
        preferences.saveAuthToken(token)
    }
}