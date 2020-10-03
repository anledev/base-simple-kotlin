package com.anledev.basekotlinsimple.data.repository

import com.anledev.basekotlinsimple.data.UserPreferences
import com.anledev.basekotlinsimple.data.network.AuthApi
import com.anledev.basekotlinsimple.data.network.UserApi

class UserRepository(private val api: UserApi) :
    BaseRepository() {
    suspend fun getUser() = safeApiCall {
        api.getUser()
    }
}