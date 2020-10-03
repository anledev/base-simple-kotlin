package com.anledev.basekotlinsimple.data.network

import com.anledev.basekotlinsimple.data.response.LoginResponse
import retrofit2.http.GET

interface UserApi {
    @GET("user")
    suspend fun getUser(): LoginResponse
}