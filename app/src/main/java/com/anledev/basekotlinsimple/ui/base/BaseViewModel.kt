package com.anledev.basekotlinsimple.ui.base

import androidx.lifecycle.ViewModel
import com.anledev.basekotlinsimple.data.network.UserApi
import com.anledev.basekotlinsimple.data.repository.BaseRepository

abstract class BaseViewModel (private val repository: BaseRepository) : ViewModel(){
    suspend fun logout(api: UserApi) = repository.logout(api)
}